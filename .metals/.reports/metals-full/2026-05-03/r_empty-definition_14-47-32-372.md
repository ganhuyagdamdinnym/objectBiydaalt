error id: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/.vscode/GUI/OrderCardGUI.java:Basket#
file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/.vscode/GUI/OrderCardGUI.java
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 2919
uri: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/.vscode/GUI/OrderCardGUI.java
text:
```scala
// package .vscode.GUI;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class OrderCardGUI extends JFrame {
     ShopManager shop = new ShopManager();
    private static final Color BG_COLOR     = new Color(255, 255, 255);
    private static final Color BORDER       = new Color(220, 220, 220);
    private static final Color BORDER_HOVER = new Color(170, 180, 200);
    private static final Color MUTED        = new Color(120, 120, 120);
    private static final Color DIVIDER      = new Color(230, 230, 225);


    public OrderCardGUI(Basket b,ShopManager shop){
        this.shop=shop;
        setLayout(new GridBagLayout());
        setBackground(BG_COLOR);
        // makeBorder(makeBorder(BORDER));
        setPreferredSize(new Dimension(200, 185));

        GridBagConstraints g = new GridBagConstraints();
        g.anchor  = GridBagConstraints.WEST;
        g.gridx   = 0;
        g.weightx = 1;
        g.fill    = GridBagConstraints.HORIZONTAL;

        // Badge
        g.gridy  = 0;
        g.insets = new Insets(0, 0, 6, 0);
        add(makeBadge(b.getType()), g);

        // Нэр
        g.gridy  = 1;
        g.insets = new Insets(0, 0, 2, 0);
        JLabel name = new JLabel(b.getName());
        name.setFont(new Font("SansSerif", Font.BOLD, 15));
        add(name, g);

        // ID
        g.gridy  = 2;
        g.insets = new Insets(0, 0, 8, 0);
        JLabel id = new JLabel("ID: " + b.getId());
        id.setFont(new Font("SansSerif", Font.PLAIN, 10));
        id.setForeground(MUTED);
        add(id, g);

        // Хуваагч шугам
        g.gridy  = 3;
        g.insets = new Insets(0, 0, 8, 0);
        JSeparator sep = new JSeparator();
        sep.setForeground(DIVIDER);
        add(sep, g);

        // Үнэ + тоо
        g.gridy  = 4;
        g.insets = new Insets(0, 0, 10, 0);
        add(makeBottom(b), g);

        // Сагсанд нэмэх товч
        g.gridy  = 5;
        g.insets = new Insets(0, 0, 0, 0);
        JButton btnCart = new JButton("Сагсанд нэмэх");
        btnCart.setFont(new Font("SansSerif", Font.PLAIN, 11));
        btnCart.setBackground(new Color(83, 74, 183));
        btnCart.setForeground(Color.WHITE);
        btnCart.setFocusPainted(false);
        btnCart.setBorderPainted(false);
        btnCart.setOpaque(true);
        btnCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCart.addActionListener(e -> MinusProductFromBasket(b));
        add(btnCart, g);

        // Hover
        // addMouseListener(new MouseAdapter() {
        //     @Override public void mouseEntered(MouseEvent e) { makeBorder(makeBorder(BORDER_HOVER)); }
        //     @Override public void mouseExited (MouseEvent e) { makeBorder(makeBorder(BORDER)); }
        // });
    }
    private void MinusProductFromBasket(Basket@@ p) {
    // Хэдэн ширхэг нэмэх вэ?
    String input = JOptionPane.showInputDialog(
        null,
        p.getName() + " - хэдэн ширхэг hasah вэ?",
        "Тоо оруулах",
        JOptionPane.QUESTION_MESSAGE
    );

    if (input == null) return; // Цуцлах дарвал

    try {
        int qty = Integer.parseInt(input.trim());
        if (qty <= 0) {
            JOptionPane.showMessageDialog(null, "0-ээс их тоо оруулна уу!");
            return;
        }
        if (qty > p.getQuantity()) {
            JOptionPane.showMessageDialog(null, "Үлдэгдэл хүрэлцэхгүй байна! (Үлдэгдэл: " + p.getQuantity() + " ш)");
            return;
        }
       
        String id=p.getId();
        String name=p.getName();
        double price=p.getPrice();
         shop.addProductToBasket(new Basket(id, name, price,qty));
        // JOptionPane.showMessageDialog(null, p.getName() + " x" + qty + " ширхэг сагсанд нэмэгдлээ!");

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Зөвхөн тоо оруулна уу!");
    }
}

    private JPanel makeBottom(Basket b) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_COLOR);

        JLabel price = new JLabel(String.format("%,.0f₮", b.getPrice()));
        price.setFont(new Font("SansSerif", Font.BOLD, 15));

        JLabel qty = new JLabel("<html><div style='text-align:right'>Үлдэгдэл<br><b>"
                + b.getQuantity() + " ш</b></div></html>");
        qty.setFont(new Font("SansSerif", Font.PLAIN, 11));
        qty.setForeground(MUTED);

        panel.add(price, BorderLayout.WEST);
        panel.add(qty,   BorderLayout.EAST);
        return panel;
    }

    private Border makeBorder(Color color) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 1, true),
            BorderFactory.createEmptyBorder(14, 16, 14, 16)
        );
    }

    private JLabel makeBadge(String type) {
        JLabel lbl = new JLabel(type) {
            @Override
            protected void paintComponent(Graphics g2) {
                Graphics2D g = (Graphics2D) g2.create();
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                   RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g.dispose();
                super.paintComponent(g2);
            }
        };
        lbl.setOpaque(false);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 11));
        lbl.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
        switch (type) {
            case "Electronic": lbl.setBackground(new Color(230,241,251));
                               lbl.setForeground(new Color(12,68,124));  break;
            case "Clothing":   lbl.setBackground(new Color(238,237,254));
                               lbl.setForeground(new Color(60,52,137));  break;
            case "Food":       lbl.setBackground(new Color(234,243,222));
                               lbl.setForeground(new Color(39,80,10));   break;
            default:           lbl.setBackground(new Color(241,239,232));
                               lbl.setForeground(new Color(68,68,65));
        }
        return lbl;
    }

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 