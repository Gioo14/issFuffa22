package it.unibo.Radar22.domain.mock;

import it.unibo.Radar22.domain.interfaces.IDistance;
import it.unibo.Radar22.domain.interfaces.ISonar;
import it.unibo.Radar22.domain.models.SonarModel;
import it.unibo.Radar22.domain.mock.Distance;
import it.unibo.Radar22.domain.utils.ColorsOut;
import it.unibo.Radar22.domain.utils.BasiUtils;
import it.unibo.Radar22.domain.utils.DomainSystemConfig;

public class SonarMock extends SonarModel implements ISonar {
	private int delta = 1;
	IDistance curVal = new Distance(90);
	
	@Override
	protected void sonarSetUp() {
		curVal = new Distance(90);		
		ColorsOut.out("SonarMock | sonarSetUp curVal="+curVal);
	}
	
	@Override
	public IDistance getDistance() {
		return this.curVal;
	}	
	
	@Override
	protected void sonarProduce( ) {
		if( DomainSystemConfig.testing ) {	//produces always the same value
			updateDistance( DomainSystemConfig.testingDistance );			      
		}else {
			int v = ((IDistance) curVal).getVal() - delta;
			updateDistance( v );			    
			stopped = ( v <= 0 );
		}
		BasiUtils.delay(DomainSystemConfig.sonarDelay);  //avoid fast generation
 	}


}
