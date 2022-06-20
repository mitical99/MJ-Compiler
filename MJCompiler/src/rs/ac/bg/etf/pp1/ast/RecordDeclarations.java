// generated with ast extension for cup
// version 0.8
// 18/5/2022 21:59:8


package rs.ac.bg.etf.pp1.ast;

public class RecordDeclarations extends ProgDecl {

    private RecordDeclLine RecordDeclLine;

    public RecordDeclarations (RecordDeclLine RecordDeclLine) {
        this.RecordDeclLine=RecordDeclLine;
        if(RecordDeclLine!=null) RecordDeclLine.setParent(this);
    }

    public RecordDeclLine getRecordDeclLine() {
        return RecordDeclLine;
    }

    public void setRecordDeclLine(RecordDeclLine RecordDeclLine) {
        this.RecordDeclLine=RecordDeclLine;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RecordDeclLine!=null) RecordDeclLine.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RecordDeclLine!=null) RecordDeclLine.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RecordDeclLine!=null) RecordDeclLine.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RecordDeclarations(\n");

        if(RecordDeclLine!=null)
            buffer.append(RecordDeclLine.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RecordDeclarations]");
        return buffer.toString();
    }
}
