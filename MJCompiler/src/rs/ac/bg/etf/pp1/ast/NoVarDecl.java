// generated with ast extension for cup
// version 0.8
// 13/1/2022 21:9:23


package rs.ac.bg.etf.pp1.ast;

public class NoVarDecl extends VarDeclList {

    public NoVarDecl () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoVarDecl(\n");

        buffer.append(tab);
        buffer.append(") [NoVarDecl]");
        return buffer.toString();
    }
}
