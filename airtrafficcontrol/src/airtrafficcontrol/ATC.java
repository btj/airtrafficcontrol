package airtrafficcontrol;

import java.util.HashSet;
import java.util.Set;

/**
 * @invar | getControlledAircraft() != null
 * @invar | getControlledAircraft().stream().allMatch(aircraft -> aircraft != null && aircraft.getATC() == this)
 */
public abstract class ATC {
	
	/**
	 * @invar | controlledAircraft != null
	 * @invar | controlledAircraft.stream().allMatch(aircraft -> aircraft != null && aircraft.atc == this)
	 * @representationObject
	 * @peerObjects
	 */
	Set<Aircraft> controlledAircraft = new HashSet<>();
	
	/**
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Aircraft> getControlledAircraft() { return Set.copyOf(controlledAircraft); }
	
	ATC() {}
	
	public abstract boolean canLand(Aircraft aircraft);
	
	public abstract boolean isSimilarTo(ATC other);

}
