package bach.dev.foody;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import bach.dev.foody.ui.fragment.ChatFragment;
import bach.dev.foody.ui.fragment.FavouriteFragment;
import bach.dev.foody.ui.fragment.HomeFragment;
import bach.dev.foody.ui.fragment.SettingFragment;
import bach.dev.foody.util.Constants;

public class MainActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;
    private ImageView ivThumbnail;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, new HomeFragment())
                .commit();

// Lắng nghe sự thay đổi trong BackStack
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
            if (fragment instanceof HomeFragment) {
                View homeView = fragment.getView();
                if (homeView != null) {
                    ImageView ivCart = homeView.findViewById(R.id.iv_cart);
                    if (ivCart != null) {  // Kiểm tra tránh lỗi NullPointerException
                        ivCart.setOnClickListener(v -> {
                            startActivity(new Intent(MainActivity.this, CartActivity.class));
                        });
                    } else {
                        Log.e("MainActivity", "iv_cart không tồn tại trong HomeFragment!");
                    }
                }
            }
        });

// Ánh xạ ImageView ngoài Fragment (trong layout của MainActivity)
        ivThumbnail = findViewById(R.id.iv_cart);
        if (ivThumbnail != null) {
            ivThumbnail.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            });
        } else {
            Log.e("MainActivity", "iv_cart không tồn tại trong MainActivity!");
        }


// Xử lý sự kiện click vào ảnh (tránh gán sự kiện 2 lần)
        if (ivThumbnail != null) {
            ivThumbnail.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            });
        }

        initGUI();
    }

        private void initGUI() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemReselectedListener(item -> {
            Fragment selectedFragment = new HomeFragment();
            if(item.getItemId() == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            };
            if(item.getItemId() == R.id.nav_favorite) {
                selectedFragment = new FavouriteFragment();
            };
            if(item.getItemId() == R.id.nav_chat) {
                selectedFragment = new ChatFragment();
            };
            if(item.getItemId() == R.id.nav_setting) {
                selectedFragment = new SettingFragment();
            };
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
        });

        //gio hàng
//        if(Constants.manggiohang==null){
//            Constants.manggiohang = new ArrayList<>();
//        }
    }

}