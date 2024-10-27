package Authentication;

import java.util.HashMap;

public class UserID {
    HashMap<String, String> LoginData = new HashMap<String, String>();

    public UserID() {

    }

    private void setLoginData(String username, String password) {
        LoginData.put(username, password);
    }

    protected HashMap<String, String> getLoginData() {
        return LoginData;
    }
}
