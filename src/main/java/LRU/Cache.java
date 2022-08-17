package LRU;

import java.util.Optional;

public interface Cache <k,v>{
    boolean set (k key,v value);
    Optional <v> get(k key);
    int size ();
    boolean isEmpty();
    void clear();
}