/**
 *
 * @author Jan Rajnoha, xrajno09
 */

package Classes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import javafx.scene.layout.AnchorPane;

public class Block implements Serializable{

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    public String Name;
    public double PosX;
    public double PosY;
    public double Width = 60;
    public double Height = 90;
    public BlockType BlockType;
    public Group group;

    public Hashtable<Port, BlockPort> Connections = new Hashtable<>();
    public Collection<String> StringConnections = new ArrayList<>();
    public List<Port> Ports = new ArrayList<>();

    /**
     * Novy blok
     */
    public Block()
    {
        //super.setFill(Color.rgb(127, 255, 128));
    }

    /**
     * Novy blok s nazvem
     * @param name nazev bloku
     */
    public Block(String name)
    {
        this();
        Name = name;
    }

    /**
     * Novy blok s nazvem a pozici a typem
     * @param name nazev bloku
     * @param x pozice X
     * @param y pozice Y
     * @param blockType typ bloku
     */
    public Block(String name, double x, double y, BlockType blockType)
    {
        this(name);

  //      super.setHeight(Height);
    //    super.setWidth(Width);
      //  super.setX(x);
        //super.setY(y);
        this.PosX = x;
        this.PosY = y;
        this.BlockType = blockType;


        Rectangle blockMain = new Rectangle(this.Width, this.Height, Color.BLACK);
        blockMain.setX(this.PosX);
        blockMain.setY(this.PosY);

        Rectangle blockMainInside = new Rectangle(this.Width-2, this.Height-2, Color.WHITESMOKE);
        blockMainInside.setX(this.PosX+1);
        blockMainInside.setY(this.PosY+1);

        this.group = new Group();
        Text t = new Text(this.PosX, this.PosY+this.Height+13, name);
        
        if (this.BlockType == BlockType.Start){
            Rectangle port3  = new Rectangle(10,10,Color.BLACK);
            port3.setX(this.PosX+Width-10);
            port3.setY(this.PosY+40);
            
            this.group.getChildren().addAll(blockMain,blockMainInside,port3,t);
        }
        else if (this.BlockType == BlockType.End){
            Rectangle port1  = new Rectangle(10,10,Color.BLACK);
            port1.setX(this.PosX);
            port1.setY(this.PosY+40);
            
            this.group.getChildren().addAll(blockMain,blockMainInside,port1,t);
        }
        else {
            Rectangle port1  = new Rectangle(10,10,Color.BLACK);
            port1.setX(this.PosX);
            port1.setY(this.PosY+20);

            Rectangle port2  = new Rectangle(10,10,Color.BLACK);
            port2.setX(this.PosX);
            port2.setY(this.PosY+60);

            Rectangle port3  = new Rectangle(10,10,Color.BLACK);
            port3.setX(this.PosX+Width-10);
            port3.setY(this.PosY+40);
            
            this.group.getChildren().addAll(blockMain,blockMainInside,port1,port2,port3,t);
        }
        
        group.setOnMouseDragged(e ->{
                    double offsetX = e.getSceneX() - orgSceneX;
                    double offsetY = e.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Group)(e.getSource())).setTranslateX(newTranslateX);
                    ((Group)(e.getSource())).setTranslateY(newTranslateY);

                    this.PosX = ((Rectangle)group.getChildren().get(0)).getX()+ newTranslateX;
                    this.PosY = ((Rectangle)group.getChildren().get(0)).getY()+ newTranslateY;

                    // movement of connected lines
                    this.refresh();
                    /*
                    if (((Port)this.Ports.get(0)).connection instanceof Line){
                        ((Port)this.Ports.get(0)).connection.setEndX(this.PosX+5);
                        ((Port)this.Ports.get(0)).connection.setEndY(this.PosY+25);
                    }
                    if (((Port)this.Ports.get(1)).connection instanceof Line){
                        ((Port)this.Ports.get(1)).connection.setEndX(this.PosX+5);
                        ((Port)this.Ports.get(1)).connection.setEndY(this.PosY+65);
                    }
                    if (((Port)this.Ports.get(2)).connection instanceof Line){
                        ((Port)this.Ports.get(2)).connection.setStartX(this.PosX+this.Width-5);
                        ((Port)this.Ports.get(2)).connection.setStartY(this.PosY+45);
                    }
                    */
                }
        );

        group.setOnMousePressed(e ->{
                    orgSceneX = e.getSceneX();
                    orgSceneY = e.getSceneY();
                    orgTranslateX = ((Group)(e.getSource())).getTranslateX();
                    orgTranslateY = ((Group)(e.getSource())).getTranslateY();
                }
        );
        group.setOnMouseReleased(e -> {
            group.getChildren().forEach(item -> {
                if (item instanceof Rectangle){
                    ((Rectangle)item).setX(((Rectangle)item).getX() + group.getTranslateX());
                    ((Rectangle)item).setY(((Rectangle)item).getY() + group.getTranslateY());
                }
                else if (item instanceof Text){
                    ((Text)item).setX(((Text)item).getX() + group.getTranslateX());
                    ((Text)item).setY(((Text)item).getY() + group.getTranslateY());
                }
            });
            group.setTranslateX(0);
            group.setTranslateY(0);

            this.PosX = ((Rectangle)group.getChildren().get(0)).getX();
            this.PosY = ((Rectangle)group.getChildren().get(0)).getY();

            // movement of connected lines
            this.refresh();
            /*
            if (((Port)this.Ports.get(0)).connection instanceof Line){
                ((Port)this.Ports.get(0)).connection.setEndX(this.PosX+5);
                ((Port)this.Ports.get(0)).connection.setEndY(this.PosY+25);
            }
            if (((Port)this.Ports.get(1)).connection instanceof Line){
                ((Port)this.Ports.get(1)).connection.setEndX(this.PosX+5);
                ((Port)this.Ports.get(1)).connection.setEndY(this.PosY+65);
            }
            if (((Port)this.Ports.get(2)).connection instanceof Line){
                ((Port)this.Ports.get(2)).connection.setStartX(this.PosX+this.Width-5);
                ((Port)this.Ports.get(2)).connection.setStartY(this.PosY+45);
            }
            */
        });

    }
    /*
    public void updateConnections2(AnchorPane design){
        this.Connections.forEach((k,v) ->{
        if (v.Port.PortType != k.PortType){
            if (v.Port.PortType == PortType.Out){
                v.Port.connection = new Line(v.Block.PosX+v.Block.Width-5,
                                            v.Block.PosY+45,
                                            this.PosX+5,
                                            this.PosY+25);
                k.connection = v.Port.connection;
                design.getChildren().add(k.connection);
            }
            else {
                v.Port.connection = new Line(this.PosX+this.Width-5,
                                            this.PosY+45,
                                            v.Block.PosX+5,
                                            v.Block.PosY+25);
                k.connection = v.Port.connection;
                design.getChildren().add(k.connection);     
            }
        }
        });
        
    }
    */

    /**
     * Prekresli linku
     * @param design zde se to vykresli
     */
    public void updateConnections(AnchorPane design){
        this.Connections.forEach((k,v) ->{
        if (v.Port.PortType != k.PortType){
            v.Port.connection = new Line();
            k.connection = v.Port.connection;
            design.getChildren().add(k.connection);
        }
        this.refresh();
        v.Block.refresh();
        });   
    }

    /**
     * Obnovi pozice linky
     */
    public void refresh(){
        if (this.BlockType == BlockType.Start){
            if (this.Ports.size() > 0)
            if (((Port)this.Ports.get(0)).connection instanceof Line){
                ((Port)this.Ports.get(0)).connection.setStartX(this.PosX+this.Width-5);
                ((Port)this.Ports.get(0)).connection.setStartY(this.PosY+45);
            }
        }
        else if (this.BlockType == BlockType.End){
            if (this.Ports.size() > 0)
            if (((Port)this.Ports.get(0)).connection instanceof Line){
                ((Port)this.Ports.get(0)).connection.setEndX(this.PosX+5);
                ((Port)this.Ports.get(0)).connection.setEndY(this.PosY+45);
            }
        }
        else {
            if (this.Ports.size() > 0)
            if (((Port)this.Ports.get(0)).connection instanceof Line){
                ((Port)this.Ports.get(0)).connection.setEndX(this.PosX+5);
                ((Port)this.Ports.get(0)).connection.setEndY(this.PosY+25);
            }
            if (this.Ports.size() > 1)
            if (((Port)this.Ports.get(1)).connection instanceof Line){
                ((Port)this.Ports.get(1)).connection.setEndX(this.PosX+5);
                ((Port)this.Ports.get(1)).connection.setEndY(this.PosY+65);
            }
            if (this.Ports.size() > 2)
            if (((Port)this.Ports.get(2)).connection instanceof Line){
                ((Port)this.Ports.get(2)).connection.setStartX(this.PosX+this.Width-5);
                ((Port)this.Ports.get(2)).connection.setStartY(this.PosY+45);
            }
        }
    }

    /**
     * Vrati port
     * @param blockPort Port
     * @return Port
     */
    public Port GetPort(Port blockPort)
    {return new Port();}

    /**
     * Provede operaci nad blokem
     */
    public void DoOperation()
    {}

    /**
     * Znovu prida bloky
     */
    public void ReAddPorts()
    {}

    /**
     * Zobrazi hodnoty nad porty
     */
    public void AddPortFunctionality(){
        
        if (this.BlockType == BlockType.Start){
            Rectangle port3=((Rectangle)this.group.getChildren().get(2));

            Text show = new Text(port3.getX()+15, port3.getY()+25,"");
            port3.setOnMouseEntered(e -> {
                show.setText(Double.toString(((PortDouble)this.Ports.get(0)).value));
                this.group.getChildren().add(show);
            });
            port3.setOnMouseExited(e -> {
                this.group.getChildren().remove(show);
            });
        }
        else if (this.BlockType == BlockType.End){
            Rectangle port3=((Rectangle)this.group.getChildren().get(2));

            Text show = new Text(port3.getX()-15, port3.getY()-25,"");
            port3.setOnMouseEntered(e -> {
                show.setText(Double.toString(((PortDouble)this.Ports.get(0)).value));
                this.group.getChildren().add(show);
            });
            port3.setOnMouseExited(e -> {
                this.group.getChildren().remove(show);
            });
        }
        else {}
        
        
        
    }
}
