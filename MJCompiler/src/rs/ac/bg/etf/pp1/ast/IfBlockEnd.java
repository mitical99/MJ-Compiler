// generated with ast extension for cup
// version 0.8
// 18/5/2022 21:59:9


package rs.ac.bg.etf.pp1.ast;

public class IfBlockEnd extends IfStatementEnd {

    public IfBlockEnd () {
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
        buffer.append("IfBlockEnd(\n");

        buffer.append(tab);
        buffer.append(") [IfBlockEnd]");
        return buffer.toString();
    }
}
