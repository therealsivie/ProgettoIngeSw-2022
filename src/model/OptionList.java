package model;
import controller.*;

import java.util.ArrayList;

public class OptionList {
    final private ArrayList<Option> voci = new ArrayList<>();
    String[] arr;
    public OptionList(){}
    private void setOptionConf(){
        voci.clear();
        voci.add(new Option("Esci", new Exit()));
        voci.add(new Option("Login", new LoginConf()));
        voci.add(new Option("Visualizza Gerarchie", new VisualizzaGerarchie()));
    }
    private void setLoggedOptionConf(){
        voci.clear();
        voci.add(new Option("Esci", new Exit()));
        voci.add(new Option("Logout", new Logout()));
        voci.add(new Option("Visualizza Gerarchie", new VisualizzaGerarchie()));
        voci.add(new Option("Inserisci Gerarchia", new InserisciGerarchia()));
    }




    public ArrayList<String> getConfOptionList(boolean logged){
        if(logged){
            this.setLoggedOptionConf();
        }
        else{
            this.setOptionConf();
        }
        ArrayList<String> temp = new ArrayList<>();
        for (Option opt: voci){
            temp.add(opt.getLabel());
        }
        return temp;
    }

    public Option getOption(int n){
        return voci.get(n);
    }


    public ArrayList<String> getFruitOptionList(boolean logged) {
        if (logged)
            this.setLoggedOptionFruit();
        else
            this.setOptionFruit();
        ArrayList<String> temp = new ArrayList<>();
        for (Option opt: voci){
            temp.add(opt.getLabel());
        }
        return temp;
    }

    private void setOptionFruit() {
        voci.clear();
        voci.add(new Option("Esci", new Exit()));
        voci.add(new Option("Login", new LoginFruit()));
    }

    private void setLoggedOptionFruit() {
        voci.clear();
        voci.add(new Option("Esci", new Exit()));
        voci.add(new Option("Logout", new Logout()));
    }
}
