package com.craft;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.*;
import io.vavr.control.Option;

import java.util.Comparator;

public class LinkedListApp {

    public static void main(String[] args) {

        liskedList();
        QueueTest();
        SortedSet();
        stream();
    }

    public static void liskedList(){
        // = List(1, 2, 3)
        List<Integer> list1 = List.of(1, 2, 3);
        // = List(0, 2, 3)
        List<Integer> list2 = list1.tail().prepend(0);
    }

    public static void QueueTest(){
        Queue<Integer> queue = Queue.of(1, 2, 3)
                .enqueue(4)
                .enqueue(5);

        System.out.println(queue);


        // = (1, Queue(2, 3))
        Tuple2<Integer, Queue<Integer>> dequeued =
                queue.dequeue();
        System.out.println(dequeued);

        // = Some((1, Queue()))
        Queue.of(1).dequeueOption();

        // = None
        Queue.empty().dequeueOption();


        // = Queue(1)
        Queue<Integer> queue2 = Queue.of(1);

        // = Some((1, Queue()))
        Option<Tuple2<Integer, Queue<Integer>>> dequeued1 =
                queue2.dequeueOption();

        // = Some(1)
        Option<Integer> element = dequeued1.map(Tuple2::_1);

        // = Some(Queue())
        Option<Queue<Integer>> remaining =
                dequeued1.map(Tuple2::_2);
    }

    public static void SortedSet(){
        // = TreeSet(1, 2, 3, 4, 6, 7, 8)
        SortedSet<Integer> xs = TreeSet.of(6, 1, 3, 2, 4, 7, 8);
        SortedSet<Integer> ys = xs.add(5);
        System.out.println(ys);

        // = TreeSet(1, 2, 3);
        SortedSet<Integer> set = TreeSet.of(2, 3, 1, 2);

        // = TreeSet(3, 2, 1);
        Comparator<Integer> c = (a, b) -> b - a;
        SortedSet<Integer> reversed = TreeSet.of(c, 2, 3, 1, 2);
        System.out.println(reversed);
    }

    public static void stream(){
        // = Stream("1", "2", "3") in Vavr
//        System.out.println();
        Stream.of(1, 2, 3).map(Object::toString).forEach(System.out::println);
        List.of("Java", "Advent").forEach(s -> {
            // side effects and mutation
            System.out.println(s);
        });

        // 2, 4, 6, ...
        Stream.from(1).filter(i -> i % 2 == 0);

    }

    String join(String... words) {
        return List.of(words)
                .intersperse(", ")
                .foldLeft(new StringBuilder(), StringBuilder::append)
                .toString();
    }

    public static void List(){
        // = HashMap((0, List(2, 4)), (1, List(1, 3)))
        List.of(1, 2, 3, 4).groupBy(i -> i % 2);

        // = List((a, 0), (b, 1), (c, 2))
        List.of('a', 'b', 'c').zipWithIndex();

        // = (1, "A")
        Tuple2<Integer, String> entry = Tuple.of(1, "A");

        Integer key = entry._1;
        String value = entry._2;
    }
}
