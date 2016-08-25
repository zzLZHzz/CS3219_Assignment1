package PipeAndFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyzer on 25/8/2016.
 */
public class Data {
    List<String> ignoreList = new ArrayList<String>();
    List<String> inputs = new ArrayList<String>();

    public void addIgnoreWord(String word){
        ignoreList.add(word);
    }

    public void addInput(String sentence){
        inputs.add(sentence);
    }
}
