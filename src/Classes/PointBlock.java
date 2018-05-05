package Classes;

public class PointBlock  extends Block {
    public PortDouble PortIN1 = new PortDouble(PortType.In);
    public PortDouble PortIN2 = new PortDouble(PortType.In);
    public PortPoint PortOUT = new PortPoint(PortType.Out);

    public PointBlock()
    {

    }

    public PointBlock(String name)
    {
        super(name);
    }

    public PointBlock(String name, double x, double y)
    {
        super(name, x, y);
        BlockType = Classes.BlockType.Point;

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
                PortOUT instanceof PortPoint)

            PortOUT.x = PortIN1.value;
            PortOUT.y = PortIN2.value;
    }
}
