package src.structure.block;

import java.util.Random;

public class Code extends Block {

    public Code(){
        super();
    }

    @Override
    public String prevDump(){
        return "\n\\begin{lstlisting}[caption=,label="+(new Random()).nextInt(9999)+"]\n";
    }

    @Override
    public String nextDump(){
        return "\n\\end{lstlisting}\n";
    }
}