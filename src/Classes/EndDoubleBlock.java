package Classes;

public class EndDoubleBlock extends EndBlock {

    public PortDouble PortIN = new PortDouble(PortType.In, "PortIN");
    public double Value;

    /**
     * Novy EndDoubleBlock
     */
    public EndDoubleBlock()
    {
    }


    /**
     * Novy EndDoubleBlock s nazvem
     * @param name nazev blocku
     */
    public EndDoubleBlock(String name)
    {
        super(name);
    }

    /**
     * Novy EndDoubleBlock s nazvem a pozici
     * @param name Nazev blocku
     * @param x Souradnice X
     * @param y Souradnice Y
     */
    public EndDoubleBlock(String name, double x, double y)
    {
        super(name, x, y);
        EndBlockType = EndBlockType.Double;
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
        Value = PortIN.value;
    }


    /**
     * Znovu prida porty
     */
    public void ReAddPorts()
    {
        Ports.clear();

        Ports.add(PortIN);}
}
