error id: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/ShopGUI.java:java/awt/BorderLayout#
file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/ShopGUI.java
empty definition using pc, found symbol in pc: java/awt/BorderLayout#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1762
uri: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/ShopGUI.java
text:
```scala
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShopGUI extends JFrame {
    private ShopManager manager;
    private JTable table;
    private DefaultTableModel tableModel;

    public ShopGUI(ShopManager manager) {
        this.manager = manager;
        setTitle("Онлайн Дэлгүүрийн Систем");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"ID", "Нэр", "Тоо", "Үнэ", "Төрөл"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        setLayout(new BorderLayout());

        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        cardPanel.setBackground(new Color(250, 250, 248));
        JScrollPane cardScroll = new JScrollPane(cardPanel);
        cardScroll.setBorder(BorderFactory.createEmptyBorder());
        cardScroll.getVerticalScrollBar().setUnitIncrement(16);

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());

        CardLayout cardLayout = new CardLayout();
        JPanel viewPanel = new JPanel(cardLayout);
        viewPanel.add(cardScroll,  "cards");
        viewPanel.add(tableScroll, "table");
        add(viewPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnRefresh = new JButton("Шинэчлэх");
        JButton btnSort    = new JButton("Үнээр эрэмбэлэх asdA");
        JButton btnBasket  = new JButton("Sags Harah");

        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnSort);
        buttonPanel.add(btnBasket);
        add(buttonPanel, Bor@@derLayout.SOUTH);

        btnRefresh.addActionListener(e -> {
            updateTable();
            updateCards(cardPanel);
        });

        btnSort.addActionListener(e -> {
            manager.sortByPrice();
            updateTable();
            updateCards(cardPanel);
        });

        final boolean[] isCards = {true};
        btnBasket.addActionListener(e -> {
            isCards[0] = !isCards[0];
           
        });

        updateTable();
        updateCards(cardPanel);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Product p : manager.getProducts()) {
            tableModel.addRow(p.toTableRow());
        }
    }

    private void updateCards(JPanel cardPanel) {
        cardPanel.removeAll();
        for (Product p : manager.getProducts()) {
            cardPanel.add(new ProductCard(p));
        }
        cardPanel.revalidate();
        cardPanel.repaint();
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: java/awt/BorderLayout#