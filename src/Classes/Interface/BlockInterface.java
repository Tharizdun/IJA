package Classes.Interface;

import Classes.Port;
/**
 *
 * @author xzedni12
 * 
 * Rozhrani, ktere je implementovano Blokem
 */
public interface BlockInterface {
    public void executeCalculation();
    public double outputValue();
    public void connectPort1(Port outPort);
    public void connectPort2(Port outPort);
    public Port getOutPort();
    public String getName();
    
}
