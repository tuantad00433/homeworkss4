/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import com.sun.javafx.css.StyleManager;
import console.entity.Student;
import console.model.StudentModel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ProgressBar;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author ADMIN-PC
 */
public class SwingApp {

    private JLabel lblId, lblName, lblEmail, lblPhone, lblClass, lblRollNum, lblDob, lblTotalMsg, lblNameMsg, lblMailMsg, lblClock;
    private JButton button, submitBtn, button2, p3Button;
    private JTextField txtId, txtPhone;
    private JSpinner txtDob;
    private JTextField txtName, txtEmail, txtClass, txtRollNum, p3txtField;
    private JTextArea p3TxtArea;
    private JPanel pane1, pane2, pane, pane3;
    private JScrollPane scrList;
    private JTable tblList;
    private JMenuBar mnBar;
    private JMenu mnManager, mnClock, mnAbout, mnLang;
    private JMenuItem mnClock2, mnManager1, mnLang1, mnLang2, mnLang3;
    private JCheckBoxMenuItem mnClock1;
    private JProgressBar pBar;
    private JFrame fr;
    private int barValue = 0;
    private JDialog box;
    private Timer clock;
    private Locale myLocale;
    private ResourceBundle langResource;

    public SwingApp() {
        //chinh Frame
        fr = new JFrame();

        this.fr.setSize(600, 600);
        this.fr.setLocationRelativeTo(null);
        this.fr.setTitle("==================Đang Chờ Đồng Hồ Khởi Động...==================");
        pane1 = new JPanel(null);
        pane2 = new JPanel(null);
        pane = new JPanel(new CardLayout());
//        Kết nối resource
        myLocale = new Locale("vi", "VN");
        langResource = ResourceBundle.getBundle("resource/language", myLocale);
//      Khoi tao cac component
        lblId = new JLabel("ID:");
        lblName = new JLabel("Name:");
        lblEmail = new JLabel("Email:");
        lblPhone = new JLabel("Phone:");
        lblDob = new JLabel("Date of Birth:");
        lblClass = new JLabel("Class:");
        lblRollNum = new JLabel("RollNum:");
        lblClock = new JLabel("CLOCK WAIT ");
        button = new JButton("XEM DANH SÁCH");
        button2 = new JButton("QUAY LẠI");
        submitBtn = new JButton("NHẬP DỮ LIỆU");
        lblTotalMsg = new JLabel();
        lblMailMsg = new JLabel();
        lblNameMsg = new JLabel();
        txtClass = new JTextField();
        txtRollNum = new JTextField();
        txtId = new JTextField();
        txtPhone = new JTextField();
        txtName = new JTextField();
        txtEmail = new JTextField();

        SpinnerDateModel spinModel = new SpinnerDateModel();
        txtDob = new JSpinner(spinModel);
        txtDob.setEditor(new JSpinner.DateEditor(txtDob, "dd/MM/yyyy"));

//      Thêm vào pane2
        Vector<String> clName = new Vector<>();
        Vector<Vector> vectorData = new Vector<>();

//        Dùng Reflection gọi tên các field làm tên cột trong bảng.
        Field[] fields = new Student().getClass().getDeclaredFields();
        for (Field field : fields) {
            clName.addElement(field.getName());
        }
//        Sử dụng vector khởi tạo JTable.
        ArrayList<Student> rowData;
        StudentModel studentModel = new StudentModel();
        rowData = studentModel.getList();
        for (Student student : studentModel.getList()) {
            Vector<String> data = new Vector<>();
            data.addElement(String.valueOf(student.getId()));
            data.addElement(student.getName());
            data.addElement(student.getEmail());
            vectorData.addElement(data);
        }
        tblList = new JTable(vectorData, clName);

        tblList.setRowHeight(30);
//        Chỉnh lề,font cho text trong cột (Alignment).
        DefaultTableCellRenderer renders = new DefaultTableCellRenderer();
        renders.setHorizontalAlignment(SwingConstants.CENTER);
        tblList.getColumn("id").setCellRenderer(renders);
        tblList.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        scrList = new JScrollPane(tblList);
        scrList.setBounds(100, 50, 400, 300);
        pane2.add(scrList);

        this.button2.addActionListener(new switchToPane1());
        button2.setBounds(100, 350, 150, 50);
        pane2.add(button2);

//      Them component vao pane1
        lblId.setBounds(50, 50, 100, 50);
        this.pane1.add(this.lblId);

        txtId.setBounds(150, 50, 200, 50);
        this.pane1.add(this.txtId);

        lblName.setBounds(50, 100, 100, 50);
        this.pane1.add(this.lblName);

        txtName.setBounds(150, 100, 200, 50);
        this.pane1.add(this.txtName);

        lblNameMsg.setBounds(355, 100, 230, 50);
        this.pane1.add(this.lblNameMsg);

        lblEmail.setBounds(50, 150, 100, 50);
        this.pane1.add(this.lblEmail);

        txtEmail.setBounds(150, 150, 200, 50);
        this.pane1.add(this.txtEmail);

        lblMailMsg.setBounds(355, 150, 230, 50);
        this.pane1.add(this.lblMailMsg);

        lblPhone.setBounds(50, 200, 100, 50);
        this.pane1.add(this.lblPhone);

        txtPhone.setBounds(150, 200, 200, 50);
        this.pane1.add(this.txtPhone);

        lblClass.setBounds(50, 250, 100, 50);
        this.pane1.add(this.lblClass);

        txtClass.setBounds(150, 250, 200, 50);
        this.pane1.add(this.txtClass);

        lblRollNum.setBounds(50, 300, 100, 50);
        this.pane1.add(this.lblRollNum);

        txtRollNum.setBounds(150, 300, 200, 50);
        this.pane1.add(this.txtRollNum);

        lblDob.setBounds(50, 350, 100, 50);
        this.pane1.add(this.lblDob);

        txtDob.setBounds(150, 350, 200, 50);
        this.pane1.add(this.txtDob);

        button.setBounds(50, 400, 150, 50);
        this.button.addActionListener(new switchToPane2());
        this.pane1.add(this.button);

        submitBtn.setBounds(200, 400, 150, 50);
        this.submitBtn.addActionListener(new LoginHandle());
        this.submitBtn.addActionListener(new JLog());
        this.pane1.add(this.submitBtn);

        lblTotalMsg.setBounds(150, 25, 200, 25);
        pane.setSize(600, 600);
        this.pane1.add(this.lblTotalMsg);
        this.pane.add(pane1, "pane1");
        this.pane.add(pane2, "pane2");

//        Tạo Pane 3.
        pane3 = new JPanel(null);
        p3Button = new JButton(langResource.getString("pane3Button"));
        p3TxtArea = new JTextArea();
        p3txtField = new JTextField();
        p3TxtArea.setBounds(25, 25, 500, 420);
        pane3.add(p3TxtArea);
        p3txtField.setBounds(25, 480, 300, 50);
        pane3.add(p3txtField);
        p3Button.setBounds(330, 480, 200, 50);
        pane3.add(p3Button);
        pane.add(pane3, "pane3");
        this.fr.add(pane);
//        Tạo JMenuBar
        mnBar = new JMenuBar();
        mnClock = new JMenu(langResource.getString("mnClock"));
        mnManager = new JMenu(langResource.getString("mnManager"));
        mnAbout = new JMenu(langResource.getString("mnAbout"));
        mnClock1 = new JCheckBoxMenuItem(langResource.getString("mnClockStart"));
        mnClock1.addItemListener(new StartClock());
        mnClock2 = new JMenuItem(langResource.getString("mnClockInfo"));
        mnManager1 = new JMenuItem(langResource.getString("mnManagerConnect"));
        mnManager1.addActionListener(new switchToPane3());
        mnLang = new JMenu(langResource.getString("mnLang"));
        mnLang1 = new JMenuItem(langResource.getString("mnLangVN"));
        mnLang2 = new JMenuItem(langResource.getString("mnLangEN"));
        mnLang3 = new JMenuItem(langResource.getString("mnLangJP"));
        mnLang2.addActionListener(new changeLangUS());
        mnLang1.addActionListener(new changeLangVN());
        mnLang.add(mnLang1);
        mnLang.add(mnLang2);
        mnLang.add(mnLang3);
        mnManager.add(mnManager1);
        mnManager.add(mnLang);
        mnClock.add(mnClock1);
        mnClock.add(mnClock2);
        mnBar.add(mnManager);
        mnBar.add(mnClock);
        mnBar.add(mnAbout);
        mnBar.add(Box.createHorizontalGlue());
        mnBar.add(lblClock);
        fr.setJMenuBar(mnBar);

        //Xu ly khi tat
        this.fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ProgressBar
        pBar = new JProgressBar(JProgressBar.HORIZONTAL, 50, 100);
    }

