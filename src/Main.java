package src;
import src.parser.Parser;

public class Main{
    public static void main(String[] args){
        Parser parser = new Parser();
        parser.parse("sample/sample.md");
    }
}