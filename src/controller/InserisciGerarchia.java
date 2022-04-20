package controller;

import data.DataWriter;
import model.Action;
import mylib.InputDati;
import structure.CampoNativo;
import structure.CategoriaPadre;
import structure.Gerarchia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InserisciGerarchia implements Action {
    @Override
    public boolean execute() {
        inserisciGerarchia();
        return false;
    }

    private void inserisciGerarchia() {
        boolean exit = false;
        String nomeGerarchia = InputDati.leggiStringaNonVuota("Inserire nome gerarchia: ");
        String descrizione = InputDati.leggiStringaNonVuota("Inserire descrizione: ");

        //creo nuova gerarchia
        Gerarchia gerarchia = new Gerarchia(new CategoriaPadre(nomeGerarchia, descrizione));

        //inserimento campi nativi
        boolean decisione = false;
        do {
            boolean insCampiNat = InputDati.yesOrNo("Vuoi inserire campi nativi? ");
            if (insCampiNat) {
                String nomeCampo = InputDati.leggiStringaNonVuota("Nome campo nativo: ");
                boolean obbligatorio = InputDati.yesOrNo("Il campo è obbligatorio? ");
                gerarchia.getCategoriaRadice().addCampo(new CampoNativo(nomeCampo, obbligatorio));
            }
            else
                decisione = true;
        } while (!decisione);
        boolean save = InputDati.yesOrNo("Vuoi salvare la gerarchia inserita?");
        if(save){
            try {
                DataWriter.writeGerarchia(gerarchia);
                System.out.println("salvato");
            } catch (IOException e) {
                System.out.println("Errore nel salvataggio della gerarchia");
            }
        }

    }

}
