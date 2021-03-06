package language.foltl;

import formula.ltlf.LTLfEventuallyFormula;
import formula.ltlf.LTLfFormula;

import java.util.HashMap;

/**
 * Class that represents a FO-LTL eventually formula.
 * <br>
 * Created by Simone Calciolari on 06/08/15.
 * @author Simone Calciolari
 */
public class FoLtlEventuallyFormula extends FoLtlUnaryFormula implements FoLtlTempOpTempFormula {

	public FoLtlEventuallyFormula(FoLtlFormula nestedFormula) {
		super(nestedFormula);
	}

	@Override
	public language.FormulaType getFormulaType(){
		return language.FormulaType.EVENTUALLY;
	}

	@Override
	public String stringOperator() {
		return "F";
	}

	//F(phi) == true U phi
	@Override
	public FoLtlFormula nnf(){
		FoLtlFormula nested = this.getNestedFormula().clone();
		return new FoLtlUntilFormula(new FoLtlLocalTrueAtom(), nested).nnf();
	}

	@Override
	public FoLtlFormula negate(){
		return (FoLtlFormula) this.nnf().negate();
	}

	@Override
	public LTLfFormula toLTLf(HashMap<FoLtlFormula, LTLfFormula> foltlTOltlf,
														HashMap<LTLfFormula, FoLtlFormula> ltlfTOfoltl){
		FoLtlFormula nested = this.getNestedFormula();
		return new LTLfEventuallyFormula(nested.toLTLf(foltlTOltlf, ltlfTOfoltl));
	}

}
