// generated with ast extension for cup
// version 0.8
// 16/3/2022 22:58:49


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarations extends ProgDecl {

    private VarDeclLine VarDeclLine;

    public VarDeclarations (VarDeclLine VarDeclLine) {
        this.VarDeclLine=VarDeclLine;
        if(VarDeclLine!=null) VarDeclLine.setParent(this);
    }

    public VarDeclLine getVarDeclLine() {
        return VarDeclLine;
    }

    public void setVarDeclLine(VarDeclLine VarDeclLine) {
        this.VarDeclLine=VarDeclLine;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclLine!=null) VarDeclLine.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclLine!=null) VarDeclLine.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclLine!=null) VarDeclLine.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarations(\n");

        if(VarDeclLine!=null)
            buffer.append(VarDeclLine.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarations]");
        return buffer.toString();
    }
}
