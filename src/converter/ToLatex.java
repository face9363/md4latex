package src.converter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import src.tree.Branch;

public class ToLatex{
    public Branch root;
    public Branch current;
    StringBuilder val = new StringBuilder();
    StringBuilder middle = new StringBuilder();
    StringBuilder endVal = new StringBuilder();

    public ToLatex(Branch root){
        this.root = root;
        this.current = root;
    }

    public void dump(){
        System.out.println(val);
        System.out.println(middle);
        System.out.println(endVal);
    }

    public StringBuilder dumper(){
        this.root.explore(middle);
        // System.out.println(middle);
        return middle;
    }

    public String readFile(String filepath){
        FileSystem fs = FileSystems.getDefault();
        Path inputPath = fs.getPath(filepath);

        try (Stream<String> stream = Files.lines(inputPath, StandardCharsets.UTF_8)) {
            String[] template = stream.toArray(s -> new String[s]);
            Boolean notend = true;
            String inlinePath = null;

            for(String str: template){
                Matcher matcher = ToLatex.Inline.matcher(str);
                if(matcher.find() && notend){
                    notend = false;
                    String covered = matcher.replaceAll("");
                    inlinePath = covered.substring(1, covered.length()-1);
                }
                else if(notend){
                    val.append(str + "\n");
                }
                else{
                    endVal.append(str + "\n");
                }
            }
            return inlinePath;
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("## failed to aquire stream.");
            return null;
        }
    }


    private static Pattern Inline = Pattern.compile("^\\\\insert");
}