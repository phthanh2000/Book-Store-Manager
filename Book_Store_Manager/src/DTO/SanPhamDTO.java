/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author PHAN GIA PHAT
 */
public class SanPhamDTO {

    public int getMasach() {
        return masach;
    }

    public SanPhamDTO() {
    }

    public SanPhamDTO(int masach) {
        this.masach = masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTentacgia() {
        return tentacgia;
    }

    public void setTentacgia(String tentacgia) {
        this.tentacgia = tentacgia;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public int getNamxb() {
        return namxb;
    }

    public void setNamxb(int namxb) {
        this.namxb = namxb;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getMatl() {
        return matl;
    }

    public void setMatl(int matl) {
        this.matl = matl;
    }
    private int masach;
    private String tensach;
    private String tentacgia;
    private String nxb;
    private int namxb;
    private int gia;
    private int  matl;
    
    
   
}
