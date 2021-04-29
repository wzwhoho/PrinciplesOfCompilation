package pers.lomesome.compliation.utils.grammatical;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrammaticalHandle {
    String myGrammar ;

    public GrammaticalHandle(String myGrammar) {
        this.myGrammar = myGrammar;
    }

    public Map<String, List<List<String>>> grammaticlHandel() {
        Map<String, List<List<String>>> grammarMap = new LinkedHashMap<>();
        String[] grammarlist = myGrammar.split("\t;");
        for (String grammar : grammarlist){
            String g =  replaceBlank(grammar.split("\t:")[0]);
            if ((byte)g.charAt(0) == -1)
                g = g.substring(1);
            String[] replaceableList = grammar.split("\t:")[1].split("\t\\|");
            List<List<String>> arrayList = new ArrayList<>();
            for (String word : replaceableList){
                List<String> stringArrayList = new ArrayList<>(Arrays.asList(replaceBlank(word).split(" ")));
                stringArrayList.removeAll(Collections.singleton(""));
                arrayList.add(stringArrayList);
            }
            grammarMap.put(g, arrayList);
        }
        return grammarMap;
    }

    public String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\t|\r|\n|'");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
