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
import Tools.TableUtil;
import DAO.LoaiSachDAO;
import DTO.LoaiSachDTO;

/**
 *
 * @author ChiThien
 */
public class LoaiSachBUS {

    public static LoaiSachDTO getNhaCungCap(int mancc) {
        return LoaiSachDAO.getNhaCungCap(mancc);
    }

    public ArrayList<LoaiSachDTO> find(String ten) {
        LoaiSachDAO nccDAO = new LoaiSachDAO();
        return nccDAO.find(ten);
    }
    public void add(String tentl) {
        LoaiSachDAO nccDAO = new LoaiSachDAO();
        LoaiSachDTO ncc = new LoaiSachDTO(nccDAO.getNewID());
        ncc.setTentl(tentl);
        nccDAO.add(ncc);
    }

    // Xóa
//  
//    public static void delete(JTable tblnccData) {
//        int mancc = TableUtil.getTable(tblnccData);
//        if (mancc != 0) {
//            LoaiSachDAO nccDAO = new LoaiSachDAO();
//            nccDAO.delete(mancc);
//        }
//    }

    
      public static void delete(int matl){
          LoaiSachDAO nccDAO = new LoaiSachDAO();
            nccDAO.delete(matl);
      }
      
    
   public void edit(int mancc, String ten) {
        LoaiSachDTO ncc = new LoaiSachDTO(mancc);
        ncc.setTentl(ten);
        LoaiSachDAO nccDAO = new LoaiSachDAO();
        nccDAO.edit(ncc);
    }
   public static void init(JTable tbl) {
        ArrayList<LoaiSachDTO> dsncc = LoaiSachDAO.load();
        uploadTable(tbl, dsncc);
    }

    public static void uploadTable(JTable tbl, ArrayList<LoaiSachDTO> list) {
        String[] columnNames = {"Mã thể loại", "Tên thể loại"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (LoaiSachDTO ncc : list) {
            data[i][0] = ncc.getMatl();
            data[i][1] = ncc.getTentl();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        LoaiSachDAO nccDAO = new LoaiSachDAO();
        ArrayList<LoaiSachDTO> dsncc = nccDAO.load();
        uploadTable(tbl, dsncc);
    }

    public static void loadInfo(JTable tbl, JTextField form1, JTextField form2) {
        LoaiSachDTO ncc = LoaiSachDAO.getNhaCungCap(TableUtil.getTable(tbl));
        form1.setText(String.valueOf(ncc.getMatl()));
        form2.setText(ncc.getTentl());
    }
    
}
