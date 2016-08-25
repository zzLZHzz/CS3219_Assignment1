package PipeAndFilter;
import java.util.*;

/**
 * Created by kyzer on 25/8/2016.
 */
public abstract class Filter implements Pipe {
    List<Pipe> listeners = new ArrayList<Pipe>();

    public void addPipe(Pipe toAdd) {
        listeners.add(toAdd);
    }
}
