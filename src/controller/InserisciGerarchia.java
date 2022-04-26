package controller;

import utility.JsonUtil;
import utility.InputDati;
import model.structure.CampoNativo;
import model.structure.CategoriaPadre;
import model.structure.Gerarchia;

import java.util.ArrayList;

public class InserisciGerarchia implements Action {
    @Override
    public boolean execute() {
        inserisciGerarchia();
        return false;
    }

    private void inserisciGerarchia() {
        //nome gerarchia
        String nomeGerarchia = setNomeGerarchia();
        //descrizione
        String descrizione = InputDati.leggiStringaNonVuota("Inserire descrizione: ");
        //aggiunta campi nativi
        ArrayList<CampoNativo> campi = addCampiNativi();
        //creo nuova gerarchia
        Gerarchia gerarchia = new Gerarchia(new CategoriaPadre(nomeGerarchia, descrizione, campi, null));

        boolean save = InputDati.yesOrNo("Vuoi salvare la gerarchia inserita?");
        if (save) {
            JsonUtil.writeGerarchia(gerarchia);
            System.out.println("salvato");
        }
    }

    private ArrayList<CampoNativo> addCampiNativi(){
        ArrayList<CampoNativo> campi = new ArrayList<>();
        //campi nativi sempre presenti
        campi.add(new CampoNativo("Stato di conservazione", true));
        campi.add(new CampoNativo("Descrizione libera", false));
        //inserimento campi nativi
        boolean decisione = false;
        do {
            boolean insCampiNat = InputDati.yesOrNo("Vuoi inserire campi nativi? ");
            if (insCampiNat) {
                String nomeCampo = InputDati.leggiStringaNonVuota("Nome campo nativo: ");
                boolean obbligatorio = InputDati.yesOrNo("Il campo è obbligatorio? ");
                campi.add(new CampoNativo(nomeCampo, obbligatorio));
            } else
                decisione = true;
        } while (!decisione);

        return campi;
    }

    private String setNomeGerarchia() {
        boolean exitNomeRipetuto = true;
        String nomeGerarchia;
        do{
            nomeGerarchia = InputDati.leggiStringaNonVuota("Inserire nome gerarchia: ");
            if(!JsonUtil.checkNomeGerarchiaRipetuto(nomeGerarchia)){
                exitNomeRipetuto = false;
            }
            else {
                System.out.println("Nome della gerarchia già presente!");
            }
        }while (exitNomeRipetuto);
        return nomeGerarchia;
    }
}
