// generated with ast extension for cup
// version 0.8
// 11/3/2022 21:18:49


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclarations extends ProgDecl {

    private ClassDeclLine ClassDeclLine;

    public ClassDeclarations (ClassDeclLine ClassDeclLine) {
        this.ClassDeclLine=ClassDeclLine;
        if(ClassDeclLine!=null) ClassDeclLine.setParent(this);
    }

    public ClassDeclLine getClassDeclLine() {
        return ClassDeclLine;
    }

    public void setClassDeclLine(ClassDeclLine ClassDeclLine) {
        this.ClassDeclLine=ClassDeclLine;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassDeclLine!=null) ClassDeclLine.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassDeclLine!=null) ClassDeclLine.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassDeclLine!=null) ClassDeclLine.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclarations(\n");

        if(ClassDeclLine!=null)
            buffer.append(ClassDeclLine.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclarations]");
        return buffer.toString();
    }
}
