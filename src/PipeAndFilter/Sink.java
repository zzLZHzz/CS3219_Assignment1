package PipeAndFilter;

/**
 * Created by kyzer on 25/8/2016.
 */
public class Sink implements Pipe {
    @Override
    public void onComplete(Data data) {
        //display
        for(String s : data.inputs){
            System.out.println(s);
        }
        System.out.println("Done");
    }
}
