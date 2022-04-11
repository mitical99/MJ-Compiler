// generated with ast extension for cup
// version 0.8
// 11/3/2022 21:18:49


package rs.ac.bg.etf.pp1.ast;

public class MoreFormParam extends FormParams {

    private FormParams FormParams;
    private OneFormParam OneFormParam;

    public MoreFormParam (FormParams FormParams, OneFormParam OneFormParam) {
        this.FormParams=FormParams;
        if(FormParams!=null) FormParams.setParent(this);
        this.OneFormParam=OneFormParam;
        if(OneFormParam!=null) OneFormParam.setParent(this);
    }

    public FormParams getFormParams() {
        return FormParams;
    }

    public void setFormParams(FormParams FormParams) {
        this.FormParams=FormParams;
    }

    public OneFormParam getOneFormParam() {
        return OneFormParam;
    }

    public void setOneFormParam(OneFormParam OneFormParam) {
        this.OneFormParam=OneFormParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParams!=null) FormParams.accept(visitor);
        if(OneFormParam!=null) OneFormParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParams!=null) FormParams.traverseTopDown(visitor);
        if(OneFormParam!=null) OneFormParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParams!=null) FormParams.traverseBottomUp(visitor);
        if(OneFormParam!=null) OneFormParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreFormParam(\n");

        if(FormParams!=null)
            buffer.append(FormParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OneFormParam!=null)
            buffer.append(OneFormParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreFormParam]");
        return buffer.toString();
    }
}
