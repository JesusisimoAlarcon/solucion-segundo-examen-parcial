package data.structure;

public class ListLinked<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public ListLinked() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public int size() {
        return size;
    }

    public ListLinked<E> addHead(E data) {
        Node<E> node = new Node<>(data);
        node.setLink(head);
        if (isEmpty()) {
            tail = node;
        }
        head = node;
        size++;
        return this;
    }

    public ListLinked<E> addTail(E data) {
        Node<E> node = new Node<>(data);
        if (isEmpty()) {
            head = node;
        } else {
            tail.setLink(node);
        }
        tail = node;
        size++;
        return this;
    }

    public ListLinked<E> add(E data) {
        Node<E> node = new Node<>(data);
        if (isEmpty()) {
            head = node;
        } else {
            tail.setLink(node);
        }
        tail = node;
        size++;
        return this;
    }

    public Object removeHead() throws Exception {
        if (isEmpty()) {
            throw new Exception("List empty, not deleted");
        } else if (size == 1) {
            tail = null;
        }
        Node<E> node = head;
        head = head.getLink();
        size--;
        return node.getData();
    }

    public Object removeTail() throws Exception {
        Node<E> node = tail;
        if (isEmpty()) {
            throw new Exception("List empty, not deleted");
        } else if (size == 1) {
            head = tail = null;
        } else {
            Node<E> temp = head;
            Node<E> preview = head;
            while (temp.getLink() != null) {
                preview = temp;
                temp = temp.getLink();
            }
            preview.setLink(null);
            tail = preview;
        }
        size--;
        return node.getData();
    }

    public String toString() {
        return "List={size={" + size + "},head={" + head + "},tail={" + tail + "}}";
    }

    /*
     * public String toString() { return "List={size={" + size + "},head={" + head +
     * "}}"; }
     */

}
