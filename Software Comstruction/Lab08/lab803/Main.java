package lap803;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {

    public static void main(String[] args) {
    	int count = 1;
        JFrame frame = new JFrame("TEST");
        frame.setSize(250, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton jb = new JButton("Click");
        jb.addActionListener(this);
        JLabel countLabel = new JLabel("Click count: " + count);

        JPanel center = new JPanel();
        center.add(jb);
        center.add(countLabel);

        frame.getContentPane().add(center, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
