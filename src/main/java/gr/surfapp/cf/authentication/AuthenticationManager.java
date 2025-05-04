package gr.surfapp.cf.authentication;

import java.util.Arrays;

/**
 * A simple authentication manager to handle authentication logic.
 * <p>
 * This class performs a username and password check, simulating user authentication.
 * In a real-world scenario, passwords should be hashed and compared with stored hashed passwords.
 * </p>
 */

public class AuthenticationManager {

    private AuthenticationManager() {}

    public static boolean authenticate(String username, char[] password) {
        return username.matches("[aA]dmin") && Arrays.equals(password, "12345".toCharArray());
    }


}


