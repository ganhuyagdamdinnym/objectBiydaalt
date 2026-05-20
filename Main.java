import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        ShopManager shop = new ShopManager();
        ShopGUI shopGUI = new ShopGUI(shop);
// new LoginGUI(shop, shopGUI).setVisible(true);

        // Бараа нэмэх
        // shop.addProduct(new Product("P001", "Laptop Bag", 45000,5));
        // shop.addProduct(new Electronics("E001", "Smartphone", 1200000, 12,9));
        // shop.addProduct(new Electronics("E002", "Mouse", 25000, 6,10));

        System.out.println("--- Бүх бараа ---");
        shop.showAll();

        // Эрэмбэлэх
        // shop.sortByPrice();
        shop.showAll();

        // Хайлт
        System.out.println("\n--- Хайлт хийх: Smartphone ---");
        shop.searchByName("Smartphone");

        // Файлд хадгалах
        shop.saveToFile("products.txt");


        SwingUtilities.invokeLater(() -> {
          new LoginGUI(shop,shopGUI).setVisible(true);
        });
       
    }
}