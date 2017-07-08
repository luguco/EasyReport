package me.luguco.easyreport.commands;

import me.luguco.easyreport.main.EasyReport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by luguco on 20.06.2017.
 */
public class Report implements CommandExecutor{
    private EasyReport plugin;
    public Report(EasyReport easyReport) {
        this.plugin = easyReport;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return true;
    }
}
