error id: file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/AdminGUI.java:java/lang/NumberFormatException#
file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/AdminGUI.java
empty definition using pc, found symbol in pc: java/lang/NumberFormatException#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 7931
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
        String[] columns = {"ID", "Нэр", "Тоо", "Үнэ",};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override 
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        add(new JScrollPane(table), BorderLayout.CENTER);

       
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));

        JButton btnAdd     = new JButton("+ Бараа нэмэх");
        JButton btnUpdate  = new JButton("Шинэчлэх / Засах");
        JButton btnDelete  = new JButton("Устгах");

        btnAdd.setBackground(new Color(40, 167, 69)); // Ногоон өнгө
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setOpaque(true);

       
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
                this, form, "Бараа нэмэх", JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {
                try {
                   
                    String id    = idField.getText().trim();
                    String name  = nameField.getText().trim();
                    double price = Double.parseDouble(priceField.getText().trim());
                    int qty      = Integer.parseInt(qtyField.getText().trim());

                    if (id.isEmpty() || name.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "ID эсвэл Нэр хоосон байж болохгүй!");
                        return;
                    }
                    boolean isDuplicate = false;
                    for (Product p : shop.getProducts()) {
                        // Энд p.getId() функц таны Product класс дотор байгаа гэж үзэв
                        if (p.getId().equalsIgnoreCase(id)) {
                            isDuplicate = true;
                            break;
                        }
                    }

                    if (isDuplicate) {
                        JOptionPane.showMessageDialog(this, "Алдаа: '" + id + "' ID-тай бараа аль хэдийн бүртгэгдсэн байна!", "Алдаа", JOptionPane.ERROR_MESSAGE);
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

   
        btnUpdate.addActionListener(e -> {
    int row = table.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Шинэчлэх бараагаа хүснэгтээс сонгоно уу!");
        return;
    }

    // 1. Сонгогдсон мөрийн ID-г авах
    String currentId = (String) tableModel.getValueAt(row, 0);
    
    // 2. Хүснэгтийн текстээс биш, ShopManager-ийн дата жагсаалтаас жинхэнэ барааг хайж олох
    Product selectedProduct = null;
    for (Product p : shop.getProducts()) {
        if (p.getId().equals(currentId)) {
            selectedProduct = p;
            break;
        }
    }

    // Хэрэв бараа олдсонгүй бол (аюулгүй байдлын үүднээс)
    if (selectedProduct == null) {
        JOptionPane.showMessageDialog(this, "Барааны мэдээлэл олдсонгүй!");
        return;
    }

    // 3. Dialog цонхны талбаруудыг форматын алдаагүй цэвэр датагаар дүүргэх
    JTextField nameField  = new JTextField(selectedProduct.getName());
    JTextField priceField = new JTextField(String.valueOf(selectedProduct.getPrice())); // Таслал, ₮ тэмдэггүй цэвэр тоо
    JTextField qtyField   = new JTextField(String.valueOf(selectedProduct.getQuantity()));

    JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
    form.add(new JLabel("Барааны нэр:")); form.add(nameField);
    form.add(new JLabel("Үнэ:"));         form.add(priceField);
    form.add(new JLabel("Тоо ширхэг:"));  form.add(qtyField);

    int result = JOptionPane.showConfirmDialog(
        this, form, "Барааны мэдээлэл шинэчлэх (ID: " + currentId + ")", JOptionPane.OK_CANCEL_OPTION
    );

    if (result == JOptionPane.OK_OPTION) {
        try {
            String newName  = nameField.getText().trim();
            String priceStr = priceField.getText().trim();
            String qtyStr   = qtyField.getText().trim();

            if (newName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Нэр хоосон байж болохгүй!");
                return;
            }

            // Хэрэглэгч гараас санамсаргүй таслал эсвэл ₮ тэмдэгт бичсэн бол цэвэрлэх хамгаалалт
            priceStr = priceStr.replace(",", "").replace("₮", "").replaceAll("\\s+", "");
            qtyStr   = qtyStr.replace(",", "").replaceAll("\\s+", "");

            double newPrice = Double.parseDouble(priceStr);
            int newQty      = Integer.parseInt(qtyStr);

            if (newPrice < 0 || newQty < 0) {
                JOptionPane.showMessageDialog(this, "Үнэ болон тоо хэмжээ хасах утга байж болохгүй!");
                return;
            }

            // Жагсаалт болон файлыг шинэчлэх
            shop.removeProduct(currentId);
            shop.addProduct(new Product(currentId, newName, newPrice, newQty));
            
            updateTable();
            JOptionPane.showMessageDialog(this, "Барааны мэдээлэл шинэчлэгдлээ!");
        } catch (Number@@FormatException ex) {
            JOptionPane.showMessageDialog(this, "Үнэ болон тоог зөвхөн тоон утгаар (жишээ нь: 15000) оруулна уу!\nҮсэг эсвэл тусгай тэмдэгт оруулж болохгүй.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Алдаа гарлаа: " + ex.getMessage());
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

empty definition using pc, found symbol in pc: java/lang/NumberFormatException#