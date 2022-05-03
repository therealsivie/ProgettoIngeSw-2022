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
        Categoria radice = new Categoria(nome, descrizione, campi, null);

        this.aggiungiCampi(radice);



        //inserimento categorie figlie
        boolean inserimentoMinimo = false;
        do {
            System.out.println("Inserisci almeno 2 sottocategorie:");
            Categoria padre = this.scegliPadre(radice);
            int catInserite = 0;
            boolean inserisciCat = true;
            do {
                padre.addSingoloFiglio(this.inserisciFiglio(catInserite+1, padre));
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

    private CampoNativo inserisciCampo(){
        String nomeCampo = InputDati.leggiStringaNonVuota("Nome campo nativo: ");;
        boolean obbligatorio = InputDati.yesOrNo("Il campo è obbligatorio? ");
        return new CampoNativo(nomeCampo, obbligatorio);
    }

    private void aggiungiCampi(Categoria categoria){
        boolean campoOk = false;
        boolean esci = false;
        do{
            CampoNativo campoNativo = inserisciCampo();
            if(categoria.checkCampoRipetuto(campoNativo.getNome()))
                System.out.println("Nome campo ripetuto");
            else
                categoria.addSingoloCampo(campoNativo);
            esci = InputDati.yesOrNo("Vuoi inserire campi nativi? ");
        }while (esci);
    }

    /*
    private ArrayList<CampoNativo> aggiungiCampi(Categoria padre) {
        ArrayList<CampoNativo> campi = new ArrayList<>();
        //inserimento campi nativi
        boolean decisione = false;
        boolean campoOk = false;
        do {
            boolean insCampiNativi = InputDati.yesOrNo("Vuoi inserire campi nativi? ");
            String nomeCampo;
            if (insCampiNativi) {
                nomeCampo =
                boolean obbligatorio = InputDati.yesOrNo("Il campo è obbligatorio? ");
                campi.add(new CampoNativo(nomeCampo, obbligatorio));
            } else
                decisione = true;
        } while (!decisione);
        return campi;
    }

    */
    private Categoria scegliPadre(Categoria radice) {
        MyMenu menu = new MyMenu("Scelta categoria padre");
        ArrayList<String> voci = radice.getStrutturaCompleta();
        menu.setVoci(voci);
        String nomeCatSelezionata = voci.get(menu.scegli());
        //restituisce la categoria scelta nel menu (quella di cui si vuole inserire dei figli (almeno 2))
        return radice.getCategoriaByNome(nomeCatSelezionata);
    }

    private Categoria inserisciFiglio(int num, Categoria padre) {
        String nome = InputDati.leggiStringaNonVuota("nome sottocategoria " + num + ": ");
        //controllo del campo
        String descrizione = InputDati.leggiStringaNonVuota("descrizione: ");
        ArrayList<CampoNativo> campi = new ArrayList<>();
        Categoria figlio = new Categoria(nome, descrizione, null, padre.getNome());
        this.aggiungiCampi(figlio);
        return figlio;
    }
}

/*
if(padre == null)
                    nomeCampo = InputDati.leggiStringaNonVuota("Nome campo nativo: ");
                else{
                    Categoria antenato = padre;
                    do{
                        nomeCampo = InputDati.leggiStringaNonVuota("Nome campo nativo: ");
                        antenato = padre.getCategoriaByNome(padre.getPadre());
                        campoOk = antenato.checkCampoRipetuto(nomeCampo);
                    }while (antenato != null && campoOk);
                }
 */