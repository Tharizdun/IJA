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
import sun.rmi.server.LoaderHandler;

import java.util.*;

public class Scheme implements SchemeInterface {

    public Hashtable<String, Block> BlockDictionary;
    private ObjectSize TableSize;
    public List<ConnectionInfo> ConnectionDictionary = new ArrayList<>();
    public List<Block> CountingLine = new ArrayList<>();
    public List<String> CountingLineNames = new ArrayList<>();

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

    public void RestoreConnections()
    {
        for (Block b : BlockDictionary.values())
        {
            b.ReAddPorts();

            for (String con : b.StringConnections)
            {
                String[] data = con.split(";");

                Port ownPort = new Port();

                for (Port p : b.Ports)
                {
                    if (p.FullName.hashCode() == data[0].hashCode()) {
                        ownPort = p;
                        break;
                    }
                }

                Block foreignBlock = new Block();

                for (Block fb : BlockDictionary.values())
                {
                    fb.ReAddPorts();

                    if (fb.Name.hashCode() == data[1].hashCode()) {
                        foreignBlock = fb;
                        break;
                    }
                }

                Port foreignPort = new Port();

                for (Port fp : foreignBlock.Ports)
                {
                    if (fp.FullName.hashCode() == data[2].hashCode()) {
                        foreignPort = fp;
                        break;
                    }
                }

                BlockPort bp = new BlockPort();
                bp.Block = foreignBlock;
                bp.Port = foreignPort;

                b.Connections.put(ownPort, bp);
            }
        }
    }

    public String Run()
    {
        Block endBlock = BlockDictionary.get("End");
        CountingLine.clear();
        LoadBlock(endBlock);

        double res = Count();

        return Double.toString(res);
    }

    private void LoadBlock(Block b)
    {
        CountingLine.add(b);
        CountingLineNames.add(b.Name);
        b.Connections.forEach((k,v) ->
        {
            if (k.PortType == PortType.In)
                LoadBlock(v.Block);
        });
    }

    private double Count()
    {
        List<Block> ss = new ArrayList<>(CountingLine);

        Collections.reverse(ss);

        double res = 0;

        for (Block b : ss)
        {
            b.Connections.forEach((k,v) ->
            {
               if (k.PortType == PortType.In)
                   if (k instanceof PortDouble)
                   {
                       ((PortDouble)k).value = ((PortDouble)v.Port).value;
                   }
                   else if (k instanceof PortPoint) {
                       ((PortPoint) k).x = ((PortPoint) v.Port).x;
                       ((PortPoint) k).y = ((PortPoint) v.Port).y;
                   }
                   else
                   {
                       ((PortVector)k).x = ((PortVector)v.Port).x;
                       ((PortVector)k).y = ((PortVector)v.Port).y;
                   }
            });

            b.DoOperation();

            if (b.Name.hashCode() == "End".hashCode())
                res = ((EndDoubleBlock)b).Value;
        }

        return res;
    }
}
