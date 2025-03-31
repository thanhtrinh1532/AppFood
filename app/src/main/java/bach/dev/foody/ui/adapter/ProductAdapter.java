package bach.dev.foody.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

import bach.dev.foody.CartActivity;
import bach.dev.foody.ProductActivity;
import bach.dev.foody.R;
import bach.dev.foody.data.dto.ProductDto;
import bach.dev.foody.util.Constants;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<ProductDto> productList;

    public ProductAdapter(List<ProductDto> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductDto product = productList.get(position);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getThumbnail()).into(holder.ivThumbnail);

        // Sự kiện click vào ảnh sản phẩm
        holder.ivThumbnail.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductActivity.class);
            intent.putExtra(Constants.PRODUCT_ID, product.getId());
            v.getContext().startActivity(intent);
        });

        // Sự kiện click vào giỏ hàng
        holder.tvAddToCart.setOnClickListener(v -> {
            // Mở CartActivity khi click vào giỏ hàng
            Intent intent = new Intent(v.getContext(), CartActivity.class);
            // Truyền dữ liệu sản phẩm vào CartActivity (có thể thêm product ID nếu cần)
            intent.putExtra(Constants.PRODUCT_ID, product.getId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivThumbnail;
        public TextView tvName;
        public TextView tvPrice;
        public ImageView tvAddToCart;  // Đoạn này để tham chiếu đến ImageView giỏ hàng

        public ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.iv_product_item_thumbnail);
            tvName = itemView.findViewById(R.id.tv_product_item_name);
            tvPrice = itemView.findViewById(R.id.tv_product_item_price);
            tvAddToCart = itemView.findViewById(R.id.tv_add_to_cart);  // Gán ImageView cho giỏ hàng
        }
    }
}
