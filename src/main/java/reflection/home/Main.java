package reflection.home;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, IOException {

        Iam me = new Iam();
        String myPathFile = "d://my.txt";
        String customPathFile = "d://custom.txt";

        //My XML serializer implementation;
        XMLUtil xmlUtil = new XMLUtil();
        xmlUtil.serialize(new Iam(), myPathFile);

        //Some outer XML serializer;
        XmlMapper mapper = new XmlMapper();
        String xml = mapper.writeValueAsString(new Iam());

        mapper.writeValue(new File(customPathFile), me);

        System.out.println(xml);

        Iam value = mapper.readValue(new String(Files.readAllBytes(Paths.get(myPathFile))), Iam.class);

        System.out.println("Is serializing object equals to serialized object: " + value.equals(me));

    }
}