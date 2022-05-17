package controller;

import model.baratto.Appuntamento;
import model.offerta.Offerta;
import model.offerta.StatoOfferta;
import model.scambio.Scambio;
import model.user.Utente;
import utility.InputDati;
import utility.JsonUtil;
import utility.MyMenu;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AccettaBaratto implements Action {
    @Override
    public Utente execute(Utente utente) throws ExitException {
        this.accettaBaratto(utente);
        return null;
    }

    private void accettaBaratto(Utente utente) {

        //selezione offerta da accettare
        Offerta offertaDaAccettare = selezionaOfferta(utente);
        if (offertaDaAccettare == null)
            return;
        //chiedo conferma dopo aver mostrato le info dell'offerta
        System.out.println(offertaDaAccettare);
        boolean accetta = InputDati.yesOrNo("sei sicuro di voler accettare l'offerta: " + offertaDaAccettare.getTitolo() + " ? ");

        Scambio scambio = JsonUtil.readScambio();
        Appuntamento appuntamento;
        if (accetta)
            appuntamento = this.inserisciAppuntamento(scambio);


    }

    private Offerta selezionaOfferta(Utente utente) {
        List<Offerta> offerteSelezionate = JsonUtil.readOffertaByAutoreAndState(utente.getUsername(), StatoOfferta.SELEZIONATA);
        MyMenu menu = new MyMenu("Accetta Baratto o Esci");
        if (offerteSelezionate != null && offerteSelezionate.size() >= 1) {
            for (Offerta offerta : offerteSelezionate) {
                menu.addVoce(offerta.getTitolo());
            }
            menu.addVoce("Esci senza accettare baratti");
        } else
            System.out.println("Non sono presenti Offerte nello stato Selezionato");

        //scelta dell'offerta da accettare
        int scelta = menu.scegli();
        //esci
        if (scelta == offerteSelezionate.size())
            return null;
        else
            return offerteSelezionate.get(scelta);
    }

    private Appuntamento inserisciAppuntamento(Scambio scambio) {
        //mostro luoghi disponibili e scelgo
        MyMenu menuLuoghi = new MyMenu("scegli luogo");
        menuLuoghi.setVoci((ArrayList<String>) scambio.getLuoghi());
        String luogo = scambio.getLuoghi().get(menuLuoghi.scegli());

        System.out.println("luogo: " + luogo);

        //mostro giorni disponibili e scelgo
        MyMenu menuGiorni = new MyMenu("scegli giorno");
        ArrayList<String> giorni = new ArrayList<>();
        for (DayOfWeek giorno : scambio.getGiorni()) {
            giorni.add(giorno.name());
        }
        menuGiorni.setVoci(giorni);
        DayOfWeek giorno = DayOfWeek.valueOf(giorni.get(menuGiorni.scegli()));

        System.out.println("Giorno: " + giorno);

        //mostro orari disponibili e scelgo
        MyMenu menuOrari = new MyMenu("scegli orario");
        ArrayList<String> orari = scambio.getOrariScambio();
        menuOrari.setVoci(orari);
        String strOrario = orari.get(menuOrari.scegli());
        LocalTime orario = LocalTime.parse(strOrario);

        System.out.println("Orario: " + orario);

        return new Appuntamento(luogo, orario, giorno);
    }
}
