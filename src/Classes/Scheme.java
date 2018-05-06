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
    public List<Block> TraceLine = new ArrayList<>();
    public List<String> CountingLineNames = new ArrayList<>();
    private int TraceLineSize = -1;
    private int TraceCurrentIndex = 0;
    private boolean TraceRunning = false;

    /**
     * Deklarace noveho schema
     */
    public Scheme()
    {
        BlockDictionary = new Hashtable<String, Block>();

        TableSize = new ObjectSize(0, 0);
    }

    /**
     * Deklarace noveho schema o velikosti x a y
     * @param x Rozmer x schema
     * @param y Rozmer y schema
     */
    public Scheme(double x, double y)
    {
        BlockDictionary = new Hashtable<String, Block>();

        TableSize = new ObjectSize(x, y);
    }

    /**
     * Prida blok do schematu
     * @param newBlock Novy blok
     * @return Vysledek, zda byl blok pridan
     */
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

    /**
     * Odebere blok ze schema
     * @param blockName Jmeno odebiraneho bloku
     * @return Vysledek, zda byl blok odebran
     */
    @Override
    public boolean RemoveBlock(String blockName) {

        if (!BlockDictionary.containsKey(blockName))
            return false;

        BlockDictionary.remove(blockName);

        return true;
    }

    /**
     * Zjisti velikost schema
     * @return Velikost schema
     */
    @Override
    public ObjectSize GetSchemeTableSize() {
        return TableSize.GetObjectSize();
    }

    /**
     * Nastavi velikost schema
     * @param x Rozmer x schema
     * @param y Rozmer Y schema
     * @return Vysledek, zda byla operace provedena
     */
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

    /**
     * Ulozi schema
     * @param outputFile Soubor, kam se ulozi schema
     */
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

    /**
     * Nacte schema
     * @param inputFile XML soubor se schematem
     */
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

    /**
     * Zjisti pocet bloku daneho typu
     * @param blockType Typ bloku
     * @return Pocet bloku daneho typu
     */
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

    /**
     * Zjisti volne porty
     * @param portType Typ portu
     * @param portname Nazev portu
     * @return List s volnymi porty
     */
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

    /**
     * Obnovi connections mezi bloky
     */
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

    /**
     * Spusti vypocet
     * @return Vysledek
     */
    public String Run()
    {
        TraceRunning = false;

        String res = "";

        if (!BlockDictionary.containsKey("End"))
            return "End not found";

        Block endBlock = BlockDictionary.get("End");
        CountingLine.clear();
        CountingLineNames.clear();
        res = LoadBlock(endBlock);

        if (res.hashCode() == "".hashCode())
            res = Count();

        return (res);
    }

    /**
     * Nacte bloky pro vypocet
     * @param b aktualni blok
     * @return Vysledek nacitani
     */
    private String LoadBlock(Block b)
    {
        if (!CountingLineNames.contains(b.Name))
            CountingLineNames.add(b.Name);
        else
            return "Cycle detected !!!";

        CountingLine.add(b);

        if (b.Ports.size() != b.Connections.size())
            return "Free ports !!!";

        b.Connections.forEach((k,v) ->
        {
            if (k.PortType == PortType.In)
                LoadBlock(v.Block);
        });

        return "";
    }

    /**
     * Vypocet
     * @return Vysledek
     */
    private String Count()
    {
        List<Block> ss = new ArrayList<>(CountingLine);

        Collections.reverse(ss);

        String res = "";

        for (Block b : ss)
        {
            b.isHighlighted(true);
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
                if (b instanceof EndDoubleBlock)
                {
                    res = Double.toString(((EndDoubleBlock)b).Value);
                }
                else if (b instanceof EndPointBlock) {

                    res = "x [" + ((EndPointBlock)b).ValueX + ", " + ((EndPointBlock)b).ValueY + "]";
                }
                else
                {
                    res = "x = (" + ((EndVectorBlock)b).ValueX + ", " + ((EndVectorBlock)b).ValueY + ")";
                }
            b.isHighlighted(false);
        }

        return res;
    }

    /**
     * Trasovani vypoctu
     * @return Vysledek
     */
    public String Trace()
    {
        String res = "";

        if (!BlockDictionary.containsKey("End"))
            return "End not found";

        if (TraceLineSize != CountingLine.size())
            TraceRunning = false;

        if (TraceCurrentIndex == TraceLineSize)
            TraceRunning = false;

        if (!TraceRunning)
        {
            TraceCurrentIndex = 0;
            Block endBlock = BlockDictionary.get("End");
            CountingLine.clear();
            CountingLineNames.clear();
            res = LoadBlock(endBlock);

            for (Block b : CountingLine)
                b.isHighlighted(false);

            TraceLine = CountingLine;
            Collections.reverse(TraceLine);

            TraceLineSize = CountingLine.size();
        }

        if (res.hashCode() == "".hashCode()) {
            TraceRunning = true;
            res = TraceCount(TraceCurrentIndex);
            TraceCurrentIndex++;
        }
        return res;
    }

    /**
     * Krok trasovani
     * @param currentIndex Aktualni index
     * @return Vysledek akce
     */
    private String TraceCount(int currentIndex)
    {
        String res = "";

        if (currentIndex > 0) {

            (TraceLine.get(currentIndex - 1)).isHighlighted(false);
        }

        Block b = TraceLine.get(currentIndex);

        b.isHighlighted(true);

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

                if (b instanceof EndDoubleBlock)
                {
                    res = Double.toString(((EndDoubleBlock)b).Value) + "; Block Name: " + b.Name + " - Trace finished";
                }
                else if (b instanceof EndVectorBlock)
                {
                    res = "x = (" + ((EndVectorBlock)b).ValueX + ", " + ((EndVectorBlock)b).ValueY + "); Block Name: " + b.Name + " - Trace finished";
                }
                else if (b instanceof EndPointBlock) {

                    res = "x [" + ((EndPointBlock)b).ValueX + ", " + ((EndPointBlock)b).ValueY + "]; Block Name: " + b.Name + " - Trace finished";
                }
                else if (b instanceof StartBlock)
                {
                    res = Double.toString(((StartBlock)b).Value) + "; Block Name: " + b.Name;
                }
                else if (b instanceof PointBlock) {
                    res = "[" + ((PointBlock)b).PortOUT.x + ", " + ((PointBlock)b).PortOUT.y + "]; Block Name: " + b.Name;
                }
                else if (b instanceof VectorBlock) {

                    res = "(" + ((VectorBlock) b).PortOUT.x + ", " + ((VectorBlock) b).PortOUT.y + "); Block Name: " + b.Name;
                }
                else if (b instanceof AddBlock){
                    res = Double.toString(((AddBlock) b).PortOUT.value) + "; Block Name: " + b.Name;
                }
                else if (b instanceof SubBlock){
                    res = Double.toString(((SubBlock) b).PortOUT.value) + "; Block Name: " + b.Name;
                }
                else if (b instanceof MulBlock){
                    res = Double.toString(((MulBlock) b).PortOUT.value) + "; Block Name: " + b.Name;
                }
                else if (b instanceof DivBlock){
                    res = Double.toString(((DivBlock) b).PortOUT.value) + "; Block Name: " + b.Name;
                }
        return res;
    }
}
