// generated with ast extension for cup
// version 0.8
// 3/3/2022 19:41:32


package rs.ac.bg.etf.pp1.ast;

public class NoMoreOrConditions extends ConditionTermList {

    public NoMoreOrConditions () {
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
        buffer.append("NoMoreOrConditions(\n");

        buffer.append(tab);
        buffer.append(") [NoMoreOrConditions]");
        return buffer.toString();
    }
}
