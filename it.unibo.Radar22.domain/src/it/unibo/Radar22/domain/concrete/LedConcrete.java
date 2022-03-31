package it.unibo.Radar22.domain.concrete;

import java.io.IOException;

import it.unibo.Radar22.domain.interfaces.ILed;
import it.unibo.Radar22.domain.models.LedModel;

public class LedConcrete extends LedModel implements ILed{
	private Runtime rt  = Runtime.getRuntime();
	
	@Override
	protected void ledActivate(boolean val) {
		try {
		      if( val ) rt.exec( "sudo bash led25GpioTurnOn.sh" );
		      else rt.exec( "sudo bash led25GpioTurnOff.sh" );
		    } catch (IOException e) { System.out.println("Eccezione"); 
		  }
	}

}
