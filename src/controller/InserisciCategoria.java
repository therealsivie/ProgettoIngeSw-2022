package controller;

import model.Action;
import model.ExitException;
import mylib.InputDati;
import structure.Categoria;
import structure.Gerarchia;

public class InserisciCategoria implements Action {
    @Override
    public boolean execute() throws ExitException {
        inserisciCategoria();
        return false;
    }

    private void inserisciCategoria() {
        String nomeCategoria = InputDati.leggiStringaNonVuota("Inserire nome categoria: ");
        String descrizione = InputDati.leggiStringaNonVuota("Inserire descrizione: ");
        //serve la scelta su dove inserire la categoria (gerarchia di riferimento e categoria padre)
        Categoria c = new Categoria(nomeCategoria, descrizione);
    }
}
