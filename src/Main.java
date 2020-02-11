package src;
import src.converter.ToLatex;

public class Main{
    public static void main(String[] args){
        if(args.length<1){
            System.out.println("No file found. pass correct file/to/your/layout.tex");
            return;
        }
        ToLatex converter = new ToLatex();
        converter.readLayoutTex(args[0]);
        converter.dump();
    }
}