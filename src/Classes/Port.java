package Classes;


import javafx.scene.shape.Line;

import java.io.Serializable;

public class Port implements Serializable{
    public String Name;
    public PortType PortType;
    public Line connection;
    public String FullName;
}
