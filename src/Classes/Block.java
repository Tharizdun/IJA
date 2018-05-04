/**
 *
 * @author Jan Rajnoha, xrajno09
 */

package Classes;

public abstract class Block {

    public String Name;
    public double PosX;
    public double PosY;
    public double Width = 60;
    public double Height = 100;
    

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
        PosX = x;
        PosY = y;
    }


    public abstract void DoOperation();
}
