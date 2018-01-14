package PSOAlgorithm;

import Vector.Vector;

public interface ParticleObserver<T> {
	void onDiscoveredNewBestSwarmPosition(final Vector<T> newBest);
}
