package me.luguco.easyreport.readData;

import me.luguco.easyreport.mysql.MySQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by luguco on 13.10.2017.
 */
public class ReportsCounting {

    static int count = 0;
    static PreparedStatement ps;
    public static int counts(){


        try {
            ps = MySQLConnection.getConnection().prepareStatement("SELECT `Count` FROM ReportedPlayer");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                count++;
            }
        } catch (SQLException e) {
            return count;
        }
        return count;
    }
}
