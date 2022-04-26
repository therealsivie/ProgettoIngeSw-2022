package controller;

import utility.DbConnect;
import utility.InputDati;
import model.user.Utente;

public class LoginFruit implements Action {
    DbConnect db = new DbConnect();

    @Override
    public boolean execute() {
        return doLogin();
    }

    private boolean doLogin() {
        boolean firstLogin = InputDati.yesOrNo("Primo Login? ");
        if (firstLogin)
            return this.firstLogin();
        else
            return this.normalLogin();
    }

    private boolean normalLogin() {
        String user = InputDati.leggiStringaNonVuota("Inserisci username: ");
        String pass = InputDati.leggiStringaNonVuota("Inserisci password: ");
        Utente u = db.checkLogin(user, pass);
        if (u != null) {
            if (user.equals(u.getUsername()) && pass.equals(u.getPassword()))
                return true;
            else {
                System.out.println("Login Errato...");
                return false;
            }
        }
        return false;
    }

    private boolean firstLogin() {
        boolean userOk = false;
        String user;
        String password;
        do{
            user = InputDati.leggiStringaNonVuota("Inserisci username: ");
            userOk = db.checkNewUser(user);
            if(!userOk)
                System.out.println("Username già presente");
            else
                userOk = true;
        }while(!userOk);
        boolean passOk = false;
        do {
            password = InputDati.leggiStringaNonVuota("Inserisci password: ");
            String ripeti = InputDati.leggiStringaNonVuota("Ripeti password: ");
            if(password.equals(ripeti))
                passOk = true;
            else
                System.out.println("Passowrd diverse...");
        }while(!passOk);
        db.insertUser(user, password, false, false);
        return true;
    }
}
