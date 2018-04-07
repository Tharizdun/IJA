package Classes;
import Classes.Interface.BlockInterface;

/**
 *
 * @author Modifikator
 */
public class Block implements BlockInterface{
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
            String n1,double val1,
            String n2,double val2,
            String n3,
            char op,
            int posx,int posy,
            int width,int height
        ){
        
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
            String n1,double val1,
            String n2,
            String n3,
            char op,
            int posx,int posy,
            int width,int height
        ){
        
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
            String n1,
            String n2,
            String n3,
            char op,
            int posx,int posy,
            int width,int height
        ){
        
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
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        Block b1 = new Block("p1",1,"p2",2,"out",'+',7,7,7,7);
        b1.executeCalculation();
        System.out.println(b1.outputValue());
        
        Block b2 = new Block("p3",10,"p4",5,"out2",'-',7,7,7,7);
        b2.executeCalculation();
        System.out.println(b2.outputValue());
        
        Block b3 = new Block("p5",3,"p6",4,"out3",'*',7,7,7,7);
        b3.executeCalculation();
        System.out.println(b3.outputValue());
        
        Block b4 = new Block("p7",20,"p8",4,"out4",'/',7,7,7,7);
        b4.executeCalculation();
        System.out.println(b4.outputValue());
    }
}
