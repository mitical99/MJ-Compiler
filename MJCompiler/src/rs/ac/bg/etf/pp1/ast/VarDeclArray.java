// generated with ast extension for cup
// version 0.8
// 7/3/2022 22:16:21


package rs.ac.bg.etf.pp1.ast;

public class VarDeclArray extends VarDeclLine {

    private Type Type;
    private VarDeclLineList VarDeclLineList;

    public VarDeclArray (Type Type, VarDeclLineList VarDeclLineList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclLineList=VarDeclLineList;
        if(VarDeclLineList!=null) VarDeclLineList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarDeclLineList getVarDeclLineList() {
        return VarDeclLineList;
    }

    public void setVarDeclLineList(VarDeclLineList VarDeclLineList) {
        this.VarDeclLineList=VarDeclLineList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarDeclLineList!=null) VarDeclLineList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclLineList!=null) VarDeclLineList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclLineList!=null) VarDeclLineList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclArray(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclLineList!=null)
            buffer.append(VarDeclLineList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclArray]");
        return buffer.toString();
    }
}
