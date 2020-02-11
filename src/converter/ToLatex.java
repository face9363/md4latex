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

import src.parser.Parser;

public class ToLatex{
    StringBuilder val = new StringBuilder();

    public void dump(){
        System.out.println(val);
    }

    public void readLayoutTex(String filepath){
        FileSystem fs = FileSystems.getDefault();
        Path inputPath = fs.getPath(filepath);

        try (Stream<String> stream = Files.lines(inputPath, StandardCharsets.UTF_8)) {
            String[] template = stream.toArray(s -> new String[s]);
            String inlinePath;
            Parser parser;

            for(String str: template){
                Matcher matcher = ToLatex.Inline.matcher(str);
                if(matcher.find()){
                    String covered = matcher.replaceAll("");
                    inlinePath = covered.substring(1, covered.length()-1);
                    parser = new Parser();
                    parser.parse(inlinePath);
                    val.append(parser.dumpTex());
                }
                else{
                    val.append(str + "\n");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("## failed to aquire stream.");
        }
    }


    private static Pattern Inline = Pattern.compile("^\\\\insert");
}