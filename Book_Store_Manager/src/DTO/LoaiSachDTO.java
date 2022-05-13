/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Acer
 */
public class LoaiSachDTO {

    public int getMatl() {
        return matl;
    }

    public LoaiSachDTO(int matl) {
        this.matl = matl;
    }

    public void setMatl(int matl) {
        this.matl = matl;
    }

    public String getTentl() {
        return tentl;
    }

    public void setTentl(String tentl) {
        this.tentl = tentl;
    }
    private int matl;
    private String tentl;
}
