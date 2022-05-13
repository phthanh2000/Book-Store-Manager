package DTO;

public class NhanVienDTO {
    private int manv;
    private String hoten;
    private String sdt; 
    private String diachi;

    public NhanVienDTO(int manv) {
        this.manv = manv;
    }
    
    public NhanVienDTO(String hoten) {
        this.hoten = hoten;
    }
    
    public NhanVienDTO(int manv, String hoten, String sdt, String diachi) {
        this.manv = manv;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    
    

}