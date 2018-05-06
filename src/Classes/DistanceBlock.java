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
public class DistanceBlock extends Block {
    public PortPoint PortIN1 = new PortPoint(PortType.In, "PortIN1");
    public PortPoint PortIN2 = new PortPoint(PortType.In, "PortIN2");
    public PortDouble PortOUT = new PortDouble(PortType.Out, "PortOUT");

    /**
     * Novy DistanceBlock
     */
    public DistanceBlock()
    {

    }


    /**
     * Novy DistanceBlock s nazvem
     * @param name nazev blocku
     */
    public DistanceBlock(String name)
    {
        super(name);
    }


    /**
     * Novy DistanceBlock s nazvem a pozici
     * @param name Nazev blocku
     * @param x Souradnice X
     * @param y Souradnice Y
     */
    public DistanceBlock(String name, double x, double y)
    {
        super(name, x, y, Classes.BlockType.Distance);

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
        if (PortIN1 instanceof PortPoint && 
            PortIN2 instanceof PortPoint && 
            PortOUT instanceof PortDouble)
            
            PortOUT.value = Math.sqrt(
                                Math.pow(PortIN2.x-PortIN1.x, 2)+
                                Math.pow(PortIN2.y-PortIN1.y, 2)
                            );
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
