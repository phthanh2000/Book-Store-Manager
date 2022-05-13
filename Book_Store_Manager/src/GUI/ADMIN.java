/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.NhaCungCapDTO;
import BUS.NhaCungCapBUS;
import BUS.SanPhamBUS;
import Tools.ThongBao;
import Tools.RegexExpression;
import BUS.TaiKhoanBUS;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import Tools.TableUtil;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
public class ADMIN extends javax.swing.JFrame {

    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    EMPLOYEE eBUS = new EMPLOYEE();
    SanPhamBUS spBUS = new SanPhamBUS();

    /**
     * Creates new form ADMIN
     */
    public ADMIN() throws Exception {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        jTextFieldmancc.setEnabled(false);
        txtnvFormMaNV.setEditable(false);
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        //setEnabled(false)
        txtnvFormMaNV.setEnabled(false);
        jTextFieldmancc.setEditable(false);
        txtnvFormMaNV.setEnabled(false);

        initNV(tblnvData);
        initTK(tbltkData);
        nccBUS.updateTable(jTablencc);
        jTablencc.removeColumn(jTablencc.getColumnModel().getColumn(0));
        spBUS.updateTable(jTablesp);
        jTablesp.removeColumn(jTablesp.getColumnModel().getColumn(0));

        TaiKhoanBUS tkBUS = new TaiKhoanBUS();
        ArrayList<NhanVienDTO> list = new ArrayList<>();
        list = tkBUS.manv();
        for (NhanVienDTO ma : list) {
            jComboBox1.addItem((String.valueOf(ma.getManv())) + "-" + ma.getHoten());
        }
        //      for(int i=0; i< manv.length; i++){
        //         jComboBox1.addItem(manv[i]);
        //    }
    }

    public void AutomaticallyClosedMsgBox(long time, String text) {
        JOptionPane jop = new JOptionPane();
        jop.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        jop.setMessage(text);
        JDialog dialog = jop.createDialog(null, "Thông báo");
    }

    //NV
    public static void initNV(JTable tbl) {
        ArrayList<NhanVienDTO> dsnv = NhanVienDAO.load();
        uploadTableNV(tbl, dsnv);
    }

