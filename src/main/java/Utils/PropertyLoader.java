package main.java.Utils;

import java.util.ResourceBundle;

public class PropertyLoader {
    private static ResourceBundle res = ResourceBundle.getBundle("params");

    public static String loadProperty(String name){
        return res.getString(name);
    }
}
