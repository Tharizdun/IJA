package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Classes.*;

import java.io.File;

public class Designer extends Application {
    double InvokedX, InvokedY;
    Button NewButton;
    Button LoadButton;
    Button SaveButton;
    Button RunButton;
    Button TraceButton;

    ScrollPane designScroll;

    Scheme currentScheme;

    Stage window;
    AnchorPane design;

    boolean NeedSave = false;

    private Block blok1;
    private Block blok2;
    private Block blok3;
    private Block blok4;
    private Block blok5;
    private Block blok6;

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

        HBox TopMenu = new HBox(5);
        TopMenu.getChildren().addAll(NewButton, LoadButton, SaveButton, RunButton, TraceButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(TopMenu);

        ContextMenu context = new ContextMenu();

        MenuItem mItem = new MenuItem("New Add block");
        mItem.setOnAction(e ->
        {
            AddBlockSpec newAddBlock = new AddBlockSpec();
            newAddBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Add) + 1;

            if (newAddBlock.Display()) {
                AddBlock addBlock = new AddBlock(newAddBlock.BlockName, InvokedX, InvokedY);

                design.getChildren().add(addBlock);
                currentScheme.AddBlock(addBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Sub block");
        mItem.setOnAction(e ->
        {
            SubBlockSpec newSubBlock = new SubBlockSpec();
            newSubBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Sub) + 1;

            if (newSubBlock.Display()) {
                SubBlock subBlock = new SubBlock(newSubBlock.BlockName, InvokedX, InvokedY);

                design.getChildren().add(subBlock);
                currentScheme.AddBlock(subBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Mul block");
        mItem.setOnAction(e ->
        {
            MulBlockSpec newMulBlock = new MulBlockSpec();
            newMulBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Add) + 1;

            if (newMulBlock.Display()) {
                MulBlock mulBlock = new MulBlock(newMulBlock.BlockName, InvokedX, InvokedY);

                design.getChildren().add(mulBlock);
                currentScheme.AddBlock(mulBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Div block");
        mItem.setOnAction(e ->
        {
            DivBlockSpec newDivBlock = new DivBlockSpec();
            newDivBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Div) + 1;

            if (newDivBlock.Display()) {
                DivBlock divBlock = new DivBlock(newDivBlock.BlockName, InvokedX, InvokedY);

                design.getChildren().add(divBlock);
                currentScheme.AddBlock(divBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Point block");
        mItem.setOnAction(e ->
        {
            PointBlockSpec newPointBlock = new PointBlockSpec();
            newPointBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Point) + 1;

            if (newPointBlock.Display()) {
                PointBlock pointBlock = new PointBlock(newPointBlock.BlockName, InvokedX, InvokedY);

                design.getChildren().add(pointBlock);
                currentScheme.AddBlock(pointBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Distance block");
        mItem.setOnAction(e ->
        {
            DistanceBlockSpec newDistanceBlock = new DistanceBlockSpec();
            newDistanceBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Distance) + 1;

            if (newDistanceBlock.Display()) {
                DistanceBlock distanecBlock = new DistanceBlock(newDistanceBlock.BlockName, InvokedX, InvokedY);

                design.getChildren().add(distanecBlock);
                currentScheme.AddBlock(distanecBlock);

                NeedSave = true;
            }
        });
        context.getItems().add(mItem);

        mItem = new MenuItem("New Vector block");
        mItem.setOnAction(e ->
        {
            VectorBlockSpec newVectorBlock = new VectorBlockSpec();
            newVectorBlock.CurrentBlock = currentScheme.GetBlockTypeCount(BlockType.Vector) + 1;

            if (newVectorBlock.Display()) {
                VectorBlock vectorBlock = new VectorBlock(newVectorBlock.BlockName, InvokedX, InvokedY);

                design.getChildren().add(vectorBlock);
                currentScheme.AddBlock(vectorBlock);

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

    public void CreateBlock()
    {
        ContextMenu context = new ContextMenu();
        MenuItem sss = new MenuItem("Add block");
        context.getItems().add(sss);
        //design.setOnContextMenuRequested();

        //pane, event.getScreenX(), event.getScreenY()






        NewSchemeSet newScheme = new NewSchemeSet();
        newScheme.Display();

        AddBlock fuckBlock = new AddBlock("Fuck me", newScheme.HeightValue, newScheme.WidthValue);

        design.getChildren().add(fuckBlock);
        currentScheme.AddBlock(fuckBlock);

        NeedSave = true;
    }

    public void CreateNewScheme()
    {
        NewSchemeSet newScheme = new NewSchemeSet();

        if (newScheme.Display()) {
            currentScheme = new Scheme(newScheme.HeightValue, newScheme.WidthValue);
            SetSchemeSize(currentScheme.GetSchemeTableSize().X, currentScheme.GetSchemeTableSize().Y);

            NeedSave = true;
        }
    }

    public void LoadScheme()
    {
        currentScheme = new Scheme();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select XML Scheme");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        File file =  fileChooser.showOpenDialog(window);

        if (file != null)
        {
            currentScheme.LoadScheme(file.getAbsolutePath());
            SetSchemeSize(currentScheme.GetSchemeTableSize().X, currentScheme.GetSchemeTableSize().Y);

            for (Block b : currentScheme.BlockDictionary.values())
            {
                AddBlock fuckBlock = new AddBlock(b.Name, b.PosX, b.PosY);

                design.getChildren().add(fuckBlock);
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
        //currentScheme = new Scheme(400, 350);

        //blok1 = new AddBlock("blok1",10, 10);
        //blok2 = new AddBlock("blok2",20, 10);
        //blok3 = new AddBlock("blok3",50, 10);
        //blok4 = new AddBlock("blok4",0, 10);
        //blok5 = new AddBlock("blok5",90, 10);
        //blok6 = new AddBlock("blok6",11, 10);

        //currentScheme.AddBlock(blok1);
        //currentScheme.AddBlock(blok2);
        //currentScheme.AddBlock(blok3);
        //currentScheme.AddBlock(blok4);
        //currentScheme.AddBlock(blok5);
        //currentScheme.AddBlock(blok6);

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
    {}

    public void TraceScheme()
    {}

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
