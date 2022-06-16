package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.ac.bg.etf.pp1.ast.AssignStmt;
import rs.ac.bg.etf.pp1.ast.Multiply;
import rs.ac.bg.etf.pp1.ast.FactorBoolConstant;
import rs.ac.bg.etf.pp1.ast.ReadStmt;
import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPC;

	public int getMainPC() {
		return mainPC;
	}

	// PRINT

	public void visit(PrintStmtNoNumConst print) {
		if (print.getExpr().struct == Tab.intType || print.getExpr().struct == SemanticAnalyzer.boolType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}

	public void visit(PrintStmtNumConst print) {
		Code.loadConst(print.getWidth());
		if (print.getExpr().struct == Tab.intType || print.getExpr().struct == SemanticAnalyzer.boolType) {
			Code.put(Code.print);
		} else {
			Code.put(Code.bprint);
		}
	}

	// READ

	public void visit(ReadStmt read) {
		if (read.getDesignator().obj.getType() == Tab.intType) {
			Code.put(Code.read);
		} else {
			Code.put(Code.bread);
		}

		Code.store(read.getDesignator().obj);
	}

	// CONSTANT FACTORS

	public void visit(FactorCharConstant charConst) {
		Obj con = Tab.insert(Obj.Con, "$const", Tab.charType);
		con.setLevel(0);
		con.setAdr(charConst.getC1());

		Code.load(con);
	}

	public void visit(FactorNumberConstant numberConst) {
		Obj con = Tab.insert(Obj.Con, "$const", Tab.intType);
		con.setLevel(0);
		con.setAdr(numberConst.getN1());

		Code.load(con);
	}

	public void visit(FactorBoolConstant boolConst) {
		Obj con = Tab.insert(Obj.Con, "$const", SemanticAnalyzer.boolType);
		con.setLevel(0);
		if (boolConst.getB1()) {
			con.setAdr(1);
		} else {
			con.setAdr(0);
		}

		Code.load(con);
	}

	// NEW OPERATOR FACTORS

	public void visit(FactorNewObjectExpr newArrayExpr) {
		Code.put(Code.newarray);

		if (newArrayExpr.getType().struct == Tab.charType) {
			Code.put(0);
		} else {
			Code.put(1);
		}
	}
	
	public void visit(FactorNewObjectNoExpr newStructExpr) {
		Code.put(Code.new_);
		
		int numOfFields = newStructExpr.struct.getNumberOfFields();
		Code.put2(numOfFields * 4);
	}

	public void visit(NoRetMethodTypeName methodName) {
		if ("main".equals(methodName.getMethodName())) {
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
		if ("main".equals(methodName.getMethodName())) {
			mainPC = Code.pc;
			//hehe
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

	// ARITMETHIC OPERATIONS

	public void visit(FactorList mulOperation) {
		if (mulOperation.getMulop() instanceof Multiply) {
			Code.put(Code.mul);
		} else if (mulOperation.getMulop() instanceof Division) {
			Code.put(Code.div);
		} else {
			Code.put(Code.rem);
		}

	}

	public void visit(MoreExpr addOperation) {
		if (addOperation.getAddop() instanceof Plus) {
			Code.put(Code.add);
		} else {
			Code.put(Code.sub);
		}
	}

	// ASSIGN OPERATION

	public void visit(AssignStmt assignment) {
		Code.store(assignment.getDesignator().obj);
	}

	// INC AND DEC OPERATION

	public void visit(IncStmt incStatement) {
//		Code.loadConst(1);
//		Code.put(Code.add);
//		
//		Code.store(incStatement.getDesignator().obj);

		Code.loadConst(1);
		Code.put(Code.inc);
	}

	public void visit(DecStmt decStatement) {
		Code.loadConst(-1);
		Code.put(Code.inc);
	}

	// FACTORS

	public void visit(FactorDesignatorOnly factorDesignator) {
		Code.load(factorDesignator.getDesignator().obj);
	}

	// DESIGNATOR

	public void visit(StructFieldDesignator structDesignator) {
		Code.load(structDesignator.getDesignator().obj);
	}

	public void visit(ArrayElemDesignator arrayDesignator) {
		Code.load(arrayDesignator.getDesignator().obj);
	}

//	public void visit(DesignatorOnly designator) {
//		SyntaxNode parent = designator.getParent();
//		if (parent instanceof ArrayElemDesignator) {
//			
//		} else if (parent instanceof StructFieldDesignator) {
//
//		}
//	}
	
}
