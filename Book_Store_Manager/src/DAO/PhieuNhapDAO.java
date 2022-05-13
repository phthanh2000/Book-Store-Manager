/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PhieuNhapDTO;
import Tools.ThongBao;
import UTILS.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ChiThien
 */
public class PhieuNhapDAO {

    public static ArrayList<PhieuNhapDTO> load() {
        ArrayList<PhieuNhapDTO> l_phieunhap = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM phieunhap");

        try {
            while (rs.next()) {
                PhieuNhapDTO pn = new PhieuNhapDTO(rs.getInt(1));

                pn.setNgaynhap(rs.getString(2));
                pn.setMancc(rs.getInt(3));
                l_phieunhap.add(pn);
            }
        } catch (SQLException e) {
            ThongBao.error("[PhieuNhapDAO:load] " + e);
        }

        DB.disconnect();

        return l_phieunhap;
    }

    public static PhieuNhapDTO getPhieuNhap(int mapn) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM phieunhap WHERE mapn=" + mapn);
        try {
            while (rs.next()) {
                PhieuNhapDTO pn = new PhieuNhapDTO();
                pn.setMapn(rs.getInt(1));
                pn.setNgaynhap(rs.getString(2));
                pn.setMancc(rs.getInt(3));
                DB.disconnect();

                return pn;
            }
        } catch (SQLException e) {
            ThongBao.error("[PhieuNhapDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(PhieuNhapDTO pn) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO phieunhap (ngaynhap , mancc) VALUES ('";
        sql += pn.getNgaynhap() + "', '";
        sql += pn.getMancc() + "');";

        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int mapn) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM phieunhap WHERE mapn=" + mapn);
        DB.disconnect();
    }

    public void edit(PhieuNhapDTO pn) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE phieunhap SET ";
        sql += " ngaynhap='" + pn.getNgaynhap();
        sql += "', mancc=" + pn.getMancc();
        sql += " WHERE phieunhap.mapn = " + pn.getMapn() + ";";

        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(mapn) FROM phieunhap");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            ThongBao.warning("[PhieuNhapDAO:load] error sql: " + e);
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<PhieuNhapDTO> find(String ten) {
        ArrayList<PhieuNhapDTO> l_phieunhap = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM phieunhap WHERE ";

        if (!ten.isEmpty()) {
            sql += "ngaynhap='" + ten + "' AND ";
        }

        sql = sql.substring(0, sql.length() - 4);

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                PhieuNhapDTO pn = new PhieuNhapDTO(rs.getInt(1));

                pn.setNgaynhap(rs.getString(2));
                pn.setMancc(rs.getInt(3));

                l_phieunhap.add(pn);
            }
        } catch (SQLException e) {
            ThongBao.error("[PhieuNhapDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return l_phieunhap;
    }
}
