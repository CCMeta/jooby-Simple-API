package app.Controller;

import java.lang.reflect.InvocationTargetException;

public class BaseController {
    public static String runRequest(String controller, String method)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var getClass_obj = Class.forName("app.Controller." + controller + "Controller");
        var responseBody = getClass_obj.getMethod(method);
        return responseBody.invoke(getClass_obj.getDeclaredConstructor().newInstance()).toString();
    }
}
