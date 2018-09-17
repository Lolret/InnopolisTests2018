package lesson7.reflection.home;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class XMLUtil {

    public String createXML(String filePath, Object object) throws IllegalAccessException {

        String className = object.getClass().getSimpleName();
        List<Field> fields = Arrays.asList(object.getClass().getFields());

        StringBuilder sb = new StringBuilder();
        sb.append("<" + className + ">\n");
        for (Field f : fields) {
            sb.append("\t<" + f.getName() + ">\n");
            sb.append("\t\t" + f.get(object) + "\n");
            sb.append("\t</" + f.getName() + ">\n");

        }
        sb.append("</" + className + ">\n");

        return sb.toString();

    }
}
