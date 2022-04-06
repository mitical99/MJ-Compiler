// generated with ast extension for cup
// version 0.8
// 6/3/2022 22:17:28


package rs.ac.bg.etf.pp1.ast;

public class NoMoreAndConditions extends CondFactList {

    public NoMoreAndConditions () {
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
        buffer.append("NoMoreAndConditions(\n");

        buffer.append(tab);
        buffer.append(") [NoMoreAndConditions]");
        return buffer.toString();
    }
}
