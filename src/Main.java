import PipeAndFilter.*;
import EventBasedImplicitInvocation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String inputLocation, ignoreLocation;
        if (args.length == 2){
            inputLocation = args[0];
            ignoreLocation =  args[1];
        }else{
            inputLocation = "input.txt";
            ignoreLocation =  "ignore.txt";
        }

        List<String> sentences = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(inputLocation))) {
            String line = br.readLine();

            while (line != null) {
                sentences.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
        }

        List<String> ignoredWords = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(ignoreLocation))) {
            String line = br.readLine();

            while (line != null) {
                ignoredWords.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
        }


        //Architecture 1
        Data data = new Data();
        data.inputs = sentences;
        data.ignoreList = ignoredWords;
        Pump p = new Pump();
        CircularShiftFilter csf = new CircularShiftFilter();
        IgnoredWordsFilter iwf = new IgnoredWordsFilter();
        AscendingAlphabetSortFilter aasf = new AscendingAlphabetSortFilter();
        CapitalizeFilter cf = new CapitalizeFilter();
        Sink s = new Sink();

        p.addPipe(csf);
        csf.addPipe(iwf);
        iwf.addPipe(aasf);
        aasf.addPipe(cf);
        cf.addPipe(s);

        p.start(data);

        //Architecture 2
        KWIC kwic = new KWIC();
        kwic.doWorkOnCompletion(new OnCompleteListener() {
            public void onComplete(KWICResult result) {
                Path file = Paths.get("output2.txt");
                try {
                    Files.write(file, result.results, Charset.forName("UTF-8"));
                } catch (IOException e) {
                }
                //display
                //for(String s : result.results){
                    //System.out.println(s);
                //}
            }
        });
        kwic.doWork(sentences, ignoredWords);
    }
}
