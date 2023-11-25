package org.example;

import java.util.Map;

public interface Repository {

    Map <Integer, String> load ();
    int loadCount();
    void save (Integer i, String str);
}
