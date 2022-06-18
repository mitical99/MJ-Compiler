// generated with ast extension for cup
// version 0.8
// 17/5/2022 21:52:38


package rs.ac.bg.etf.pp1.ast;

public class ConstLineDeclaration extends ConstDeclLine {

    private Type Type;
    private ConstDeclLineList ConstDeclLineList;

    public ConstLineDeclaration (Type Type, ConstDeclLineList ConstDeclLineList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstDeclLineList=ConstDeclLineList;
        if(ConstDeclLineList!=null) ConstDeclLineList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstDeclLineList getConstDeclLineList() {
        return ConstDeclLineList;
    }

    public void setConstDeclLineList(ConstDeclLineList ConstDeclLineList) {
        this.ConstDeclLineList=ConstDeclLineList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ConstDeclLineList!=null) ConstDeclLineList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstDeclLineList!=null) ConstDeclLineList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstDeclLineList!=null) ConstDeclLineList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstLineDeclaration(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclLineList!=null)
            buffer.append(ConstDeclLineList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstLineDeclaration]");
        return buffer.toString();
    }
}
