package edu.wpi.first.wpilibj.helpers;

/**
 *
 * @author Sean Zammit
 */
public class ArrayHelper {

    public static Object[] combineLists(Object[] list1, Object[] list2) {
        Object[] list = new Object[list1.length + list2.length];
        for(int a = 0; a < list1.length; a++) list[a] = list1[a];
        for(int b = 0; b < list2.length; b++) list[list1.length + b] = list1[b];
        
        return list;
    }
    
    public static Object[] combineLists(Object[] list1, Object obj2) {
        Object[] list = new Object[list1.length + 1];
        for(int a = 0; a < list1.length; a++) list[a] = list1[a];
        list[list1.length] = obj2;
        
        return list;
    }
}
