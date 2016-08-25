package PipeAndFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyzer on 25/8/2016.
 */
public class CapitalizeFilter  extends Filter {
    public void filter(Data data){

        List<String> output = new ArrayList<>();

        for(String s : data.inputs){
            output.add(s.substring(0, 1).toUpperCase() + s.substring(1));
        }

        data.inputs = output;

        // Notify completion
        for (Pipe hl : listeners)
            hl.onComplete(data);
    }
    @Override
    public void onComplete(Data data) {
        filter(data);
    }
}
