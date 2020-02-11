package src.structure.block;

public class Title extends Block{
    
    public Title(int mode){
        super();
        this.mode = mode;
    }

    public int mode;

    @Override
    public String prevDump(){
        String str = "";
        if(this.mode == 1){
            str = "section";
        }
        else if(this.mode == 2){
            str = "subsection";
        }
        else if(this.mode == 3){
            str = "subsubsection";
        }
        else if(this.mode == 4){
            str = "paragraph";
        }
        else if(this.mode == 5 || this.mode == 6){
            str = "subparagraph";
        }
        return "\n\\" + str + "{";
    }

    @Override
    public String nextDump(){
        return "}\n";
    }
}