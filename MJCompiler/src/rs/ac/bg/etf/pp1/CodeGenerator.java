package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPC;

	public int getMainPC() {
		return mainPC;
	}
	
	public void visit(PrintStmtNoNumConst print) {
		if(print.getExpr().struct == Tab.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	public void visit(FactorNumberConstant numberConst) {
		Obj con = Tab.insert(Obj.Con, "$", numberConst.struct);
		con.setLevel(0);
		con.setAdr(numberConst.getN1());
		
		Code.load(con);
	}
	
	public void visit(NoRetMethodTypeName methodName) {
		if("main".equals(methodName.getMethodName())) {
			mainPC = Code.pc;
		}
		
		methodName.obj.setAdr(Code.pc);
		SyntaxNode node = methodName.getParent();
		VarCounter varCounter = new VarCounter();
		node.traverseTopDown(varCounter);
		
		FormParamCounter formParamCounter = new FormParamCounter();
		node.traverseTopDown(formParamCounter);
		
		Code.put(Code.enter);
		Code.put(formParamCounter.getCount());
		Code.put(formParamCounter.getCount() + varCounter.getCount());
	}
	
	public void visit(TypeRetMethodTypeName methodName) {
		if("main".equals(methodName.getMethodName())) {
			mainPC = Code.pc;
		}
		
		methodName.obj.setAdr(Code.pc);
		SyntaxNode node = methodName.getParent();
		VarCounter varCounter = new VarCounter();
		node.traverseTopDown(varCounter);
		
		FormParamCounter formParamCounter = new FormParamCounter();
		node.traverseTopDown(formParamCounter);
		
		Code.put(Code.enter);
		Code.put(formParamCounter.getCount());
		Code.put(formParamCounter.getCount() + varCounter.getCount());
	}
	
	public void visit(MethodDeclaration method) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
}
