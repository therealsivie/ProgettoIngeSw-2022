package controller;

import model.Action;
import mylib.InputDati;
import structure.Gerarchia;

public class InserisciGerarchia implements Action {
    @Override
    public boolean execute() {
        inserisci();
        return false;
    }


    private void inserisci(){



        String nomeGerarchia = InputDati.leggiStringaNonVuota("Inserire nome gerarchia: ");
        String descrizione = InputDati.leggiStringaNonVuota("Inserire descrizione: ");
        Gerarchia g = new Gerarchia(nomeGerarchia, descrizione);

    }
}
