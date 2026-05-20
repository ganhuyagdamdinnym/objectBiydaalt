import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ShopGUI extends JFrame {
    private ShopManager manager;
    private JPanel cardPanel;

    private static final Color BG_PAGE   = new Color(250, 250, 248);
    private static final Color BTN_COLOR = new Color(83, 74, 183);
    private static final Color BTN_EXIT  = new Color(220, 53, 69);

    public ShopGUI(ShopManager manager) {
        this.manager = manager;
        setTitle("Онлайн Дэлгүүрийн Систем");
        setSize(960, 600);
        setMinimumSize(new Dimension(700, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ── Карт панел (WrapLayout — мөрт таарахаар автоматаар зэрэгцдэг) ──
        cardPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 14, 14));
        cardPanel.setBackground(BG_PAGE);
        cardPanel.setBorder(new EmptyBorder(16, 16, 16, 16));

        JScrollPane cardScroll = new JScrollPane(cardPanel);
        cardScroll.setBorder(BorderFactory.createEmptyBorder());
        cardScroll.getViewport().setBackground(BG_PAGE);
        cardScroll.getVerticalScrollBar().setUnitIncrement(20);
        cardScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cardScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        cardScroll.getViewport().addComponentListener(new java.awt.event.ComponentAdapter() {
    @Override
    public void componentResized(java.awt.event.ComponentEvent e) {
        cardPanel.setPreferredSize(null); // өргөнийг дахин тооцуулна
        cardPanel.revalidate();
    }
});

        // ── Доод товчлуурын панел ──
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)),
            new EmptyBorder(10, 14, 10, 14)
        ));
        bottomPanel.setBackground(Color.WHITE);

        JPanel leftBtns = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        leftBtns.setOpaque(false);
        JButton btnSort   = new RoundedButton("↑↓  Үнээр эрэмбэлэх", BTN_COLOR, Color.WHITE);
        JButton btnBasket = new RoundedButton("🛒  Сагс харах",       BTN_COLOR, Color.WHITE);
        leftBtns.add(btnSort);
        leftBtns.add(btnBasket);

        JPanel rightBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightBtns.setOpaque(false);
        JButton btnExit = new RoundedButton("Гарах", BTN_EXIT, Color.WHITE);
        rightBtns.add(btnExit);

        bottomPanel.add(leftBtns,  BorderLayout.WEST);
        bottomPanel.add(rightBtns, BorderLayout.EAST);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(cardScroll,   BorderLayout.CENTER);
        getContentPane().add(bottomPanel,  BorderLayout.SOUTH);

        // ── Listeners ──
        btnSort.addActionListener(e -> {
            manager.getProducts().sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
            refreshCards();
        });
        btnBasket.addActionListener(e -> new BasketGUI(manager).setVisible(true));
        btnExit.addActionListener(e -> dispose());

        refreshCards();
    }

    public void refreshCards() {
        cardPanel.removeAll();
        for (Product p : manager.getProducts()) {
            cardPanel.add(new ProductCard(p, manager, this));
        }
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    // ── Дугуй товч ────────────────────────────────────────────
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
            setBorder(new EmptyBorder(8, 18, 8, 18));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getModel().isPressed() || getModel().isRollover() ? bg.darker() : bg);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ── WrapLayout: 4 карт мөрт багтахааргүй бол доод мөрт шилждэг ──
    static class WrapLayout extends FlowLayout {
        WrapLayout(int align, int hgap, int vgap) { super(align, hgap, vgap); }

        @Override public Dimension preferredLayoutSize(Container t) { return layoutSize(t, true);  }
        @Override public Dimension minimumLayoutSize(Container t)   { return layoutSize(t, false); }

        private Dimension layoutSize(Container target, boolean preferred) {
    synchronized (target.getTreeLock()) {
        // ── Эцэг контейнерийн (viewport) өргөнийг авна ──
        int targetWidth = target.getSize().width;
        Container parent = SwingUtilities.getUnwrappedParent(target);
        if (parent instanceof JViewport) {
            targetWidth = parent.getSize().width; // ← viewport өргөн
        }
        if (targetWidth == 0) targetWidth = 800; // fallback

        Insets insets = target.getInsets();
        int maxW = targetWidth - insets.left - insets.right;
        int x = 0, y = insets.top + getVgap(), rowH = 0;

        for (Component m : target.getComponents()) {
            if (!m.isVisible()) continue;
            Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();
            if (x != 0 && x + d.width > maxW) {
                y += rowH + getVgap();
                x = 0; rowH = 0;
            }
            x += d.width + getHgap();
            rowH = Math.max(rowH, d.height);
        }
        y += rowH + getVgap() + insets.bottom;
        return new Dimension(maxW, y);
    }
}
    }
}