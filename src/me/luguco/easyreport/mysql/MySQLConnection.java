package me.luguco.easyreport.mysql;

import me.luguco.easyreport.main.EasyReport;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by luguco on 20.06.2017.
 */
public class MySQLConnection {

    private static EasyReport plugin;

    public MySQLConnection(EasyReport main) {
        this.plugin = main;
    }

    public static Connection con;
    static String host = plugin.mycfg.getString("MySQL.Host");
    static String port = plugin.mycfg.getString("MySQL.Port");
    static String database = plugin.mycfg.getString("MySQL.Database");
    static String username = plugin.mycfg.getString("MySQL.Username");
    static String password = plugin.mycfg.getString("MySQL.Password");

    public static void connect(){

        if(!isConnected()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                    Bukkit.getConsoleSender().sendMessage("§aVerbindung hergestellt!");
                } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("§cMySQL Verbindung konnte nicht hergestellt werden!");
                Bukkit.getPluginManager().disablePlugin(plugin);
            }
        }
    }
    public static void disconnect(){

        if(isConnected()){
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage("§cVerbindung getrennt!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean isConnected(){
        return (con == null ? false : true);
    }

    public static Connection getConnection(){
        return con;
    }

}
