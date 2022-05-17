package controller;

import model.baratto.Appuntamento;
import model.baratto.Baratto;
import model.offerta.Offerta;
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
        Baratto baratto = selezionaBaratto(utente);
        if (baratto == null)
            return;

        //chiedo conferma dopo aver mostrato le info dell'offerta
        Offerta offertaBaratto = baratto.getOffertaA();
        System.out.println(offertaBaratto);
        boolean accetta = InputDati.yesOrNo("sei sicuro di voler accettare l'offerta: " + offertaBaratto.getTitolo() + " ? ");

        Scambio scambio = JsonUtil.readScambio();

        if (!accetta)
            return;
        Appuntamento appuntamento = this.inserisciAppuntamento(scambio);
        baratto.setAppuntamento(appuntamento);
        JsonUtil.writeBaratto(baratto);
    }

    private Baratto selezionaBaratto(Utente utente) {
        List<Baratto> barattoList = JsonUtil.readBarattoByUtente(utente.getUsername());
        MyMenu menu = new MyMenu("Accetta Baratto o Esci");
        if (barattoList != null && barattoList.size() >= 1) {
            for (Baratto baratto : barattoList) {
                menu.addVoce(baratto.getOffertaA().getTitolo());
            }
            menu.addVoce("Esci senza accettare baratti");
        } else
            System.out.println("Non sono presenti Offerte nello stato Selezionato");

        //scelta dell'offerta da accettare
        int scelta = menu.scegli();
        //esci
        if (scelta == barattoList.size())
            return null;
        else
            return barattoList.get(scelta);
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