    //NV
    public static void uploadTableNV(JTable tbl, ArrayList<NhanVienDTO> list) {
        String[] columnNames = {"Mã NV", "Họ Tên", "Sđt", "Địa chỉ"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (NhanVienDTO nv : list) {
            data[i][0] = nv.getManv();
            data[i][1] = nv.getHoten();
            data[i][2] = nv.getSdt();
            data[i][3] = nv.getDiachi();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    //NV
    public void updateTableNV(JTable tbl) throws Exception {
        NhanVienDAO nvDAO = new NhanVienDAO();
        ArrayList<NhanVienDTO> dsnv = nvDAO.load();
        uploadTableNV(tbl, dsnv);
    }

    //NV
    public static void loadInfoNV(JTable tbl, JTextField formMaNV, JTextField formHoTen, JTextField formSDT, JTextField formDC) {
        NhanVienDTO nv = NhanVienDAO.getNhanVien(TableUtil.getTable(tbl));

        formMaNV.setText(String.valueOf(nv.getManv()));
        formHoTen.setText(nv.getHoten());
        formSDT.setText(nv.getSdt());
        formDC.setText(nv.getDiachi());
    }

    //TK
    public static void initTK(JTable tbl) {
        ArrayList<TaiKhoanDTO> ds = TaiKhoanDAO.load();
        uploadTableTK(tbl, ds);
    }

    //TK
    public static void uploadTableTK(JTable tbl, ArrayList<TaiKhoanDTO> list) {
        String[] columnNames = {"Mã NV", "Tài khoản", "Mật khẩu", "Quyền"};
        Object[][] data = new Object[list.size()][columnNames.length];
        int i = 0;
        for (TaiKhoanDTO tk : list) {
            data[i][0] = tk.getMa();
            data[i][1] = tk.getTaikhoan();
            data[i][2] = tk.getMatkhau();
            data[i][3] = tk.getQuyen() == 0 ? "Thủ kho" : "Admin";

            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, columnNames);
        tbl.setModel(tableModel);
    }

    //TK
    public void updateTableTK(JTable tbl) throws Exception {
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        ArrayList<TaiKhoanDTO> dstk = tkDAO.load();
        uploadTableTK(tbl, dstk);
    }

    //TK
    public static void loadInfoTK(JTable tbl, JTextField formTK, JTextField formMK
    //   , JComboBox formQuyen, JComboBox formMaNV
    ) {
        TaiKhoanDTO tk = TaiKhoanDAO.getTaiKhoan((String) tbl.getValueAt(tbl.getSelectedRow(), 0));

        System.out.println(tk.getTaikhoan());
        //formTK.setText(tk.getTaikhoan());
        //formMK.setText(tk.getMatkhau());
        //formQuyen.setSelectedItem(tk.getQuyen());
        //formMaNV.setSelectedItem(tk.getMa());

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
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnvData = new javax.swing.JTable();
        jButtonSuaNV = new javax.swing.JButton();
        jButtonXoaNV = new javax.swing.JButton();
        jButtonThemNV = new javax.swing.JButton();
        jButtonTimNV = new javax.swing.JButton();
        jTextFieldTim = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnvFormMaNV = new javax.swing.JTextField();
        txtnvFormHoTen = new javax.swing.JTextField();
        txtnvFormSdt = new javax.swing.JTextField();
        txtnvFormDc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltkData = new javax.swing.JTable();
        jButtonSuaTK = new javax.swing.JButton();
        jButtonXoaTK = new javax.swing.JButton();
        jButtonThemTK = new javax.swing.JButton();
        jButtonTimTK = new javax.swing.JButton();
        jTextFieldTimTK = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtnvFormTK = new javax.swing.JTextField();
        txtnvFormMK = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
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
        jLabel18 = new javax.swing.JLabel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTablesp = new javax.swing.JTable();
        jButtontimsp = new javax.swing.JButton();
        jTextFieldtimsp = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jButtontimthongke = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
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

        jTabbedPane2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        tblnvData.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tblnvData.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Chức vụ"
            }
        ));
        tblnvData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnvDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblnvData);

        jButtonSuaNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonSuaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pencil-30.png"))); // NOI18N
        jButtonSuaNV.setText("SỬA");
        jButtonSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaNVActionPerformed(evt);
            }
        });

        jButtonXoaNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonXoaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-trash-can-30.png"))); // NOI18N
        jButtonXoaNV.setText("XÓA");
        jButtonXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaNVActionPerformed(evt);
            }
        });

        jButtonThemNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-add-new-30.png"))); // NOI18N
        jButtonThemNV.setText("THÊM");
        jButtonThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemNVActionPerformed(evt);
            }
        });

        jButtonTimNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonTimNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-google-web-search-30.png"))); // NOI18N
        jButtonTimNV.setText("TÌM KIẾM");
        jButtonTimNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimNVActionPerformed(evt);
            }
        });

        jTextFieldTim.setMinimumSize(new java.awt.Dimension(7, 28));
        jTextFieldTim.setPreferredSize(new java.awt.Dimension(7, 28));
        jTextFieldTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTimActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Tìm theo tên:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Họ tên:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Địa chỉ:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Số điện thoại:");

        txtnvFormMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnvFormMaNVActionPerformed(evt);
            }
        });

        txtnvFormHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnvFormHoTenActionPerformed(evt);
            }
        });

        txtnvFormSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnvFormSdtActionPerformed(evt);
            }
        });

        txtnvFormDc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnvFormDcActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Mã nhân viên: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(186, 186, 186))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldTim, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtnvFormSdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                        .addComponent(txtnvFormHoTen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                        .addComponent(txtnvFormMaNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                        .addComponent(txtnvFormDc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)))
                .addGap(90, 90, 90)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonXoaNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonThemNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(489, 489, 489))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonXoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtnvFormMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtnvFormHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnvFormSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtnvFormDc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("THÔNG TIN NHÂN VIÊN", jPanel1);

        tbltkData.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tbltkData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Quyền"
            }
        ));
        tbltkData.setMaximumSize(new java.awt.Dimension(2147483647, 384));
        tbltkData.setPreferredSize(new java.awt.Dimension(450, 384));
        tbltkData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltkDataMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbltkData);

        jButtonSuaTK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonSuaTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pencil-30.png"))); // NOI18N
        jButtonSuaTK.setText("SỬA");
        jButtonSuaTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaTKActionPerformed(evt);
            }
        });

        jButtonXoaTK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonXoaTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-trash-can-30.png"))); // NOI18N
        jButtonXoaTK.setText("XÓA");
        jButtonXoaTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaTKActionPerformed(evt);
            }
        });

        jButtonThemTK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonThemTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-add-new-30.png"))); // NOI18N
        jButtonThemTK.setText("THÊM");
        jButtonThemTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemTKActionPerformed(evt);
            }
        });

        jButtonTimTK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtonTimTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-google-web-search-30.png"))); // NOI18N
        jButtonTimTK.setText("TÌM KIẾM");
        jButtonTimTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimTKActionPerformed(evt);
            }
        });

        jTextFieldTimTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTimTKActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Tên nhân viên: ");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Tên đăng nhập:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Mật khẩu:");

        txtnvFormTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnvFormTKActionPerformed(evt);
            }
        });

        txtnvFormMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnvFormMKActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Quyền:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Thủ kho" }));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Tìm theo tên tài khoản:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addGap(186, 186, 186))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, 0, 349, Short.MAX_VALUE)
                    .addComponent(jTextFieldTimTK, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtnvFormMK, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                    .addComponent(txtnvFormTK)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(49, 49, 49)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSuaTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonXoaTK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonThemTK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonTimTK, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addGap(489, 489, 489))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTimTK, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTimTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonThemTK, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSuaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonXoaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtnvFormTK, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtnvFormMK, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("TÀI KHOẢN", jPanel5);

        jTabbedPane1.addTab("NHÂN VIÊN       ", new javax.swing.ImageIcon(getClass().getResource("/images/nhanvien.png")), jTabbedPane2); // NOI18N

        jTabbedPane4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jTablencc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTablencc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại"
            }
        ));
        jTablencc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablenccMouseClicked(evt);
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

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Tìm theo tên:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addGap(186, 186, 186))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldsdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addComponent(jTextFielddc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldtenncc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldmancc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldtim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonsuancc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonxoancc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonthemncc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtontimncc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(489, 489, 489))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtontimncc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldtim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextFieldtenncc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTextFieldmancc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFielddc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jTextFieldsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("THÔNG TIN NHÀ CUNG CẤP", jPanel2);

        jTabbedPane1.addTab("NHÀ CUNG CẤP", new javax.swing.ImageIcon(getClass().getResource("/images/doitac.png")), jTabbedPane4); // NOI18N

        jTabbedPane5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jTablesp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTablesp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Tên tác giả", "Nhà xuất bản", "Năm xuất bản", "Thể loại", "Giá"
            }
        ));
        jScrollPane5.setViewportView(jTablesp);

        jButtontimsp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtontimsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-google-web-search-30.png"))); // NOI18N
        jButtontimsp.setText("TÌM KIẾM");
        jButtontimsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtontimspActionPerformed(evt);
            }
        });

        jTextFieldtimsp.setPreferredSize(new java.awt.Dimension(7, 28));
        jTextFieldtimsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldtimspActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Tìm kiếm theo tên sách");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane5)
                .addGap(186, 186, 186))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldtimsp, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addComponent(jButtontimsp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(520, 520, 520))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldtimsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtontimsp, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(198, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("THÔNG TIN SÁCH", jPanel3);

        jTabbedPane1.addTab("SẢN PHẨM        ", new javax.swing.ImageIcon(getClass().getResource("/images/sanpham.png")), jTabbedPane5); // NOI18N

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

        jButtontimthongke.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButtontimthongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-google-web-search-30.png"))); // NOI18N
        jButtontimthongke.setText("TÌM KIẾM");
        jButtontimthongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtontimthongkeActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("TỔNG DOANH THU:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane8)
                .addGap(243, 243, 243))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jButtontimthongke, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addGap(742, 742, 742))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtontimthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jTabbedPane7.addTab("DOANH THU", jPanel7);

        jTabbedPane1.addTab("THỐNG KÊ       ", new javax.swing.ImageIcon(getClass().getResource("/images/doanhthu.png")), jTabbedPane7); // NOI18N

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonXoaTK1)
                .addGap(188, 188, 188))
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButtonXoaTK1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButtontimthongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtontimthongkeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtontimthongkeActionPerformed

    private void jTextFieldtimspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldtimspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldtimspActionPerformed

    private void jButtontimspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtontimspActionPerformed
        // TODO add your handling code here:
        String timsach = jTextFieldtimsp.getText();
        ArrayList<SanPhamDTO> lists = new ArrayList<SanPhamDTO>();
        if (!timsach.isEmpty()) {
            lists = spBUS.find(timsach);
        }
        spBUS.uploadTable(jTablesp, lists);
        jTablesp.removeColumn(jTablesp.getColumnModel().getColumn(0));

    }//GEN-LAST:event_jButtontimspActionPerformed

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
        ArrayList<NhaCungCapDTO> list = new ArrayList<>();
        if (!tim.isEmpty()) {
            list = nccBUS.find(tim);
        }
        nccBUS.uploadTable(jTablencc, list);
        jTablencc.removeColumn(jTablencc.getColumnModel().getColumn(0));
    }//GEN-LAST:event_jButtontimnccActionPerformed

    private void jButtonthemnccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonthemnccActionPerformed
        // TODO add your handling code here:
        if (jTextFieldtenncc.getText().isEmpty()) {
            ThongBao.error("Tên không để trống");
        } else if (jTextFielddc.getText().isEmpty()) {
            ThongBao.error("Địa chỉ không để trống");
        } else if (!RegexExpression.checkPhoneNumber(jTextFieldsdt.getText())) {
            ThongBao.error("Số đt không hợp lệ");
        } else {
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
                eBUS.updatenhacungcap();
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
        // TODO add your handling code here:
        int mancc = Integer.parseInt(jTextFieldmancc.getText());
        nccBUS.delete(mancc);
        NhaCungCapBUS nvBUS = new NhaCungCapBUS();
        try {
            AutomaticallyClosedMsgBox(1500, "Đang xóa.\nXin hãy đợi....");
            ThongBao.noitice("Xóa thành công");
            nvBUS.updateTable(jTablencc);
            jTablencc.removeColumn(jTablencc.getColumnModel().getColumn(0));
            eBUS.updatenhacungcap();
            jTextFieldmancc.setText(null);
            jTextFieldtenncc.setText(null);
            jTextFielddc.setText(null);
            jTextFieldsdt.setText(null);
        } catch (Exception ex) {
            Logger.getLogger(EMPLOYEE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonxoanccActionPerformed

    private void txtnvFormMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnvFormMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnvFormMKActionPerformed

    private void txtnvFormTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnvFormTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnvFormTKActionPerformed

    private void jTextFieldTimTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTimTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTimTKActionPerformed

    private void jButtonTimTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimTKActionPerformed

        /*
        String hoten = jTextFieldTim.getText();
        BUS.NhanVienBUS nvBUS = new BUS.NhanVienBUS();
        ArrayList<NhanVienDTO> list = new ArrayList<>();
        if (!hoten.isEmpty()) {
            list = nvBUS.find(hoten);
        }
        uploadTableNV(tblnvData, list);
         */
        String tentk = jTextFieldTimTK.getText();
        BUS.TaiKhoanBUS tkBUS = new TaiKhoanBUS();
        ArrayList<TaiKhoanDTO> list = new ArrayList<>();
        if (!tentk.isEmpty()) {
            list = tkBUS.find(tentk);
        }
        uploadTableTK(tbltkData, list);
    }//GEN-LAST:event_jButtonTimTKActionPerformed

    private void jButtonThemTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemTKActionPerformed
        BUS.TaiKhoanBUS tkBUS = new TaiKhoanBUS();
        tkBUS.add(txtnvFormTK, txtnvFormMK, jComboBox2, jComboBox1);
        try {
            AutomaticallyClosedMsgBox(1500, "Đang thêm.\nXin hãy đợi....");
            ThongBao.noitice("Thêm thành công");
            updateTableTK(tbltkData);
        } catch (Exception ex) {
            Logger.getLogger(ADMIN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonThemTKActionPerformed

    private void jButtonXoaTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaTKActionPerformed

        String manvC = (String) jComboBox1.getSelectedItem();
        int manv = 0;
        if (!manvC.isEmpty()) {
            //manv = Integer.valueOf(formMaNV.getText());
            String[] list = manvC.split("-");
            String manv1 = list[0];
            manv = Integer.parseInt(manv1);
        }
        BUS.TaiKhoanBUS tkBUS = new TaiKhoanBUS();
        tkBUS.delete(manv);
        try {
            AutomaticallyClosedMsgBox(1500, "Đang xóa.\nXin hãy đợi....");
            ThongBao.noitice("Xóa thành công");
            updateTableTK(tbltkData);
        } catch (Exception ex) {
            Logger.getLogger(ADMIN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonXoaTKActionPerformed

    private void txtnvFormDcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnvFormDcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnvFormDcActionPerformed

    private void txtnvFormSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnvFormSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnvFormSdtActionPerformed

    private void txtnvFormHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnvFormHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnvFormHoTenActionPerformed

    private void txtnvFormMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnvFormMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnvFormMaNVActionPerformed

    private void jTextFieldTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTimActionPerformed

    private void jButtonTimNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimNVActionPerformed
        String hoten = jTextFieldTim.getText();
        BUS.NhanVienBUS nvBUS = new BUS.NhanVienBUS();
        ArrayList<NhanVienDTO> list = new ArrayList<>();
        if (!hoten.isEmpty()) {
            list = nvBUS.find(hoten);
        }
        uploadTableNV(tblnvData, list);
    }//GEN-LAST:event_jButtonTimNVActionPerformed

    private void jButtonThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemNVActionPerformed
        if (txtnvFormHoTen.getText().isEmpty()) {
            ThongBao.error("Họ ten không hợp lệ");
        } else if (!RegexExpression.checkPhoneNumber(txtnvFormSdt.getText())) {
            ThongBao.error("Số đt không hợp lệ");
        } else if (txtnvFormDc.getText().isEmpty()) {
            ThongBao.error("Địa chỉ không hợp lệ");
        } else {
            String hoten = txtnvFormHoTen.getText();
            String sdt = txtnvFormSdt.getText();
            String dc = txtnvFormDc.getText();

            BUS.NhanVienBUS1 nvBUS = new BUS.NhanVienBUS1();
            nvBUS.add(hoten, sdt, dc);
            int ma = nvBUS.ma();
            try {
                AutomaticallyClosedMsgBox(1500, "Đang thêm.\nXin hãy đợi....");
                ThongBao.noitice("Thêm thành công");
                jComboBox1.addItem(ma + "-" + hoten);
                updateTableNV(tblnvData);
                txtnvFormHoTen.setText(null);
                txtnvFormSdt.setText(null);
                txtnvFormDc.setText(null);
            } catch (Exception ex) {
                Logger.getLogger(ADMIN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonThemNVActionPerformed

    private void jButtonXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaNVActionPerformed
        BUS.NhanVienBUS nvBUS = new BUS.NhanVienBUS();
        int manv = Integer.parseInt(txtnvFormMaNV.getText());
        String hoten = txtnvFormHoTen.getText();
        nvBUS.delete(manv);

        try {
            AutomaticallyClosedMsgBox(1500, "Đang xóa.\nXin hãy đợi....");
            ThongBao.noitice("Xóa thành công");
            jComboBox1.removeItem(manv + "-" + hoten);
            updateTableNV(tblnvData);
            txtnvFormMaNV.setText(null);
            txtnvFormHoTen.setText(null);
            txtnvFormSdt.setText(null);
            txtnvFormDc.setText(null);
        } catch (Exception ex) {
            Logger.getLogger(ADMIN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonXoaNVActionPerformed

    private void jButtonSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaNVActionPerformed
        
        if (txtnvFormHoTen.getText().isEmpty()) {
            ThongBao.error("Họ ten không hợp lệ");
        } else if (!RegexExpression.checkPhoneNumber(txtnvFormSdt.getText())) {
            ThongBao.error("Số đt không hợp lệ");
        } else if (txtnvFormDc.getText().isEmpty()) {
            ThongBao.error("Địa chỉ không hợp lệ");
        } else {
            int manv = Integer.valueOf(txtnvFormMaNV.getText());
            String hoten = txtnvFormHoTen.getText();
            String sdt = txtnvFormSdt.getText();
            String dc = txtnvFormDc.getText();

            BUS.NhanVienBUS nvBUS = new BUS.NhanVienBUS();
            nvBUS.edit(manv, hoten, sdt, dc);
            try {
                AutomaticallyClosedMsgBox(1500, "Đang sửa.\nXin hãy đợi....");
                ThongBao.noitice("Sửa thành công");
                jComboBox1.addItem(manv + "-" + hoten);
                updateTableNV(tblnvData);
                txtnvFormMaNV.setText(null);
                txtnvFormHoTen.setText(null);
                txtnvFormSdt.setText(null);
                txtnvFormDc.setText(null);
            } catch (Exception ex) {
                Logger.getLogger(ADMIN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonSuaNVActionPerformed

    private void tblnvDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnvDataMouseClicked
        loadInfoNV(tblnvData, txtnvFormMaNV, txtnvFormHoTen, txtnvFormSdt, txtnvFormDc);
    }//GEN-LAST:event_tblnvDataMouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        txtnvFormMaNV.setText(null);
        txtnvFormHoTen.setText(null);
        txtnvFormSdt.setText(null);
        txtnvFormDc.setText(null);
        jTextFieldTim.setText(null);

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void tbltkDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltkDataMouseClicked
        //loadInfoTK(tbltkData, txtnvFormTK, txtnvFormMK, jComboBox2, jComboBox1);
        //loadInfoTK(tbltkData, txtnvFormTK, txtnvFormMK);
        //TaiKhoanDTO tk = TaiKhoanDAO.getTaiKhoan((String) tbltkData.getValueAt(tbltkData.getSelectedRow(), 0));
        jComboBox1.setSelectedItem(tbltkData.getValueAt(tbltkData.getSelectedRow(), 0).toString());
        txtnvFormTK.setText(tbltkData.getValueAt(tbltkData.getSelectedRow(), 1).toString());
        txtnvFormMK.setText(tbltkData.getValueAt(tbltkData.getSelectedRow(), 2).toString());
        jComboBox2.setSelectedItem(tbltkData.getValueAt(tbltkData.getSelectedRow(), 3).toString());
    }//GEN-LAST:event_tbltkDataMouseClicked

    private void jButtonSuaTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaTKActionPerformed
        //TaiKhoanBUS.validateForm(txtnvFormTK, txtnvFormMK, txtnvFormMaNV);
        String tentk = txtnvFormTK.getText();
        String matkhau = txtnvFormMK.getText();
        String quyenchu = (String) jComboBox2.getSelectedItem();
        int quyen;
        if (quyenchu.equalsIgnoreCase("Admin")) {
            quyen = 1;
        } else {
            quyen = 0;
        }
        String manvC = (String) jComboBox1.getSelectedItem();
        int manv = 0;
        if (!manvC.isEmpty()) {
            //manv = Integer.valueOf(formMaNV.getText());
            String[] list = manvC.split("-");
            String manv1 = list[0];
            manv = Integer.parseInt(manv1);
        }
        if (TaiKhoanBUS.validateForm(tentk, matkhau, manv)) {
            BUS.TaiKhoanBUS tkBUS = new TaiKhoanBUS();
            tkBUS.edit(manv, tentk, matkhau, quyen);
            try {
                AutomaticallyClosedMsgBox(1500, "Đang sửa.\nXin hãy đợi....");
                ThongBao.noitice("Sửa thành công");
                updateTableTK(tbltkData);
            } catch (Exception ex) {
                Logger.getLogger(ADMIN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonSuaTKActionPerformed

    private void jTablenccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablenccMouseClicked
        // TODO add your handling code here:
        jTextFieldmancc.setText((jTablencc.getModel().getValueAt(jTablencc.getSelectedRow(), 0)).toString());
        jTextFieldtenncc.setText((jTablencc.getModel().getValueAt(jTablencc.getSelectedRow(), 1)).toString());
        jTextFielddc.setText((jTablencc.getModel().getValueAt(jTablencc.getSelectedRow(), 2)).toString());
        jTextFieldsdt.setText((jTablencc.getModel().getValueAt(jTablencc.getSelectedRow(), 3)).toString());
    }//GEN-LAST:event_jTablenccMouseClicked

    private void jButtonsuanccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsuanccActionPerformed
        // TODO add your handling code here:
        if (jTextFieldtenncc.getText().isEmpty()) {
            ThongBao.error("Tên không để trống");
        } else if (jTextFielddc.getText().isEmpty()) {
            ThongBao.error("Địa chỉ không để trống");
        } else if (!RegexExpression.checkPhoneNumber(jTextFieldsdt.getText())) {
            ThongBao.error("Số đt không hợp lệ");
        } else {
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
                eBUS.updatenhacungcap();
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

    private void jButtonXoaTK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaTK1ActionPerformed
        LOGIN lg = new LOGIN();
        lg.setLocationRelativeTo(null);
        lg.setVisible(true);
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonXoaTK1ActionPerformed

    /**
     * @param args the command line arguments
     */
    /*    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
 /*      try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
 /*       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ADMIN().setVisible(true);
            }
        });
        }
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSuaNV;
    private javax.swing.JButton jButtonSuaTK;
    private javax.swing.JButton jButtonThemNV;
    private javax.swing.JButton jButtonThemTK;
    private javax.swing.JButton jButtonTimNV;
    private javax.swing.JButton jButtonTimTK;
    private javax.swing.JButton jButtonXoaNV;
    private javax.swing.JButton jButtonXoaTK;
    private javax.swing.JButton jButtonXoaTK1;
    private javax.swing.JButton jButtonsuancc;
    private javax.swing.JButton jButtonthemncc;
    private javax.swing.JButton jButtontimncc;
    private javax.swing.JButton jButtontimsp;
    private javax.swing.JButton jButtontimthongke;
    private javax.swing.JButton jButtonxoancc;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTablencc;
    private javax.swing.JTable jTablesp;
    private javax.swing.JTextField jTextFieldTim;
    private javax.swing.JTextField jTextFieldTimTK;
    private javax.swing.JTextField jTextFielddc;
    private javax.swing.JTextField jTextFieldmancc;
    private javax.swing.JTextField jTextFieldsdt;
    private javax.swing.JTextField jTextFieldtenncc;
    private javax.swing.JTextField jTextFieldtim;
    private javax.swing.JTextField jTextFieldtimsp;
    private javax.swing.JTable tblnvData;
    private javax.swing.JTable tbltkData;
    private javax.swing.JTextField txtnvFormDc;
    private javax.swing.JTextField txtnvFormHoTen;
    private javax.swing.JTextField txtnvFormMK;
    private javax.swing.JTextField txtnvFormMaNV;
    private javax.swing.JTextField txtnvFormSdt;
    private javax.swing.JTextField txtnvFormTK;
    // End of variables declaration//GEN-END:variables
}
