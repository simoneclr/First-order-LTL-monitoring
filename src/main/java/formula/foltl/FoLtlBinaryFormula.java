package formula.foltl;

import formula.BinaryFormula;

/**
 * Created by Simone Calciolari on 06/08/15.
 */
public abstract class FoLtlBinaryFormula implements FoLtlFormula, BinaryFormula {

	private FoLtlFormula left;
	private FoLtlFormula right;

	public FoLtlBinaryFormula(FoLtlFormula left, FoLtlFormula right){
		this.left = left;
		this.right = right;
	}

	public FoLtlFormula getLeftFormula() {
		return left;
	}

	public FoLtlFormula getRightFormula() {
		return right;
	}

	@Override
	public String toString() {
		return "(" + this.getLeftFormula() + ") " + this.stringOperator() + " (" + this.getRightFormula() + ")";
	}

	@Override
	public boolean equals(Object o){
		boolean res = false;

		if (o != null && this.getClass().equals(o.getClass())){
			FoLtlBinaryFormula other = (FoLtlBinaryFormula) o;
			res = this.getLeftFormula().equals(other.getLeftFormula())
					&& this.getRightFormula().equals(other.getRightFormula());
		}

		return res;
	}

	@Override
	public FoLtlFormula clone(){
		return this.formulaFactory(this.getFormulaType(), this.getLeftFormula().clone(), this.getRightFormula().clone());
	}

	public FoLtlFormula formulaFactory(FoLtlFormulaType type, FoLtlFormula left, FoLtlFormula right){
		FoLtlFormula res;

		switch(type){

			case TEMP_DOUBLE_IMPL:
				res = new FoLtlTempDoubleImplFormula(left, right);
				break;

			case TEMP_IMPLICATION:
				res = new FoLtlTempImplFormula(left, right);
				break;

			case TEMP_OR:
				res = new FoLtlTempOrFormula(left, right);
				break;

			case TEMP_AND:
				res = new FoLtlTempAndFormula(left, right);
				break;

			case WEAK_UNTIL:
				res = new FoLtlWeakUntilFormula(left, right);
				break;

			case RELEASE:
				res = new FoLtlReleaseFormula(left, right);
				break;

			case UNTIL:
				res = new FoLtlUntilFormula(left, right);
				break;

			case LOCAL_DOUBLE_IMPL:
				res = new FoLtlLocalDoubleImplFormula(left, right);
				break;

			case LOCAL_IMPLICATION:
				res = new FoLtlLocalImplFormula(left, right);
				break;

			case LOCAL_OR:
				res = new FoLtlLocalOrFormula(left, right);
				break;

			case LOCAL_AND:
				res = new FoLtlLocalAndFormula(left, right);
				break;

			default:
				throw new RuntimeException("Unknown formula type");

		}

		return res;
	}

}
