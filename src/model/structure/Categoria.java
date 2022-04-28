package model.structure;

import java.util.ArrayList;

public class Categoria {
    private String nome;
    private String descrizione;
    private ArrayList<CampoNativo> campi = null;
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

    public void addCampo(CampoNativo campoNativo) {
        if (this.campi == null) {
            campi = new ArrayList<>();
        }
        this.campi.add(campoNativo);
    }

    public void setFather(CategoriaPadre padre) {
        this.padre = padre;
    }

    public CategoriaPadre getPadre() {
        return this.padre;
    }

    public boolean checkNomeRipetuto(String nome) {
        for (Categoria c : padre.getFigli()) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public CategoriaPadre getPadreByNome(String nomeCatPadre) {
        if (nomeCatPadre.equals(this.getNome()))
            return (CategoriaPadre) this;
        return null;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Nome: ").append(this.getNome())
                .append("\nDescrizione: ").append(this.getDescrizione());
        if(campi == null)
            return str.toString();
        else {
            for (CampoNativo c : this.getCampi()) {
                str.append("\nNome campo: ").append(c.getNomeCampo())
                        .append(c.isRequired() ? "" : "\t(opzionale)");
            }
            return str.toString();
        }
    }

    public void setCampi(ArrayList<CampoNativo> campi) {
        this.campi = campi;
    }
}