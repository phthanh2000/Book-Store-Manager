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

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
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
public class HoaDonBUS {

    public static HoaDonDTO getHoaDơn(int masach) {
        return HoaDonDAO.getHoaDon(masach);
    }

    public static void init(JTable tbl) {
        ArrayList<HoaDonDTO> dssp = HoaDonDAO.load();
        uploadTable(tbl, dssp);
    }

    public ArrayList<HoaDonDTO> find(String ten) {
        HoaDonDAO spDAO = new HoaDonDAO();
        return spDAO.find(ten);
    }

    public static void uploadTable(JTable tbl, ArrayList<HoaDonDTO> list) {
        String[] columnNames = {"Mã Hóa đơn", "Ngày lập hóa đơn", "Nhân viên"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (HoaDonDTO sp : list) {
            data[i][0] = sp.getMahd();
            data[i][1] = sp.getNgayhd();
            data[i][2] = Convert.getTennv(sp.getManv());

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        HoaDonDAO  spDAO = new HoaDonDAO();
        ArrayList<HoaDonDTO> dssp = spDAO.load();
        uploadTable(tbl, dssp);
    }

    public void add(String ngaynhap, int mancc) {
        HoaDonDAO spDAO = new HoaDonDAO();

        HoaDonDTO sp = new HoaDonDTO(spDAO.getNewID());
        sp.setNgayhd(ngaynhap);
        sp.setManv(mancc);
        spDAO.add(sp);
    }

      public static void delete(int mahd){
          HoaDonDAO nccDAO = new HoaDonDAO();
            nccDAO.delete(mahd);
      }
    public void edit(int mahd, String ngaynhap, int mancc) {
        HoaDonDTO sp = new HoaDonDTO(mahd);
        sp.setNgayhd(ngaynhap);
        sp.setManv(mancc);

        HoaDonDAO spDAO = new HoaDonDAO();
        spDAO.edit(sp);
    }

 public static void loadInfo(JTable tbl, JTextField form1, JDateChooser form2, JComboBox form3) {
        try {
            HoaDonDTO sp = HoaDonDAO.getHoaDon(TableUtil.getTable(tbl));
            form1.setText(String.valueOf(sp.getMahd()));
            Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(sp.getNgayhd());
            form2.setDate(date);
            form3.setSelectedItem(Convert.getTenncc(sp.getManv()));
        } catch (ParseException ex) {
            Logger.getLogger(HoaDonBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
