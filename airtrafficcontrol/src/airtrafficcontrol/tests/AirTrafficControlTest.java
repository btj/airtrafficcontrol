package airtrafficcontrol.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import airtrafficcontrol.Aircraft;
import airtrafficcontrol.AirportATC;
import airtrafficcontrol.AreaATC;

class AirTrafficControlTest {

	@Test
	void test() {
		AirportATC zaventem = new AirportATC(60);
		assertEquals(Set.of(), zaventem.getControlledAircraft());
		assertEquals(60, zaventem.getNbGates());
		
		Aircraft aircraft1 = new Aircraft(zaventem);
		assertEquals(zaventem, aircraft1.getATC());
		assertEquals(0, aircraft1.getSpeed());
		assertEquals(Set.of(aircraft1), zaventem.getControlledAircraft());
		
		aircraft1.setSpeed(400);
		assertEquals(400, aircraft1.getSpeed());
		
		AreaATC areaATC1 = new AreaATC();
		assertEquals(Set.of(), areaATC1.getControlledAircraft());
		
		aircraft1.transferTo(areaATC1);
		assertEquals(areaATC1, aircraft1.getATC());
		assertEquals(Set.of(), zaventem.getControlledAircraft());
		assertEquals(Set.of(aircraft1), areaATC1.getControlledAircraft());
		
		assertFalse(areaATC1.canLand(aircraft1));
		aircraft1.setSpeed(200);
		aircraft1.transferTo(zaventem);
		assertTrue(zaventem.canLand(aircraft1));
		
		AreaATC areaATC2 = new AreaATC();
		AirportATC heathrow = new AirportATC(100);
		AirportATC schiphol = new AirportATC(60);
		assertTrue(areaATC1.isSimilarTo(areaATC2));
		assertFalse(areaATC1.isSimilarTo(zaventem));
		assertFalse(zaventem.isSimilarTo(heathrow));
		assertTrue(zaventem.isSimilarTo(schiphol));
		
		// Transfer to same ATC
		aircraft1.transferTo(zaventem);
	}

}
