package ua.ithillel.dsalgo;

import ua.ithillel.dsalgo.list.MyArrayList;
import ua.ithillel.dsalgo.list.MyList;
import ua.ithillel.dsalgo.list.MySinglyLinkedList;
import ua.ithillel.dsalgo.model.Person;
import ua.ithillel.dsalgo.model.Student;
import ua.ithillel.dsalgo.util.ArithmeticUtil;
import ua.ithillel.dsalgo.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;

public class Application {
    public static void main(String[] args) {



        final Student vasyl = new Student("Vasyl", 21, 8.9);
        final Student anna = new Student("Anna", 22, 4.6);
        final Student petro = new Student("Petro", 35, 9.8);
        final Student ivan = new Student("Ivan", 48, 7.5);
        final Student olha = new Student("Olha", 31, 3.2);
        final Student maxim = new Student("Maxim", 37, 8.9);

        Comparator<Student> gpaBest = (s1, s2) -> (int) (10 * (s2.getGpa() - s1.getGpa()));

        System.out.println("Best student: "
                + ArithmeticUtil.max(gpaBest,
                vasyl, anna, petro, ivan, olha, maxim));

        System.out.println("Best student: "
                + ArithmeticUtil.maxRec(gpaBest,
                vasyl, anna, petro, ivan, olha, maxim));


        System.out.println("Oldest student: "
                + ArithmeticUtil.max(vasyl, anna, petro, ivan, olha, maxim));

        System.out.println(ArithmeticUtil.maxInt(3, -9, 2, 0, 1, 10));
        System.out.println(ArithmeticUtil.maxIntRec(3, -9, 2, 0, 1, 10));

        System.out.println(StringUtils.reverseRecurse("Hello"));
        System.out.println(StringUtils.reverseRecurse("s"));
        System.out.println(StringUtils.reverseRecurse("World!"));

        // 0! = 1
        // 1! = 1 * 0!
        // 2! = 2 * 1!
        // 3! = 3 * 2! = 3 * 2 * 1 = 6
        // 4 = 4 * 3 * 2 * 1 = 24
        System.out.println(ArithmeticUtil.factorial(0));
        System.out.println(ArithmeticUtil.factorial(1));
        System.out.println(ArithmeticUtil.factorial(2));
        System.out.println(ArithmeticUtil.factorial(3));
        System.out.println(ArithmeticUtil.factorial(4));





        // Double-Ended Queue - DEQ - deck - Deque
        Deque<Student> studentsDeque = new LinkedList<>();
        studentsDeque.add(anna);
//        studentsDeque.add(petro);
        studentsDeque.addLast(petro);
        studentsDeque.add(ivan);
        studentsDeque.addFirst(vasyl);
        studentsDeque.add(olha);
        studentsDeque.add(maxim);

//        Comparator<Student> gpaComp = Comparator.comparing(Student::getGpa);
        Comparator<Student> gpaComp = (s1, s2) -> (int) (s2.getGpa() - s1.getGpa());

//        Queue<Student> studentsQueue = new LinkedList<>();
        Queue<Student> studentsQueue = new PriorityQueue<>(gpaComp);
        studentsQueue.add(vasyl);
        studentsQueue.add(anna);
        studentsQueue.add(petro);
        studentsQueue.add(ivan);
        studentsQueue.add(olha);
        studentsQueue.add(maxim);

        //              Vasyl
        //          Anna    Petro

        // [Student{name='Vasyl', age=21, gpa=8.9},
        // Student{name='Ivan', age=48, gpa=7.5},
        // Student{name='Petro', age=35, gpa=9.8},
        // Student{name='Anna', age=22, gpa=4.6},
        // Student{name='Olha', age=31, gpa=3.2},
        // Student{name='Maxim', age=37, gpa=8.9}]

        System.out.println(studentsQueue);

        final Student firstStudent = studentsQueue.remove();
        final Student secondStudent = studentsQueue.poll();

        while (!studentsQueue.isEmpty()) {
            final Student element = studentsQueue.element();
            final Student peek = studentsQueue.peek();
            System.out.println(studentsQueue.remove());
        }




        Comparator<Student> nameComp = (s1, s2) -> {
            return s1.getName().compareTo(s2.getName());
        };



//        SortedSet<Student> sortedStudents = new TreeSet<>();
        NavigableSet<Student> sortedStudents = new TreeSet<>(nameComp);
        sortedStudents.add(vasyl);
        sortedStudents.add(anna);
        sortedStudents.add(petro);
        sortedStudents.add(ivan);
        sortedStudents.add(olha);
        sortedStudents.add(maxim);

        System.out.println("Sorted: " + sortedStudents);

        System.out.println("From Anna to Max (exl)" + sortedStudents.subSet(anna, maxim));
        System.out.println("After Anna" + sortedStudents.tailSet(anna));
        System.out.println("Before Anna" + sortedStudents.headSet(anna));




        Set<Student> javaStudents = new HashSet<>();
        javaStudents.add(vasyl);
        javaStudents.add(anna);
        javaStudents.add(petro);
        javaStudents.add(ivan);
        javaStudents.add(olha);



        for (Student s :
                javaStudents) {
            System.out.println(s);
        }

        Set<Student> frontEndStudents = Set.of(anna, petro, maxim);

        System.out.println("Java students: " + javaStudents);
        System.out.println("FE students: " + frontEndStudents);

//        Set<Student> allStudents = new HashSet<>();
        Set<Student> allStudents = new LinkedHashSet<>();

        allStudents.addAll(javaStudents);
        allStudents.addAll(frontEndStudents);

        System.out.println("All students: " + allStudents);

        Set<Student> javaAndFeStudents = new HashSet<>();
        javaAndFeStudents.addAll(javaStudents);
        javaAndFeStudents.retainAll(frontEndStudents);
//        javaAndFeStudents.retainAll(javaStudents);

        System.out.println("JAVA & FE students: " + javaAndFeStudents);

        System.out.println();



//        Comparator<Person> nameComparator = (p1, p2) -> {
//          return p1.getName().compareTo(p2.getName());
//        };
//
//        Map<Person, String> personStrMap2 = new LinkedHashMap<>();
//        Map<Person, String> personStrMap1 = new TreeMap<>(); // cool
//        MyMap<Person, String> personStrMap = new MyTreeMap<>(); // sucks

//        final Person petro = new Person(23, "Petro");
//        personStrMap.put(petro, "Hello");
//        personStrMap.put(new Person(27, "Inna"), "Hello");
//        personStrMap.put(new Person(34, "Vasyl"), "Hello");
//        personStrMap.put(new Person(42, "Fedir"), "Hello");
//        personStrMap.put(new Person(26, "John"), "fwfe");
//        personStrMap.put(new Person(12, "Jane"), "fwfe");
//
//        personStrMap.remove(petro);

//        MyMap<Integer, String> intStrMap = new MyTreeMap<>();
//        intStrMap.put(13, "Hello");
//        intStrMap.put(8, "sffsd");
//        intStrMap.put(17, "aaaaa");
//        intStrMap.put(11, "bbbbbb");
//        intStrMap.put(1, "dddd");
//
//        final String s1 = intStrMap.get(8);
//        final String s2 = intStrMap.get(13);
//
//
//        Map<String, Integer> cityPopulation = new TreeMap<>();
//        cityPopulation.put("Kyiv", 3_000_000);
//        cityPopulation.put("Odessa", 1_100_000);
//        cityPopulation.put("Tokyo", 13_000_000);
//        cityPopulation.put("New York", 9_000_000);
//        cityPopulation.put("Cairo", 9500_000);
//        cityPopulation.put("Mexico-City", 800_000);
//        cityPopulation.put("Karachi", 15_000_000);
//        cityPopulation.put("Lviv", 700_000);
//
//        for(var key: cityPopulation.keySet()) {
//            System.out.println(key + ": " +cityPopulation.get(key));
//        }
//        System.out.println();
//
//        cityPopulation.put("City", 100_000);
//        cityPopulation.put("City1", 100_000);
//        cityPopulation.put("City2", 100_000);
//        cityPopulation.put("City3", 100_000);
//        cityPopulation.put("City4", 100_000);
//        for(var key: cityPopulation.keySet()) {
//            System.out.println(key + ": " +cityPopulation.get(key));
//        }

//        MyMap<String, Integer> cityPopulation = new MyHashMap<>();
//        Map<String, Integer> cityPopulation = new HashMap<>();
//        cityPopulation.put("Kyiv", 3_000_000);
//        cityPopulation.put("Odessa", 1_100_000);
//        cityPopulation.put("Tokyo", 13_000_000);
//        cityPopulation.put("New York", 9_000_000);
//        cityPopulation.put("Cairo", 9500_000);
//        cityPopulation.put("Mexico-City", 800_000);
//        cityPopulation.put("Karachi", 15_000_000);
//        cityPopulation.put("Lviv", 700_000);
//
//        for(var key: cityPopulation.keySet()) {
//            System.out.println(key + ": " +cityPopulation.get(key));
//        }
//        System.out.println();
//
//        cityPopulation.put("City", 100_000);
//        cityPopulation.put("City1", 100_000);
//        cityPopulation.put("City2", 100_000);
//        cityPopulation.put("City3", 100_000);
//        cityPopulation.put("City4", 100_000);
//        for(var key: cityPopulation.keySet()) {
//            System.out.println(key + ": " +cityPopulation.get(key));
//        }
//
//        final Integer odessa = cityPopulation.get("Odessa");
//        final Integer newYork = cityPopulation.get("New York");
//        final Integer lviv = cityPopulation.get("Lviv");


//        Map<String, Integer> cityPopulation = new HashMap<>();
//        cityPopulation.put("Kyiv", 3_000_000);
//        cityPopulation.put("Odessa", 1_100_000);
//        cityPopulation.put("Tokyo", 13_000_000);
//        cityPopulation.put("New York", 9_000_000);
//
//        final Integer odessa = cityPopulation.get("Odessa");
//
//        final Integer kyiv = cityPopulation.get("Kyiv");
//        final Integer miamy = cityPopulation.get("Miamy");
//        cityPopulation.put("Odessa", 1_020_000);
//        final Integer odessa1 = cityPopulation.get("Odessa");


        List<Person> personList = new ArrayList<>();

//        final Person ivan = new Person(30, "Ivan");
//        final Person olha = new Person(28, "Olha");
//
//        personList.add(ivan);
//        personList.add(olha);

        System.out.println(personList.contains(olha));
        System.out.println(personList.contains(new Person(28, "Olha")));


        List<String> strList = new LinkedList<>();
        // ...

        for (int i = 0; i < strList.size(); i++) {
            System.out.println(strList.get(i));
        }

        for (String s :
                strList) {
            System.out.println(s);
        }







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
