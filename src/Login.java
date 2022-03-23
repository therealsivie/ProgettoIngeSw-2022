import mylib.InputDati;
import structure.Configuratore;
import structure.Utente;

import java.sql.SQLException;

public class Login {
    static Connect db = new Connect();

    public boolean doLogin() {
        String user = InputDati.leggiStringaNonVuota("Inserisci username: ");
        String pass = InputDati.leggiStringaNonVuota("Inserisci password: ");
        Utente u = db.checkLogin(user, pass);
        //controllo correttezza login
        if (user.equals(u.getUsername()) && pass.equals(u.getPassword())) {
            //se first Login, procedura per il first Login, altrimenti loggato
            if (u.getFirstLogin()) {
                return firstLogin(u);
            }
            return true;
        }
        return false;
    }

    public boolean firstLogin(Utente utente) {
        String user = InputDati.leggiStringaNonVuota("Inserisci nuovo username: ");
        String pass = InputDati.leggiStringaNonVuota("Inserisci nuova password: ");
        db.updateCredentials(utente);
        System.out.println("Utente " + user + " modificato correttamente");
        return true;
    }
}
