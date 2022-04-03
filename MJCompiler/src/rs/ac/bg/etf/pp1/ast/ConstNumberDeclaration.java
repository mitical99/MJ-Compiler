// generated with ast extension for cup
// version 0.8
// 3/3/2022 14:51:33


package rs.ac.bg.etf.pp1.ast;

public class ConstNumberDeclaration extends ConstDecl {

    private String varName;
    private Integer constValue;

    public ConstNumberDeclaration (String varName, Integer constValue) {
        this.varName=varName;
        this.constValue=constValue;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public Integer getConstValue() {
        return constValue;
    }

    public void setConstValue(Integer constValue) {
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
        buffer.append("ConstNumberDeclaration(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        buffer.append(" "+tab+constValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstNumberDeclaration]");
        return buffer.toString();
    }
}
