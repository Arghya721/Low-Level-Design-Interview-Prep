package product;

public class Product{
    String name;
    int price;
    int id;
    int quantity;

    public Product(String name, int price, int id, int quantity){
        this.name = name;
        this.price = price;
        this.id = id;
        this.quantity = quantity;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public int getPrice(){
        return price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}