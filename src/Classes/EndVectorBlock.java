package Classes;

public class EndVectorBlock extends EndBlock {

    public PortVector PortIN = new PortVector(PortType.In);
    public double ValueX;
    public double ValueY;

    public EndVectorBlock()
    {
    }

    public EndVectorBlock(String name)
    {
        super(name);
    }

    public EndVectorBlock(String name, double x, double y)
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
        ValueX = PortIN.x;
        ValueY = PortIN.y;
    }
}
