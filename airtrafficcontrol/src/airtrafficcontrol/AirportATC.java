package airtrafficcontrol;

/**
 * @invar | 1 <= getNbGates()
 */
public class AirportATC extends ATC {
	
	/**
	 * @invar | 1 <= nbGates
	 */
	final int nbGates;
	
	public int getNbGates() { return nbGates; }

	/**
	 * @post | getControlledAircraft().isEmpty()
	 * @post | getNbGates() == nbGates
	 */
	public AirportATC(int nbGates) {
		this.nbGates = nbGates;
	}

	@Override
	public boolean canLand(Aircraft aircraft) {
		return aircraft.getSpeed() < 300;
	}

	@Override
	public boolean isSimilarTo(ATC other) {
		return other instanceof AirportATC && nbGates == ((AirportATC)other).nbGates;
	}
	
}
