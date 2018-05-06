package Classes;

public class EndDoubleBlock extends EndBlock {

    public PortDouble PortIN = new PortDouble(PortType.In, "PortIN");
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
        EndBlockType = EndBlockType.Double;
        Ports.add(PortIN);
        super.AddPortFunctionality();
    }

    @Override
    public Port GetPort(Port blockPort) {
        return PortIN;
    }

    @Override
    public void DoOperation() {
        Value = PortIN.value;
    }

    public void ReAddPorts()
    {
        Ports.clear();

        Ports.add(PortIN);}
}
