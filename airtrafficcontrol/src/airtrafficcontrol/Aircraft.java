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
	 * @invar | atc.controlledAircraft.contains(this)
	 * @invar | 0 <= speed
	 * 
	 * @peerObject
	 */
	ATC atc;
	int speed;
	
	/**
	 * @peerObject
	 */
	public ATC getATC() { return atc; }
	
	public int getSpeed() { return speed; }
	
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
		atc.controlledAircraft.add(this);
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
		this.atc.controlledAircraft.remove(this);
		this.atc = atc;
		atc.controlledAircraft.add(this);
	}

}
