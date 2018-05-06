/**
 *
 * @author Jan Rajnoha, xrajno09
 */

package Classes;

public class ObjectSize {

    public double X;
    public double Y;

    /**
     * Vytvori novou tridu ObjectSize
     */
    public ObjectSize()
    {}

    /**
     * Vytvori novou tridu ObjectSize o zadanych rozmerech
     * @param x Rozzmer X
     * @param y Rozmer Y
     */
    public ObjectSize(double x, double y)
    {
        X = x;
        Y = y;
    }

    /**
     * Nastavi velikost
     * @param x rozmer x
     * @param y rozmer Y
     */
    public void SetObjectSize(double x, double y)
    {
        X = x;
        Y = y;
    }

    /**
     * Ziska velikost ObjectSize
     * @return ObjectSize
     */
    public ObjectSize GetObjectSize()
    {
        return new ObjectSize(X, Y);
    }

    /**
     * Zkontroluje validitu ObjectSize
     * @return Validnost ObjectSize
     */
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
