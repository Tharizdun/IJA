package Classes;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class StartBlock extends Block {

    public PortDouble PortOUT = new PortDouble(PortType.Out, "PortOUT");
    public double Value;

    public StartBlock()
    {
    }

    public StartBlock(String name)
    {
        super(name);
    }

    public StartBlock(String name, double x, double y)
    {
        super(name, x, y, Classes.BlockType.Start);

        Ports.add(PortOUT);
        super.AddPortFunctionality();
        
    }

    @Override
    public Port GetPort(Port blockPort) {
        return PortOUT;
    }

    @Override
    public void DoOperation() {
        PortOUT.value = Value;
    }

    public void ReAddPorts()
    {
        Ports.clear();
        Ports.add(PortOUT);}
}
