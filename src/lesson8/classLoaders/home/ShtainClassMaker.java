package lesson8.classLoaders.home;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ShtainClassMaker {

    Shtain shtain;

    static String methodCode;

    static StringBuilder source = new StringBuilder().append("package lesson8.classLoaders.home;\n" +
            "\n" +
            "public class Shtain {\n" +
            "\n" +
            "    void doWork() {\n");

    private static void makeMethod() {
        source.append("        ")
                .append(methodCode).append("\n")
                .append("    }" +
                        "\n}");
    }

    private static File sourceFile;

    public static void doAll(String path) {
        collectCode();
        makeMethod();
        makeFile(path);
        compileFile(path);
        loadAndinstantiate(path);
    }

    private static void loadAndinstantiate(String path) {
        // Load and instantiate compiled class.
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { path.toURI().toURL() });
        Class<?> cls = Class.forName("test.Test", true, classLoader); // Should print "hello".
//        Object instance = cls.newInstance(); // Should print "world".
        System.out.println(instance); // Should print "test.Test@hashcode".
    }

    private static void compileFile(String path) {

// Compile source file.
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        System.out.println( sourceFile.getPath());

        compiler.run(null, null, null,  sourceFile.getPath());


    }

    private static void makeFile(String path) {
        sourceFile = new File(path + "\\Shtain.java");
        try {
            Files.write(Paths.get(sourceFile.getPath()), source.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void collectCode() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            sb.append(s);
            if (s.equals("")) break;
        }
        methodCode = sb.toString();
    }
}
