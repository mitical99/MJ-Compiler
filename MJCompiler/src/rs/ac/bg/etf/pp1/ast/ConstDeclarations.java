// generated with ast extension for cup
// version 0.8
// 15/5/2022 22:24:6


package src.rs.ac.bg.etf.pp1.ast;

public class ConstDeclarations extends ProgDecl {

    private ConstDeclLine ConstDeclLine;

    public ConstDeclarations (ConstDeclLine ConstDeclLine) {
        this.ConstDeclLine=ConstDeclLine;
        if(ConstDeclLine!=null) ConstDeclLine.setParent(this);
    }

    public ConstDeclLine getConstDeclLine() {
        return ConstDeclLine;
    }

    public void setConstDeclLine(ConstDeclLine ConstDeclLine) {
        this.ConstDeclLine=ConstDeclLine;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclLine!=null) ConstDeclLine.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclLine!=null) ConstDeclLine.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclLine!=null) ConstDeclLine.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclarations(\n");

        if(ConstDeclLine!=null)
            buffer.append(ConstDeclLine.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclarations]");
        return buffer.toString();
    }
}
