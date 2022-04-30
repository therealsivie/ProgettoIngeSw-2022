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
        this.campi.addAll(campi);
        this.padre = padre;
    }


    public void addSingoloCampo(CampoNativo campo) {
        if (this.campi == null)
            this.campi = new ArrayList<>();
        this.campi.add(campo);
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
        if(this.isFoglia()){
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
        if (isFoglia())
            return this;
        if (!isFoglia())
            for (Categoria cat : this.figli)
                if (cat.getNome().equals(nomeCatSelezionata))
                    return this;
        if (isRadice())
            return this;
        return null;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", campi=" + campi +
                ", figli=" + figli +
                ", padre='" + padre + '\'' +
                '}';
    }
}
