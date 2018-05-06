package Classes;

public class PointBlock  extends Block {
    public PortDouble PortIN1 = new PortDouble(PortType.In, "PortIN1");
    public PortDouble PortIN2 = new PortDouble(PortType.In, "PortIN2");
    public PortPoint PortOUT = new PortPoint(PortType.Out, "PortOUT");

    /**
     * Novy PointBlock
     */
    public PointBlock()
    {

    }


    /**
     * Novy PointBlock s nazvem
     * @param name nazev blocku
     */
    public PointBlock(String name)
    {
        super(name);
    }


    /**
     * Novy PointBlock s nazvem a pozici
     * @param name Nazev blocku
     * @param x Souradnice X
     * @param y Souradnice Y
     */
    public PointBlock(String name, double x, double y)
    {
        super(name, x, y, Classes.BlockType.Point);

        Ports.add(PortIN1);
        Ports.add(PortIN2);
        Ports.add(PortOUT);
        super.AddPortFunctionality();
    }

    /**
     * Ziska port
     * @param blockPort Port, ktery vrati
     * @return Port, ktery byl predan argumentem
     */
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

    /**
     * Provede operaci nad blockem
     */
    @Override
    public void DoOperation() {
        if (PortIN1 instanceof PortDouble &&
                PortIN2 instanceof PortDouble &&
                PortOUT instanceof PortPoint)

            PortOUT.x = PortIN1.value;
            PortOUT.y = PortIN2.value;
    }

    /**
     * Znovu prida porty
     */
    @Override
    public void ReAddPorts()
    {
        Ports.clear();

        Ports.add(PortIN1);
        Ports.add(PortIN2);
        Ports.add(PortOUT);}
}
