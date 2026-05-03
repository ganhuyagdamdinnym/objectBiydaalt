import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShopGUI extends JFrame {
    private ShopManager manager;
    private JTable table;
   

    public ShopGUI(ShopManager manager) {
        this.manager = manager;
        setTitle("Онлайн Дэлгүүрийн Систем");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       

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
        JButton btnSort    = new JButton("Үнээр эрэмбэлэх");
        JButton btnBasket  = new JButton("Sags Harah");

        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnSort);
        buttonPanel.add(btnBasket);
        add(buttonPanel, BorderLayout.SOUTH);

        btnRefresh.addActionListener(e -> {
           
            updateCards(cardPanel);
        });

        btnSort.addActionListener(e -> {
            manager.sortByPrice();
            
            updateCards(cardPanel);
        });

        
         btnBasket.addActionListener(e -> {
           new BasketGUI(manager).setVisible(true);
        });

       
        updateCards(cardPanel);
    }

 

    private void updateCards(JPanel cardPanel) {
        cardPanel.removeAll();
        for (Product p : manager.getProducts()) {
            cardPanel.add(new ProductCard(p,manager));
        }
        cardPanel.revalidate();
        cardPanel.repaint();
    }
}