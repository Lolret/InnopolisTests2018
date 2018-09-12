package lesson3.collections.inClass;

import lesson3.collections.home.ObjectBox;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class MathBox<T extends Number> extends ObjectBox {

    private TreeSet<T> sortedSet;

    public MathBox() {
    }

    public MathBox(T[] array)  {
        initCollection(array);
    }

    public void initCollection(T[] array) {
        TreeSet<T> set = new TreeSet<>(Arrays.asList(array));
        if (set.size() < array.length) try {
            throw new ArrayValuesNotUnique("values of array is not unique");
        } catch (ArrayValuesNotUnique arrayValuesNotUnique) {
            arrayValuesNotUnique.printStackTrace();
        }
        else this.sortedSet = set;
    }

    public Double summator() {
        // return sortedSet.stream().mapToDouble(x -> (double) x).sum();
        Double sum = 0d;
        for (T val : sortedSet) {
            sum += val.doubleValue();
        }
        return sum;
    }

    public Set<T> splitter(T div) {
        TreeSet<T> set = new TreeSet<>();
        for (T val: sortedSet) {
            Double val2 =  (val.doubleValue() / div.doubleValue());
            set.add((T) val2);
        }
        return set;
        // My lovely stream not withstand
        // the ticket "Класс MathBox необходимо доработать так, чтобы он работал не только с Integer,
        // но и с любым Number"
        // return sortedSet.stream().map(x -> (x / div).collect(Collectors.toSet());
    }

    public boolean delete(T val) {
        return sortedSet.remove(val);
    }

    public TreeSet<T> getSortedSet() {
        return sortedSet;
    }

    public void setSortedSet(TreeSet<T> sortedSet) {
        this.sortedSet = sortedSet;
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "sortedSet=" + sortedSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MathBox)) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(sortedSet, mathBox.sortedSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortedSet);
    }
}