package src.structure.inline;

import java.util.Random;
import java.util.regex.Pattern;

public class Image extends Inline{
    private String path;
    private String caption;
    private String label;

    public static Image parse(String str){
        // Matcher pathMatcher = PATH.matcher(str);
        // pathMatcher.find();
        // int end = pathMatcher.start();
        // String coverdCaption = pathMatcher.replaceAll("");
        // String path = str.substring(end+1, str.length()-1);
        // String[] captionAndLabel = coverdCaption.substring(2, coverdCaption.length()-1).split("_");
        // String label, caption;
        String[] data = IMG.split(str);
        String label = "l-"+(new Random()).nextInt(9999);
        if(data.length>=4){
            label = data[3];
        }
        return new Image(data[1], data[2], label);
    }

    public Image(String caption, String path, String label){
        this.path = path;
        this.caption = caption;
        this.label = label;
    }

    @Override
    public String prevDump(){
        return "\n\\begin{figure}[H]\n\\centering\n\\includegraphics[width=8cm]{"+path+"}\n\\caption{"+caption+"}\n\\label{"+label+"}\n\\end{figure}";
    }

    @Override
    public String nextDump(){
        return "";
    }

    // private static Pattern PATH = Pattern.compile("\\(.*\\)");
    private static Pattern IMG = Pattern.compile("!\\[|\\]\\(|_|\\)");
}