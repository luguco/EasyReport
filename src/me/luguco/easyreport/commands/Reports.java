package me.luguco.easyreport.commands;

import me.luguco.easyreport.main.EasyReport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by luguco on 20.06.2017.
 */
public class Reports implements CommandExecutor{
    private EasyReport plugin;
    public Reports(EasyReport main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return true;
    }
}
