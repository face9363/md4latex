package src;
import src.converter.ToLatex;
import src.parser.Parser;

public class Main{
    public static void main(String[] args){
        Parser parser = new Parser();
        ToLatex converter = new ToLatex(parser.root);

        String file = converter.readFile(args[0]);
        parser.parse(file);

        converter.dumper();
        converter.dump();
    }
}