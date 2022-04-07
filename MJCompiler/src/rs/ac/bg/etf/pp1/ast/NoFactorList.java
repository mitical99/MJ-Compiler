// generated with ast extension for cup
// version 0.8
// 7/3/2022 22:16:21


package rs.ac.bg.etf.pp1.ast;

public class NoFactorList extends MulFactorList {

    public NoFactorList () {
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
        buffer.append("NoFactorList(\n");

        buffer.append(tab);
        buffer.append(") [NoFactorList]");
        return buffer.toString();
    }
}
