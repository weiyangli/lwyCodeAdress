package ssm.handle;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MIma {
    public static void main(String[] args) {
        PasswordEncoder B_CRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();
        String a = "{bcrypt}" + B_CRYPT_PASSWORD_ENCODER.encode("123456");
        System.out.println(a);
    }
}
