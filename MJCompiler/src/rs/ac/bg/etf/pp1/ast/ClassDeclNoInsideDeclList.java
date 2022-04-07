// generated with ast extension for cup
// version 0.8
// 7/3/2022 22:16:21


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclNoInsideDeclList extends ClassDeclLine {

    private String I1;
    private ExtendedClass ExtendedClass;
    private VarDeclList VarDeclList;

    public ClassDeclNoInsideDeclList (String I1, ExtendedClass ExtendedClass, VarDeclList VarDeclList) {
        this.I1=I1;
        this.ExtendedClass=ExtendedClass;
        if(ExtendedClass!=null) ExtendedClass.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public ExtendedClass getExtendedClass() {
        return ExtendedClass;
    }

    public void setExtendedClass(ExtendedClass ExtendedClass) {
        this.ExtendedClass=ExtendedClass;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExtendedClass!=null) ExtendedClass.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendedClass!=null) ExtendedClass.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendedClass!=null) ExtendedClass.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclNoInsideDeclList(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(ExtendedClass!=null)
            buffer.append(ExtendedClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclNoInsideDeclList]");
        return buffer.toString();
    }
}
