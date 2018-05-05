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
    public PortPoint PortIN1 = new PortPoint(PortType.In);
    public PortPoint PortIN2 = new PortPoint(PortType.In);
    public PortVector PortOUT = new PortVector(PortType.Out);

    public VectorBlock()
    {

    }

    public VectorBlock(String name)
    {
        super(name);
    }

    public VectorBlock(String name, double x, double y)
    {
        super(name, x, y);
        BlockType = Classes.BlockType.Vector;
        Connections.put(PortIN1, null);
        Connections.put(PortIN2, null);
        Connections.put(PortOUT, null);
    }
    

    @Override
    public void DoOperation() {
        if (PortIN1 instanceof PortPoint && 
            PortIN2 instanceof PortPoint && 
            PortOUT instanceof PortVector){
            
            PortOUT.x = PortIN2.x - PortIN1.x;
            PortOUT.y = PortIN2.y - PortIN1.y;
        }
    }
}
