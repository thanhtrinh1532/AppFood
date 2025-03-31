package bach.dev.foody.data.response;

public class LoginResponse {

        private boolean success;
        private String message;
        private String token;

        public boolean isLoggedIn() {
            return success;
        }

        public String getUserEmail() {
            return message;
        }

        public String getUserToken() {
            return token;
        }


    public String getMessage() {
            return "lỗi đăng nhập";
    }
}
