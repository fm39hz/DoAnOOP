package team4.KitchenManager.View;

import javax.swing.*;
import javax.swing.border.*;

import team4.KitchenManager.Controller.InvoiceController;

import java.awt.*;

public class FeedbackView extends JPanel {
    private JTextPane Title;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;

    public FeedbackView() {
        initComponents();
        ReloadData();
        }
    
    public void ReloadData(){
        var _controller = new InvoiceController();
        var _invoices = _controller.getAll();
        for (var invoice : _invoices){
            var feedback = new Feedback(invoice.getCustomer().getFirstName() + " " + invoice.getCustomer().getLastName(), invoice.getCustomerFeedback());
            feedback.setPreferredSize(new Dimension(1278, 60));
            jPanel1.add(feedback);
            }
        }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        Title = new JTextPane();
        jScrollPane1 = new JScrollPane();
        jPanel1 = new JPanel();

        setMinimumSize(new Dimension(1278, 20));
        setPreferredSize(new Dimension(1280, 150));
        setLayout(new GridBagLayout());

        Title.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        Title.setText("Phản hồi khách hàng");
        Title.setEditable(false);
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

        jPanel1.setPreferredSize(new Dimension(1179, jPanel1.getMaximumSize().height));
        jPanel1.setLayout(new FlowLayout(BoxLayout.PAGE_AXIS, 0, 0));

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
