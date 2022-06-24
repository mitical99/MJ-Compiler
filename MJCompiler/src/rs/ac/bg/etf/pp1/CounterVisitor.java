package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.*;

public class CounterVisitor extends VisitorAdaptor {
	protected int count;

	public int getCount() {
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor {
		
		@Override
		public void visit(OneElemParam formalParameterDeclaration) {
			count++;
		}
		
		public void visit(ArrayParam formalParameterDeclaration) {
			count++;
		}
		
		public void visit(VarArgs formalParameterDeclaration) {
			count++;
		}
		
	}

	public static class VarCounter extends CounterVisitor {
		@Override
		public void visit(Var varDecl) {
			count++;
		}
		
		public void visit(ArrayVar varDecl) {
			count++;
		}
		
	}
}
