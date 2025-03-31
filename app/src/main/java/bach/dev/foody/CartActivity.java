package bach.dev.foody;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import bach.dev.foody.data.model.CartItem;
import bach.dev.foody.ui.adapter.CartAdapter;
import bach.dev.foody.util.Constants;
import bach.dev.foody.utils.CartManager;

public class CartActivity extends AppCompatActivity {
    private ListView cartListView;
    private TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cart_list_view);
        totalPrice = findViewById(R.id.total_price);

        List<CartItem> cartItems = CartManager.getInstance().getCartItems();

        CartAdapter adapter = new CartAdapter(this, cartItems, new CartAdapter.OnCartItemChangeListener() {
            @Override
            public void onCartUpdated() {
                totalPrice.setText("Tổng tiền: " + CartManager.getInstance().getTotalPrice() + " VNĐ");
            }
        });

        cartListView.setAdapter(adapter);
    }
}
