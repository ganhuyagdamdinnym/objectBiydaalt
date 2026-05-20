error id: file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/AdminGUI.java:javax/swing/JButton#
file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/AdminGUI.java
empty definition using pc, found symbol in pc: javax/swing/JButton#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1584
uri: file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/AdminGUI.java
text:
```scala
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminGUI extends JFrame {
    private ShopManager shop;
    private JTable table;
    private DefaultTableModel tableModel;

    public AdminGUI(ShopManager shop) {
        this.shop = shop;
        setTitle("Админ");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Толгой
        JLabel title = new JLabel("Админ хуудас", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setBackground(new Color(83, 74, 183));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        add(title, BorderLayout.NORTH);

        // Хүснэгт
        String[] columns = {"ID", "Нэр", "Тоо", "Үнэ", "Төрөл"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Товчлуурууд
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));

        JButton btnAdd    = new JButton("+ Бараа нэмэх");
        @@JButton btnDelete = new JButton("Устгах");
        JButton btnRefresh = new JButton("Шинэчлэх");

        btnAdd.setBackground(new Color(83, 74, 183));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setOpaque(true);

        btnDelete.setBackground(new Color(220, 53, 69));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);
        btnDelete.setBorderPainted(false);
        btnDelete.setOpaque(true);

        btnPanel.add(btnAdd);
        btnPanel.add(btnDelete);
        btnPanel.add(btnRefresh);
        add(btnPanel, BorderLayout.SOUTH);

        // Үйлдлүүд
        btnRefresh.addActionListener(e -> updateTable());

        btnAdd.addActionListener(e -> {
            // Бараа нэмэх dialog
            JTextField idField    = new JTextField();
            JTextField nameField  = new JTextField();
            JTextField priceField = new JTextField();
            JTextField qtyField   = new JTextField();

            JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
            form.add(new JLabel("ID:"));    form.add(idField);
            form.add(new JLabel("Нэр:"));   form.add(nameField);
            form.add(new JLabel("Үнэ:"));   form.add(priceField);
            form.add(new JLabel("Тоо:"));   form.add(qtyField);

            int result = JOptionPane.showConfirmDialog(
                this, form, "Бараа нэмэх", JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String id    = idField.getText().trim();
                    String name  = nameField.getText().trim();
                    double price = Double.parseDouble(priceField.getText().trim());
                    int qty      = Integer.parseInt(qtyField.getText().trim());

                    shop.addProduct(new Product(id, name, price, qty));
                    updateTable();
                    JOptionPane.showMessageDialog(this, "Бараа нэмэгдлээ!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Үнэ болон тоог зөв оруулна уу!");
                }
            }
        });

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Устгах барааг сонгоно уу!");
                return;
            }
            String id = (String) tableModel.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(
                this, "Устгах уу?", "Баталгаажуулах", JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                shop.removeProduct(id);
                updateTable();
            }
        });

        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Product p : shop.getProducts()) {
            tableModel.addRow(p.toTableRow());
        }
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: javax/swing/JButton#