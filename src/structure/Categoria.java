package structure;

import java.util.ArrayList;

public class Categoria {
    private String nome;
    private String descrizione;
    private ArrayList<CampoNativo> campi;
    private CategoriaPadre padre = null;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ArrayList<CampoNativo> getCampi() {
        return campi;
    }

    public void addCamp0(CampoNativo campoNativo) {
        this.campi.add(campoNativo);
    }

    public void setFather(CategoriaPadre padre){
        this.padre = padre;
    }

    public CategoriaPadre getPadre(){
        return this.padre;
    }

    public boolean isPadre(){
        return this.padre == null;
    }
}