package me.luguco.easyreport.commands;

import me.luguco.easyreport.main.EasyReport;
import me.luguco.easyreport.mysql.MySQLConnection;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by luguco on 20.06.2017.
 */


public class Report implements CommandExecutor{


    public String proof;
    PreparedStatement ps;
    private EasyReport plugin;
    public Report(EasyReport easyReport) {
        this.plugin = easyReport;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if(!(sender instanceof Player)){
            sender.sendMessage("You must be a player to report someone!");
            return true;
        }

        Player reporter = (Player) sender;

        if(!(args.length > 1)){
            reporter.sendMessage(plugin.msgcfg.getString("Messages.CmdUsage"));
            return true;
        }

        String target = Bukkit.getOfflinePlayer(args[0]).toString();
        String taruuid = Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString();
        String reason = args[1];

        if(args.length > 2){

            for(int i = 1; i < args.length; i++){
               proof = proof + args[1] + " ";
            }

            if(!MySQLConnection.isConnected()){
                sender.sendMessage("§cEin Fehler ist aufgetreten! Bitte kontaktiere eine Teammitglied!");
                return true;
            }

            String sendermessage = plugin.msgcfg.getString("ReportBestätigung");
            sendermessage.replaceAll("%target%", target);
            sendermessage.replaceAll("%reason%", reason);
            sendermessage.replaceAll("%proof%", proof);
            sender.sendMessage(sendermessage);

            try {
                ps = MySQLConnection.getConnection().prepareStatement("INSERT INTO ReportedPlayer (`Player`,`UUID`,`Reason`,`Proof`,`Reporter`,`Report_Seen`,`Supporter`) VALUES (?,?,?,?,?,'false','NULL')");
                ps.setString(1, target);
                ps.setString(2, taruuid);
                ps.setString(3, reason);
                ps.setString(4, proof);
                ps.setString(5, sender.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for(Player admins : Bukkit.getOnlinePlayers()){
                if(admins.hasPermission("easyreport.reportmessage")){
                    admins.sendMessage(plugin.msgcfg.getString("Messages.neuerReport"));
                }
            }
        }



        return true;
    }
}
