package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import structure.Gerarchia;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private final static String listaFile = "files/listaGerarchie.txt";
    private final static String dataName = "files/gerarchie/gerarchia";
    private static int numFile = getNumFile();

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

    public static void writeGerarchia(Gerarchia gerarchia) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = null;
        int numFile = JsonUtil.getNumFile();
        String nomeFile = dataName + numFile + ".json";
        JsonUtil.addFileToListaFile(nomeFile);
        writer = new FileWriter(nomeFile);
        writer.write(gson.toJson(gerarchia));
        writer.close();
    }

    private static void addFileToListaFile(String nomeFile) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(listaFile, true));
            writer.append(nomeFile);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> createListOfFile() {
        ArrayList<String> listOfLines = new ArrayList<>();
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(listaFile));

            String line = bufReader.readLine();
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
        Gerarchia gerarchia = null;
        try {
            Reader reader = null;
            for (String file : JsonUtil.createListOfFile()) {
                 reader = Files.newBufferedReader(Paths.get(file));
                Gson gson = new Gson();
                // convert JSON file to Gerarchia
                gerarchia = gson.fromJson(reader, Gerarchia.class);
                gerarchiaList.add(gerarchia);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return gerarchiaList;
    }
}
