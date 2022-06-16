// generated with ast extension for cup
// version 0.8
// 15/5/2022 22:24:6


package src.rs.ac.bg.etf.pp1.ast;

public class UnmatchedIf extends Unmatched {

    private IfStatement IfStatement;
    private Statement Statement;

    public UnmatchedIf (IfStatement IfStatement, Statement Statement) {
        this.IfStatement=IfStatement;
        if(IfStatement!=null) IfStatement.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public IfStatement getIfStatement() {
        return IfStatement;
    }

    public void setIfStatement(IfStatement IfStatement) {
        this.IfStatement=IfStatement;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfStatement!=null) IfStatement.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfStatement!=null) IfStatement.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfStatement!=null) IfStatement.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("UnmatchedIf(\n");

        if(IfStatement!=null)
            buffer.append(IfStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [UnmatchedIf]");
        return buffer.toString();
    }
}
