package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.*;
import org.apache.log4j.Logger;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	int printCallCount = 0;
	int varDeclCount = 0;
	Obj currentMethod = null;
	Obj currentVarTypeLine = null;
	Struct currLineType = null;
	boolean returnFound = false;
	boolean errorDetected = false;
	int nVars;
	
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
	
	
	public void visit(ArrayVar arrayVarDecl) {
		Obj varNode = Tab.insert(Obj.Var, arrayVarDecl.getVarName(), currLineType);
	}
	
	public void visit(SingleVar var) {
		var.struct = currLineType;
	}
	
	public void visit(Var varDecl) {
		Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), currLineType);
	}
	
	
	
	public void visit(NoRetMethodTypeName methodTypeName) {
		currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethodName(), Tab.noType);
		methodTypeName.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethodName(), methodTypeName);
	}
	
	public void visit(TypeRetMethodTypeName methodTypeName) {
		currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethodName(), methodTypeName.getType().struct);
		methodTypeName.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethodName(), methodTypeName);
	}
	
	public void visit(MethodDeclaration methodDecl) {
		if(!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funkcija " + currentMethod.getName() + " nema return iskaz!", null);
		}
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		returnFound = false;
		currentMethod = null;
	}
	
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if(typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola!", null);
			type.struct = Tab.noType;
		} else {
			if(Obj.Type == typeNode.getKind()) {
				 type.struct = typeNode.getType();
				 currLineType = typeNode.getType();
			} else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
	}
	
	public void visit(Designator designator) {
		Obj obj = Tab.find(designator.getName());
		if(obj == Tab.noObj) {
			report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.getName()+" nije deklarisano! ", null);
		}
		designator.obj = obj;
	}
	
	public void visit(FactorDesignatorWithAct function) {
		Obj func = function.getDesignator().obj;
		if(Obj.Meth == func.getKind()) {
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + function.getLine(), null);
			function.struct = func.getType();
		} else {
			report_error("Greska na liniji " + function.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
			function.struct = Tab.noType;
		}
	}
	
	public void visit(PositiveExpr expr) {
		expr.struct = expr.getTerm().struct;
	}
	
	public void visit(NegativeExpr expr) {
		expr.struct = expr.getTerm().struct;
	}
	
	public void visit(Terms term) {
		term.struct = term.getFactor().struct;
	}
	
	public void visit(FactorNumberConstant cnst) {
		cnst.struct = Tab.intType;
	}
	
	public void visit(FactorCharConsant cnst) {
		cnst.struct = Tab.charType;
	}
	
	public void visit(FactorDesignatorOnly var) {
		var.struct = var.getDesignator().obj.getType();
	}
	
	public void visit(ReturnExprStmt returnExpr) {
		returnFound = true;
		Struct currMethodType = currentMethod.getType();
		if(!currMethodType.compatibleWith(returnExpr.getExpr().struct)) {
			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
		}
	}
	
	public void visit(ConstCharDeclaration charDecl) {
		Obj node = Tab.insert(Obj.Con, charDecl.getVarName(), currLineType);
		
	}
	
	public void visit(ConstNumberDeclaration numDecl) {
		Obj node = Tab.insert(Obj.Con, numDecl.getVarName(), currLineType);
		node.setAdr(numDecl.getConstValue());
	}
	
	public void visit(ConstBoolDeclaration boolDecl) {
		Obj node = Tab.insert(Obj.Con, boolDecl.getVarName(), currLineType);
	}
	
	
    public boolean passed() {
    	return !errorDetected;
    }
}
