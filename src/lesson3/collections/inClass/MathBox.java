package lesson3.collections.inClass;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MathBox {

    private TreeSet<Integer> sortedSet;

    public MathBox() {
    }

    public MathBox(Integer[] array)  {
        initCollection(array);
    }

    public void initCollection(Integer[] array) {
        TreeSet<Integer> set = new TreeSet<>(Arrays.asList(array));
        if (set.size() < array.length) try {
            throw new ArrayValuesNotUnique("values of array is not unique");
        } catch (ArrayValuesNotUnique arrayValuesNotUnique) {
            arrayValuesNotUnique.printStackTrace();
        }
        else this.sortedSet = set;
    }

    public Integer summator() {
        return sortedSet.stream().mapToInt(x -> x).sum();
    }

    public Set<Integer> splitter(Integer div) {
        return sortedSet.stream().map(x -> x / div).collect(Collectors.toSet());
    }

    public boolean delete( Integer val) {
        return sortedSet.remove(val);
    }

    public TreeSet<Integer> getSortedSet() {
        return sortedSet;
    }

    public void setSortedSet(TreeSet<Integer> sortedSet) {
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