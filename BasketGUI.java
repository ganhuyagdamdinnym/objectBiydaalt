import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BasketGUI extends JFrame {

    public BasketGUI(ShopManager shop) {
        setTitle("Миний сагс");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Миний сагс", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
        add(title, BorderLayout.NORTH);

        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        cardPanel.setBackground(new Color(250, 250, 248));
        JScrollPane cardScroll = new JScrollPane(cardPanel);
        cardScroll.setBorder(BorderFactory.createEmptyBorder());
        cardScroll.getVerticalScrollBar().setUnitIncrement(16);
        add(cardScroll, BorderLayout.CENTER);

        JLabel totalLabel = new JLabel("Нийт: 0₮");
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnOrder = new JButton("Захиалах");
        btnOrder.setBackground(new Color(83, 74, 183));
        btnOrder.setForeground(Color.WHITE);
        btnOrder.setFocusPainted(false);
        btnOrder.setBorderPainted(false);
        btnOrder.setOpaque(true);
        btnOrder.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Захиалга амжилттай!")
        );

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        bottomPanel.add(totalLabel, BorderLayout.WEST);
        bottomPanel.add(btnOrder,   BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        updateCards(cardPanel, totalLabel, shop);
    }

    private void updateCards(JPanel cardPanel, JLabel totalLabel, ShopManager shop) {
        cardPanel.removeAll();

        List<Basket> items = shop.getBasketProducts();
        if (items.isEmpty()) {
            JLabel empty = new JLabel("Сагс хоосон байна");
            empty.setForeground(new Color(120, 120, 120));
            empty.setFont(new Font("SansSerif", Font.PLAIN, 13));
            cardPanel.add(empty);
            totalLabel.setText("Нийт: 0₮");
        } else {
            double total = 0;
            for (Basket b : items) {
                cardPanel.add(new OrderCardGUI(b, shop,
                    () -> updateCards(cardPanel, totalLabel, shop) // callback
                ));
                total += b.getPrice() * b.getQuantity();
            }
            totalLabel.setText("Нийт: " + String.format("%,.0f₮", total));
        }

        cardPanel.revalidate();
        cardPanel.repaint();
    }
}