package bach.dev.foody;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();

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
        if(Constants.manggiohang==null){
            Constants.manggiohang = new ArrayList<>();
        }
    }

}