package it.unibo.Radar22.domain;

import static org.junit.Assert.assertTrue;

import it.unibo.Radar22.domain.interfaces.IDistance;
import it.unibo.Radar22.domain.interfaces.ISonar;
import it.unibo.Radar22.domain.utils.BasiUtils;
import it.unibo.Radar22.domain.utils.ColorsOut;
import it.unibo.Radar22.domain.utils.DomainSystemConfig;

public class SonarTestForConsumer extends Thread{
	private ISonar sonar;
	private int delta;
	
		public SonarTestForConsumer( ISonar sonar, int delta) {
			this.sonar = sonar;
			this.delta = delta;
		}
		
		@Override
		public void run() {
			int v0 = sonar.getDistance().getVal();
			ColorsOut.out("SonarConsumerForTesting | initial value v0=" + v0);
			while( v0 == 90 ) {
				BasiUtils.delay(DomainSystemConfig.sonarDelay);
				v0 = sonar.getDistance().getVal();
				ColorsOut.out("SonarConsumerForTesting | initial value =" + v0);
			}
	 		while( sonar.isActive() ) {
	 			BasiUtils.delay(DomainSystemConfig.sonarDelay/2); //per non perdere dati simulati
	 			IDistance d = sonar.getDistance();
				int v       = d.getVal();
				ColorsOut.out("SonarConsumerForTesting | v=" + v);
				int vexpectedMin = v0-delta;
				int vexpectedMax = v0+delta;
				assertTrue(  v <= vexpectedMax && v >= vexpectedMin );
				v0 = v;
			}
		}
}
