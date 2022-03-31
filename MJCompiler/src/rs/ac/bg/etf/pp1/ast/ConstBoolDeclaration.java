// generated with ast extension for cup
// version 0.8
// 13/1/2022 21:9:23


package rs.ac.bg.etf.pp1.ast;

public class ConstBoolDeclaration extends ConstDecl {

    private String varName;
    private Boolean constValue;

    public ConstBoolDeclaration (String varName, Boolean constValue) {
        this.varName=varName;
        this.constValue=constValue;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public Boolean getConstValue() {
        return constValue;
    }

    public void setConstValue(Boolean constValue) {
        this.constValue=constValue;
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
        buffer.append("ConstBoolDeclaration(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        buffer.append(" "+tab+constValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstBoolDeclaration]");
        return buffer.toString();
    }
}
