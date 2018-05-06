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

    /**
     * Vytvori novy vector port
     */
    public PortVector(){
         Name = "vector2";
    }

    /**
     * Vytvori novy vector port se jmenem
     * @param portType typ portu
     * @param fullName jmeno portu
     */
    public PortVector(PortType portType, String fullName)
    {
        this();
        PortType = portType;
        this.FullName = fullName;
    }

    /**
     * Vytvori novy vector port s hodnoty, typem a jmenem
     * @param portType typ portu
     * @param x hodnota x
     * @param y hodnota y
     * @param fullName jmeno portu
     */
    public PortVector(PortType portType, double x, double y, String fullName){
        this(portType, fullName);
        this.x = x;
        this.y = y;
    }
}


