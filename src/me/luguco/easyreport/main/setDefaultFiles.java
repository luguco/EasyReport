package me.luguco.easyreport.main;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by luguco on 20.06.2017.
 */
public class setDefaultFiles {

    private static EasyReport plugin;

    public setDefaultFiles(EasyReport main){
        this.plugin = main;
    }
    public static void setFiles(){
        if(!plugin.mycfg.contains("MySQL.Host")){
            plugin.mycfg.set("MySQL.Host", "localhost");
        }
        if(!plugin.mycfg.contains("MySQL.Port")){
            plugin.mycfg.set("MySQL.Port", "3306");
        }
        if(!plugin.mycfg.contains("MySQL.Database")){
            plugin.mycfg.set("MySQL.Database", "localhost");
        }
        if(!plugin.mycfg.contains("MySQL.Username")){
            plugin.mycfg.set("MySQL.Username", "root");
        }
        if(!plugin.mycfg.contains("MySQL.Password")){
            plugin.mycfg.set("MySQL.Password", "password");
        }

        try {
            plugin.mycfg.save(plugin.mysql);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> lores = new ArrayList<>();

        lores.add("§cReporteter Spieler wurde gekickt!");
        lores.add("§aReport wird bearbeitet!");
        lores.add("§4Warnung: §cDu wurdest reportet! Tu das nicht wieder!");
        lores.add("§cReport wurde bearbeitet!");
        if(!plugin.msgcfg.contains("Messages.Report")){
            plugin.msgcfg.set("Messages.Report", lores);
        }
        if(!plugin.msgcfg.contains("Messages.neuerReport")){
            plugin.msgcfg.set("Messages.neuerReport", "§cEin neuer Report ist eingegangen!");
        }
        if(!plugin.msgcfg.contains("Messages.ReportBestätigung")){
            plugin.msgcfg.set("Messages.ReportBestätigung", "§aDu hast %target% mit der Begründung %reason% und dem Beweis(optional) %proof% reportet!");
        }
        if(!plugin.msgcfg.contains("Messages.CmdUsage")){
            plugin.msgcfg.set("Messages.CmdUsage", "§cNutze: /report Spieler Grund (optional)Beweis!");
        }

        try {
            plugin.msgcfg.save(plugin.messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}