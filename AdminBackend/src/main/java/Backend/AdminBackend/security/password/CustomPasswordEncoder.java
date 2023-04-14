package Backend.AdminBackend.security.password;

import Backend.AdminBackend.security.Base64Utility;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        try {
            byte[] salt = HashSaltPassword.generateSalt();
            byte[] hashedPassword = HashSaltPassword.hashPassword(rawPassword.toString(),salt);
            return HashSaltPassword.formatiranjeCuvanjeSifre(hashedPassword,salt);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            byte[]tempSalt = HashSaltPassword.ekstrakcijaSalta(encodedPassword);
            return HashSaltPassword.authenticate(rawPassword.toString(), Base64Utility.decode(encodedPassword),tempSalt);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
}
