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

import DAO.CTPNDAO;
import DTO.CTPNDTO;
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
public class CTPNBUS {

    public static CTPNDTO getHoaDơn(int mahd) {
        return CTPNDAO.getCTPN(mahd);
    }

    public static void init(JTable tbl,int mapn) {
        ArrayList<CTPNDTO> dssp = CTPNDAO.load(mapn);
        uploadTable(tbl, dssp,mapn);
    }

//    public ArrayList<CTPNDTO> find(String ten) {
//        CTPNDAO spDAO = new CTPNDAO();
//        return spDAO.find(ten);
//    }

    public static void uploadTable(JTable tbl, ArrayList<CTPNDTO> list,int mapn) {
        String[] columnNames = {"Mã chi tiết phiếu nhập", "Mã phiểu nhập", "Tên sách", "Số lượng", "Giá"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (CTPNDTO sp : list) {
            data[i][0] = sp.getMactpn();
            data[i][1] = sp.getMapn();
            data[i][2] = Convert.getTensach(sp.getMasach());
            data[i][3] = sp.getSl();
            data[i][4] = sp.getGia();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl,int mapn) throws Exception {
        CTPNDAO spDAO = new CTPNDAO();
        ArrayList<CTPNDTO> dssp = spDAO.load(mapn);
        uploadTable(tbl, dssp,mapn);
    }

    public void add(int mahd,int masach,int sl, int gia) {
        CTPNDAO spDAO = new CTPNDAO();
        CTPNDTO sp = new CTPNDTO(spDAO.getNewID());
        sp.setMapn(mahd);
        sp.setMasach(masach);
        sp.setSl(sl);
        sp.setGia(gia);
        spDAO.add(sp);
    }

    public static void delete(int macthd) {
        CTPNDAO nccDAO = new CTPNDAO();
        nccDAO.delete(macthd);
    }

    public void edit(int macthd,int mahd,int masach,int sl,int gia) {
        CTPNDTO sp = new CTPNDTO(macthd);
         sp.setMapn(mahd);
        sp.setMasach(masach);
        sp.setSl(sl);
        sp.setGia(gia);
        CTPNDAO spDAO = new CTPNDAO();
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
