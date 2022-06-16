// generated with ast extension for cup
// version 0.8
// 16/5/2022 22:48:18


package rs.ac.bg.etf.pp1.ast;

public class DoStmt extends SingleStatement {

    private DoWhileDepthCounterHelper DoWhileDepthCounterHelper;
    private Statement Statement;
    private DoWhileConditionStart DoWhileConditionStart;
    private Condition Condition;
    private DoWhileConditionEnd DoWhileConditionEnd;

    public DoStmt (DoWhileDepthCounterHelper DoWhileDepthCounterHelper, Statement Statement, DoWhileConditionStart DoWhileConditionStart, Condition Condition, DoWhileConditionEnd DoWhileConditionEnd) {
        this.DoWhileDepthCounterHelper=DoWhileDepthCounterHelper;
        if(DoWhileDepthCounterHelper!=null) DoWhileDepthCounterHelper.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.DoWhileConditionStart=DoWhileConditionStart;
        if(DoWhileConditionStart!=null) DoWhileConditionStart.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.DoWhileConditionEnd=DoWhileConditionEnd;
        if(DoWhileConditionEnd!=null) DoWhileConditionEnd.setParent(this);
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

    public DoWhileConditionStart getDoWhileConditionStart() {
        return DoWhileConditionStart;
    }

    public void setDoWhileConditionStart(DoWhileConditionStart DoWhileConditionStart) {
        this.DoWhileConditionStart=DoWhileConditionStart;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public DoWhileConditionEnd getDoWhileConditionEnd() {
        return DoWhileConditionEnd;
    }

    public void setDoWhileConditionEnd(DoWhileConditionEnd DoWhileConditionEnd) {
        this.DoWhileConditionEnd=DoWhileConditionEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DoWhileDepthCounterHelper!=null) DoWhileDepthCounterHelper.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(DoWhileConditionStart!=null) DoWhileConditionStart.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(DoWhileConditionEnd!=null) DoWhileConditionEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DoWhileDepthCounterHelper!=null) DoWhileDepthCounterHelper.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(DoWhileConditionStart!=null) DoWhileConditionStart.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(DoWhileConditionEnd!=null) DoWhileConditionEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DoWhileDepthCounterHelper!=null) DoWhileDepthCounterHelper.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(DoWhileConditionStart!=null) DoWhileConditionStart.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(DoWhileConditionEnd!=null) DoWhileConditionEnd.traverseBottomUp(visitor);
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

        if(DoWhileConditionStart!=null)
            buffer.append(DoWhileConditionStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DoWhileConditionEnd!=null)
            buffer.append(DoWhileConditionEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DoStmt]");
        return buffer.toString();
    }
}
