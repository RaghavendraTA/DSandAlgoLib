package org.buildwithraghu.linearDS;

import java.util.*;

public class CollectionsSTL {

    private static Random random = new Random();

    public static void main(String[] args) {
        // Immutable list
        List<Integer> ls1 = List.of(9, 8, 7, 6, 5, 4, 3);
        try {
            ls1.set(1, 10);
        } catch (Exception e) {
            System.out.println("You cannot change immutable list");
        }

        // Immutable empty list
        List<Integer> ll = Collections.EMPTY_LIST;
        try {
            ll.add(1);
        } catch (Exception e) {
            System.out.println("You cannot change immutable list");
        }

        // This list can be altered
        List<Integer> ls2 = Arrays.asList(9, 2, 7, 3, 6, 4);
        // ls2.add(10); -> this will fail, only size is muted (possible to alter position or set value
        ls2.set(4, 1);

        Collections.swap(ls2, 2, 4);
        System.out.println(ls2);

        // Sorts in desc
        ls2.sort(Comparator.comparingInt(a -> -a));
        System.out.println(ls2);

        // Sorting in asc
        Collections.sort(ls2);
        System.out.println(ls2);

        // Print's index
        System.out.println(Collections.binarySearch(ls2, 2));

        // Creating single values collection
        Set<Character> set1 = Collections.singleton('A');
        List<Integer> list1 = Collections.singletonList(12);
        System.out.println(set1);
        System.out.println(list1);

        List<Integer> ls3 = new ArrayList<>(ls2);
        ls3.addAll(Arrays.asList(2, 2, 2));
        System.out.println(Collections.frequency(ls3, 2));

        // Randomizes the existing list
        Collections.shuffle(ls3);
        System.out.println(ls3);

        System.out.println("-------- Navigable DataStructure --------");
        // It only keeps unique values
        // allowed me to traverse the SortedSet
        NavigableSet<Integer> nset = new TreeSet<>(ls3);
        System.out.println(nset);
        System.out.println(nset.lower(9));

        NavigableMap<Integer, Integer> nmap = new TreeMap<>();
        ls3.forEach(x -> nmap.put(x, random.nextInt(0, 100)));
        System.out.println(nmap.lowerEntry(8));
    }
}
