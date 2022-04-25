package controller;

import data.DbConnect;
import model.Action;
import model.ExitException;
import mylib.InputDati;
import structure.Utente;

public class LoginFruit implements Action {
    DbConnect db = new DbConnect();

    @Override
    public boolean execute() {
        return doLogin();
    }

    private boolean doLogin() {
        boolean firstLogin = InputDati.yesOrNo("Primo Login? ");
        boolean result;
        if(firstLogin)
            result= this.firstLogin();
        else
            result= this.normalLogin();
        return result;
    }

    private boolean normalLogin() {
        String user = InputDati.leggiStringaNonVuota("Inserisci username: ");
        String pass = InputDati.leggiStringaNonVuota("Inserisci password: ");
        Utente u = db.checkLogin(user, pass);
        if(u != null){
            if(user.equals(u.getUsername()) && pass.equals(u.getPassword()))
                return true;
            else
                return false;
        }
    }

    private boolean firstLogin() {
        return false;
    }
}
