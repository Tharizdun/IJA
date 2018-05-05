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

public class StartBlockSpec extends NewBlockSpec{

    public double ValueValue;

    public boolean Display()
    {
        List<BlockPort> freePortsIn = CurrentScheme.GetFreePorts(PortType.In, "double");

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Start block specification");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(25, 25, 25, 25));

        Label ValueLab = new Label("Value:");
        TextField Value = new TextField();

        HBox hb = new HBox();
        hb.getChildren().addAll(ValueLab, Value);
        hb.setSpacing(10);
        layout.getChildren().add(hb);

        Label OutputLab = new Label("Output:");
        ComboBox<String> Output = new ComboBox<>();

        for (BlockPort bp : freePortsIn)
        {
            Output.getItems().add(bp.toString());
        }

        hb = new HBox();
        hb.getChildren().addAll(OutputLab, Output);
        hb.setSpacing(10);
        layout.getChildren().add(hb);

        Button Confirm = new Button("Confirm");
        Confirm.setOnAction(e -> {
            ValueValue = CheckDoubleValue(Value.getText());

            if (ValueValue != -1) {
                if (Output.getValue() != null)
                    OutC = freePortsIn.get(GetSelectedIndex(Output.getValue(), freePortsIn));

                result = true;
                BlockName = "Start" + Integer.toString(CurrentBlock);
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

        Scene scene = new Scene(layout, 300, 175);

        window.setScene(scene);
        window.showAndWait();

        return result;
    }

    public double CheckDoubleValue(String stringValue)
    {
        try {
            return Double.parseDouble(stringValue);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
