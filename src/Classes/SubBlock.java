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
public class SubBlock extends Block {
    public PortDouble PortIN1;
    public PortDouble PortIN2;
    public PortDouble PortOUT;
    

    public SubBlock()
    {

    }

    public SubBlock(String name)
    {
        super(name);
    }

    public SubBlock(String name, double x, double y)
    {
        super(name, x, y);
    }
    

    @Override
    public void DoOperation() {
        if (PortIN1 instanceof PortDouble && 
            PortIN2 instanceof PortDouble && 
            PortOUT instanceof PortDouble)
            
            PortOUT.value = PortIN1.value - PortIN2.value;
    }
}
