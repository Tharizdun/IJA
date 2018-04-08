package Classes;
import Classes.Interface.BlockInterface;

/**
 *
 * @author xzedni12
 */
public class Block implements BlockInterface{
    protected String name;
    protected Port PortIn1;
    protected Port PortIn2;
    protected Port PortOut;
    protected char operation;
    protected int pos_x;
    protected int pos_y;
    protected int width;
    protected int height;
    
    public Block
        (
            String name,
            String n1,double val1,
            String n2,double val2,
            String n3,
            char op,
            int posx,int posy,
            int width,int height
        ){
        
        this.name = name;
        this.PortIn1 = new Port(n1,val1);
        this.PortIn2 = new Port(n2,val2);
        this.PortOut = new Port(n3);
        this.operation = op;
        this.pos_x = posx;
        this.pos_y = posy;
        this.width = width;
        this.height = height;
    }
        
    public Block
        (
            String name,
            String n1,double val1,
            String n2,
            String n3,
            char op,
            int posx,int posy,
            int width,int height
        ){
        
        this.name = name;
        this.PortIn1 = new Port(n1,val1);
        this.PortIn2 = new Port(n2);
        this.PortOut = new Port(n3);
        this.operation = op;
        this.pos_x = posx;
        this.pos_y = posy;
        this.width = width;
        this.height = height;
    }
        
    public Block
        (
            String name,
            String n1,
            String n2,
            String n3,
            char op,
            int posx,int posy,
            int width,int height
        ){
        
        this.name = name;
        this.PortIn1 = new Port(n1);
        this.PortIn2 = new Port(n2);
        this.PortOut = new Port(n3);
        this.operation = op;
        this.pos_x = posx;
        this.pos_y = posy;
        this.width = width;
        this.height = height;
    }
    
    public void executeCalculation(){
        switch(this.operation){
            case '+':
                this.PortOut.value = this.PortIn1.value + this.PortIn2.value;
            break;
            case '-':
                this.PortOut.value = this.PortIn1.value - this.PortIn2.value;
            break;
            case '*':
                this.PortOut.value = this.PortIn1.value * this.PortIn2.value;
            break;
            case '/':
                if (this.PortIn2.value != 0)
                this.PortOut.value = this.PortIn1.value / this.PortIn2.value;
            break;
            default:break;
        }
    }
    public double outputValue(){
        return this.PortOut.value;
    }
    
    public void connectPort1(Port outPort){
        this.PortIn1 = outPort;
    }
    
    public void connectPort2(Port outPort){
        this.PortIn2 = outPort;
    }
    
    public Port getOutPort(){
        return this.PortOut;
    }
    
    public String getName(){
        return this.name;
    }
    
}
