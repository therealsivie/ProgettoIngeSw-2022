package controller;

import model.baratto.Baratto;
import model.user.Utente;
import utility.JsonUtil;
import utility.MyMenu;

import java.util.ArrayList;
import java.util.List;

public class ModificaAppuntamento implements Action {
    @Override
    public Utente execute(Utente utente) throws ExitException {
        this.visualizzaAppuntamento(utente);
        return null;
    }

    private void visualizzaAppuntamento(Utente utente) {
        List<Baratto> barattoList = JsonUtil.readBarattoInScambio(utente.getUsername());
        MyMenu menu = new MyMenu("Scegli offerta");
        List<String> voci = new ArrayList<>();
        StringBuilder voce = new StringBuilder();
        for (Baratto baratto: barattoList){
            if (baratto.isRispondeA())
                System.out.println("A tocca a te");
            else
                System.out.println("B tocca a te");
        }

    }
}
