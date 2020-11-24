import java.io.Serializable;
import java.util.Iterator;

public class CustomIterator implements Iterator<Integer>, Serializable {
    private Integer i = 0;


    public boolean hasNext() {
        return i < 100;
    }

    public Integer next() {
        i++;
        return i;
    }

    public void remove() {
        
    }
}