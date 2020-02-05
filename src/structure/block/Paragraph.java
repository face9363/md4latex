package src.structure.block;

public class Paragraph extends Block{
    public String content;

    public Paragraph(String content){
        super();
        this.content = content;
    }
    @Override
    public String prevDump(){
        return content;
    }

    @Override
    public String nextDump(){
        return "";
    }
}