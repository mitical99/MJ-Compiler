// generated with ast extension for cup
// version 0.8
// 6/3/2022 22:17:28


package rs.ac.bg.etf.pp1.ast;

public class AddTerm extends AdditionalTerm {

    private AdditionalTerm AdditionalTerm;
    private Addop Addop;
    private Term Term;

    public AddTerm (AdditionalTerm AdditionalTerm, Addop Addop, Term Term) {
        this.AdditionalTerm=AdditionalTerm;
        if(AdditionalTerm!=null) AdditionalTerm.setParent(this);
        this.Addop=Addop;
        if(Addop!=null) Addop.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public AdditionalTerm getAdditionalTerm() {
        return AdditionalTerm;
    }

    public void setAdditionalTerm(AdditionalTerm AdditionalTerm) {
        this.AdditionalTerm=AdditionalTerm;
    }

    public Addop getAddop() {
        return Addop;
    }

    public void setAddop(Addop Addop) {
        this.Addop=Addop;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AdditionalTerm!=null) AdditionalTerm.accept(visitor);
        if(Addop!=null) Addop.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AdditionalTerm!=null) AdditionalTerm.traverseTopDown(visitor);
        if(Addop!=null) Addop.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AdditionalTerm!=null) AdditionalTerm.traverseBottomUp(visitor);
        if(Addop!=null) Addop.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddTerm(\n");

        if(AdditionalTerm!=null)
            buffer.append(AdditionalTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Addop!=null)
            buffer.append(Addop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddTerm]");
        return buffer.toString();
    }
}
