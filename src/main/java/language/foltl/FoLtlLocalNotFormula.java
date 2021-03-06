package language.foltl;

import language.FormulaType;
import language.NotFormula;
import formula.ltlf.LTLfLocalFormula;
import formula.ltlf.LTLfLocalNotFormula;
import language.foltl.semantics.FoLtlAssignment;
import net.sf.tweety.logics.fol.syntax.FolFormula;
import net.sf.tweety.logics.fol.syntax.Negation;

import java.util.LinkedHashSet;

/**
 * Class that represents a FO-LTL local negation.
 * <br>
 * Created by Simone Calciolari on 06/08/15.
 * @author Simone Calciolari
 */
public class FoLtlLocalNotFormula extends FoLtlUnaryFormula implements NotFormula, FoLtlBoolOpLocalFormula{

	public FoLtlLocalNotFormula(FoLtlFormula nestedFormula) {
		super(nestedFormula);
	}

	@Override
	public FormulaType getFormulaType(){
		return FormulaType.LOCAL_NOT;
	}

	@Override
	public LTLfLocalFormula propositionalize(LinkedHashSet<FoLtlConstant> domain, FoLtlAssignment assignment){
		FoLtlLocalFormula nested = (FoLtlLocalFormula) this.getNestedFormula();
		return new LTLfLocalNotFormula(nested.propositionalize(domain, assignment));
	}

	@Override
	public FoLtlLocalFormula quantifierExpansion(LinkedHashSet<FoLtlConstant> domain, FoLtlAssignment assignment){
		FoLtlLocalFormula nested = (FoLtlLocalFormula) this.getNestedFormula().clone();
		return new FoLtlLocalNotFormula(nested.quantifierExpansion(domain, assignment));
	}

	@Override
	public FolFormula toTweetyFol(){
		FoLtlLocalFormula nested = (FoLtlLocalFormula) this.getNestedFormula();
		return new Negation(nested.toTweetyFol());
	}

	@Override
	public String getAtomicName(){
		FoLtlLocalFormula nested = (FoLtlLocalFormula) this.getNestedFormula();
		return this.stringOperator().toUpperCase() + "_" + nested.getAtomicName();
	}

}
