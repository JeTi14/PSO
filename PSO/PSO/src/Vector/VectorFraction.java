package Vector;

import com.sofloni.fraction.Fraction;
import com.sofloni.fraction.FractionConstructionException;;

public class VectorFraction extends Vector<Fraction> {

	public VectorFraction(Fraction... args) {
		super(args);
	}

	public VectorFraction(int dim) {
		super(Fraction.class, dim);
	}

	@Override
	public void normalise() {

	}

	private void dimCheck(Vector<Fraction> other) throws DimensionException {
		if (other.getDimension() != this.getDimension()) {
			throw new DimensionException("This Vectors Dimension( " + getDimension() + " ) "
					+ "is not equals the other vectors dimension( " + other.getDimension() + " ).");
		}
	}

	@Override
	public void add(Vector<Fraction> other) throws DimensionException {
		this.mElements = addConst(other).mElements;
	}

	@Override
	public void sub(Vector<Fraction> other) throws DimensionException {
		this.mElements = subConst(other).mElements;
	}

	@Override
	public void mul(Vector<Fraction> other) throws DimensionException {
		this.mElements = mulConst(other).mElements;
	}

	@Override
	public void div(Vector<Fraction> other) throws DimensionException, IllegalArgumentException {
		this.mElements = divConst(other).mElements;
	}

	@Override
	public void mul(Fraction other) {
		this.mElements = mulConst(other).mElements;
	}

	@Override
	public void div(Fraction other) throws IllegalArgumentException {
		this.mElements = divConst(other).mElements;	
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof VectorFraction) || ((VectorFraction) o).getDimension() != this.getDimension()) {
			return false;
		}

		for (int i = 0; i <= getDimension() - 1; ++i) {
			if (!((VectorFraction) o).mElements[i].equals(this.mElements[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Vector<Fraction> addConst(Vector<Fraction> other) throws DimensionException {
		dimCheck(other);

		VectorFraction result = new VectorFraction(getDimension());

		for (int i = 0; i <= getDimension() - 1; ++i) {
			result.mElements[i] = this.mElements[i].add(other.mElements[i]);
		}

		return result;
	}

	@Override
	public Vector<Fraction> subConst(Vector<Fraction> other) throws DimensionException {
		dimCheck(other);

		VectorFraction result = new VectorFraction(getDimension());

		for (int i = 0; i <= getDimension() - 1; ++i) {
			result.mElements[i] = this.mElements[i].subtract(other.mElements[i]);
		}

		return result;
	}

	@Override
	public Vector<Fraction> mulConst(Vector<Fraction> other) throws DimensionException {
		dimCheck(other);

		VectorFraction result = new VectorFraction(getDimension());

		for (int i = 0; i <= getDimension() - 1; ++i) {
			result.mElements[i] = this.mElements[i].multiply(other.mElements[i]);
		}

		return result;
	}

	@Override
	public Vector<Fraction> divConst(Vector<Fraction> other) throws DimensionException, IllegalArgumentException {
		dimCheck(other);

		VectorFraction result = new VectorFraction(getDimension());
		
		for (int i = 0; i <= getDimension() - 1; ++i) {
			try {
				result.mElements[i] = this.mElements[i].divide(other.mElements[i]);
			} catch (FractionConstructionException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		
		return result;
	}

	@Override
	public Vector<Fraction> mulConst(Fraction other) {
		VectorFraction result = new VectorFraction(getDimension());
		
		for (int i = 0; i <= getDimension() - 1; ++i) {
			result.mElements[i] = this.mElements[i].multiply(other);
		}
		
		return result;
	}

	@Override
	public Vector<Fraction> divConst(Fraction other) throws IllegalArgumentException {
		VectorFraction result = new VectorFraction(getDimension());
		
		for (int i = 0; i <= getDimension() - 1; ++i) {
			try {
				result.mElements[i] = this.mElements[i].divide(other);
			} catch (FractionConstructionException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		
		return result;
	}
}
