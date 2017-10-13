package me.luguco.easyreport.commands;

import me.luguco.easyreport.main.EasyReport;
import me.luguco.easyreport.readData.ReportsCounting;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.luguco.easyreport.mysql.MySQLConnection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by luguco on 20.06.2017.
 */
public class Reports implements CommandExecutor{

    private EasyReport plugin;
    public Reports(EasyReport main) {
        this.plugin = main;
    }

    PreparedStatement ps;
    Inventory inv;
    int slot;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        Player admin = (Player) sender;

        createinventory();

        if(!admin.hasPermission("easyreport.seereports")){
            admin.sendMessage("§cDu hast keine Rechte, um diesen Command zu benutzen!");
            return true;
        }

        if(ReportsCounting.counts() != 0) {
            try {
                ps = MySQLConnection.getConnection().prepareStatement("SELECT * FROM ReportedPlayer");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    private void createinventory(){
        int i = ReportsCounting.counts();
        inv = Bukkit.createInventory(null, 54, "§aReports");
        if(i == 0){
            ItemStack is = new ItemStack(Material.BARRIER, 1);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName("§cKeine Reports");
            is.setItemMeta(im);
            inv.setItem(4, is);
        }
        if(i > 44){

            ItemStack is2 = new ItemStack(Material.STAINED_GLASS_PANE, 1);
            ItemMeta im2 = is2.getItemMeta();
            im2.setDisplayName("§aNächste Seite");
            is2.setItemMeta(im2);
            inv.setItem(51, is2);
        }
    }
}
