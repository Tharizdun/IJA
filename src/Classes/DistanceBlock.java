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
    public PortPoint PortIN1;
    public PortPoint PortIN2;
    public PortDouble PortOUT;
    

    public DistanceBlock()
    {

    }

    public DistanceBlock(String name)
    {
        super(name);
    }

    public DistanceBlock(String name, double x, double y)
    {
        super(name, x, y);
    }
    

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
}
