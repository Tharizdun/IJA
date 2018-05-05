package Classes;

public class EndDoubleBlock extends EndBlock {

    public PortDouble PortIN = new PortDouble(PortType.In);
    public double Value;

    public EndDoubleBlock()
    {
    }

    public EndDoubleBlock(String name)
    {
        super(name);
    }

    public EndDoubleBlock(String name, double x, double y)
    {
        super(name, x, y);

        Ports.add(PortIN);
    }

    @Override
    public Port GetPort(Port blockPort) {
        return PortIN;
    }

    @Override
    public void DoOperation() {
        Value = PortIN.value;
    }
}
