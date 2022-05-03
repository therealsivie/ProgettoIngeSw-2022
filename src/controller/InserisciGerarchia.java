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
        String nome = this.inserisciNome();
        String descrizione = InputDati.leggiStringaNonVuota("Inserisci descrizione categoria: ");
        ArrayList<CampoNativo> campi = new ArrayList<>();
        //aggiunta campi obbligatori
        campi.add(new CampoNativo("Stato di conservazione", true));
        campi.add(new CampoNativo("Descrizione libera", false));
        //aggiunta campi da parte dell'utente
        campi.addAll(aggiungiCampi());

        Categoria radice = new Categoria(nome, descrizione, campi, null);

        //inserimento categorie figlie
        boolean inserimentoMinimo = false;
        do {
            System.out.println("Inserisci almeno 2 sottocategorie:");
            Categoria padre = this.scegliPadre(radice);
            int catInserite = 0;
            boolean inserisciCat = true;
            do {
                padre.addSingoloFiglio(this.inserisciFiglio(catInserite+1, padre.getNome()));
                catInserite++;
                if (catInserite >= 2) {
                    inserisciCat = InputDati.yesOrNo("Vuoi proseguire nell'inserimento di una sottocategoria di "+padre.getNome()+" ?");
                }
            } while (catInserite < 2 || inserisciCat);
            inserimentoMinimo = InputDati.yesOrNo("Vuoi inserire altre sottocategorie?");
        }while (inserimentoMinimo);
        Gerarchia gerarchia = new Gerarchia(radice);
        System.out.println("Vuoi salvare la gerarchia inserita?\n");
        boolean save = InputDati.yesOrNo("");
        if (save) {
            JsonUtil.writeGerarchia(gerarchia);
            System.out.println("salvato");
        }
    }

    private String inserisciNome() {
        boolean nomeRipetuto = true;
        String nome;
        do{
            nome = InputDati.leggiStringaNonVuota("Inserisci il nome della categoria radice: ");
            if(JsonUtil.checkNomeGerarchiaRipetuto(nome))
                System.out.println("Nome categoria radice già presente, inseriscine un altro");
            else
                nomeRipetuto = false;
        }while(nomeRipetuto);
        return nome;
    }

    private ArrayList<CampoNativo> aggiungiCampi() {
        ArrayList<CampoNativo> campi = new ArrayList<>();
        //inserimento campi nativi
        boolean decisione = false;
        do {
            boolean insCampiNat = InputDati.yesOrNo("Vuoi inserire campi nativi? ");
            if (insCampiNat) {
                String nomeCampo = InputDati.leggiStringaNonVuota("Nome campo nativo: ");
                //controllo campo
                boolean obbligatorio = InputDati.yesOrNo("Il campo è obbligatorio? ");
                campi.add(new CampoNativo(nomeCampo, obbligatorio));
            } else
                decisione = true;
        } while (!decisione);
        if(campi.size() == 0)
            return null;
        return campi;
    }


    private Categoria scegliPadre(Categoria radice) {
        MyMenu menu = new MyMenu("Scelta categoria padre");
        ArrayList<String> voci = radice.getStrutturaCompleta();
        menu.setVoci(voci);
        String nomeCatSelezionata = voci.get(menu.scegli());
        //restituisce la categoria scelta nel menu (quella di cui si vuole inserire dei figli (almeno 2))
        return radice.getCategoriaByNome(nomeCatSelezionata);
    }

    private Categoria inserisciFiglio(int num, String padre) {
        String nome = InputDati.leggiStringaNonVuota("nome sottocategoria " + num + ": ");
        //controllo del campo
        String descrizione = InputDati.leggiStringaNonVuota("descrizione: ");
        ArrayList<CampoNativo> campi = new ArrayList<>();
        campi = aggiungiCampi();
        return new Categoria(nome, descrizione, campi, padre);
    }
}