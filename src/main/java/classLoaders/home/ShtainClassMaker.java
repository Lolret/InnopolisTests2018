package classLoaders.home;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ShtainClassMaker {

    Shtain shtain;

    static String methodCode;

    private static File sourceFile;

    static StringBuilder source = new StringBuilder().append("package classLoaders.home;\n" +
            "\n" +
            "public class Shtain {\n" +
            "\n" +
            "    public void doWork() {\n");


    public static void doAll(String path) {
        collectCode();
        makeMethod();
        makeFile(path);
        compileFile(path);
        loadAndInstantiate(path);
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

    private static void makeMethod() {
        source.append("        ")
                .append(methodCode).append("\n")
                .append("    }" +
                        "\n}");
    }

    private static void makeFile(String path) {
        sourceFile = new File(path + "\\Shtain.java");
        try {
            Files.write(Paths.get(sourceFile.getPath()), source.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void compileFile(String path) {

// Compile source file.
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        System.out.println( sourceFile.getPath());

        compiler.run(null, null, null,  sourceFile.getPath());
    }

    private static void loadAndInstantiate(String path) {
        // Load and instantiate compiled class.

        ClassLoader classLoader = Shtain.class.getClassLoader();
        try {
            Class shtainClass = classLoader.loadClass("");
            Shtain shtain =  (Shtain) shtainClass.newInstance();
            shtain.doWork();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}