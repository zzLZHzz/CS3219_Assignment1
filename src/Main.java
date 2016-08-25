import PipeAndFilter.*;

public class Main {

    public static void main(String[] args) {

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
    }
}
