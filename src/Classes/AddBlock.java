package Classes;

public class AddBlock extends Block {
    public PortDouble PortIN1;
    public PortDouble PortIN2;
    public PortDouble PortOUT;
    

    public AddBlock()
    {

    }

    public AddBlock(String name)
    {
        super(name);
    }

    public AddBlock(String name, double x, double y)
    {
        super(name, x, y);
    }
    

    @Override
    public void DoOperation() {
        if (PortIN1 instanceof PortDouble && 
            PortIN2 instanceof PortDouble && 
            PortOUT instanceof PortDouble)
            
            PortOUT.value = PortIN1.value + PortIN2.value;
    }
}
