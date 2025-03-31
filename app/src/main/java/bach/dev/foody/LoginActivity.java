package bach.dev.foody;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;

import bach.dev.foody.data.api.ApiService;
import bach.dev.foody.data.api.RetrofitClient;
import bach.dev.foody.data.dto.UserDto;
import bach.dev.foody.data.response.LoginResponse;
import retrofit2.Response;

//public class LoginActivity extends AppCompatActivity {
//    EditText etEmail, etPassword;
//    ImageView ivTogglePassword;
//    Button btnLogin;
//    TextView tvRegister;
//
//    boolean isPasswordVisible = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        etEmail = findViewById(R.id.etEmail);
//        etPassword = findViewById(R.id.etPassword);
//        ivTogglePassword = findViewById(R.id.ivTogglePassword);
//        btnLogin = findViewById(R.id.btnLogin);
//        tvRegister = findViewById(R.id.tvRegister);
//
//        // Xử lý ẩn/hiện mật khẩu
//        ivTogglePassword.setOnClickListener(v -> {
//            isPasswordVisible = !isPasswordVisible;
//
//            if (isPasswordVisible) {
//                etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                ivTogglePassword.setImageResource(R.drawable.ic_eye_open);
//            } else {
//                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                ivTogglePassword.setImageResource(R.drawable.ic_eye_closed);
//            }
//
//            etPassword.setSelection(etPassword.getText().length()); // Giữ vị trí con trỏ
//        });
//
//        // Xử lý sự kiện đăng nhập
//        btnLogin.setOnClickListener(v -> handleLogin());
//
//        // Xử lý chuyển hướng đến trang đăng ký
////        tvRegister.setOnClickListener(v -> {
////            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
////            startActivity(intent);
////        });
//    }
//
//    private void handleLogin() {
//        String email = etEmail.getText().toString().trim();
//        String password = etPassword.getText().toString().trim();
//
//        // Kiểm tra email và mật khẩu có hợp lệ không
//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Vui lòng nhập đầy đủ Email và Mật khẩu", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Giả lập dữ liệu đăng nhập (có thể thay bằng API hoặc Database)
////        String correctEmail = "user@example.com";
////        String correctPassword = "123456";
//        ApiService apiService = RetrofitClient.getApiService();
//        UserDto request = new UserDto(email, password);
//        apiService.login(request).enqueue(new Callback<LoginPresenter>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    LoginResponse loginResponse = response.body();
//                    if (loginResponse.isSuccess()) {
//                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        finish();
//                    } else {
//                        Toast.makeText(LoginActivity.this, "Lỗi: " + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(LoginActivity.this, "Lỗi máy chủ!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
//            }
//        });
//                                          }
////        if (email.equals(correctEmail) && password.equals(correctPassword)) {
////            // Đăng nhập thành công, chuyển sang SplashActivity
////            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
////            Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
////            startActivity(intent);
////            finish(); // Đóng màn hình login
////        } else {
////            // Sai thông tin đăng nhập, hiển thị thông báo lỗi
////            Toast.makeText(this, "Email hoặc Mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
////        }
//
//    }
//}
public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ImageView ivTogglePassword;
    boolean isPasswordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ivTogglePassword = findViewById(R.id.ivTogglePassword);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
// Xử lý ẩn/hiện mật khẩu
        ivTogglePassword.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;

            if (isPasswordVisible) {
                etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                ivTogglePassword.setImageResource(R.drawable.ic_eye_open);
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                ivTogglePassword.setImageResource(R.drawable.ic_eye_closed);
            }

            etPassword.setSelection(etPassword.getText().length()); // Giữ vị trí con trỏ
        });
        btnLogin.setOnClickListener(view -> loginUser());
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = RetrofitClient.getApiService();
        UserDto request = new UserDto(email, password);
        UserDto post = new UserDto("trinh@gmail.com", "123");
        apiService.login(new UserDto(email, password)).enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                if (post != null) {
                    UserDto user = response.body();
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
