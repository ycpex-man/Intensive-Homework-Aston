import hashmap.MyHashMap;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Oleg", 1);
        map.put("Anton", 2);
        map.put("Alex", 3);
        map.put("Dug", 4);
        map.put("Mag", 5);
        map.put("UUUUU", 6);
        map.put("GGGG", 7);
        map.put("VVVB", 8);
        map.put("DEF", 9);
        map.put("ERT", 10);
        map.put("QQWEER", 11);
        map.put("{PFKE{A}", 12);
        map.put("Etrrty", 13);
        map.put("Ecnv xvn ", 14);
        map.put("Ewhbwwadvwv", 15);
        map.put("Ebagertrtnhrwnt", 16);
        map.put("Eweeeeee", 17);
        map.put("Oleg", 228);
        
        System.out.println(map.remove("Oleg"));
        System.out.println(map.remove("Anton"));
        System.out.println(map.remove("Alex"));
        System.out.println(map.remove("Mag"));
        System.out.println(map.remove("UUUUU"));
        System.out.println(map.remove("GGGG"));


        for (MyHashMap<String, Integer>.Node<String, Integer> entry : map.entrySet()){
            System.out.println(entry);
        }



    }
}