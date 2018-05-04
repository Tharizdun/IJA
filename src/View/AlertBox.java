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

public class AlertBox {

    static boolean result = false;

    Stage window;

    public static boolean Display(String message)
    {
        return Display(message, "Alert box");
    }

    public static boolean Display(String message, String title)
    {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(25, 25, 25, 25));

        Label Question = new Label(message);
        layout.getChildren().add(Question);

        Button Confirm = new Button("Yes");
        Confirm.setOnAction(e -> {
            result = true;
            window.close();
        });

        Button Cancel = new Button("No");
        Cancel.setOnAction(e -> {
            result = false;
            window.close();
        });

        HBox hb = new HBox();
        hb.getChildren().addAll(Confirm, Cancel);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        layout.getChildren().add(hb);

        Scene scene = new Scene(layout, 300, 100);

        window.setScene(scene);
        window.showAndWait();

        return result;
    }
}
