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
public class PortDouble extends Port {
    public double value;

    public PortDouble(){
        Name = "double";
    }

    public PortDouble(PortType portType, String fullName)
    {
        this();
        PortType = portType;
        this.FullName = fullName;
    }
    
    public PortDouble(PortType portType, double value, String fullName){
        this(portType, fullName);
        this.value = value;
        this.FullName = fullName;
    }
}
