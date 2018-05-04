package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddBlockSpec {

    boolean result = false;

    public String BlockName;


    Stage window;

    public boolean Display()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add block specification");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(25, 25, 25, 25));

        Label NameBlockLab = new Label("Block name:");
        TextField NameBlock = new TextField();
        HBox hb = new HBox();
        hb.getChildren().addAll(NameBlockLab, NameBlock);
        hb.setSpacing(10);
        layout.getChildren().add(hb);

        Label Input1Lab = new Label("Input 1:");
        TextField Input1 = new TextField();
        hb = new HBox();
        hb.getChildren().addAll(Input1Lab, Input1);
        hb.setSpacing(10);
        layout.getChildren().add(hb);

        Label Input2Lab = new Label("Input 2:");
        TextField Input2 = new TextField();
        hb = new HBox();
        hb.getChildren().addAll(Input2Lab, Input2);
        hb.setSpacing(10);
        layout.getChildren().add(hb);

        Label OutputLab = new Label("Output:");
        TextField Output = new TextField();
        hb = new HBox();
        hb.getChildren().addAll(OutputLab, Output);
        hb.setSpacing(10);
        layout.getChildren().add(hb);

        Button Confirm = new Button("Confirm");
        Confirm.setOnAction(e -> {
            result = true;
            BlockName = NameBlock.getText();
            window.close();
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

        Scene scene = new Scene(layout, 300, 250);

        window.setScene(scene);
        window.showAndWait();

        return result;
    }
}
