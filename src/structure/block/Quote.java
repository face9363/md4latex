package src.structure.block;

public class Quote extends Block{

    public Quote(){
        super();
        
    }

    @Override
    public String prevDump(){
        return "\n\\begin{quote}\n";
    }

    @Override
    public String nextDump(){
        return "\\end{quote}\n";
    }
}