/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;
import Tools.Convert;
import Tools.TableUtil;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ChiThien
 */
public class PhieuNhapBUS {

    public static PhieuNhapDTO getPhieuNhap(int masach) {
        return PhieuNhapDAO.getPhieuNhap(masach);
    }

    public static void init(JTable tbl) {
        ArrayList<PhieuNhapDTO> dssp = PhieuNhapDAO.load();
        uploadTable(tbl, dssp);
    }

    public ArrayList<PhieuNhapDTO> find(String ten) {
        PhieuNhapDAO spDAO = new PhieuNhapDAO();
        return spDAO.find(ten);
    }

    public static void uploadTable(JTable tbl, ArrayList<PhieuNhapDTO> list) {
        String[] columnNames = {"Mã Phiếu nhập", "Ngày nhập", "Nhà cung cấp"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (PhieuNhapDTO sp : list) {
            data[i][0] = sp.getMapn();
            data[i][1] = sp.getNgaynhap();
            data[i][2] = Convert.getTenncc(sp.getMancc());

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        PhieuNhapDAO spDAO = new PhieuNhapDAO();
        ArrayList<PhieuNhapDTO> dssp = spDAO.load();
        uploadTable(tbl, dssp);
    }

    public void add(String ngaynhap, int mancc) {
        PhieuNhapDAO spDAO = new PhieuNhapDAO();

        PhieuNhapDTO sp = new PhieuNhapDTO(spDAO.getNewID());
        sp.setNgaynhap(ngaynhap);
        sp.setMancc(mancc);
        spDAO.add(sp);
    }

       public static void delete(int matl) {
        PhieuNhapDAO nccDAO = new PhieuNhapDAO();
        nccDAO.delete(matl);
    }

    public void edit(int mapn, String ngaynhap, int mancc) {
        PhieuNhapDTO sp = new PhieuNhapDTO(mapn);
        sp.setNgaynhap(ngaynhap);
        sp.setMancc(mancc);

        PhieuNhapDAO spDAO = new PhieuNhapDAO();
        spDAO.edit(sp);
    }

 public static void loadInfo(JTable tbl, JTextField form1, JDateChooser form2, JComboBox form3) {
        try {
            PhieuNhapDTO sp = PhieuNhapDAO.getPhieuNhap(TableUtil.getTable(tbl));
            form1.setText(String.valueOf(sp.getMapn()));
            Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(sp.getNgaynhap());
            form2.setDate(date);
            form3.setSelectedItem(Convert.getTenncc(sp.getMancc()));
        } catch (ParseException ex) {
            Logger.getLogger(PhieuNhapBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
