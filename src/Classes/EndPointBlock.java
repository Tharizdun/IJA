package Classes;

public class EndPointBlock extends EndBlock {

    public PortPoint PortIN = new PortPoint(PortType.In, "PortIN");
    public double ValueX;
    public double ValueY;

    /**
     * Novy EndPointBlock
     */
    public EndPointBlock()
    {
    }


    /**
     * Novy EndPointBlock s nazvem
     * @param name nazev blocku
     */
    public EndPointBlock(String name)
    {
        super(name);
    }


    /**
     * Novy EndPointBlock s nazvem a pozici
     * @param name Nazev blocku
     * @param x Souradnice X
     * @param y Souradnice Y
     */
    public EndPointBlock(String name, double x, double y)
    {
        super(name, x, y);
        EndBlockType = EndBlockType.Point;
        Ports.add(PortIN);
        super.AddPortFunctionality();
    }

    /**
     * Ziska port
     * @param blockPort Port, ktery vrati
     * @return Port, ktery byl predan argumentem
     */
    @Override
    public Port GetPort(Port blockPort) {
        return PortIN;
    }


    /**
     * Provede operaci nad blockem
     */
    @Override
    public void DoOperation() {
        ValueX = PortIN.x;
        ValueY = PortIN.y;
    }

    /**
     * Znovu prida porty
     */
    public void ReAddPorts()
    {
        Ports.clear();

        Ports.add(PortIN);}
}
