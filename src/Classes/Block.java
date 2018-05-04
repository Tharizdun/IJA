/**
 *
 * @author Jan Rajnoha, xrajno09
 */

package Classes;

import javafx.scene.shape.Rectangle;
import java.util.Hashtable;

public abstract class Block extends Rectangle{

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    public String Name;
    public double PosX;
    public double PosY;
    public double Width = 60;
    public double Height = 100;

    public Hashtable<Integer, Hashtable<Port, Block>> Connections = new Hashtable<>();

    public Block()
    {

    }

    public Block(String name)
    {
        this();
        Name = name;
    }

    public Block(String name, double x, double y)
    {
        this(name);
        super.setHeight(Height);
        super.setWidth(Width);
        super.setX(x);
        super.setY(y);
        PosX = x;
        PosY = y;

        this.setOnMouseDragged(e ->{
                    double offsetX = e.getSceneX() - orgSceneX;
                    double offsetY = e.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Rectangle)(e.getSource())).setTranslateX(newTranslateX);
                    ((Rectangle)(e.getSource())).setTranslateY(newTranslateY);
         //           System.out.println("souradnice X,Y pro primku start: " + usecka.getStartX()+ " " + usecka.getStartY() );
           //         System.out.println("souradnice X,Y pro block : " + Block1.getX()+ " " + Block1.getY() );
             //       usecka.setStartX(this.getX()+40);
               //     usecka.setStartY(this.getY()+10);
                 //   System.out.println("souradnice X,Y pro primku start: " + usecka.getStartX()+ " " + usecka.getStartY() );

                }
        );

        this.setOnMousePressed(e ->{
                    orgSceneX = e.getSceneX();
                    orgSceneY = e.getSceneY();
                    orgTranslateX = ((Rectangle)(e.getSource())).getTranslateX();
                    orgTranslateY = ((Rectangle)(e.getSource())).getTranslateY();

                }
        );

        this.setOnMouseReleased(e -> {
            this.setX(this.getX() + this.getTranslateX());
            this.setY(this.getY() + this.getTranslateY());
            this.setTranslateX(0);
            this.setTranslateY(0);
          //  usecka.setStartX(Block1.getX()+40);
            //usecka.setStartY(Block1.getY()+10);
        });

    }

    public void ResizeScheme(double Height, double Width)
    {

    }

    public abstract void DoOperation();
}
