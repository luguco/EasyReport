package me.luguco.easyreport.main;

import me.luguco.easyreport.commands.Report;
import me.luguco.easyreport.commands.Reports;
import me.luguco.easyreport.mysql.MySQLConnection;
import me.luguco.easyreport.mysql.MySQLCreateTable;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


/**
 * Created by luguco on 20.06.2017.
 */
public class EasyReport extends JavaPlugin{

    public File mysql = new File("plugins/EasyReport", "mysql.yml");
    public YamlConfiguration mycfg = YamlConfiguration.loadConfiguration(mysql);

    public File messages = new File("plugins/EasyReport", "messages.yml");
    public YamlConfiguration msgcfg = YamlConfiguration.loadConfiguration(messages);

    @Override
    public void onEnable(){
        Bukkit.getConsoleSender().sendMessage("§a[EasyReport] enabled!");

        loadConfig();
        registerCommands();
        loadClasses();
        setDefaultFiles.setFiles();
        MySQLConnection.connect();
        MySQLCreateTable.CreateTables();

    }

    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage("§c[EasyReport] disabled!");
        MySQLConnection.disconnect();

    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void registerCommands(){

        Report cReport= new Report(this);
        getCommand("report").setExecutor(cReport);

        Reports cReports = new Reports(this);
        getCommand("reports").setExecutor(cReports);

    }

    public void loadClasses(){
        new setDefaultFiles(this);
        new MySQLConnection(this);
    }
}
