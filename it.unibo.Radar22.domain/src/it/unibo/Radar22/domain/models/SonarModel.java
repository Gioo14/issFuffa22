package it.unibo.Radar22.domain.models;

import it.unibo.Radar22.domain.concrete.SonarConcrete;
import it.unibo.Radar22.domain.interfaces.ISonar;
import it.unibo.Radar22.domain.mock.Distance;
import it.unibo.Radar22.domain.interfaces.IDistance;
import it.unibo.Radar22.domain.mock.SonarMock;
import it.unibo.Radar22.domain.utils.DomainSystemConfig;
import it.unibo.Radar22.domain.utils.ColorsOut;

public abstract class SonarModel  implements ISonar {
	protected  IDistance curVal = (IDistance) new Distance(90);	 
	protected boolean stopped   = true;
	
	 	
		public static ISonar create() {
			if( DomainSystemConfig.simulation )  return createSonarMock();
			else  return createSonarConcrete();		
		}

		public static ISonar createSonarMock() {
			ColorsOut.out("createSonarMock", ColorsOut.BLUE);
			return new SonarMock();
		}	
		public static ISonar createSonarConcrete() {
			ColorsOut.out("createSonarConcrete", ColorsOut.BLUE);
			return new SonarConcrete();
		}	
		
		@Override
		public IDistance getDistance() {
			return curVal;
		}
		
		protected SonarModel() {//hidden costructor, to force setup
			ColorsOut.out("SonarModel | calling (specialized) sonarSetUp ", ColorsOut.BLUE );
			sonarSetUp();   
		}
		
		protected void updateDistance( int d ) {
			curVal = new Distance( d );
			ColorsOut.out("SonarModel | updateDistance "+ d, ColorsOut.BLUE);
		}	

		protected abstract void sonarSetUp() ;
	 	protected abstract void sonarProduce() ;

		@Override
		public boolean isActive() {
			//ColorsOut.out("SonarModel | isActive "+ (! stopped), ColorsOut.GREEN);
			return ! stopped;
		}
		
		
		@Override
		public void activate() {
			curVal = (IDistance) new Distance( 90 );
	 		ColorsOut.out("SonarModel | activate" );
			stopped = false;
			new Thread() {
				public void run() {
					while( ! stopped  ) {
						//Colors.out("SonarModel | call produce", Colors.GREEN);
						sonarProduce(  );
						//Utils.delay(RadarSystemConfig.sonarDelay);
					}
					ColorsOut.out("SonarModel | ENDS" );
			    }
			}.start();
		}
	 	
		@Override
		public void deactivate() {
			ColorsOut.out("SonarModel | deactivate", ColorsOut.BgYellow );
			stopped = true;
		}

	

}
