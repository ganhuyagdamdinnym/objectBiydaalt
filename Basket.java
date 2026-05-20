public class Basket {

    protected String id;
    protected String name;
    protected double price;
    protected int quantity;
    
    public Basket(String id, String name,double price,int quantity){
        this.id=id;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }


    public void removeAll(){
        removeAll();
    }
 public String  getId()       { return id; }
public String  getName()     { return name; }
public double  getPrice()    { return price; }
public int     getQuantity() { return quantity; }
public String  getType()     { return "General"; } 
  
public void setQuantity(int quantity) { this.quantity = quantity; }
}