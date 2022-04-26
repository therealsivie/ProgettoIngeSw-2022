package model.structure;

public class CampoNativo {
    private String nomeCampo;
    private boolean required;

    public CampoNativo(String nomeCampo, boolean required) {
        this.nomeCampo = nomeCampo;
        this.required = required;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public boolean isRequired() {
        return required;
    }
}
