error id: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/ShopManager.java:java/lang/String#
file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/ShopManager.java
empty definition using pc, found symbol in pc: java/lang/String#
found definition using semanticdb; symbol ShopManager#addProductToBasket().
empty definition using fallback
non-local guesses:

offset: 752
uri: file:///C:/Users/user/OneDrive/Desktop/obbiydaalt/ShopManager.java
text:
```scala
import java.io.*;
import java.util.*;

class ShopManager {
    // d. Бүрдмэл харьцаа (Composition) - ShopManager бараануудын жагсаалтыг агуулж байна
    private List<Product> products = new ArrayList<>();
    private List<Basket> basket=new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }



  public void removeProductFromBasket(String id, int quantity){
    for (Basket b:basket){
        if(b.getId().equals(id)){
            int newQuantity=b.quantity-quantity;
            if(newQuantity<=0){
                basket.remove(b);
            }else{
                b.setQuantity(newQuantity);
            }
            return;
        }
    } 


  }
  public void removeProduct(Stri@@ng id) {
    products.removeIf(p -> p.getId().equals(id));
}
  public void addProductToBasket(Basket p) {
    // Сагсанд аль хэдийн байгаа эсэхийг шалгах
    for (Basket b : basket) {
        if (b.getId().equals(p.getId())) {
            // Байвал тоог нэмэх
            int newQty = b.getQuantity() + p.getQuantity();
            basket.set(basket.indexOf(b), new Basket(b.getId(), b.getName(), b.getPrice(), newQty));
            System.out.println("Тоо шинэчлэгдлээ: " + b.getName() + " x" + newQty);
            return;
        }
    }
    // Байхгүй бол шинээр нэмэх
    basket.add(p);
    System.out.println("Сагсанд нэмэгдлээ: " + p.getName() + " x" + p.getQuantity());
}
    public List<Product> getProducts(){
        return products;
    }
    public List<Basket> getBasketProducts() { return basket; }

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


#### Short summary: 

empty definition using pc, found symbol in pc: java/lang/String#