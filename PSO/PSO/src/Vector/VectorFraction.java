package Vector;

import com.sofloni.fraction.Fraction;
import com.sofloni.fraction.FractionConstructionException;;

public class VectorFraction extends Vector<Fraction> {

    public VectorFraction(Fraction ...args)
    {
        super(args);
    }

    @Override
    public  void normalise() {

    }

    private void dimCheck(Vector<Fraction> other) throws DimensionException
    {
        if(other.getDimension() != this.getDimension())
        {
            throw new DimensionException("This Vectors Dimension( " + getDimension() + " ) " +
                    "is not equals the other vectors dimension( " + other.getDimension() + " ).");
        }
    }

    @Override
    public  void add(Vector<Fraction> other) throws DimensionException {
        dimCheck(other);

        for(int i = 0; i <= getDimension() - 1; ++i)
        {
            this.mElements[i] = this.mElements[i].add(other.mElements[i]);
        }
    }

    @Override
    public void sub(Vector<Fraction> other) throws DimensionException {
        dimCheck(other);

        for(int i = 0; i <= getDimension() - 1; ++i)
        {
            this.mElements[i] = this.mElements[i].subtract(other.mElements[i]);
        }
    }

    @Override
    public  void mul(Vector<Fraction> other) throws DimensionException {
        dimCheck(other);

        for(int i = 0; i <= getDimension() - 1; ++i)
        {
            this.mElements[i] = this.mElements[i].multiply(other.mElements[i]);
        }
    }

    @Override
    public void div(Vector<Fraction> other) throws DimensionException,IllegalArgumentException {
        dimCheck(other);

        for(int i = 0; i <= getDimension() - 1; ++i)
        {
            try {
                this.mElements[i] = this.mElements[i].divide(other.mElements[i]);
            }catch (FractionConstructionException e)
            {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    @Override
    public void mul(Fraction other) {

    }

    @Override
    public  void div(Fraction other) {

    }

    @Override
    public boolean equals(Object o)
    {
        if( !(o instanceof VectorFraction) || ((VectorFraction) o).getDimension() != this.getDimension() )
        {
            return false;
        }

        for(int i = 0; i <= getDimension() - 1; ++i)
        {
            if(!((VectorFraction) o).mElements[i].equals(this.mElements[i]))
            {
                return false;
            }
        }
        return true;
    }
}
