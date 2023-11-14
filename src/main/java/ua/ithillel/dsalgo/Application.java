package ua.ithillel.dsalgo;

import ua.ithillel.dsalgo.list.MyArrayList;
import ua.ithillel.dsalgo.list.MyList;
import ua.ithillel.dsalgo.list.MySinglyLinkedList;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;

public class Application {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue = new PriorityQueue<>();
        queue = new LinkedBlockingQueue<>();

        new ConcurrentSkipListSet<>();



        Stack<String> stack = new Stack<>();

        stack.push("Hello");
        stack.push("how are you");
        stack.push("Hillel");

        final String pop = stack.pop();
        final String peek = stack.peek();



        MyList<Integer> myLinkedList = new MySinglyLinkedList<>();

        myLinkedList.add(1);
        myLinkedList.add(4);
        myLinkedList.add(6);
        myLinkedList.add(8);

        for (Integer i : myLinkedList) {
            System.out.println("i: " + i);
        }

        final Integer remove = myLinkedList.remove(2);


        MyList<Integer> myInts = new MyArrayList<>();

        myInts.add(1);
        myInts.add(1);
        myInts.add(6);
        myInts.add(5);
        myInts.add(1);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);
        myInts.add(10);

        final Iterator<Integer> intIterator = myInts.iterator();
        while (intIterator.hasNext()) {
            System.out.println(intIterator.next());
        }

        // only Iterable
        for (Integer i :myInts) {
            System.out.println("i: " + i);
        }


        List<String> list = new ArrayList<>(100); // generic
        list = new LinkedList<>();

        list.add("Hello");
        list.add("Hillel");

        final String s = list.get(1);
        final int size = list.size();


        int[] arr = new int[3];
        arr = new int[]{1, 2};
        int i = arr[1];
//        arr[2] = 3; // ArrayOutOfBoundException
        arr = new int[10];
        String[] strArr = null;

        int[][] intMatrix = {

        };

        intMatrix = new int[4][4];

        intMatrix = new int[4][];
        intMatrix[0] = new int[1];
        intMatrix[1] = new int[]{2, 3};
        intMatrix[2] = new int[] {1, 2};



        System.out.println(intMatrix.length);
        System.out.println(Arrays.toString(intMatrix));
    }
}
