/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import Tools.Convert;
import Tools.TableUtil;

/**
 *
 * @author PHAN GIA PHAT
 */
public class SanPhamBUS {

    public static SanPhamDTO getSanPham(int masach) {
        return SanPhamDAO.getSanPham(masach);
    }

    public static void init(JTable tbl) {
        ArrayList<SanPhamDTO> dssp = SanPhamDAO.load();
        uploadTable(tbl, dssp);
    }

    public ArrayList<SanPhamDTO> find(String ten) {
        SanPhamDAO spDAO = new SanPhamDAO();
        return spDAO.find(ten);
    }

    public static void uploadTable(JTable tbl, ArrayList<SanPhamDTO> list) {
        String[] columnNames = {"Mã Sách", "Tên sách", "Tên tác giả", "Nhà xuất bản", "Năm xuất bản", "Giá", "Thể loại"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (SanPhamDTO sp : list) {
            data[i][0] = sp.getMasach();
            data[i][1] = sp.getTensach();
            data[i][2] = sp.getTentacgia();
            data[i][3] = sp.getNxb();
            data[i][4] = sp.getNamxb();
            data[i][5] = sp.getGia();
            data[i][6] = Convert.getTentl(sp.getMatl());
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        SanPhamDAO spDAO = new SanPhamDAO();
        ArrayList<SanPhamDTO> dssp = spDAO.load();
        uploadTable(tbl, dssp);
    }

    public void add(String tensach, String tentacgia, String nhaxuatban, int namxuatban, int gia, int matl) {
        SanPhamDAO spDAO = new SanPhamDAO();
        SanPhamDTO sp = new SanPhamDTO(spDAO.getNewID());
        sp.setTensach(tensach);
        sp.setTentacgia(tentacgia);
        sp.setNxb(nhaxuatban);
        sp.setNamxb(namxuatban);
        sp.setGia(gia);
        sp.setMatl(matl);
        spDAO.add(sp);
    }

    public static void delete(int matl) {
        SanPhamDAO nccDAO = new SanPhamDAO();
        nccDAO.delete(matl);
    }

    public void edit(int masach, String tensach, String tentacgia, String nhaxuatban, int namxuatban, int gia, int matl) {
        SanPhamDAO spDAO = new SanPhamDAO();
        SanPhamDTO sp = new SanPhamDTO(masach);
        sp.setTensach(tensach);
        sp.setTentacgia(tentacgia);
        sp.setNxb(nhaxuatban);
        sp.setNamxb(namxuatban);
        sp.setGia(gia);
        sp.setMatl(matl);
        spDAO.edit(sp);
    }

    public static void loadInfo(JTable tbl, JTextField formMasach, JTextField formTensach, JTextField formTentacgia, JTextField formNhaxuatban, JTextField formNamxuatban, JTextField formGia, JComboBox formTheloai) {
        SanPhamDTO sp = SanPhamDAO.getSanPham(TableUtil.getTable(tbl));
        formMasach.setText(String.valueOf(sp.getMasach()));
        formTensach.setText(sp.getTensach());
        formTentacgia.setText(sp.getTentacgia());
        formNhaxuatban.setText(sp.getNxb());
        formNamxuatban.setText(String.valueOf(sp.getNamxb()));
        formGia.setText(String.valueOf(sp.getGia()));
        formTheloai.setSelectedItem(Convert.getTentl(sp.getMatl()));
    }

}
