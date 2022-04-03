package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.*;
import org.apache.log4j.Logger;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	int printCallCount = 0;
	int varDeclCount = 0;
	Struct currLineType = null;
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
			report_error("Constant with name" + constName + "already exists in symbol table", null);
			return true;
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
		Obj varNode = Tab.insert(Obj.Var, varName, varType);
	}
	
	
    public boolean passed() {
    	return !errorDetected;
    }
}
