package TexProject.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class AllGui {

    private static JPanel singlePanel;
    private static JPanel divorcePanel;
    private static JPanel marriedHaveMoneyPanel;
    private static JPanel marriedDontHaveMoneyPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1133, 744);
        frame.setTitle("โปรแกรมคำนวณภาษีหนึ่งเดียวที่คุณไว้ใจ");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("project.jpg"));
        frame.getContentPane().setBackground(Color.decode("#D8F5FF"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1133, 744);
        layeredPane.setBackground(Color.decode("#D8F5FF"));

        DisplayGraphics m = new DisplayGraphics();
        m.setBounds(0, 0, 1133, 744);
        layeredPane.add(m, JLayeredPane.DEFAULT_LAYER);

        // รายการลดหย่อนภาษี : ครอบครัว
        JLabel family = new JLabel("รายการลดหย่อนภาษี : ครอบครัว");
        family.setFont(new Font("Tahoma", Font.BOLD, 32));
        family.setBounds(160, 15, 1000, 50);
        layeredPane.add(family, JLayeredPane.PALETTE_LAYER);

        // สถานะสมรส
        JLabel status = new JLabel("สถานะสมรส");
        status.setFont(new Font("Tahoma", Font.BOLD, 24));
        status.setBounds(75, 100, 1000, 50);
        layeredPane.add(status, JLayeredPane.PALETTE_LAYER);

        // ลดหย่อนส่วนบุคคล
        JLabel own = new JLabel("ลดหย่อนส่วนบุคคล");
        own.setFont(new Font("Tahoma", Font.BOLD, 24));
        own.setBounds(540, 100, 1000, 50);
        layeredPane.add(own, JLayeredPane.PALETTE_LAYER);

        // ล็อก ลดหย่อน 60000
        JLabel discount = new JLabel("  60,000");
        discount.setFont(new Font("Tahoma", Font.PLAIN, 24));
        discount.setBounds(540, 155, 300, 45);
        discount.setBackground(Color.LIGHT_GRAY);
        Border border = BorderFactory.createLineBorder(Color.GRAY, 3);
        discount.setBorder(border);
        discount.setOpaque(true);
        layeredPane.add(discount, JLayeredPane.PALETTE_LAYER);

        // เลือกสถานะ
        String[] s1 = { "โสด", "หย่า", "คู่สมรสมีเงินได้", "คู่สมรสไม่มีเงินได้" };
        JComboBox<String> chosestatus = new JComboBox<>(s1);
        chosestatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
        chosestatus.setBounds(75, 155, 300, 45);
        chosestatus.setBackground(Color.WHITE);
        chosestatus.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        chosestatus.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(new EmptyBorder(0, 10, 0, 0));
                if (index == -1 && value == null) {
                    setText("กรุณาเลือกสถานะ");
                    setForeground(Color.GRAY);
                } else {
                    setForeground(Color.BLACK);
                }
                return this;
            }
        });

        chosestatus.setSelectedIndex(-1);

        // Create singlePanel
        singlePanel = createSinglePanel();
        singlePanel.setVisible(false);
        layeredPane.add(singlePanel, JLayeredPane.PALETTE_LAYER);

        // Create DivorcePanel
        divorcePanel = createDivorcePanel();
        divorcePanel.setVisible(false);
        layeredPane.add(divorcePanel, JLayeredPane.PALETTE_LAYER);

        // Create MarriedHaveMoneyPanel
        marriedHaveMoneyPanel = createMarriedHaveMoneyPanel();
        marriedHaveMoneyPanel.setVisible(false);
        layeredPane.add(marriedHaveMoneyPanel, JLayeredPane.PALETTE_LAYER);

        // Create MarriedDontHaveMoneyPanel
        marriedDontHaveMoneyPanel = createMarriedDontHaveMoneyPanel();
        marriedDontHaveMoneyPanel.setVisible(false);
        layeredPane.add(marriedDontHaveMoneyPanel, JLayeredPane.PALETTE_LAYER);

        // Add ActionListener to chosestatus
        chosestatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStatus = (String) chosestatus.getSelectedItem();
                singlePanel.setVisible("โสด".equals(selectedStatus));
                divorcePanel.setVisible("หย่า".equals(selectedStatus));
                marriedHaveMoneyPanel.setVisible("คู่สมรสมีเงินได้".equals(selectedStatus));
                marriedDontHaveMoneyPanel.setVisible("คู่สมรสไม่มีเงินได้".equals(selectedStatus));
            }
        });

        layeredPane.add(chosestatus, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Home
        JButton button = new JButton("HOME");
        button.setBackground(Color.decode("#80C8CD"));
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setBounds(20, 20, 120, 50);
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);
        button.setBorder(border1);
        button.addActionListener(e -> System.out.println("HOME button clicked"));
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Back
        JButton button1 = new JButton("BACK");
        button1.setBackground(Color.decode("#E5B6B3"));
        button1.setFont(new Font("Tahoma", Font.BOLD, 20));
        button1.setForeground(Color.WHITE);
        button1.setBounds(427, 622, 120, 50);
        button1.setBorder(border1);
        button1.addActionListener(e -> System.out.println("BACK button clicked"));
        layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม NEXT
        JButton button2 = new JButton("NEXT");
        button2.setBackground(Color.decode("#A5CBB0"));
        button2.setFont(new Font("Tahoma", Font.BOLD, 20));
        button2.setForeground(Color.WHITE);
        button2.setBounds(587, 622, 120, 50);
        button2.setBorder(border1);
        button2.addActionListener(e -> System.out.println("NEXT button clicked"));
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);

        frame.add(layeredPane);
        frame.setVisible(true);
    }

    private static JPanel createSinglePanel() {
        JPanel singlePanel = new JPanel();
        singlePanel.setLayout(null);
        singlePanel.setBounds(0, 220, 1133, 400);
        singlePanel.setOpaque(false);

        // ลดหย่อนบิดา-มารดา (ตนเอง)
        JLabel single_fathmoth = new JLabel("ลดหย่อนบิดา-มารดา (ตนเอง)");
        single_fathmoth.setFont(new Font("Tahoma", Font.BOLD, 24));
        single_fathmoth.setBounds(75, 0, 500, 45);
        singlePanel.add(single_fathmoth);

        // ปุ่มกลมพ่อแม่
        JRadioButton single_father1 = new JRadioButton(" บิดา");
        single_father1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        single_father1.setBounds(100, 30, 150, 50);
        single_father1.setOpaque(false);
        singlePanel.add(single_father1);

        JRadioButton single_mother1 = new JRadioButton(" มารดา");
        single_mother1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        single_mother1.setBounds(300, 30, 200, 50);
        single_mother1.setOpaque(false);
        singlePanel.add(single_mother1);

        // คนละ 30,000 บาท (บิดามารดาต้องมีอายุเกิน 60 ปี และมีเงินได้ไม่เกิน 30,000
        // บาทต่อปี)
        JLabel single_condition1 = new JLabel(
                "คนละ 30,000 บาท (บิดามารดาต้องมีอายุเกิน 60 ปี และมีเงินได้ไม่เกิน 30,000 บาทต่อปี)");
        single_condition1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        single_condition1.setBounds(75, 60, 1000, 45);
        singlePanel.add(single_condition1);

        // (ได้ทั้งบิดา มารดาของตนเอง และคู่สมรส)
        JLabel single_condition2 = new JLabel("(ได้ทั้งบิดา มารดาของตนเอง และคู่สมรส)");
        single_condition2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        single_condition2.setBounds(75, 75, 1000, 45);
        singlePanel.add(single_condition2);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)
        JLabel single_disabled_no_money = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)");
        single_disabled_no_money.setFont(new Font("Tahoma", Font.BOLD, 24));
        single_disabled_no_money.setBounds(75, 115, 1000, 45);
        singlePanel.add(single_disabled_no_money);

        // ปุ่มกลมพ่อแม่ญาติ
        JRadioButton single_father2 = new JRadioButton(" บิดา");
        single_father2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        single_father2.setBounds(100, 155, 150, 50);
        single_father2.setOpaque(false);
        singlePanel.add(single_father2);

        JRadioButton single_mother2 = new JRadioButton(" มารดา");
        single_mother2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        single_mother2.setBounds(300, 155, 200, 50);
        single_mother2.setOpaque(false);
        singlePanel.add(single_mother2);

        // กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง
        JLabel single_condition3 = new JLabel("กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง");
        single_condition3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        single_condition3.setBounds(75, 195, 1000, 45);
        singlePanel.add(single_condition3);

        // หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท
        // (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)
        JLabel single_condition4 = new JLabel(
                "หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)");
        single_condition4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        single_condition4.setBounds(75, 210, 1000, 45);
        singlePanel.add(single_condition4);

        return singlePanel;
    }

    private static JPanel createDivorcePanel() {
        JPanel divorcePanel = new JPanel();
        divorcePanel.setLayout(null);
        divorcePanel.setBounds(0, 220, 1133, 744);
        divorcePanel.setOpaque(false);

        // ลดหย่อนบิดา-มารดา (ตนเอง)
        JLabel divorce_fathmoth = new JLabel("ลดหย่อนบิดา-มารดา (ตนเอง)");
        divorce_fathmoth.setFont(new Font("Tahoma", Font.BOLD, 24));
        divorce_fathmoth.setBounds(75, 0, 500, 45);
        divorcePanel.add(divorce_fathmoth, JLayeredPane.PALETTE_LAYER);

        // ปุ่มกลมพ่อแม่
        JRadioButton divorce_father1 = new JRadioButton(" บิดา");
        divorce_father1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_father1.setBounds(100, 30, 150, 50);
        divorce_father1.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        divorcePanel.add(divorce_father1, JLayeredPane.PALETTE_LAYER);

        JRadioButton divorce_mother1 = new JRadioButton(" มารดา");
        divorce_mother1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_mother1.setBounds(300, 30, 200, 50);
        divorce_mother1.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        divorcePanel.add(divorce_mother1, JLayeredPane.PALETTE_LAYER);

        // คนละ 30,000 บาท (บิดามารดาต้องมีอายุเกิน 60 ปี และมีเงินได้ไม่เกิน 30,000
        // บาทต่อปี) 
        JLabel divorce_condition1 = new JLabel(
                "คนละ 30,000 บาท (บิดามารดาต้องมีอายุเกิน 60 ปี และมีเงินได้ไม่เกิน 30,000 บาทต่อปี)");
        divorce_condition1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        divorce_condition1.setBounds(75, 60, 1000, 45);
        divorcePanel.add(divorce_condition1, JLayeredPane.PALETTE_LAYER);

        // (ได้ทั้งบิดา มารดาของตนเอง และคู่สมรส)
        JLabel divorce_condition2 = new JLabel("(ได้ทั้งบิดา มารดาของตนเอง และคู่สมรส)");
        divorce_condition2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        divorce_condition2.setBounds(75, 75, 1000, 45);
        divorcePanel.add(divorce_condition2, JLayeredPane.PALETTE_LAYER);

        // บุตรคนที่ 1 (เกิดปีใดก็ตาม)
        JLabel divorce_son_1 = new JLabel("บุตรคนที่ 1 (เกิดปีใดก็ตาม)");
        divorce_son_1.setFont(new Font("Tahoma", Font.BOLD, 24));
        divorce_son_1.setBounds(540, 0, 500, 45);
        divorcePanel.add(divorce_son_1, JLayeredPane.PALETTE_LAYER);

        // ปุ่มเลือกบุตร1
        JRadioButton divorce_havekid = new JRadioButton(" มี");
        JRadioButton divorce_donthavekid = new JRadioButton(" ไม่มี");
        ButtonGroup bg = new ButtonGroup();
        bg.add(divorce_donthavekid);
        bg.add(divorce_havekid);
        divorce_havekid.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_havekid.setBounds(565, 30, 150, 50);
        divorce_havekid.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        divorcePanel.add(divorce_havekid, JLayeredPane.PALETTE_LAYER);
        divorce_donthavekid.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_donthavekid.setBounds(765, 30, 150, 50);
        divorce_donthavekid.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        divorcePanel.add(divorce_donthavekid, JLayeredPane.PALETTE_LAYER);

        // ลดหย่อน 30,000 บาท
        JLabel divorce_discount_30000 = new JLabel("ลดหย่อน 30,000 บาท");
        divorce_discount_30000.setFont(new Font("Tahoma", Font.BOLD, 11));
        divorce_discount_30000.setBounds(855, 5, 500, 45);
        divorcePanel.add(divorce_discount_30000, JLayeredPane.PALETTE_LAYER);

        // สร้าง JPanel สำหรับเก็บคอมโพเนนต์ที่จะแสดงเมื่อเลือก "มี"
        JPanel divorce_haveKidPanel = new JPanel();
        divorce_haveKidPanel.setLayout(null);
        divorce_haveKidPanel.setBounds(75, 110, 1000, 300);
        divorce_haveKidPanel.setOpaque(false);
        divorce_haveKidPanel.setVisible(false);
        divorcePanel.add(divorce_haveKidPanel, JLayeredPane.PALETTE_LAYER);

        // บุตรคนที่ 2 เป็นต้นไป
        JLabel divorce_son_2_more = new JLabel("บุตรคนที่ 2 เป็นต้นไป");
        divorce_son_2_more.setFont(new Font("Tahoma", Font.BOLD, 24));
        divorce_son_2_more.setBounds(0, 0, 500, 45); // เดิม (75, 325) ใน JFrame
        divorce_haveKidPanel.add(divorce_son_2_more);

        // จำนวนบุตรที่เกิดก่อนปี 2561
        JLabel divorce_son_before2561 = new JLabel("จำนวนบุตรที่เกิดก่อนปี 2561");
        divorce_son_before2561.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_son_before2561.setBounds(0, 30, 500, 45); // เดิม (75, 355) ใน JFrame
        divorce_haveKidPanel.add(divorce_son_before2561);

        // ลดหย่อน 30,000 บาท+
        JLabel divorce_discount2_30000 = new JLabel("ลดหย่อน 30,000 บาท ");
        divorce_discount2_30000.setFont(new Font("Tahoma", Font.BOLD, 11));
        divorce_discount2_30000.setBounds(245, 32, 500, 45); // เดิม (320, 357) ใน JFrame
        divorce_haveKidPanel.add(divorce_discount2_30000);

        // จำนวนบุตรที่เกิดตั้งแต่ปี 2561 เป็นต้นไป
        JLabel divorce_son_after2561 = new JLabel("จำนวนบุตรที่เกิดตั้งแต่ปี 2561 เป็นต้นไป");
        divorce_son_after2561.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_son_after2561.setBounds(465, 30, 1000, 45); // เดิม (540, 355) ใน JFrame
        divorce_haveKidPanel.add(divorce_son_after2561);

        // ลดหย่อน 30,000 บาท
        JLabel divorce_discount3_60000 = new JLabel("ลดหย่อน 60,000 บาท");
        divorce_discount3_60000.setFont(new Font("Tahoma", Font.BOLD, 11));
        divorce_discount3_60000.setBounds(800, 32, 500, 45); // เดิม (875, 357) ใน JFrame
        divorce_haveKidPanel.add(divorce_discount3_60000);

        // textfield ของบุตรก่อน2561
        JTextField divorce_text_before = new JTextField(" ");
        divorce_text_before.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_text_before.setBounds(0, 70, 300, 45); // เดิม (75, 395) ใน JFrame
        divorce_haveKidPanel.add(divorce_text_before);

        // textfield ของบุตรหลัง2561
        JTextField divorce_text_after = new JTextField(" ");
        divorce_text_after.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_text_after.setBounds(465, 70, 300, 45); // เดิม (540, 395) ใน JFrame
        divorce_haveKidPanel.add(divorce_text_after);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)
        JLabel divorce_disabled_no_money1 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)");
        divorce_disabled_no_money1.setFont(new Font("Tahoma", Font.BOLD, 24));
        divorce_disabled_no_money1.setBounds(0, 115, 1000, 45);
        divorce_haveKidPanel.add(divorce_disabled_no_money1);

        // ปุ่มกลมพ่อแม่ญาติ
        JRadioButton divorce_father2 = new JRadioButton(" บิดา");
        divorce_father2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_father2.setBounds(25, 145, 100, 50);
        divorce_father2.setOpaque(false);
        divorce_haveKidPanel.add(divorce_father2);

        JRadioButton divorce_mother2 = new JRadioButton(" มารดา");
        divorce_mother2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_mother2.setBounds(125, 145, 100, 50);
        divorce_mother2.setOpaque(false);
        divorce_haveKidPanel.add(divorce_mother2);

        JRadioButton divorce_son = new JRadioButton(" บุตร");
        divorce_son.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_son.setBounds(225, 145, 100, 50);
        divorce_son.setOpaque(false);
        divorce_haveKidPanel.add(divorce_son);

        JRadioButton divorce_cousin1 = new JRadioButton(" ญาติ(พี่ น้อง ฯลฯ)");
        divorce_cousin1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_cousin1.setBounds(325, 145, 200, 50);
        divorce_cousin1.setOpaque(false);
        divorce_haveKidPanel.add(divorce_cousin1);

        // กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง
        JLabel divorce_condition3 = new JLabel("กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง");
        divorce_condition3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        divorce_condition3.setBounds(0, 180, 1000, 45);
        divorce_haveKidPanel.add(divorce_condition3);

        // หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท
        // (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)
        JLabel divorce_condition4 = new JLabel(
                "หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)");
        divorce_condition4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        divorce_condition4.setBounds(0, 195, 1000, 45);
        divorce_haveKidPanel.add(divorce_condition4);

        // สร้าง JPanel สำหรับเก็บคอมโพเนนต์ที่จะแสดงเมื่อเลือก "ไม่มี"
        JPanel divorce_dontHaveKidPanel = new JPanel();
        divorce_dontHaveKidPanel.setLayout(null);
        divorce_dontHaveKidPanel.setBounds(75, 110, 1000, 300);
        divorce_dontHaveKidPanel.setOpaque(false);
        divorce_dontHaveKidPanel.setVisible(false);
        divorcePanel.add(divorce_dontHaveKidPanel, JLayeredPane.PALETTE_LAYER);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)
        JLabel divorce_disabled_no_money2 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)");
        divorce_disabled_no_money2.setFont(new Font("Tahoma", Font.BOLD, 24));
        divorce_disabled_no_money2.setBounds(0, 0, 1000, 45); // เดิม (75, 325) ใน JFrame
        divorce_dontHaveKidPanel.add(divorce_disabled_no_money2);

        // ปุ่มกลมพ่อแม่ญาติ
        JRadioButton divorce_father4 = new JRadioButton(" บิดา");
        divorce_father4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_father4.setBounds(25, 35, 150, 50); // เดิม (100, 360) ใน JFrame
        divorce_father4.setOpaque(false);
        divorce_dontHaveKidPanel.add(divorce_father4);

        JRadioButton divorce_mother4 = new JRadioButton(" มารดา");
        divorce_mother4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_mother4.setBounds(125, 35, 200, 50); // เดิม (300, 360) ใน JFrame
        divorce_mother4.setOpaque(false);
        divorce_dontHaveKidPanel.add(divorce_mother4);

        JRadioButton divorce_cousin2 = new JRadioButton(" ญาติ(พี่ น้อง ฯลฯ)");
        divorce_cousin2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        divorce_cousin2.setBounds(225, 35, 500, 50); // เดิม (500, 360) ใน JFrame
        divorce_cousin2.setOpaque(false);
        divorce_dontHaveKidPanel.add(divorce_cousin2);

        // กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง
        JLabel divorce_condition5 = new JLabel("กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง");
        divorce_condition5.setFont(new Font("Tahoma", Font.PLAIN, 11));
        divorce_condition5.setBounds(0, 65, 1000, 45);
        divorce_dontHaveKidPanel.add(divorce_condition5);

        // หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท
        // (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)
        JLabel divorce_condition6 = new JLabel(
                "หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)");
        divorce_condition6.setFont(new Font("Tahoma", Font.PLAIN, 11));
        divorce_condition6.setBounds(0, 80, 1000, 45);
        divorce_dontHaveKidPanel.add(divorce_condition6);

        divorce_havekid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                divorce_haveKidPanel.setVisible(true);
                divorce_dontHaveKidPanel.setVisible(false);

            }
        });

        divorce_donthavekid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                divorce_haveKidPanel.setVisible(false);
                divorce_dontHaveKidPanel.setVisible(true);
            }
        });

        return divorcePanel;
    }

    private static JPanel createMarriedHaveMoneyPanel() {
        JPanel panelMHM = new JPanel();
        panelMHM.setLayout(null);
        panelMHM.setBounds(0, 220, 1133, 744);
        panelMHM.setOpaque(false);

        // ลดหย่อนบิดา-มารดา (ตนเอง)
        JLabel MHM_fathmoth = new JLabel("ลดหย่อนบิดา-มารดา (ตนเอง)");
        MHM_fathmoth.setFont(new Font("Tahoma", Font.BOLD, 24));
        MHM_fathmoth.setBounds(75, 0, 500, 45);
        panelMHM.add(MHM_fathmoth);

        // ปุ่มกลมพ่อแม่
        JRadioButton MHM_father1 = new JRadioButton(" บิดา");
        MHM_father1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_father1.setBounds(100, 30, 150, 50);
        MHM_father1.setOpaque(false);
        panelMHM.add(MHM_father1);

        JRadioButton MHM_mother1 = new JRadioButton(" มารดา");
        MHM_mother1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_mother1.setBounds(300, 30, 200, 50);
        MHM_mother1.setOpaque(false);
        panelMHM.add(MHM_mother1);

        // คนละ 30,000 บาท (บิดามารดาต้องมีอายุเกิน 60 ปี และมีเงินได้ไม่เกิน 30,000
        // บาทต่อปี)
        JLabel MHM_condition1 = new JLabel(
                "คนละ 30,000 บาท (บิดามารดาต้องมีอายุเกิน 60 ปี และมีเงินได้ไม่เกิน 30,000 บาทต่อปี)");
        MHM_condition1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        MHM_condition1.setBounds(75, 60, 1000, 45);
        panelMHM.add(MHM_condition1);

        // (ได้ทั้งบิดา มารดาของตนเอง และคู่สมรส)
        JLabel MHM_condition2 = new JLabel("(ได้ทั้งบิดา มารดาของตนเอง และคู่สมรส)");
        MHM_condition2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        MHM_condition2.setBounds(75, 75, 1000, 45);
        panelMHM.add(MHM_condition2);

        // บุตรคนที่ 1 (เกิดปีใดก็ตาม)
        JLabel MHM_son_1 = new JLabel("บุตรคนที่ 1 (เกิดปีใดก็ตาม)");
        MHM_son_1.setFont(new Font("Tahoma", Font.BOLD, 24));
        MHM_son_1.setBounds(540, 0, 500, 45);
        panelMHM.add(MHM_son_1);

        // ปุ่มเลือกบุตร1
        JRadioButton MHM_havekid = new JRadioButton(" มี");
        JRadioButton MHM_donthavekid = new JRadioButton(" ไม่มี");
        ButtonGroup bg = new ButtonGroup();
        bg.add(MHM_donthavekid);
        bg.add(MHM_havekid);

        MHM_havekid.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_havekid.setBounds(565, 30, 150, 50);
        MHM_havekid.setOpaque(false);
        panelMHM.add(MHM_havekid);
        MHM_donthavekid.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_donthavekid.setBounds(765, 30, 150, 50);
        MHM_donthavekid.setOpaque(false);
        panelMHM.add(MHM_donthavekid);

        // ลดหย่อน 30,000 บาท
        JLabel MHM_discount_30000 = new JLabel("ลดหย่อน 30,000 บาท");
        MHM_discount_30000.setFont(new Font("Tahoma", Font.BOLD, 11));
        MHM_discount_30000.setBounds(855, 5, 500, 45);
        panelMHM.add(MHM_discount_30000);

        // สร้าง JPanel สำหรับเก็บคอมโพเนนต์ที่จะแสดงเมื่อเลือก "มี"
        JPanel MHM_haveKidPanel = new JPanel();
        MHM_haveKidPanel.setLayout(null);
        MHM_haveKidPanel.setBounds(75, 110, 1000, 300);
        MHM_haveKidPanel.setOpaque(false);
        MHM_haveKidPanel.setVisible(false);
        panelMHM.add(MHM_haveKidPanel);

        // บุตรคนที่ 2 เป็นต้นไป
        JLabel MHM_son_2_more = new JLabel("บุตรคนที่ 2 เป็นต้นไป");
        MHM_son_2_more.setFont(new Font("Tahoma", Font.BOLD, 24));
        MHM_son_2_more.setBounds(0, 0, 500, 45); // เดิม (75, 325) ใน JFrame
        MHM_haveKidPanel.add(MHM_son_2_more);

        // จำนวนบุตรที่เกิดก่อนปี 2561
        JLabel MHM_son_before2561 = new JLabel("จำนวนบุตรที่เกิดก่อนปี 2561");
        MHM_son_before2561.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_son_before2561.setBounds(0, 30, 500, 45); // เดิม (75, 355) ใน JFrame
        MHM_haveKidPanel.add(MHM_son_before2561);

        // ลดหย่อน 30,000 บาท
        JLabel MHM_discount2_30000 = new JLabel("ลดหย่อน 30,000 บาท ");
        MHM_discount2_30000.setFont(new Font("Tahoma", Font.BOLD, 11));
        MHM_discount2_30000.setBounds(245, 32, 500, 45); // เดิม (320, 357) ใน JFrame
        MHM_haveKidPanel.add(MHM_discount2_30000);

        // จำนวนบุตรที่เกิดตั้งแต่ปี 2561 เป็นต้นไป
        JLabel MHM_son_after2561 = new JLabel("จำนวนบุตรที่เกิดตั้งแต่ปี 2561 เป็นต้นไป");
        MHM_son_after2561.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_son_after2561.setBounds(465, 30, 1000, 45); // เดิม (540, 355) ใน JFrame
        MHM_haveKidPanel.add(MHM_son_after2561);

        // ลดหย่อน 60,000 บาท
        JLabel MHM_discount3_60000 = new JLabel("ลดหย่อน 60,000 บาท");
        MHM_discount3_60000.setFont(new Font("Tahoma", Font.BOLD, 11));
        MHM_discount3_60000.setBounds(800, 32, 500, 45); // เดิม (875, 357) ใน JFrame
        MHM_haveKidPanel.add(MHM_discount3_60000);

        // textfield ของบุตรก่อน2561
        JTextField MHM_text_before = new JTextField(" ");
        MHM_text_before.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_text_before.setBounds(0, 70, 300, 45); // เดิม (75, 395) ใน JFrame
        MHM_haveKidPanel.add(MHM_text_before);

        // textfield ของบุตรหลัง2561
        JTextField MHM_text_after = new JTextField(" ");
        MHM_text_after.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_text_after.setBounds(465, 70, 300, 45); // เดิม (540, 395) ใน JFrame
        MHM_haveKidPanel.add(MHM_text_after);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)
        JLabel MHM_disabled_no_money1 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)");
        MHM_disabled_no_money1.setFont(new Font("Tahoma", Font.BOLD, 24));
        MHM_disabled_no_money1.setBounds(0, 115, 1000, 45);
        MHM_haveKidPanel.add(MHM_disabled_no_money1);

        // ปุ่มกลมพ่อแม่ญาติ
        JRadioButton MHM_father2 = new JRadioButton(" บิดา");
        MHM_father2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_father2.setBounds(25, 145, 100, 50);
        MHM_father2.setOpaque(false);
        MHM_haveKidPanel.add(MHM_father2);

        JRadioButton MHM_mother2 = new JRadioButton(" มารดา");
        MHM_mother2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_mother2.setBounds(125, 145, 100, 50);
        MHM_mother2.setOpaque(false);
        MHM_haveKidPanel.add(MHM_mother2);

        JRadioButton MHM_son = new JRadioButton(" บุตร");
        MHM_son.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_son.setBounds(225, 145, 100, 50);
        MHM_son.setOpaque(false);
        MHM_haveKidPanel.add(MHM_son);

        JRadioButton MHM_cousin1 = new JRadioButton(" ญาติ(พี่ น้อง ฯลฯ)");
        MHM_cousin1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_cousin1.setBounds(325, 145, 200, 50);
        MHM_cousin1.setOpaque(false);
        MHM_haveKidPanel.add(MHM_cousin1);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)
        JLabel MHM_disabled_married_no_money1 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)");
        MHM_disabled_married_no_money1.setFont(new Font("Tahoma", Font.BOLD, 24));
        MHM_disabled_married_no_money1.setBounds(0, 180, 1000, 45);
        MHM_haveKidPanel.add(MHM_disabled_married_no_money1);

        // ปุ่มกลมบิดามารดาคู่สมรส
        JRadioButton MHM_father3 = new JRadioButton(" บิดา");
        MHM_father3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_father3.setBounds(25, 210, 150, 50);
        MHM_father3.setOpaque(false);
        MHM_haveKidPanel.add(MHM_father3);

        JRadioButton MHM_mother3 = new JRadioButton(" มารดา");
        MHM_mother3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_mother3.setBounds(125, 210, 200, 50);
        MHM_mother3.setOpaque(false);
        MHM_haveKidPanel.add(MHM_mother3);

        JRadioButton MHM_married1 = new JRadioButton(" คู่สมรส");
        MHM_married1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_married1.setBounds(225, 210, 500, 50);
        MHM_married1.setOpaque(false);
        MHM_haveKidPanel.add(MHM_married1);

        // กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง
        JLabel MHM_condition3 = new JLabel("กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง");
        MHM_condition3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        MHM_condition3.setBounds(0, 235, 1000, 45);
        MHM_haveKidPanel.add(MHM_condition3);

        // หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท
        // (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)
        JLabel MHM_condition4 = new JLabel(
                "หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)");
        MHM_condition4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        MHM_condition4.setBounds(0, 250, 1000, 45);
        MHM_haveKidPanel.add(MHM_condition4);

        // สร้าง JPanel สำหรับเก็บคอมโพเนนต์ที่จะแสดงเมื่อเลือก "ไม่มี"
        JPanel MHM_dontHaveKidPanel = new JPanel();
        MHM_dontHaveKidPanel.setLayout(null);
        MHM_dontHaveKidPanel.setBounds(75, 110, 1000, 300);
        MHM_dontHaveKidPanel.setOpaque(false);
        MHM_dontHaveKidPanel.setVisible(false);
        panelMHM.add(MHM_dontHaveKidPanel);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)
        JLabel MHM_disabled_no_money2 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)");
        MHM_disabled_no_money2.setFont(new Font("Tahoma", Font.BOLD, 24));
        MHM_disabled_no_money2.setBounds(0, 0, 1000, 45); // เดิม (75, 325) ใน JFrame
        MHM_dontHaveKidPanel.add(MHM_disabled_no_money2);

        // ปุ่มกลมพ่อแม่ญาติ
        JRadioButton MHM_father4 = new JRadioButton(" บิดา");
        MHM_father4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_father4.setBounds(25, 35, 150, 50); // เดิม (100, 360) ใน JFrame
        MHM_father4.setOpaque(false);
        MHM_dontHaveKidPanel.add(MHM_father4);

        JRadioButton MHM_mother4 = new JRadioButton(" มารดา");
        MHM_mother4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_mother4.setBounds(225, 35, 200, 50); // เดิม (300, 360) ใน JFrame
        MHM_mother4.setOpaque(false);
        MHM_dontHaveKidPanel.add(MHM_mother4);

        JRadioButton MHM_cousin2 = new JRadioButton(" ญาติ(พี่ น้อง ฯลฯ)");
        MHM_cousin2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_cousin2.setBounds(425, 35, 500, 50); // เดิม (500, 360) ใน JFrame
        MHM_cousin2.setOpaque(false);
        MHM_dontHaveKidPanel.add(MHM_cousin2);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)
        JLabel MHM_disabled_married_no_money2 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)");
        MHM_disabled_married_no_money2.setFont(new Font("Tahoma", Font.BOLD, 24));
        MHM_disabled_married_no_money2.setBounds(0, 75, 1000, 45); // เดิม (75, 400) ใน JFrame
        MHM_dontHaveKidPanel.add(MHM_disabled_married_no_money2);

        // ปุ่มกลมบิดามารดาคู่สมรส
        JRadioButton MHM_father5 = new JRadioButton(" บิดา");
        MHM_father5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_father5.setBounds(25, 110, 150, 50); // เดิม (100, 430) ใน JFrame
        MHM_father5.setOpaque(false);
        MHM_dontHaveKidPanel.add(MHM_father5);

        JRadioButton MHM_mother5 = new JRadioButton(" มารดา");
        MHM_mother5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_mother5.setBounds(225, 110, 200, 50); // เดิม (300, 430) ใน JFrame
        MHM_mother5.setOpaque(false);
        MHM_dontHaveKidPanel.add(MHM_mother5);

        JRadioButton MHM_married2 = new JRadioButton(" คู่สมรส");
        MHM_married2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MHM_married2.setBounds(425, 110, 500, 50); // เดิม (500, 430) ใน JFrame
        MHM_married2.setOpaque(false);
        MHM_dontHaveKidPanel.add(MHM_married2);

        // กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง
        JLabel MHM_condition5 = new JLabel("กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง");
        MHM_condition5.setFont(new Font("Tahoma", Font.PLAIN, 11));
        MHM_condition5.setBounds(0, 140, 1000, 45);
        MHM_dontHaveKidPanel.add(MHM_condition5);

        // หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท
        // (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)
        JLabel MHM_condition6 = new JLabel(
                "หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)");
        MHM_condition6.setFont(new Font("Tahoma", Font.PLAIN, 11));
        MHM_condition6.setBounds(0, 155, 1000, 45);
        MHM_dontHaveKidPanel.add(MHM_condition6);

        MHM_havekid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MHM_haveKidPanel.setVisible(true);
                MHM_dontHaveKidPanel.setVisible(false);

            }
        });
        MHM_donthavekid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MHM_haveKidPanel.setVisible(false);
                MHM_dontHaveKidPanel.setVisible(true);
            }
        });
        return panelMHM;
    }

    private static JPanel createMarriedDontHaveMoneyPanel() {
        JPanel MDHMpanel = new JPanel();
        MDHMpanel.setLayout(null);
        MDHMpanel.setBounds(0, 220, 1133, 744);
        MDHMpanel.setOpaque(false);

        // ลดหย่อนบิดา-มารดา (ตนเอง)
        JLabel MDHM_fathmoth = new JLabel("ลดหย่อนบิดา-มารดา (ตนเอง)");
        MDHM_fathmoth.setFont(new Font("Tahoma", Font.BOLD, 24));
        MDHM_fathmoth.setBounds(75, 0, 500, 45);
        MDHMpanel.add(MDHM_fathmoth);

        // ปุ่มกลมพ่อแม่
        JRadioButton MDHM_father1 = new JRadioButton(" บิดา");
        MDHM_father1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_father1.setBounds(100, 30, 150, 50);
        MDHM_father1.setOpaque(false);
        MDHMpanel.add(MDHM_father1);

        JRadioButton MDHM_mother1 = new JRadioButton(" มารดา");
        MDHM_mother1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_mother1.setBounds(300, 30, 200, 50);
        MDHM_mother1.setOpaque(false);
        MDHMpanel.add(MDHM_mother1);

        // คนละ 30,000 บาท (บิดามารดาต้องมีอายุเกิน 60 ปี และมีเงินได้ไม่เกิน 30,000
        // บาทต่อปี)
        JLabel MDHM_condition1 = new JLabel(
                "คนละ 30,000 บาท (บิดามารดาต้องมีอายุเกิน 60 ปี และมีเงินได้ไม่เกิน 30,000 บาทต่อปี)");
        MDHM_condition1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        MDHM_condition1.setBounds(75, 60, 1000, 45);
        MDHMpanel.add(MDHM_condition1);

        // (ได้ทั้งบิดา มารดาของตนเอง และคู่สมรส)
        JLabel MDHM_condition2 = new JLabel("(ได้ทั้งบิดา มารดาของตนเอง และคู่สมรส)");
        MDHM_condition2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        MDHM_condition2.setBounds(75, 75, 1000, 45);
        MDHMpanel.add(MDHM_condition2);

        // บุตรคนที่ 1 (เกิดปีใดก็ตาม)
        JLabel MDHM_son_1 = new JLabel("บุตรคนที่ 1 (เกิดปีใดก็ตาม)");
        MDHM_son_1.setFont(new Font("Tahoma", Font.BOLD, 24));
        MDHM_son_1.setBounds(540, 0, 500, 45);
        MDHMpanel.add(MDHM_son_1);

        // ปุ่มเลือกบุตร1
        JRadioButton MDHM_havekid = new JRadioButton(" มี");
        JRadioButton MDHM_donthavekid = new JRadioButton(" ไม่มี");
        ButtonGroup bg = new ButtonGroup();
        bg.add(MDHM_donthavekid);
        bg.add(MDHM_havekid);

        MDHM_havekid.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_havekid.setBounds(565, 30, 150, 50);
        MDHM_havekid.setOpaque(false);
        MDHMpanel.add(MDHM_havekid);
        MDHM_donthavekid.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_donthavekid.setBounds(765, 30, 150, 50);
        MDHM_donthavekid.setOpaque(false);
        MDHMpanel.add(MDHM_donthavekid);

        // ลดหย่อน 30,000 บาท
        JLabel MDHM_discount_30000 = new JLabel("ลดหย่อน 30,000 บาท");
        MDHM_discount_30000.setFont(new Font("Tahoma", Font.BOLD, 11));
        MDHM_discount_30000.setBounds(855, 5, 500, 45);
        MDHMpanel.add(MDHM_discount_30000);

        // สร้าง JPanel สำหรับเก็บคอมโพเนนต์ที่จะแสดงเมื่อเลือก "มี"
        JPanel MDHM_haveKidPanel = new JPanel();
        MDHM_haveKidPanel.setLayout(null);
        MDHM_haveKidPanel.setBounds(75, 110, 1000, 300);
        MDHM_haveKidPanel.setOpaque(false);
        MDHM_haveKidPanel.setVisible(false);
        MDHMpanel.add(MDHM_haveKidPanel);

        // บุตรคนที่ 2 เป็นต้นไป
        JLabel MDHM_son_2_more = new JLabel("บุตรคนที่ 2 เป็นต้นไป");
        MDHM_son_2_more.setFont(new Font("Tahoma", Font.BOLD, 24));
        MDHM_son_2_more.setBounds(0, 0, 500, 45); // เดิม (75, 325) ใน JFrame
        MDHM_haveKidPanel.add(MDHM_son_2_more);

        // จำนวนบุตรที่เกิดก่อนปี 2561
        JLabel MDHM_son_before2561 = new JLabel("จำนวนบุตรที่เกิดก่อนปี 2561");
        MDHM_son_before2561.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_son_before2561.setBounds(0, 30, 500, 45); // เดิม (75, 355) ใน JFrame
        MDHM_haveKidPanel.add(MDHM_son_before2561);

        // ลดหย่อน 30,000 บาท
        JLabel MDHM_discount2_30000 = new JLabel("ลดหย่อน 30,000 บาท ");
        MDHM_discount2_30000.setFont(new Font("Tahoma", Font.BOLD, 11));
        MDHM_discount2_30000.setBounds(245, 32, 500, 45); // เดิม (320, 357) ใน JFrame
        MDHM_haveKidPanel.add(MDHM_discount2_30000);

        // จำนวนบุตรที่เกิดตั้งแต่ปี 2561 เป็นต้นไป
        JLabel MDHM_son_after2561 = new JLabel("จำนวนบุตรที่เกิดตั้งแต่ปี 2561 เป็นต้นไป");
        MDHM_son_after2561.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_son_after2561.setBounds(465, 30, 1000, 45); // เดิม (540, 355) ใน JFrame
        MDHM_haveKidPanel.add(MDHM_son_after2561);

        // ลดหย่อน 60,000 บาท
        JLabel MDHM_discount3_60000 = new JLabel("ลดหย่อน 60,000 บาท");
        MDHM_discount3_60000.setFont(new Font("Tahoma", Font.BOLD, 11));
        MDHM_discount3_60000.setBounds(800, 32, 500, 45); // เดิม (875, 357) ใน JFrame
        MDHM_haveKidPanel.add(MDHM_discount3_60000);

        // textfield ของบุตรก่อน2561
        JTextField MDHM_text_before = new JTextField(" ");
        MDHM_text_before.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_text_before.setBounds(0, 70, 300, 45); // เดิม (75, 395) ใน JFrame
        MDHM_haveKidPanel.add(MDHM_text_before);

        // textfield ของบุตรหลัง2561
        JTextField MDHM_text_after = new JTextField(" ");
        MDHM_text_after.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_text_after.setBounds(465, 70, 300, 45); // เดิม (540, 395) ใน JFrame
        MDHM_haveKidPanel.add(MDHM_text_after);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)
        JLabel MDHM_disabled_no_money1 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)");
        MDHM_disabled_no_money1.setFont(new Font("Tahoma", Font.BOLD, 24));
        MDHM_disabled_no_money1.setBounds(0, 115, 1000, 45);
        MDHM_haveKidPanel.add(MDHM_disabled_no_money1);

        // ปุ่มกลมพ่อแม่ญาติ
        JRadioButton MDHM_father2 = new JRadioButton(" บิดา");
        MDHM_father2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_father2.setBounds(25, 145, 150, 50);
        MDHM_father2.setOpaque(false);
        MDHM_haveKidPanel.add(MDHM_father2);

        JRadioButton MDHM_mother2 = new JRadioButton(" มารดา");
        MDHM_mother2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_mother2.setBounds(125, 145, 200, 50);
        MDHM_mother2.setOpaque(false);
        MDHM_haveKidPanel.add(MDHM_mother2);

        JRadioButton MDHM_son1 = new JRadioButton(" บุตร");
        MDHM_son1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_son1.setBounds(225, 145, 500, 50);
        MDHM_son1.setOpaque(false);
        MDHM_haveKidPanel.add(MDHM_son1);

        JRadioButton MDHM_cousin1 = new JRadioButton(" ญาติ(พี่ น้อง ฯลฯ)");
        MDHM_cousin1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_cousin1.setBounds(325, 145, 500, 50);
        MDHM_cousin1.setOpaque(false);
        MDHM_haveKidPanel.add(MDHM_cousin1);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)
        JLabel MDHM_disabled_married_no_money1 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)");
        MDHM_disabled_married_no_money1.setFont(new Font("Tahoma", Font.BOLD, 24));
        MDHM_disabled_married_no_money1.setBounds(0, 180, 1000, 45);
        MDHM_haveKidPanel.add(MDHM_disabled_married_no_money1);

        // ปุ่มกลมบิดามารดาคู่สมรส
        JRadioButton MDHM_father3 = new JRadioButton(" บิดา");
        MDHM_father3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_father3.setBounds(25, 210, 150, 50);
        MDHM_father3.setOpaque(false);
        MDHM_haveKidPanel.add(MDHM_father3);

        JRadioButton MDHM_mother3 = new JRadioButton(" มารดา");
        MDHM_mother3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_mother3.setBounds(125, 210, 200, 50);
        MDHM_mother3.setOpaque(false);
        MDHM_haveKidPanel.add(MDHM_mother3);

        JRadioButton MDHM_married1 = new JRadioButton(" คู่สมรส");
        MDHM_married1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_married1.setBounds(225, 210, 500, 50);
        MDHM_married1.setOpaque(false);
        MDHM_haveKidPanel.add(MDHM_married1);

        // กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง
        JLabel MDHM_condition3 = new JLabel("กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง");
        MDHM_condition3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        MDHM_condition3.setBounds(0, 235, 1000, 45);
        MDHM_haveKidPanel.add(MDHM_condition3);

        // หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท
        // (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)
        JLabel MDHM_condition4 = new JLabel(
                "หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)");
        MDHM_condition4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        MDHM_condition4.setBounds(0, 250, 1000, 45);
        MDHM_haveKidPanel.add(MDHM_condition4);

        // สร้าง JPanel สำหรับเก็บคอมโพเนนต์ที่จะแสดงเมื่อเลือก "ไม่มี"
        JPanel MDHM_dontHaveKidPanel = new JPanel();
        MDHM_dontHaveKidPanel.setLayout(null);
        MDHM_dontHaveKidPanel.setBounds(75, 110, 1000, 300);
        MDHM_dontHaveKidPanel.setOpaque(false);
        MDHM_dontHaveKidPanel.setVisible(false);
        MDHMpanel.add(MDHM_dontHaveKidPanel);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)
        JLabel MDHM_disabled_no_money2 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)");
        MDHM_disabled_no_money2.setFont(new Font("Tahoma", Font.BOLD, 24));
        MDHM_disabled_no_money2.setBounds(0, 0, 1000, 45); // เดิม (75, 325) ใน JFrame
        MDHM_dontHaveKidPanel.add(MDHM_disabled_no_money2);

        // ปุ่มกลมพ่อแม่ญาติ
        JRadioButton MDHM_father4 = new JRadioButton(" บิดา");
        MDHM_father4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_father4.setBounds(25, 35, 150, 50); // เดิม (100, 360) ใน JFrame
        MDHM_father4.setOpaque(false);
        MDHM_dontHaveKidPanel.add(MDHM_father4);

        JRadioButton MDHM_mother4 = new JRadioButton(" มารดา");
        MDHM_mother4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_mother4.setBounds(225, 35, 200, 50); // เดิม (300, 360) ใน JFrame
        MDHM_mother4.setOpaque(false);
        MDHM_dontHaveKidPanel.add(MDHM_mother4);

        JRadioButton MDHM_cousin2 = new JRadioButton(" ญาติ(พี่ น้อง ฯลฯ)");
        MDHM_cousin2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_cousin2.setBounds(425, 35, 500, 50); // เดิม (500, 360) ใน JFrame
        MDHM_cousin2.setOpaque(false);
        MDHM_dontHaveKidPanel.add(MDHM_cousin2);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)
        JLabel MDHM_disabled_married_no_money2 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)");
        MDHM_disabled_married_no_money2.setFont(new Font("Tahoma", Font.BOLD, 24));
        MDHM_disabled_married_no_money2.setBounds(0, 75, 1000, 45); // เดิม (75, 400) ใน JFrame
        MDHM_dontHaveKidPanel.add(MDHM_disabled_married_no_money2);

        // ปุ่มกลมบิดามารดาคู่สมรส
        JRadioButton MDHM_father5 = new JRadioButton(" บิดา");
        MDHM_father5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_father5.setBounds(25, 110, 150, 50); // เดิม (100, 430) ใน JFrame
        MDHM_father5.setOpaque(false);
        MDHM_dontHaveKidPanel.add(MDHM_father5);

        JRadioButton MDHM_mother5 = new JRadioButton(" มารดา");
        MDHM_mother5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_mother5.setBounds(225, 110, 200, 50); // เดิม (300, 430) ใน JFrame
        MDHM_mother5.setOpaque(false);
        MDHM_dontHaveKidPanel.add(MDHM_mother5);

        JRadioButton MDHM_married2 = new JRadioButton(" คู่สมรส");
        MDHM_married2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MDHM_married2.setBounds(425, 110, 500, 50); // เดิม (500, 430) ใน JFrame
        MDHM_married2.setOpaque(false);
        MDHM_dontHaveKidPanel.add(MDHM_married2);

        // กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง
        JLabel MDHM_condition5 = new JLabel("กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง");
        MDHM_condition5.setFont(new Font("Tahoma", Font.PLAIN, 11));
        MDHM_condition5.setBounds(0, 140, 1000, 45);
        MDHM_dontHaveKidPanel.add(MDHM_condition5);

        // หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท
        // (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)
        JLabel MDHM_condition6 = new JLabel(
                "หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)");
        MDHM_condition6.setFont(new Font("Tahoma", Font.PLAIN, 11));
        MDHM_condition6.setBounds(0, 155, 1000, 45);
        MDHM_dontHaveKidPanel.add(MDHM_condition6);

        MDHM_havekid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MDHM_haveKidPanel.setVisible(true);
                MDHM_dontHaveKidPanel.setVisible(false);
            }
        });
        MDHM_donthavekid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MDHM_haveKidPanel.setVisible(false);
                MDHM_dontHaveKidPanel.setVisible(true);
            }
        });
        return MDHMpanel;
    }

    public static class DisplayGraphics extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setOpaque(false);

            Graphics2D g2d = (Graphics2D) g;
            // วาดเส้น
            g2d.setColor(Color.decode("#6390BA"));
            g2d.setStroke(new BasicStroke(7));
            g.drawLine(0, 90, 1133, 90);
        }
    }
    

    

}