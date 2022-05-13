
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.SanPhamDTO;
import Tools.Convert;
import Tools.ThongBao;
import UTILS.Database;
public class SanPhamDAO {

    public static ArrayList<SanPhamDTO> load() {
        ArrayList<SanPhamDTO> l_sanpham = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM sach");

        try {
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO(rs.getInt(1));

                sp.setTensach(rs.getString(2));
                sp.setTentacgia(rs.getString(3));
                sp.setNxb(rs.getString(4));
                sp.setNamxb(rs.getInt(5));
                sp.setGia(rs.getInt(6));
                sp.setMatl(rs.getInt(7));
                l_sanpham.add(sp);
            }
        } catch (SQLException e) {
            ThongBao.error("[SanPhamDAO:load] " + e);
        }

        DB.disconnect();

        return l_sanpham;
    }

    public static SanPhamDTO getSanPham(int masach) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM sach WHERE masach=" + masach);

        try {
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO(rs.getInt(1));
                sp.setTensach(rs.getString(2));
                sp.setTentacgia(rs.getString(3));
                sp.setNxb(rs.getString(4));
                sp.setNamxb(rs.getInt(5));
                sp.setGia(rs.getInt(6));
                sp.setMatl(rs.getInt(7));

                DB.disconnect();

                return sp;
            }
        } catch (SQLException e) {
            ThongBao.error("[SanPhamDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(SanPhamDTO sp) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO sach (tensach, tentg , nxb, namxb, gia, matl) VALUES ('";
        sql += sp.getTensach() + "', '";
        sql += sp.getTentacgia() + "', '";
        sql += sp.getNxb() + "', '";
        sql += sp.getNamxb() + "', '";
        sql += sp.getGia() + "', '";
        sql += sp.getMatl()+ "');";

        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int masach) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM sach WHERE masach=" + masach);
        DB.disconnect();
    }

    public static void edit(SanPhamDTO sp) {
        Database DB = new Database();
        DB.connect();
               //UPDATE `sach` SET`tensach`='abc',`tentg`='abc',`nxb`='abc',`namxb`=2020,`gia`=200,`matl`=20 WHERE masach= 9;
            String sql = "UPDATE sach SET ";
            sql += " tensach = ' " + sp.getTensach();
            sql += " ' , tentg = ' " + sp.getTentacgia();
            sql += " ', nxb = ' " + sp.getNxb();
            sql += " ', namxb = " + sp.getNamxb();
            sql += " , gia =  " + sp.getGia();
            sql += " , matl= " + sp.getMatl();
            sql += " WHERE sach.masach = " +  sp.getMasach()+ " ;";
        DB.update(sql);
        DB.disconnect();
    }
    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(masach) FROM sach");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            ThongBao.warning("[SanPhamDAO:load] error sql: " + e);
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<SanPhamDTO> find(String ten) {
        ArrayList<SanPhamDTO> l_sanpham = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM sach WHERE ";

        if (!ten.isEmpty()) {
            sql += "tensach='" + ten + "' AND ";
        }

        sql = sql.substring(0, sql.length() - 4);

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO(rs.getInt(1));

                sp.setTensach(rs.getString(2));
                sp.setTentacgia(rs.getString(3));
                sp.setNxb(rs.getString(4));
                sp.setNamxb(rs.getInt(5));
                sp.setGia(rs.getInt(6));
                sp.setMatl(rs.getInt(7));

                l_sanpham.add(sp);
            }
        } catch (SQLException e) {
            ThongBao.error("[SanPhamDAO:find] error sql: " + e);
        }

        DB.disconnect();

        return l_sanpham;
    }

}
