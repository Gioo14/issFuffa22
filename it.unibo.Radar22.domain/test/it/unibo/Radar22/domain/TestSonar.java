package it.unibo.Radar22.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unibo.Radar22.domain.interfaces.ISonar;
import it.unibo.Radar22.domain.mock.SonarMock;

public class TestSonar {
	
	@Test
	public void testSonarOn() {
		ISonar sonar = new SonarMock();
		sonar.activate();
		assertTrue(sonar.isActive());
	}
	
	@Test
	public void testSonarOff() {
		ISonar sonar = new SonarMock();
		assertTrue(!sonar.isActive());
	}
	
	@Test
	public void testDistance() {
		ISonar sonar = new SonarMock();
		assertTrue(sonar.getDistance().getVal()==90);
	}
}
