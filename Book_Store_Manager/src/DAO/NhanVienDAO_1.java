/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVienDTO;
import Tools.ThongBao;
import UTILS.Database;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hoalo
 */
public class NhanVienDAO_1 {

    public static ArrayList<NhanVienDTO> load() {
        ArrayList<NhanVienDTO> l_NhanVien = new ArrayList<>();
        //Database DB = new Database();
        Database.connect();

        ResultSet rs = Database.execution("SELECT * FROM nhanvien");
        try {
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(rs.getInt(1));
                nv.setHoten(rs.getString(2));
                nv.setSdt(rs.getString(3));
                nv.setDiachi(rs.getString(4));
                l_NhanVien.add(nv);
            }
        } catch (SQLException e) {
            ThongBao.warning("[NhanVienDAO:load] error sql: " + e);
        }
        Database.disconnect();
        return l_NhanVien;
    }

    public static NhanVienDTO getNhanVien(int manv) {
        Database.connect();
        ResultSet rs = Database.execution("SELECT * FROM nhanvien WHERE manv=" + manv);
        try {
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(rs.getInt(1));
                nv.setHoten(rs.getString(2));
                nv.setSdt(rs.getString(3));
                nv.setDiachi(rs.getString(4));

                Database.disconnect();
                return nv;
            }
        } catch (SQLException e) {
            ThongBao.error("[NhanVienDAO:find] error sql: " + e);
        }
        Database.disconnect();
        return null;
    }

    public void add(NhanVienDTO nv) {
        Database.connect();
//INSERT INTO `nhanvien`(`manv`, `hoten`, `sdt`) VALUES ('[value-1]','[value-2]','[value-3]')
        String sql = "INSERT INTO nhanvien(hoten, sdt, diachi) VALUES ('";
        sql += nv.getHoten() + "', '";
        sql += nv.getSdt() + "', '";
        sql += nv.getDiachi() + "');";
        Database.update(sql);
        Database.disconnect();
    }

    public void delete(int manv) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM nhanvien WHERE manv=" + manv);
        DB.disconnect();
    }

    public void edit(NhanVienDTO nv) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE NhanVien SET ";
        sql += "hoten='" + nv.getHoten();
        sql += "', sdt='" + nv.getSdt();
        sql += "', diachi='" + nv.getDiachi();
        sql += "' WHERE NhanVien.manv = " + nv.getManv() + ";";

        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(manv) FROM nhanvien");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            ThongBao.warning("[NhanVienDAO:load] error sql: " + e);
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<NhanVienDTO> find(String hoten) {
        ArrayList<NhanVienDTO> l_nhanvien = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM nhanvien WHERE ";

        if (!hoten.isEmpty()) {
            sql += "hoten LIKE '%" + hoten + "%';";
        }

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(rs.getInt(1));
                nv.setHoten(rs.getString(2));
                nv.setSdt(rs.getString(3));
                nv.setDiachi(rs.getString(4));
                l_nhanvien.add(nv);
            }
        } catch (SQLException e) {
            ThongBao.error("[NhanVienDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return l_nhanvien;
    }
}
