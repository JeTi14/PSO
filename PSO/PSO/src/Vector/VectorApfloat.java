package Vector;

import org.apfloat.Apfloat;
/**
 * Concrete Vector implementation for Apfloat values.
 * @author tdreissigacker
 *
 */
public class VectorApfloat extends Vector<Apfloat>{
	
	public VectorApfloat(Apfloat... args) {
		super(args);
	}

	public VectorApfloat(int dim) {
		super(Apfloat.class, dim);
}
	
	@Override
	public void normalise() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof VectorApfloat) || ((VectorApfloat) o).getDimension() != this.getDimension()) {
			return false;
		}

		for (int i = 0; i <= getDimension() - 1; ++i) {
			if (!((VectorApfloat) o).mElements[i].equals(this.mElements[i])) {
				return false;
			}
		}
		return true;
}
	
	private void dimCheck(Vector<Apfloat> other) throws DimensionException
    {
        if(other.getDimension() != this.getDimension())
        {
            throw new DimensionException("This Vectors Dimension( " + getDimension() + " ) " +
                    "is not equals the other vectors dimension( " + other.getDimension() + " ).");
        }
    }

	@Override
	public void add(Vector<Apfloat> other) throws DimensionException {
		this.mElements = addConst(other).mElements;
		
	}

	@Override
	public void sub(Vector<Apfloat> other) throws DimensionException {
		this.mElements = subConst(other).mElements;
		
	}

	@Override
	public void mul(Vector<Apfloat> other) throws DimensionException {
		this.mElements = mulConst(other).mElements;
		
	}

	@Override
	public void div(Vector<Apfloat> other) throws DimensionException, IllegalArgumentException {
		this.mElements = divConst(other).mElements;
		
	}

	@Override
	public void mul(Apfloat other) {
		this.mElements = mulConst(other).mElements;
		
	}

	@Override
	public void div(Apfloat other) throws IllegalArgumentException {
		this.mElements = divConst(other).mElements; 
		
	}

	@Override
	public Vector<Apfloat> addConst(Vector<Apfloat> other) throws DimensionException {
		dimCheck(other);

		VectorApfloat result = new VectorApfloat(getDimension());

		for (int i = 0; i <= getDimension() - 1; ++i) {
			result.mElements[i] = this.mElements[i].add(other.mElements[i]);
		}

		return result;
	}

	@Override
	public Vector<Apfloat> subConst(Vector<Apfloat> other) throws DimensionException {
		dimCheck(other);

		VectorApfloat result = new VectorApfloat(getDimension());

		for (int i = 0; i <= getDimension() - 1; ++i) {
			result.mElements[i] = this.mElements[i].subtract(other.mElements[i]);
		}

		return result;
	}

	@Override
	public Vector<Apfloat> mulConst(Vector<Apfloat> other) throws DimensionException {
		dimCheck(other);

		VectorApfloat result = new VectorApfloat(getDimension());

		for (int i = 0; i <= getDimension() - 1; ++i) {
			result.mElements[i] = this.mElements[i].multiply(other.mElements[i]);
		}

		return result;
	}

	@Override
	public Vector<Apfloat> divConst(Vector<Apfloat> other) throws DimensionException, IllegalArgumentException {
		dimCheck(other);

		VectorApfloat result = new VectorApfloat(getDimension());

		for (int i = 0; i <= getDimension() - 1; ++i) {
			result.mElements[i] = this.mElements[i].divide(other.mElements[i]);
		}

		return result;
	}

	@Override
	public Vector<Apfloat> mulConst(Apfloat other) {
		VectorApfloat result = new VectorApfloat(getDimension());
		
		for (int i = 0; i <= getDimension() - 1; ++i) {
			result.mElements[i] = this.mElements[i].multiply(other);
		}
		
		return result;
	}

	@Override
	public Vector<Apfloat> divConst(Apfloat other) throws IllegalArgumentException {
		VectorApfloat result = new VectorApfloat(getDimension());
		
		for (int i = 0; i <= getDimension() - 1; ++i) {
			result.mElements[i] = this.mElements[i].divide(other);
		}
		
		return result;
	}

	@Override
	public Vector<Apfloat> copy() {
		VectorApfloat result = new VectorApfloat(getDimension());
		result.mElements = this.mElements;
		return result;
	}

}
