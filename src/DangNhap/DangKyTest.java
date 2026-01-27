package DangNhap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DangKyTest {

    // 1. Khởi tạo dữ liệu kiểm thử (Test Data)
    @DataProvider(name = "registrationData")
    public Object[][] registrationData() {
        return new Object[][] {
            { "existingUser", "WeakPass1", false }, // TH1: User đã tồn tại
            { "newUser", "weak", false },           // TH2: Mật khẩu yếu
            { "newUser", "StrongPass1", true }      // TH3: Đăng ký thành công
        };
    }

    // 2. Bài kiểm thử tự động sử dụng DataProvider
    @Test(dataProvider = "registrationData")
    public void testUserRegistration(String username, String password, boolean expectedOutcome) {
        // Gọi phương thức giả lập đăng ký và kiểm tra kết quả
        boolean actualOutcome = registerUser(username, password);
        
        // Xác minh kết quả thực tế có khớp với mong đợi không
        Assert.assertEquals(actualOutcome, expectedOutcome, "Kết quả đăng ký không khớp cho user: " + username);
    }

    // 3. Phương thức giả lập logic đăng ký (Backend Mock)
    private boolean registerUser(String username, String password) {
        // Giả sử "existingUser" đã có trong hệ thống
        if (username.equals("existingUser")) {
            return false;
        }
        
        // Kiểm tra mật khẩu mạnh: Độ dài >= 8, có chữ Hoa và có Số
        if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*")) {
            return false;
        }
        
        return true; // Hợp lệ
    }
}