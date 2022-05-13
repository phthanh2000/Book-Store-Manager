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
import DAO.NhaCungCapDAO;
import DTO.LoaiSachDTO;
import DTO.NhaCungCapDTO;

/**
 *
 * @author ChiThien
 */
public class NhaCungCapBUS {

    public static NhaCungCapDTO getNhaCungCap(int mancc) {
        return NhaCungCapDAO.getNhaCungCap(mancc);
    }

    public ArrayList<NhaCungCapDTO> find(String ten) {
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        return nccDAO.find(ten);
    }

    public void add(String tenncc, String diachi, String sdt) {
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        NhaCungCapDTO ncc = new NhaCungCapDTO(nccDAO.getNewID());
        ncc.setTenncc(tenncc);
        ncc.setDiachi(diachi);
        ncc.setSdt(sdt);
        nccDAO.add(ncc);
    }

    public static void delete(int mancc) {
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        nccDAO.delete(mancc);
    }

    public void edit(int mancc, String tenncc, String diachi, String sdt) {
        NhaCungCapDTO ncc = new NhaCungCapDTO(mancc);
        ncc.setTenncc(tenncc);
        ncc.setDiachi(diachi);
        ncc.setSdt(sdt);
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        nccDAO.edit(ncc);
    }

    public static void init(JTable tbl) {
        ArrayList<NhaCungCapDTO> dsncc = NhaCungCapDAO.load();
        uploadTable(tbl, dsncc);
    }

    public static void uploadTable(JTable tbl, ArrayList<NhaCungCapDTO> list) {
        String[] columnNames = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "SĐT"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (NhaCungCapDTO ncc : list) {
            data[i][0] = ncc.getMancc();
            data[i][1] = ncc.getTenncc();
            data[i][2] = ncc.getDiachi();
            data[i][3] = ncc.getSdt();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    public void updateTable(JTable tbl) throws Exception {
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        ArrayList<NhaCungCapDTO> dsncc = nccDAO.load();
        uploadTable(tbl, dsncc);
    }

    public static void loadInfo(JTable tbl, JTextField form1, JTextField form2, JTextField form3, JTextField form4) {
        NhaCungCapDTO ncc = NhaCungCapDAO.getNhaCungCap(TableUtil.getTable(tbl));
        form1.setText(String.valueOf(ncc.getMancc()));
        form2.setText(ncc.getTenncc());
        form3.setText(ncc.getDiachi());
        form4.setText(ncc.getSdt());
    }

}
