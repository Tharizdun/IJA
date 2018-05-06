package Classes;

public class EndVectorBlock extends EndBlock {

    public PortVector PortIN = new PortVector(PortType.In, "PortIN");
    public double ValueX;
    public double ValueY;

    /**
     * Novy EndVectorBlock
     */
    public EndVectorBlock()
    {
    }


    /**
     * Novy EndVectorBlock s nazvem
     * @param name nazev blocku
     */
    public EndVectorBlock(String name)
    {
        super(name);
    }


    /**
     * Novy EndVectorBlock s nazvem a pozici
     * @param name Nazev blocku
     * @param x Souradnice X
     * @param y Souradnice Y
     */
    public EndVectorBlock(String name, double x, double y)
    {
        super(name, x, y);
        EndBlockType = EndBlockType.Vector;
        Ports.add(PortIN);
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
