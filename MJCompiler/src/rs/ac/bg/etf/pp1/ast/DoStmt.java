// generated with ast extension for cup
// version 0.8
// 15/5/2022 22:24:6


package src.rs.ac.bg.etf.pp1.ast;

public class DoStmt extends SingleStatement {

    private DoWhileDepthCounterHelper DoWhileDepthCounterHelper;
    private Statement Statement;
    private Condition Condition;

    public DoStmt (DoWhileDepthCounterHelper DoWhileDepthCounterHelper, Statement Statement, Condition Condition) {
        this.DoWhileDepthCounterHelper=DoWhileDepthCounterHelper;
        if(DoWhileDepthCounterHelper!=null) DoWhileDepthCounterHelper.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
    }

    public DoWhileDepthCounterHelper getDoWhileDepthCounterHelper() {
        return DoWhileDepthCounterHelper;
    }

    public void setDoWhileDepthCounterHelper(DoWhileDepthCounterHelper DoWhileDepthCounterHelper) {
        this.DoWhileDepthCounterHelper=DoWhileDepthCounterHelper;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DoWhileDepthCounterHelper!=null) DoWhileDepthCounterHelper.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DoWhileDepthCounterHelper!=null) DoWhileDepthCounterHelper.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DoWhileDepthCounterHelper!=null) DoWhileDepthCounterHelper.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DoStmt(\n");

        if(DoWhileDepthCounterHelper!=null)
            buffer.append(DoWhileDepthCounterHelper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DoStmt]");
        return buffer.toString();
    }
}
