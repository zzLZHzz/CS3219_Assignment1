package EventBasedImplicitInvocation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.event.EventListenerList;

public class KWIC {
    protected EventListenerList listenerList = new EventListenerList();

    public void doWorkOnCompletion(OnCompleteListener listener) {
        listenerList.add(OnCompleteListener.class, listener);
    }
    public void removeOnCompleteListener(OnCompleteListener listener) {
        listenerList.remove(OnCompleteListener.class, listener);
    }

    public void doWork(List<String> inputs, List<String> ignoreList){
        List<String> output = new ArrayList<>();

        for(String s : inputs) {
            List<String> sOutput = new ArrayList<>();
            shift(s, sOutput);
            processSentence(sOutput, ignoreList);
            output.addAll(sOutput);
        }

        //sort
        output.sort((p1, p2) -> p1.compareTo(p2));

        KWICResult result = new KWICResult(output);
        triggerOnCompleteListener(result);
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

    public String extractFirstWord(String s){
        String arr[] = s.split(" ", 2);
        return arr[0];
    }

    public Boolean isIgnoredWord(String s, List<String> ignoredList){
        return ignoredList.stream().anyMatch(str -> str.trim().toLowerCase().equals(s.toLowerCase()));
    }

    public void processSentence(List<String> inputs, List<String> ignoredList){
        List<String> output = new ArrayList<>();

        Iterator<String> i = inputs.iterator();
        while (i.hasNext()) {
            String s = i.next(); // must be called before you can call i.remove()
            if (isIgnoredWord(extractFirstWord(s),ignoredList)){
                i.remove();
            }else{
                s = s.substring(0, 1).toUpperCase() + s.substring(1);
            }
        }
    }

    private void triggerOnCompleteListener(KWICResult result){
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i+2) {
            if (listeners[i] == OnCompleteListener.class) {
                ((OnCompleteListener) listeners[i+1]).onComplete(result);
            }
        }
    }
}
