// generated with ast extension for cup
// version 0.8
// 12/3/2022 21:59:59


package rs.ac.bg.etf.pp1.ast;

public class ActualParameters extends ActPars {

    private ActParams ActParams;

    public ActualParameters (ActParams ActParams) {
        this.ActParams=ActParams;
        if(ActParams!=null) ActParams.setParent(this);
    }

    public ActParams getActParams() {
        return ActParams;
    }

    public void setActParams(ActParams ActParams) {
        this.ActParams=ActParams;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActParams!=null) ActParams.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParams!=null) ActParams.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParams!=null) ActParams.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParameters(\n");

        if(ActParams!=null)
            buffer.append(ActParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParameters]");
        return buffer.toString();
    }
}
