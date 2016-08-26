import PipeAndFilter.*;
import EventBasedImplicitInvocation.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Architecture 1");
        Data data = new Data();
        data.addIgnoreWord("is");
        data.addIgnoreWord("the");
        data.addIgnoreWord("of");
        data.addIgnoreWord("and");
        data.addIgnoreWord("as");
        data.addIgnoreWord("a");
        data.addIgnoreWord("after");
        data.addInput("The Day after Tomorrow");
        data.addInput("Fast and Furious");
        data.addInput("Man of Steel");

	    //Pipe & Filter logic
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

        System.out.println("Architecture 2");
        List<String> ignoredWords = new ArrayList<String>() {{
            add("hi");
            add("is");
            add("the");
            add("of");
            add("and");
            add("as");
            add("a");
            add("after");
        }};
        List<String> sentences = new ArrayList<String>() {{
            add("The Day after Tomorrow");
            add("Fast and Furious");
            add("Man of Steel");
        }};
        KWIC kwic = new KWIC();
        kwic.doWorkOnCompletion(new OnCompleteListener() {
            public void onComplete(KWICResult result) {
                //display
                for(String s : result.results){
                    System.out.println(s);
                }
            }
        });
        kwic.doWork(sentences, ignoredWords);
    }
}
