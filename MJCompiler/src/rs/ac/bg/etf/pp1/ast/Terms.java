// generated with ast extension for cup
// version 0.8
// 6/3/2022 22:17:28


package rs.ac.bg.etf.pp1.ast;

public class Terms extends Term {

    private Factor Factor;
    private MulFactorList MulFactorList;

    public Terms (Factor Factor, MulFactorList MulFactorList) {
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
        this.MulFactorList=MulFactorList;
        if(MulFactorList!=null) MulFactorList.setParent(this);
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public MulFactorList getMulFactorList() {
        return MulFactorList;
    }

    public void setMulFactorList(MulFactorList MulFactorList) {
        this.MulFactorList=MulFactorList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Factor!=null) Factor.accept(visitor);
        if(MulFactorList!=null) MulFactorList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
        if(MulFactorList!=null) MulFactorList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        if(MulFactorList!=null) MulFactorList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Terms(\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MulFactorList!=null)
            buffer.append(MulFactorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Terms]");
        return buffer.toString();
    }
}
