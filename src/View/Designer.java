package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Classes.*;

import javax.xml.transform.Result;
import java.io.File;

public class Designer extends Application {
    double InvokedX, InvokedY;
    Button NewButton;
    Button LoadButton;
    Button SaveButton;
    Button RunButton;
    Button TraceButton;
    Label ResultText;

    ScrollPane designScroll;

    Scheme currentScheme;

    Stage window;
    AnchorPane design;

    boolean NeedSave = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;
        window.setTitle("Designer");
        window.setOnCloseRequest(e -> {
            e.consume();
            CloseRequested();
        });

        /////
        Line usecka = new Line();

        usecka.setStartX(80.0);
        usecka.setStartY(50.0);
        usecka.setEndX(100.0);
        usecka.setEndY(50.0);
/////
        NewButton = new Button("New");
        NewButton.setOnAction(e -> CreateNewScheme());

        LoadButton = new Button("Load");
        LoadButton.setOnAction(e -> LoadScheme());

        SaveButton = new Button("Save");
        SaveButton.setOnAction(e -> SaveScheme());

        RunButton = new Button("Run");
        RunButton.setOnAction(e -> RunScheme());

        TraceButton = new Button("Trace");
        TraceButton.setOnAction(e -> TraceScheme());

        ResultText = new Label("");
        ResultText.setPadding(new Insets(5));

        HBox TopMenu = new HBox(5);
        TopMenu.getChildren().addAll(NewButton, LoadButton, SaveButton, RunButton, TraceButton, ResultText);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(TopMenu);

        ContextMenu context = new ContextMenu();

