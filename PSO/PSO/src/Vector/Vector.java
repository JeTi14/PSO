package Vector;

import java.lang.reflect.Array;

/**N-Dimensional Vector template.
 * 
 * @author soenke f
 *
 * @param <T> Type of vectors elements
 */
public abstract class Vector<T> {
    private final int mDim;
    protected T[] mElements;

    /**
     * @return Normalised vector
     */
    public abstract void normalise();

    /*Vector state change
     */
    public abstract void add(final Vector<T> other) throws DimensionException;
    public abstract void sub(final Vector<T> other) throws DimensionException;
    public abstract void mul(final Vector<T> other) throws DimensionException;
    public abstract void div(final Vector<T> other) throws DimensionException, IllegalArgumentException;

    /*Scalar
     */
    public abstract void mul(final T other);
    public abstract void div(final T other) throws IllegalArgumentException;
    
    /*Vector no state change*/
    public abstract Vector<T> addConst(final Vector<T> other) throws DimensionException;
    public abstract Vector<T> subConst(final Vector<T> other) throws DimensionException;
    public abstract Vector<T> mulConst(final Vector<T> other) throws DimensionException;
    public abstract Vector<T> divConst(final Vector<T> other) throws DimensionException, IllegalArgumentException;
    
    /*Scalar*/
    public abstract Vector<T> mulConst(final T other);
    public abstract Vector<T> divConst(final T other) throws IllegalArgumentException;
    
    /**Copies the vector into a new object.
     * 
     * @return
     */
    public abstract Vector<T> copy();

    /*Concrete impl
    */
    @SafeVarargs
	public Vector(T... args)
    {
        mDim = args.length;
        mElements = args;
    }
    
    @SuppressWarnings("unchecked")
	public Vector(Class<T> type, final int dim)
    {
    	mDim = dim;
    	mElements = (T[]) Array.newInstance(type, dim);
    }

    public final int getDimension()
    {
        return mDim;
    }

    @Override
    public String toString()
    {
        String result = "[";

        for(T t : mElements)
        {
            result += t.toString() + ", ";
        }

        result += "]";
        return result;
    }
}
