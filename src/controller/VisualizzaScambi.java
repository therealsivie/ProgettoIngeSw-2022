package controller;

import model.gerarchia.Gerarchia;
import model.scambio.IntervalloOrario;
import model.scambio.Scambio;
import model.user.Utente;
import utility.JsonUtil;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VisualizzaScambi implements Action {
    @Override
    public Utente execute(Utente utente) throws ExitException {
        this.visualizzaScambi();
        return null;
    }

    private void visualizzaScambi() {
        //metodo per visualizzare gli scambi
        List<Scambio> scambi = JsonUtil.readScambi();
        List<String> nomiGerarchie = new ArrayList<>();
        for (Scambio scambio : scambi)
            nomiGerarchie.add(scambio.getNomeGerarchiaScambio());
        List<Gerarchia> gerarchie = JsonUtil.readGerarchieByName(nomiGerarchie);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < scambi.size(); i++) {
            Scambio scambio = scambi.get(i);
            Gerarchia gerarchia = gerarchie.get(i);

            stringBuilder.append("\nNome: ").append(gerarchia.getNomeRadice())
                    .append("\nDescrizione: ").append(gerarchia.getRadice().getDescrizione())
                    .append("\nPiazza di scambio: ").append(scambio.getPiazza())
                    .append("\nLuoghi:");
            for (String luogo: scambio.getLuoghi())
                stringBuilder.append("\n\t").append(luogo);
            stringBuilder.append("\nGiorni dello scambio:");
            for (DayOfWeek giorno: scambio.getGiorni())
                stringBuilder.append("\n\t").append(giorno.getDisplayName(TextStyle.FULL, Locale.getDefault()));
            stringBuilder.append("\nIntervalli orari:");
            for (IntervalloOrario interval: scambio.getIntervalliOrari()) {
                stringBuilder.append("\n\tOra inizio: ").append(interval.getOraInizio())
                        .append("\tOra fine: ").append(interval.getOraFine());
            }
        }
        System.out.println(stringBuilder);
    }
}
