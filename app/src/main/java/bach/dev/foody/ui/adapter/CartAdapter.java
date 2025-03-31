package bach.dev.foody.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bach.dev.foody.R;
import bach.dev.foody.data.model.CartItem;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private List<CartItem> cartItemList;
    private OnCartItemChangeListener cartItemChangeListener;

    public interface OnCartItemChangeListener {
        void onCartUpdated();
    }

    public CartAdapter(Context context, List<CartItem> cartItemList, OnCartItemChangeListener listener) {
        this.context = context;
        this.cartItemList = cartItemList;
        this.cartItemChangeListener = listener;
    }

    @Override
    public int getCount() {
        return cartItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CartItem cartItem = cartItemList.get(position);

        holder.name.setText(cartItem.getName());
        holder.price.setText("$" + cartItem.getPrice());
        holder.quantity.setText(String.valueOf(cartItem.getQuantity()));

        Glide.with(context)
                .load(cartItem.getthumbnail())
                .placeholder(R.drawable.ic_cart)
                .into(holder.image);

        // Xử lý tăng số lượng
        holder.btnIncrease.setOnClickListener(v -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            notifyDataSetChanged();
            cartItemChangeListener.onCartUpdated();
        });

        // Xử lý giảm số lượng
        holder.btnDecrease.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                notifyDataSetChanged();
                cartItemChangeListener.onCartUpdated();
            }
        });

        // Xóa sản phẩm khỏi giỏ hàng
        holder.btnRemove.setOnClickListener(v -> {
            cartItemList.remove(position);
            notifyDataSetChanged();
            cartItemChangeListener.onCartUpdated();
        });

        return convertView;
    }

    static class ViewHolder {
        TextView name, price, quantity;
        ImageView image, btnIncrease, btnDecrease, btnRemove;

        ViewHolder(View view) {
            name = view.findViewById(R.id.cart_item_name);
            price = view.findViewById(R.id.cart_item_price);
            quantity = view.findViewById(R.id.cart_item_quantity);
            image = view.findViewById(R.id.cart_item_image);
            btnIncrease = view.findViewById(R.id.btn_increase);
            btnDecrease = view.findViewById(R.id.btn_decrease);
            btnRemove = view.findViewById(R.id.btn_remove);
        }
    }
}
