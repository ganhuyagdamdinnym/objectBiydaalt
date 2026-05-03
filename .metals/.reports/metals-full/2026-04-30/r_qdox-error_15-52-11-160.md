error id: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/ShopManager.java
file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/ShopManager.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[18,24]

error in qdox parser
file content:
```java
offset: 517
uri: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/ShopManager.java
text:
```scala
import java.io.*;
import java.util.*;

class ShopManager {
    // d. Бүрдмэл харьцаа (Composition) - ShopManager бараануудын жагсаалтыг агуулж байна
    private List<Product> products = new ArrayList<>();
    private List<Product> basket=new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }
    public void addProductToBasket(Product p){
        basket.add(p);
    }
    public List<Product> getProducts(){
        return products;
    }
    public List<Produc\t@@> getBasketProducts() { return basket; }

    // a. Файлтай ажиллах (I/O) - Барааг файл руу хадгалах
    public void saveToFile(String fileName) {
       
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product p : products) {
                writer.write(p.id + "," + p.name + "," + p.price);
                writer.newLine();
            }
            System.out.println("Мэдээлэл амжилттай хадгалагдлаа.");
        } catch (IOException e) {
            System.out.println("Файлд бичихэд алдаа гарлаа: " + e.getMessage());
        }
    }

    // e. Эрэмбэлэлт (Sorting) - Үнээр нь эрэмбэлэх
    public void sortByPrice() {
        products.sort(Comparator.comparingDouble(p -> p.price));
        System.out.println("Барааг үнээр нь эрэмбэллээ.");
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

QDox parse error in file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/ShopManager.java