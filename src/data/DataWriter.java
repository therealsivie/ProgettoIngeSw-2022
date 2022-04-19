package data;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import structure.Gerarchia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {

    public static void writeGerarchia(Gerarchia gerarchia)  {
        ObjectMapper mapper = new ObjectMapper();

        try (
                FileWriter file = new FileWriter("data.json", true);
        ){
            mapper.writeValue(file, gerarchia);
        } catch (StreamWriteException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
