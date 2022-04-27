package controller;

import model.structure.*;
import utility.JsonUtil;
import utility.InputDati;
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

        //aggiunta campi nativi
        ArrayList<CampoNativo> campi = new ArrayList<>();
        //campi nativi sempre presenti
        campi.add(new CampoNativo("Stato di conservazione", true));
        campi.add(new CampoNativo("Descrizione libera", false));
        //aggiunta altri campi nativi
        this.addCampiNativi(campi);

        //creo nuova gerarchia (categoria radice)
        CategoriaPadre radice = new CategoriaPadre(nomeGerarchia, descrizione, campi, null);
        Gerarchia gerarchia = new Gerarchia(radice);

        //creazione categorie figlie della categoria radice
        System.out.println("Inserimento categorie figlie:");
        this.inserisciFigli(radice);

        //visualizzare un riepilogo prima di chiedere il salvataggio
        System.out.println("Vuoi salvare la gerarchia inserita? \nRiepilogo:\n");
        System.out.println(gerarchia);
        boolean save = InputDati.yesOrNo("");

        if (save) {
            JsonUtil.writeGerarchia(gerarchia);
            System.out.println("salvato");
        }
    }

    private boolean inserisciFigli(CategoriaPadre radice) {
        int catInserite = 0;
        MyMenu menu = new MyMenu("Scelta categoria padre");
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Categoria> strutturaCompleta = radice.getStrutturaCompleta();
        for (Categoria c : strutturaCompleta)
            temp.add(c.getNome());
        menu.setVoci(temp);
        //restituisce la categoria scelta nel menu (quella di cui si vuole inserire dei figli (almeno 2))
        Categoria scelta = strutturaCompleta.get(menu.scegli());
        //inserimento categorie figlie (almeno 2)
        boolean esci = false;
        do {
            esci = this.inserisciSingolaCategoria(scelta);
            catInserite++;
        } while (catInserite < 2 || !esci);
        return false;
    }

    private boolean inserisciSingolaCategoria(Categoria categoria) {
        String nome = InputDati.leggiStringaNonVuota("Inserisci nome sottocategoria: ");
        String descrizione = InputDati.leggiStringaNonVuota("Inserisci descrizione: ");
        //aggiunta altri campi nativi
        ArrayList<CampoNativo> campi = new ArrayList<>();
        this.addCampiNativi(campi);
        if(categoria instanceof CategoriaPadre)
            ((CategoriaPadre)categoria).addFiglio(new CategoriaFiglia(nome, descrizione, (CategoriaPadre)categoria));
        else{
            CategoriaPadre cat = ((CategoriaFiglia)categoria).convertiCategoria(null);
            cat.addFiglio(new CategoriaFiglia(nome, descrizione, (CategoriaPadre)categoria));
        }
        return InputDati.yesOrNo("vuoi proseguire con l'inserimento di una sottocategoria ");
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


/*
boolean addFiglie = InputDati.yesOrNo("Vuoi proseguire con l'inserimento di categorie?\n" +
                "(Nota: una categoria deve avere 2 o più sottocategorie): ");
        if(!addFiglie)
            return false;
        MyMenu menu = new MyMenu("Seleziona categoria padre");
        if (radice.getFigli() == null) {
            menu.addVoce(radice.getNome());
        } else {
            for (Categoria c : radice.getFigli()) {
                menu.addVoce(c.getNome());
            }
        }
        int scelta = menu.scegli();
        String nomeCatPadre = menu.getVoce(scelta);
        System.out.println("Hai scelto " + nomeCatPadre);
        while(true){
            this.inserisciFigli(radice.get(nomeCatPadre));
        }
 */