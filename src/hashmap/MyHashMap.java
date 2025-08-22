package hashmap;

import java.util.ArrayList;
import java.util.List;

public class MyHashMap<K, V> {
    private static final int INIT_CAPACITY = 16;
    private Node<K, V>[] table;
    private int size;

    public MyHashMap() {
        table = new Node[INIT_CAPACITY];
    }

    public void put(K key, V value) {
        // Если массив заполнен, увеличиваем его размер
        if (size == table.length)
            resize();
        Node<K, V> newNode = new Node<>(key, value);
        int index = key.hashCode() & (table.length - 1); // Вычисляем индекс
        // Если индекс пустой, добавляем новый узел
        if (table[index] == null) {
            table[index] = newNode;
            size++;
        // Если ключ уже существует, обновляем значение
        } else if (table[index].key.equals(newNode.key)) {
            table[index].value = newNode.value;
        // Если есть коллизия, добавляем узел в конец списка
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
        // Перебираем список
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value; // Возвращаем значение, если нашли подходящий ключ
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public Node<K, V> remove(K key) {
        int index = key.hashCode() & (table.length - 1);
        Node<K, V> deleteNode = null; // Для удалённого узла
        Node<K, V> prevNode = null; // Для предыдущего узла
        Node<K, V> currentNode = table[index]; // Для текущего узла
        // Перебираем список
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                if (prevNode == null){
                    table[index] = currentNode.next; // Удаляем, если первый в списке
                } else {
                    prevNode.next = currentNode.next; // Если в середине списка
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
        // Перебираем массив table и для каждого узла находим новый индекс, для нового массива newTable
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

    // Чисто для отладки
    public List<Node<K, V>> entrySet() {
        List<Node<K, V>> entries = new ArrayList<>();
        for (Node<K, V> node : table) {
            while (node != null) {
                entries.add(node);
                node = node.next;
            }
        }
        return entries;
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