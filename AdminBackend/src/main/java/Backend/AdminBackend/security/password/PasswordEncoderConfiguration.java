package Backend.AdminBackend.security.password;

import Backend.AdminBackend.AdminBackendApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderConfiguration {

    @Bean
    CustomPasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }

    public static void main(String[] args){
        CustomPasswordEncoder bCryptPasswordEncoder = new CustomPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("sertifikat"));
    }
}


