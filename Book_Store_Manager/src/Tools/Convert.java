/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;

import GUI.EMPLOYEE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Convert {

    public static String getTentl(int ma) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_ch_sach?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT tentl from theloaisach where matl=" + String.valueOf(ma);
            ResultSet rs = stat.executeQuery(query);
            String result = null;
            while (rs.next()) {
                 result = rs.getString(1);
            }
            rs.close();
            stat.close();
     
            return result;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
      public static String getTenncc(int ncc) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_ch_sach?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT tenncc from nhacungcap  where mancc=" + String.valueOf(ncc) ;
            ResultSet rs = stat.executeQuery(query);
            String result = null;
            while (rs.next()) {
                 result = rs.getString(1);
            }
          
            rs.close();
            stat.close();
     
            return result;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
       
    public static String getTennv(int nv) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_ch_sach?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT hoten  from nhanvien  where manv=" + String.valueOf(nv) ;
            ResultSet rs = stat.executeQuery(query);
            String result = null;
            while (rs.next()) {
                 result = rs.getString(1);
            }
          
            rs.close();
            stat.close();
     
            return result;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
     public static String getTensach(int sach) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_ch_sach?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT tensach from sach where masach=" + String.valueOf(sach);
            ResultSet rs = stat.executeQuery(query);
            String result = null;
            while (rs.next()) {
                 result = rs.getString(1);
            }
            rs.close();
            stat.close();
     
            return result;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
