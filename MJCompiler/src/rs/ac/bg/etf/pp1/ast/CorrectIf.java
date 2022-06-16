// generated with ast extension for cup
// version 0.8
// 16/5/2022 22:48:18


package rs.ac.bg.etf.pp1.ast;

public class CorrectIf extends IfStatement {

    private IfStatementStart IfStatementStart;
    private Condition Condition;
    private IfStatementEnd IfStatementEnd;

    public CorrectIf (IfStatementStart IfStatementStart, Condition Condition, IfStatementEnd IfStatementEnd) {
        this.IfStatementStart=IfStatementStart;
        if(IfStatementStart!=null) IfStatementStart.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.IfStatementEnd=IfStatementEnd;
        if(IfStatementEnd!=null) IfStatementEnd.setParent(this);
    }

    public IfStatementStart getIfStatementStart() {
        return IfStatementStart;
    }

    public void setIfStatementStart(IfStatementStart IfStatementStart) {
        this.IfStatementStart=IfStatementStart;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public IfStatementEnd getIfStatementEnd() {
        return IfStatementEnd;
    }

    public void setIfStatementEnd(IfStatementEnd IfStatementEnd) {
        this.IfStatementEnd=IfStatementEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfStatementStart!=null) IfStatementStart.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(IfStatementEnd!=null) IfStatementEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfStatementStart!=null) IfStatementStart.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(IfStatementEnd!=null) IfStatementEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfStatementStart!=null) IfStatementStart.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(IfStatementEnd!=null) IfStatementEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CorrectIf(\n");

        if(IfStatementStart!=null)
            buffer.append(IfStatementStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfStatementEnd!=null)
            buffer.append(IfStatementEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CorrectIf]");
        return buffer.toString();
    }
}
