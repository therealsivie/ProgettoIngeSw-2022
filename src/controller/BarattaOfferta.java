package controller;

import model.gerarchia.Categoria;
import model.offerta.Offerta;
import model.offerta.StatoOfferta;
import model.user.Utente;
import utility.JsonUtil;
import utility.MyMenu;

import java.util.List;

public class BarattaOfferta implements Action {
    @Override
    public Utente execute(Utente utente) throws ExitException {
        this.barattaOfferta(utente);
        return null;
    }

    private void barattaOfferta(Utente utente) {
        List<Offerta> offerteAperte = JsonUtil.readOffertaByAutoreAndState(utente.getUsername(), StatoOfferta.APERTA);
        MyMenu menu = new MyMenu("Scegli oggetto da barattare");
        if (offerteAperte != null && offerteAperte.size() >= 1) {
            for (Offerta offerta : offerteAperte) {
                menu.addVoce(offerta.getTitolo());
            }
            menu.addVoce("Esci senza barattare");
        } else
            System.out.println("Non sono presenti Offerte nello stato Aperto");
        //scelta dell'offerta da barattare
        int scelta = menu.scegli();
        //esci
        if (scelta == offerteAperte.size())
            return;

        Offerta offertaDaBarattare = offerteAperte.get(scelta);
        Offerta offertaScelta = this.scegliOffertaAltroAutore(utente, offertaDaBarattare.getCategoria());
        if (offertaScelta == null)
            return;

    }

    private Offerta scegliOffertaAltroAutore(Utente utente, Categoria categoria) {
        List<Offerta> offerte = JsonUtil.readOfferteApertebyCategoria(utente.getUsername(), categoria);
        MyMenu menu = new MyMenu("Scegli oggetto che vorresti");
        if (offerte.size() < 1) {
            System.out.println("Non sono presenti offerte aperte della stessa categoria");
        } else {
            for (Offerta offerta : offerte) {
                menu.addVoce(offerta.getTitolo()+"\t\tutente: "+offerta.getAutore());
            }
            menu.addVoce("Esci senza barattare");
            int scelta = menu.scegli();
            if (scelta == offerte.size())
                return null;
            return offerte.get(scelta);
        }
        return null;
    }
}
