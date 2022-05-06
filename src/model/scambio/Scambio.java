package model.scambio;

import java.time.DayOfWeek;
import java.util.List;

public class Scambio {
    private String nomeGerarchiaScambio;
    private String piazza;
    private List<String> luoghi;
    private List<DayOfWeek> giorni;
    private List<IntervalliOrari> intervalliOrari;
    private int scadenzaProposta;

    public String getNomeGerarchiaScambio(){
        return this.nomeGerarchiaScambio;
    }

    public Scambio(String nomeGerarchiaScambio, String piazza) {
        this.nomeGerarchiaScambio = nomeGerarchiaScambio;
        this.piazza = piazza;
    }

    public void setLuoghi(List<String> luoghi) {
        this.luoghi = luoghi;
    }

    public void setGiorni(List<DayOfWeek> giorni) {
        this.giorni = giorni;
    }

    public void setIntervalliOrari(List<IntervalliOrari> intervalliOrari) {
        this.intervalliOrari = intervalliOrari;
    }

    public void setScadenzaProposta(int scadenzaProposta) {
        this.scadenzaProposta = scadenzaProposta;
    }
}
