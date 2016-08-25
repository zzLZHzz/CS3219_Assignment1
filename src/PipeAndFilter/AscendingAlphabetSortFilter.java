package PipeAndFilter;

/**
 * Created by kyzer on 25/8/2016.
 */
public class AscendingAlphabetSortFilter extends Filter {
    public void filter(Data data){

        data.inputs.sort((p1, p2) -> p1.compareTo(p2));

        // Notify completion
        for (Pipe hl : listeners)
            hl.onComplete(data);
    }
    @Override
    public void onComplete(Data data) {
        //data come in

        filter(data);
    }
}
