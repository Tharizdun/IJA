/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ijaapplication;
import javafx.scene.shape.Line;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Modifikator
 */
public class blok {
    public String name;
    public double posX;
    public double posY;
    public final double width = 60;
    public final double height = 90;
    public Line port1;
    public Line port2;
    public Line portOUT;
    public Group group;
    
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    
    public blok(String name,double x, double y){
        this.name = name;
        this.posX = x;
        this.posY = y;
        
        Rectangle blockMain = new Rectangle(this.width, this.height, Color.BLACK);
        blockMain.setX(this.posX);
        blockMain.setY(this.posY);
        
        Rectangle blockMainInside = new Rectangle(this.width-2, this.height-2, Color.WHITESMOKE);
        blockMainInside.setX(this.posX+1);
        blockMainInside.setY(this.posY+1);
        
        Rectangle port1  = new Rectangle(10,10,Color.BLACK);
        port1.setX(this.posX);
        port1.setY(this.posY+20);
        
        Rectangle port2  = new Rectangle(10,10,Color.BLACK);
        port2.setX(this.posX);
        port2.setY(this.posY+60);
        
        Rectangle port3  = new Rectangle(10,10,Color.BLACK);
        port3.setX(this.posX+width-10);
        port3.setY(this.posY+40);
        
        Text t = new Text(this.posX, this.posY+this.height+10, name);

        this.group = new Group();
        this.group.getChildren().addAll(blockMain,blockMainInside,port1,port2,port3,t);
        
        group.setOnMouseDragged(e ->{
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
             
            ((Group)(e.getSource())).setTranslateX(newTranslateX);
            ((Group)(e.getSource())).setTranslateY(newTranslateY);

            this.posX = ((Rectangle)group.getChildren().get(0)).getX()+ newTranslateX;
            this.posY = ((Rectangle)group.getChildren().get(0)).getY()+ newTranslateY;
            
            // movement of connected lines
            if (this.port1 instanceof Line){
                this.port1.setEndX(this.posX+5);
                this.port1.setEndY(this.posY+25);
            }
            if (this.port2 instanceof Line){
                this.port2.setEndX(this.posX+5);
                this.port2.setEndY(this.posY+65);
            }
            if (this.portOUT instanceof Line){
                this.portOUT.setStartX(this.posX+this.width-5);
                this.portOUT.setStartY(this.posY+45);
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
            
            this.posX = ((Rectangle)group.getChildren().get(0)).getX();
            this.posY = ((Rectangle)group.getChildren().get(0)).getY();
            
            // movement of connected lines
            if (this.port1 instanceof Line){
                this.port1.setEndX(this.posX+5);
                this.port1.setEndY(this.posY+25);
            }
            if (this.port2 instanceof Line){
                this.port2.setEndX(this.posX+5);
                this.port2.setEndY(this.posY+65);
            }
            if (this.portOUT instanceof Line){
                this.portOUT.setStartX(this.posX+this.width-5);
                this.portOUT.setStartY(this.posY+45);
            }
        });
    }
    
    public Line connectBlocks(blok foreignBlock,String foreignPortType,String PortType){
        if (foreignPortType != PortType){
            if (foreignPortType == "out"){
                foreignBlock.portOUT = new Line(foreignBlock.posX+foreignBlock.width-5, 
                                            foreignBlock.posY+45, 
                                            this.posX+5, 
                                            this.posY+25);
                this.port1 = foreignBlock.portOUT;
                return this.port1;
            }
            else {
                foreignBlock.port1 = new Line(this.posX+this.width-5, 
                                            this.posY+45,
                                            foreignBlock.posX+5, 
                                            foreignBlock.posY+25);
                this.portOUT = foreignBlock.port1;
                return this.portOUT;
            }
        }
        return null;
    }
}
