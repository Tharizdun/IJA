/**
 *
 * @author Jan Rajnoha, xrajno09
 */

import Classes.AddBlock;
import Classes.Block;
import Classes.ObjectSize;
import Classes.Scheme;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class TestClass {

    private Scheme schema;
    private ObjectSize referenceSize;
    private Block blok1;
    private Block blok2;
    private Block blok3;
    private Block blok4;
    private Block blok5;
    private Block blok6;
    private Block blok7;
    private Block blok8;
    private Block blok9;

    @Before
    public void Init()
    {
        schema = new Scheme(300, 300);

        referenceSize = new ObjectSize(45, 500);

        blok1 = new AddBlock("blok1",10, 10);
        blok2 = new AddBlock("blok2",20, 10);
        blok3 = new AddBlock("blok3",50, 10);
        blok4 = new AddBlock("blok4",0, 10);
        blok5 = new AddBlock("blok5",90, 10);
        blok6 = new AddBlock("blok6",11, 10);
        blok7 = new AddBlock("blok7",-5, 10);
        blok8 = new AddBlock("blok1",10, 10);
        blok9 = new AddBlock("blok2",1000, 10);
    }

    @Test
    public void TestBlocks()
    {
        Assert.assertEquals("Test pridani bloku", true, schema.AddBlock(blok1));
        Assert.assertEquals("Test pridani bloku", true, schema.AddBlock(blok2));
        Assert.assertEquals("Test pridani bloku", true, schema.AddBlock(blok3));
        Assert.assertEquals("Test pridani bloku", true, schema.AddBlock(blok4));
        Assert.assertEquals("Test pridani bloku", true, schema.AddBlock(blok5));
        Assert.assertEquals("Test pridani bloku", true, schema.AddBlock(blok6));
        Assert.assertEquals("Test pridani bloku", false, schema.AddBlock(blok7));
        Assert.assertEquals("Test pridani bloku", false, schema.AddBlock(blok8));
        Assert.assertEquals("Test pridani bloku", false, schema.AddBlock(blok9));

        Assert.assertEquals("Test odebrani bloku", true, schema.RemoveBlock("blok6"));
        Assert.assertEquals("Test odebrani bloku", true, schema.RemoveBlock("blok5"));
        Assert.assertEquals("Test odebrani bloku", false, schema.RemoveBlock("blok8"));
        Assert.assertEquals("Test odebrani bloku", false, schema.RemoveBlock("blok9"));
    }

    @Test
    public void TestTableSize()
    {
        Assert.assertEquals("Test pridani bloku", true, schema.AddBlock(blok1));
        Assert.assertEquals("Test pridani bloku", true, schema.AddBlock(blok2));
        Assert.assertEquals("Test pridani bloku", true, schema.AddBlock(blok3));
        Assert.assertEquals("Test pridani bloku", true, schema.AddBlock(blok4));

        Assert.assertEquals("Test zmeny stolu", true, schema.SetSchemeTableSize(500, 500));

        Assert.assertEquals("Test zmeny stolu", false, schema.SetSchemeTableSize(45, 500));
        Assert.assertEquals("Test odebrani bloku", true, schema.RemoveBlock("blok3"));
        Assert.assertEquals("Test zmeny stolu", true, schema.SetSchemeTableSize(45, 500));

        //Assert.assertEquals("Test velikosti stolu", referenceSize, schema.GetSchemeTableSize());
    }

}
