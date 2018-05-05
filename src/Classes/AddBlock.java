package Classes;

import javafx.scene.shape.Rectangle;

public class AddBlock extends Block {
    public PortDouble PortIN1 = new PortDouble(PortType.In);
    public PortDouble PortIN2 = new PortDouble(PortType.In);
    public PortDouble PortOUT = new PortDouble(PortType.Out);

    public AddBlock()
    {
        Rectangle input1 = new Rectangle(10, 10);
        input1.setX(0);
        input1.setY(45);
    }

    public AddBlock(String name)
    {
        super(name);
    }

    public AddBlock(String name, double x, double y)
    {
        super(name, x, y);
        BlockType = Classes.BlockType.Add;
        Connections.put(PortIN1, null);
        Connections.put(PortIN2, null);
        Connections.put(PortOUT, null);
    }
    

    @Override
    public void DoOperation() {
        if (PortIN1 instanceof PortDouble && 
            PortIN2 instanceof PortDouble && 
            PortOUT instanceof PortDouble)
            
            PortOUT.value = PortIN1.value + PortIN2.value;
    }
}
