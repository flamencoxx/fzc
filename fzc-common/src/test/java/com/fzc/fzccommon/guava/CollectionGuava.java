package com.fzc.fzccommon.guava;

import com.google.common.collect.*;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;

@SpringBootTest
public class CollectionGuava {

    @Test
    public void ImmutableSetTestingTest() throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("list");
        list.add("list1");
        list.add("list2");
        list.add("list");
        HashSet<String> set = new HashSet<String>();
        set.add("a");
        set.add("b");
        set.add("wxw");
        set.add("kaka");
        set.add("lal");

        final ImmutableSet<String> set1 = ImmutableSet.of("i am", "fzc", "haha");

        ImmutableList<String> set3 = ImmutableList.copyOf(list);

        ImmutableSortedSet<String> set4 = ImmutableSortedSet.copyOf(set);

        ImmutableSet<String> set2 = ImmutableSet.copyOf(set);
        System.out.println(set1);
        System.out.println(set2);
        System.out.println(set3);
        for(int i = 0;i < set4.size();i++){
            System.out.println(set4.asList().get(i));
        }
    }


    @Test
    public void findWordsCount() {
        String words = "hello,my friends i am wxw hello i";
        String[] wordArray = words.split(" ");
        Map<String,Integer> map = new HashMap<String,Integer>();
        for(String s:wordArray){
            Integer count = map.get(s);
            if(count == null){
                map.put(s,1);
            }else{
                map.put(s,count + 1);
            }
        }
        System.out.println(map);
    }

    @Test
    public void newCollectionType_MultiSet(){
        String words = "hello my friends i am wxw hello i";
        String[] wordArray = words.split(" ");
        List<String> list = Arrays.asList(wordArray);
        Multiset<String> set = HashMultiset.create();
        set.addAll(list);
//        System.out.println(set.count("i"));
//        System.out.println(set.elementSet());

        List<String> l = new ArrayList<String>();
//        set.forEach((s)->{
//            System.out.println(s);
//            l.add(s);
//        });
//        Set<Multiset.Entry<String>> k = set.entrySet();
//
//        k.forEach((s)->{
//           System.out.println(s.getElement() +"--count:   "+ s.getCount());
//        });

        for(String s: set){
            System.out.println(s+"-count:"+set.count(s));
        }

//        set.forEachEntry((s)->{
//            System.out.println(s.);
//        });
        
//        System.out.println(set.contains("i"));

//        Iterator<String> it = set.iterator();
//        while(it.hasNext()){
//            System.out.println("element : " + it);
//        }
    }

    @Test
    public void newCollectionType_MultiMap(){


        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1,"fzc");
        map.put(2,"wxw");
        map.put(3,"ha");
        SetMultimap<String,String> multiMap = MultimapBuilder.hashKeys().hashSetValues().build();
        multiMap.put("fzc","ibm");
        multiMap.put("fzc","apple");
        multiMap.put("fzc","tsm");
        multiMap.put("wxw","stock");
        multiMap.put("wxw","house");
        multiMap.put("wxw","swarm");
        System.out.println(multiMap.size());


        List<String> list = new ArrayList<String>();
        list.add("list1");
        list.add("list2");
        list.add("list3");
        multiMap.putAll("list",list);

        Set<String> set = new HashSet<String>();
        set.add("fzc");
        set.add("pol");
        set.add("kafka");
        multiMap.putAll("test",set);
        System.out.println(multiMap);


        ListMultimap<String,String> multiList = MultimapBuilder.treeKeys().arrayListValues().build();
        multiList.putAll("fzc",list);
        multiList.put("wxw","1");
        multiList.put("wxw","2");
        multiList.put("wxw","3");
        multiList.put("wxw","4");
        System.out.println(multiList);
        System.out.println(multiList.asMap());
        System.out.println(multiList.keySet());
        System.out.println(multiList.values());
        System.out.println(multiList.get("wxw"));

//        Iterables it = new Iterables();
//        Map<String, List<String>> tes = multiList.asMap().values();
    }

}
