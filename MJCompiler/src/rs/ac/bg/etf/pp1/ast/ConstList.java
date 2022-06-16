// generated with ast extension for cup
// version 0.8
// 15/5/2022 22:24:6


package src.rs.ac.bg.etf.pp1.ast;

public class ConstList extends ConstDeclLineList {

    private ConstDeclLineList ConstDeclLineList;
    private ConstDecl ConstDecl;

    public ConstList (ConstDeclLineList ConstDeclLineList, ConstDecl ConstDecl) {
        this.ConstDeclLineList=ConstDeclLineList;
        if(ConstDeclLineList!=null) ConstDeclLineList.setParent(this);
        this.ConstDecl=ConstDecl;
        if(ConstDecl!=null) ConstDecl.setParent(this);
    }

    public ConstDeclLineList getConstDeclLineList() {
        return ConstDeclLineList;
    }

    public void setConstDeclLineList(ConstDeclLineList ConstDeclLineList) {
        this.ConstDeclLineList=ConstDeclLineList;
    }

    public ConstDecl getConstDecl() {
        return ConstDecl;
    }

    public void setConstDecl(ConstDecl ConstDecl) {
        this.ConstDecl=ConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclLineList!=null) ConstDeclLineList.accept(visitor);
        if(ConstDecl!=null) ConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclLineList!=null) ConstDeclLineList.traverseTopDown(visitor);
        if(ConstDecl!=null) ConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclLineList!=null) ConstDeclLineList.traverseBottomUp(visitor);
        if(ConstDecl!=null) ConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstList(\n");

        if(ConstDeclLineList!=null)
            buffer.append(ConstDeclLineList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDecl!=null)
            buffer.append(ConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstList]");
        return buffer.toString();
    }
}
