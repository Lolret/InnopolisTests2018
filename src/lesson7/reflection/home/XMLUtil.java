package lesson7.reflection.home;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class XMLUtil {
    private Object obj;
    int label = 0;

    public String createXML(Object object) throws IllegalAccessException {

        this.obj = object;

        String className = object.getClass().getSimpleName();
        List<Field> fields = Arrays.asList(object.getClass().getFields());


        label++;

        StringBuilder sb = new StringBuilder();

        sb.append("<" + className + ">\n");

        for (Field f : fields) {
            sb.append(new String(new char[label]).replace("\0", "\t"));
            sb.append("<" + f.getName() + ">\n");
            sb.append(new String(new char[label]).replace("\0", "\t"));

            sb.append(fieldMaker(f) + "\n");
            sb.append(new String(new char[label]).replace("\0", "\t"));

            sb.append("</" + f.getName() + ">\n");

        }
        sb.append("</" + className + ">\n");

        return sb.toString();

    }


    private String fieldMaker(Field f) throws IllegalAccessException {
        if (!(f.getType().isPrimitive() || f.getType().getSimpleName().equals("String"))) {
            return createXML(f.get(obj));
        } else return "\t" + f.get(obj).toString();
    }
}
