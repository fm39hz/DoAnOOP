package team4.KitchenManager.View;

import javax.swing.*;
import javax.swing.border.*;


import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Feedback extends JPanel {
    private JTextPane CustomerName;
    private JTextPane FeedbackData;

    public Feedback() {
        this.InitComponents();
        }
    public Feedback(String customer, String feedback){
        InitComponents();
        this.CustomerName.setText(customer);
        this.FeedbackData.setText(feedback);
        }
    
    private void InitComponents() {
        GridBagConstraints gridBagConstraints;

        this.CustomerName = new JTextPane();
        this.FeedbackData = new JTextPane();

        this.setPreferredSize(new Dimension(1280, 120));
        this.setLayout(new GridBagLayout());

        this.CustomerName.setEditable(false);
        this.CustomerName.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
            // //TODO: add invoices view
            // var _attendancesView = new AttendancesView(employee);
            //     CustomerName.setFocusable(false);
            //     CustomerName.addMouseListener(new MouseListener() {
            //         @Override
            //         public void mouseClicked(MouseEvent arg0) {
            //             _attendancesView.setVisible(true);
            //         }
            //         @Override
            //         public void mouseEntered(MouseEvent arg0) {
            //             if (!_attendancesView.isVisible()){
            //                 CustomerName.setCursor(new Cursor(Cursor.HAND_CURSOR));
            //                 CustomerName.setForeground(Color.CYAN);
            //                 }
            //             }
            //         @Override
            //         public void mouseExited(MouseEvent arg0) {
            //                 CustomerName.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            //             CustomerName.setForeground(getForeground());
            //             }
            //         @Override
            //         public void mousePressed(MouseEvent arg0) {
            //         }
            //         @Override
            //         public void mouseReleased(MouseEvent arg0) {
            //         }
            //     });
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = GridBagConstraints.VERTICAL;
            gridBagConstraints.ipadx = 120;
            gridBagConstraints.ipady = 63;
            gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new Insets(6, 6, 6, 0);
            add(this.CustomerName, gridBagConstraints);

        this.FeedbackData.setEditable(false);
        this.FeedbackData.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.ipadx = 280;
            gridBagConstraints.ipady = 65;
            gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.insets = new Insets(6, 6, 6, 6);
            add(this.FeedbackData, gridBagConstraints);
        }
    }
