package model;
import java.util.ArrayList;

public class OptionList {
    final private ArrayList<Option> voci = new ArrayList<>();
    String[] arr;
    public OptionList(){}
    public void setOption(){
        voci.clear();
        voci.add(new Option("Esci", new Exit()));
        voci.add(new Option("Login", new Login()));
        voci.add(new Option("Visualizza Gerarchie", new VisualizzaGerarchie()));
    }
    public void setLoggedOption(){
        voci.clear();
        voci.add(new Option("Esci", new Exit()));
        voci.add(new Option("Login", new Login()));
        voci.add(new Option("Visualizza Gerarchie", new VisualizzaGerarchie()));
        voci.add(new Option("Aggiungi gerarchia", new VisualizzaGerarchie()));
        voci.add(new Option("prova", new VisualizzaGerarchie()));
    }

    public ArrayList<String> getOptionList(boolean logged){
        if(logged){
            this.setLoggedOption();
        }
        else{
            this.setOption();
        }
        ArrayList<String> temp = new ArrayList<>();
        for (Option opt: voci){
            temp.add(opt.getLabel());
        }
        return temp;
    }
    /*
    public ArrayList<String> getOptionList(){
        ArrayList<String> temp = new ArrayList<>();
        for(Option opt: voci){
            if(!opt.isRequireLogin()){
                temp.add(opt.getLabel());
            }
        }
        return temp;
    }
    */
    public Option getOption(int n){
        return voci.get(n);
    }
}
