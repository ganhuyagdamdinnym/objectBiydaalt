// g. Интерфэйс
interface Displayable {
    void displayInfo();
}

// Үндсэн класс
class Product implements Displayable {
    protected String id;
    protected String name;
    protected double price;
    protected int quantity;

    // b. Байгуулагч функц (Constructor)
    public Product(String id, String name, double price,int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity=quantity;
    }

public String  getId()       { return id; }
public String  getName()     { return name; }
public double  getPrice()    { return price; }
public int     getQuantity() { return quantity; }
public String  getType()     { return "General"; } 
    @Override
    public void displayInfo() {
        System.out.println("ID: " + id + " | Нэр: " + name + " | Үнэ: " + price + "₮");
    }
    public Object[] toTableRow() {
        return new Object[]{id, name,quantity, price + "₮", "Ерөнхий"};
    }
}