    private void resetMsg() {
        lblNameMsg.setText("");
        lblMailMsg.setText("");
        lblTotalMsg.setText("");
    }

    private void showMsg(HashMap<String, String> errors) {
        lblTotalMsg.setForeground(Color.red);
        lblTotalMsg.setText("Vui lòng sửa các lỗi bên dưới: ");
        if (errors.containsKey("txtName")) {
            lblNameMsg.setForeground(Color.red);
            lblNameMsg.setText(errors.get("txtName"));

        } else {
            lblNameMsg.setForeground(Color.GREEN);
            lblNameMsg.setText("\u2713" + "Tên chính xác");
        }
        if (errors.containsKey("txtMail")) {
            lblMailMsg.setForeground(Color.red);
            lblMailMsg.setText(errors.get("txtMail"));
        } else {
            lblMailMsg.setForeground(Color.green);
            lblMailMsg.setText("\u2713" + "Email đúng định dạng");
        }
    }
//       ActionListener Chuyển đổi ngôn ngữ
//      Tiếng Anh

    class changeLangUS implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            langResource = ResourceBundle.getBundle("resource/language", myLocale = new Locale("en", "US"));
            mnClock.setText(langResource.getString("mnClock"));
            mnManager.setText(langResource.getString("mnManager"));
            mnAbout.setText(langResource.getString("mnAbout"));
            mnClock1.setText(langResource.getString("mnClockStart"));

