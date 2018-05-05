package View;

import Classes.BlockPort;
import Classes.Port;
import Classes.Scheme;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.List;

public class NewBlockSpec
{
    boolean result = false;

    Stage window;

    public String BlockName;
    public int CurrentBlock = 0;
    public Scheme CurrentScheme;

    public BlockPort In1C;
    public BlockPort In2C;
    public BlockPort OutC;

    public int GetSelectedIndex(String selectedValue, List<BlockPort> freePorts)
    {
        int i = -1;
        String currentFreePort  ="";

        while (selectedValue.hashCode() != currentFreePort.hashCode())
        {
            i++;
            currentFreePort = freePorts.get(i).toString();
        }

        return i;
    }
}
