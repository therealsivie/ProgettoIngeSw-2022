import mylib.*;

import java.sql.Connection;

public class Administrator {
    private static String url = "jdbc:sqlite:/home/alessandro/ProgettoIngeSw-2022/Data.db";


    public static void main(String[] args) {
        Connect db = new Connect(url);
        Connection conn = db.connect();
        db.createNewTable(conn, "Utenti");
        String utente = InputDati.leggiStringaNonVuota("Inserire username: ");
        String password = InputDati.leggiStringaNonVuota("Inserire password");
        db.insert("Utenti", utente, password);
    }
}
