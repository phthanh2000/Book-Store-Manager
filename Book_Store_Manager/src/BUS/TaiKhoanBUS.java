/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import Tools.ThongBao;
import Tools.TableUtil;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author hoalo
 */
public class TaiKhoanBUS {

    private static TaiKhoanDTO m_user;

    public TaiKhoanBUS() {
        m_user = null;
    }

    public static TaiKhoanDTO getUser() {
        return m_user;
    }

    public static boolean login(String tentk, String matkhau) {
        TaiKhoanDTO tk = TaiKhoanDAO.getTaiKhoan(tentk);

        if (tk != null && tk.getMatkhau().equals(matkhau)) {
            m_user = tk;
            System.out.println("Ok nhe");
            return true;
        }
        System.out.println("Out");
        return false;
    }

    public static void logout() {
        m_user = null;
    }

    public ArrayList<TaiKhoanDTO> getList() {
        return TaiKhoanDAO.load();
    }

    public static boolean validateForm(String tentk, String matkhau, int manv) {
        if (tentk.isEmpty()) {
            ThongBao.warning("Chưa điền tên tài khoản");
        } else if (matkhau.isEmpty()) {
            ThongBao.warning("Chưa điền tên tài khoản");
        } else if (manv == 0 || DAO.NhanVienDAO.getNhanVien(manv) == null) {
            ThongBao.warning("Mã nhân viên không hợp lệ");
        } else {
            return true;
        }
        return false;
    }

    public static void add(JTextField formTenTK, JTextField formMK, JComboBox formQ, JComboBox formMaNV) {
        String tentk = formTenTK.getText();
        String matkhau = formMK.getText();
        String quyenchu = (String) formQ.getSelectedItem();
        int quyen;
        if( quyenchu.equalsIgnoreCase("Admin")){
            quyen = 1;
        } else{
            quyen = 0;
        }
        String manvC = (String) formMaNV.getSelectedItem();
        int manv=0;
        if (!manvC.isEmpty()) {
            //manv = Integer.valueOf(formMaNV.getText());
            String[] list = manvC.split("-");
            String manv1 = list[0]; 
            manv = Integer.parseInt(manv1);
        }

        if (validateForm(tentk, matkhau, manv)) {
            TaiKhoanDTO tk = new TaiKhoanDTO(tentk);
            tk.setMatkhau(matkhau);
            tk.setQuyen(quyen);
            tk.setManv(manv);
            TaiKhoanDAO.add(tk);
        }
    }
    // Xóa

    public static void delete(int manv) {
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        tkDAO.delete(manv);
    }

    public void edit(int manv, String tk, String mk, int Quyen){
        TaiKhoanDTO tkDTO = new TaiKhoanDTO(manv);
        tkDTO.setTaikhoan(tk);
        tkDTO.setMatkhau(mk);
        tkDTO.setQuyen(Quyen);
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        tkDAO.edit(tkDTO);
    }
    
    public ArrayList<TaiKhoanDTO> find(String tentk) {
        ArrayList<TaiKhoanDTO> tkDTO = new ArrayList<>();
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        tkDTO = tkDAO.find(tentk);
        return tkDTO;
    }
    
   public ArrayList<NhanVienDTO> manv(){
        ArrayList<NhanVienDTO> list = new ArrayList<>();
        TaiKhoanDAO tkdao = new TaiKhoanDAO();
        list = tkdao.MaNV();
        return list;
    }
   
    public static void main(String[] args) {
       TaiKhoanBUS tkBUS = new TaiKhoanBUS();
       TaiKhoanDTO tk = new TaiKhoanDTO();
      
    }
}
