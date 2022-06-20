// generated with ast extension for cup
// version 0.8
// 18/5/2022 21:59:9


package rs.ac.bg.etf.pp1.ast;

public class StructFieldDesignator extends Designator {

    private Designator Designator;
    private String field;

    public StructFieldDesignator (Designator Designator, String field) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.field=field;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field=field;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StructFieldDesignator(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+field);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StructFieldDesignator]");
        return buffer.toString();
    }
}
