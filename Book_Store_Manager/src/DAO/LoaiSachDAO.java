package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.LoaiSachDTO;
import Tools.ThongBao;
import UTILS.Database;
import java.util.Arrays;

/**
 *
 * @author ChiThien
 */
public class LoaiSachDAO {
    public static ArrayList<LoaiSachDTO> load() {
        ArrayList<LoaiSachDTO> list = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM theloaisach");

        try {
            while (rs.next()) {
                LoaiSachDTO ls = new LoaiSachDTO(rs.getInt(1));
                ls.setTentl(rs.getString(2));
                list.add(ls);
            }
        } catch (SQLException e) {
            ThongBao.error("[LoaiSachDAO:load] " + e);
        }

        DB.disconnect();

        return list;
    }

    public static LoaiSachDTO getNhaCungCap(int mancc) {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT * FROM theloaisach WHERE matl=" + mancc);

        try {
            while (rs.next()) {
                LoaiSachDTO ncc = new LoaiSachDTO(rs.getInt(1));
                ncc.setTentl(rs.getString(2));

                DB.disconnect();

                return ncc;
            }
        } catch (SQLException e) {
            ThongBao.error("[LoaiSachDAO:load] " + e);
        }

        DB.disconnect();

        return null;
    }

    public void add(LoaiSachDTO ncc) {
        Database DB = new Database();
        DB.connect();
        String sql = "INSERT INTO theloaisach (tentl) VALUES ('";
        sql += ncc.getTentl() + "');";

        DB.update(sql);
        DB.disconnect();
    }

    public void delete(int mancc) {
        Database DB = new Database();
        DB.connect();
        DB.update("DELETE FROM theloaisach WHERE matl=" + mancc);
        DB.disconnect();
    }

    public void edit(LoaiSachDTO ncc) {
        Database DB = new Database();
        DB.connect();

        String sql = "UPDATE theloaisach SET ";
        sql += "tentl='" + ncc.getTentl();
        sql += "' WHERE theloaisach.matl = " + ncc.getMatl() + ";";

        DB.update(sql);
        DB.disconnect();
    }

    public static int getNewID() {
        Database DB = new Database();
        DB.connect();

        ResultSet rs = DB.execution("SELECT MAX(matl) FROM theloaisach");

        try {
            while (rs.next()) {
                int newid = rs.getInt(1) + 1;
                DB.disconnect();
                return newid;
            }
        } catch (SQLException e) {
            ThongBao.error("[LoaiSachDAO:load] " + e);
        }

        DB.disconnect();

        return -1;
    }

    public ArrayList<LoaiSachDTO> find(String oj) {
        ArrayList<LoaiSachDTO> l_nhacungcap = new ArrayList<>();

        Database DB = new Database();
        DB.connect();

        String sql = "SELECT * FROM theloaisach WHERE ";

        if (!oj.isEmpty()) {
            sql += "tentl='" + oj + "' AND ";
        }
        sql = sql.substring(0, sql.length() - 4);

        ResultSet rs = DB.execution(sql);

        try {
            while (rs.next()) {
                LoaiSachDTO ncc = new LoaiSachDTO(rs.getInt(1));
                ncc.setTentl(rs.getString(2));

                l_nhacungcap.add(ncc);
            }
        } catch (SQLException e) {
            ThongBao.error("[LoaiSachDAO:load] " + e);
        }

        DB.disconnect();

        return l_nhacungcap;
    }

    public static void main(String[] agrs) {
            System.out.println(load());
        
    }
}
