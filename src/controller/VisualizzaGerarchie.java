package controller;

import data.DataReader;
import model.Action;
import structure.Gerarchia;

import java.util.ArrayList;
import java.util.List;

public class VisualizzaGerarchie implements Action {
    private void visualizza(){
        List<Gerarchia> gerarchias = DataReader.readGerarchie();
        if(gerarchias != null) {
            for (Gerarchia g : gerarchias) {
                gerarchias.toString();
            }
        } else
            System.out.println("Non sono presenti gerarchie.");
    }
    @Override
    public boolean execute() {
        this.visualizza();
        return false;
    }
}
