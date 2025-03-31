package bach.dev.foody.data.model;

public class CartItem {
    private String productId;
    private String name;
    private double price;
    private int quantity;
    private String thumbnail;

    public CartItem(String productId, String name, double price, int quantity, String thumbnail) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.thumbnail = thumbnail;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getthumbnail() { return thumbnail; }

    public void setQuantity(int quantity) { this.quantity = quantity; }


}
