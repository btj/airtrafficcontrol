package airtrafficcontrol;

import logicalcollections.LogicalSet;

/**
 * @invar | getATC() != null
 * @invar | getATC().getControlledAircraft().contains(this)
 * @invar | 0 <= getSpeed()
 */
public class Aircraft {

	/**
	 * @invar | atc != null
	 * @invar | 0 <= speed
	 */
	private ATC atc;
	private int speed;
	
	/**
	 * @invar | getATCInternal().getControlledAircraftInternal(). contains(this)
	 * 
	 * @post | result != null
	 * @peerObject (package-level)
	 */
	ATC getATCInternal() { return atc; }
	/**
	 * @post | 0 <= result
	 */
	int getSpeedInternal() { return speed; }
	
	/**
	 * @peerObject
	 */
	public ATC getATC() { return getATCInternal(); }
	
	public int getSpeed() { return getSpeedInternal(); }
	
	/**
	 * @throws IllegalArgumentException | atc == null
	 * @mutates_properties | atc.getControlledAircraft()
	 * @post | getATC() == atc
	 * @post | atc.getControlledAircraft().equals(LogicalSet.plus(old(atc.getControlledAircraft()), this))
	 * @post | getSpeed() == 0
	 */
	public Aircraft(AirportATC atc) {
		if (atc == null)
			throw new IllegalArgumentException("`atc` is null");
		this.atc = atc;
		atc.addControlledAircraft(this);
	}
	
	/**
	 * @pre | 0 <= speed
	 * @mutates_properties | getSpeed()
	 * @post | getSpeed() == speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * @pre | atc != null
	 * @mutates_properties | getATC(), getATC().getControlledAircraft()
	 * @post | getATC() == atc
	 * @post | atc == old(getATC()) ?
	 *       |     atc.getControlledAircraft().equals(old(atc.getControlledAircraft()))
	 *       | :
	 *       |     old(getATC()).getControlledAircraft().equals(LogicalSet.minus(old(getATC().getControlledAircraft()), this)) &&
	 *       |     atc.getControlledAircraft().equals(LogicalSet.plus(old(atc.getControlledAircraft()), this))
	 */
	public void transferTo(ATC atc) {
		this.atc.removeControlledAircraft(this);
		this.atc = atc;
		atc.addControlledAircraft(this);
	}

}
