package bach.dev.foody.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class CartModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "thumbnail")
    public String thumbnail;
    @ColumnInfo(name = "price")
    public double price;
    @ColumnInfo(name = "quantity")
    public int quantity;
    @ColumnInfo(name = "category_id")
    public int categoryId;

    public CartModel(String name, String thumbnail, double price, int quantity, int categoryId) {
        this.name = name;

        this.thumbnail = thumbnail;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public CartModel() {
    }
}
