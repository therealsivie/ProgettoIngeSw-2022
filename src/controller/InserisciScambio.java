package controller;

import model.gerarchia.Gerarchia;
import model.scambio.Scambio;
import utility.InputDati;
import utility.JsonUtil;
import utility.MyMenu;

import java.util.ArrayList;
import java.util.List;

public class InserisciScambio implements Action {
    @Override
    public boolean execute() throws ExitException {
        this.inserisciScambio();
        return false;
    }

    private void inserisciScambio() {
        MyMenu menu = new MyMenu("Inserisci scambio");
        List<Gerarchia> gerarchiaList = JsonUtil.getGerarchieLibere();
        ArrayList<String> voci = new ArrayList<>();
        if( gerarchiaList.size() >= 1 ){
            for (Gerarchia gerarchia: gerarchiaList){
                voci.add(gerarchia.getNomeRadice());
            }
            menu.setVoci(voci);
            Gerarchia scelta = gerarchiaList.get(menu.scegli());
            String piazza = InputDati.leggiStringaNonVuota("Inserisci piazza: ");
            //creazione scambio
            Scambio scambio = new Scambio(scelta.getNomeRadice(), piazza);


            /**
             * dà la facoltà al configuratore di fissare il valore dei seguenti parametri:
             * - “Piazza”: la città in cui avvengono gli scambi: tale città è unica e, una volta stabilita, non può più cambiare;
             * - “Luoghi”: alcuni luoghi (al limite uno solo) in cui tali scambi sono effettuati;
             * - “Giorni”: il giorno o i giorni della settimana in cui gli scambi possono avere luogo;
             * - “Intervalli orari”: gli intervalli orari (almeno uno) entro cui effettuare gli scambi, dove
             *      i soli orari in corrispondenza dei quali si possono fissare appuntamenti finalizzati allo
             *      scambio di articoli fra le due parti coinvolte in un baratto sono quelli dello scoccare
             *      dell’ora e della mezz’ora;
             * - “Scadenza”: il numero massimo di giorni entro cui un fruitore può accettare una
             *      proposta di scambio avanzata da un altro fruitore
             */



            if(InputDati.yesOrNo("Salvare scambio? "))
                JsonUtil.writeScambio(scambio);
        }
        else
            System.out.println("\nNon sono presenti Gerarchie per cui inserire scambi...");
    }
}
