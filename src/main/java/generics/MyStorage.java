package generics;

import java.util.List;

public class MyStorage <K extends Animal> {
    List<K> list;
}