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
        //aggiunta campi nativi da parte dell'utente
        campi.addAll(addCampiNativi());

        //creo categoria radice
        CategoriaPadre radice = new CategoriaPadre(nomeGerarchia, descrizione, campi, null);
        Gerarchia gerarchia = new Gerarchia(radice);

        //inserimento e creazione categorie figlie
        boolean inserimentoMinimo = false;
        do {
            System.out.println("Inserisci almeno 2 sottocategorie:");
            CategoriaPadre padre = this.scegliPadre(radice);
            int catInserite = 0;
            boolean inserisciCat = true;
            do {
                CategoriaFiglia figlia = this.inserisciFiglio(padre, catInserite);
                radice.addFiglio(figlia);
                gerarchia.addCategoria(figlia);
                catInserite++;
                if (catInserite >= 2) {
                    inserisciCat = InputDati.yesOrNo("Vuoi proseguire nell'inserimento di una sottocategoria di "+radice.getNome()+" ?");
                }
            } while (catInserite < 2 || inserisciCat);
            inserimentoMinimo = InputDati.yesOrNo("Vuoi inserire altre sottocategorie?");
        }while (inserimentoMinimo);
        System.out.println("Vuoi salvare la gerarchia inserita?\n");
        boolean save = InputDati.yesOrNo("");
        if (save) {
            JsonUtil.writeGerarchia(gerarchia);
            System.out.println("salvato");
        }

    }

    private String setNomeGerarchia() {
        boolean trovato = true;
        String nome;
        do {
            nome = InputDati.leggiStringaNonVuota("Inserisci nome Categoria radice: ");
            if (!JsonUtil.checkNomeGerarchiaRipetuto(nome)) {
                trovato = false;
            }
        } while (trovato);
        return nome;
    }

    private ArrayList<CampoNativo> addCampiNativi() {
        ArrayList<CampoNativo> campi = new ArrayList<>();
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

    private CategoriaPadre scegliPadre(CategoriaPadre radice) {
        MyMenu menu = new MyMenu("Scelta categoria padre");
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Categoria> strutturaCompleta = radice.getStrutturaCompleta();
        for (Categoria c : strutturaCompleta)
            temp.add(c.getNome());
        menu.setVoci(temp);
        //restituisce la categoria scelta nel menu (quella di cui si vuole inserire dei figli (almeno 2))
        Categoria scelta = strutturaCompleta.get(menu.scegli());
        if (scelta instanceof CategoriaFiglia)
            return ((CategoriaFiglia) scelta).convertiCategoria();
        else
            return (CategoriaPadre) scelta;
    }

    private CategoriaFiglia inserisciFiglio(CategoriaPadre padre, int num) {
        String nome = InputDati.leggiStringaNonVuota("nome sottocategoria " + num + ": ");
        String descrizione = InputDati.leggiStringaNonVuota("descrizione sottocategoria "+ num + ": ");
        ArrayList<CampoNativo> campi = new ArrayList<>();
        campi = addCampiNativi();
        return new CategoriaFiglia(nome, descrizione, campi, padre);
    }



        /*

        //creo nuova gerarchia (categoria radice)
        CategoriaPadre radice = new CategoriaPadre(nomeGerarchia, descrizione, campi, null);
        Gerarchia gerarchia = new Gerarchia(radice);

        //creazione categorie figlie della categoria radice
        System.out.println("Inserimento categorie figlie:");
        this.inserisciFigli(radice);

        //visualizzare un riepilogo prima di chiedere il salvataggio
        System.out.println("Vuoi salvare la gerarchia inserita? \nRiepilogo:\n");
        //System.out.println(gerarchia);
        boolean save = InputDati.yesOrNo("");

        if (save) {
            for (Categoria c: radice.getStrutturaCompleta())
                gerarchia.addCategoria(c);
            JsonUtil.writeGerarchia(gerarchia);
            System.out.println("salvato");
        }
    }

    private boolean inserisciFigli(CategoriaPadre radice) {
        int catInserite = 0;

        //inserimento categorie figlie (almeno 2)
        boolean esci = false;
        do {
            esci = this.inserisciSingolaCategoria(scelta);
            catInserite++;
        } while (catInserite < 2 || esci);
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
}