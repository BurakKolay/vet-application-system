package burakkolay.vetproject.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecuredPasswordGenerator {
    public static void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword="1912";
        String encodedPassword=encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}
