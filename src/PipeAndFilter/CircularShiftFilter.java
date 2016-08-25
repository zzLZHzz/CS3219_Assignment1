package PipeAndFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyzer on 25/8/2016.
 */
public class CircularShiftFilter extends Filter {
    public void filter(Data data){
        List<String> output = new ArrayList<>();
        for(String s : data.inputs){
            shift(s,output);
        }

        data.inputs = output;

        // Notify completion
        for (Pipe hl : listeners)
            hl.onComplete(data);
    }

    public void shift(String s, List<String> output){
        String[] word = s.split("\\s+");

        for (String w : word) {
            s.trim();
            output.add(s);
            int index =  s.indexOf(" ");
            String toShift = s.substring(0,index);
            s = s.substring(index + 1);
            s = s + " " + toShift;
        }
    }

    @Override
    public void onComplete(Data data) {
        filter(data);
    }
}
