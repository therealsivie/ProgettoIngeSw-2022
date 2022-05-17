package model.baratto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Appuntamento {
    private String luogo;
    private LocalTime dataOra;
    private DayOfWeek giorno;

    public Appuntamento(String luogo, LocalTime dataOra, DayOfWeek giorno) {
        this.luogo = luogo;
        this.dataOra = dataOra;
        this.giorno = giorno;
    }
}
