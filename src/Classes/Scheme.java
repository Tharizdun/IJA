/**
 *
 * @author Jan Rajnoha, xrajno09
 */

package Classes;
import Classes.Interface.SchemeInterface;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class Scheme implements SchemeInterface {

    public Hashtable<String, Block> BlockDictionary;
    private ObjectSize TableSize;
    public List<ConnectionInfo> ConnectionDictionary = new ArrayList<>();

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

            List<ConnectionInfo> connections = new ArrayList<>();

            for(Block b : BlockDictionary.values())
            {
                b.Connections.forEach((k,v) ->
                {
                    ConnectionInfo ci = new ConnectionInfo();
                    BlockPort bp = new BlockPort();

                    bp.Block = b;
                    bp.Port = k;

                    ci.bp1 = bp;
                    ci.bp2 = v;

                    connections.add(ci);
                });
            }

            schemeStorage.ConnectionDictionary = connections;

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
            ConnectionDictionary = schemeStorage.ConnectionDictionary;
        }
        catch (Exception e)
        {}
    }

    public int GetBlockTypeCount(BlockType blockType)
    {
        int i = 0;

        for (Block b : BlockDictionary.values())
        {
            if (b.BlockType == blockType)
                i++;
        }

        return i;
    }

    public List<BlockPort> GetFreePorts(PortType portType, String portname)
    {
        List<BlockPort> res = new ArrayList<>();

        for(Block b : BlockDictionary.values())
        {
            for (Object p : b.Ports)
            {
                if (p instanceof Port)
                    if (!b.Connections.containsKey((Port)p))
                        if (((Port)p).Name == portname && ((Port)p).PortType == portType)
                        {
                            BlockPort bp = new BlockPort();
                            bp.Block = b;
                            bp.Port = (Port)p;
                            res.add(bp);
                        }
            }
        }

        return res;
    }
}
