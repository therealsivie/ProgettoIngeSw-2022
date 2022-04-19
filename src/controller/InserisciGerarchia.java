package controller;

import data.DataWriter;
import model.Action;
import mylib.InputDati;
import structure.CategoriaPadre;
import structure.Gerarchia;
import data.DataWriter;

public class InserisciGerarchia implements Action {
    @Override
    public boolean execute() {
        inserisciGerarchia();
        return false;
    }

    private void inserisciGerarchia(){
        String nomeGerarchia = InputDati.leggiStringaNonVuota("Inserire nome gerarchia: ");
        String descrizione = InputDati.leggiStringaNonVuota("Inserire descrizione: ");
        Gerarchia g = new Gerarchia(new CategoriaPadre(nomeGerarchia, descrizione));
        DataWriter.writeGerarchia(g);
    }

}
