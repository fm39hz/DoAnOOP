import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Kitchenmanager extends JFrame {
    private JMenuItem ExportCsv;
    private JMenu File;
    private JMenu Help;
    private JMenuItem ImportCsv;
    private JMenuBar Menu;
    private JScrollBar ScrollBar;
    private JScrollPane ViewPanel;

    public Kitchenmanager() {
        initComponents();
        }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
            } catch (Exception e) {
            e.printStackTrace();
            }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kitchenmanager().setVisible(true);
                }
            });
        }

    private void initComponents() {
        ViewPanel = new JScrollPane();
        ScrollBar = new JScrollBar();
        Menu = new JMenuBar();
        File = new JMenu();
        ExportCsv = new JMenuItem();
        ImportCsv = new JMenuItem();
        Help = new JMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kitchen Manager");
        setMinimumSize(new Dimension(1280, 720));
        setName("MainFrame");
        
        ViewPanel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent wheelEvent) {
                ScrollBar.setValue(ScrollBar.getValue() + (int)wheelEvent.getPreciseWheelRotation());
                }
            });

        Menu.setBorder(null);
        Menu.setMaximumSize(new Dimension(70, 23));

        File.setText("File");
        File.setToolTipText("Xuất nhập dữ liệu dưới dạng file");

        ExportCsv.setText("Export Csv");
        ExportCsv.setToolTipText("Xuất file dưới dạng csv");
        ExportCsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ExportCsvActionPerformed(evt);
                }
            });
        File.add(ExportCsv);

        ImportCsv.setText("ImportCsv");
        ImportCsv.setToolTipText("Nhập file dưới dạng csv");
        ImportCsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ImportCsvActionPerformed(evt);
                }
            });
        File.add(ImportCsv);

        Menu.add(File);

        Help.setText("Help");
        Menu.add(Help);

        setJMenuBar(Menu);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(ViewPanel, GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(ScrollBar, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(ViewPanel, GroupLayout.DEFAULT_SIZE, 542,
                            Short.MAX_VALUE)
                        .addComponent(ScrollBar, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()));

        pack();
        }
    }
