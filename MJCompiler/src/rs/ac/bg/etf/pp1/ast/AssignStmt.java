// generated with ast extension for cup
// version 0.8
// 7/3/2022 22:16:21


package rs.ac.bg.etf.pp1.ast;

public class AssignStmt extends DesignatorStatement {

    private Designator Designator;
    private Assignop Assignop;
    private AssignOrErrorStatement AssignOrErrorStatement;

    public AssignStmt (Designator Designator, Assignop Assignop, AssignOrErrorStatement AssignOrErrorStatement) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.AssignOrErrorStatement=AssignOrErrorStatement;
        if(AssignOrErrorStatement!=null) AssignOrErrorStatement.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public AssignOrErrorStatement getAssignOrErrorStatement() {
        return AssignOrErrorStatement;
    }

    public void setAssignOrErrorStatement(AssignOrErrorStatement AssignOrErrorStatement) {
        this.AssignOrErrorStatement=AssignOrErrorStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(Assignop!=null) Assignop.accept(visitor);
        if(AssignOrErrorStatement!=null) AssignOrErrorStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
        if(AssignOrErrorStatement!=null) AssignOrErrorStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        if(AssignOrErrorStatement!=null) AssignOrErrorStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssignStmt(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AssignOrErrorStatement!=null)
            buffer.append(AssignOrErrorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssignStmt]");
        return buffer.toString();
    }
}
