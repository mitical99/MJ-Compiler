// generated with ast extension for cup
// version 0.8
// 24/5/2022 21:51:15


package rs.ac.bg.etf.pp1.ast;

public class VarList extends VarDeclLineList {

    private VarDeclLineList VarDeclLineList;
    private VarDecl VarDecl;

    public VarList (VarDeclLineList VarDeclLineList, VarDecl VarDecl) {
        this.VarDeclLineList=VarDeclLineList;
        if(VarDeclLineList!=null) VarDeclLineList.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public VarDeclLineList getVarDeclLineList() {
        return VarDeclLineList;
    }

    public void setVarDeclLineList(VarDeclLineList VarDeclLineList) {
        this.VarDeclLineList=VarDeclLineList;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclLineList!=null) VarDeclLineList.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclLineList!=null) VarDeclLineList.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclLineList!=null) VarDeclLineList.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarList(\n");

        if(VarDeclLineList!=null)
            buffer.append(VarDeclLineList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarList]");
        return buffer.toString();
    }
}
