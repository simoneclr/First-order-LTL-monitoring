package language.foltl;

import language.DoubleImplFormula;
import formula.ltlf.LTLfLocalDoubleImplFormula;
import formula.ltlf.LTLfLocalFormula;
import language.foltl.semantics.FoLtlAssignment;

import java.util.LinkedHashSet;

/**
 * Class that represents a FO-LTL local double implication.
 * <br>
 * Created by Simone Calciolari on 06/08/15.
 * @author Simone Calciolari
 */
public class FoLtlLocalDoubleImplFormula extends FoLtlBinaryFormula implements DoubleImplFormula, FoLtlBoolOpLocalFormula {

	public FoLtlLocalDoubleImplFormula(FoLtlFormula left, FoLtlFormula right) {
		super(left, right);
	}

	@Override
	public language.FormulaType getFormulaType(){
		return language.FormulaType.LOCAL_DOUBLE_IMPL;
	}

	@Override
	public LTLfLocalFormula propositionalize(LinkedHashSet<FoLtlConstant> domain, FoLtlAssignment assignment){
		FoLtlLocalFormula left = (FoLtlLocalFormula) this.getLeftFormula();
		FoLtlLocalFormula right = (FoLtlLocalFormula) this.getRightFormula();
		return new LTLfLocalDoubleImplFormula(left.propositionalize(domain, assignment), right.propositionalize(domain, assignment));
	}

	@Override
	public FoLtlLocalFormula quantifierExpansion(LinkedHashSet<FoLtlConstant> domain, FoLtlAssignment assignment){
		FoLtlLocalFormula left = (FoLtlLocalFormula) this.getLeftFormula().clone();
		FoLtlLocalFormula right = (FoLtlLocalFormula) this.getRightFormula().clone();
		return new FoLtlLocalDoubleImplFormula(left.quantifierExpansion(domain, assignment),
				right.quantifierExpansion(domain, assignment));
	}

	@Override
	public String getAtomicName(){
		FoLtlLocalFormula left = (FoLtlLocalFormula) this.getLeftFormula();
		FoLtlLocalFormula right = (FoLtlLocalFormula) this.getRightFormula();
		return left.getAtomicName() + "_" + this.stringOperator().toUpperCase() + "_" + right.getAtomicName();
	}

	/*
	@Override
	public boolean equals(Object o){
		boolean res = false;

		if (o != null && this.getClass().equals(o.getClass())){
			FoLtlLocalDoubleImplFormula other = (FoLtlLocalDoubleImplFormula) o;
			res = (this.getLeftFormula().equals(other.getLeftFormula())
					&& this.getRightFormula().equals(other.getRightFormula()))
					|| (this.getLeftFormula().equals(other.getRightFormula())
					&& this.getRightFormula().equals(other.getLeftFormula()));
		}

		return res;
	}
	*/

}
