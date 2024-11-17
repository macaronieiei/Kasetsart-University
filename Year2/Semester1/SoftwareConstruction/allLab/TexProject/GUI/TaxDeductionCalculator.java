package TexProject.GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaxDeductionCalculator extends JFrame {
    private JTextField childBefore2561Field;
    private JTextField childAfter2561Field;
    private JRadioButton hasChildButton;
    private JRadioButton noChildButton;
    private JCheckBox fatherDeductionCheckBox;
    private JCheckBox motherDeductionCheckBox;
    private JCheckBox disabledFatherCheckBox;
    private JCheckBox disabledMotherCheckBox;
    private JCheckBox disabledSpouseCheckBox;
    private JCheckBox disabledChildCheckBox;
    private JTextField disabledChildCountField;
    private JTextField totalDeductionField;

    public TaxDeductionCalculator() {
        setTitle("Tax Deduction Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        initComponents();
        addListeners();

        setVisible(true);
    }

    private void initComponents() {
        Font thaiFont = new Font("Tahoma", Font.PLAIN, 18); // กำหนดฟอนต์ภาษาไทย
    
        JLabel hasChildLabel = new JLabel("มีบุตร:");
        hasChildLabel.setFont(thaiFont);
        add(hasChildLabel);
    
        JPanel childPanel = new JPanel();
        hasChildButton = new JRadioButton("มี");
        hasChildButton.setFont(thaiFont);
        noChildButton = new JRadioButton("ไม่มี");
        noChildButton.setFont(thaiFont);
        ButtonGroup childGroup = new ButtonGroup();
        childGroup.add(hasChildButton);
        childGroup.add(noChildButton);
        childPanel.add(hasChildButton);
        childPanel.add(noChildButton);
        add(childPanel);
    
        JLabel childBefore2561Label = new JLabel("จำนวนบุตรเกิดก่อนปี 2561:");
        childBefore2561Label.setFont(thaiFont);
        add(childBefore2561Label);
    
        childBefore2561Field = new JTextField(5);
        childBefore2561Field.setFont(thaiFont);
        add(childBefore2561Field);
    
        JLabel childAfter2561Label = new JLabel("จำนวนบุตรเกิดตั้งแต่ปี 2561:");
        childAfter2561Label.setFont(thaiFont);
        add(childAfter2561Label);
    
        childAfter2561Field = new JTextField(5);
        childAfter2561Field.setFont(thaiFont);
        add(childAfter2561Field);
    
        JLabel fatherDeductionLabel = new JLabel("ลดหย่อนบิดา:");
        fatherDeductionLabel.setFont(thaiFont);
        add(fatherDeductionLabel);
    
        fatherDeductionCheckBox = new JCheckBox();
        fatherDeductionCheckBox.setFont(thaiFont);
        add(fatherDeductionCheckBox);
    
        JLabel motherDeductionLabel = new JLabel("ลดหย่อนมารดา:");
        motherDeductionLabel.setFont(thaiFont);
        add(motherDeductionLabel);
    
        motherDeductionCheckBox = new JCheckBox();
        motherDeductionCheckBox.setFont(thaiFont);
        add(motherDeductionCheckBox);
    
        JLabel disabledFatherLabel = new JLabel("บิดาพิการ:");
        disabledFatherLabel.setFont(thaiFont);
        add(disabledFatherLabel);
    
        disabledFatherCheckBox = new JCheckBox();
        disabledFatherCheckBox.setFont(thaiFont);
        add(disabledFatherCheckBox);
    
        JLabel disabledMotherLabel = new JLabel("มารดาพิการ:");
        disabledMotherLabel.setFont(thaiFont);
        add(disabledMotherLabel);
    
        disabledMotherCheckBox = new JCheckBox();
        disabledMotherCheckBox.setFont(thaiFont);
        add(disabledMotherCheckBox);
    
        JLabel disabledSpouseLabel = new JLabel("คู่สมรสพิการ:");
        disabledSpouseLabel.setFont(thaiFont);
        add(disabledSpouseLabel);
    
        disabledSpouseCheckBox = new JCheckBox();
        disabledSpouseCheckBox.setFont(thaiFont);
        add(disabledSpouseCheckBox);
    
        JLabel disabledChildLabel = new JLabel("บุตรพิการ:");
        disabledChildLabel.setFont(thaiFont);
        add(disabledChildLabel);
    
        disabledChildCheckBox = new JCheckBox();
        disabledChildCheckBox.setFont(thaiFont);
        add(disabledChildCheckBox);
    
        JLabel disabledChildCountLabel = new JLabel("จำนวนบุตรพิการ:");
        disabledChildCountLabel.setFont(thaiFont);
        add(disabledChildCountLabel);
    
        disabledChildCountField = new JTextField(5);
        disabledChildCountField.setFont(thaiFont);
        add(disabledChildCountField);
    
        JLabel totalDeductionLabel = new JLabel("ยอดลดหย่อนรวม:");
        totalDeductionLabel.setFont(thaiFont);
        add(totalDeductionLabel);
    
        totalDeductionField = new JTextField(10);
        totalDeductionField.setFont(thaiFont);
        totalDeductionField.setEditable(false);
        add(totalDeductionField);
    }
    

    private void addListeners() {
        hasChildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                childBefore2561Field.setEnabled(true);
                childAfter2561Field.setEnabled(true);
                updateTotalDeduction();
            }
        });

        noChildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                childBefore2561Field.setEnabled(false);
                childAfter2561Field.setEnabled(false);
                childBefore2561Field.setText("0");
                childAfter2561Field.setText("0");
                updateTotalDeduction();
            }
        });

        DocumentListener childDocumentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotalDeduction();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotalDeduction();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotalDeduction();
            }
        };

        childBefore2561Field.getDocument().addDocumentListener(childDocumentListener);
        childAfter2561Field.getDocument().addDocumentListener(childDocumentListener);

        ActionListener deductionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTotalDeduction();
            }
        };

        fatherDeductionCheckBox.addActionListener(deductionListener);
        motherDeductionCheckBox.addActionListener(deductionListener);
        disabledFatherCheckBox.addActionListener(deductionListener);
        disabledMotherCheckBox.addActionListener(deductionListener);
        disabledSpouseCheckBox.addActionListener(deductionListener);
        disabledChildCheckBox.addActionListener(deductionListener);

        disabledChildCountField.getDocument().addDocumentListener(childDocumentListener);
    }

    private void updateTotalDeduction() {
        int totalDeduction = 60000; // ค่าลดหย่อนส่วนบุคคลพื้นฐาน
        totalDeduction += calculateChildDeduction();
        totalDeduction += calculateParentDeduction();
        totalDeduction += calculateDisabledPersonDeduction();

        totalDeductionField.setText(String.format("%,d", totalDeduction));
    }

    private int calculateChildDeduction() {
        if (hasChildButton.isSelected()) {
            int childBefore2561 = Integer.parseInt(childBefore2561Field.getText().isEmpty() ? "0" : childBefore2561Field.getText());
            int childAfter2561 = Integer.parseInt(childAfter2561Field.getText().isEmpty() ? "0" : childAfter2561Field.getText());
            return (childBefore2561 * 30000) + (childAfter2561 * 60000);
        }
        return 0;
    }

    private int calculateParentDeduction() {
        int parentDeduction = 0;
        if (fatherDeductionCheckBox.isSelected()) parentDeduction += 30000;
        if (motherDeductionCheckBox.isSelected()) parentDeduction += 30000;
        return parentDeduction;
    }

    private int calculateDisabledPersonDeduction() {
        int disabledPersonCount = 0;
        if (disabledFatherCheckBox.isSelected()) disabledPersonCount++;
        if (disabledMotherCheckBox.isSelected()) disabledPersonCount++;
        if (disabledSpouseCheckBox.isSelected()) disabledPersonCount++;
        if (disabledChildCheckBox.isSelected()) {
            int disabledChildCount = Integer.parseInt(disabledChildCountField.getText().isEmpty() ? "0" : disabledChildCountField.getText());
            disabledPersonCount += disabledChildCount;
        }
        return disabledPersonCount * 60000;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaxDeductionCalculator();
            }
        });
    }
}