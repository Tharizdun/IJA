/**
 *
 * @author Jan Rajnoha, xrajno09
 */

package Classes;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SchemeStorage {

    public ObjectSize TableSize;
    public Hashtable<String, Block> BlockDictionary;
    public List<ConnectionInfo> ConnectionDictionary = new ArrayList<>();

    public SchemeStorage()
    {}

}
