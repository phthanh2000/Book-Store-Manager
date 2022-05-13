/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.HoaDonDTO;
import Tools.ThongBao;
import UTILS.Database;
/**
 *
 * @author ChiThien
 */
public class HoaDonDAO {
    public static ArrayList<HoaDonDTO> load() {
        ArrayList<HoaDonDTO> l_hoadon = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM hoadon");

        try {
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO(rs.getInt(1));

                hd.setNgayhd(rs.getString(2));
                hd.setManv(rs.getInt(3));
                
                l_hoadon.add(hd);
            }
        } catch (SQLException e) {
            ThongBao.error("[HoaDonDAO:load] " + e);
        }

        DB.disconnect();

        return l_hoadon;
    }

    public static HoaDonDTO getHoaDon(int mahd) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM hoadon WHERE mahd=" + mahd);

        try {
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO(rs.getInt(1));
                hd.setNgayhd(rs.getString(2));
                hd.setManv(rs.getInt(3));

                DB.disconnect();

                return hd;
            }
        } catch (SQLException e) {
            ThongBao.error("[HoaDonDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(HoaDonDTO hd) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO hoadon ( ngayhd, manv) VALUES ('";
        sql += hd.getNgayhd()+ "', '";
        sql += hd.getManv()+ "');";

        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int mahd) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM hoadon WHERE mahd=" + mahd);
        DB.disconnect();
    }

    public void edit(HoaDonDTO hd) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE hoadon SET ";
        sql += "ngayhd='" + hd.getNgayhd();
        sql += "', manv='" + hd.getManv();
        sql += "' WHERE hoadon.mahd = " + hd.getMahd()+ ";";

        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(mahd) FROM hoadon");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            ThongBao.warning("[HoaDonDAO:load] error sql: " + e);
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<HoaDonDTO> find(String ten) {
        ArrayList<HoaDonDTO> l_hoadon = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM hoadon WHERE ";

        if (!ten.isEmpty()) {
            sql += "ngayhd='" + ten + "' AND ";
        }

        sql = sql.substring(0, sql.length() - 4);

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO(rs.getInt(1));

                hd.setNgayhd(rs.getString(2));
                hd.setManv(rs.getInt(3));

                l_hoadon.add(hd);
            }
        } catch (SQLException e) {
            ThongBao.error("[HoaDonDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return l_hoadon;
    }
}
