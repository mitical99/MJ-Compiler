package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.ac.bg.etf.pp1.ast.AssignStmt;
import rs.ac.bg.etf.pp1.ast.Multiply;
import rs.ac.bg.etf.pp1.ast.FactorBoolConstant;
import rs.ac.bg.etf.pp1.ast.ReadStmt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPC;
	
	private int formParamCountCurrMethod = 0;
	private Stack<Integer> actualParamCountCurrMethodStack = new Stack<>();
	private int actualParamCountCurrMethod = 0;
	private Struct methodVarArgType = null;
	private Map<String, Struct> varArgMethodType = new HashMap<>();
	private Map<String, Integer> varArgsMethodFormParamCount = new HashMap<>();
	private boolean methodWithVarArgs = false;
	private Stack<List<Integer>> patchAddressPositionsOrCondition = new Stack<>();
	private Stack<List<Integer>> patchAddressPositionsAndCondition = new Stack<>();
	private Stack<List<Integer>> patchAddressPositionsElseCondition = new Stack<>();
	private Stack<Integer> doWhileStartAddresses = new Stack<>();
	private Stack<List<Integer>> patchAddressPositionsContinueStatement = new Stack<>();
	private Stack<List<Integer>> patchAddressPositionsBreakStatement = new Stack<>();


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
		if (read.getDesignator().obj.getType() == Tab.charType) {
			Code.put(Code.bread);
		} else {
			Code.put(Code.read);
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
		formParamCountCurrMethod = formParamCounter.getCount();
		Code.put(formParamCounter.getCount() + varCounter.getCount());
	}

	//FUNCTION PROCESSING
	
	public void visit(VarArgs varArg) {
		methodWithVarArgs = true;
		methodVarArgType = varArg.getType().struct;
	}
	
	public void visit(TypeRetMethodTypeName methodName) {
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
		formParamCountCurrMethod = formParamCounter.getCount();
		Code.put(formParamCounter.getCount() + varCounter.getCount());
	}

	public void visit(MethodDeclaration method) {
		Code.put(Code.exit);
		Code.put(Code.return_);
		if(methodWithVarArgs) {
			varArgsMethodFormParamCount.put(method.getMethodTypeName().obj.getName(), formParamCountCurrMethod);
			varArgMethodType.put(method.getMethodTypeName().obj.getName(), methodVarArgType);
		}
		
		methodVarArgType = null;
		methodWithVarArgs = false;
		formParamCountCurrMethod = 0;
	}
	
	public void visit(FunctionNameDesignator funcCallName) {
		if(varArgsMethodFormParamCount.containsKey(funcCallName.obj.getName())) {
			methodWithVarArgs = true;
			//actualParamCountCurrMethod = 0;
			actualParamCountCurrMethodStack.push(0);
		}
	}
	
	private void prepareVarArgMethodCall(String funcName) {
		int formalParamCount = varArgsMethodFormParamCount.get(funcName);
		int arraySize = actualParamCountCurrMethodStack.peek() - formalParamCount + 1;
		
		Code.loadConst(arraySize);
		Code.put(Code.newarray);
		if(varArgMethodType.get(funcName) == Tab.charType) {
			Code.put(0);
		} else {
			Code.put(1);
		}
		
		for(int i = arraySize - 1; i >= 0; i--) { // ..., __, adr.array -- __ is current actual parameter value
			Code.put(Code.dup_x1); // ..., adr.array, __, adr.array
			Code.put(Code.dup_x1); // ..., adr.array, adr.array, __, adr.array
			Code.loadConst(i); // ..., adr.array, adr.array, __, adr.array, index
			Code.put(Code.dup_x2); //..., adr.array, adr.array, index, __, adr.array, index
			Code.put(Code.pop); //..., adr.array, adr.array, index, __, adr.array
			Code.put(Code.pop); //..., adr.array, adr.array, index, __
			if(varArgMethodType.get(funcName) == Tab.charType) { //..., adr.array
				Code.put(Code.bastore);
			} else {
				Code.put(Code.astore);
			}
		}
	}
	
	public void visit(FuncCall functionCall) { //function call in statement, not in assignment
		
		String funcName = functionCall.getFunctionName().obj.getName();
		if("ord".equals(funcName) || "chr".equals(funcName)) {
			return;
		}
		if("len".equals(funcName)) {
			Code.put(Code.arraylength);
			return;
		}
		
		if(methodWithVarArgs) {
			this.prepareVarArgMethodCall(funcName);
		}
		
		
		Code.put(Code.call);
		int functionAdr = functionCall.getFunctionName().obj.getAdr();
		Code.put2(functionAdr - Code.pc + 1);
		
		if(functionCall.getFunctionName().obj.getType() != Tab.noType) { //should pop return value, so exprStack stays consistent
			Code.put(Code.pop);
		}
		if(methodWithVarArgs) {
			actualParamCountCurrMethodStack.pop();
			if(actualParamCountCurrMethodStack.isEmpty()) {		
				methodWithVarArgs = false;
				//actualParamCountCurrMethod = 0;
			}	
		}
	}
	
	public void visit(FactorDesignatorWithAct functionCall) { // function call in assignment
		
		String funcName = functionCall.getFunctionName().obj.getName();
		if("ord".equals(funcName) || "chr".equals(funcName)) {
			return;
		}
		if("len".equals(funcName)) {
			Code.put(Code.arraylength);
			return;
		}
		
		if(methodWithVarArgs) {
			this.prepareVarArgMethodCall(funcName);
		}
		
		Code.put(Code.call);
		int functionAdr = functionCall.getFunctionName().obj.getAdr();
		
		Code.put2(functionAdr - Code.pc + 1);
		if(methodWithVarArgs) {
			actualParamCountCurrMethodStack.pop();
			if(actualParamCountCurrMethodStack.isEmpty()) {		
				methodWithVarArgs = false;
				//actualParamCountCurrMethod = 0;
			}	
		}
	}
	
	public void visit(ReturnNoExprStmt returnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(ReturnExprStmt returnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	//PARAMETERS IN FUNCION PROCESSING
	
	public void visit(FirstParam actParam) {
		if(methodWithVarArgs) {	
			int numParam = actualParamCountCurrMethodStack.pop();
			actualParamCountCurrMethodStack.push(++numParam);
			//actualParamCountCurrMethod++;
		}
	}
	
	public void visit(MoreParameters actParam) {
		if(methodWithVarArgs) {	
			int numParam = actualParamCountCurrMethodStack.pop();
			actualParamCountCurrMethodStack.push(++numParam);
			//actualParamCountCurrMethod++;
		}
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
			
		if(incStatement.getDesignator().obj.getKind() == Obj.Fld) {
			Code.put(Code.dup);
			Code.load(incStatement.getDesignator().obj);
		} else if(incStatement.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
			Code.load(incStatement.getDesignator().obj);
		} else if(incStatement.getDesignator().obj.getKind() == Obj.Var) {
			Code.load(incStatement.getDesignator().obj);
		}	
		Code.loadConst(1);
		Code.put(Code.add);
		
		Code.store(incStatement.getDesignator().obj);
	}

	public void visit(DecStmt decStatement) {
		
		if(decStatement.getDesignator().obj.getKind() == Obj.Fld) {
			Code.put(Code.dup);
			Code.load(decStatement.getDesignator().obj);
		} else if(decStatement.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
			Code.load(decStatement.getDesignator().obj);
		} else if(decStatement.getDesignator().obj.getKind() == Obj.Var) {
			Code.load(decStatement.getDesignator().obj);
		}
		
		Code.loadConst(1);
		Code.put(Code.sub);
		
		Code.store(decStatement.getDesignator().obj);

	}

	// FACTORS

	public void visit(FactorDesignatorOnly factorDesignator) {
		Code.load(factorDesignator.getDesignator().obj);
	}
	
	public void visit(NegativeExpr negExpr) {
		Code.put(Code.neg);
	}

	// DESIGNATOR

	public void visit(StructFieldDesignator structDesignator) {
		Code.load(structDesignator.getDesignator().obj);
		
	}

	public void visit(DesignatorArray arrayDesignator) {
		Code.load(arrayDesignator.getDesignator().obj);
	}
	
	//CONDITION STATEMENT PROCESSING
	
	public void visit(OrBlockEnd orBlockEnd) {
		Code.putJump(0); //patch later
		
		int patchesNeeded = patchAddressPositionsAndCondition.peek().size();
		for(int i = 0; i < patchesNeeded; i++) {
			int patchAdr = patchAddressPositionsAndCondition.peek().remove(0);
			Code.fixup(patchAdr);
		}	
		//patchAddressPositionsAndCondition.peek().clear();
		
		patchAddressPositionsOrCondition.peek().add(Code.pc - 2);
	}
	
	public void visit(CondFactOneExpr singleFact) {
		Code.loadConst(1);
		Code.putFalseJump(Code.eq, 0);
		
		patchAddressPositionsAndCondition.peek().add(Code.pc - 2);
		
	}
	
	public void visit(CondFactTwoExpr relationFact) {
		SyntaxNode operation = relationFact.getRelop();
		if(operation instanceof DoubleEqual) {
			Code.putFalseJump(Code.eq, 0);
		} else if(operation instanceof NotEqual) {
			Code.putFalseJump(Code.ne, 0);
		} else if(operation instanceof Greater) {
			Code.putFalseJump(Code.gt, 0);
		} else if(operation instanceof GreaterOrEqual) {
			Code.putFalseJump(Code.ge, 0);
		} else if(operation instanceof Less) {
			Code.putFalseJump(Code.lt, 0);
		} else {
			Code.putFalseJump(Code.le, 0);
		}
		
		patchAddressPositionsAndCondition.peek().add(Code.pc - 2);
	}
	
	public void visit(IfBlockEnd ifBlockEnd) {
		int orBlockPatchCount = patchAddressPositionsOrCondition.peek().size();
		for(int i = 0; i < orBlockPatchCount; i++) {
			int patchAdr = patchAddressPositionsOrCondition.peek().remove(0);
			Code.fixup(patchAdr);
		}
	}
	
	public void visit(IfBlockStart ifBlockStart) {
		patchAddressPositionsOrCondition.add(new ArrayList<>());
		patchAddressPositionsAndCondition.add(new ArrayList<>());
		patchAddressPositionsElseCondition.add(new ArrayList<>());

	}
	
	public void visit(IfElseEnd ifElseBlockEnd) {	
		SyntaxNode parent = ifElseBlockEnd.getParent();
		
		if(parent instanceof IfElseStmt) {
			Code.putJump(0); //patch later
			patchAddressPositionsElseCondition.peek().add(Code.pc - 2);
		}
		
		int andBlockPatchCount = patchAddressPositionsAndCondition.peek().size();
		
		for(int i = 0; i < andBlockPatchCount; i++) {
			Code.fixup(patchAddressPositionsAndCondition.peek().remove(0));
		}
		

	}
	
	public void visit(IfElseStmt ifElseStmt) {
		
		int elseBlockPatchCount = patchAddressPositionsElseCondition.peek().size();
		
		for(int i = 0; i < elseBlockPatchCount; i++) {
			Code.fixup(patchAddressPositionsElseCondition.peek().remove(0));
		}
		
		this.closeCurrentConditionBlock();
	}
	
	public void visit(IfStmt ifStmt) {
		this.closeCurrentConditionBlock();
	}
	
	private void closeCurrentConditionBlock() {
		patchAddressPositionsAndCondition.pop();
		patchAddressPositionsElseCondition.pop();
		patchAddressPositionsOrCondition.pop();
	}
	
	
	//DO WHILE PROCESSING
	
	public void visit(DoWhileCounter doWhileStart) {
		doWhileStartAddresses.push(Code.pc);
		patchAddressPositionsContinueStatement.push(new ArrayList<>());
		patchAddressPositionsBreakStatement.push(new ArrayList<>());
	}
	
	public void visit(DoStmt doStmt) {
		doWhileStartAddresses.pop();
		patchAddressPositionsBreakStatement.pop();
		patchAddressPositionsContinueStatement.pop();
		patchAddressPositionsAndCondition.pop();
		patchAddressPositionsOrCondition.pop();
	}
	
	public void visit(DoWhileCondStart doWhileCondStart) {
		int continueStatementPatchCount = patchAddressPositionsContinueStatement.peek().size();
		
		for(int i = 0; i < continueStatementPatchCount; i++) {
			Code.fixup(patchAddressPositionsContinueStatement.peek().remove(0));
		}
		
		patchAddressPositionsOrCondition.push(new ArrayList<>());
		patchAddressPositionsAndCondition.push(new ArrayList<>());
	}
	
	public void visit(DoWhileCondEnd doWhileCondEnd) {
		
		int doWhileStartAdr = doWhileStartAddresses.peek();
		Code.putJump(doWhileStartAdr);
		int breakStatementPatchCount = patchAddressPositionsBreakStatement.peek().size();
		
		for(int i = 0; i < breakStatementPatchCount; i++) {
			Code.fixup(patchAddressPositionsBreakStatement.peek().remove(0));
		}
		
		for(int orBlockPatchAdr : patchAddressPositionsOrCondition.peek()) {
			Code.put2(orBlockPatchAdr, doWhileStartAdr - orBlockPatchAdr + 1);
		}
		
		for(int andBlockPatchAdr : patchAddressPositionsAndCondition.peek()) {
			Code.fixup(andBlockPatchAdr);
		}
	}
	
	public void visit(ContinueStmt continueStmt) {
		Code.putJump(0); // patch later
		patchAddressPositionsContinueStatement.peek().add(Code.pc - 2);
	}
	
	public void visit(BreakStmt breakStmt) {
		Code.putJump(0); // patch later
		patchAddressPositionsBreakStatement.peek().add(Code.pc - 2);
	}
}
