// generated with ast extension for cup
// version 0.8
// 24/5/2022 21:51:15


package rs.ac.bg.etf.pp1.ast;

public class ConstNumberDeclaration extends ConstDecl {

    private String constName;
    private Integer numConstValue;

    public ConstNumberDeclaration (String constName, Integer numConstValue) {
        this.constName=constName;
        this.numConstValue=numConstValue;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Integer getNumConstValue() {
        return numConstValue;
    }

    public void setNumConstValue(Integer numConstValue) {
        this.numConstValue=numConstValue;
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
        buffer.append("ConstNumberDeclaration(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        buffer.append(" "+tab+numConstValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstNumberDeclaration]");
        return buffer.toString();
    }
}
