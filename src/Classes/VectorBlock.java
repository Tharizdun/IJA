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
public class VectorBlock extends Block {
    public PortPoint PortIN1 = new PortPoint(PortType.In, "PortIN1");
    public PortPoint PortIN2 = new PortPoint(PortType.In, "PortIN2");
    public PortVector PortOUT = new PortVector(PortType.Out, "PortOUT");

    /**
     * Novy VectorBlock
     */
    public VectorBlock()
    {

    }

    /**
     * Novy VectorBlock s nazvem
     * @param name nazev blocku
     */
    public VectorBlock(String name)
    {
        super(name);
    }

    /**
     * Novy VectorBlock s nazvem a pozici
     * @param name Nazev blocku
     * @param x Souradnice X
     * @param y Souradnice Y
     */
    public VectorBlock(String name, double x, double y)
    {
        super(name, x, y, Classes.BlockType.Vector);

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
            PortOUT instanceof PortVector){
            
            PortOUT.x = PortIN2.x - PortIN1.x;
            PortOUT.y = PortIN2.y - PortIN1.y;
        }
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
