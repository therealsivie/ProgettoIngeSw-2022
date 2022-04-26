package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.structure.Gerarchia;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private final static String listaFile = "files/listaGerarchie.txt";
    private final static String dataName = "files/gerarchie/gerarchia";
    private final static int numFile = getNumFile();

    private static int getNumFile() {
        File file = new File(listaFile);
        int lines = 0;
        try {
            file.createNewFile();
            LineNumberReader lnr = new LineNumberReader(new FileReader(file));
            while (lnr.readLine() != null) ;
            lines = lnr.getLineNumber();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public static void writeGerarchia(Gerarchia gerarchia) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        int numFile = JsonUtil.getNumFile();
        String nomeFile = dataName + numFile + ".json";
        JsonUtil.addFileToListaFile(nomeFile);
        try (
                FileWriter writer = new FileWriter(nomeFile)
        ) {
            writer.write(gson.toJson(gerarchia));
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio della gerarchia");
        }

    }

    private static void addFileToListaFile(String nomeFile) {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(listaFile, true))
        ) {
            writer.append(nomeFile);
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> createListOfFile() {
        ArrayList<String> listOfLines = new ArrayList<>();
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(listaFile));
            String line = bufReader.readLine();
            if(line == null || JsonUtil.getNumFile()==0){
                return null;
            }
            while (line != null) {
                listOfLines.add(line);
                line = bufReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Errore lista file");
        }
        return listOfLines;
    }

    public static List<Gerarchia> readGerarchie() {
        List<Gerarchia> gerarchiaList = new ArrayList<>();
        Gerarchia gerarchia;
        try {
            Reader reader;
            if(JsonUtil.createListOfFile() == null){
                return null;
            }
            for (String file : JsonUtil.createListOfFile()) {
                reader = Files.newBufferedReader(Paths.get(file));
                Gson gson = new Gson();
                // convert JSON file to Gerarchia
                gerarchia = gson.fromJson(reader, Gerarchia.class);
                gerarchiaList.add(gerarchia);
            }
        } catch (IOException ex) {
            System.out.println("Errore apertura file Gerarchie");
        }
        return gerarchiaList;
    }
    public static boolean checkNomeGerarchiaRipetuto(String nome){
        List<Gerarchia> gerarchiaList = JsonUtil.readGerarchie();
        for(Gerarchia g: gerarchiaList){
            if(g.getCategoriaRadice().getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }
    public static boolean checkNomeCategoriaRipetuto(String nome){
        List<Gerarchia> gerarchiaList = JsonUtil.readGerarchie();
        for(Gerarchia g: gerarchiaList){
            return g.nomeRipetuto(nome);
        }
        return false;
    }
}
