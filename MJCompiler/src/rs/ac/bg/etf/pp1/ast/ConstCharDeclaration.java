// generated with ast extension for cup
// version 0.8
// 15/5/2022 22:24:6


package src.rs.ac.bg.etf.pp1.ast;

public class ConstCharDeclaration extends ConstDecl {

    private String constName;
    private Character charConstValue;

    public ConstCharDeclaration (String constName, Character charConstValue) {
        this.constName=constName;
        this.charConstValue=charConstValue;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Character getCharConstValue() {
        return charConstValue;
    }

    public void setCharConstValue(Character charConstValue) {
        this.charConstValue=charConstValue;
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
        buffer.append("ConstCharDeclaration(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        buffer.append(" "+tab+charConstValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstCharDeclaration]");
        return buffer.toString();
    }
}
