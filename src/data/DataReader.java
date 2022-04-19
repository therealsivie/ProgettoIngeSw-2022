package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import structure.Gerarchia;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<Gerarchia> readGerarchie() {
        ObjectMapper mapper = new ObjectMapper();
        List<Gerarchia> gerarchiaList = null;
        try {
            gerarchiaList = mapper.readValue(new File("data.json"), new TypeReference<List<Gerarchia>>() {});
            for(Gerarchia gerarchia: gerarchiaList){
                gerarchia.toString();
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file");
        }
        return gerarchiaList;
    }
}
