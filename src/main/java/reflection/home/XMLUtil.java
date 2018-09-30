package reflection.home;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class XMLUtil {

    private StringBuilder sb;

    public void serialize(Object object, String file) throws IllegalAccessException, IOException {
        sb = new StringBuilder();
        String className = object.getClass().getSimpleName();

        sb.append("<" + className + ">");
        fieldsMaker(object);
        sb.append("</" + className + ">");

        Files.write(Paths.get(file), sb.toString().getBytes());
    }

    private void fieldsMaker(Object object) throws IllegalAccessException {
        for (Field f : Arrays.asList(object.getClass().getFields())) {
            sb.append("<" + f.getName() + ">");
            if (!(f.getType().isPrimitive() || f.getType().getSimpleName().equals("String"))) {
                fieldsMaker(f.get(object));
            } else sb.append(f.get(object));
            sb.append("</" + f.getName() + ">");
        }
    }

    public Object deSerialize(String file) {
        XmlMapper mapper = new XmlMapper();
        Object value = null;
        try {
            value = mapper.readValue(new String(Files.readAllBytes(Paths.get(file))), Object.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;

    }
}