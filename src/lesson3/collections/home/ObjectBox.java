package lesson3.collections.home;

import java.util.TreeSet;

public class ObjectBox {

    TreeSet<Object> sortedSet;

    public ObjectBox() {
        sortedSet = new TreeSet<>();
    }

    public ObjectBox(TreeSet<Object> sortedSet) {
        this.sortedSet = sortedSet;
    }

    protected void addObject(Object obj) {
        sortedSet.add(obj);
    }

    protected boolean deleteObject(Object obj) {
        return sortedSet.remove(obj);
    }

    public String dump() {
        StringBuffer sb = new StringBuffer();
        sortedSet.forEach(sb::append);
        return sb.toString();
    }
}
