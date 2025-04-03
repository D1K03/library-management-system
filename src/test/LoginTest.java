import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Service.UserService;

public class LoginTest {

    /**
     * Tests authentication with incorrect username.
     */
    @Test
    public void testIncorrectEmail() {
        UserService userService = new UserService();
        assertFalse(userService.checkUserCredentials("john.sp@gmail.com", "johnsparrow101"));
    }



    /**
     * Tests authentication with incorrect password.
     */
    @Test
    public void testIncorrectPassword() {
        UserService userService = new UserService();
        assertFalse(userService.checkUserCredentials("john.sp2000@gmail.com", "543210"));
    }

    /**
     * Tests authentication with incorrect username and password.
     */
    @Test
    public void testBothIncorrectFields() {
        UserService userService = new UserService();
        assertFalse(userService.checkUserCredentials("jkgmail.com", "543210"));
    }

    /**
     * Tests authentication with correct username and password.
     */
    @Test
    public void testValidCredentials() {
        UserService userService = new UserService();
        assertTrue(userService.checkUserCredentials("john.sp2000@gmail.com", "johnsparrow101"));
    }
}
