/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Modifikator
 */
public class DivBlock extends Block {
    public PortDouble PortIN1 = new PortDouble(PortType.In);
    public PortDouble PortIN2 = new PortDouble(PortType.In);
    public PortDouble PortOUT = new PortDouble(PortType.Out);

    public DivBlock()
    {

    }

    public DivBlock(String name)
    {
        super(name);
    }

    public DivBlock(String name, double x, double y)
    {
        super(name, x, y, Classes.BlockType.Div);

        Ports.add(PortIN1);
        Ports.add(PortIN2);
        Ports.add(PortOUT);
    }

    @Override
    public Port GetPort(Port blockPort) {
        if (blockPort == PortOUT)
            return PortOUT;
        else
        if (blockPort == PortIN1)
            return PortIN1;
        else
            return PortIN2;
    }

    @Override
    public void DoOperation() {
        if (PortIN1 instanceof PortDouble && 
            PortIN2 instanceof PortDouble && 
            PortOUT instanceof PortDouble)
            
            if (PortIN2.value != 0)
                PortOUT.value = PortIN1.value / PortIN2.value;
    }
}
