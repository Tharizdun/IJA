/**
 *
 * @author Jan Rajnoha, xrajno09
 */

package Classes;

public abstract class Block {

    public String Name;
    public double LocX;
    public double LocY;

    public Block()
    {

    }

    public Block(String name)
    {
        Name = name;
    }

    public Block(String name, double x, double y)
    {
        Name = name;
        LocX = x;
        LocY = y;
    }


    public abstract double DoOperation();
}
