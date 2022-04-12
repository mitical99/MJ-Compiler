package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	private int varDeclCount = 0;
	private int formParamCount = 0;
	private int doWhileCounter = 0;
	private Stack<List<Struct>> actualArgFunctionStack = new Stack<List<Struct>>();
	private Struct currLineType = null;
	private Struct currRecord = null;
	private Obj currMethod = null;
	private String addOp = null;
	private String mulOp = null;
	private String relOp = null;
	private boolean isRecordField = false;
	private boolean mainFound = false;
	public static final Struct boolType = new Struct(Struct.Bool);
	private boolean returnFound = false;
	private boolean errorDetected = false;
	private int nVars;
	
	public SemanticAnalyzer() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
	}
	
	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}
	
	public void visit(Program program) {
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		
		if(typeNode == Tab.noObj) {
			report_error("Type " + type.getTypeName() + "not found in symbol table!", type);
			type.struct = Tab.noType;
		} else {
			if(typeNode.getKind() == Obj.Type) {
				type.struct = typeNode.getType();
			} else {
				type.struct = Tab.noType;
				report_error("Type name isn't in symbol table", type);
			}
		}
		
		currLineType = type.struct;
	}
	
	private boolean checkForConstDeclErrors(String constName, Struct constType) {
		if(Tab.find(constName) != Tab.noObj) { //present in table already
			if(Tab.currentScope.findSymbol(constName) != null) { //present in table and in the same scope
			report_error("Constant with name" + constName + "already exists in symbol table", null);
			return true;
			}
		}
		if(!currLineType.equals(constType)) { //Error in type constant and assigned value
			report_error("Incompatible types of assigned value and declared constant type!", null);
			return true;
		}
		return false;
	}
	
	
	public void visit(ConstNumberDeclaration numConst) {
		String constName = numConst.getConstName();
		Integer constValue = numConst.getNumConstValue();
		if(this.checkForConstDeclErrors(constName, Tab.intType))
			return;
		Obj constNode = Tab.insert(Obj.Con, constName, Tab.intType);
		constNode.setAdr(constValue);
	}
	
	public void visit(ConstCharDeclaration charConst) {
		String constName = charConst.getConstName();
		Character constValue = charConst.getCharConstValue();
		if(this.checkForConstDeclErrors(constName, Tab.charType))
			return;
		Obj constNode = Tab.insert(Obj.Con, constName, Tab.charType);
		constNode.setAdr(constValue);
	}
	
	public void visit(ConstBoolDeclaration boolConst) {
		String constName = boolConst.getConstName();
		Boolean constValue = boolConst.getBoolConstValue();
		if(this.checkForConstDeclErrors(constName, boolType))
			return;
		Obj constNode = Tab.insert(Obj.Con, constName, boolType);
		if(constValue)
			constNode.setAdr(1);
		else
			constNode.setAdr(0);
	}
	
	
	private boolean checkForVarDeclErrors(String varName) {
		if(Tab.find(varName) != Tab.noObj) {
			if(Tab.currentScope.findSymbol(varName) != null) {
			report_error("Variable with name" + varName + "already exists in symbol table", null);
			return true;
			}
		}
		return false;
	}
	
	public void visit(Var var) {
		String varName = var.getVarName();
		if(this.checkForVarDeclErrors(varName)) 
			return;
		Obj varNode = Tab.insert(Obj.Var, varName, currLineType);
	}
	
	public void visit(ArrayVar arrayVar) {
		String varName = arrayVar.getVarName();
		if(this.checkForVarDeclErrors(varName)) 
			return;
		Struct varType = new Struct(Struct.Array, currLineType);
		Obj varNode = null;
		if(isRecordField)
			varNode = Tab.insert(Obj.Fld, varName, varType);
		else
			varNode = Tab.insert(Obj.Var, varName, varType);
	}
	
	private boolean checkForRecordDeclErrors(String recordName) {
		if(Tab.find(recordName) != Tab.noObj) {
			report_error("Record name" + recordName + "already declared", null);
			return true;
		}
		return false;
	}
	
	public void visit(RecordName recordName) {
		String recName = recordName.getRecordName();
		if(this.checkForRecordDeclErrors(recName))
			return;
		
		isRecordField = true;
		currRecord = new Struct(Struct.Class);
		currRecord.setElementType(Tab.noType);
		recordName.obj = Tab.insert(Obj.Type, recName, currRecord);
		Tab.openScope();
	}
	
	public void visit(RecordDeclaration recordDecl) {
		Tab.chainLocalSymbols(currRecord);
		Tab.closeScope();
		currRecord = null;
		isRecordField = false;
	}
	
	private boolean insertMethodInSymTable(String methodName, Struct retType) {
		if(Tab.find(methodName) != Tab.noObj) {
			return false;
		}
		Obj methodNode = Tab.insert(Obj.Meth, methodName, retType);
		Tab.openScope();
		currMethod = methodNode;
		return true;
	}
	
	public void visit(NoRetMethodTypeName voidMethodName) {
		String methodName = voidMethodName.getMethodName();
		if(!this.insertMethodInSymTable(methodName, Tab.noType)) {
			report_error("Symbol with name " + methodName + " already exists in symbol table!", voidMethodName);
		}
	}
	
	public void visit(TypeRetMethodTypeName typeMethodName) {
		String methodName = typeMethodName.getMethodName();
		
		if(!this.insertMethodInSymTable(methodName, currLineType)) {
			report_error("Symbol with name " + methodName + " already exists in symbol table!", typeMethodName);
		}
	}
	
	public void visit(OneElemParam singleParam) {
		String paramName = singleParam.getParamName();
		if(this.checkForVarDeclErrors(paramName)) {
			return;
		}
		Obj paramNode = Tab.insert(Obj.Var, paramName, currLineType);
		formParamCount++;
	}
	
	public void visit(ArrayParam arrayParam) {
		String paramName = arrayParam.getParamName();
		if(this.checkForVarDeclErrors(paramName)) {
			return;
		}
		Struct varType = new Struct(Struct.Array, currLineType);
		Obj paramNode = Tab.insert(Obj.Var, paramName, varType);
		formParamCount++;
	}
	
	public void visit(MethodDeclaration method) {
		
		if(!returnFound && currMethod.getType() != Tab.noType) {
			report_error("Function doesn't have correct return value!", method);
			return;
		}
		currMethod.setLevel(formParamCount);
		
		if(currMethod.getName().equals("main") && currMethod.getLevel() == 0) {
			mainFound = true;
		}
		
		Tab.chainLocalSymbols(currMethod);
		returnFound = false;
		currMethod = null;
		formParamCount = 0;
		Tab.closeScope();
	}
	
	public void visit(FirstParam param) {
		actualArgFunctionStack.peek().add(param.getExpr().struct);
	}
	
	public void visit(MoreParameters param) {
		actualArgFunctionStack.peek().add(param.getExpr().struct);
	}
	
	public void visit(FactorNumberConstant factor) {
		factor.struct = Tab.intType;
	}
	
	public void visit(FactorCharConstant factor) {
		factor.struct = Tab.charType;
	}
	
	public void visit(FactorBoolConstant factor) {
		factor.struct = boolType;
	}
	
	public void visit(FunctionNameDesignator funcCall) {
		funcCall.obj = funcCall.getDesignator().obj;
		
		actualArgFunctionStack.push(new ArrayList<Struct>());
	}
	
	//PRINT PROCESSING
	
	private boolean checkPrintStmt(Struct exprStruct, SyntaxNode info) {
		if(exprStruct != Tab.intType && exprStruct != Tab.charType && exprStruct != SemanticAnalyzer.boolType) {
			report_error("Variable in print statement isn't base type!", info);
			return false;
		}
		return true;
	}
	
	public void visit(PrintStmtNoNumConst printStmt) {
		Struct expr = printStmt.getExpr().struct;
		checkPrintStmt(expr, printStmt);
	}
	
	public void visit(PrintStmtNumConst printStmt) {
		Struct expr = printStmt.getExpr().struct;
		checkPrintStmt(expr, printStmt);
	}
	
	//READ PROCESSING
	
	public void visit(ReadStmt readStmt) {
		Obj designator = readStmt.getDesignator().obj;
		if(!checkRightValueType(designator, readStmt)) {
			return;
		}
		Struct designatorType = designator.getType();
		if(designatorType != Tab.intType && designatorType != Tab.charType 
				&& designatorType != SemanticAnalyzer.boolType) {
			report_error("Variable type in read statement must be int, char or bool type!", readStmt);
		}
	}
	
	//DO-WHILE PROCESSING
	
	public void visit(DoWhileCounter doWhile) {
		doWhileCounter++;
	}
	
	public void visit(DoStmt doWhile) {
		doWhileCounter--;
	}
	
	//BREAK AND CONTINUE PROCESSING
	
	public void visit(BreakStmt breakStmt) {
		if(doWhileCounter == 0) {
			report_error("Break statement must be inside do-while loop", breakStmt);
		}
	}
	
	public void visit(ContinueStmt continueStmt) {
		if(doWhileCounter == 0) {
			report_error("Continue statement must be inside do-while loop", continueStmt);
		}
	}
	
	//ASSIGN STATEMENT PROCESSING
	
	public void visit(AssignStmt assignStmt) {
		Obj leftOperator = assignStmt.getDesignator().obj;
		Struct rightType = assignStmt.getExpr().struct;
		if(!checkRightValueType(leftOperator, assignStmt)) {
			return;
		}
		//check type compatibility
		if(!rightType.assignableTo(leftOperator.getType())) {
			report_error("Incompatible types in assigment to a variable " + leftOperator.getName(), assignStmt);
		}
	}
	
	
	private boolean checkRightValueType(Obj node, SyntaxNode info) {
		if(node == Tab.noObj)
			return false;
		int kind = node.getKind();
		if(kind != Obj.Var && kind != Obj.Elem && kind != Obj.Fld) {
			report_error("Wrong right value type in operation!", info);
			return false;
		}
		return true;
	}
	//INC AND DEC PROCESSING
	
	public void visit(IncStmt incStmt) {
		Obj node = incStmt.getDesignator().obj;
		if(!checkRightValueType(node, incStmt)) {
			return;
		}
		
		if(node.getType() != Tab.intType) {
			report_error("Increment can be done only on integer type variables!", incStmt);
		}
	}
	
	public void visit(DecStmt decStmt) {
		Obj node = decStmt.getDesignator().obj;
		if(!checkRightValueType(node, decStmt)) {
			return;
		}
		
		if(node.getType() != Tab.intType) {
			report_error("Decrement can be done only on integer type variables!", decStmt);
		}
	}
	//EXPRESSIONS PROCESSING
	
	public void visit(NegativeExpr expr) {
		if(expr.getTerm().struct != Tab.intType) {
			report_error("Used term must be int type!", expr);
			expr.struct = Tab.noType;
			return;
		}
		expr.struct = Tab.intType;
	}
	
	public void visit(PositiveExpr expr) {
		expr.struct = expr.getTerm().struct;
	}
	
	public void visit(MoreExpr exprList) {
		Struct exprStruct = exprList.getExpr().struct, termStruct = exprList.getTerm().struct;
		if(exprStruct != Tab.intType || termStruct != Tab.intType) {
			report_error("Terms in add operation must be int type!", exprList);
			exprList.struct = Tab.noType;
			return;
		}
//		if(!exprStruct.compatibleWith(termStruct)) {
//			report_error("Term types aren't compatible!", exprList);
//			exprList.struct = Tab.noType;
//			return;
//		}
		exprList.struct = Tab.intType;
	}
	
	//TERMS PROCESSING
	
	public void visit(Terms termList) {
		termList.struct = termList.getMulFactorList().struct;
	}
	
	public void visit(FactorList factorList) {
		Struct factor = factorList.getFactor().struct, factors = factorList.getMulFactorList().struct;
		if(factor != Tab.intType || factors != Tab.intType) {
			report_error("Factors in multiply operation must be int type!", factorList);
			factorList.struct = Tab.noType;
			return;
		}
//		if(!factor.compatibleWith(factors)) {
//			report_error("Factors in multiply operation aren't compatible!", factorList);
//			factorList.struct = Tab.noType;
//			return;
//		}
		
		factorList.struct = Tab.intType;
	}
	
	public void visit(SingleFactor factor) {
		factor.struct = factor.getFactor().struct;
	}
	
	//FUNCTION RETURN PROCESSING
	
	public void visit(ReturnNoExprStmt returnExpr) {
		if(this.currMethod == null) {
			report_error("Return statement found outside of function!", returnExpr);
			return;
		}
		if(this.currMethod.getType() != Tab.noType) {
			report_error("Function must return a value of type " + currMethod.getType(), returnExpr);
			return;
		}
		returnFound = true;
	}
	
	public void visit(ReturnExprStmt returnExpr) {
		Struct expr = returnExpr.getExpr().struct;
		if(currMethod == null) {
			report_error("Return statement found outside of function!", returnExpr);
			return;
		}
		if(currMethod.getType() != expr) {
			returnFound = true;
		}
	}
	
	//CONDITIONS PROCESSING
	
	
	public void visit(CondFactTwoExpr condFact) {
		Struct leftExpr = condFact.getExpr().struct, rightExpr = condFact.getExpr1().struct;
		if(!leftExpr.compatibleWith(rightExpr)) {
			report_error("Types aren't compatible!", condFact);
			return;
		}
		
		if(leftExpr.getKind() == Struct.Array || rightExpr.getKind() == Struct.Array
				|| leftExpr.getKind() == Struct.Class || rightExpr.getKind() == Struct.Class) {
			if(relOp != "==" && relOp != "!=") {
				report_error("Unsupported operation on type variables! Acceptable operations are == or !=", condFact);
				return;
			}
		}
		
	}
	
	public void visit(CondFactOneExpr condFact) {
		Struct expr = condFact.getExpr().struct;
		if(expr != SemanticAnalyzer.boolType) {
			report_error("Condition isn't bool type!", condFact);
		}
	}
	
	//FUNCTIONS PROCESSING
	
	private boolean checkFunctionCall(String functionName, SyntaxNode info) {
		Obj function = Tab.find(functionName);
		if(function == Tab.noObj) {
			report_error("Called function " + functionName + " not declared yet!", info);
			return false;
		}
		//check if used typename is a function
		if(function.getKind() != Obj.Meth) {
			report_error("Used variable " + functionName + " isn't a function!", info);
			return false;
		}
		//check number of arguments
		List<Obj> formalArgList = (List<Obj>) function.getLocalSymbols();
		List<Struct> actualArgList = actualArgFunctionStack.pop();
		if(actualArgList.size() != function.getLevel()) {
			report_error("Number of formal and actuals arguments doesn't match!", info);
			return false;
		}
		//check compatibility
		for(int i = 0; i < function.getLevel(); i++) {
			if(!actualArgList.get(i).compatibleWith(formalArgList.get(i).getType())) {
				report_error("Error in " +  i + 1 + " argument: Not compatible types!", info);
				return false;
			}
		}
		return true;
	}
	
	public void visit(FactorDesignatorWithAct functionCall) {
		String functionName = functionCall. getFunctionName().obj.getName();
		Obj function = Tab.find(functionName);
		if(!checkFunctionCall(functionName, functionCall)) {
			return;
		}
		functionCall.struct = function.getType();
	}
	
	public void visit(FuncCall designatorFuncCall) {
		String functionName = designatorFuncCall.getFunctionName().obj.getName();
		Obj function = Tab.find(functionName);
		if(!checkFunctionCall(functionName, designatorFuncCall)) {
			return;
		}
		
	}
	
	//DESIGNATOR PROCESSING
	public void visit(FactorDesignatorOnly factor) {
		factor.struct = factor.getDesignator().obj.getType();
	}
	
	public void visit(DesignatorOnly designator) {
		String name = designator.getName();
		Obj node = Tab.find(name);
		if(node == Tab.noObj) {
			report_error("Variable with name " + name + " not found in symbol table!", designator);
			return;
		}
		designator.obj = node; 
	}
	
	public void visit(ArrayElemDesignator arrayDesignator) {
		String name = arrayDesignator.getDesignator().obj.getName();
		if(arrayDesignator.getDesignator().obj.getType().getKind() != Struct.Array) {
			report_error("Variable with name " + name + "isn't array!" , arrayDesignator);
			arrayDesignator.obj = Tab.noObj;
			return;
		}
		if(arrayDesignator.getExpr().struct != Tab.intType) {
			report_error("Index of an array must be integer type!", arrayDesignator);
			arrayDesignator.obj = Tab.noObj;
			return;
		}
		Struct type = arrayDesignator.getDesignator().obj.getType().getElemType();
		
		arrayDesignator.obj = new Obj(Obj.Elem, name, type);
	}
	
	public void visit(StructFieldDesignator structDesignator) {
		String name = structDesignator.getDesignator().obj.getName();
		String selectedField = structDesignator.getField();
		Obj recordObj = structDesignator.getDesignator().obj; 
		if(recordObj == Tab.noObj) {
			report_error("Typename " + name + " isn't in symbol table!", structDesignator);
			structDesignator.obj = Tab.noObj;
			return;
		}
		if(recordObj.getType().getKind() != Struct.Class) {
			report_error("Typename " + name + " is not record type!", structDesignator);
			structDesignator.obj = Tab.noObj;
			return;
		}
		
		ArrayList<Obj> fields = (ArrayList<Obj>) recordObj.getType().getMembers();
		
		for(Obj field : fields) {
			if(selectedField.equals(field.getName())) {
				structDesignator.obj = field;
				return;
			}
		}
		
		report_error("Struct doesn't contain field with name " + selectedField, structDesignator);
		
	}
	
	public void visit(DoubleEqual op) {
		relOp = "==";
	}
	
	public void visit(NotEqual op) {
		relOp = "!=";
	}
	
	public void visit(Greater op) {
		relOp = ">";
	}
	
	public void visit(GreaterOrEqual op) {
		relOp = ">=";
	}
	
	public void visit(Less op) {
		relOp = "<";
	}
	
	public void visit(LessOrEqual op) {
		relOp = "<=";
	}
	
	public void visit(Plus op) {
		addOp = "+";
	}
	
	public void visit(Minus op) {
		addOp = "-";
	}
	
	public void visit(Multiply op) {
		mulOp = "*";
	}
	
	public void visit(Division op) {
		mulOp = "/";
	}
	
	public void visit(Moduo op) {
		mulOp = "%";
	}
	
    public boolean passed() {
    	return !errorDetected;
    }
}
