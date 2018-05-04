/**
 *
 * @author Jan Rajnoha, xrajno09
 */

package Classes;

public class ObjectSize {

    public double X;
    public double Y;

    public ObjectSize()
    {}

    public ObjectSize(double x, double y)
    {
        X = x;
        Y = y;
    }

    public void SetObjectSize(double x, double y)
    {
        X = x;
        Y = y;
    }

    public ObjectSize GetObjectSize()
    {
        return new ObjectSize(X, Y);
    }

    public boolean HasValidSize()
    {
        return X > 0 && Y > 0;
    }

    @Override
    public int hashCode()
    {
        return ("Y:" + Double.toString(X) + "Y:" + Double.toString(Y)).hashCode();
    }

}
