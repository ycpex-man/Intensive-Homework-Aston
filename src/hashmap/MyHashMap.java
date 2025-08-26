package hashmap;

public class MyHashMap<K, V> {
    private static final int INIT_CAPACITY = 16;
    private Node<K, V>[] table;
    private int size;

    public MyHashMap() {
        table = new Node[INIT_CAPACITY];
    }

    public void put(K key, V value) {
        if (size == table.length)
            resize();
        Node<K, V> newNode = new Node<>(key, value);
        int index = key.hashCode() & (table.length - 1);
        if (table[index] == null) {
            table[index] = newNode;
            size++;
        } else if (table[index].key.equals(newNode.key)) {
            table[index].value = newNode.value;
        } else {
            Node<K, V> currentNode = table[index];
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
            size++;
        }
    }

    public V get(K key) {
        int index = key.hashCode() & (table.length - 1);
        Node<K, V> currentNode = table[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public Node<K, V> remove(K key) {
        int index = key.hashCode() & (table.length - 1);
        Node<K, V> deleteNode = null;
        Node<K, V> prevNode = null;
        Node<K, V> currentNode = table[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                if (prevNode == null) {
                    table[index] = currentNode.next;
                } else {
                    prevNode.next = currentNode.next;
                }
                size--;
                deleteNode = currentNode;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        return deleteNode;
    }

    private void resize() {
        int newCapacity = table.length * 2;
        Node<K, V>[] newTable = new Node[newCapacity];
        for (Node<K, V> node : table) {
            while (node != null) {
                int index = node.hash & (newCapacity - 1);
                Node<K, V> nextNode = node.next;
                node.next = newTable[index];
                newTable[index] = node;
                node = nextNode;
            }
        }
        table = newTable;
    }

    public class Node<K, V> {
        protected int hash;
        protected K key;
        protected V value;
        protected Node<K, V> next;

        protected Node(K key, V value) {
            this.hash = key.hashCode();
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Key - " + key + "; " + "Value - " + value;
        }
    }
}
