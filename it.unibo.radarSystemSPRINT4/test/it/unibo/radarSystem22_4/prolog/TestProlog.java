package it.unibo.radarSystem22_4.prolog;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import it.unibo.radarSystem22_4.comm.utils.ColorsOut;


public class TestProlog {
    private String cmd;
    private Struct cmdAsTerm;

    @Before
    public void setUp() {
         cmd       = "msg( cmd, dispatch, main, led, turn(off), 1)";	
         cmdAsTerm = (Struct) Term.createTerm(cmd);   	
    }
	@Test
	public void test0() {
	    ColorsOut.outappl( "test0 ----------- " , ColorsOut.GREEN );
	    //ci da un argomento di ordine 4 -> termine prolog strutturato
 	    Struct payload   = (Struct) cmdAsTerm.getArg(4);
 	    //Payload è una struttura ch rappresenta il payload
	    ColorsOut.out( "payload=" + payload );
	    assertEquals( payload.toString(), "turn(off)"); // rappresentazione in termini di stringa di questa roba
	    Term onOff       = payload.getArg(0); //mi dà il primo argomento della stringa di payload
	    ColorsOut.out( "onOff=" + onOff );
	    assertEquals( onOff.toString(), "off");		
	}
	
	@Test
	//JSON non ha il concetto di unificazione perciò per fare un concetto di unificazione
	//uso una macchina prolog e vedo se una cosa è vera o meno.
	public void testUnify() {
	    ColorsOut.outappl( "testUnify ----------- " , ColorsOut.GREEN );
		Prolog pengine = new Prolog();
 	    String cmdTemplate = "msg( cmd, MSGTYPE, main, led, turn(X), 1)";
 	    //La stringa cmdTemplate è una stringa che ha come argomento cmd, ma il secondo è una variabile (non è più così)
 	    //Quando traduco questa stringa in prolog ho una variabile
 	    //turn(x) anche x è una variabile, potrei avere sia turnOn che turnOff
 	   
	    String goal      = cmdAsTerm+"="+cmdTemplate;
	  //prolog mi dice se la stringa è vera o false "=" è il simbolo di unificazione (non di ugalianza)
	    //In particolare unifica solo se MSGTYPE = dspatch e X =off
	    //N.B. le clausole PROLOG finiscono sempre con .
	    try {
			SolveInfo sol = pengine.solve( goal+"." );
			//sol serve per provare ad unificare cmd e cmdAsTerm
		    ColorsOut.out( "sol=" + sol );
		    Term msgType = sol.getVarValue("MSGTYPE");
		    ColorsOut.out( "msgType=" + msgType );
		    assertEquals( msgType.toString(), "dispatch");	
		    Term data = sol.getVarValue("X");
		    ColorsOut.out( "data=" + data );
		    assertEquals( data.toString(), "off");	
		} catch ( Exception e) {
 			e.printStackTrace();
		}	    
	}
}