// generated with ast extension for cup
// version 0.8
// 6/3/2022 22:17:28


package rs.ac.bg.etf.pp1.ast;

public class DesignatorFields extends DesignatorFieldList {

    private DesignatorFieldList DesignatorFieldList;
    private DesignatorField DesignatorField;

    public DesignatorFields (DesignatorFieldList DesignatorFieldList, DesignatorField DesignatorField) {
        this.DesignatorFieldList=DesignatorFieldList;
        if(DesignatorFieldList!=null) DesignatorFieldList.setParent(this);
        this.DesignatorField=DesignatorField;
        if(DesignatorField!=null) DesignatorField.setParent(this);
    }

    public DesignatorFieldList getDesignatorFieldList() {
        return DesignatorFieldList;
    }

    public void setDesignatorFieldList(DesignatorFieldList DesignatorFieldList) {
        this.DesignatorFieldList=DesignatorFieldList;
    }

    public DesignatorField getDesignatorField() {
        return DesignatorField;
    }

    public void setDesignatorField(DesignatorField DesignatorField) {
        this.DesignatorField=DesignatorField;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorFieldList!=null) DesignatorFieldList.accept(visitor);
        if(DesignatorField!=null) DesignatorField.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorFieldList!=null) DesignatorFieldList.traverseTopDown(visitor);
        if(DesignatorField!=null) DesignatorField.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorFieldList!=null) DesignatorFieldList.traverseBottomUp(visitor);
        if(DesignatorField!=null) DesignatorField.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorFields(\n");

        if(DesignatorFieldList!=null)
            buffer.append(DesignatorFieldList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorField!=null)
            buffer.append(DesignatorField.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorFields]");
        return buffer.toString();
    }
}
