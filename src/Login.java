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
        if(u != null) {
            if (user.equals(u.getUsername()) && pass.equals(u.getPassword())) {
                //se first Login, procedura per il first Login, altrimenti loggato
                if (u.getFirstLogin()) {
                    return firstLogin(u);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean firstLogin(Utente utente) {
        boolean credentialsChanged;
        String newUser;
        String newPass;
        do {
            newUser = InputDati.leggiStringaNonVuota("Inserisci nuovo username: ");
            newPass = InputDati.leggiStringaNonVuota("Inserisci nuova password: ");
            credentialsChanged = db.updateCredentials(utente, newUser, newPass);
            if (!credentialsChanged)
                System.out.println("Utente già presente, Inseriscine un altro");
        }
        while (!credentialsChanged);
        System.out.println("Utente " + newUser + " modificato correttamente");
        return credentialsChanged;
    }
}
