/**
 *
 * @author Jan Rajnoha, xrajno09
 */

package Classes;
import Classes.Interface.SchemeInterface;

import java.awt.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.scene.shape.Line;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Scheme implements SchemeInterface {

    public Hashtable<String, Block> BlockDictionary;
    private ObjectSize TableSize;

    public Scheme()
    {
        BlockDictionary = new Hashtable<String, Block>();

        TableSize = new ObjectSize(0, 0);
    }

    public Scheme(double x, double y)
    {
        BlockDictionary = new Hashtable<String, Block>();

        TableSize = new ObjectSize(x, y);
    }

    @Override
    public boolean AddBlock(Block newBlock) {

        if (!TableSize.HasValidSize())
            return false;

        if (BlockDictionary.containsKey(newBlock.Name))
            return false;

        if (newBlock.PosX < 0 || newBlock.PosY < 0)
            return false;

        BlockDictionary.put(newBlock.Name, newBlock);

        return true;
    }

    @Override
    public boolean RemoveBlock(String blockName) {

        if (!BlockDictionary.containsKey(blockName))
            return false;

        BlockDictionary.remove(blockName);

        return true;
    }

    @Override
    public ObjectSize GetSchemeTableSize() {
        return TableSize.GetObjectSize();
    }

    @Override
    public boolean SetSchemeTableSize(double x, double y) {

        if (x < TableSize.X || y < TableSize.Y)
        {
            for (Block unit : BlockDictionary.values())
            {
                if (unit.PosX > x || unit.PosY > y)
                    return false;
            }
        }

        TableSize.SetObjectSize(x, y);

        return true;
    }

    @Override
    public void SaveScheme(String outputFile) {

        try
        {
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            XMLEncoder encoderXML = new XMLEncoder(outputStream);

            SchemeStorage schemeStorage = new SchemeStorage();
            schemeStorage.TableSize = TableSize;
            schemeStorage.BlockDictionary = BlockDictionary;

            encoderXML.writeObject(schemeStorage);
            encoderXML.close();
        }
        catch (Exception e)
        {}
    }

    @Override
    public void LoadScheme(String inputFile) {
        try
        {
            FileInputStream inputStream = new FileInputStream(inputFile);

            XMLDecoder decoderXML = new XMLDecoder(inputStream);

            SchemeStorage schemeStorage = (SchemeStorage) decoderXML.readObject();
            decoderXML.close();

            TableSize = schemeStorage.TableSize;
            BlockDictionary = schemeStorage.BlockDictionary;
        }
        catch (Exception e)
        {}
    }
}
