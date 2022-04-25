package controller;

import data.DbConnect;
import model.Action;
import mylib.InputDati;
import structure.Utente;

public class LoginConf implements Action {
    DbConnect db = new DbConnect();
    private boolean doLogin() {
        String user = InputDati.leggiStringaNonVuota("Inserisci username: ");
        String pass = InputDati.leggiStringaNonVuota("Inserisci password: ");
        Utente u = db.checkLogin(user, pass);

        //controllo correttezza login
        if(u != null) {
            if (user.equals(u.getUsername()) && pass.equals(u.getPassword())) {
                //se first Login, procedura per il first Login, altrimenti si logga normalmente
                if (u.getFirstLogin()) {
                    return firstLogin(u);
                }
                return true;
            }
            System.out.println("Login Errato...");
            return false;
        }
        System.out.println("Login Errato...");
        return false;
    }

    private boolean firstLogin(Utente utente) {
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

    @Override
    public boolean execute() {
        return doLogin();
    }
}
