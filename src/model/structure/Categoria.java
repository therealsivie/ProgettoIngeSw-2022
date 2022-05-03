package model.structure;

import java.util.ArrayList;

public class Categoria {
    private String nome;
    private String descrizione;
    private ArrayList<CampoNativo> campi;
    private ArrayList<Categoria> figli;
    private String padre;

    public Categoria(String nome, String descrizione, ArrayList<CampoNativo> campi, String padre) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.campi = new ArrayList<>();
        if (campi != null)
            this.campi.addAll(campi);
        else
            this.campi = campi;
        this.padre = padre;
    }


    public boolean addSingoloCampo(CampoNativo campo) {
        if (this.campi == null)
            this.campi = new ArrayList<>();
        if (!this.checkCampoRipetuto(campo.getNome())) {
            this.campi.add(campo);
            return true;
        } else
            return false;
    }

    public void addCampi(ArrayList<CampoNativo> campi) {
        if (this.campi == null)
            this.campi = new ArrayList<>();
        this.campi.addAll(campi);
    }

    public String getNome() {
        return nome;
    }

    public String getPadre() {
        return padre;
    }

    public boolean isRadice() {
        return padre == null;
    }

    public boolean isFoglia() {
        return figli == null;
    }

    public void addSingoloFiglio(Categoria figlia) {
        if (this.figli == null)
            this.figli = new ArrayList<>();
        this.figli.add(figlia);
    }

    public void addFigli(ArrayList<Categoria> figliIns) {
        if (this.figli == null)
            this.figli = new ArrayList<>();
        this.figli.addAll(figliIns);
    }

    public int getSizeFigli() {
        return this.figli.size();
    }

    public ArrayList<String> getStrutturaCompleta() {
        ArrayList<String> strutturaCompleta = new ArrayList<>();
        if (this.isFoglia()) {
            strutturaCompleta.add(this.getNome());
            return strutturaCompleta;
        }
        for (Categoria c : this.figli) {
            if (!isFoglia())
                strutturaCompleta.addAll(c.getStrutturaCompleta());
            else
                strutturaCompleta.add(c.getNome());
        }
        return strutturaCompleta;
    }

    public Categoria getCategoriaByNome(String nomeCatSelezionata) {
        Categoria cat = null;
        if (this.nome.equals(nomeCatSelezionata))
            cat = this;
        if (figli != null) {
            for (Categoria categoria : this.figli)
                cat = categoria.getCategoriaByNome(nomeCatSelezionata);
        }
        return cat;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\nNome: ").append(nome)
                .append("\nDescrizione: ").append(descrizione)
                .append("\nCampi:");
        for (CampoNativo cmp : this.campi) {
            str.append(cmp.toString());
        }
        if (!isFoglia()) {
            str.append("\nFigli:");
            for (Categoria cat : this.figli)
                str.append("\t").append(cat.toString());
        }
        return str.toString();
    }

    public String printFiglio() {
        StringBuilder str = new StringBuilder();
        str.append("\n\tNome: ").append(nome)
                .append("\n\tDescizione: ").append(descrizione);
        if (this.campi != null)
            str.append("");
        return str.toString();
    }

    public boolean checkCampoRipetuto(String nomeCampo) {
        if (isRadice()) {
            for (CampoNativo campo : this.campi) {
                if (campo.getNome().equals(nomeCampo))
                    return true;
            }
        } else {
            Categoria padre = this.getCategoriaByNome(this.getPadre());
            if (campi != null) {
                for (CampoNativo campo : this.campi) {
                    if (campo.getNome().equals(nomeCampo))
                        return true;
                }
            }
            else return false;
            padre.checkCampoRipetuto(nomeCampo);
        }

        return false;
    }
}
