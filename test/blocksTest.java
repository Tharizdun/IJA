
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import Classes.Block;

/**
 *
 * @author xzedni12
 * 
 * Zakladni test bloku
 */
public class blocksTest {
    private Block b1;
    private Block b2;
    private Block b3;
    private Block b4;
    private Block b5;
    private Block b6;
    private Block b7;
      
    @Before
    public void setUp() {
        b1 = new Block("b1","p1",1,"p2",2,"out1",'+',7,7,7,7);
        b2 = new Block("b2","p3",10,"p4",5,"out2",'-',7,7,7,7);
        b3 = new Block("b3","p5",3,"p6",4,"out3",'*',7,7,7,7);
        b4 = new Block("b4","p7",20,"p8",4,"out4",'/',7,7,7,7);
        b5 = new Block("b5","in1","in2","out5",'+',7,7,7,7);
        b6 = new Block("b6","in3","in4","out6",'+',7,7,7,7);
        b7 = new Block("b7","in5","in6","out7",'+',7,7,7,7);
    }
    
    @Test
    public void test01() {
        
        Assert.assertEquals("Test nazvu.", "b1", b1.getName());
        Assert.assertEquals("Test nazvu.", "b2", b2.getName());
        Assert.assertEquals("Test nazvu.", "b3", b3.getName());
        Assert.assertEquals("Test nazvu.", "b4", b4.getName());
        Assert.assertEquals("Test nazvu.", "b5", b5.getName());
        Assert.assertEquals("Test nazvu.", "b6", b6.getName());
        Assert.assertEquals("Test nazvu.", "b7", b7.getName());
    }
    
    @Test
    public void test02() {
        
        b1.executeCalculation();
        Assert.assertEquals("Test vysledku hodnoty.", 3.0, b1.outputValue(),0);
        
        b2.executeCalculation();
        Assert.assertEquals("Test vysledku hodnoty.", 5.0, b2.outputValue(),0);
        
        b3.executeCalculation();
        Assert.assertEquals("Test vysledku hodnoty.", 12.0, b3.outputValue(),0);
        
        b4.executeCalculation();
        Assert.assertEquals("Test vysledku hodnoty.", 5.0, b4.outputValue(),0);
        
        b5.connectPort1(b1.getOutPort());
        b5.connectPort2(b2.getOutPort());
        b5.executeCalculation();
        Assert.assertEquals("Test vysledku hodnoty.", 8.0, b5.outputValue(),0);
        
        b6.connectPort1(b3.getOutPort());
        b6.connectPort2(b4.getOutPort());
        b6.executeCalculation();
        Assert.assertEquals("Test vysledku hodnoty.", 17.0, b6.outputValue(),0);
        
        b7.connectPort1(b5.getOutPort());
        b7.connectPort2(b6.getOutPort());
        b7.executeCalculation();
        Assert.assertEquals("Test vysledku hodnoty.", 25.0, b7.outputValue(),0);
        
    }
}
