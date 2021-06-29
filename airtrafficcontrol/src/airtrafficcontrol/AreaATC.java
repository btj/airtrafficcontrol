package airtrafficcontrol;

public class AreaATC extends ATC {
	
	/**
	 * @post | getControlledAircraft().isEmpty()
	 */
	public AreaATC() {}

	@Override
	public boolean canLand(Aircraft aircraft) {
		return false;
	}

	@Override
	public boolean isSimilarTo(ATC other) {
		return other instanceof AreaATC;
	}

}
