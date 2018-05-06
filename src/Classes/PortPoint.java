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
public class PortPoint extends Port{
    public double x;
    public double y;

    /**
     * Vytvori novy point port
     */
    public PortPoint(){
        Name = "point";
    }

    /**
     * Vytvori novy point port se jmenem
     * @param portType typ portu
     * @param fullName jmeno portu
     */
    public PortPoint(PortType portType, String fullName)
    {
        this();
        PortType = portType;
        this.FullName = fullName;
    }

    /**
     * Vytvori novy point port s hodnoty, typem a jmenem
     * @param portType typ portu
     * @param x hodnota x
     * @param y hodnota y
     * @param fullName jmeno portu
     */
    public PortPoint(PortType portType, double x, double y, String fullName){
        this(portType, fullName);
        this.x = x;
        this.y = y;
        this.FullName = fullName;
    }
}
