package me.luguco.easyreport.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by luguco on 08.07.2017.
 */
public class MySQLCreateTable {

    public static void CreateTables() {
        if (MySQLConnection.isConnected()) {

            PreparedStatement ps;

            try {
                ps = MySQLConnection.getConnection().prepareStatement(
                        "CREATE TABLE IF NOT EXISTS `ReportedPlayer` (\n" +
                                "  `Count` int(11) NOT NULL AUTO_INCREMENT,\n" +
                                "  `Player` varchar(100)  DEFAULT NULL,\n" +
                                "  `UUID` varchar(100)  DEFAULT NULL,\n" +
                                "  `Reason` varchar(100)  DEFAULT NULL,\n" +
                                "  `Proof` varchar(100)  DEFAULT NULL,\n" +
                                "  `Reporter` varchar(100)  DEFAULT NULL,\n" +
                                "  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                                "  `Report_Seen` varchar(100)  DEFAULT NULL,\n" +
                                "  `Supporter` varchar(100)  DEFAULT NULL,\n" +
                                "  PRIMARY KEY (`Count`)\n" +
                                ")");
                ps.executeUpdate();
                ps = MySQLConnection.getConnection().prepareStatement("INSERT INTO ReportedPlayer (`Player`,`UUID`,`Reason`,`Proof`,`Reporter`,`Report_Claimed`,`Supporter`) VALUES ('Testplayer','TestUUID','Hacking','www.testproof.de','Testreporter','false','NULL')");
                ps.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            try {
                ps = MySQLConnection.getConnection().prepareStatement(
                        "CREATE TABLE IF NOT EXISTS `ReportCount` (\n" +
                                "  `Count` int(11) NOT NULL AUTO_INCREMENT,\n" +
                                "  `Player` varchar(100)  DEFAULT NULL,\n" +
                                "  `UUID` varchar(100)  DEFAULT NULL,\n" +
                                "  `Counter` int(100) DEFAULT NULL,\n" +
                                "  PRIMARY KEY (`Count`)\n" +
                                ")");
                ps.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            try {
                ps = MySQLConnection.getConnection().prepareStatement(
                        "CREATE TABLE IF NOT EXISTS `ClosedReports` (\n" +
                                "  `Count` int(11) NOT NULL AUTO_INCREMENT,\n" +
                                "  `Player` varchar(100)  DEFAULT NULL,\n" +
                                "  `UUID` varchar(100)  DEFAULT NULL,\n" +
                                "  `Reason` varchar(100)  DEFAULT NULL,\n" +
                                "  `Proof` varchar(100)  DEFAULT NULL,\n" +
                                "  `Reporter` varchar(100)  DEFAULT NULL,\n" +
                                "  `Date` datetime DEFAULT NULL,\n" +
                                "  `CloseDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                                "  `Closer` varchar(100)  DEFAULT NULL,\n" +
                                "  `Result` varchar(100)  DEFAULT NULL,\n" +
                                "  PRIMARY KEY (`Count`)\n" +
                                ")");
                ps.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}