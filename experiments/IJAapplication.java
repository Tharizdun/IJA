/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ijaapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

/**
 *
 * @author Modifikator
 */
public class IJAapplication extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
        
        blok blok1 = new blok("kappa",40,40);
        blok blok2 = new blok("kappa2",120,40);
        blok blok3 = new blok("kappa3",120,80);
       
        AnchorPane root = new AnchorPane();
        
        root.getChildren().addAll(blok1.group);
        root.getChildren().addAll(blok2.group);
        root.getChildren().addAll(blok3.group);
        root.getChildren().add(blok1.connectBlocks(blok2, "in", "out"));
        root.getChildren().add(blok2.connectBlocks(blok3, "in", "out"));
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
