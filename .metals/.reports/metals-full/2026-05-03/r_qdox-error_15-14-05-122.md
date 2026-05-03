error id: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/BasketGUI.java
file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/BasketGUI.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[61,5]

error in qdox parser
file content:
```java
offset: 2641
uri: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/BasketGUI.java
text:
```scala
// import javax.swing.*;
// import java.awt.*;
// import java.util.List;

// public class BasketGUI extends JFrame {

//     public BasketGUI(ShopManager shop) {
//         setTitle("Миний сагс");
//         setSize(700, 500);
//         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//         setLocationRelativeTo(null);
//         setLayout(new BorderLayout());

//         JLabel title = new JLabel("Миний сагс", SwingConstants.CENTER);
//         title.setFont(new Font("SansSerif", Font.BOLD, 18));
//         title.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
//         add(title, BorderLayout.NORTH);

//         JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
//         cardPanel.setBackground(new Color(250, 250, 248));
//         JScrollPane cardScroll = new JScrollPane(cardPanel);
//         cardScroll.setBorder(BorderFactory.createEmptyBorder());
//         cardScroll.getVerticalScrollBar().setUnitIncrement(16);
//         add(cardScroll, BorderLayout.CENTER);

//         JLabel totalLabel = new JLabel("Нийт: 0₮");
//         totalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

//         JButton btnOrder = new JButton("Захиалах");
//         btnOrder.setBackground(new Color(83, 74, 183));
//         btnOrder.setForeground(Color.WHITE);
//         btnOrder.setFocusPainted(false);
//         btnOrder.setBorderPainted(false);
//         btnOrder.setOpaque(true);
//         btnOrder.addActionListener(e ->
//             JOptionPane.showMessageDialog(this, "Захиалга амжилттай!")
//         );

//         JPanel bottomPanel = new JPanel(new BorderLayout());
//         bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
//         bottomPanel.add(totalLabel, BorderLayout.WEST);
//         bottomPanel.add(btnOrder,   BorderLayout.EAST);
//         add(bottomPanel, BorderLayout.SOUTH);

//         // Сагсны бараануудыг харуулах
//         List<Basket> items = shop.getBasketProducts();
//         if (items.isEmpty()) {
//             JLabel empty = new JLabel("Сагс хоосон байна");
//             empty.setForeground(new Color(120, 120, 120));
//             empty.setFont(new Font("SansSerif", Font.PLAIN, 13));
//             cardPanel.add(empty);
//         } else {
//             double total = 0;
//             for (Basket b : items) {
//                 cardPanel.add(new OrderCardGUI(b, shop));
//                 total += b.getPrice() * b.getQuantity();
//             }
//             totalLabel.setText("Нийт: " + String.format("%,.0f₮", total));
//         }
//     }
// }@@
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

QDox parse error in file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/BasketGUI.java