/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.NhaCungCapDTO;
import Tools.ThongBao;
import UTILS.Database;

/**
 *
 * @author PHAN GIA PHAT
 */
public class NhaCungCapDAO {

    public static ArrayList<NhaCungCapDTO> load() {
        ArrayList<NhaCungCapDTO> l_ncc = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM nhacungcap");

        try {
            while (rs.next()) {
                NhaCungCapDTO ncc = new NhaCungCapDTO(rs.getInt(1));

                ncc.setTenncc(rs.getString(2));
                ncc.setDiachi(rs.getString(3));
                ncc.setSdt(rs.getString(4));

                l_ncc.add(ncc);
            }
        } catch (SQLException e) {
            ThongBao.error("[NCCDAO:load] " + e);
        }

        DB.disconnect();

        return l_ncc;
    }

    public static NhaCungCapDTO getNhaCungCap(int mancc) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM nhacungcap WHERE mancc=" + mancc);

        try {
            while (rs.next()) {
                NhaCungCapDTO sp = new NhaCungCapDTO(rs.getInt(1));
                sp.setTenncc(rs.getString(2));
                sp.setDiachi(rs.getString(3));
                sp.setSdt(rs.getString(4));

                DB.disconnect();

                return sp;
            }
        } catch (SQLException e) {
            ThongBao.error("[NCCDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(NhaCungCapDTO ncc) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO nhacungcap (tenncc, diachi ,sdt) VALUES ('";
        sql += ncc.getTenncc() + "', '";
        sql += ncc.getDiachi() + "', '";
        sql += ncc.getSdt()+ "');";

        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int mancc) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM nhacungcap WHERE mancc=" + mancc);
        DB.disconnect();
    }

    public void edit(NhaCungCapDTO ncc) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE nhacungcap SET ";
        sql += "tenncc='" + ncc.getTenncc();
        sql += "', diachi='" + ncc.getDiachi();
        sql += "', sdt='" + ncc.getSdt();
        sql += "' WHERE nhacungcap.mancc = " + ncc.getMancc() + ";";

        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(mancc) FROM nhacungcap");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            ThongBao.warning("NCCDAO:load] error sql: " + e);
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<NhaCungCapDTO> find(String ten) {
        ArrayList<NhaCungCapDTO> l_ncc = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM nhacungcap WHERE ";

        if (!ten.isEmpty()) {
            sql += "tenncc='" + ten + "' AND ";
        }

        sql = sql.substring(0, sql.length() - 4);

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                NhaCungCapDTO sp = new NhaCungCapDTO(rs.getInt(1));

                sp.setTenncc(rs.getString(2));
                sp.setDiachi(rs.getString(3));
                sp.setSdt(rs.getString(4));

                l_ncc.add(sp);
            }
        } catch (SQLException e) {
            ThongBao.error("[SanPhamDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return l_ncc;
    }
}
