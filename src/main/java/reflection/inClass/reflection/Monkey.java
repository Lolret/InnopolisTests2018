package reflection.inClass.reflection;

public class Monkey {
    private String name;
    public Integer age;
    private String country;
    private String subtype;

    private final int numlegs = 2;

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    private void saySimething(String speech) {
        System.out.println(speech);
    }
}