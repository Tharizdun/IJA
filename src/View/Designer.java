package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Classes.*;

import java.io.File;

public class Designer extends Application {
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
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

        design = new AnchorPane();
        design.setPrefSize(0, 0);
        design.setStyle("-fx-background-color: DeepSkyBlue");
        design.setOnMouseClicked(e ->
        {
            if (e.getButton() == MouseButton.SECONDARY)
                CreateBlock();
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
                Rectangle fuckBlockRect = new Rectangle(40, 50);

                fuckBlockRect.setX(b.PosX);
                fuckBlockRect.setY(b.PosY);

                design.getChildren().add(fuckBlockRect);
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
