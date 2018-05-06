package Classes;

import javafx.scene.shape.Rectangle;

public class AddBlock extends Block {
    public PortDouble PortIN1 = new PortDouble(PortType.In, "PortIN1");
    public PortDouble PortIN2 = new PortDouble(PortType.In, "PortIN2");
    public PortDouble PortOUT = new PortDouble(PortType.Out, "PortOUT");

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
        super(name, x, y,Classes.BlockType.Add);

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
            
            PortOUT.value = PortIN1.value + PortIN2.value;
    }
}
