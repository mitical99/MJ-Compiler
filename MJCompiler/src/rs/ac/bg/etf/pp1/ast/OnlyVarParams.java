// generated with ast extension for cup
// version 0.8
// 24/5/2022 21:51:15


package rs.ac.bg.etf.pp1.ast;

public class OnlyVarParams extends FormPars {

    private VarParams VarParams;

    public OnlyVarParams (VarParams VarParams) {
        this.VarParams=VarParams;
        if(VarParams!=null) VarParams.setParent(this);
    }

    public VarParams getVarParams() {
        return VarParams;
    }

    public void setVarParams(VarParams VarParams) {
        this.VarParams=VarParams;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarParams!=null) VarParams.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarParams!=null) VarParams.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarParams!=null) VarParams.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OnlyVarParams(\n");

        if(VarParams!=null)
            buffer.append(VarParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OnlyVarParams]");
        return buffer.toString();
    }
}
