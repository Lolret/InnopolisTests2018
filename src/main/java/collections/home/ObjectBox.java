package collections.home;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ObjectBox {

    private List<Object> list;

    public ObjectBox() {
        list = new ArrayList<>();
    }

    public ObjectBox(List<Object> list) {
        this.list = list;
    }

    protected void addObject(Object obj) {
        list.add(obj);
    }

    protected boolean deleteObject(Object obj) {
        return list.remove(obj);
    }

    public String dump() {
        StringBuffer sb = new StringBuffer();
        list.forEach(sb::append);
        return sb.toString();
    }
}