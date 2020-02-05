package src.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.structure.Structure;
import src.tree.Branch;
import java.util.stream.Stream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import src.structure.block.*;

public class Parser{
    public ArrayList<Branch> stack = new ArrayList<>();
    public Branch current;
    public Branch root;


    public Parser(){
        this.root = new Branch(new Document(), null);
        this.current = this.root;
    }

    public void parse(String filepath) {

        FileSystem fs = FileSystems.getDefault();
        Path inputPath = fs.getPath(filepath);

        try (Stream<String> stream = Files.lines(inputPath, StandardCharsets.UTF_8)) {
            stream.forEach((str) -> {
                readOneLine(str);
            });
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("## failed to aquire stream.");
        }

    }

    private void readOneLine(String str) {
        // StringBuilder prev = new StringBuilder();
        str = this.topReader(str);
        this.current.self.content += str;
        // str.chars()
        //     .mapToObj(i -> (char)i)
        //     .forEach((ch) -> {
        //         switch(ch){
        //             case '#':
        //                 prev.append(ch);
        //         }
        //     });
    }

    private String topReader(String str){
        Matcher matcher;
        if(Parser.Space.matcher(str).find() && this.current.self instanceof Block){
            if(this.current.self instanceof Quote){
                this.current = this.current.parent;
            }
            return null;
        }
        matcher = Parser.Title.matcher(str);
        if(matcher.find()){
            int len = matcher.end()-1;
            Structure struct = new Title(len);
            this.current = this.current.appendChild(struct);
            this.current = this.current.parent;

            return matcher.replaceFirst("");
        }
        matcher = Parser.Quote.matcher(str);
        if(matcher.find()){
            Structure struct = new Quote();
            this.current = this.current.appendChild(struct);

            return matcher.replaceFirst("");
        }
        matcher = Parser.Math.matcher(str);
        if(matcher.find()){
            int len = matcher.end()-1;
            Structure struct = new MathMode(len);
            this.current = this.current.appendChild(struct);
            return matcher.replaceFirst("");
        }
        return str;
    }

    private static Pattern Title = Pattern.compile("^#{1,6}\\ ");
    private static Pattern Math = Pattern.compile("^\\${1,3}\\ ");
    private static Pattern Quote = Pattern.compile("^>");
    private static Pattern Space = Pattern.compile("^\\ *\n");
}