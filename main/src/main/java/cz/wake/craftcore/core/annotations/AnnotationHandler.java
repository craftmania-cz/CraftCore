package cz.wake.craftcore.core.annotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnnotationHandler {

    private static HashMap<Class, List<Object>> data = new HashMap<>();

    /**
     * Registers the given object and its class.<br>By registering, it allowed CraftCore to handle custom annotations.
     * You only need to call this method only one per object.
     * @param object an object
     * @param clazz the class of the given object
     */
    public static void register(Class clazz, Object object){
        List<Object> x = new ArrayList<>();
        if(data.containsKey(clazz)){
            x = data.get(clazz);
        }
        x.add(object);
        data.put(clazz, x);
    }

    /**
     * Unregisters the given registered object and its class.<br>Only calls this method whenever you handled completely and don't want to use any annotations anymore. If there's a working annotation, shouldn't call this method!
     * @param object an registered object
     * @param clazz the class of the given object
     */
    public static void unregister(Class clazz, Object object){
        List<Object> x = new ArrayList<>();
        if(data.containsKey(clazz)){
            x = data.get(clazz);
        }
        x.remove(object);
        data.put(clazz, x);
    }

    public static HashMap<Class, List<Object>> getClasses(){
        return data;
    }
}
