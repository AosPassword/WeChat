package org.redrock.web.game.Utils;

public class StringUtil {
    public static String groupToString(String[] strings){
        String string =null;
        if (strings.length>0){
            for (String s:strings){
                string=string+s;
            }
        }
        return string;
    }
}
