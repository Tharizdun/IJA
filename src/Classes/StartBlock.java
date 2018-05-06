package Classes;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class StartBlock extends Block {

    public PortDouble PortOUT = new PortDouble(PortType.Out, "PortOUT");
    public double Value;

    /**
     * Novy StartBlock
     */
    public StartBlock()
    {
    }


    /**
     * Novy StartBlock s nazvem
     * @param name nazev blocku
     */
    public StartBlock(String name)
    {
        super(name);
    }


    /**
     * Novy StartBlock s nazvem a pozici
     * @param name Nazev blocku
     * @param x Souradnice X
     * @param y Souradnice Y
     */
    public StartBlock(String name, double x, double y)
    {
        super(name, x, y, Classes.BlockType.Start);

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
        return PortOUT;
    }


    /**
     * Provede operaci nad blockem
     */
    @Override
    public void DoOperation() {
        PortOUT.value = Value;
    }


    /**
     * Znovu prida porty
     */
    public void ReAddPorts()
    {
        Ports.clear();
        Ports.add(PortOUT);}
}
