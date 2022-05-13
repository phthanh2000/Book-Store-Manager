/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import Tools.TableUtil;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author hoalo
 */
public class NhanVienBUS {
    
    public static NhanVienDTO getNhanVien(int manv) {
        return NhanVienDAO.getNhanVien(manv);
    }
    
    public int ma(){
        return NhanVienDAO.getNewID()-1;
    }
    
    public void add(String hoten, String sdt, String dc) {
        NhanVienDAO nvDAO = new NhanVienDAO();
        
        NhanVienDTO nv = new NhanVienDTO(nvDAO.getNewID());
        nv.setHoten(hoten);
        nv.setSdt(sdt);
        nv.setDiachi(dc);
        
        nvDAO.add(nv);
    }
    
//    public void delete(JTable tblnvData) {
//        int manv = TableUtil.getTable(tblnvData);
//        if (manv != 1) {
//            NhanVienDAO nvDAO = new NhanVienDAO();
//            nvDAO.delete(manv);
//        }
//    }
       public static void delete(int matl) {
        NhanVienDAO nccDAO = new NhanVienDAO();
        nccDAO.delete(matl);
    }
    public void edit(int manv, String hoten, String sdt, String dc ) {
        NhanVienDTO nv = new NhanVienDTO(manv);
        
        nv.setHoten(hoten);
        nv.setSdt(sdt);
        nv.setDiachi(dc);
        
        NhanVienDAO nvDAO = new NhanVienDAO();
        nvDAO.edit(nv);
    }
    
    public ArrayList<NhanVienDTO> find(String hoten){
        ArrayList<NhanVienDTO> list = new ArrayList<>();
        NhanVienDAO nvdao = new NhanVienDAO();
        list = nvdao.find(hoten);
        return list;
    }
}
