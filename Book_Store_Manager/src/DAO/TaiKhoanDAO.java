/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import Tools.ThongBao;
import UTILS.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author hoalo
 */
public class TaiKhoanDAO {

    public static ArrayList<TaiKhoanDTO> load() {
        ArrayList<TaiKhoanDTO> l_TaiKhoan = new ArrayList<>();
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT CONCAT(role.manv, '-' , nhanvien.hoten) AS manv, role.tk, role.mk, role.quyen FROM role JOIN nhanvien ON role.manv = nhanvien.manv ");
        try {
            while (rs.next()) {
                TaiKhoanDTO tk = new TaiKhoanDTO(rs.getString(2));
                tk.setMa(rs.getString(1));
                tk.setMatkhau(rs.getString(3));
                tk.setQuyen(rs.getInt(4));
                l_TaiKhoan.add(tk);
            }
        } catch (SQLException e) {
            ThongBao.warning("[TaiKhoanDAO:load] error sql: " + e);
        }

        DB.disconnect();

        return l_TaiKhoan;
    }

    public static TaiKhoanDTO getTaiKhoan(String tentk) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT CONCAT(role.manv, '-' , nhanvien.hoten) AS manv, role.tk, role.mk, role.quyen FROM role JOIN nhanvien ON role.manv = nhanvien.manv WHERE tk='" + tentk + "'");

        try {
            while (rs.next()) {
                TaiKhoanDTO tk = new TaiKhoanDTO(rs.getString(2));
                tk.setMa(rs.getString("manv"));
                tk.setMatkhau(rs.getString("mk"));
                tk.setQuyen(rs.getInt("quyen"));

                DB.disconnect();

                return tk;
            }
        } catch (SQLException e) {
            ThongBao.error("[TaiKhoanDAO:getTaiKhoan] error sql: " + e);
        }

        DB.disconnect();

        return null;
    }

    public static void add(TaiKhoanDTO tk) {
        Database DB = new Database();
        DB.connect();
        //INSERT INTO `role`(`manv`, `tk`, `mk`, `quyen`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]')
        String sql = "INSERT INTO role(manv, tk, mk, quyen) VALUES ('";
        sql += tk.getManv() + "', '";
        sql += tk.getTaikhoan() + "', '";
        sql += tk.getMatkhau() + "', '";
        sql += tk.getQuyen() + "');";
        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int manv) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM role WHERE manv=" + manv);
        DB.disconnect();
    }

    public static void edit(TaiKhoanDTO tk) {
        Database DB = new Database();
        DB.connect();
//UPDATE role SET tk='Thienne', mk='Thienne',quyen=1 WHERE manv = 6
        String sql = "UPDATE role SET";
        sql += " tk='" + tk.getTaikhoan();
        sql += "', mk='" + tk.getMatkhau();
        sql += "', quyen=" + tk.getQuyen();
        sql += " WHERE role.manv = " + tk.getManv()+ ";";

        DB.update(sql);
        DB.disconnect();
    }

    public ArrayList<TaiKhoanDTO> find(String tentk) {
        ArrayList<TaiKhoanDTO> list = new ArrayList<>();
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT CONCAT(role.manv, '-' , nhanvien.hoten) AS manv, role.tk, role.mk, role.quyen FROM role JOIN nhanvien ON role.manv = nhanvien.manv WHERE tk='" + tentk + "'");

        try {
            while (rs.next()) {
                TaiKhoanDTO tk = new TaiKhoanDTO(rs.getString(2));

                tk.setMa(rs.getString(1));
                tk.setMatkhau(rs.getString(3));
                tk.setQuyen(rs.getInt(4));

                list.add(tk);
            }
        } catch (SQLException e) {
            Tools.ThongBao.error("[TaiKhoanDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return list;
    }

    public static ArrayList<NhanVienDTO> MaNV() {
        ArrayList<NhanVienDTO> l_NV = new ArrayList<>();
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT manv, hoten FROM nhanvien");
        try {
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(rs.getInt(1));
                nv.setHoten(rs.getString(2));
                l_NV.add(nv);
            }
        } catch (SQLException e) {
            ThongBao.warning("[TaiKhoanDAO:load] error sql: " + e);
        }

        DB.disconnect();

        return l_NV;
    }

    public static void main(String[] args) {
        /*
        ArrayList<NhanVienDTO> list = new ArrayList<>();
        list = MaNV();
        for (NhanVienDTO ma : list) {
            System.out.println(ma.getManv());
            System.out.println(ma.getHoten());
        }
*/
        TaiKhoanDTO tk = new TaiKhoanDTO();
        tk.setManv(6);
        tk.setTaikhoan("Thiennenhe");
        tk.setMatkhau("Thiennenhe");
        tk.setQuyen(1);
        
        edit(tk);
        
    }
}
