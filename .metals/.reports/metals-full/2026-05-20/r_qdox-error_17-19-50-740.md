error id: file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/ShopManager.java
file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/ShopManager.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[34,5]

error in qdox parser
file content:
```java
offset: 897
uri: file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/ShopManager.java
text:
```scala
import java.io.*;
import java.util.*;

class ShopManager {
    private static final String FILE_NAME = "products.csv";

    // d. Бүрдмэл харьцаа (Composition)
    private List<Product> products = new ArrayList<>();
    private List<Basket> basket = new ArrayList<>();

    // Байгуулагч - програм эхлэхэд файлаас уншина
    public ShopManager() {
        loadFromFile();
    }

    public void addProduct(Product p) {
        products.add(p);
        saveToFile(FILE_NAME);
    }

    public void removeProductAfterBuy(String id, int quantity) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                int newQuantity = p.quantity - quantity;
                if (newQuantity >= 0) {
                    p.setQuantity(newQuantity);
                }
            }
        }
        saveToFile(FILE_NAME);
    }
    
    p
    p@@ublic void removeProductFromBasket(String id, int quantity) {
        for (Basket b : basket) {
            if (b.getId().equals(id)) {
                int newQuantity = b.quantity - quantity;
                if (newQuantity <= 0) {
                    basket.remove(b);
                } else {
                    b.setQuantity(newQuantity);
                }
                return;
            }
        }
    }

    public void removeProduct(String id) {
        products.removeIf(p -> p.getId().equals(id));
        saveToFile(FILE_NAME);
    }

    public void addProductToBasket(Basket p) {
        for (Basket b : basket) {
            if (b.getId().equals(p.getId())) {
                int newQty = b.getQuantity() + p.getQuantity();
                basket.set(basket.indexOf(b), new Basket(b.getId(), b.getName(), b.getPrice(), newQty));
                System.out.println("Тоо шинэчлэгдлээ: " + b.getName() + " x" + newQty);
                return;
            }
        }
        basket.add(p);
        System.out.println("Сагсанд нэмэгдлээ: " + p.getName() + " x" + p.getQuantity());
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Basket> getBasketProducts() {
        return basket;
    }

    // a. Файлтай ажиллах (I/O) - Барааг файл руу хадгалах
    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product p : products) {
                writer.write(p.getId() + "," + p.getName() + "," + p.getPrice() + "," + p.getQuantity());
                writer.newLine();
            }
            System.out.println("Мэдээлэл амжилттай хадгалагдлаа.");
        } catch (IOException e) {
            System.out.println("Файлд бичихэд алдаа гарлаа: " + e.getMessage());
        }
    }

    // Файлаас уншина
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;
                String id    = parts[0];
                String name  = parts[1];
                double price = Double.parseDouble(parts[2]);
                int qty      = Integer.parseInt(parts[3]);
                products.add(new Product(id, name, price, qty));
            }
            System.out.println("Файлаас амжилттай уншлаа.");
        } catch (IOException e) {
            System.out.println("Файл уншихад алдаа гарлаа: " + e.getMessage());
        }
    }

    // e. Хайлтын алгоритм (Search) - Нэрээр хайх
    public void searchByName(String name) {
        boolean found = false;
        for (Product p : products) {
            if (p.name.equalsIgnoreCase(name)) {
                p.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("Бараа олдсонгүй.");
    }

    public void showAll() {
        for (Product p : products) p.displayInfo();
    }
}
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

QDox parse error in file:///C:/Users/user/OneDrive/Desktop/objectBiydaalt/ShopManager.java