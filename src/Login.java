import mylib.*;

import java.sql.Connection;

public class Login {
    public static boolean checkLogin(String usr, String pwd) {
        Connect db = new Connect("jdbc:sqlite:/home/alessandro/ProgettoIngeSw-2022/Data.db");
        db.connect();
        db.selectAll("utenti", usr, pwd);

        return false;
    }
}
