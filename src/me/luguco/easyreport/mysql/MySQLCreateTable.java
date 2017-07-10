package me.luguco.easyreport.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by luguco on 08.07.2017.
 */
public class MySQLCreateTable {

    public static void CreateTables(){
        if(MySQLConnection.isConnected()){

            PreparedStatement ps;

            try {
                ps = MySQLConnection.getConnection().prepareStatement("SELECT * FROM `ReportedPlayer`");
                ps.executeQuery();
            } catch (SQLException e) {

                try {
                    ps = MySQLConnection.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `ReportedPlayer` (`Player` VARCHAR(100),`UUID` VARCHAR (100),`Reason` VARCHAR(100),`Proof` VARCHAR(100),`Reporter` VARCHAR(100),`Date` TIMESTAMP default CURRENT_TIMESTAMP,`Report_Seen` VARCHAR(100),`Supporter` VARCHAR(100))");
                    ps.executeUpdate();
                    ps = MySQLConnection.getConnection().prepareStatement("ALTER TABLE ReportedPlayer AUTO_INCREMENT = 1;");
                    ps.executeUpdate();
                    ps = MySQLConnection.getConnection().prepareStatement("ALTER TABLE ReportedPlayer ADD `Count` INT PRIMARY KEY AUTO_INCREMENT FIRST");
                    ps.executeUpdate();

                    ps = MySQLConnection.getConnection().prepareStatement("INSERT INTO ReportedPlayer (`Player`,`UUID`,`Reason`,`Proof`,`Reporter`,`Report_Seen`,`Supporter`) VALUES ('Testplayer','TestUUID','Hacking','www.testproof.de','Testreporter','false','NULL')");
                    ps.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

            try {
                ps = MySQLConnection.getConnection().prepareStatement(" SELECT * FROM `ReportCount`");
                ps.executeQuery();
            } catch (SQLException e) {
                try {
                    ps = MySQLConnection.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `ReportCount` (`Player` VARCHAR(100),`UUID` VARCHAR(100),`Counter` INT(100))");
                    ps.executeUpdate();
                    ps = MySQLConnection.getConnection().prepareStatement("ALTER TABLE `ReportCount` AUTO_INCREMENT = 1;");
                    ps.executeUpdate();
                    ps = MySQLConnection.getConnection().prepareStatement("ALTER TABLE `ReportCount` ADD `Count` INT PRIMARY KEY AUTO_INCREMENT FIRST");
                    ps.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }


            try {
                ps = MySQLConnection.getConnection().prepareStatement(" SELECT * FROM `ClosedReports`");
                ps.executeQuery();
            } catch (SQLException e) {

                try {
                    ps = MySQLConnection.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `ClosedReports` (`Player` VARCHAR(100),`UUID` VARCHAR (100),`Reason` VARCHAR(100),`Proof` VARCHAR(100),`Reporter` VARCHAR(100),`Date` DATETIME,`CloseDate` TIMESTAMP default CURRENT_TIMESTAMP,`Closer` VARCHAR(100),`Result`VARCHAR(100))");
                    ps.executeUpdate();
                    ps = MySQLConnection.getConnection().prepareStatement("ALTER TABLE `ClosedReports` AUTO_INCREMENT = 1;");
                    ps.executeUpdate();
                    ps = MySQLConnection.getConnection().prepareStatement("ALTER TABLE `ClosedReports` ADD `Count` INT PRIMARY KEY AUTO_INCREMENT FIRST");
                    ps.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            //sdga
        }
    }
}
