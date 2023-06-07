package team4.KitchenManager.View;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class FeedbackView extends JPanel {
    private JTextPane Title;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;

    public FeedbackView() {
        initComponents();
        }
    
    public void ReloadData(){
        var a = 3;
        for (int i = 0; i < a; i++){
            //TODO: lấy dữ liệu từ InvoiceController
            jPanel1.add(new Feedback("customerName", "feedback"));
            }
        this.revalidate();
        }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        Title = new JTextPane();
        jScrollPane1 = new JScrollPane();
        jPanel1 = new JPanel();

        setMinimumSize(new Dimension(1278, 20));
        setPreferredSize(new Dimension(1280, 150));
        setLayout(new GridBagLayout());

        // Title.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        Title.setText("Phản hồi khách hàng");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 1212;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 6);
        add(Title, gridBagConstraints);

        jScrollPane1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        jScrollPane1.setViewportBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        jScrollPane1.setPreferredSize(new Dimension(200, 120));

        jPanel1.setPreferredSize(new Dimension(1179, 270));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 1260, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1256;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(6, 0, 6, 6);
        add(jScrollPane1, gridBagConstraints);
        }
    }
