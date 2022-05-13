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

import DAO.CTHDDAO;
import DTO.CTHDDTO;
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
public class CTHDBUS {

    public static CTHDDTO getHoaDơn(int mahd) {
        return CTHDDAO.getCTHD(mahd);
    }

    public static void init(JTable tbl,int mahd) {
        ArrayList<CTHDDTO> dssp = CTHDDAO.load(mahd);
        uploadTable(tbl, dssp,mahd);
    }

    public ArrayList<CTHDDTO> find(String ten) {
        CTHDDAO spDAO = new CTHDDAO();
        return spDAO.find(ten);
    }

    public static void uploadTable(JTable tbl, ArrayList<CTHDDTO> list,int mahd) {
        String[] columnNames = {"Mã chi tiết hóa đơn", "Mã hóa đơn", "Tên sách", "Số lượng", "Giá"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (CTHDDTO sp : list) {
            data[i][0] = sp.getMacthd();
            data[i][1] = sp.getMahd();
            data[i][2] = Convert.getTensach(sp.getMasach());
            data[i][3] = sp.getSl();
            data[i][4] = sp.getGia();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl,int mahd) throws Exception {
        CTHDDAO spDAO = new CTHDDAO();
        ArrayList<CTHDDTO> dssp = spDAO.load(mahd);
        uploadTable(tbl, dssp,mahd);
    }

    public void add(int mahd,int masach,int sl, int gia) {
        CTHDDAO spDAO = new CTHDDAO();
        CTHDDTO sp = new CTHDDTO(spDAO.getNewID());
        sp.setMahd(mahd);
        sp.setMasach(masach);
        sp.setSl(sl);
        sp.setGia(gia);
        spDAO.add(sp);
    }

    public static void delete(int macthd) {
        CTHDDAO nccDAO = new CTHDDAO();
        nccDAO.delete(macthd);
    }

    public void edit(int macthd,int mahd,int masach,int sl,int gia) {
        CTHDDTO sp = new CTHDDTO(macthd);
         sp.setMahd(mahd);
        sp.setMasach(masach);
        sp.setSl(sl);
        sp.setGia(gia);
        CTHDDAO spDAO = new CTHDDAO();
        spDAO.edit(sp);
    }

//    public static void loadInfo(JTable tbl, JTextField form1, JDateChooser form2, JComboBox form3) {
//        try {
//            CTHDDAO sp = CTHDDAO.getCTHD(\TableUtil.getTable(tbl));
//            form1.setText(String.valueOf(sp.getMahd()));
//            Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(sp.getNgayhd());
//            form2.setDate(date);
//            form3.setSelectedItem(Convert.getTenncc(sp.getManv()));
//        } catch (ParseException ex) {
//            Logger.getLogger(CTHDBUS.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
