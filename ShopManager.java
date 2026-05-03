import java.io.*;
import java.util.*;

class ShopManager {
    // d. Бүрдмэл харьцаа (Composition) - ShopManager бараануудын жагсаалтыг агуулж байна
    private List<Product> products = new ArrayList<>();
    private List<Basket> basket=new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }
    public void addProductToBasket(Basket p){
        basket.add(p);
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