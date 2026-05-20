import java.io.*;
import java.util.*;

class ShopManager {
    private static final String FILE_NAME = "products.txt";

    
    private List<Product> products = new ArrayList<>();
    private List<Basket> basket = new ArrayList<>();

    
    public ShopManager() {
        loadFromFile();
    }

    public void addProduct(Product p) {
        try {
            products.add(p);
            saveToFile(FILE_NAME);
        } catch (Exception e) {
            System.out.println("Бараа нэмэхэд алдаа гарлаа: " + e.getMessage());
        }
    }

    public void removeProductAfterBuy(String id, int quantity) {
        try {
            for (Product p : products) {
                if (p.getId().equals(id)) {
                    int newQuantity = p.getQuantity() - quantity;
                    if (newQuantity >= 0) {
                        p.setQuantity(newQuantity);
                    }
                }
            }
            saveToFile(FILE_NAME);
        } catch (Exception e) {
            System.out.println("Худалдан авалтын дараа тоо хэмжээ хасахад алдаа гарлаа: " + e.getMessage());
        }
    }
    
    public void removeBugd() {
        try {
            basket.clear();
        } catch (Exception e) {
            System.out.println("Сагсыг цэвэрлэхэд алдаа гарлаа: " + e.getMessage());
        }
    }

    public void removeProductFromBasket(String id, int quantity) {
        try {
            Iterator<Basket> iterator = basket.iterator();
            while (iterator.hasNext()) {
                Basket b = iterator.next();
                if (b.getId().equals(id)) {
                    int newQuantity = b.getQuantity() - quantity;
                    if (newQuantity <= 0) {
                        iterator.remove(); // Safe removal using Iterator
                    } else {
                        b.setQuantity(newQuantity);
                    }
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Сагснаас бараа устгахад алдаа гарлаа: " + e.getMessage());
        }
    }

   
    public void addProductReturn(String id, int quantity) {
        try {
           
            for (Product p : products) {
                if (p.getId().equals(id)) {
                    int newQuantity = p.getQuantity() + quantity;
                    p.setQuantity(newQuantity);
                    break;
                }
            }
           
            saveToFile(FILE_NAME);

          
            Iterator<Basket> iterator = basket.iterator();
            while (iterator.hasNext()) {
                Basket b = iterator.next();
                if (b.getId().equals(id)) {
                    int newQuantity = b.getQuantity() - quantity;
                    if (newQuantity <= 0) {
                        iterator.remove();
                    } else {
                        b.setQuantity(newQuantity);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Бараа буцаан олгоход алдаа гарлаа: " + e.getMessage());
        }
    }

    public void removeProduct(String id) {
        try {
            products.removeIf(p -> p.getId().equals(id));
            saveToFile(FILE_NAME);
        } catch (Exception e) {
            System.out.println("Барааг устгахад алдаа гарлаа: " + e.getMessage());
        }
    }

    public void addProductToBasket(Basket p) {
        try {
            for (Basket b : basket) {
                if (b.getId().equals(p.getId())) {
                    int newQty = b.getQuantity() + p.getQuantity();
                    b.setQuantity(newQty);
                    System.out.println("Тоо шинэчлэгдлээ: " + b.getName() + " x" + newQty);
                    return;
                }
            }
            basket.add(p);
            System.out.println("Сагсанд нэмэгдлээ: " + p.getName() + " x" + p.getQuantity());
        } catch (Exception e) {
            System.out.println("Сагсанд бараа нэмэхэд алдаа гарлаа: " + e.getMessage());
        }
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
            products.clear(); // Давхардаж уншихаас сэргийлнэ
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
        } catch (IOException | NumberFormatException e) {
            System.out.println("Файл уншихад алдаа гарлаа: " + e.getMessage());
        }
    }

    // e. Хайлтын алгоритм (Search) - Нэрээр хайх
    public void searchByName(String name) {
        try {
            boolean found = false;
            for (Product p : products) {
                if (p.getName().equalsIgnoreCase(name)) {
                    p.displayInfo();
                    found = true;
                }
            }
            if (!found) System.out.println("Бараа олдсонгүй.");
        } catch (Exception e) {
            System.out.println("Хайлт хийхэд алдаа гарлаа: " + e.getMessage());
        }
    }

    public void showAll() {
        try {
            for (Product p : products) p.displayInfo();
        } catch (Exception e) {
            System.out.println("Жагсаалтыг харуулахад алдаа гарлаа: " + e.getMessage());
        }
    }
}