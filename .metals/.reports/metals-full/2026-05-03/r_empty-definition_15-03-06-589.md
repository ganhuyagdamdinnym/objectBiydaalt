error id: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/BasketGUI.java:_empty_/Product#
file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/BasketGUI.java
empty definition using pc, found symbol in pc: _empty_/Product#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 3593
uri: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/BasketGUI.java
text:
```scala
import javax.swing.*;
import java.awt.*;

public class BasketGUI extends JFrame {

    public BasketGUI(ShopManager shop) {
        setTitle("Миний сагс");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Сагс", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
        add(title, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(250, 250, 248));
        listPanel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        cardPanel.setBackground(new Color(250, 250, 248));
        JScrollPane cardScroll = new JScrollPane(cardPanel);
         cardScroll.setBorder(BorderFactory.createEmptyBorder());
        cardScroll.getVerticalScrollBar().setUnitIncrement(16);
        // Сагсны бараануудыг харуулах


        CardLayout cardLayout = new CardLayout();
        JPanel viewPanel = new JPanel(cardLayout);
        viewPanel.add(cardScroll,  "cards");
        add(viewPanel, BorderLayout.CENTER);

        java.util.List<Basket> items = shop.getBasketProducts();
        if (items.isEmpty()) {
            JLabel empty = new JLabel("Сагс хоосон байна");

            empty.setForeground(new Color(120, 120, 120));
            empty.setFont(new Font("SansSerif", Font.PLAIN, 13));
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(Box.createVerticalGlue());
            listPanel.add(empty);
            listPanel.add(Box.createVerticalGlue());
        } else {
            double total = 0;
            for (Basket b : items) {
                JLabel item = new JLabel(b.getName() + "  x" + b.getQuantity()
                        + "  —  " + String.format("%,.0f₮", b.getPrice() * b.getQuantity()));
                item.setFont(new Font("SansSerif", Font.PLAIN, 13));
                item.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
                listPanel.add(item);
                total += b.getPrice() * b.getQuantity();
            }
            // Нийт дүн
            JLabel totalLabel = new JLabel("Нийт: " + String.format("%,.0f₮", total));
            totalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            totalLabel.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));
            listPanel.add(totalLabel);
        }

        add(new JScrollPane(listPanel), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        JButton btnOrder = new JButton("Захиалах");
        btnOrder.setBackground(new Color(83, 74, 183));
        btnOrder.setForeground(Color.WHITE);
        btnOrder.setFocusPainted(false);
        btnOrder.setBorderPainted(false);
        btnOrder.setOpaque(true);
        btnOrder.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Захиалга амжилттай!")
        );

        bottomPanel.add(btnOrder, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        updateCards(cardPanel);

         private void updateCards(JPanel cardPanel) {
        cardPanel.removeAll();
        for (Pro@@duct p : manager.getProducts()) {
            cardPanel.add(new ProductCard(p,manager));
        }
        cardPanel.revalidate();
        cardPanel.repaint();
    }
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Product#