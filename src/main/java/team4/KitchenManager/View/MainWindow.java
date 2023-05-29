package team4.KitchenManager.View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class MainWindow extends JFrame {
    private JMenuBar Menu = new JMenuBar();
    private JMenu File = new JMenu();
    private JMenu Help = new JMenu();
    private JMenuItem ExportCsv = new JMenuItem();
    private JMenuItem ImportCsv = new JMenuItem();
    private JTabbedPane MainView = new JTabbedPane();
    private JScrollPane Tab1 = new JScrollPane();
    private JScrollPane Tab2 = new JScrollPane();

    public MainWindow() {
        InitWindow();
        InitComponent();
        InitLayout();
        pack();
        }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
            } catch (Exception themeException) {
            themeException.printStackTrace();
            }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
                }
            });
        }
    
    private void InitWindow(){
        this.setName("MainFrame");
        this.setTitle("Kitchen Manager");
        this.setMinimumSize(new Dimension(1280, 720));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

    private void InitComponent() {
        MainView.addTab("Tab 1", Tab1);
        MainView.addTab("Tab 2", Tab2);

        Menu.setBorder(null);
        Menu.setMaximumSize(new Dimension(70, 23));

        File.setText("File");
        File.setToolTipText("Xuất nhập dữ liệu dưới dạng file");

        ExportCsv.setText("Export Csv");
        ExportCsv.setToolTipText("Xuất file dưới dạng csv");
        ExportCsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent buttonEvent) {
                //System.out.println("Export CSV");
                }
            });
        File.add(ExportCsv);

        ImportCsv.setText("ImportCsv");
        ImportCsv.setToolTipText("Nhập file dưới dạng csv");
        ImportCsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent buttonEvent) {
                //System.out.println("Import CSV");
                }
            });
        File.add(ImportCsv);

        Menu.add(File);

        Help.setText("Help");
        Menu.add(Help);

        this.setJMenuBar(Menu);
        }
    private void InitLayout(){
        var _layout = new GroupLayout(getContentPane());
            this.getContentPane().setLayout(_layout);
            _layout.setHorizontalGroup(
                _layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(MainView, GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE));
            _layout.setVerticalGroup(
                _layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(MainView, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE));
        }
    }
