package model.scambio;

import java.time.DayOfWeek;
import java.util.List;

public class Scambio {
    private String piazza;
    private List<String> luoghi;
    private List<DayOfWeek> giorni;
    private List<IntervalloOrario> intervalliOrari;
    private int scadenzaProposta;

    public Scambio(String piazza) {
        this.piazza = piazza;
    }

    public void setLuoghi(List<String> luoghi) {
        this.luoghi = luoghi;
    }

    public void setGiorni(List<DayOfWeek> giorni) {
        this.giorni = giorni;
    }

    public void setIntervalliOrari(List<IntervalloOrario> intervalliOrari) {
        this.intervalliOrari = intervalliOrari;
    }

    public void setScadenzaProposta(int scadenzaProposta) {
        this.scadenzaProposta = scadenzaProposta;
    }

    public String getPiazza() {
        return piazza;
    }

    public List<String> getLuoghi() {
        return luoghi;
    }

    public List<DayOfWeek> getGiorni() {
        return giorni;
    }

    public List<IntervalloOrario> getIntervalliOrari() {
        return intervalliOrari;
    }
}
