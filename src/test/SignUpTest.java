import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import Service.UserService;
import Authentication.SignUp;
import org.junit.jupiter.api.Test;

public class SignUpTest {

    /**
     * Tests input into database with some valid inputs
     */
    @Test
    public void testEmptyFields() {
        UserService userService = new UserService();

        String firstName = "";
        String lastName = "";
        String email = "";
        String pass  = "";
        String role = "";
        Timestamp regDate = Timestamp.valueOf(LocalDateTime.now());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            assertNotEquals(-1, userService.addUser(firstName, lastName, email, role, regDate, pass));
        });
    }

    /**
     * Tests input into database with some valid inputs.
     */
    @Test
    public void testSomeEmptyFields() {
        UserService userService = new UserService();

        String firstName = "";
        String lastName = "Kennedy";
        String email = "jk2015@gmail.com";
        String role = "";
        String pass  = "";
        Timestamp regDate = Timestamp.valueOf(LocalDateTime.now());

        assertThrows(IllegalArgumentException.class, () -> {
            assertNotEquals(-1, userService.addUser(firstName, lastName, email, role, regDate, pass));
        });
    }

    /**
     * Tests input into the database with all valid inputs.
     */
    @Test
    public void testValidFields() {
        UserService userService = new UserService();

        String firstName = "Jane";
        String lastName = "Doe";
        String email = "JaneDoe123@gmail.com";
        String pass  = "1234567";
        String role = "librarian";
        Timestamp regDate = Timestamp.valueOf(LocalDateTime.now());

        assertNotEquals(-1, userService.addUser(firstName, lastName, email, role, regDate, pass));
    }
}
