package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewSchemeSet{

    boolean result = false;

    int HeightValue;
    int WidthValue;

    Stage window;

    public boolean Display()
    {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create new scheme");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(25, 25, 25, 25));

        Label HeightLab = new Label("Height:");
        TextField Height = new TextField();
        HBox hb = new HBox();
        hb.getChildren().addAll(HeightLab, Height);
        hb.setSpacing(10);
        layout.getChildren().add(hb);

        Label WidthLab = new Label("Width:");
        TextField Width = new TextField();
        hb = new HBox();
        hb.getChildren().addAll(WidthLab, Width);
        hb.setSpacing(10);
        layout.getChildren().add(hb);

        Button Confirm = new Button("Confirm");
        Confirm.setOnAction(e -> {
            HeightValue = CheckIntValue(Height.getText());
            WidthValue = CheckIntValue(Width.getText());

            if (HeightValue != -1 && WidthValue != -1) {
                result = true;
                window.close();
            }
        });

        Button Cancel = new Button("Cancel");
        Cancel.setOnAction(e -> {
            result = false;
            window.close();
        });

        hb = new HBox();
        hb.getChildren().addAll(Confirm, Cancel);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        layout.getChildren().add(hb);

        Scene scene = new Scene(layout, 300, 150);

        window.setScene(scene);
        window.showAndWait();

        return result;
    }

    public int CheckIntValue(String stringValue)
    {
        try {
            return Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
