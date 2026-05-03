error id: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/OrderCardGUI.java
file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/OrderCardGUI.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[125,39]

error in qdox parser
file content:
```java
offset: 4753
uri: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/OrderCardGUI.java
text:
```scala
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class OrderCardGUI extends JPanel {
    private ShopManager shop;
    private static final Color BG_COLOR     = new Color(255, 255, 255);
    private static final Color BORDER       = new Color(220, 220, 220);
    private static final Color BORDER_HOVER = new Color(170, 180, 200);
    private static final Color MUTED        = new Color(120, 120, 120);
    private static final Color DIVIDER      = new Color(230, 230, 225);

    public OrderCardGUI(Basket b, ShopManager shop) {
        this.shop = shop;
        setLayout(new GridBagLayout());
        setBackground(BG_COLOR);
        setBorder(makeBorder(BORDER));
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

        // Хасах товч
        g.gridy  = 5;
        g.insets = new Insets(0, 0, 0, 0);
        JButton btnRemove = new JButton("Хасах");
        btnRemove.setFont(new Font("SansSerif", Font.PLAIN, 11));
        btnRemove.setBackground(new Color(220, 53, 69));
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFocusPainted(false);
        btnRemove.setBorderPainted(false);
        btnRemove.setOpaque(true);
        btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRemove.addActionListener(e -> minusProductFromBasket(b));
        add(btnRemove, g);

        // Hover
        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { setBorder(makeBorder(BORDER_HOVER)); }
            @Override public void mouseExited (MouseEvent e) { setBorder(makeBorder(BORDER)); }
        });
    }

    private void minusProductFromBasket(Basket b) {
        String input = JOptionPane.showInputDialog(
            null,
            b.getName() + " - хэдэн ширхэг хасах вэ?",
            "Тоо оруулах",
            JOptionPane.QUESTION_MESSAGE
        );
        if (input == null) return;
        try {
            int qty = Integer.parseInt(input.trim());
            if (qty <= 0) {
                JOptionPane.showMessageDialog(null, "0-ээс их тоо оруулна уу!");
                return;
            }
            if (qty > b.getQuantity()) {
                JOptionPane.showMessageDialog(null, "Хасах тоо хэтэрлээ! (Үлдэгдэл: " + b.getQuantity() + " ш)");
                return;
            }
            shop.removeFromBasket(b.getId(), qty);
            JOptionPane.showMessageDialog(null, b.getName() + " x" + qty + " ширхэг хасагдлаа!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Зөвхөн тоо оруулна уу!");
        }
    }

    private JPanel makeBottom(Basket b) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_COLOR);
        JLabel price = new JLabel(String.format("%,.0f₮", b.getPrice() * b.getQuantity()));
        price.setFont(new Font("SansSerif", Font.BOLD, 15));
        JLabel qty = new JLabel("<html><div style='text-align:right'>Тоо<br><b>" + b.getQuantity() + " ш</b></div></html>");
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

    private JLabel makeBadge(String ty@@
```

```



#### Error stacktrace:

```
com.thoughtworks.qdox.parser.impl.Parser.yyerror(Parser.java:2025)
	com.thoughtworks.qdox.parser.impl.Parser.yyparse(Parser.java:2147)
	com.thoughtworks.qdox.parser.impl.Parser.parse(Parser.java:2006)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:232)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:190)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:94)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:89)
	com.thoughtworks.qdox.library.SortedClassLibraryBuilder.addSource(SortedClassLibraryBuilder.java:162)
	com.thoughtworks.qdox.JavaProjectBuilder.addSource(JavaProjectBuilder.java:174)
	scala.meta.internal.mtags.JavaMtags.indexRoot(JavaMtags.scala:49)
	scala.meta.internal.metals.SemanticdbDefinition$.foreachWithReturnMtags(SemanticdbDefinition.scala:99)
	scala.meta.internal.metals.Indexer.indexSourceFile(Indexer.scala:560)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3(Indexer.scala:691)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3$adapted(Indexer.scala:688)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:630)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:628)
	scala.collection.AbstractIterator.foreach(Iterator.scala:1313)
	scala.meta.internal.metals.Indexer.reindexWorkspaceSources(Indexer.scala:688)
	scala.meta.internal.metals.MetalsLspService.$anonfun$onChange$2(MetalsLspService.scala:940)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.concurrent.Future$.$anonfun$apply$1(Future.scala:691)
	scala.concurrent.impl.Promise$Transformation.run(Promise.scala:500)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1090)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:614)
	java.base/java.lang.Thread.run(Thread.java:1474)
```
#### Short summary: 

QDox parse error in file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/OrderCardGUI.java