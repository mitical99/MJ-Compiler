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
	
	public void visit(VarDecl varDecl) {
		
	}
	
	
    public boolean passed() {
    	return !errorDetected;
    }
}
