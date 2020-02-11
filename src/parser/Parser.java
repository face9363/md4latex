package src.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.tree.Branch;
import java.util.stream.Stream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import src.structure.block.*;
import src.structure.inline.Image;

public class Parser{
    public ArrayList<Branch> stack = new ArrayList<>();
    public Branch current;
    public Branch root;


    public Parser(){
        this.root = new Branch(new Document(), null);
        this.current = this.root;
    }

    public StringBuilder dumpTex(){
        StringBuilder val = new StringBuilder();
        this.root.explore(val);
        return val;
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
        str = this.topReader(str);
        if(! (str == null)){
            inlineReader(str);
        }
    }

    private void inlineReader(String str){
        Matcher matcher = Parser.INLINE.matcher(str);
        int prevEnd = 0;
        while(matcher.find()){
            int start = matcher.start();
            this.current.appendChild(new Paragraph(str.substring(prevEnd, start)+"\n"));

            prevEnd = matcher.end();
            String inlinePart = matcher.group();
            if(IMAGE.matcher(inlinePart).find()){
                this.current.appendChild(Image.parse(inlinePart));
            }
            else{
                this.current.appendChild(new Paragraph(str + "\n"));
            }
        }
        if(str.substring(prevEnd)!="") this.current.appendChild(new Paragraph(str.substring(prevEnd)+"\n"));
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
            Block struct = new Title(len);
            this.current = this.current.appendChild(struct);
            this.current.appendChild(new Paragraph(matcher.replaceFirst("")));
            this.current = this.current.parent;

            return null;
        }
        matcher = Parser.Quote.matcher(str);
        if(matcher.find()){
            Block struct = new Quote();
            this.current = this.current.appendChild(struct);

            return matcher.replaceFirst("");
        }
        matcher = Parser.Math.matcher(str);
        if(matcher.find()){
            if(this.current.self instanceof MathMode){
                this.current = this.current.parent;
                return null;
            }
            int len = matcher.end();
            Block struct = new MathMode(len);
            this.current = this.current.appendChild(struct);
            String result = matcher.replaceFirst("");
            return result=="" ? null : result;
        }
        matcher = Parser.Code.matcher(str);
        if(matcher.find()){
            if(this.current.self instanceof Code){
                this.current = this.current.parent;
                return null;
            }
            Block struct = new Code();
            this.current = this.current.appendChild(struct);
            return matcher.replaceFirst("");
        }
        return str;
    }

    private static Pattern Title = Pattern.compile("^#{1,6}\\ ");
    private static Pattern Math = Pattern.compile("^\\${1,3}");
    private static Pattern Quote = Pattern.compile("^>");
    private static Pattern Space = Pattern.compile("^\\ *$");
    private static Pattern Code = Pattern.compile("^```\\ *$");

    private static String S_IMAGE = "!\\[.*\\]\\(.*\\)";
    private static Pattern IMAGE = Pattern.compile(S_IMAGE);
    private static Pattern INLINE = Pattern.compile(S_IMAGE);
}