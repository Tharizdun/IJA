package View;

import Classes.BlockPort;
import Classes.Port;
import Classes.PortType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class SubBlockSpec  extends NewBlockSpec{

    /**
     * Nastaveni hodnot pro Sub
     * @return Vysledek
     */
    public boolean Display()
    {
        List<BlockPort> freePortsOut = CurrentScheme.GetFreePorts(PortType.Out, "double");
        List<BlockPort> freePortsIn = CurrentScheme.GetFreePorts(PortType.In, "double");

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Sub block specification");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(25, 25, 25, 25));

        Label Input1Lab = new Label("Input 1:");
        ComboBox<String> Input1 = new ComboBox<>();

        for (BlockPort bp : freePortsOut)
        {
            Input1.getItems().add(bp.toString());
        }

        HBox hb = new HBox();
        hb.getChildren().addAll(Input1Lab, Input1);
        hb.setSpacing(10);
        layout.getChildren().add(hb);

        Label Input2Lab = new Label("Input 2:");
        ComboBox<String> Input2 = new ComboBox<>();

        for (BlockPort bp : freePortsOut)
        {
            Input2.getItems().add(bp.toString());
        }

        hb = new HBox();
        hb.getChildren().addAll(Input2Lab, Input2);
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

            boolean go = Input1.getValue() == null;

            if (!go)
            {
                go = Input2.getValue() == null;

                if (!go)
                    go = Input1.getValue().hashCode() != Input2.getValue().hashCode();
            }

            if (go) {
                if (Input1.getValue() != null)
                    In1C = freePortsOut.get(GetSelectedIndex(Input1.getValue(), freePortsOut));

                if (Input2.getValue() != null)
                    In2C = freePortsOut.get(GetSelectedIndex(Input2.getValue(), freePortsOut));

                if (Output.getValue() != null)
                    OutC = freePortsIn.get(GetSelectedIndex(Output.getValue(), freePortsIn));

                result = true;
                BlockName = "Sub" + Integer.toString(CurrentBlock);
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
}
