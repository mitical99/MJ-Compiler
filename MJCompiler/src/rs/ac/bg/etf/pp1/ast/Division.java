// generated with ast extension for cup
// version 0.8
// 15/5/2022 22:24:7


package src.rs.ac.bg.etf.pp1.ast;

public class Division extends Mulop {

    public Division () {
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
        buffer.append("Division(\n");

        buffer.append(tab);
        buffer.append(") [Division]");
        return buffer.toString();
    }
}
