package controller;

import model.user.Utente;

public class PubblicaArticolo implements Action{
    @Override
    public Utente execute(Utente utente) throws ExitException {
        this.pubblicaArticolo(utente);
        return null;
    }

    private void pubblicaArticolo(Utente utente) {

    }
}
