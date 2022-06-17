// generated with ast extension for cup
// version 0.8
// 17/5/2022 16:38:12


package rs.ac.bg.etf.pp1.ast;

public class Conditions extends Condition {

    private Condition Condition;
    private OrConditionBlockEnd OrConditionBlockEnd;
    private ConditionTerm ConditionTerm;

    public Conditions (Condition Condition, OrConditionBlockEnd OrConditionBlockEnd, ConditionTerm ConditionTerm) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.OrConditionBlockEnd=OrConditionBlockEnd;
        if(OrConditionBlockEnd!=null) OrConditionBlockEnd.setParent(this);
        this.ConditionTerm=ConditionTerm;
        if(ConditionTerm!=null) ConditionTerm.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public OrConditionBlockEnd getOrConditionBlockEnd() {
        return OrConditionBlockEnd;
    }

    public void setOrConditionBlockEnd(OrConditionBlockEnd OrConditionBlockEnd) {
        this.OrConditionBlockEnd=OrConditionBlockEnd;
    }

    public ConditionTerm getConditionTerm() {
        return ConditionTerm;
    }

    public void setConditionTerm(ConditionTerm ConditionTerm) {
        this.ConditionTerm=ConditionTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(OrConditionBlockEnd!=null) OrConditionBlockEnd.accept(visitor);
        if(ConditionTerm!=null) ConditionTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(OrConditionBlockEnd!=null) OrConditionBlockEnd.traverseTopDown(visitor);
        if(ConditionTerm!=null) ConditionTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(OrConditionBlockEnd!=null) OrConditionBlockEnd.traverseBottomUp(visitor);
        if(ConditionTerm!=null) ConditionTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Conditions(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OrConditionBlockEnd!=null)
            buffer.append(OrConditionBlockEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionTerm!=null)
            buffer.append(ConditionTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Conditions]");
        return buffer.toString();
    }
}
