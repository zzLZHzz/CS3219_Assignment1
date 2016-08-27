package PipeAndFilter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by kyzer on 25/8/2016.
 */
public class Sink implements Pipe {
    @Override
    public void onComplete(Data data) {
        Path file = Paths.get("output1.txt");
        try {
            Files.write(file, data.inputs, Charset.forName("UTF-8"));
        } catch (IOException e) {
        }
    }
}
