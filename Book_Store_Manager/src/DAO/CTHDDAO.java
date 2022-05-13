/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.CTHDDTO;
import Tools.ThongBao;
import UTILS.Database;
/**
 *
 * @author ChiThien
 */
public class CTHDDAO {
    public static ArrayList<CTHDDTO> load(int mahd) {
        ArrayList<CTHDDTO> l_cthd = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM chitiethoadon WHERE mahd="+mahd);

        try {
            while (rs.next()) {
                CTHDDTO cthd = new CTHDDTO(rs.getInt(1));
                cthd.setMahd(rs.getInt(2));
                cthd.setMasach(rs.getInt(3));
                cthd.setSl(rs.getInt(4));
                cthd.setGia(rs.getInt(5));
                l_cthd.add(cthd);
            }
        } catch (SQLException e) {
            ThongBao.error("[CTHDDAO:load] " + e);
        }

        DB.disconnect();

        return l_cthd;
    }

    public static CTHDDTO getCTHD(int macthd) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM chitiethoadon WHERE macthd=" + macthd);

        try {
            while (rs.next()) {
                CTHDDTO cthd = new CTHDDTO(rs.getInt(1));
                cthd.setMahd(rs.getInt(2));
                cthd.setMasach(rs.getInt(3));
                cthd.setSl(rs.getInt(4));
                cthd.setGia(rs.getInt(5));;
                DB.disconnect();

                return cthd;
            }
        } catch (SQLException e) {
            ThongBao.error("[CTHDDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(CTHDDTO cthd) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO chitiethoadon (mahd, masach , sl, gia) VALUES ('";
        sql += cthd.getMahd()+ "', '";
        sql += cthd.getMasach() + "', '";
        sql += cthd.getSl() + "', '";
        sql += cthd.getGia()+ "');";

        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int macthd) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM chitiethoadon WHERE macthd=" + macthd);
        DB.disconnect();
    }

    public static void edit(CTHDDTO cthd) {
        Database DB = new Database();
        DB.connect();
         String sql = "UPDATE chitiethoadon SET ";
             sql += " mahd = " + cthd.getMahd();
            sql += " , masach = " + cthd.getMasach();
            sql += " , sl =  " + cthd.getSl();
            sql += " , gia = " + cthd.getGia();
            sql += " WHERE chitiethoadon.macthd = " +  cthd.getMacthd()+ " ;";
        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(macthd) FROM chitiethoadon");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            ThongBao.warning("[CTHDDAO:load] error sql: " + e);
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<CTHDDTO> find(String ten) {
        ArrayList<CTHDDTO> l_cthd = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM chitiethoadon WHERE ";

        if (!ten.isEmpty()) {
            sql += "tenchitiethoadon='" + ten + "' AND ";
        }

        sql = sql.substring(0, sql.length() - 4);

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                CTHDDTO cthd = new CTHDDTO(rs.getInt(1));

                cthd.setMahd(rs.getInt(2));
                cthd.setMasach(rs.getInt(3));
                cthd.setSl(rs.getInt(4));
                cthd.setGia(rs.getInt(5));

                l_cthd.add(cthd);
            }
        } catch (SQLException e) {
            ThongBao.error("[CTHDDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return l_cthd;
    }
    public static void main(String[] args) {
        
    }
}
