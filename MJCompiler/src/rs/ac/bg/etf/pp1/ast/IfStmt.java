// generated with ast extension for cup
// version 0.8
// 18/5/2022 21:59:9


package rs.ac.bg.etf.pp1.ast;

public class IfStmt extends SingleStatement {

    private IfStatement IfStatement;
    private Statement Statement;
    private IfElseBlockEnd IfElseBlockEnd;

    public IfStmt (IfStatement IfStatement, Statement Statement, IfElseBlockEnd IfElseBlockEnd) {
        this.IfStatement=IfStatement;
        if(IfStatement!=null) IfStatement.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.IfElseBlockEnd=IfElseBlockEnd;
        if(IfElseBlockEnd!=null) IfElseBlockEnd.setParent(this);
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

    public IfElseBlockEnd getIfElseBlockEnd() {
        return IfElseBlockEnd;
    }

    public void setIfElseBlockEnd(IfElseBlockEnd IfElseBlockEnd) {
        this.IfElseBlockEnd=IfElseBlockEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfStatement!=null) IfStatement.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(IfElseBlockEnd!=null) IfElseBlockEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfStatement!=null) IfStatement.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(IfElseBlockEnd!=null) IfElseBlockEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfStatement!=null) IfStatement.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(IfElseBlockEnd!=null) IfElseBlockEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStmt(\n");

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

        if(IfElseBlockEnd!=null)
            buffer.append(IfElseBlockEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStmt]");
        return buffer.toString();
    }
}
