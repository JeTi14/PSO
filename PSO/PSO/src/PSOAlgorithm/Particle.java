package PSOAlgorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

import Vector.Vector;
import Vector.DimensionException;
import Vector.VectorApfloat;

/**Representing a single particle in the algorithm.
 * 
 * @author soenke f
 * @author tdreissigacker
 *
 */
public class Particle<T>{
	/**
	 * Current position
	 */
	private Vector<T> mCurrentPosition;
	
	/**
	 * The best position this particle experienced.
	 */
	private Vector<T> mSelfBestKnownPosition;
	
	/**
	 * The best evaluation this particle experienced.
	 */
	private Apfloat mSelfBestKnownEvaluation;
	
	/**
	 * The best position an Particle in the swarm experienced.
	 */
	private Vector<T> mSwarmBestKnownPosition;
	
	/**
	 * The best evaluation an Particle in the swarm experienced.
	 */
	private Apfloat mSwarmBestKnownEvaluation;
	
	/**
	 * Current velocity
	 */
	private Vector<T> mVelocity;
	
	private List<ParticleObserver<T>> mObservers;

	/////////////////////////////////////////////////////////////////////
	//Important algorithm parameters:
	
	/**
	 * Function which decides if one Position is better than another.
	 */
	private final BiFunction<Vector<T>, Vector<T>, Boolean> mPredicate;
	
	/**
	 * Defines how much the current velocity will be altered.
	 * => OMEGA
	 */
	private final T mInertia;
	
	/**
	 * Defines the influence of the particles personal best position
	 * when altering the velocity.
	 * => PHIp
	 */
	private final T mPersonalBestWeight;
	
	/**
	 * Defines the influence of the swarms best position
	 * when altering the velocity.
	 * => PHIg
	 */
	private final T mSwarmBestWeight;
	
	//////////////////////////////////////////////////////////////////////////
	//Operations
	public Particle(
			final BiFunction<Vector<T>, Vector<T>, Boolean> predicate,
			final T inertia,
			final T personalBestWeight,
			final T swarmBestWeight,
			final Vector<T> currentPosition,
			final Vector<T> currentVelocity
			)
	{
		mPredicate = predicate;
		mInertia = inertia;
		mPersonalBestWeight = personalBestWeight;
		mSwarmBestWeight = swarmBestWeight;
		
		mCurrentPosition = currentPosition;
		mSelfBestKnownPosition = currentPosition.copy();
		
		mObservers = new LinkedList<ParticleObserver<T>>();
	}
	
	/**
	 * One iteration step.
	 * @throws DimensionException 
	 */
	public void update() throws DimensionException
	{
		//Update velocity
		updateVelocity();
		//Change position of the particle
		updatePosition();
		//If the evaluation of the currentPosition is the particles personal best
		//->Set mSelfBestKnownPosition
		//->Set mSelfBestKnowEvaluation
		Apfloat currentEval = eval();
		if(currentEval.compareTo(mSelfBestKnownEvaluation) == -1){
			this.mSelfBestKnownEvaluation = currentEval;
			this.mSelfBestKnownPosition = this.mCurrentPosition;
			//->If the evaluation of the currentPosition is better than the Swarms best known evaluation
			//--> Notify the observer
			if(currentEval.compareTo(mSwarmBestKnownEvaluation) == -1){
				//TODO NotifyObserver
			}
		}
	}
	
	/**
     * Update the velocity of a particle using the velocity update formula
     * @throws DimensionException 
     */
    private void updateVelocity() throws DimensionException {
        VectorApfloat pBest = (VectorApfloat) this.mSelfBestKnownPosition.copy();
        VectorApfloat gBest = (VectorApfloat) this.mSwarmBestKnownPosition.copy();
        VectorApfloat pos = (VectorApfloat) this.mCurrentPosition.copy();

        Random random = new Random();
        Apfloat r1 = new Apfloat(random.nextDouble());
        Apfloat r2 = new Apfloat(random.nextDouble());

        // The first product of the formula.
        VectorApfloat newVelocity = (VectorApfloat) this.mVelocity.copy();
        newVelocity.mul((Vector<Apfloat>) this.mInertia);
        
        // The second product of the formula.
        pBest.sub(pos);
        pBest.mul((Vector<Apfloat>) this.mPersonalBestWeight);
        pBest.mul(r1);
        newVelocity.add(pBest);

        // The third product of the formula.
        gBest.sub(pos);
        gBest.mul((Vector<Apfloat>) this.mSwarmBestWeight);
        gBest.mul(r2);
        newVelocity.add(gBest);
        this.mVelocity = (Vector<T>) newVelocity;
    }
    
    /**
     * Update the position of a particle by adding its velocity to its position.
     * @throws DimensionException 
     */
    public void updatePosition () throws DimensionException {
    	this.mCurrentPosition.add(this.mVelocity);
    }
    
    /**
     * The evaluation of the current position.
     * @return      the evaluation
     */
    private Apfloat eval (){
    	Apfloat[] nums = new Apfloat[1];
    	nums[0] = this.calculateFunction();
    	return rootMeanSquare(nums);
    }
    
    /**
     * Calculates the function value
     * @return the value of the current function
     * @throws  
     */
    private Apfloat calculateFunction(){
    	//TODO hier die ermittelten werte mit den messwerten prüfen
    	//aktuell nur zum testen. gesucht ist position[4] mit evaluation = 0
    	if(((VectorApfloat)this.mCurrentPosition).getElements()[0].compareTo(new Apfloat(4)) == 1) return ((VectorApfloat)this.mCurrentPosition).getElements()[0].subtract(new Apfloat(4));
    	return new Apfloat(4).subtract(((VectorApfloat)this.mCurrentPosition).getElements()[0]);
    	
    }
    
    /**
     * Calculates the root-mean-square of the given values
     * @param nums
     * @return
     * @throws 
     */
    private Apfloat rootMeanSquare(Apfloat... nums){
        Apfloat sum = Apfloat.ZERO;
        for (Apfloat num : nums)
            sum = sum.add(num.multiply(num));
        return ApfloatMath.sqrt(new Apfloat(sum.divide(new Apfloat(nums.length)).doubleValue(),100));
    }
    /**
     * Sets the new best evaluation and position of the swarm.
     * @param pos
     * @param eval
     */
	public void setSwarmBestPosition(Vector<T> pos, Apfloat eval)
	{
		mSwarmBestKnownPosition = pos;
		mSwarmBestKnownEvaluation = eval;
	}
}
