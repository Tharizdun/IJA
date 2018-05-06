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

    /**
     * Vytvori novy double port
     */
    public PortDouble(){
        Name = "double";
    }

    /**
     * Vytvori novy double port se jmenem
     * @param portType typ portu
     * @param fullName jmeno portu
     */
    public PortDouble(PortType portType, String fullName)
    {
        this();
        PortType = portType;
        this.FullName = fullName;
    }

    /**
     * Vyvori novy double port s hodnotou a jmenem
     * @param portType typ portu
     * @param value hodnota portu
     * @param fullName jmeno portu
     */
    public PortDouble(PortType portType, double value, String fullName){
        this(portType, fullName);
        this.value = value;
        this.FullName = fullName;
    }
}
