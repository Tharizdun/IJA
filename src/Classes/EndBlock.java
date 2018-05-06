package Classes;

public abstract class EndBlock extends Block {

    public EndBlock()
    {
    }

    public EndBlock(String name)
    {
        super(name);
    }

    public EndBlock(String name, double x, double y)
    {
        super(name, x, y, Classes.BlockType.End);
    }
}
