// generated with ast extension for cup
// version 0.8
// 16/5/2022 22:48:18


package rs.ac.bg.etf.pp1.ast;

public class DoWhileCondEnd extends DoWhileConditionEnd {

    public DoWhileCondEnd () {
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
        buffer.append("DoWhileCondEnd(\n");

        buffer.append(tab);
        buffer.append(") [DoWhileCondEnd]");
        return buffer.toString();
    }
}
