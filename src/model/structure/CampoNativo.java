package model.structure;

import java.util.ArrayList;
public class CampoNativo {
    private String nomeCampo;
    private boolean required;
    public CampoNativo(String nomeCampo, boolean required) {
        this.nomeCampo = nomeCampo;
        this.required = required;
    }
    public String getNome() {
        return nomeCampo;
    }
    public boolean isRequired() {
        return required;
    }
    public boolean isNomeRipetuto(String nome){
        return this.nomeCampo.equals(nome);
    }
    public boolean campoGiaInLista(ArrayList<CampoNativo> campi){
        return campi.stream().anyMatch(cmp -> this.isNomeRipetuto(cmp.getNome()));
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n\t\t").append(nomeCampo);
        if(!isRequired())
            str.append(" (opzionale)");
        return str.toString();
    }

}