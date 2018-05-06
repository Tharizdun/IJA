package Classes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Hashtable;

public class MyHashtable extends Hashtable {

    PropertyChangeSupport pcs = new PropertyChangeSupport (this);

    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    // Override whatever Hashtable method you want; here I'm just doing put
    public Object put(Object key, Object value) {
        Object result = super.put(key, value);
        pcs.firePropertyChange("contents", null, null);
        return result;
    }
}
