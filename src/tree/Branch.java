package src.tree;
import src.structure.Structure;
import java.util.ArrayList;

public class Branch{
    public Structure self;
    public Branch parent;
    public ArrayList<Branch> children;

    public Branch(Structure self, Branch parent){
        this.children = new ArrayList<>();
        this.self = self;
        this.parent = parent;
    }

    public Branch appendChild(Structure target) {
        Branch newBranch = new Branch(target, this);
        this.children.add(newBranch);
        return newBranch;
    }

    public void explore(StringBuilder val){
        val.append(this.self.prevDump());
        for(Branch child: children){
            child.explore(val);
        }
        val.append(this.self.nextDump());
    }
}