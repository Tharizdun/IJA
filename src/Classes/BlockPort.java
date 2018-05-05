package Classes;

public class BlockPort {

    public Port Port;
    public Block Block;


    @Override
    public boolean equals(Object o)
    {
        if (o instanceof BlockPort)
            return Block.Name == ((BlockPort)o).Block.Name && Port == ((BlockPort)o).Port;

        return false;
    }

    @Override
    public String toString(){

        String index = "";

        if (Port.PortType == PortType.In)
            index = Integer.toString(Block.Ports.indexOf(Port) + 1);

        return Block.Name + " - Port" + Port.PortType.toString() + index;
    }
}
