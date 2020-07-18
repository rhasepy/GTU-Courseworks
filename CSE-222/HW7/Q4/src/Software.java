public class Software implements Comparable<Software>, Countable
{
    private String name;
    private int quantity;
    private double price;

    public Software(String name, double price) {
        this.name = name;
        this.quantity = 1;
        this.price = price;
    }

    @Override
    public int compareTo(Software software) { return this.getName().compareTo(software.getName()); }

    public void setName(String newName) { this.name = newName; }
    public void setQuantity(int newQuantitiy) { this.quantity = newQuantitiy; }
    public void setPrice(double newPrice) { this.price = newPrice; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    @Override
    public void setCount(int x) { this.setQuantity(x); }
    @Override
    public int getCount() { return this.getQuantity(); }
    @Override
    public void incrementCount() { this.setQuantity(getQuantity() + 1); }
    @Override
    public void decrementCount() { this.setQuantity(getQuantity() - 1); }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Quantitiy: " + quantity + "\n" +
                "Price: " + "$" + price + "\n";
    }
}
