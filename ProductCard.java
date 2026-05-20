import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class ProductCard extends JPanel {
    private ShopManager shop;
    private ShopGUI shopGUI;

    private static final Color BG_COLOR     = new Color(255, 255, 255);
    private static final Color BORDER_NORM  = new Color(220, 220, 220);
    private static final Color BORDER_HOVER = new Color(83, 74, 183);
    private static final Color MUTED        = new Color(120, 120, 120);
    private static final Color DIVIDER      = new Color(230, 230, 225);
    private static final Color ACCENT       = new Color(83, 74, 183);

    // ShopGUI-тэй constructor
    public ProductCard(Product p, ShopManager shop, ShopGUI shopGUI) {
        this.shop    = shop;
        this.shopGUI = shopGUI;
        buildUI(p);
    }

    // Хуучин кодтой нийцтэй байлгах
    public ProductCard(Product p, ShopManager shop) {
        this(p, shop, null);
    }

    private void buildUI(Product p) {
        setLayout(new GridBagLayout());
        setBackground(BG_COLOR);
        setBorder(makeBorder(BORDER_NORM));
        setPreferredSize(new Dimension(210, 195));

        GridBagConstraints g = new GridBagConstraints();
        g.anchor  = GridBagConstraints.WEST;
        g.gridx   = 0;
        g.weightx = 1;
        g.fill    = GridBagConstraints.HORIZONTAL;

        // Badge
        g.gridy = 0; g.insets = new Insets(0, 0, 6, 0);
        add(makeBadge(p.getType()), g);

        // Нэр
        g.gridy = 1; g.insets = new Insets(0, 0, 2, 0);
        JLabel name = new JLabel(p.getName());
        name.setFont(new Font("SansSerif", Font.BOLD, 15));
        add(name, g);

        // ID
        g.gridy = 2; g.insets = new Insets(0, 0, 8, 0);
        JLabel id = new JLabel("ID: " + p.getId());
        id.setFont(new Font("SansSerif", Font.PLAIN, 10));
        id.setForeground(MUTED);
        add(id, g);

        // Хуваагч
        g.gridy = 3; g.insets = new Insets(0, 0, 8, 0);
        JSeparator sep = new JSeparator();
        sep.setForeground(DIVIDER);
        add(sep, g);

        // Үнэ + тоо
        g.gridy = 4; g.insets = new Insets(0, 0, 10, 0);
        add(makeBottom(p), g);

        // Товч
        g.gridy = 5; g.insets = new Insets(0, 0, 0, 0);
        JButton btnCart = new RoundedButton("Сагсанд нэмэх", ACCENT, Color.WHITE);
        btnCart.addActionListener(e -> addProductToBasket(p));
        add(btnCart, g);

        // Hover
        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { setBorder(makeBorder(BORDER_HOVER)); }
            @Override public void mouseExited (MouseEvent e) { setBorder(makeBorder(BORDER_NORM)); }
        });
    }

    private void addProductToBasket(Product p) {
        if (p.getQuantity() <= 0) {
            JOptionPane.showMessageDialog(this, "Энэ бүтээгдэхүүн дууссан байна!");
            return;
        }

        SpinnerNumberModel spinModel = new SpinnerNumberModel(1, 1, p.getQuantity(), 1);
        JSpinner spinner = new JSpinner(spinModel);
        spinner.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 8));
        panel.add(new JLabel(p.getName() + " — хэдэн ширхэг нэмэх вэ? (Үлдэгдэл: " + p.getQuantity() + " ш)"));
        panel.add(spinner);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Сагсанд нэмэх",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );
        if (result != JOptionPane.OK_OPTION) return;

        int qty = (int) spinner.getValue();
        shop.addProductToBasket(new Basket(p.getId(), p.getName(), p.getPrice(), qty));
        shop.removeProductAfterBuy(p.getId(), qty);

        if (shopGUI != null) shopGUI.refreshCards();

        JOptionPane.showMessageDialog(this, p.getName() + " x" + qty + " ширхэг сагсанд нэмэгдлээ!");
    }

    private JPanel makeBottom(Product p) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_COLOR);

        JLabel price = new JLabel(String.format("%,.0f₮", p.getPrice()));
        price.setFont(new Font("SansSerif", Font.BOLD, 15));

        JLabel qty = new JLabel("<html><div style='text-align:right'>Үлдэгдэл<br><b>"
                + p.getQuantity() + " ш</b></div></html>");
        qty.setFont(new Font("SansSerif", Font.PLAIN, 11));
        qty.setForeground(MUTED);

        panel.add(price, BorderLayout.WEST);
        panel.add(qty,   BorderLayout.EAST);
        return panel;
    }

    private Border makeBorder(Color color) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 1, true),
            new EmptyBorder(14, 16, 14, 16)
        );
    }

    private JLabel makeBadge(String type) {
        JLabel lbl = new JLabel(type) {
            @Override
            protected void paintComponent(Graphics g2) {
                Graphics2D g = (Graphics2D) g2.create();
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g.dispose();
                super.paintComponent(g2);
            }
        };
        lbl.setOpaque(false);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 11));
        lbl.setBorder(new EmptyBorder(3, 8, 3, 8));
        switch (type) {
            case "Electronic": lbl.setBackground(new Color(230,241,251)); lbl.setForeground(new Color(12,68,124));  break;
            case "Clothing":   lbl.setBackground(new Color(238,237,254)); lbl.setForeground(new Color(60,52,137));  break;
            case "Food":       lbl.setBackground(new Color(234,243,222)); lbl.setForeground(new Color(39,80,10));   break;
            default:           lbl.setBackground(new Color(241,239,232)); lbl.setForeground(new Color(68,68,65));
        }
        return lbl;
    }

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
            setFont(new Font("SansSerif", Font.PLAIN, 11));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getModel().isPressed() || getModel().isRollover() ? bg.darker() : bg);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
            g2.dispose();
            super.paintComponent(g);
        }
    }
}