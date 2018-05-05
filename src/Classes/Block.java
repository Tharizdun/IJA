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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class Block{

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    public String Name;
    public double PosX;
    public double PosY;
    public double Width = 60;
    public double Height = 100;
    public BlockType BlockType;
    public Line port1;
    public Line port2;
    public Line portOUT;
    public Group group;

    public Hashtable<Port, BlockPort> Connections = new Hashtable<>();
    public List Ports = new ArrayList<Port>();

    public Block()
    {
        //super.setFill(Color.rgb(127, 255, 128));
    }

    public Block(String name)
    {
        this();
        Name = name;
    }

    public Block(String name, double x, double y)
    {
        this(name);
  //      super.setHeight(Height);
    //    super.setWidth(Width);
      //  super.setX(x);
        //super.setY(y);
        PosX = x;
        PosY = y;


        Rectangle blockMain = new Rectangle(this.Width, this.Height, Color.BLACK);
        blockMain.setX(this.PosX);
        blockMain.setY(this.PosY);

        Rectangle blockMainInside = new Rectangle(this.Width-2, this.Height-2, Color.WHITESMOKE);
        blockMainInside.setX(this.PosX+1);
        blockMainInside.setY(this.PosY+1);

        Rectangle port1  = new Rectangle(10,10,Color.BLACK);
        port1.setX(this.PosX);
        port1.setY(this.PosY+20);

        Rectangle port2  = new Rectangle(10,10,Color.BLACK);
        port2.setX(this.PosX);
        port2.setY(this.PosY+60);

        Rectangle port3  = new Rectangle(10,10,Color.BLACK);
        port3.setX(this.PosX+Width-10);
        port3.setY(this.PosY+40);

        Text t = new Text(this.PosX, this.PosY+this.Height+10, name);

        this.group = new Group();
        this.group.getChildren().addAll(blockMain,blockMainInside,port1,port2,port3,t);

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
                    if (this.port1 instanceof Line){
                        this.port1.setEndX(this.PosX+5);
                        this.port1.setEndY(this.PosY+25);
                    }
                    if (this.port2 instanceof Line){
                        this.port2.setEndX(this.PosX+5);
                        this.port2.setEndY(this.PosY+65);
                    }
                    if (this.portOUT instanceof Line){
                        this.portOUT.setStartX(this.PosX+this.Width-5);
                        this.portOUT.setStartY(this.PosY+45);
                    }
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
            if (this.port1 instanceof Line){
                this.port1.setEndX(this.PosX+5);
                this.port1.setEndY(this.PosY+25);
            }
            if (this.port2 instanceof Line){
                this.port2.setEndX(this.PosX+5);
                this.port2.setEndY(this.PosY+65);
            }
            if (this.portOUT instanceof Line){
                this.portOUT.setStartX(this.PosX+this.Width-5);
                this.portOUT.setStartY(this.PosY+45);
            }
        });

//        this.setOnMouseDragged(e ->{
  //                  double offsetX = e.getSceneX() - orgSceneX;
    //                double offsetY = e.getSceneY() - orgSceneY;
      //              double newTranslateX = orgTranslateX + offsetX;
        //            double newTranslateY = orgTranslateY + offsetY;

          //          ((Rectangle)(e.getSource())).setTranslateX(newTranslateX);
            //        ((Rectangle)(e.getSource())).setTranslateY(newTranslateY);
         //           System.out.println("souradnice X,Y pro primku start: " + usecka.getStartX()+ " " + usecka.getStartY() );
           //         System.out.println("souradnice X,Y pro block : " + Block1.getX()+ " " + Block1.getY() );
             //       usecka.setStartX(this.getX()+40);
               //     usecka.setStartY(this.getY()+10);
                 //   System.out.println("souradnice X,Y pro primku start: " + usecka.getStartX()+ " " + usecka.getStartY() );

   //             }
//        );

  //      this.setOnMousePressed(e ->{
    //                orgSceneX = e.getSceneX();
      //              orgSceneY = e.getSceneY();
        //            orgTranslateX = ((Rectangle)(e.getSource())).getTranslateX();
          //          orgTranslateY = ((Rectangle)(e.getSource())).getTranslateY();

            //    }
//        );

  //      this.setOnMouseReleased(e -> {
    //        this.setX(this.getX() + this.getTranslateX());
      //      this.setY(this.getY() + this.getTranslateY());
        //    this.setTranslateX(0);
          //  this.setTranslateY(0);
          //  usecka.setStartX(Block1.getX()+40);
            //usecka.setStartY(Block1.getY()+10);
        //});

    }

    public Line connectBlocks(Block foreignBlock,String foreignPortType,String PortType){
        if (foreignPortType != PortType){
            if (foreignPortType == "out"){
                foreignBlock.portOUT = new Line(foreignBlock.PosX+foreignBlock.Width-5,
                        foreignBlock.PosY+45,
                        this.PosX+5,
                        this.PosY+25);
                this.port1 = foreignBlock.portOUT;
                return this.port1;
            }
            else {
                foreignBlock.port1 = new Line(this.PosX+this.Width-5,
                        this.PosY+45,
                        foreignBlock.PosX+5,
                        foreignBlock.PosY+25);
                this.portOUT = foreignBlock.port1;
                return this.portOUT;
            }
        }
        return null;
    }

    public abstract Port GetPort(Port blockPort);

    public void ResizeScheme(double Height, double Width)
    {

    }

    public abstract void DoOperation();
}
