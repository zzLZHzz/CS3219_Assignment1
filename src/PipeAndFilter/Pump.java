package PipeAndFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyzer on 25/8/2016.
 */
public class Pump {
    List<Pipe> listeners = new ArrayList<Pipe>();

    public void addPipe(Pipe toAdd) {
        listeners.add(toAdd);
    }

    public void start(Data data){
        System.out.println("Pump started");

        // Notify completion
        for (Pipe hl : listeners)
            hl.onComplete(data);
    }
}
