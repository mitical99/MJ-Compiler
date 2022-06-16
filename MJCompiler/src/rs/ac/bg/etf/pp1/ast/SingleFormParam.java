// generated with ast extension for cup
// version 0.8
// 16/5/2022 22:48:18


package rs.ac.bg.etf.pp1.ast;

public class SingleFormParam extends FormParams {

    private OneFormParam OneFormParam;

    public SingleFormParam (OneFormParam OneFormParam) {
        this.OneFormParam=OneFormParam;
        if(OneFormParam!=null) OneFormParam.setParent(this);
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
        if(OneFormParam!=null) OneFormParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OneFormParam!=null) OneFormParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OneFormParam!=null) OneFormParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleFormParam(\n");

        if(OneFormParam!=null)
            buffer.append(OneFormParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleFormParam]");
        return buffer.toString();
    }
}
