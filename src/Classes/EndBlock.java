package Classes;

public abstract class EndBlock extends Block {

    public EndBlockType EndBlockType;

    /**
     * Novy EndBlock
     */
    public EndBlock()
    {
    }

    /**
     * Novy EndBlock s nazvem
     * @param name nazev blocku
     */
    public EndBlock(String name)
    {
        super(name);
    }

    /**
     * Novy EndBlock s nazvem a pozici
     * @param name Nazev blocku
     * @param x Souradnice X
     * @param y Souradnice Y
     */
    public EndBlock(String name, double x, double y)
    {
        super(name, x, y, Classes.BlockType.End);
    }
}
