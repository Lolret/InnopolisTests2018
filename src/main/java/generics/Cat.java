package generics;

public class Cat implements Animal {
    private  int pawCount;
    private  int age;
    private String voice;


    @Override
    public int getPawCount() {
        return pawCount;
    }

    @Override
    public void setPawCount(int pawCount) {
        this.pawCount = pawCount;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;

    }

    @Override
    public String getVoice() {
        return voice;
    }

    public Cat(int pawCount, int age, String voice) {
        this.pawCount = pawCount;
        this.age = age;
        this.voice = voice;
    }

    @Override
    public void setVoice(String voice) {
        this.voice = voice;

    }
}
