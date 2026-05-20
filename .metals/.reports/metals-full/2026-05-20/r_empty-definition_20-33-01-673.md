error id: file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/AdminGUI.java:javax/swing/JOptionPane#OK_CANCEL_OPTION.
file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/AdminGUI.java
empty definition using pc, found symbol in pc: javax/swing/JOptionPane#OK_CANCEL_OPTION.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 3425
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
        setTitle("Админ хуудас");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Толгой хэсэг
        JLabel title = new JLabel("Админ хуудас", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setBackground(new Color(83, 74, 183));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        add(title, BorderLayout.NORTH);

        // Хүснэгт хэсэг
        String[] columns = {"ID", "Нэр", "Тоо", "Үнэ", "Төрөл"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override 
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Товчлуурууд байрлах панел
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));

        JButton btnAdd     = new JButton("+ Бараа нэмэх");
        JButton btnUpdate  = new JButton("Шинэчлэх / Засах");
        JButton btnDelete  = new JButton("Устгах");

        // Нэмэх товчны дизайн
        btnAdd.setBackground(new Color(40, 167, 69)); // Ногоон өнгө
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setOpaque(true);

        // Шинэчлэх товчны дизайн
        btnUpdate.setBackground(new Color(83, 74, 183)); // Хөх нил өнгө
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setOpaque(true);

        // Устгах товчны дизайн
        btnDelete.setBackground(new Color(220, 53, 69)); // Улаан өнгө
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);
        btnDelete.setBorderPainted(false);
        btnDelete.setOpaque(true);

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        add(btnPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> {
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
                this, form, "Бараа нэмэх", JOptionPane.OK_CANCEL_OP@@TION
            );

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String checkId=Product.getId();
                    String id    = idField.getText().trim();
                    String name  = nameField.getText().trim();
                    double price = Double.parseDouble(priceField.getText().trim());
                    int qty      = Integer.parseInt(qtyField.getText().trim());

                    if (id.isEmpty() || name.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "ID эсвэл Нэр хоосон байж болохгүй!");
                        return;
                    }

                    shop.addProduct(new Product(id, name, price, qty));
                    updateTable();
                    JOptionPane.showMessageDialog(this, "Бараа амжилттай нэмэгдлээ!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Үнэ болон тоог зөв тоон утгаар оруулна уу!");
                }
            }
        });

        // 2. БАРАА ШИНЭЧЛЭХ (ЗАСАХ)
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Шинэчлэх бараагаа хүснэгтээс сонгоно уу!");
                return;
            }

            // Сонгогдсон мөрийн мэдээллийг авах
            String currentId    = (String) tableModel.getValueAt(row, 0);
            String currentName  = (String) tableModel.getValueAt(row, 1);
            String currentQty   = String.valueOf(tableModel.getValueAt(row, 2));
            String currentPrice = String.valueOf(tableModel.getValueAt(row, 3));

            // Dialog цонхны талбаруудыг одоогийн утгаар дүүргэх
            JTextField nameField  = new JTextField(currentName);
            JTextField priceField = new JTextField(currentPrice);
            JTextField qtyField   = new JTextField(currentQty);

            JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
            form.add(new JLabel("Барааны нэр:")); form.add(nameField);
            form.add(new JLabel("Үнэ:"));        form.add(priceField);
            form.add(new JLabel("Тоо ширхэг:"));  form.add(qtyField);

            int result = JOptionPane.showConfirmDialog(
                this, form, "Барааны мэдээлэл шинэчлэх (ID: " + currentId + ")", JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String newName  = nameField.getText().trim();
                    double newPrice = Double.parseDouble(priceField.getText().trim());
                    int newQty      = Integer.parseInt(qtyField.getText().trim());

                    if (newName.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Нэр хоосон байж болохгүй!");
                        return;
                    }

                    
                    shop.removeProduct(currentId);
                    shop.addProduct(new Product(currentId, newName, newPrice, newQty));
                    
                    updateTable();
                    JOptionPane.showMessageDialog(this, "Барааны мэдээлэл шинэчлэгдлээ!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Үнэ болон тоог зөв оруулна уу!");
                }
            }
        });

        // 3. БАРАА УСТГАХ
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

        // Анхны байдлаар хүснэгтийг датагаар дүүргэх
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

empty definition using pc, found symbol in pc: javax/swing/JOptionPane#OK_CANCEL_OPTION.