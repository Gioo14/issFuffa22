package it.unibo.Radar22.domain.models;

import it.unibo.Radar22.domain.concrete.LedConcrete;
import it.unibo.Radar22.domain.interfaces.ILed;
import it.unibo.Radar22.domain.mock.LedMock;
import it.unibo.Radar22.domain.utils.DomainSystemConfig;

public abstract class LedModel implements ILed{
	private boolean state = false;
	
	//Factory methods
	  public static ILed create() {
		ILed led ; 
		if( DomainSystemConfig.simulation ) led = createLedMock();
		else led = createLedConcrete();
		return led;
	  }
	
	  public static ILed createLedMock() {
			if( DomainSystemConfig.ledGui ) return LedMock.create();
			else return new LedMock();
			
		}

		public static ILed createLedConcrete() {
			//ColorsOut.out("createLedConcrete", ColorsOut.BLUE);
			return new LedConcrete();
		}	
		
		protected abstract void ledActivate( boolean val);
		
		protected void setState( boolean val ) {
			state = val;
			ledActivate( state );
		}
			
		@Override
		public void turnOn(){
			setState( true );
		}
		@Override
		public void turnOff() {
			setState(  false );		
		}
		@Override
		public boolean getState(){
			return state;
		}
}
