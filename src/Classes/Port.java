package Classes;

/**
 *
 * @author xzedni12
 * 
 * Jednoducha implementace portu
 */
public class Port {
    protected String name;
    protected double value;
    
    public Port(String name){
        this.name = name;
    }
    
    public Port(String name,double value){
        this.name = name;
        this.value = value;
    }
}