        MenuItem mItem = new MenuItem("New Add block");
        mItem.setOnAction(e ->
        {
            AddBlockSpec newAddBlock = new AddBlockSpec();
            newAddBlock.CurrentScheme = currentScheme;
            newAddBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Add) + 1;

            if (newAddBlock.Display()) {
                AddBlock addBlock = new AddBlock(newAddBlock.BlockName, InvokedX, InvokedY);

                BlockPort bp;

                if (newAddBlock.In1C != null) {
                    addBlock.Connections.put(addBlock.PortIN1, newAddBlock.In1C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newAddBlock.In1C.Block.Name;
                    sbp.PortName = newAddBlock.In1C.Port.FullName;
                    addBlock.StringConnections.add(addBlock.PortIN1.FullName + ";" + newAddBlock.In1C.Block.Name + ";" + newAddBlock.In1C.Port.FullName);//put(addBlock.PortIN1.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = addBlock;
                    bp.Port = addBlock.PortIN1;
                    newAddBlock.In1C.Block.Connections.put(newAddBlock.In1C.Block.GetPort(newAddBlock.In1C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = addBlock.Name;
                    sbp.PortName = addBlock.PortIN1.FullName;
                    newAddBlock.In1C.Block.StringConnections.add(newAddBlock.In1C.Block.GetPort(newAddBlock.In1C.Port).FullName + ";" + addBlock.Name + ";" + addBlock.PortIN1.FullName);//put(newAddBlock.In1C.Block.GetPort(newAddBlock.In1C.Port).FullName, sbp);
                }

                if (newAddBlock.In2C != null){
                    addBlock.Connections.put(addBlock.PortIN2, newAddBlock.In2C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newAddBlock.In2C.Block.Name;
                    sbp.PortName = newAddBlock.In2C.Port.FullName;
                    addBlock.StringConnections.add(addBlock.PortIN2.FullName + ";" + newAddBlock.In2C.Block.Name + ";" + newAddBlock.In2C.Port.FullName);//put(addBlock.PortIN2.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = addBlock;
                    bp.Port = addBlock.PortIN2;
                    newAddBlock.In2C.Block.Connections.put(newAddBlock.In2C.Block.GetPort(newAddBlock.In2C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = addBlock.Name;
                    sbp.PortName = addBlock.PortIN2.FullName;
                    newAddBlock.In2C.Block.StringConnections.add(newAddBlock.In2C.Block.GetPort(newAddBlock.In2C.Port).FullName + ";" + addBlock.Name + ";" + addBlock.PortIN2.FullName);//put(newAddBlock.In2C.Block.GetPort(newAddBlock.In2C.Port).FullName, sbp);
                }

                if (newAddBlock.OutC != null){
                    addBlock.Connections.put(addBlock.PortOUT, newAddBlock.OutC);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newAddBlock.OutC.Block.Name;
                    sbp.PortName = newAddBlock.OutC.Port.FullName;
                    addBlock.StringConnections.add(addBlock.PortOUT.FullName + ";" + newAddBlock.OutC.Block.Name + ";" + newAddBlock.OutC.Port.FullName);//put(addBlock.PortOUT.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = addBlock;
                    bp.Port = addBlock.PortOUT;
                    newAddBlock.OutC.Block.Connections.put(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = addBlock.Name;
                    sbp.PortName = addBlock.PortOUT.FullName;
                    newAddBlock.OutC.Block.StringConnections.add(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port).FullName + ";" + addBlock.Name + ";" + addBlock.PortOUT.FullName);//put(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port).FullName, sbp);
                }

                addBlock.updateConnections(design);
                design.getChildren().add(addBlock.group);
                currentScheme.AddBlock(addBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Sub block");
        mItem.setOnAction(e ->
        {
            SubBlockSpec newSubBlock = new SubBlockSpec();
            newSubBlock.CurrentScheme = currentScheme;
            newSubBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Sub) + 1;

            if (newSubBlock.Display()) {
                SubBlock subBlock = new SubBlock(newSubBlock.BlockName, InvokedX, InvokedY);

                BlockPort bp;

                if (newSubBlock.In1C != null) {
                    subBlock.Connections.put(subBlock.PortIN1, newSubBlock.In1C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newSubBlock.In1C.Block.Name;
                    sbp.PortName = newSubBlock.In1C.Port.FullName;
                    subBlock.StringConnections.add(subBlock.PortIN1.FullName + ";" + newSubBlock.In1C.Block.Name + ";" + newSubBlock.In1C.Port.FullName);//put(addBlock.PortIN1.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = subBlock;
                    bp.Port = subBlock.PortIN1;
                    newSubBlock.In1C.Block.Connections.put(newSubBlock.In1C.Block.GetPort(newSubBlock.In1C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = subBlock.Name;
                    sbp.PortName = subBlock.PortIN1.FullName;
                    newSubBlock.In1C.Block.StringConnections.add(newSubBlock.In1C.Block.GetPort(newSubBlock.In1C.Port).FullName + ";" + subBlock.Name + ";" + subBlock.PortIN1.FullName);//put(newAddBlock.In1C.Block.GetPort(newAddBlock.In1C.Port).FullName, sbp);
                }

                if (newSubBlock.In2C != null){
                    subBlock.Connections.put(subBlock.PortIN2, newSubBlock.In2C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newSubBlock.In2C.Block.Name;
                    sbp.PortName = newSubBlock.In2C.Port.FullName;
                    subBlock.StringConnections.add(subBlock.PortIN2.FullName + ";" + newSubBlock.In2C.Block.Name + ";" + newSubBlock.In2C.Port.FullName);//put(addBlock.PortIN2.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = subBlock;
                    bp.Port = subBlock.PortIN2;
                    newSubBlock.In2C.Block.Connections.put(newSubBlock.In2C.Block.GetPort(newSubBlock.In2C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = subBlock.Name;
                    sbp.PortName = subBlock.PortIN2.FullName;
                    newSubBlock.In2C.Block.StringConnections.add(newSubBlock.In2C.Block.GetPort(newSubBlock.In2C.Port).FullName + ";" + subBlock.Name + ";" + subBlock.PortIN2.FullName);//put(newAddBlock.In2C.Block.GetPort(newAddBlock.In2C.Port).FullName, sbp);
                }

                if (newSubBlock.OutC != null){
                    subBlock.Connections.put(subBlock.PortOUT, newSubBlock.OutC);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newSubBlock.OutC.Block.Name;
                    sbp.PortName = newSubBlock.OutC.Port.FullName;
                    subBlock.StringConnections.add(subBlock.PortOUT.FullName + ";" + newSubBlock.OutC.Block.Name + ";" + newSubBlock.OutC.Port.FullName);//put(addBlock.PortOUT.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = subBlock;
                    bp.Port = subBlock.PortOUT;
                    newSubBlock.OutC.Block.Connections.put(newSubBlock.OutC.Block.GetPort(newSubBlock.OutC.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = subBlock.Name;
                    sbp.PortName = subBlock.PortOUT.FullName;
                    newSubBlock.OutC.Block.StringConnections.add(newSubBlock.OutC.Block.GetPort(newSubBlock.OutC.Port).FullName + ";" + subBlock.Name + ";" + subBlock.PortOUT.FullName);//put(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port).FullName, sbp);
                }

                subBlock.updateConnections(design);
                design.getChildren().add(subBlock.group);
                currentScheme.AddBlock(subBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Mul block");
        mItem.setOnAction(e ->
        {
            MulBlockSpec newMulBlock = new MulBlockSpec();
            newMulBlock.CurrentScheme = currentScheme;
            newMulBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Add) + 1;

            if (newMulBlock.Display()) {
                MulBlock mulBlock = new MulBlock(newMulBlock.BlockName, InvokedX, InvokedY);

                BlockPort bp;

                if (newMulBlock.In1C != null) {
                    mulBlock.Connections.put(mulBlock.PortIN1, newMulBlock.In1C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newMulBlock.In1C.Block.Name;
                    sbp.PortName = newMulBlock.In1C.Port.FullName;
                    mulBlock.StringConnections.add(mulBlock.PortIN1.FullName + ";" + newMulBlock.In1C.Block.Name + ";" + newMulBlock.In1C.Port.FullName);//put(addBlock.PortIN1.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = mulBlock;
                    bp.Port = mulBlock.PortIN1;
                    newMulBlock.In1C.Block.Connections.put(newMulBlock.In1C.Block.GetPort(newMulBlock.In1C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = mulBlock.Name;
                    sbp.PortName = mulBlock.PortIN1.FullName;
                    newMulBlock.In1C.Block.StringConnections.add(newMulBlock.In1C.Block.GetPort(newMulBlock.In1C.Port).FullName + ";" + mulBlock.Name + ";" + mulBlock.PortIN1.FullName);//put(newAddBlock.In1C.Block.GetPort(newAddBlock.In1C.Port).FullName, sbp);
                }

                if (newMulBlock.In2C != null){
                    mulBlock.Connections.put(mulBlock.PortIN2, newMulBlock.In2C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newMulBlock.In2C.Block.Name;
                    sbp.PortName = newMulBlock.In2C.Port.FullName;
                    mulBlock.StringConnections.add(mulBlock.PortIN2.FullName + ";" + newMulBlock.In2C.Block.Name + ";" + newMulBlock.In2C.Port.FullName);//put(addBlock.PortIN2.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = mulBlock;
                    bp.Port = mulBlock.PortIN2;
                    newMulBlock.In2C.Block.Connections.put(newMulBlock.In2C.Block.GetPort(newMulBlock.In2C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = mulBlock.Name;
                    sbp.PortName = mulBlock.PortIN2.FullName;
                    newMulBlock.In2C.Block.StringConnections.add(newMulBlock.In2C.Block.GetPort(newMulBlock.In2C.Port).FullName + ";" + mulBlock.Name + ";" + mulBlock.PortIN2.FullName);//put(newAddBlock.In2C.Block.GetPort(newAddBlock.In2C.Port).FullName, sbp);
                }

                if (newMulBlock.OutC != null){
                    mulBlock.Connections.put(mulBlock.PortOUT, newMulBlock.OutC);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newMulBlock.OutC.Block.Name;
                    sbp.PortName = newMulBlock.OutC.Port.FullName;
                    mulBlock.StringConnections.add(mulBlock.PortOUT.FullName + ";" + newMulBlock.OutC.Block.Name + ";" + newMulBlock.OutC.Port.FullName);//put(addBlock.PortOUT.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = mulBlock;
                    bp.Port = mulBlock.PortOUT;
                    newMulBlock.OutC.Block.Connections.put(newMulBlock.OutC.Block.GetPort(newMulBlock.OutC.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = mulBlock.Name;
                    sbp.PortName = mulBlock.PortOUT.FullName;
                    newMulBlock.OutC.Block.StringConnections.add(newMulBlock.OutC.Block.GetPort(newMulBlock.OutC.Port).FullName + ";" + mulBlock.Name + ";" + mulBlock.PortOUT.FullName);//put(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port).FullName, sbp);
                }

                mulBlock.updateConnections(design);
                design.getChildren().add(mulBlock.group);
                currentScheme.AddBlock(mulBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Div block");
        mItem.setOnAction(e ->
        {
            DivBlockSpec newDivBlock = new DivBlockSpec();
            newDivBlock.CurrentScheme = currentScheme;
            newDivBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Div) + 1;

            if (newDivBlock.Display()) {
                DivBlock divBlock = new DivBlock(newDivBlock.BlockName, InvokedX, InvokedY);
                BlockPort bp;

                if (newDivBlock.In1C != null) {
                    divBlock.Connections.put(divBlock.PortIN1, newDivBlock.In1C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newDivBlock.In1C.Block.Name;
                    sbp.PortName = newDivBlock.In1C.Port.FullName;
                    divBlock.StringConnections.add(divBlock.PortIN1.FullName + ";" + newDivBlock.In1C.Block.Name + ";" + newDivBlock.In1C.Port.FullName);//put(addBlock.PortIN1.FullName, sbp);


                    bp = new BlockPort();
                    bp.Block = divBlock;
                    bp.Port = divBlock.PortIN1;
                    newDivBlock.In1C.Block.Connections.put(newDivBlock.In1C.Block.GetPort(newDivBlock.In1C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = divBlock.Name;
                    sbp.PortName = divBlock.PortIN1.FullName;
                    newDivBlock.In1C.Block.StringConnections.add(newDivBlock.In1C.Block.GetPort(newDivBlock.In1C.Port).FullName + ";" + divBlock.Name + ";" + divBlock.PortIN1.FullName);//put(newAddBlock.In1C.Block.GetPort(newAddBlock.In1C.Port).FullName, sbp);
                }

                if (newDivBlock.In2C != null){
                    divBlock.Connections.put(divBlock.PortIN2, newDivBlock.In2C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newDivBlock.In2C.Block.Name;
                    sbp.PortName = newDivBlock.In2C.Port.FullName;
                    divBlock.StringConnections.add(divBlock.PortIN2.FullName + ";" + newDivBlock.In2C.Block.Name + ";" + newDivBlock.In2C.Port.FullName);//put(addBlock.PortIN2.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = divBlock;
                    bp.Port = divBlock.PortIN2;
                    newDivBlock.In2C.Block.Connections.put(newDivBlock.In2C.Block.GetPort(newDivBlock.In2C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = divBlock.Name;
                    sbp.PortName = divBlock.PortIN2.FullName;
                    newDivBlock.In2C.Block.StringConnections.add(newDivBlock.In2C.Block.GetPort(newDivBlock.In2C.Port).FullName + ";" + divBlock.Name + ";" + divBlock.PortIN2.FullName);//put(newAddBlock.In2C.Block.GetPort(newAddBlock.In2C.Port).FullName, sbp);
                }

                if (newDivBlock.OutC != null){
                    divBlock.Connections.put(divBlock.PortOUT, newDivBlock.OutC);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newDivBlock.OutC.Block.Name;
                    sbp.PortName = newDivBlock.OutC.Port.FullName;
                    divBlock.StringConnections.add(divBlock.PortOUT.FullName + ";" + newDivBlock.OutC.Block.Name + ";" + newDivBlock.OutC.Port.FullName);//put(addBlock.PortOUT.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = divBlock;
                    bp.Port = divBlock.PortOUT;
                    newDivBlock.OutC.Block.Connections.put(newDivBlock.OutC.Block.GetPort(newDivBlock.OutC.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = divBlock.Name;
                    sbp.PortName = divBlock.PortOUT.FullName;
                    newDivBlock.OutC.Block.StringConnections.add(newDivBlock.OutC.Block.GetPort(newDivBlock.OutC.Port).FullName + ";" + divBlock.Name + ";" + divBlock.PortOUT.FullName);//put(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port).FullName, sbp);
                }

                divBlock.updateConnections(design);
                design.getChildren().add(divBlock.group);
                currentScheme.AddBlock(divBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Point block");
        mItem.setOnAction(e ->
        {
            PointBlockSpec newPointBlock = new PointBlockSpec();
            newPointBlock.CurrentScheme = currentScheme;
            newPointBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Point) + 1;

            if (newPointBlock.Display()) {
                PointBlock pointBlock = new PointBlock(newPointBlock.BlockName, InvokedX, InvokedY);

                BlockPort bp;

                if (newPointBlock.In1C != null) {
                    pointBlock.Connections.put(pointBlock.PortIN1, newPointBlock.In1C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newPointBlock.In1C.Block.Name;
                    sbp.PortName = newPointBlock.In1C.Port.FullName;
                    pointBlock.StringConnections.add(pointBlock.PortIN1.FullName + ";" + newPointBlock.In1C.Block.Name + ";" + newPointBlock.In1C.Port.FullName);//put(addBlock.PortIN1.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = pointBlock;
                    bp.Port = pointBlock.PortIN1;
                    newPointBlock.In1C.Block.Connections.put(newPointBlock.In1C.Block.GetPort(newPointBlock.In1C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = pointBlock.Name;
                    sbp.PortName = pointBlock.PortIN1.FullName;
                    newPointBlock.In1C.Block.StringConnections.add(newPointBlock.In1C.Block.GetPort(newPointBlock.In1C.Port).FullName + ";" + pointBlock.Name + ";" + pointBlock.PortIN1.FullName);//put(newAddBlock.In1C.Block.GetPort(newAddBlock.In1C.Port).FullName, sbp);

                }

                if (newPointBlock.In2C != null){
                    pointBlock.Connections.put(pointBlock.PortIN2, newPointBlock.In2C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newPointBlock.In2C.Block.Name;
                    sbp.PortName = newPointBlock.In2C.Port.FullName;
                    pointBlock.StringConnections.add(pointBlock.PortIN2.FullName + ";" + newPointBlock.In2C.Block.Name + ";" + newPointBlock.In2C.Port.FullName);//put(addBlock.PortIN2.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = pointBlock;
                    bp.Port = pointBlock.PortIN2;
                    newPointBlock.In2C.Block.Connections.put(newPointBlock.In2C.Block.GetPort(newPointBlock.In2C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = pointBlock.Name;
                    sbp.PortName = pointBlock.PortIN2.FullName;
                    newPointBlock.In2C.Block.StringConnections.add(newPointBlock.In2C.Block.GetPort(newPointBlock.In2C.Port).FullName + ";" + pointBlock.Name + ";" + pointBlock.PortIN2.FullName);//put(newAddBlock.In2C.Block.GetPort(newAddBlock.In2C.Port).FullName, sbp);
                }

                if (newPointBlock.OutC != null){
                    pointBlock.Connections.put(pointBlock.PortOUT, newPointBlock.OutC);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newPointBlock.OutC.Block.Name;
                    sbp.PortName = newPointBlock.OutC.Port.FullName;
                    pointBlock.StringConnections.add(pointBlock.PortOUT.FullName + ";" + newPointBlock.OutC.Block.Name + ";" + newPointBlock.OutC.Port.FullName);//put(addBlock.PortOUT.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = pointBlock;
                    bp.Port = pointBlock.PortOUT;
                    newPointBlock.OutC.Block.Connections.put(newPointBlock.OutC.Block.GetPort(newPointBlock.OutC.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = pointBlock.Name;
                    sbp.PortName = pointBlock.PortOUT.FullName;
                    newPointBlock.OutC.Block.StringConnections.add(newPointBlock.OutC.Block.GetPort(newPointBlock.OutC.Port).FullName + ";" + pointBlock.Name + ";" + pointBlock.PortOUT.FullName);//put(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port).FullName, sbp);
                }

                pointBlock.updateConnections(design);
                design.getChildren().add(pointBlock.group);
                currentScheme.AddBlock(pointBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Distance block");
        mItem.setOnAction(e ->
        {
            DistanceBlockSpec newDistanceBlock = new DistanceBlockSpec();
            newDistanceBlock.CurrentScheme = currentScheme;
            newDistanceBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Distance) + 1;

            if (newDistanceBlock.Display()) {
                DistanceBlock distanceBlock = new DistanceBlock(newDistanceBlock.BlockName, InvokedX, InvokedY);

                BlockPort bp;

                if (newDistanceBlock.In1C != null) {
                    distanceBlock.Connections.put(distanceBlock.PortIN1, newDistanceBlock.In1C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newDistanceBlock.In1C.Block.Name;
                    sbp.PortName = newDistanceBlock.In1C.Port.FullName;
                    distanceBlock.StringConnections.add(distanceBlock.PortIN1.FullName + ";" + newDistanceBlock.In1C.Block.Name + ";" + newDistanceBlock.In1C.Port.FullName);//put(addBlock.PortIN1.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = distanceBlock;
                    bp.Port = distanceBlock.PortIN1;
                    newDistanceBlock.In1C.Block.Connections.put(newDistanceBlock.In1C.Block.GetPort(newDistanceBlock.In1C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = distanceBlock.Name;
                    sbp.PortName = distanceBlock.PortIN1.FullName;
                    newDistanceBlock.In1C.Block.StringConnections.add(newDistanceBlock.In1C.Block.GetPort(newDistanceBlock.In1C.Port).FullName + ";" + distanceBlock.Name + ";" + distanceBlock.PortIN1.FullName);//put(newAddBlock.In1C.Block.GetPort(newAddBlock.In1C.Port).FullName, sbp);

                }

                if (newDistanceBlock.In2C != null){
                    distanceBlock.Connections.put(distanceBlock.PortIN2, newDistanceBlock.In2C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newDistanceBlock.In2C.Block.Name;
                    sbp.PortName = newDistanceBlock.In2C.Port.FullName;
                    distanceBlock.StringConnections.add(distanceBlock.PortIN2.FullName + ";" + newDistanceBlock.In2C.Block.Name + ";" + newDistanceBlock.In2C.Port.FullName);//put(addBlock.PortIN2.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = distanceBlock;
                    bp.Port = distanceBlock.PortIN2;
                    newDistanceBlock.In2C.Block.Connections.put(newDistanceBlock.In2C.Block.GetPort(newDistanceBlock.In2C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = distanceBlock.Name;
                    sbp.PortName = distanceBlock.PortIN2.FullName;
                    newDistanceBlock.In2C.Block.StringConnections.add(newDistanceBlock.In2C.Block.GetPort(newDistanceBlock.In2C.Port).FullName + ";" + distanceBlock.Name + ";" + distanceBlock.PortIN2.FullName);//put(newAddBlock.In2C.Block.GetPort(newAddBlock.In2C.Port).FullName, sbp);
                }

                if (newDistanceBlock.OutC != null){
                    distanceBlock.Connections.put(distanceBlock.PortOUT, newDistanceBlock.OutC);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newDistanceBlock.OutC.Block.Name;
                    sbp.PortName = newDistanceBlock.OutC.Port.FullName;
                    distanceBlock.StringConnections.add(distanceBlock.PortOUT.FullName + ";" + newDistanceBlock.OutC.Block.Name + ";" + newDistanceBlock.OutC.Port.FullName);//put(addBlock.PortOUT.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = distanceBlock;
                    bp.Port = distanceBlock.PortOUT;
                    newDistanceBlock.OutC.Block.Connections.put(newDistanceBlock.OutC.Block.GetPort(newDistanceBlock.OutC.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = distanceBlock.Name;
                    sbp.PortName = distanceBlock.PortOUT.FullName;
                    newDistanceBlock.OutC.Block.StringConnections.add(newDistanceBlock.OutC.Block.GetPort(newDistanceBlock.OutC.Port).FullName + ";" + distanceBlock.Name + ";" + distanceBlock.PortOUT.FullName);//put(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port).FullName, sbp);
                }

                distanceBlock.updateConnections(design);
                design.getChildren().add(distanceBlock.group);
                currentScheme.AddBlock(distanceBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Vector block");
        mItem.setOnAction(e ->
        {
            VectorBlockSpec newVectorBlock = new VectorBlockSpec();
            newVectorBlock.CurrentScheme = currentScheme;
            newVectorBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Vector) + 1;

            if (newVectorBlock.Display()) {
                VectorBlock vectorBlock = new VectorBlock(newVectorBlock.BlockName, InvokedX, InvokedY);

                BlockPort bp;

                if (newVectorBlock.In1C != null) {
                    vectorBlock.Connections.put(vectorBlock.PortIN1, newVectorBlock.In1C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newVectorBlock.In1C.Block.Name;
                    sbp.PortName = newVectorBlock.In1C.Port.FullName;
                    vectorBlock.StringConnections.add(vectorBlock.PortIN1.FullName + ";" + newVectorBlock.In1C.Block.Name + ";" + newVectorBlock.In1C.Port.FullName);//put(addBlock.PortIN1.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = vectorBlock;
                    bp.Port = vectorBlock.PortIN1;
                    newVectorBlock.In1C.Block.Connections.put(newVectorBlock.In1C.Block.GetPort(newVectorBlock.In1C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = vectorBlock.Name;
                    sbp.PortName = vectorBlock.PortIN1.FullName;
                    newVectorBlock.In1C.Block.StringConnections.add(newVectorBlock.In1C.Block.GetPort(newVectorBlock.In1C.Port).FullName + ";" + vectorBlock.Name + ";" + vectorBlock.PortIN1.FullName);//put(newAddBlock.In1C.Block.GetPort(newAddBlock.In1C.Port).FullName, sbp);

                }

                if (newVectorBlock.In2C != null){
                    vectorBlock.Connections.put(vectorBlock.PortIN2, newVectorBlock.In2C);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newVectorBlock.In2C.Block.Name;
                    sbp.PortName = newVectorBlock.In2C.Port.FullName;
                    vectorBlock.StringConnections.add(vectorBlock.PortIN2.FullName + ";" + newVectorBlock.In2C.Block.Name + ";" + newVectorBlock.In2C.Port.FullName);//put(addBlock.PortIN2.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = vectorBlock;
                    bp.Port = vectorBlock.PortIN2;
                    newVectorBlock.In2C.Block.Connections.put(newVectorBlock.In2C.Block.GetPort(newVectorBlock.In2C.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = vectorBlock.Name;
                    sbp.PortName = vectorBlock.PortIN2.FullName;
                    newVectorBlock.In2C.Block.StringConnections.add(newVectorBlock.In2C.Block.GetPort(newVectorBlock.In2C.Port).FullName + ";" + vectorBlock.Name + ";" + vectorBlock.PortIN2.FullName);//put(newAddBlock.In2C.Block.GetPort(newAddBlock.In2C.Port).FullName, sbp);
                }

                if (newVectorBlock.OutC != null){
                    vectorBlock.Connections.put(vectorBlock.PortOUT, newVectorBlock.OutC);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newVectorBlock.OutC.Block.Name;
                    sbp.PortName = newVectorBlock.OutC.Port.FullName;
                    vectorBlock.StringConnections.add(vectorBlock.PortOUT.FullName + ";" + newVectorBlock.OutC.Block.Name + ";" + newVectorBlock.OutC.Port.FullName);//put(addBlock.PortOUT.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = vectorBlock;
                    bp.Port = vectorBlock.PortOUT;
                    newVectorBlock.OutC.Block.Connections.put(newVectorBlock.OutC.Block.GetPort(newVectorBlock.OutC.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = vectorBlock.Name;
                    sbp.PortName = vectorBlock.PortOUT.FullName;
                    newVectorBlock.OutC.Block.StringConnections.add(newVectorBlock.OutC.Block.GetPort(newVectorBlock.OutC.Port).FullName + ";" + vectorBlock.Name + ";" + vectorBlock.PortOUT.FullName);//put(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port).FullName, sbp);
                }

                vectorBlock.updateConnections(design);
                design.getChildren().add(vectorBlock.group);
                currentScheme.AddBlock(vectorBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Start block");
        mItem.setOnAction(e ->
        {
            StartBlockSpec newStartBlock = new StartBlockSpec();
            newStartBlock.CurrentScheme = currentScheme;
            newStartBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Start) + 1;

            if (newStartBlock.Display()) {
                StartBlock startBlock = new StartBlock(newStartBlock.BlockName, InvokedX, InvokedY);
                startBlock.Value = newStartBlock.ValueValue;
                startBlock.DoOperation();//?

                BlockPort bp;

                if (newStartBlock.OutC != null){
                    startBlock.Connections.put(startBlock.PortOUT, newStartBlock.OutC);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newStartBlock.OutC.Block.Name;
                    sbp.PortName = newStartBlock.OutC.Port.FullName;
                    startBlock.StringConnections.add(startBlock.PortOUT.FullName + ";" + newStartBlock.OutC.Block.Name + ";" + newStartBlock.OutC.Port.FullName);//put(addBlock.PortOUT.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = startBlock;
                    bp.Port = startBlock.PortOUT;
                    newStartBlock.OutC.Block.Connections.put(newStartBlock.OutC.Block.GetPort(newStartBlock.OutC.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = startBlock.Name;
                    sbp.PortName = startBlock.PortOUT.FullName;
                    newStartBlock.OutC.Block.StringConnections.add(newStartBlock.OutC.Block.GetPort(newStartBlock.OutC.Port).FullName + ";" + startBlock.Name + ";" + startBlock.PortOUT.FullName);//put(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port).FullName, sbp);
                }

                startBlock.updateConnections(design);
                design.getChildren().add(startBlock.group);
                currentScheme.AddBlock(startBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Vector end block");
        mItem.setOnAction(e ->
        {
            EndBlockSpec newEndBlock = new EndBlockSpec();
            newEndBlock.CurrentScheme = currentScheme;
            newEndBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.End) + 1;

            if (newEndBlock.CurrentBlock != 1)
                return;

            if (newEndBlock.Display("vector2")) {
                EndVectorBlock endBlock = new EndVectorBlock("End", InvokedX, InvokedY);

                BlockPort bp;

                if (newEndBlock.OutC != null){
                    endBlock.Connections.put(endBlock.PortIN, newEndBlock.OutC);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newEndBlock.OutC.Block.Name;
                    sbp.PortName = newEndBlock.OutC.Port.FullName;
                    endBlock.StringConnections.add(endBlock.PortIN.FullName + ";" + newEndBlock.OutC.Block.Name + ";" + newEndBlock.OutC.Port.FullName);//put(addBlock.PortOUT.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = endBlock;
                    bp.Port = endBlock.PortIN;
                    newEndBlock.OutC.Block.Connections.put(newEndBlock.OutC.Block.GetPort(newEndBlock.OutC.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = endBlock.Name;
                    sbp.PortName = endBlock.PortIN.FullName;
                    newEndBlock.OutC.Block.StringConnections.add(newEndBlock.OutC.Block.GetPort(newEndBlock.OutC.Port).FullName + ";" + endBlock.Name + ";" + endBlock.PortIN.FullName);//put(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port).FullName, sbp);
                }

                endBlock.updateConnections(design);
                design.getChildren().add(endBlock.group);
                currentScheme.AddBlock(endBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Point end block");
        mItem.setOnAction(e ->
        {
            EndBlockSpec newEndBlock = new EndBlockSpec();
            newEndBlock.CurrentScheme = currentScheme;
            newEndBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.End) + 1;

            if (newEndBlock.CurrentBlock != 1)
                return;

            if (newEndBlock.Display("point")) {
                EndPointBlock endBlock = new EndPointBlock("End", InvokedX, InvokedY);

                BlockPort bp;

                if (newEndBlock.OutC != null){
                    endBlock.Connections.put(endBlock.PortIN, newEndBlock.OutC);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newEndBlock.OutC.Block.Name;
                    sbp.PortName = newEndBlock.OutC.Port.FullName;
                    endBlock.StringConnections.add(endBlock.PortIN.FullName + ";" + newEndBlock.OutC.Block.Name + ";" + newEndBlock.OutC.Port.FullName);//put(addBlock.PortOUT.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = endBlock;
                    bp.Port = endBlock.PortIN;
                    newEndBlock.OutC.Block.Connections.put(newEndBlock.OutC.Block.GetPort(newEndBlock.OutC.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = endBlock.Name;
                    sbp.PortName = endBlock.PortIN.FullName;
                    newEndBlock.OutC.Block.StringConnections.add(newEndBlock.OutC.Block.GetPort(newEndBlock.OutC.Port).FullName + ";" + endBlock.Name + ";" + endBlock.PortIN.FullName);//put(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port).FullName, sbp);
                }

                endBlock.updateConnections(design);
                design.getChildren().add(endBlock.group);
                currentScheme.AddBlock(endBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Double end block");
        mItem.setOnAction(e ->
        {
            EndBlockSpec newEndBlock = new EndBlockSpec();
            newEndBlock.CurrentScheme = currentScheme;
            newEndBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.End) + 1;

            if (newEndBlock.CurrentBlock != 1)
                return;

            if (newEndBlock.Display("double")) {
                EndDoubleBlock endBlock = new EndDoubleBlock("End", InvokedX, InvokedY);

                BlockPort bp;

                if (newEndBlock.OutC != null){
                    endBlock.Connections.put(endBlock.PortIN, newEndBlock.OutC);

                    StringBlockPort sbp = new StringBlockPort();
                    sbp.BlockName = newEndBlock.OutC.Block.Name;
                    sbp.PortName = newEndBlock.OutC.Port.FullName;
                    endBlock.StringConnections.add(endBlock.PortIN.FullName + ";" + newEndBlock.OutC.Block.Name + ";" + newEndBlock.OutC.Port.FullName);//put(addBlock.PortOUT.FullName, sbp);

                    bp = new BlockPort();
                    bp.Block = endBlock;
                    bp.Port = endBlock.PortIN;
                    newEndBlock.OutC.Block.Connections.put(newEndBlock.OutC.Block.GetPort(newEndBlock.OutC.Port), bp);

                    sbp = new StringBlockPort();
                    sbp.BlockName = endBlock.Name;
                    sbp.PortName = endBlock.PortIN.FullName;
                    newEndBlock.OutC.Block.StringConnections.add(newEndBlock.OutC.Block.GetPort(newEndBlock.OutC.Port).FullName + ";" + endBlock.Name + ";" + endBlock.PortIN.FullName);//put(newAddBlock.OutC.Block.GetPort(newAddBlock.OutC.Port).FullName, sbp);
                }

                endBlock.updateConnections(design);
                design.getChildren().add(endBlock.group);
                currentScheme.AddBlock(endBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        design = new AnchorPane();
        design.setPrefSize(0, 0);
        design.setStyle("-fx-background-color: DeepSkyBlue");
        design.setOnMouseClicked(e ->
        {
            if (e.getButton() == MouseButton.SECONDARY)
             //   CreateBlock();
            {
                InvokedX = e.getSceneX();
                InvokedY = e.getSceneY();

                context.show(design, e.getScreenX(), e.getScreenY());
            }
        });

        designScroll = new ScrollPane();
        designScroll.setContent(design);

        borderPane.setCenter(designScroll);

        Scene scene = new Scene(borderPane, 600, 600);

        window.setScene(scene);

        window.show();

    }

    public void CreateNewScheme()
    {
        NewSchemeSet newScheme = new NewSchemeSet();

        if (newScheme.Display()) {
            currentScheme = new Scheme(newScheme.HeightValue, newScheme.WidthValue);
            SetSchemeSize(currentScheme.GetSchemeTableSize().X, currentScheme.GetSchemeTableSize().Y);

            design.getChildren().clear();

            NeedSave = true;
        }
    }

    public void LoadScheme()
    {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select XML Scheme");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        File file =  fileChooser.showOpenDialog(window);

        if (file != null)
        {
            currentScheme = new Scheme();
            design.getChildren().clear();

            currentScheme.LoadScheme(file.getAbsolutePath());
            SetSchemeSize(currentScheme.GetSchemeTableSize().X, currentScheme.GetSchemeTableSize().Y);

            for (Block b : currentScheme.BlockDictionary.values())
            {
                Block fuckBlock = new AddBlock();

                switch (b.BlockType)
                {
                    case Add:
                        fuckBlock = new AddBlock(b.Name, b.PosX, b.PosY);
                        break;
                    case Distance:
                        fuckBlock = new DistanceBlock(b.Name, b.PosX, b.PosY);
                        break;
                    case Div:
                        fuckBlock = new DivBlock(b.Name, b.PosX, b.PosY);
                        break;
                    case Mul:
                        fuckBlock = new MulBlock(b.Name, b.PosX, b.PosY);
                        break;
                    case Point:
                        fuckBlock = new PointBlock(b.Name, b.PosX, b.PosY);
                        break;
                    case Sub:
                        fuckBlock = new SubBlock(b.Name, b.PosX, b.PosY);
                        break;
                    case Vector:
                        fuckBlock = new VectorBlock(b.Name, b.PosX, b.PosY);
                        break;
                    case Start:
                        fuckBlock = new StartBlock(b.Name, b.PosX, b.PosY);
                        break;
                    case End:

                        switch (((EndBlock)b).EndBlockType)
                        {
                            case Double:
                                fuckBlock = new EndDoubleBlock(b.Name, b.PosX, b.PosY);
                                break;
                            case Point:
                                fuckBlock = new EndPointBlock(b.Name, b.PosX, b.PosY);
                                break;
                            case Vector:
                                fuckBlock = new EndVectorBlock(b.Name, b.PosX, b.PosY);
                                break;
                        }
                        break;
                }

                //fuckBlock.ReAddPorts();
                design.getChildren().add(fuckBlock.group);

    //            currentScheme.BlockDictionary.forEach((k,v) ->
      //          {
        //            v.updateConnections(design);
          //      });
            }
            currentScheme.RestoreConnections();

            for (Block b : currentScheme.BlockDictionary.values())
            {
                b.updateConnections(design);
            }

            NeedSave = false;
        }
    }

    public void SetSchemeSize(double height, double width)
    {
        design.setPrefSize(height, width);
        design.setMaxSize(height, width);
        design.setMinSize(height, width);
    }

    public void SaveScheme()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save XML Scheme");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        File file =  fileChooser.showSaveDialog(window);

        if (file != null) {
            currentScheme.SaveScheme(file.getAbsolutePath());
            NeedSave = false;
        }
    }

    public void RunScheme()
    {
        String res = currentScheme.Run();

        ResultText.setText("Run result: " + res);
    }

    public void TraceScheme()
    {
        String res = currentScheme.Trace();

        ResultText.setText("Trace result: " + res);
    }

    public void CloseRequested()
    {
        if (!NeedSave)
            window.close();
        else
            if (AlertBox.Display("Do you want to save changes?", "Save changes"))
                SaveScheme();
                window.close();
    }
    
}
