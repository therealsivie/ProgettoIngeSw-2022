package controller;

import model.structure.Categoria;
import utility.JsonUtil;
import utility.InputDati;
import model.structure.CampoNativo;
import model.structure.CategoriaPadre;
import model.structure.Gerarchia;
import utility.MyMenu;

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

        //descrizione della gerarchia
        String descrizione = InputDati.leggiStringaNonVuota("Inserire descrizione: ");

        //aggiunta campi nativi  /////da ripensare pure questo
        //meglio aggiungere solo i campi standard e chiedere dopo per quelli aggiuntivi o sia prima che dopo?
        ArrayList<CampoNativo> campi = new ArrayList<>();
        //campi nativi sempre presenti
        campi.add(new CampoNativo("Stato di conservazione", true));
        campi.add(new CampoNativo("Descrizione libera", false));
        //aggiunta altri campi nativi
        this.addCampiNativi(campi);

        //creo nuova gerarchia (categoria radice)
        CategoriaPadre radice = new CategoriaPadre(nomeGerarchia, descrizione, campi, null);
        Gerarchia gerarchia = new Gerarchia(radice);

        //creazione categorie figlie
        boolean addFiglie = InputDati.yesOrNo("Vuoi proseguire con l'inserimento di categorie figlie?\n");
        if (addFiglie) {
            this.inserisciFigli(radice);
        }

        //visualizzare un riepilogo prima di chiedere il salvataggio
        System.out.println("Vuoi salvare la gerarchia inserita? \nRiepilogo:\n");
        System.out.println(gerarchia);
        boolean save = InputDati.yesOrNo("");

        if (save) {
            JsonUtil.writeGerarchia(gerarchia);
            System.out.println("salvato");
        }
    }

    private void inserisciFigli(CategoriaPadre radice) {
        MyMenu menu = new MyMenu("Seleziona categoria padre");
        if (radice.getFigli() == null) {
            menu.addVoce(radice.getNome());
        } else {
            for (Categoria c : radice.getFigli()) {
                if (c.isPadre()) {
                    menu.addVoce(c.getNome());
                }
            }
        }
        int scelta = menu.scegli();
    }

    private void addCampiNativi(ArrayList<CampoNativo> campi) {
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
    }

    private String setNomeGerarchia() {
        boolean exitNomeRipetuto = true;
        String nomeGerarchia;
        do {
            nomeGerarchia = InputDati.leggiStringaNonVuota("Inserire nome gerarchia: ");
            if (!JsonUtil.checkNomeGerarchiaRipetuto(nomeGerarchia)) {
                exitNomeRipetuto = false;
            } else {
                System.out.println("Nome della gerarchia già presente!");
            }
        } while (exitNomeRipetuto);
        return nomeGerarchia;
    }
}
