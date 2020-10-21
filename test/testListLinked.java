package test;

import data.structure.ListLinked;

public class testListLinked {
    public static void testAddsHeadsList() {
        ListLinked<Integer> list = new ListLinked<>();
        list.addHead(5);
        list.addHead(15);
        list.addHead(3);
        list.addHead(4);
        list.addHead(-2);
        list.addHead(55);
        list.addHead(6);
        list.addHead(8);
        list.addHead(0);
        System.out.println(list);
    }

    public static void testAddsTailsList() {
        ListLinked<Integer> list = new ListLinked<>();
        list.addTail(15);
        list.addTail(5);
        list.addTail(3);
        list.addTail(4);
        list.addTail(-2);
        list.addTail(55);
        list.addTail(6);
        list.addTail(8);
        list.addTail(0);
        System.out.println(list);
    }

    public static void testAddsTailsListString() {
        ListLinked<String> list = new ListLinked<>();
        list.addTail("Jesus");
        list.addTail("Indiana");
        list.addTail("Shadria");
        list.addTail("Dilema");
        list.addTail("Mary Cruz");
        list.addTail("Soledad");
        list.addTail("Janneth");
        list.addTail("Gaby");
        list.addTail("Marcela");
        System.out.println(list);
    }

    public static void testRemoveHeadList() {
        try {
            ListLinked<Integer> list = new ListLinked<>();
            list.addTail(15);
            list.addTail(5);
            list.addTail(3);
            list.addTail(4);

            System.out.println(list);

            list.removeHead();
            System.out.println(list);
            list.removeHead();
            System.out.println(list);
            list.removeHead();
            System.out.println(list);
            list.removeHead();
            System.out.println(list);
            list.removeHead();
            System.out.println(list);
            list.removeHead();
            System.out.println(list);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void testRemoveTailList() {
        try {
            ListLinked<Integer> list = new ListLinked<>();
            list.addTail(15);
            list.addTail(5);
            list.addTail(3);
            list.addTail(4);

            System.out.println(list);

            list.removeTail();
            System.out.println(list);
            list.removeTail();
            System.out.println(list);
            list.removeTail();
            System.out.println(list);
            list.removeTail();
            System.out.println(list);
            list.removeTail();
            System.out.println(list);
            list.removeTail();
            System.out.println(list);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        // testAddsHeadsList();
        // testAddsTailsList();
        // testRemoveHeadList();
        // testRemoveTailList();
        testAddsTailsListString();
    }
}
