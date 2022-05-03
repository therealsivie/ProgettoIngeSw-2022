package controller;

import utility.JsonUtil;
import model.structure.Gerarchia;

import java.util.List;

public class VisualizzaGerarchie implements Action {
    private void visualizza() {
        List<Gerarchia> gerarchiaList = JsonUtil.readGerarchie();
        if (gerarchiaList != null) {
            gerarchiaList.forEach(Gerarchia::printGerarchia);
        } else
            System.out.println("Non sono presenti gerarchie.");
    }

    @Override
    public boolean execute() {
        this.visualizza();
        return false;
    }
}
