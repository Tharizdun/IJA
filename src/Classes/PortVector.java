/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Modifikator
 */
public class PortVector extends Port {
    public double x;
    public double y; 
    
    public PortVector(){
         Name = "vector2";
    }

    public PortVector(PortType portType, String fullName)
    {
        this();
        PortType = portType;
        this.FullName = fullName;
    }
    
    public PortVector(PortType portType, double x, double y, String fullName){
        this(portType, fullName);
        this.x = x;
        this.y = y;
    }
}


