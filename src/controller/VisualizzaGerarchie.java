package controller;

import model.gerarchia.Categoria;
import utility.JsonUtil;
import model.gerarchia.Gerarchia;
import utility.MyMenu;

import java.util.ArrayList;
import java.util.List;

public class VisualizzaGerarchie implements Action {
    private void visualizza() {
        List<Gerarchia> gerarchiaList = JsonUtil.readGerarchie();
        if (gerarchiaList != null && gerarchiaList.size() != 0){
            ArrayList<String> voci = new ArrayList<>();
            for (Gerarchia gerarchia: gerarchiaList)
                voci.add(gerarchia.getNomeRadice());
            MyMenu menu = new MyMenu("Gerarchia da visualizzare");
            menu.setVoci(voci);

            //visualizzazione gerarchia
            Gerarchia gerarchia = gerarchiaList.get(menu.scegli());
            System.out.println("\nGerarchia: "+gerarchia.getNomeRadice());
            Categoria radice = gerarchia.getRadice();
            System.out.println("Descrizione: "+radice.getDescrizione());
            System.out.println("Campi:");
            radice.getCampi().forEach(System.out::println);

            //visualizzazione sottocategorie della gerarchia selezionata
            System.out.println("\nSottocategorie di "+gerarchia.getNomeRadice());
            ArrayList<Categoria> sottocategorie = radice.getFigli();
            for(Categoria sottocategoria: sottocategorie) {
                System.out.println();
                System.out.println("Categoria: "+sottocategoria.getNome());
                System.out.println("Descrizione: "+sottocategoria.getDescrizione());
                System.out.println("Campi:");
                sottocategoria.getCampi().forEach(System.out::println);
            }

            //scelta di una sottocategoria tra quelle elencate e visualizzazione del suo albero





        }else
            System.out.println("\nNessuna gerarchia Inserita");
    }

    @Override
    public boolean execute() {
        this.visualizza();
        return false;
    }
}
