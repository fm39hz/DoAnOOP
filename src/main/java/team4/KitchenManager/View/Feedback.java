package team4.KitchenManager.View;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

public class Feedback extends JPanel {
    private JScrollPane FeedbackView = new JScrollPane();
    private JTextPane CustomerName = new JTextPane();
    private JTextPane FeedbackData = new JTextPane();

    public Feedback() {
        InitComponents();
        }
    public Feedback(String customer, String feedback){
        this.CustomerName.setText(customer);
        this.FeedbackData.setText(feedback);
        InitComponents();
        }
    
    private void InitComponents() {
        GridBagConstraints gridBagConstraints;

        CustomerName = new JTextPane();
        FeedbackView = new JScrollPane();
        FeedbackData = new JTextPane();

        setMinimumSize(new Dimension(200, 93));
        setLayout(new GridBagLayout());

        CustomerName.setEditable(false);
        CustomerName.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 24;
        gridBagConstraints.ipady = 63;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 6, 6, 0);
        add(CustomerName, gridBagConstraints);

        FeedbackData.setBorder(new javax.swing.border.LineBorder(new Color(0, 0, 0), 1, true));
        FeedbackView.setViewportView(FeedbackData);

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
        add(FeedbackView, gridBagConstraints);
        }
    }
