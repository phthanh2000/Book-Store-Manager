package GUI;

import BUS.*;
import DAO.LoaiSachDAO;
import DTO.HoaDonDTO;
import DTO.LoaiSachDTO;
import DTO.NhaCungCapDTO;
import DTO.SanPhamDTO;
import DTO.PhieuNhapDTO;
import Tools.RegexExpression;
import Tools.TableUtil;
import Tools.Swap;
import Tools.ThongBao;
import java.awt.Point;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Acer
 */
public class EMPLOYEE extends javax.swing.JFrame {

    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    LoaiSachBUS lsBUS = new LoaiSachBUS();
    SanPhamBUS spBUS = new SanPhamBUS();
    PhieuNhapBUS pnBUS = new PhieuNhapBUS();
    HoaDonBUS hdBUS = new HoaDonBUS();

    public EMPLOYEE() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        theloai();
        nhacungcap();
        nhanvien();
        jTextFieldmancc.setEnabled(false);
        jTextFieldmatl.setEnabled(false);
        jTextFieldmasach.setEnabled(false);
        jTextFieldmapn.setEnabled(false);
        jTextFieldmapn.setEnabled(false);
        jTextFieldmahd.setEnabled(false);
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        try {
            nccBUS.updateTable(jTablencc);
            jTablencc.removeColumn(jTablencc.getColumnModel().getColumn(0));
            lsBUS.updateTable(jTabletl);
            jTabletl.removeColumn(jTabletl.getColumnModel().getColumn(0));
            spBUS.updateTable(jTablesach);
            jTablesach.removeColumn(jTablesach.getColumnModel().getColumn(0));
            pnBUS.updateTable(jTablepn);
            jTablepn.removeColumn(jTablepn.getColumnModel().getColumn(0));
            hdBUS.updateTable(jTablehd);
            jTablehd.removeColumn(jTablehd.getColumnModel().getColumn(0));

        } catch (Exception ex) {
            Logger.getLogger(EMPLOYEE.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    String[] arrTenTheLoai;
    int[] maTheLoai;

    public void theloai() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_ch_sach?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT tentl,matl from theloaisach";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrTenTheLoai = new String[tl.size()];
            maTheLoai = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrTenTheLoai[i] = tl.get(i).getTen();
                maTheLoai[i] = tl.get(i).getMa();
                jComboBoxtheloai.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }

    public void updatetheloai() {
        try {
            jComboBoxtheloai.removeAllItems();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_ch_sach?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT tentl,matl from theloaisach";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrTenTheLoai = new String[tl.size()];
            maTheLoai = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrTenTheLoai[i] = tl.get(i).getTen();
                maTheLoai[i] = tl.get(i).getMa();
                jComboBoxtheloai.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    String[] arrTenNhaCungCap;
    int[] maNhaCungCap;

    public void nhacungcap() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_ch_sach?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT tenncc,mancc from nhacungcap";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrTenNhaCungCap = new String[tl.size()];
            maNhaCungCap = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrTenNhaCungCap[i] = tl.get(i).getTen();
                maNhaCungCap[i] = tl.get(i).getMa();
                jComboBoxncc.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }

    public void updatenhacungcap() {
        try {
            jComboBoxncc.removeAllItems();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_ch_sach?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT tenncc,mancc from nhacungcap";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrTenNhaCungCap = new String[tl.size()];
            maNhaCungCap = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrTenNhaCungCap[i] = tl.get(i).getTen();
                maNhaCungCap[i] = tl.get(i).getMa();
                jComboBoxncc.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }
    String[] arrTenNhanVien;
    int[] maNhanVien;

    public void nhanvien() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_ch_sach?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT hoten,manv  from nhanvien";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrTenNhanVien = new String[tl.size()];
            maNhanVien = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrTenNhanVien[i] = tl.get(i).getTen();
                maNhanVien[i] = tl.get(i).getMa();
                jComboBoxnv.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }

    public void updatenhanvien() {
        try {
            jComboBoxnv.removeAllItems();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_ch_sach?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT hoten,manv  from nhanvien";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrTenNhanVien = new String[tl.size()];
            maNhanVien = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrTenNhanVien[i] = tl.get(i).getTen();
                maNhanVien[i] = tl.get(i).getMa();
                jComboBoxnv.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }
    String[] arrSach;
    int[] maSach;

    public void sach() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_ch_sach?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT tensach,masach from sach";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrSach = new String[tl.size()];
            maSach = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrSach[i] = tl.get(i).getTen();
                maSach[i] = tl.get(i).getMa();
                CHITIETHOADON.jComboBoxsach.addItem(tl.get(i).getTen());
                CHITIETPHIEUNHAP.jComboBoxsach.addItem(tl.get(i).getTen());
            }
        } catch (Exception e) {
        }
    }

