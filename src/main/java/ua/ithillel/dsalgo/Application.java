package ua.ithillel.dsalgo;

import ua.ithillel.dsalgo.container.Container;
import ua.ithillel.dsalgo.container.NumberContainer;
import ua.ithillel.dsalgo.container.NumberUtils;
import ua.ithillel.dsalgo.func.MyFunc;
import ua.ithillel.dsalgo.graph.GraphUtils;
import ua.ithillel.dsalgo.list.MyArrayList;
import ua.ithillel.dsalgo.list.MyList;
import ua.ithillel.dsalgo.list.MySinglyLinkedList;
import ua.ithillel.dsalgo.model.Graduate;
import ua.ithillel.dsalgo.model.Person;
import ua.ithillel.dsalgo.model.Student;
import ua.ithillel.dsalgo.tree.TreeNode;
import ua.ithillel.dsalgo.tree.TreeUtils;
import ua.ithillel.dsalgo.util.ArithmeticUtil;
import ua.ithillel.dsalgo.util.SearchUtils;
import ua.ithillel.dsalgo.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    private static void printString(Container<String> container) throws Exception {
        final String value = container.getValue();
        System.out.println(value);
    }

    private static void addStudentsToList(List<? super Student> s) {
        s.add(new Student("John", 40));
        s.add(new Graduate("John", 40));
    }

    private static <T> T convertStudent(Student student, Function<Student, T> converter) {
        return converter.apply(student);
    }

    private static List<Student> filterStudents(List<Student> students, Predicate<Student> condition) {
        List<Student> result =  new ArrayList<>();

        for (Student s :
                students) {
            if (condition.test(s)) {
                result.add(s);
            }
        }

        return result;
    }

    private static void printStudent(Student s) {
        System.out.println(s);
    }

    public static void main(String[] args) throws Exception {
        final Student vasyl = new Student("Vasyl", 21, 8.9);
        final Student anna = new Student("Anna", 22, 4.6);
        final Student petro = new Student("Petro", 35, 9.8);
        final Student ivan = new Student("Ivan", 48, 7.5);
        final Student olha = new Student("Olha", 31, 3.2);
        final Student maxim = new Student("Maxim", 37, 8.9);


        final Stream<Student> studentStream = Stream.of(vasyl, anna, petro, ivan, olha, maxim);

        Function<Student, Person> studPersonConverter = student -> {
            return new Person(student.getAge(), student.getName());
        };

        final List<Person> persons = studentStream.filter(s -> s.getGpa() > 5)
                .peek(s -> System.out.println(s.getName()))
                .map(studPersonConverter)
                .peek(System.out::println)
                .toList();

        final List<Student> studs = List.of(vasyl, anna, petro, ivan, olha, maxim);

        final long count = studs.stream().filter(s -> s.getGpa() > 5)
                .peek(s -> System.out.println(s.getName()))
                .map(studPersonConverter)
                .peek(System.out::println)
                .count();

        final Optional<Person> max = studs.stream().filter(s -> s.getGpa() > 5)
                .peek(s -> System.out.println(s.getName()))
                .map(studPersonConverter)
                .peek(System.out::println)
                .max((s1, s2) -> s1.getAge() - s2.getAge());

        final Person person = max.orElseThrow(() -> new Exception());

        final Map<Double, Student> studentsMappedByGpa = studs.stream().filter(s -> s.getGpa() > 5)
                .peek(s -> System.out.println(s.getName()))
                .peek(System.out::println)
                .collect(Collectors.toMap(student -> student.getGpa(),
                        student -> student,
                        (s1, s2) -> s1));

        System.out.println(persons);

        final Double gpaSum = studs.stream().filter(s -> s.getGpa() > 5)
                .peek(s -> System.out.println(s.getName()))
                .peek(System.out::println)
                .reduce(0.0, (acc, student) -> acc + student.getGpa(),
                        (sum, student) -> sum);

        final Stream<Student> parallel = studs.stream().parallel();


        final List<Double> list1 =
                Stream.generate(Math::random).limit(100).filter(v -> v > 0.2).toList();

        final List<Double> list2 =
                Stream.generate(() -> {
                    final double random = Math.random();
                    System.out.println("generating : " + random);
                    return random;
                }).limit(20).filter(v -> {
                    System.out.println("Filtering: " + v);
                    return v > 0.2;
                } ).toList();


//        Consumer<Student> studentPrinter = student -> System.out.println(student);
//        Consumer<Student> studentPrinter = System.out::println;
        Consumer<Student> studentPrinter = Application::printStudent;
        studentPrinter.accept(vasyl);

        Supplier<Student> emptyStudentGenerator = Student::new;

        final Student student1 = emptyStudentGenerator.get();

//        final List<Student> studs = List.of(vasyl, anna, petro, ivan, olha, maxim);

        Predicate<Student> bestCondition = s -> s.getGpa() > 8;

        final List<Student> bestStudents = filterStudents(studs, bestCondition);
        final List<Student> worstStudents = filterStudents(studs, s -> s.getGpa() < 5);




        Function<Student, Person> studPersonConverter2 = student
                -> new Person(student.getAge(), student.getName());

        final Person john = convertStudent(new Student("John", 32), studPersonConverter);


        MyFunc myFunc = () -> System.out.println("do smth"); // reference to an bject

        myFunc.doSmth();
        myFunc.doSmthWithInt(3);

        // in out
        List<Object> objects = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        addStudentsToList(objects);
        addStudentsToList(students);

        final Container<Integer> intContainer = new Container<>(12);
        final var stringContainer = new Container<String>("Hello");

        final Container<Student> studentContainer = new Container<Student>(new Student("", 34));
        Container container = studentContainer;

        NumberContainer<Integer> intCont = new NumberContainer<>(34);
//        intCont.setValue(23.4);
        NumberContainer<? extends Number> numContainer = new NumberContainer<>(12.0);


        NumberContainer<Double> doubleCont = new NumberContainer<>(34.0);


        Container<? super Integer> intOrObjContainer = new Container<>(new Object());

        // class A {}
        // class B extends A {}
        // A a = new B(); // inheritance
        // B b = new A();

//        int i1 = 10;
//        final Integer i6 = Integer.valueOf(i1);
//        Object i = i6;
//        Object i = 10; // auto boxing
//
//        Integer i2 = 12;
//        int i3 = i2; // auto unboxing

//        final Object value = intContainer.getValue();
//        final Object str = stringContainer.getValue();
//        final Object studValue = student.getValue();
        final Integer value = intContainer.getValue();
        final String value1 = stringContainer.getValue();

        System.out.println(intContainer instanceof Container);
        System.out.println(stringContainer instanceof Container);


        Integer[][] edgeList = new Integer[][] {
                {0, 1},
                {1, 2},
                {1, 4},
                {2, 5},
                {4, 5},
                {4, 3},
                {5, 8},
                {5, 6},
                {6, 7}
        };

        final Map<Integer, List<Integer>> integerListMap = GraphUtils.edgeListToAdjacency(edgeList);

        System.out.println(GraphUtils.depthFirst(integerListMap, 0));


        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B", "D", "E"));
        graph.put("B", List.of("D", "C"));
        graph.put("C", List.of());
        graph.put("D", List.of("C", "E", "A"));
        graph.put("E", List.of());

        System.out.println("A: " + GraphUtils.depthFirst(graph, "A"));
        System.out.println("B: " + GraphUtils.depthFirst(graph, "B"));
        System.out.println("C: " + GraphUtils.depthFirst(graph, "C"));
        System.out.println("E: " + GraphUtils.depthFirst(graph, "E"));




        final TreeNode<String> a = new TreeNode<>("A");
        final TreeNode<String> b = new TreeNode<>("B");
        final TreeNode<String> c = new TreeNode<>("C");
        final TreeNode<String> d = new TreeNode<>("D");
        final TreeNode<String> e = new TreeNode<>("E");
        final TreeNode<String> f = new TreeNode<>("F");

        a.setLeft(b);
        a.setRight(c);

        b.setLeft(d);

        c.setLeft(e);
        c.setRight(f);

        final List<String> strings = TreeUtils.depthFirst(a);
        final List<String> stringsRec = TreeUtils.depthFirstRec(a);
        final List<String> strings2 = TreeUtils.breadthFirst(a);

        for (String s :
                a) {
            System.out.printf("%s ", s);
        }

        int[] intArr = {1, 4, 5,7 ,8, 10};
        final int i1 = SearchUtils.binarySearch(intArr, 7, 0, intArr.length - 1);
        final int i2 = SearchUtils.binarySearch(intArr, 1, 0, intArr.length - 1);
        final int i3 = SearchUtils.binarySearch(intArr, 10, 0, intArr.length - 1);
        final int i4 = SearchUtils.binarySearch(intArr, 15, 0, intArr.length - 1);
        final int i5 = SearchUtils.binarySearch(intArr, 0, 0, intArr.length - 1);




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
