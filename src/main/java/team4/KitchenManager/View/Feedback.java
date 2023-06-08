package team4.KitchenManager.View;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

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
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = GridBagConstraints.VERTICAL;
            gridBagConstraints.ipadx = 120;
            gridBagConstraints.ipady = 63;
            gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
            gridBagConstraints.insets = new Insets(6, 6, 6, 0);
            add(this.CustomerName, gridBagConstraints);

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