            mnClock2.setText(langResource.getString("mnClockInfo"));
            mnManager1.setText(langResource.getString("mnManagerConnect"));

            mnLang.setText(langResource.getString("mnLang"));
            mnLang1.setText(langResource.getString("mnLangVN"));
            mnLang2.setText(langResource.getString("mnLangEN"));
            mnLang3.setText(langResource.getString("mnLangJP"));
            p3Button.setText(langResource.getString("pane3Button"));
        }

    }
//Đổi ngôn ngữ VN

    class changeLangVN implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            langResource = ResourceBundle.getBundle("resource/language", new Locale("vi", "VN"));
            mnClock.setText(langResource.getString("mnClock"));
            mnManager.setText(langResource.getString("mnManager"));
            mnAbout.setText(langResource.getString("mnAbout"));
            mnClock1.setText(langResource.getString("mnClockStart"));
            mnClock2.setText(langResource.getString("mnClockInfo"));
            mnManager1.setText(langResource.getString("mnManagerConnect"));
            mnLang.setText(langResource.getString("mnLang"));
            mnLang1.setText(langResource.getString("mnLangVN"));
            mnLang2.setText(langResource.getString("mnLangEN"));
            mnLang3.setText(langResource.getString("mnLangJP"));
            p3Button.setText(langResource.getString("pane3Button"));
        }

    }

    class LoginHandle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = txtName.getText();
            String mail = txtEmail.getText();

            HashMap<String, String> errors = new FormHandle().validateLogin(name, mail);
            if (errors.size() == 0) {
                resetMsg();
                Student student = new Student();
                StudentModel studentModel = new StudentModel();
                student.setName(name);
                student.setEmail(mail);
                try {
                    studentModel.insert(student);
                    JOptionPane.showMessageDialog(pane, "Thông tin đã được lưu vào database");
                } catch (Exception exc) {
                    System.out.println(exc);
                }
            } else {
                showMsg(errors);
            }

        }
    }

    class ResetHandle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            txtName.setText("");
            txtEmail.setText("");
        }

    }
//Xu Ly thanh progressBar

    class JLog implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            box = new JDialog(fr);
            box.setLocationRelativeTo(null);
            box.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            box.setSize(400, 150);
            box.setLocation(450, 300);

            box.setLayout(new FlowLayout(FlowLayout.CENTER));
            box.add(pBar);
            box.setVisible(true);

            Timer t = new Timer(500, new startBar());
            t.start();

        }

    }

//Xu Ly Dong Ho: 
    private class TimeRun implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            lblClock.setText(sdf.format(date));
        }

    }

    class StartClock implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (mnClock1.isSelected()) {
                clock = new Timer(200, new TimeRun());
                clock.start();
                fr.setTitle("ĐỒNG HỒ ĐANG CHẠY");
            } else {
                clock.stop();
                lblClock.setText("CLOCK WAIT ");
                fr.setTitle("ĐANG CHỜ ĐỒNG HỒ KHỞI CHẠY");

            }
        }

    }

//Xu ly ProgressBar
    class startBar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            barValue += 20;
            pBar.setValue(barValue);
            pBar.setStringPainted(true);
            if (barValue == 100) {
                barValue = 0;
                box.setVisible(false);

            }
        }

    }

    //Nut chuyen doi Pane trong CardLayout
    class switchToPane2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) pane.getLayout();
            cl.show(pane, "pane2");
        }

    }

    class switchToPane1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) pane.getLayout();
            cl.show(pane, "pane1");
        }

    }

    class switchToPane3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) pane.getLayout();
            cl.show(pane, "pane3");
        }

    }

    public void createUI(boolean a) {
        fr.setVisible(a);
    }
}
