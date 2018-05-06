package View;

import Classes.BlockPort;
import Classes.PortType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class EndBlockSpec extends NewBlockSpec{

    /**
     * Nastaveni hodnot pro End blck podle typu
     * @param portTypeName typ end bloku
     * @return vysledek
     */
    public boolean Display(String portTypeName)
    {
        List<BlockPort> freePortsOut = CurrentScheme.GetFreePorts(PortType.Out, portTypeName);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("End block specification");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(25, 25, 25, 25));

        Label InputLab = new Label("Input:");
        ComboBox<String> Input = new ComboBox<>();

        for (BlockPort bp : freePortsOut)
        {
            Input.getItems().add(bp.toString());
        }

        HBox hb = new HBox();
        hb.getChildren().addAll(InputLab, Input);
        hb.setSpacing(10);
        layout.getChildren().add(hb);

        Button Confirm = new Button("Confirm");
        Confirm.setOnAction(e -> {

                if (Input.getValue() != null)
                    OutC = freePortsOut.get(GetSelectedIndex(Input.getValue(), freePortsOut));

                result = true;
                BlockName = "Start" + Integer.toString(CurrentBlock);
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

        Scene scene = new Scene(layout, 300, 175);

        window.setScene(scene);
        window.showAndWait();

        return result;
    }
}
