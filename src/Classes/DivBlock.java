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
    public PortDouble PortIN1 = new PortDouble(PortType.In, "PortIN1");
    public PortDouble PortIN2 = new PortDouble(PortType.In, "PortIN2");
    public PortDouble PortOUT = new PortDouble(PortType.Out, "PortOUT");

    /**
     * Novy DivBlock
     */
    public DivBlock()
    {

    }


    /**
     * Novy DivBlock s nazvem
     * @param name nazev blocku
     */
    public DivBlock(String name)
    {
        super(name);
    }


    /**
     * Novy DivBlock s nazvem a pozici
     * @param name Nazev blocku
     * @param x Souradnice X
     * @param y Souradnice Y
     */
    public DivBlock(String name, double x, double y)
    {
        super(name, x, y, Classes.BlockType.Div);

        Ports.add(PortIN1);
        Ports.add(PortIN2);
        Ports.add(PortOUT);
        super.AddPortFunctionality();
    }

    /**
     * Ziska port
     * @param blockPort Port, ktery vrati
     * @return Port, ktery byl predan argumentem
     */
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

    /**
     * Provede operaci nad blockem
     */
    @Override
    public void DoOperation() {
        if (PortIN1 instanceof PortDouble && 
            PortIN2 instanceof PortDouble && 
            PortOUT instanceof PortDouble)
            
            if (PortIN2.value != 0)
                PortOUT.value = PortIN1.value / PortIN2.value;
    }

    /**
     * Znovu prida porty
     */
    @Override
    public void ReAddPorts()
    {
        Ports.clear();

        Ports.add(PortIN1);
        Ports.add(PortIN2);
        Ports.add(PortOUT);}
}
