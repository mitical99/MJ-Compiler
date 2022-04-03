// generated with ast extension for cup
// version 0.8
// 3/3/2022 14:51:33


package rs.ac.bg.etf.pp1.ast;

public class PositiveExpr extends Expr {

    private Term Term;
    private AdditionalTerm AdditionalTerm;

    public PositiveExpr (Term Term, AdditionalTerm AdditionalTerm) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.AdditionalTerm=AdditionalTerm;
        if(AdditionalTerm!=null) AdditionalTerm.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public AdditionalTerm getAdditionalTerm() {
        return AdditionalTerm;
    }

    public void setAdditionalTerm(AdditionalTerm AdditionalTerm) {
        this.AdditionalTerm=AdditionalTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(AdditionalTerm!=null) AdditionalTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(AdditionalTerm!=null) AdditionalTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(AdditionalTerm!=null) AdditionalTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PositiveExpr(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AdditionalTerm!=null)
            buffer.append(AdditionalTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PositiveExpr]");
        return buffer.toString();
    }
}
