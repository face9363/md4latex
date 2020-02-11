package src.structure.block;

public class MathMode extends Block{
    public MathMode(int mode){
        super();
        this.mode = mode;
    }

    public int mode;

    @Override
    public String prevDump(){
        String str = "";
        if(this.mode == 1){
            str = "$";
        }
        else if(this.mode == 2){
            str = "\n\\begin{gather}";
        }
        else if(this.mode == 3){
            str = "\n\\begin{align}";
        }
        return str;
    }

    @Override
    public String nextDump(){
        String str = "";
        if(this.mode == 1){
            str = "$";
        }
        else if(this.mode == 2){
            str = "\\end{gather}\n";
        }
        else if(this.mode == 3){
            str = "\\end{align}\n";
        }
        return str;
    }
}