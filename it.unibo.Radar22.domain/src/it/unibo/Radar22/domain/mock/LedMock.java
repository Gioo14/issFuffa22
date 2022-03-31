package it.unibo.Radar22.domain.mock;

import it.unibo.Radar22.domain.interfaces.*;
import it.unibo.Radar22.domain.models.LedModel;
import it.unibo.Radar22.domain.utils.ColorsOut;

public class LedMock extends LedModel implements ILed{
	@Override
	protected void ledActivate(boolean val) {	
		showState();
	}

	protected void showState(){
		ColorsOut.outappl("LedMock state=" + getState(), ColorsOut.MAGENTA );
	}
}
