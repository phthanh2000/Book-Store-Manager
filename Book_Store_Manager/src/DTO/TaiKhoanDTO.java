/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author hoalo
 */
public class TaiKhoanDTO {
    private String ma;
    private int manv;
    private String taikhoan;
    private String matkhau;
    private int quyen;
    
    public TaiKhoanDTO(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public TaiKhoanDTO(int manv){
        this.manv = manv;
    }
    
    public TaiKhoanDTO(int manv, String taikhoan, String matkhau, int quyen) {
        this.manv = manv;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.quyen = quyen;
    }

    public TaiKhoanDTO() {
        
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }    
}
