package team4.KitchenManager.View;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputAdapter;

import team4.KitchenManager.Model.Dish;

public class Dishes extends JPanel {
    private JTextPane DishDescription;
    private JPanel DishInformation;
    private JLabel DishName;
    private JLabel Image;
    private DishesInfoForm InfoForm;
    private JScrollPane ScrollableView;

    public Dishes() {
        InitComponents();
        }
    public Dishes(Dish dish){
        InitComponents();
        this.setDish(dish);
        }
    private void setDish(Dish dish){
        this.InfoForm = new DishesInfoForm(dish);
        this.DishDescription.setText(dish.getDescription());
        this.DishName.setText(dish.getName());
        this.Image.setIcon(new ImageIcon(dish.getImagePath()));
        this.DishName.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InfoForm.setVisible(true);
                }
            });
        }
    private void InitComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
    
        Image = new JLabel();
        DishInformation = new JPanel();
        ScrollableView = new JScrollPane();
        DishDescription = new JTextPane();
        DishName = new JLabel();
        InfoForm = null;
    
        this.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        this.setMaximumSize(new java.awt.Dimension(32767, 167));
    
        Image.setLabelFor(Image);
        Image.setText("Dishes Image");
        Image.setMaximumSize(new java.awt.Dimension(128, 128));
        Image.setMinimumSize(new java.awt.Dimension(128, 128));
        Image.setPreferredSize(new java.awt.Dimension(128, 128));
    
        DishInformation.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        DishInformation.setLayout(new java.awt.GridBagLayout());
    
        DishDescription.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ScrollableView.setViewportView(DishDescription);
    
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 230;
        gridBagConstraints.ipady = 96;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 1, 1, 1);
        DishInformation.add(ScrollableView, gridBagConstraints);
    
        DishName.setText("jLabel1");
        DishName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 13);
        DishInformation.add(DishName, gridBagConstraints);
    
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Image, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DishInformation, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
            );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(Image, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DishInformation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }
    }
