// generated with ast extension for cup
// version 0.8
// 16/3/2022 22:58:49


package rs.ac.bg.etf.pp1.ast;

public class ErrorIf extends IfStatement {

    public ErrorIf () {
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
        buffer.append("ErrorIf(\n");

        buffer.append(tab);
        buffer.append(") [ErrorIf]");
        return buffer.toString();
    }
}