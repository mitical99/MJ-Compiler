package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.*;
import org.apache.log4j.Logger;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	int printCallCount = 0;
	int varDeclCount = 0;
	int formParamCount = 0;
	Struct currLineType = null;
	Struct currRecord = null;
	Obj currMethod = null;
	String addOp = null;
	String mulOp = null;
	String relOp = null;
	boolean isRecordField = false;
	public static final Struct boolType = new Struct(Struct.Bool);
	boolean returnFound = false;
	boolean errorDetected = false;
	int nVars;
	
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
		currMethod.setLevel(formParamCount);
		
		Tab.chainLocalSymbols(currMethod);
		currMethod = null;
		formParamCount = 0;
		Tab.closeScope();
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
	
//	public void visit(FactorNewObjectNoExpr factor) {
//		factor.struct = currLineType;
//	}
	
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
		//TO-DO
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
