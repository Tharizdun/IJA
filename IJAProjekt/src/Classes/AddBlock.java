package Classes;

public class AddBlock extends Block {

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
    public double DoOperation() {
        return 0;
    }
}
