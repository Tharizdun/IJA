package Classes.Interface;

import Classes.Port;
/**
 *
 * @author Modifikator
 */
public interface BlockInterface {
    public void executeCalculation();
    public double outputValue();
    public void connectPort1(Port outPort);
    public void connectPort2(Port outPort);
    
}
