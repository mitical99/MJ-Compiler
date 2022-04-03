// generated with ast extension for cup
// version 0.8
// 3/3/2022 18:15:3


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String name;
    private DesignatorFieldList DesignatorFieldList;

    public Designator (String name, DesignatorFieldList DesignatorFieldList) {
        this.name=name;
        this.DesignatorFieldList=DesignatorFieldList;
        if(DesignatorFieldList!=null) DesignatorFieldList.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public DesignatorFieldList getDesignatorFieldList() {
        return DesignatorFieldList;
    }

    public void setDesignatorFieldList(DesignatorFieldList DesignatorFieldList) {
        this.DesignatorFieldList=DesignatorFieldList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorFieldList!=null) DesignatorFieldList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorFieldList!=null) DesignatorFieldList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorFieldList!=null) DesignatorFieldList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(DesignatorFieldList!=null)
            buffer.append(DesignatorFieldList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}
