package airtrafficcontrol;

import java.util.HashSet;
import java.util.Set;

import logicalcollections.LogicalSet;

/**
 * @invar | getControlledAircraft() != null
 * @invar | getControlledAircraft().stream().allMatch(aircraft -> aircraft != null && aircraft.getATC() == this)
 */
public abstract class ATC {
	
	/**
	 * @invar | controlledAircraft != null
	 * @invar | controlledAircraft.stream().allMatch(aircraft -> aircraft != null)
	 * @representationObject
	 */
	private Set<Aircraft> controlledAircraft = new HashSet<>();
	
	/**
	 * @invar | getControlledAircraftInternal().stream().allMatch(aircraft -> aircraft.getATCInternal() == this)
	 *
	 * @creates | result
	 * @post | result != null
	 * @post | result.stream().allMatch(aircraft -> aircraft != null)
	 * @peerObjects (package-level)
	 */
	Set<Aircraft> getControlledAircraftInternal() { return Set.copyOf(controlledAircraft); }
	
	/**
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Aircraft> getControlledAircraft() { return getControlledAircraftInternal(); }
	
	/**
	 * @post | getControlledAircraftInternal().isEmpty()
	 */
	ATC() {}
	
	/**
	 * @pre | aircraft != null
	 * @mutates_properties | getControlledAircraftInternal()
	 * @post | getControlledAircraftInternal().equals(LogicalSet.plus(old(getControlledAircraftInternal()), aircraft))
	 */
	void addControlledAircraft(Aircraft aircraft) {
		controlledAircraft.add(aircraft);
	}

	/**
	 * @mutates_properties | getControlledAircraftInternal()
	 * @post | getControlledAircraftInternal().equals(LogicalSet.minus(old(getControlledAircraftInternal()), aircraft))
	 */
	void removeControlledAircraft(Aircraft aircraft) {
		controlledAircraft.remove(aircraft);
	}
	
	public abstract boolean canLand(Aircraft aircraft);
	
	public abstract boolean isSimilarTo(ATC other);

}
