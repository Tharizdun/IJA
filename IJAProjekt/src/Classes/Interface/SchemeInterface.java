/**
 *
 * @author Jan Rajnoha, xrajno09
 */

package Classes.Interface;

import Classes.Block;
import Classes.ObjectSize;


public interface SchemeInterface {

    public boolean AddBlock(Block newBlock);
    public boolean RemoveBlock(String blockName);

    public ObjectSize GetSchemeTableSize();
    public boolean SetSchemeTableSize(double x, double y);

    public void SaveScheme(String outputFile);
    public void LoadScheme(String inputFile);
}
