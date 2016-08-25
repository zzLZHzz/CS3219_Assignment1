package PipeAndFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyzer on 25/8/2016.
 */
public class IgnoredWordsFilter extends Filter {
    public void filter(Data data){
        List<String> output = new ArrayList<>();
        for(String s : data.inputs){
            processSentence(s,data.ignoreList,output);
        }

        data.inputs = output;

        // Notify completion
        for (Pipe hl : listeners)
            hl.onComplete(data);
    }

    public String extractFirstWord(String s){
        String arr[] = s.split(" ", 2);
        return arr[0];
    }

    public Boolean isIgnoredWord(String s, List<String> ignoredList){
        return ignoredList.stream().anyMatch(str -> str.trim().toLowerCase().equals(s.toLowerCase()));
    }

    public void processSentence(String s, List<String> ignoredList, List<String> output){
        if (!isIgnoredWord(extractFirstWord(s),ignoredList)){
            output.add(s);
        }
    }

    @Override
    public void onComplete(Data data) {
        filter(data);
    }
}