    public void updatesach() {
        try {
            CHITIETHOADON.jComboBoxsach.removeAllItems();
            CHITIETPHIEUNHAP.jComboBoxsach.removeAllItems();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String data = "jdbc:mysql://localhost:3306/ql_ch_sach?useUnicode=true&characterEncoding=UTF-8";
            Connection con = DriverManager.getConnection(data, "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT tensach,masach from sach";
            ResultSet rs = stat.executeQuery(query);
            ArrayList<Swap> tl = new ArrayList<>();
            while (rs.next()) {
                tl.add(new Swap(rs.getString(1), rs.getInt(2)));
            }
            arrSach = new String[tl.size()];
            maSach = new int[tl.size()];
            for (int i = 0; i < tl.size(); i++) {
                arrSach[i] = tl.get(i).getTen();
                maSach[i] = tl.get(i).getMa();
                CHITIETHOADON.jComboBoxsach.addItem(tl.get(i).getTen());
                CHITIETPHIEUNHAP.jComboBoxsach.addItem(tl.get(i).getTen());

            }
        } catch (Exception e) {
        }
    }

    public void AutomaticallyClosedMsgBox(long time, String text) {
        JOptionPane jop = new JOptionPane();
        jop.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        jop.setMessage(text);
        JDialog dialog = jop.createDialog(null, "Thông báo");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablencc = new javax.swing.JTable();
        jButtonsuancc = new javax.swing.JButton();
        jButtonxoancc = new javax.swing.JButton();
        jButtonthemncc = new javax.swing.JButton();
        jButtontimncc = new javax.swing.JButton();
        jTextFieldtim = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldmancc = new javax.swing.JTextField();
        jTextFieldtenncc = new javax.swing.JTextField();
        jTextFielddc = new javax.swing.JTextField();
        jTextFieldsdt = new javax.swing.JTextField();
        jTabbedPane10 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablesach = new javax.swing.JTable();
        jButtonsuasach = new javax.swing.JButton();
        jButtonxoasach = new javax.swing.JButton();
        jButtonthemsach = new javax.swing.JButton();
        jButtontimsach = new javax.swing.JButton();
        jTextFieldtimsach = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldmasach = new javax.swing.JTextField();
        jTextFieldtensach = new javax.swing.JTextField();
        jTextFieldtentg = new javax.swing.JTextField();
        jTextFieldnxb = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldgiasach = new javax.swing.JTextField();
        jTextFieldnamxb = new javax.swing.JTextField();
        jComboBoxtheloai = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTabletl = new javax.swing.JTable();
        jButtonsuatl = new javax.swing.JButton();
        jButtonxoatl = new javax.swing.JButton();
        jButtonthemtl = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldmatl = new javax.swing.JTextField();
        jTextFieldtentl = new javax.swing.JTextField();
        jTabbedPanepn = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablepn = new javax.swing.JTable();
        jButtonsuapn = new javax.swing.JButton();
        jButtonxoapn = new javax.swing.JButton();
        jButtonthempn = new javax.swing.JButton();
        jButtontimpn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldmapn = new javax.swing.JTextField();
        jComboBoxncc = new javax.swing.JComboBox<>();
        jDateChoosertimphieunhap = new com.toedter.calendar.JDateChooser();
        jDateChooserngaypn = new com.toedter.calendar.JDateChooser();
        jTabbedPane11 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTablehd = new javax.swing.JTable();
        jButtonxoahd = new javax.swing.JButton();
        jButtonthemhd = new javax.swing.JButton();
        jButtontimhd = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldmahd = new javax.swing.JTextField();
        jDateChooserngayhd = new com.toedter.calendar.JDateChooser();
        jComboBoxnv = new javax.swing.JComboBox<>();
        jDateChoosertimhd = new com.toedter.calendar.JDateChooser();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jButton27 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        jButtonXoaTK1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ CỬA HÀNG SÁCH");

        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 102));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jTabbedPane4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jTablencc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTablencc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên nhà cung cấp", "Địa chỉ", "SĐT"
            }
        ));
        jTablencc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablenccMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTablenccMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTablencc);

        jButtonsuancc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonsuancc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pencil-30.png"))); // NOI18N
        jButtonsuancc.setText("SỬA");
        jButtonsuancc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsuanccActionPerformed(evt);
            }
        });

        jButtonxoancc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonxoancc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-trash-can-30.png"))); // NOI18N
        jButtonxoancc.setText("XÓA");
        jButtonxoancc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonxoanccActionPerformed(evt);
            }
        });

        jButtonthemncc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonthemncc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-add-new-30.png"))); // NOI18N
        jButtonthemncc.setText("THÊM");
        jButtonthemncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonthemnccActionPerformed(evt);
            }
        });

        jButtontimncc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtontimncc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-google-web-search-30.png"))); // NOI18N
        jButtontimncc.setText("TÌM KIẾM");
        jButtontimncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtontimnccActionPerformed(evt);
            }
        });

        jTextFieldtim.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldtim.setPreferredSize(new java.awt.Dimension(7, 28));
        jTextFieldtim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldtimActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Mã nhà cung cấp: ");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Tên nhà cung cấp:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Địa chỉ:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Số điện thoại:");

        jTextFieldmancc.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldmancc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldmanccActionPerformed(evt);
            }
        });

        jTextFieldtenncc.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldtenncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldtennccActionPerformed(evt);
            }
        });

        jTextFielddc.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFielddc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFielddcActionPerformed(evt);
            }
        });

        jTextFieldsdt.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldsdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldsdtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldsdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFielddc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldtenncc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldmancc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextFieldtim, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonxoancc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonthemncc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonsuancc, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtontimncc))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(489, 489, 489))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtontimncc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldtim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonthemncc, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonsuancc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonxoancc, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldmancc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldtenncc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextFielddc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jTextFieldsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("DANH SÁCH", jPanel2);

        jTabbedPane1.addTab("NHÀ CUNG CẤP", new javax.swing.ImageIcon(getClass().getResource("/images/khachHang.png")), jTabbedPane4); // NOI18N

        jTabbedPane10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jScrollPane4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane4MouseClicked(evt);
            }
        });

        jTablesach.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTablesach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên sách", "Tên tác giả", "Nhà xuất bản", "Năm xuất bản", "Giá", "Thể loại"
            }
        ));
        jTablesach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablesachMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTablesach);

        jButtonsuasach.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonsuasach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pencil-30.png"))); // NOI18N
        jButtonsuasach.setText("SỬA");
        jButtonsuasach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsuasachActionPerformed(evt);
            }
        });

        jButtonxoasach.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonxoasach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-trash-can-30.png"))); // NOI18N
        jButtonxoasach.setText("XÓA");
        jButtonxoasach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonxoasachActionPerformed(evt);
            }
        });

        jButtonthemsach.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonthemsach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-add-new-30.png"))); // NOI18N
        jButtonthemsach.setText("THÊM");
        jButtonthemsach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonthemsachActionPerformed(evt);
            }
        });

        jButtontimsach.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtontimsach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-google-web-search-30.png"))); // NOI18N
        jButtontimsach.setText("TÌM KIẾM");
        jButtontimsach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtontimsachActionPerformed(evt);
            }
        });

        jTextFieldtimsach.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldtimsach.setPreferredSize(new java.awt.Dimension(7, 28));
        jTextFieldtimsach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldtimsachActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Mã sách: ");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Tên sách:");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("Tên tác giả:");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("Nhà xuất bản:");

        jTextFieldmasach.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldmasach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldmasachActionPerformed(evt);
            }
        });

        jTextFieldtensach.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldtensach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldtensachActionPerformed(evt);
            }
        });

        jTextFieldtentg.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldtentg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldtentgActionPerformed(evt);
            }
        });

        jTextFieldnxb.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldnxb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldnxbActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("Thể loại");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setText("Giá:");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("Năm xuất bản:");

        jTextFieldgiasach.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldgiasach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldgiasachActionPerformed(evt);
            }
        });

        jTextFieldnamxb.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldnamxb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldnamxbActionPerformed(evt);
            }
        });

        jComboBoxtheloai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxtheloaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldnxb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldtentg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldtensach, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldmasach, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jTextFieldtimsach, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldgiasach, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldnamxb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonsuasach, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonxoasach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonthemsach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtontimsach)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxtheloai, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtontimsach, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldtimsach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButtonthemsach, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonsuasach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonxoasach, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldmasach, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldtensach, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldtentg, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldnxb, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))))
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextFieldnamxb, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextFieldgiasach, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxtheloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane10.addTab("DANH SÁCH ", jPanel8);

        jTabletl.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTabletl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Tên thể loại"
            }
        ));
        jTabletl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabletlMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTabletl);

        jButtonsuatl.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonsuatl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pencil-30.png"))); // NOI18N
        jButtonsuatl.setText("SỬA");
        jButtonsuatl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsuatlActionPerformed(evt);
            }
        });

        jButtonxoatl.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonxoatl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-trash-can-30.png"))); // NOI18N
        jButtonxoatl.setText("XÓA");
        jButtonxoatl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonxoatlActionPerformed(evt);
            }
        });

        jButtonthemtl.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonthemtl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-add-new-30.png"))); // NOI18N
        jButtonthemtl.setText("THÊM");
        jButtonthemtl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonthemtlActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setText("Mã thể loại: ");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("Tên thể loại:");

        jTextFieldmatl.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldmatl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldmatlActionPerformed(evt);
            }
        });

        jTextFieldtentl.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldtentl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldtentlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldtentl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldmatl, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonsuatl, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonxoatl, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonthemtl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButtonthemtl, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonsuatl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldmatl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldtentl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonxoatl, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane10.addTab("LOẠI SÁCH", jPanel9);

        jTabbedPane1.addTab("SẢN PHẨM        ", new javax.swing.ImageIcon(getClass().getResource("/images/sanpham.png")), jTabbedPane10); // NOI18N

        jTabbedPanepn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jTablepn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTablepn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Ngày nhập", "Nhà cung cấp"
            }
        ));
        jTablepn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablepnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTablepnMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTablepn);

        jButtonsuapn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonsuapn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pencil-30.png"))); // NOI18N
        jButtonsuapn.setText("SỬA");
        jButtonsuapn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsuapnActionPerformed(evt);
            }
        });

        jButtonxoapn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonxoapn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-trash-can-30.png"))); // NOI18N
        jButtonxoapn.setText("XÓA");
        jButtonxoapn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonxoapnActionPerformed(evt);
            }
        });

        jButtonthempn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonthempn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-add-new-30.png"))); // NOI18N
        jButtonthempn.setText("THÊM");
        jButtonthempn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonthempnActionPerformed(evt);
            }
        });

        jButtontimpn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtontimpn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-google-web-search-30.png"))); // NOI18N
        jButtontimpn.setText("TÌM KIẾM");
        jButtontimpn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtontimpnActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Mã phiếu nhập: ");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Ngày:");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Nhà cung cấp:");

        jTextFieldmapn.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldmapn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldmapnActionPerformed(evt);
            }
        });

        jComboBoxncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxnccActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldmapn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                            .addComponent(jComboBoxncc, javax.swing.GroupLayout.Alignment.LEADING, 0, 334, Short.MAX_VALUE)
                            .addComponent(jDateChooserngaypn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jDateChoosertimphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonsuapn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonxoapn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonthempn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtontimpn))
                .addGap(489, 489, 489))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChoosertimphieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtontimpn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButtonthempn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonsuapn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonxoapn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldmapn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jDateChooserngaypn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jComboBoxncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(137, 137, 137))
        );

        jTabbedPanepn.addTab("DANH SÁCH ", jPanel6);

        jTabbedPane1.addTab("PHIẾU NHẬP    ", new javax.swing.ImageIcon(getClass().getResource("/images/doitac.png")), jTabbedPanepn); // NOI18N

        jTabbedPane11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jTablehd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTablehd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Ngày hóa đơn", "Nhân viên"
            }
        ));
        jTablehd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablehdMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTablehdMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(jTablehd);

        jButtonxoahd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonxoahd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-trash-can-30.png"))); // NOI18N
        jButtonxoahd.setText("XÓA");
        jButtonxoahd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonxoahdActionPerformed(evt);
            }
        });

        jButtonthemhd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonthemhd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-add-new-30.png"))); // NOI18N
        jButtonthemhd.setText("THÊM");
        jButtonthemhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonthemhdActionPerformed(evt);
            }
        });

        jButtontimhd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtontimhd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-google-web-search-30.png"))); // NOI18N
        jButtontimhd.setText("TÌM KIẾM");
        jButtontimhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtontimhdActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Mã hóa đơn: ");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("Ngày:");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("Nhân viên:");

        jTextFieldmahd.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldmahd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldmahdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldmahd, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserngayhd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxnv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jDateChoosertimhd, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonxoahd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonthemhd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtontimhd))
                .addGap(489, 489, 489))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtontimhd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChoosertimhd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldmahd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(21, 21, 21)
                                .addComponent(jLabel19))
                            .addComponent(jDateChooserngayhd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jComboBoxnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButtonthemhd, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonxoahd, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(194, 194, 194))
        );

        jTabbedPane11.addTab("DANH SÁCH ", jPanel10);

        jTabbedPane1.addTab("HÓA ĐƠN        ", new javax.swing.ImageIcon(getClass().getResource("/images/hoadon.png")), jTabbedPane11); // NOI18N

        jTabbedPane7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jTable7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Ngày", "Thành tiền"
            }
        ));
        jScrollPane8.setViewportView(jTable7);

        jButton27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-google-web-search-30.png"))); // NOI18N
        jButton27.setText("TÌM KIẾM");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("TỔNG DOANH THU:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser5, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(jDateChooser6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton27))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        jTabbedPane7.addTab("DOANH THU", jPanel7);

        jTabbedPane1.addTab("THỐNG KÊ     ", new javax.swing.ImageIcon(getClass().getResource("/images/doanhthu.png")), jTabbedPane7); // NOI18N

        jButtonXoaTK1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonXoaTK1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/thoat.png"))); // NOI18N
        jButtonXoaTK1.setText("ĐĂNG XUẤT");
        jButtonXoaTK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaTK1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonXoaTK1)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonXoaTK1))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jTextFieldmahdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldmahdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldmahdActionPerformed

    private void jButtontimhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtontimhdActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String timpn = date.format(jDateChooserngayhd.getDate());
        ArrayList<HoaDonDTO> lists = new ArrayList<HoaDonDTO>();
        if (!timpn.isEmpty()) {
            lists = hdBUS.find(timpn);
        }
        hdBUS.uploadTable(jTablehd, lists);
        jTablehd.removeColumn(jTablehd.getColumnModel().getColumn(0));
    }//GEN-LAST:event_jButtontimhdActionPerformed

    private void jButtonthemhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonthemhdActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String ngay = date.format(jDateChooserngayhd.getDate());
        int mancc = maNhanVien[jComboBoxnv.getSelectedIndex()];
        BUS.HoaDonBUS khBUS = new BUS.HoaDonBUS();
        khBUS.add(ngay, mancc);
        try {
            AutomaticallyClosedMsgBox(1500, "Đang thêm.\nXin hãy đợi....");
            ThongBao.noitice("Thêm thành công");
            khBUS.updateTable(jTablehd);
            jTablehd.removeColumn(jTablehd.getColumnModel().getColumn(0));

            jTextFieldmahd.setText(null);
            jComboBoxnv.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(EMPLOYEE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonthemhdActionPerformed

    private void jButtonxoahdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonxoahdActionPerformed
        // TODO add your handling code here:
        //        hdBUS.delete(jTablehd);
        int mahd = Integer.parseInt(jTextFieldmahd.getText());
        hdBUS.delete(mahd);
        HoaDonBUS nvBUS = new HoaDonBUS();
        try {
            AutomaticallyClosedMsgBox(1500, "Đang xóa.\nXin hãy đợi....");
            ThongBao.noitice("Xóa thành công");
            nvBUS.updateTable(jTablehd);
            jTablehd.removeColumn(jTablehd.getColumnModel().getColumn(0));
            jTextFieldmahd.setText(null);

        } catch (Exception ex) {
            Logger.getLogger(EMPLOYEE.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonxoahdActionPerformed

    private void jTablehdMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablehdMousePressed
        // TODO add your handling code here:
        CTHDBUS ctBUS = new CTHDBUS();
        int index = jTablehd.getSelectedRow();
        TableModel model = jTablehd.getModel();
        String mahd = (String) (model.getValueAt(index, 0).toString());
        int mahd1 = Integer.parseInt(mahd);

        JTable table = (JTable) evt.getSource();
        Point point = evt.getPoint();
        int row = table.rowAtPoint(point);
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            CHITIETHOADON them = new CHITIETHOADON();
            them.setVisible(true);
            dispose();
            them.jTextFieldmahd.setText(mahd);
            try {
                ctBUS.updateTable(them.jTablecthd, mahd1);
                them.jTablecthd.removeColumn(them.jTablecthd.getColumnModel().getColumn(1));
                them.jTablecthd.removeColumn(them.jTablecthd.getColumnModel().getColumn(0));
            } catch (Exception ex) {
                Logger.getLogger(EMPLOYEE.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jTablehdMousePressed

    private void jTablehdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablehdMouseClicked
        // TODO add your handling code here:
        //        hdBUS.loadInfo(jTablehd, jTextFieldmahd, jDateChooserngayhd, jComboBoxnv);
        try {
            // TODO add your handling code here:
            //        pnBUS.loadInfo(jTablepn, jTextFieldmapn,jDateChooserngaypn, jComboBoxncc);
            jTextFieldmahd.setText((jTablehd.getModel().getValueAt(jTablehd.getSelectedRow(), 0)).toString());
            Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse((jTablehd.getModel().getValueAt(jTablehd.getSelectedRow(), 1)).toString());
            jDateChooserngayhd.setDate(date);
            jComboBoxnv.setSelectedItem((jTablehd.getModel().getValueAt(jTablehd.getSelectedRow(), 2)).toString());
        } catch (ParseException ex) {
            Logger.getLogger(EMPLOYEE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTablehdMouseClicked

    private void jComboBoxnccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxnccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxnccActionPerformed

    private void jTextFieldmapnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldmapnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldmapnActionPerformed

    private void jButtontimpnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtontimpnActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String timpn = date.format(jDateChooserngaypn.getDate());
        ArrayList<PhieuNhapDTO> lists = new ArrayList<PhieuNhapDTO>();
        if (!timpn.isEmpty()) {
            lists = pnBUS.find(timpn);
        }
        pnBUS.uploadTable(jTablepn, lists);
        jTablepn.removeColumn(jTablepn.getColumnModel().getColumn(0));

    }//GEN-LAST:event_jButtontimpnActionPerformed

    private void jButtonthempnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonthempnActionPerformed
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String ngay = date.format(jDateChooserngaypn.getDate());
        int mancc = maNhaCungCap[jComboBoxncc.getSelectedIndex()];
        BUS.PhieuNhapBUS khBUS = new BUS.PhieuNhapBUS();
        khBUS.add(ngay, mancc);
        try {
            AutomaticallyClosedMsgBox(1500, "Đang thêm.\nXin hãy đợi....");
            ThongBao.noitice("Thêm thành công");
            khBUS.updateTable(jTablepn);
            jTablepn.removeColumn(jTablepn.getColumnModel().getColumn(0));
            jTextFieldmapn.setText(null);
            jComboBoxncc.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(EMPLOYEE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonthempnActionPerformed

    private void jButtonxoapnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonxoapnActionPerformed
        //        pnBUS.delete(jTablepn);
        int mapn = Integer.parseInt(jTextFieldmapn.getText());
        pnBUS.delete(mapn);
        PhieuNhapBUS nvBUS = new PhieuNhapBUS();
        try {
            AutomaticallyClosedMsgBox(1500, "Đang xóa.\nXin hãy đợi....");
            ThongBao.noitice("Xóa thành công");
            nvBUS.updateTable(jTablepn);
            jTablepn.removeColumn(jTablepn.getColumnModel().getColumn(0));
            jTextFieldmapn.setText(null);

        } catch (Exception ex) {
            Logger.getLogger(EMPLOYEE.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonxoapnActionPerformed

    private void jButtonsuapnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsuapnActionPerformed
        // TODO add your handling code here:
        int mapn = Integer.valueOf(jTextFieldmapn.getText());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String ngay = date.format(jDateChooserngaypn.getDate());
        int mancc = maNhaCungCap[jComboBoxncc.getSelectedIndex()];
        BUS.PhieuNhapBUS pnBUS = new BUS.PhieuNhapBUS();
        pnBUS.edit(mapn, ngay, mancc);
        try {
            AutomaticallyClosedMsgBox(1500, "Đang sửa.\nXin hãy đợi....");
            ThongBao.noitice("Sửa thành công");
            pnBUS.updateTable(jTablepn);
            jTablepn.removeColumn(jTablepn.getColumnModel().getColumn(0));

            jTextFieldmasach.setText(null);
            jTextFieldtensach.setText(null);
            jTextFieldtentg.setText(null);
            jTextFieldnxb.setText(null);
            jTextFieldnamxb.setText(null);
            jTextFieldgiasach.setText(null);

        } catch (Exception ex) {
            Logger.getLogger(EMPLOYEE.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonsuapnActionPerformed

    private void jTablepnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablepnMousePressed
        CTPNBUS pnBUS = new CTPNBUS();
        int index = jTablepn.getSelectedRow();
        TableModel model = jTablepn.getModel();
        String mapn = (String) (model.getValueAt(index, 0).toString());
        int mapn1 = Integer.parseInt(mapn);
        JTable table = (JTable) evt.getSource();
        Point point = evt.getPoint();
        int row = table.rowAtPoint(point);
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            CHITIETPHIEUNHAP them = new CHITIETPHIEUNHAP();
            them.setVisible(true);
            dispose();
            them.jTextFieldmahd.setText(mapn);
            try {
                pnBUS.updateTable(them.jTablecthd, mapn1);
            } catch (Exception ex) {
                Logger.getLogger(EMPLOYEE.class.getName()).log(Level.SEVERE, null, ex);
            }
            them.jTablecthd.removeColumn(them.jTablecthd.getColumnModel().getColumn(1));
            them.jTablecthd.removeColumn(them.jTablecthd.getColumnModel().getColumn(0));
        }
    }//GEN-LAST:event_jTablepnMousePressed

    private void jTablepnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablepnMouseClicked
        try {
            // TODO add your handling code here:
            //        pnBUS.loadInfo(jTablepn, jTextFieldmapn,jDateChooserngaypn, jComboBoxncc);
            jTextFieldmapn.setText((jTablepn.getModel().getValueAt(jTablepn.getSelectedRow(), 0)).toString());
            Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse((jTablepn.getModel().getValueAt(jTablepn.getSelectedRow(), 1)).toString());
            jDateChooserngaypn.setDate(date);
            jComboBoxncc.setSelectedItem((jTablepn.getModel().getValueAt(jTablepn.getSelectedRow(), 2)).toString());
        } catch (ParseException ex) {
            Logger.getLogger(EMPLOYEE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTablepnMouseClicked

    private void jTextFieldtentlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldtentlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldtentlActionPerformed

    private void jTextFieldmatlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldmatlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldmatlActionPerformed

    private void jButtonthemtlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonthemtlActionPerformed
        // TODO add your handling code here:
        if (jTextFieldtentl.getText().isEmpty()) {
            ThongBao.error("Tên không để trống");
        } else {
            String tenncc = jTextFieldtentl.getText();
            BUS.LoaiSachBUS khBUS = new BUS.LoaiSachBUS();
            khBUS.add(tenncc);
            try {
                AutomaticallyClosedMsgBox(1500, "Đang thêm.\nXin hãy đợi....");
                ThongBao.noitice("Thêm thành công");
                khBUS.updateTable(jTabletl);
                jTabletl.removeColumn(jTabletl.getColumnModel().getColumn(0));
                updatetheloai();
                jTextFieldmatl.setText(null);
                jTextFieldtentl.setText(null);

            } catch (Exception ex) {
                Logger.getLogger(EMPLOYEE.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonthemtlActionPerformed

    private void jButtonxoatlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonxoatlActionPerformed
        // TODO add your handling code here:
        int matl = Integer.parseInt(jTextFieldmatl.getText());
        lsBUS.delete(matl);
        LoaiSachBUS nvBUS = new LoaiSachBUS();
        try {
            AutomaticallyClosedMsgBox(1500, "Đang xóa.\nXin hãy đợi....");
            ThongBao.noitice("Xóa thành công");
            nvBUS.updateTable(jTabletl);
            jTabletl.removeColumn(jTabletl.getColumnModel().getColumn(0));
            updatetheloai();
            jTextFieldmatl.setText(null);
            jTextFieldtentl.setText(null);

        } catch (Exception ex) {
            Logger.getLogger(EMPLOYEE.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonxoatlActionPerformed

    private void jButtonsuatlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsuatlActionPerformed
        // TODO add your handling code here:
        if (jTextFieldtentl.getText().isEmpty()) {
            ThongBao.error("Tên không để trống");
        } else {
            int matl = Integer.valueOf(jTextFieldmatl.getText());
            String tentl = jTextFieldtentl.getText();
            BUS.LoaiSachBUS nccBUS = new BUS.LoaiSachBUS();
            nccBUS.edit(matl, tentl);
            try {
                AutomaticallyClosedMsgBox(1500, "Đang sửa.\nXin hãy đợi....");
                ThongBao.noitice("sửa thành công");
                nccBUS.updateTable(jTabletl);
                jTabletl.removeColumn(jTabletl.getColumnModel().getColumn(0));
                updatetheloai();
                jTextFieldmatl.setText(null);
                jTextFieldtentl.setText(null);

            } catch (Exception ex) {
                Logger.getLogger(EMPLOYEE.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonsuatlActionPerformed

    private void jTabletlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabletlMouseClicked
        // TODO add your handling code here:
        // lsBUS.loadInfo(jTabletl, jTextFieldmatl, jTextFieldtentl);
        jTextFieldmatl.setText((jTabletl.getModel().getValueAt(jTabletl.getSelectedRow(), 0)).toString());
        jTextFieldtentl.setText((jTabletl.getModel().getValueAt(jTabletl.getSelectedRow(), 1)).toString());
    }//GEN-LAST:event_jTabletlMouseClicked

    private void jComboBoxtheloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxtheloaiActionPerformed

    }//GEN-LAST:event_jComboBoxtheloaiActionPerformed

    private void jTextFieldnamxbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldnamxbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldnamxbActionPerformed

    private void jTextFieldgiasachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldgiasachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldgiasachActionPerformed

    private void jTextFieldnxbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldnxbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldnxbActionPerformed

    private void jTextFieldtentgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldtentgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldtentgActionPerformed

    private void jTextFieldtensachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldtensachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldtensachActionPerformed

    private void jTextFieldmasachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldmasachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldmasachActionPerformed

    private void jTextFieldtimsachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldtimsachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldtimsachActionPerformed

    private void jButtontimsachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtontimsachActionPerformed
        // TODO add your handling code here:
        String timsach = jTextFieldtimsach.getText();
        ArrayList<SanPhamDTO> lists = new ArrayList<SanPhamDTO>();
        if (!timsach.isEmpty()) {
            lists = spBUS.find(timsach);
        }
        spBUS.uploadTable(jTablesach, lists);
        jTablesach.removeColumn(jTablesach.getColumnModel().getColumn(0));

    }//GEN-LAST:event_jButtontimsachActionPerformed

    private void jButtonthemsachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonthemsachActionPerformed
        // TODO add your handling code here:
        if (jTextFieldtensach.getText().isEmpty()) {
            ThongBao.error("Tên không để trống");
        } else if (jTextFieldtentg.getText().isEmpty())
            ThongBao.error("Tác giả không để trống");
        else if (jTextFieldnxb.getText().isEmpty())
            ThongBao.error("NXB  không để trống");
        else if (jTextFieldnamxb.getText().isEmpty())
            ThongBao.error("Năm XB không để trống");
        else if (jTextFieldgiasach.getText().isEmpty())
            ThongBao.error("Giá không để trống");
        else {
            String tens = jTextFieldtensach.getText();
            String tentg = jTextFieldtentg.getText();
            String nxb = jTextFieldnxb.getText();
            int namxb = Integer.parseInt(jTextFieldnamxb.getText());
            int gia = Integer.parseInt(jTextFieldgiasach.getText());
            int matl = maTheLoai[jComboBoxtheloai.getSelectedIndex()];
            BUS.SanPhamBUS khBUS = new BUS.SanPhamBUS();
            khBUS.add(tens, tentg, nxb, namxb, gia, matl);
            try {
                AutomaticallyClosedMsgBox(1500, "Đang thêm.\nXin hãy đợi....");
                ThongBao.noitice("Thêm thành công");
                khBUS.updateTable(jTablesach);
                updatesach();
                jTablesach.removeColumn(jTablesach.getColumnModel().getColumn(0));
                jTextFieldmasach.setText(null);
                jTextFieldtensach.setText(null);
                jTextFieldtentg.setText(null);
                jTextFieldnxb.setText(null);
                jTextFieldnamxb.setText(null);
                jTextFieldgiasach.setText(null);
                jComboBoxtheloai.setSelectedIndex(0);

            } catch (Exception ex) {
                Logger.getLogger(EMPLOYEE.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonthemsachActionPerformed

    private void jButtonxoasachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonxoasachActionPerformed
        int mas = Integer.parseInt(jTextFieldmasach.getText());
        spBUS.delete(mas);
        SanPhamBUS nvBUS = new SanPhamBUS();
        try {
            AutomaticallyClosedMsgBox(1500, "Đang xóa.\nXin hãy đợi....");
            ThongBao.noitice("Xóa thành công");
            nvBUS.updateTable(jTablesach);
            updatesach();
            jTablesach.removeColumn(jTablesach.getColumnModel().getColumn(0));
            jTextFieldmasach.setText(null);
            jTextFieldtensach.setText(null);
            jTextFieldtentg.setText(null);
            jTextFieldnxb.setText(null);
            jTextFieldnamxb.setText(null);
            jTextFieldgiasach.setText(null);
            jComboBoxtheloai.setSelectedIndex(0);

        } catch (Exception ex) {
            Logger.getLogger(EMPLOYEE.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonxoasachActionPerformed

    private void jButtonsuasachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsuasachActionPerformed
        // TODO add your handling code here:
        if (jTextFieldtensach.getText().isEmpty()) {
            ThongBao.error("Tên không để trống");
        } else if (jTextFieldtentg.getText().isEmpty())
            ThongBao.error("Tác giả không để trống");
        else if (jTextFieldnxb.getText().isEmpty())
            ThongBao.error("NXB  không để trống");
        else if (jTextFieldnamxb.getText().isEmpty())
            ThongBao.error("Năm XB không để trống");
        else if (jTextFieldgiasach.getText().isEmpty())
            ThongBao.error("Giá không để trống");
        else {
            int mansach = Integer.valueOf(jTextFieldmasach.getText());
            String tens = jTextFieldtensach.getText();
            String tentg = jTextFieldtentg.getText();
            String nxb = jTextFieldnxb.getText();
            int namxb = Integer.parseInt(jTextFieldnamxb.getText());
            int gia = Integer.parseInt(jTextFieldgiasach.getText());
            int matl = maTheLoai[jComboBoxtheloai.getSelectedIndex()];
            BUS.SanPhamBUS spBUS = new BUS.SanPhamBUS();
            spBUS.edit(mansach, tens, tentg, nxb, namxb, gia, matl);
            try {
                AutomaticallyClosedMsgBox(1500, "Đang sửa.\nXin hãy đợi....");
                ThongBao.noitice("Sửa thành công");
                spBUS.updateTable(jTablesach);
                updatesach();
                jTablesach.removeColumn(jTablesach.getColumnModel().getColumn(0));
                jTextFieldmasach.setText(null);
                jTextFieldtensach.setText(null);
                jTextFieldtentg.setText(null);
                jTextFieldnxb.setText(null);
                jTextFieldnamxb.setText(null);
                jTextFieldgiasach.setText(null);
                jComboBoxtheloai.setSelectedIndex(0);

            } catch (Exception ex) {
                Logger.getLogger(EMPLOYEE.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonsuasachActionPerformed

    private void jScrollPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane4MouseClicked

    private void jTablesachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablesachMouseClicked
        // TODO add your handling code here:
        //        spBUS.loadInfo(jTablesach, jTextFieldmasach, jTextFieldtensach, jTextFieldtentg, jTextFieldnxb, jTextFieldnamxb, jTextFieldgiasach, jComboBoxtheloai);
        jTextFieldmasach.setText((jTablesach.getModel().getValueAt(jTablesach.getSelectedRow(), 0)).toString());
        jTextFieldtensach.setText((jTablesach.getModel().getValueAt(jTablesach.getSelectedRow(), 1)).toString());
        jTextFieldtentg.setText((jTablesach.getModel().getValueAt(jTablesach.getSelectedRow(), 2)).toString());
        jTextFieldnxb.setText((jTablesach.getModel().getValueAt(jTablesach.getSelectedRow(), 3)).toString());
        jTextFieldnamxb.setText((jTablesach.getModel().getValueAt(jTablesach.getSelectedRow(), 4)).toString());
        jTextFieldgiasach.setText((jTablesach.getModel().getValueAt(jTablesach.getSelectedRow(), 5)).toString());
        jComboBoxtheloai.setSelectedItem((jTablesach.getModel().getValueAt(jTablesach.getSelectedRow(), 6)).toString());
    }//GEN-LAST:event_jTablesachMouseClicked

    private void jTextFieldsdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldsdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldsdtActionPerformed

    private void jTextFielddcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFielddcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFielddcActionPerformed

    private void jTextFieldtennccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldtennccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldtennccActionPerformed

    private void jTextFieldmanccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldmanccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldmanccActionPerformed

    private void jTextFieldtimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldtimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldtimActionPerformed

    private void jButtontimnccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtontimnccActionPerformed
        // TODO add your handling code here:
        String tim = jTextFieldtim.getText();
        ArrayList<NhaCungCapDTO> list = new ArrayList<NhaCungCapDTO>();
        if (!tim.isEmpty()) {
            list = nccBUS.find(tim);
        }
        nccBUS.uploadTable(jTablencc, list);
        jTablencc.removeColumn(jTablencc.getColumnModel().getColumn(0));

    }//GEN-LAST:event_jButtontimnccActionPerformed

    private void jButtonthemnccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonthemnccActionPerformed
        if (jTextFieldtenncc.getText().isEmpty()) {
            ThongBao.error("Tên không để trống");
        } else if (jTextFielddc.getText().isEmpty())
            ThongBao.error("Địa chỉ không để trống");
        else if (!RegexExpression.checkPhoneNumber(jTextFieldsdt.getText()))
            ThongBao.error("Số đt không hợp lệ");
        else {
            String tenncc = jTextFieldtenncc.getText();
            String dc = jTextFielddc.getText();
            String sdt = jTextFieldsdt.getText();
            BUS.NhaCungCapBUS khBUS = new BUS.NhaCungCapBUS();
            khBUS.add(tenncc, dc, sdt);
            try {
                AutomaticallyClosedMsgBox(1500, "Đang thêm.\nXin hãy đợi....");
                ThongBao.noitice("Thêm thành công");
                khBUS.updateTable(jTablencc);
                jTablencc.removeColumn(jTablencc.getColumnModel().getColumn(0));
                updatenhacungcap();
                jTextFieldmancc.setText(null);
                jTextFieldtenncc.setText(null);
                jTextFielddc.setText(null);
                jTextFieldsdt.setText(null);

            } catch (Exception ex) {
                Logger.getLogger(EMPLOYEE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonthemnccActionPerformed

    private void jButtonxoanccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonxoanccActionPerformed
        int mancc = Integer.parseInt(jTextFieldmancc.getText());
        nccBUS.delete(mancc);
        NhaCungCapBUS nvBUS = new NhaCungCapBUS();
        try {
            AutomaticallyClosedMsgBox(1500, "Đang xóa.\nXin hãy đợi....");
            ThongBao.noitice("Xóa thành công");
            nvBUS.updateTable(jTablencc);
            jTablencc.removeColumn(jTablencc.getColumnModel().getColumn(0));
            updatenhacungcap();
            jTextFieldmancc.setText(null);
            jTextFieldtenncc.setText(null);
            jTextFielddc.setText(null);
            jTextFieldsdt.setText(null);
        } catch (Exception ex) {
            Logger.getLogger(EMPLOYEE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonxoanccActionPerformed

    private void jButtonsuanccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsuanccActionPerformed
        // TODO add your handling code here:if (txtnvFormHo.getText().isEmpty() || txtnvFormHo.getText().length() > 28)
        if (jTextFieldtenncc.getText().isEmpty()) {
            ThongBao.error("Tên không để trống");
        } else if (jTextFielddc.getText().isEmpty())
            ThongBao.error("Địa chỉ không để trống");
        else if (!RegexExpression.checkPhoneNumber(jTextFieldsdt.getText()))
            ThongBao.error("Số đt không hợp lệ");
        else {
            int mancc = Integer.valueOf(jTextFieldmancc.getText());
            String tenncc = jTextFieldtenncc.getText();
            String dc = jTextFielddc.getText();
            String sdt = jTextFieldsdt.getText();
            BUS.NhaCungCapBUS nccBUS = new BUS.NhaCungCapBUS();
            nccBUS.edit(mancc, tenncc, dc, sdt);
            try {
                AutomaticallyClosedMsgBox(1500, "Đang sửa.\nXin hãy đợi....");
                ThongBao.noitice("sửa thành công");
                nccBUS.updateTable(jTablencc);
                jTablencc.removeColumn(jTablencc.getColumnModel().getColumn(0));
                updatenhacungcap();
                jTextFieldmancc.setText(null);
                jTextFieldtenncc.setText(null);
                jTextFielddc.setText(null);
                jTextFieldsdt.setText(null);

            } catch (Exception ex) {
                Logger.getLogger(EMPLOYEE.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonsuanccActionPerformed

    private void jTablenccMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablenccMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTablenccMousePressed

    private void jTablenccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablenccMouseClicked
        //        // TODO add your handling code here:
        //        nccBUS.loadInfo(jTablencc, jTextFieldmancc, jTextFieldtenncc, jTextFielddc, jTextFieldsdt);
        jTextFieldmancc.setText((jTablencc.getModel().getValueAt(jTablencc.getSelectedRow(), 0)).toString());
        jTextFieldtenncc.setText((jTablencc.getModel().getValueAt(jTablencc.getSelectedRow(), 1)).toString());
        jTextFielddc.setText((jTablencc.getModel().getValueAt(jTablencc.getSelectedRow(), 2)).toString());
        jTextFieldsdt.setText((jTablencc.getModel().getValueAt(jTablencc.getSelectedRow(), 3)).toString());
    }//GEN-LAST:event_jTablenccMouseClicked

    private void jButtonXoaTK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaTK1ActionPerformed
        // TODO add your handling code here:
        LOGIN lg = new LOGIN();
        lg.setLocationRelativeTo(null);
        lg.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonXoaTK1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(EMPLOYEE.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EMPLOYEE.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EMPLOYEE.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EMPLOYEE.class
//                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EMPLOYEE().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButtonXoaTK1;
    private javax.swing.JButton jButtonsuancc;
    private javax.swing.JButton jButtonsuapn;
    private javax.swing.JButton jButtonsuasach;
    private javax.swing.JButton jButtonsuatl;
    private javax.swing.JButton jButtonthemhd;
    private javax.swing.JButton jButtonthemncc;
    private javax.swing.JButton jButtonthempn;
    private javax.swing.JButton jButtonthemsach;
    private javax.swing.JButton jButtonthemtl;
    private javax.swing.JButton jButtontimhd;
    private javax.swing.JButton jButtontimncc;
    private javax.swing.JButton jButtontimpn;
    private javax.swing.JButton jButtontimsach;
    private javax.swing.JButton jButtonxoahd;
    private javax.swing.JButton jButtonxoancc;
    private javax.swing.JButton jButtonxoapn;
    private javax.swing.JButton jButtonxoasach;
    private javax.swing.JButton jButtonxoatl;
    private javax.swing.JComboBox<String> jComboBoxncc;
    private javax.swing.JComboBox<String> jComboBoxnv;
    private javax.swing.JComboBox<String> jComboBoxtheloai;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private com.toedter.calendar.JDateChooser jDateChooserngayhd;
    private com.toedter.calendar.JDateChooser jDateChooserngaypn;
    private com.toedter.calendar.JDateChooser jDateChoosertimhd;
    private com.toedter.calendar.JDateChooser jDateChoosertimphieunhap;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane10;
    private javax.swing.JTabbedPane jTabbedPane11;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTabbedPane jTabbedPanepn;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTablehd;
    private javax.swing.JTable jTablencc;
    private javax.swing.JTable jTablepn;
    private javax.swing.JTable jTablesach;
    private javax.swing.JTable jTabletl;
    private javax.swing.JTextField jTextFielddc;
    private javax.swing.JTextField jTextFieldgiasach;
    private javax.swing.JTextField jTextFieldmahd;
    private javax.swing.JTextField jTextFieldmancc;
    private javax.swing.JTextField jTextFieldmapn;
    private javax.swing.JTextField jTextFieldmasach;
    private javax.swing.JTextField jTextFieldmatl;
    private javax.swing.JTextField jTextFieldnamxb;
    private javax.swing.JTextField jTextFieldnxb;
    private javax.swing.JTextField jTextFieldsdt;
    private javax.swing.JTextField jTextFieldtenncc;
    private javax.swing.JTextField jTextFieldtensach;
    private javax.swing.JTextField jTextFieldtentg;
    private javax.swing.JTextField jTextFieldtentl;
    private javax.swing.JTextField jTextFieldtim;
    private javax.swing.JTextField jTextFieldtimsach;
    // End of variables declaration//GEN-END:variables
}
