// generated with ast extension for cup
// version 0.8
// 18/5/2022 21:59:9


package rs.ac.bg.etf.pp1.ast;

public class FormAndVarParams extends FormPars {

    private FormParams FormParams;
    private VarParams VarParams;

    public FormAndVarParams (FormParams FormParams, VarParams VarParams) {
        this.FormParams=FormParams;
        if(FormParams!=null) FormParams.setParent(this);
        this.VarParams=VarParams;
        if(VarParams!=null) VarParams.setParent(this);
    }

    public FormParams getFormParams() {
        return FormParams;
    }

    public void setFormParams(FormParams FormParams) {
        this.FormParams=FormParams;
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
        if(FormParams!=null) FormParams.accept(visitor);
        if(VarParams!=null) VarParams.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParams!=null) FormParams.traverseTopDown(visitor);
        if(VarParams!=null) VarParams.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParams!=null) FormParams.traverseBottomUp(visitor);
        if(VarParams!=null) VarParams.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormAndVarParams(\n");

        if(FormParams!=null)
            buffer.append(FormParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarParams!=null)
            buffer.append(VarParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormAndVarParams]");
        return buffer.toString();
    }
}
