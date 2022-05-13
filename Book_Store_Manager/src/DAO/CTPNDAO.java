/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.CTPNDTO;
import Tools.ThongBao;
import UTILS.Database;
/**
 *
 * @author ChiThien
 */
public class CTPNDAO {
    public static ArrayList<CTPNDTO> load(int mapn) {
        ArrayList<CTPNDTO> l_ctpn = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM chitietphieunhap WHERE mapn="+mapn);

        try {
            while (rs.next()) {
                CTPNDTO ctpn = new CTPNDTO(rs.getInt(1));

                 ctpn.setMapn(rs.getInt(2));
                ctpn.setMasach(rs.getInt(3));
                ctpn.setSl(rs.getInt(4));
                ctpn.setGia(rs.getInt(5));
                l_ctpn.add(ctpn);
            }
        } catch (SQLException e) {
            ThongBao.error("[CTPNDAO:load] " + e);
        }

        DB.disconnect();

        return l_ctpn;
    }

    public static CTPNDTO getCTPN(int mactpn) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM chitietphieunhap WHERE mactpn=" + mactpn);

        try {
            while (rs.next()) {
                CTPNDTO ctpn = new CTPNDTO(rs.getInt(1));
                 ctpn.setMapn(rs.getInt(2));
                ctpn.setMasach(rs.getInt(3));
                ctpn.setSl(rs.getInt(4));
                ctpn.setGia(rs.getInt(5));

                DB.disconnect();

                return ctpn;
            }
        } catch (SQLException e) {
            ThongBao.error("[CTPNDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(CTPNDTO ctpn) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO chitietphieunhap (mapn, masach , sl, gia) VALUES ('";
        sql += ctpn.getMapn()+ "', '";
        sql += ctpn.getMasach() + "', '";
        sql += ctpn.getSl() + "', '";
        sql += ctpn.getGia()+ "');";

        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int mactpn) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM chitietphieunhap WHERE mactpn=" + mactpn);
        DB.disconnect();
    }

    public void edit(CTPNDTO ctpn) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE chitietphieunhap SET ";
        sql += "mapn= " + ctpn.getMapn();
        sql += ", masach= " + ctpn.getMasach();
        sql += ", sl=" + ctpn.getSl();
        sql += ", gia=" + ctpn.getGia();
        sql += " WHERE chitietphieunhap.mactpn = " + ctpn.getMactpn()+ ";";

        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(mactpn) FROM chitietphieunhap");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            ThongBao.warning("[CTPNDAO:load] error sql: " + e);
        }

        DB.disconnect();

        return -1;
    }

//    public ArrayList<CTPNDTO> find(String ten) {
//        ArrayList<CTPNDTO> l_ctpn = new ArrayList<>();
//
//        Database DB = new Database();
//        DB.connect();
//
//        String sql = "SELECT * FROM chitietphieunhap WHERE ";
//
//        if (!ten.isEmpty()) {
//            sql += "tenchitietphieunhap='" + ten + "' AND ";
//        }
//
//        sql = sql.substring(0, sql.length() - 4);
//
//        ResultSet rs = DB.execution(sql);
//
//        try {
//            while (rs.next()) {
//                CTPNDTO ctpn = new CTPNDTO(rs.getInt(1));
//
//                 ctpn.setMapn(rs.getInt(2));
//                ctpn.setMasach(rs.getInt(3));
//                ctpn.setSl(rs.getInt(4));
//                ctpn.setGia(rs.getInt(5));
//
//                l_ctpn.add(ctpn);
//            }
//        } catch (SQLException e) {
//            ThongBao.error("[CTPNDAO:find] error sql: " + e);
//        }
//
//        DB.disconnect();
//
//        return l_ctpn;
//    }

}
