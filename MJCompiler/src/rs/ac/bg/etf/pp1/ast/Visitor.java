// generated with ast extension for cup
// version 0.8
// 18/5/2022 21:59:9


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(IfStatementStart IfStatementStart);
    public void visit(ArrayName ArrayName);
    public void visit(Unmatched Unmatched);
    public void visit(VarParams VarParams);
    public void visit(MethodDecl MethodDecl);
    public void visit(Mulop Mulop);
    public void visit(Matched Matched);
    public void visit(Relop Relop);
    public void visit(Assignop Assignop);
    public void visit(ProgDeclList ProgDeclList);
    public void visit(VarDeclLineList VarDeclLineList);
    public void visit(ExtendedClass ExtendedClass);
    public void visit(StatementList StatementList);
    public void visit(ActParams ActParams);
    public void visit(ConditionTerm ConditionTerm);
    public void visit(Addop Addop);
    public void visit(ConstDeclLine ConstDeclLine);
    public void visit(Factor Factor);
    public void visit(DoWhileDepthCounterHelper DoWhileDepthCounterHelper);
    public void visit(CondFactList CondFactList);
    public void visit(Designator Designator);
    public void visit(ProgDecl ProgDecl);
    public void visit(MulFactorList MulFactorList);
    public void visit(Term Term);
    public void visit(Condition Condition);
    public void visit(VarDeclLine VarDeclLine);
    public void visit(InsideClassDeclList InsideClassDeclList);
    public void visit(Statements Statements);
    public void visit(FunctionName FunctionName);
    public void visit(DoWhileConditionStart DoWhileConditionStart);
    public void visit(FormParams FormParams);
    public void visit(IfStatementEnd IfStatementEnd);
    public void visit(OneFormParam OneFormParam);
    public void visit(Label Label);
    public void visit(ClassDeclLine ClassDeclLine);
    public void visit(IfElseBlockEnd IfElseBlockEnd);
    public void visit(VarDeclList VarDeclList);
    public void visit(Expr Expr);
    public void visit(OrConditionBlockEnd OrConditionBlockEnd);
    public void visit(ConditionTermList ConditionTermList);
    public void visit(DoWhileConditionEnd DoWhileConditionEnd);
    public void visit(ActPars ActPars);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(DesignatorField DesignatorField);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(DesignatorFieldList DesignatorFieldList);
    public void visit(ConstDecl ConstDecl);
    public void visit(RecordDeclLine RecordDeclLine);
    public void visit(CondFact CondFact);
    public void visit(IfStatement IfStatement);
    public void visit(ConstDeclLineList ConstDeclLineList);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(SingleStatement SingleStatement);
    public void visit(FormPars FormPars);
    public void visit(Moduo Moduo);
    public void visit(Division Division);
    public void visit(Multiply Multiply);
    public void visit(Minus Minus);
    public void visit(Plus Plus);
    public void visit(LessOrEqual LessOrEqual);
    public void visit(Less Less);
    public void visit(GreaterOrEqual GreaterOrEqual);
    public void visit(Greater Greater);
    public void visit(NotEqual NotEqual);
    public void visit(DoubleEqual DoubleEqual);
    public void visit(AssignopDerived1 AssignopDerived1);
    public void visit(DesignatorArray DesignatorArray);
    public void visit(DesignatorOnly DesignatorOnly);
    public void visit(ArrayElemDesignator ArrayElemDesignator);
    public void visit(StructFieldDesignator StructFieldDesignator);
    public void visit(FactorExprOnly FactorExprOnly);
    public void visit(FactorNewObjectExpr FactorNewObjectExpr);
    public void visit(FactorNewObjectNoExpr FactorNewObjectNoExpr);
    public void visit(FactorBoolConstant FactorBoolConstant);
    public void visit(FactorCharConstant FactorCharConstant);
    public void visit(FactorNumberConstant FactorNumberConstant);
    public void visit(FactorDesignatorWithAct FactorDesignatorWithAct);
    public void visit(FactorDesignatorOnly FactorDesignatorOnly);
    public void visit(SingleFactor SingleFactor);
    public void visit(FactorList FactorList);
    public void visit(Terms Terms);
    public void visit(MoreExpr MoreExpr);
    public void visit(PositiveExpr PositiveExpr);
    public void visit(NegativeExpr NegativeExpr);
    public void visit(CondFactTwoExpr CondFactTwoExpr);
    public void visit(CondFactOneExpr CondFactOneExpr);
    public void visit(SingleConditionFact SingleConditionFact);
    public void visit(ConditionTerms ConditionTerms);
    public void visit(FirstParam FirstParam);
    public void visit(MoreParameters MoreParameters);
    public void visit(NoParameters NoParameters);
    public void visit(ActualParameters ActualParameters);
    public void visit(OrBlockEnd OrBlockEnd);
    public void visit(SingleCondition SingleCondition);
    public void visit(Conditions Conditions);
    public void visit(FunctionNameDesignator FunctionNameDesignator);
    public void visit(DecStmt DecStmt);
    public void visit(IncStmt IncStmt);
    public void visit(FuncCall FuncCall);
    public void visit(AssignError AssignError);
    public void visit(AssignStmt AssignStmt);
    public void visit(DoWhileCounter DoWhileCounter);
    public void visit(DoWhileCondEnd DoWhileCondEnd);
    public void visit(DoWhileCondStart DoWhileCondStart);
    public void visit(IfBlockStart IfBlockStart);
    public void visit(IfElseEnd IfElseEnd);
    public void visit(IfBlockEnd IfBlockEnd);
    public void visit(ErrorIf ErrorIf);
    public void visit(CorrectIf CorrectIf);
    public void visit(DoStmt DoStmt);
    public void visit(IfStmt IfStmt);
    public void visit(IfElseStmt IfElseStmt);
    public void visit(PrintStmtNumConst PrintStmtNumConst);
    public void visit(PrintStmtNoNumConst PrintStmtNoNumConst);
    public void visit(ReturnNoExprStmt ReturnNoExprStmt);
    public void visit(ReturnExprStmt ReturnExprStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(GotoStmt GotoStmt);
    public void visit(ContinueStmt ContinueStmt);
    public void visit(BreakStmt BreakStmt);
    public void visit(DesignatorMatchedStmt DesignatorMatchedStmt);
    public void visit(MultipleStatements MultipleStatements);
    public void visit(LabelDerived1 LabelDerived1);
    public void visit(MoreStatements MoreStatements);
    public void visit(SingleStmtLabeled SingleStmtLabeled);
    public void visit(SingleStmtNoLabel SingleStmtNoLabel);
    public void visit(NoStmtList NoStmtList);
    public void visit(StmtList StmtList);
    public void visit(VarArgs VarArgs);
    public void visit(OneElemParam OneElemParam);
    public void visit(ArrayParam ArrayParam);
    public void visit(ErrorFormParam ErrorFormParam);
    public void visit(SingleFormParam SingleFormParam);
    public void visit(MoreFormParam MoreFormParam);
    public void visit(NoFormParams NoFormParams);
    public void visit(OnlyVarParams OnlyVarParams);
    public void visit(FormAndVarParams FormAndVarParams);
    public void visit(FormParameters FormParameters);
    public void visit(TypeRetMethodTypeName TypeRetMethodTypeName);
    public void visit(NoRetMethodTypeName NoRetMethodTypeName);
    public void visit(MethodDeclaration MethodDeclaration);
    public void visit(RecordName RecordName);
    public void visit(RecordDeclaration RecordDeclaration);
    public void visit(InsideClassDeclarationListNoConstr InsideClassDeclarationListNoConstr);
    public void visit(NoExtendedClass NoExtendedClass);
    public void visit(Extends Extends);
    public void visit(ClassDeclNoInsideDeclList ClassDeclNoInsideDeclList);
    public void visit(ClassDeclaration ClassDeclaration);
    public void visit(Var Var);
    public void visit(ArrayVar ArrayVar);
    public void visit(ErrorVarDecl ErrorVarDecl);
    public void visit(SingleVar SingleVar);
    public void visit(VarList VarList);
    public void visit(VarDeclArray VarDeclArray);
    public void visit(Type Type);
    public void visit(ConstBoolDeclaration ConstBoolDeclaration);
    public void visit(ConstCharDeclaration ConstCharDeclaration);
    public void visit(ConstNumberDeclaration ConstNumberDeclaration);
    public void visit(ConstError ConstError);
    public void visit(SingleConst SingleConst);
    public void visit(ConstList ConstList);
    public void visit(ConstLineDeclaration ConstLineDeclaration);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(NoVarDecl NoVarDecl);
    public void visit(VarDeclarationList VarDeclarationList);
    public void visit(RecordDeclarations RecordDeclarations);
    public void visit(ClassDeclarations ClassDeclarations);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(ConstDeclarations ConstDeclarations);
    public void visit(NoProgDecl NoProgDecl);
    public void visit(ProgDeclarations ProgDeclarations);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
