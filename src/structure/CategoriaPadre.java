package structure;

import java.util.ArrayList;
import java.util.List;

public class CategoriaPadre extends Categoria {
    private List<Categoria> figli = new ArrayList<>();

    public CategoriaPadre(String nome, String descrizione, ArrayList<CampoNativo> campi, ArrayList<Categoria> figli){
        this.setNome(nome);
        this.setDescrizione(descrizione);
        this.figli = figli;
        for (CampoNativo campo: campi){
            this.addCampo(campo);
        }
    }

    public void addFiglio(Categoria figlio){
        figlio.setFather(this);
        this.figli.add(figlio);
    }

    public void removeFiglio(Categoria figlio){
        figlio.setFather(null);
        this.figli.remove(figlio);
    }

    public Categoria[] getFigli(){
        return this.figli.toArray(Categoria[]::new);
    }
}
