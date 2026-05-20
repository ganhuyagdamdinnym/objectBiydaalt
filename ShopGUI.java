import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

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

        // Bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.setBackground(new Color(250, 250, 248));

        // Left buttons
        JPanel leftButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        leftButtonsPanel.setOpaque(false);
        JButton btnSort   = new RoundedButton("Үнээр эрэмбэлэх", new Color(83, 74, 183), Color.WHITE);
        JButton btnBasket = new RoundedButton("Сагс харах",       new Color(83, 74, 183), Color.WHITE);
        leftButtonsPanel.add(btnSort);
        leftButtonsPanel.add(btnBasket);

        // Right button
        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightButtonsPanel.setOpaque(false);
        JButton btnExit = new RoundedButton("Гарах", new Color(220, 53, 69), Color.WHITE);
        rightButtonsPanel.add(btnExit);

        bottomPanel.add(leftButtonsPanel,  BorderLayout.WEST);
        bottomPanel.add(rightButtonsPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Listeners
        btnSort.addActionListener(e -> {
            // manager.sortByPrice();
            updateCards(cardPanel);
        });
        btnBasket.addActionListener(e -> new BasketGUI(manager).setVisible(true));
        btnExit.addActionListener(e -> dispose());

        updateCards(cardPanel);
    }

    private void updateCards(JPanel cardPanel) {
        cardPanel.removeAll();
        for (Product p : manager.getProducts()) {
            cardPanel.add(new ProductCard(p, manager));
        }
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    // ── Дугуй товч ───────────────────────────────────────────

    static class RoundedButton extends JButton {
        private final Color bg;

        RoundedButton(String text, Color bg, Color fg) {
            super(text);
            this.bg = bg;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
            setForeground(fg);
            setFont(new Font("SansSerif", Font.PLAIN, 13));
            setPreferredSize(new Dimension(getPreferredSize().width + 16, 34));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (getModel().isPressed()) {
                g2.setColor(bg.darker());
            } else if (getModel().isRollover()) {
                g2.setColor(bg.darker());
            } else {
                g2.setColor(bg);
            }
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
            g2.dispose();
            super.paintComponent(g);
        }
    }
}