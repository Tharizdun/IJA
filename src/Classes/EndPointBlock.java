package Classes;

public class EndPointBlock extends EndBlock {

    public PortPoint PortIN = new PortPoint(PortType.In, "PortIN");
    public double ValueX;
    public double ValueY;

    public EndPointBlock()
    {
    }

    public EndPointBlock(String name)
    {
        super(name);
    }

    public EndPointBlock(String name, double x, double y)
    {
        super(name, x, y);
        EndBlockType = EndBlockType.Point;
        Ports.add(PortIN);
    }

    @Override
    public Port GetPort(Port blockPort) {
        return PortIN;
    }

    @Override
    public void DoOperation() {
        ValueX = PortIN.x;
        ValueY = PortIN.y;
    }

    public void ReAddPorts()
    {
        Ports.clear();

        Ports.add(PortIN);}
}
