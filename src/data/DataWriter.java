package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import structure.Gerarchia;

import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {

    public static void writeGerarchia(Gerarchia gerarchia) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = null;
        writer = new FileWriter("structure.json", true);
        writer.write(gson.toJson(gerarchia));
        writer.close();

    }
}
