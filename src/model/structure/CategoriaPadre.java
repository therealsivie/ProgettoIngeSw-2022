package model.structure;

import java.util.ArrayList;
import java.util.List;

public class CategoriaPadre extends Categoria {
    private List<Categoria> figli;

    public CategoriaPadre(String nome, String descrizione, ArrayList<CampoNativo> campi, ArrayList<Categoria> figli) {
        this.setNome(nome);
        this.setDescrizione(descrizione);
        this.figli = figli;
        for (CampoNativo campo : campi) {
            this.addCampo(campo);
        }
    }

    public void addFiglio(Categoria figlio) {
        //figlio.setFather(this);
        if (this.figli == null)
            this.figli = new ArrayList<>();
        this.figli.add(figlio);
    }

    public void removeFiglio(Categoria figlio) {
        figlio.setFather(null);
        this.figli.remove(figlio);
    }

    public List<Categoria> getFigli() {
        if (this.figli != null) {
            for (Categoria cat : this.figli) {
                if(cat instanceof CategoriaPadre)
                    return ((CategoriaPadre) cat).getFigli();
                else
                    return null;
            }
        }
        return null;
    }

    /*
    public List<CategoriaPadre> getPadreList(){
        ArrayList<CategoriaPadre> padri = new ArrayList<CategoriaPadre>();
        for(Categoria c: this.figli){
            if(c instanceof CategoriaPadre)
                padri.add((CategoriaPadre) c);
        }
        return padri;
    }
     */

    public ArrayList<Categoria> getStrutturaCompleta() {
        ArrayList<Categoria> struttura = new ArrayList<>();
        if (this.getFigli() == null) {
            struttura.add(this);
            return struttura;
        }
        for (Categoria c : this.getFigli()) {
            if (c instanceof CategoriaPadre)
                struttura.addAll(((CategoriaPadre) c).getStrutturaCompleta());
            else
                struttura.add(c);
        }
        return struttura;
    }

    public Categoria getFiglio(Categoria boh2) {
        return null;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("\nNome: ").append(this.getNome())
                .append("\nDescrizione: ").append(this.getDescrizione());
        for (CampoNativo c: this.getCampi()){
            str.append("\nNome campo: ").append(c.getNomeCampo())
                    .append(c.isRequired()? "": "\t(opzionale)");
        }
        for (Categoria cat: this.figli){
            str.append(cat.toString());
        }
        return str.toString();
    }
}
