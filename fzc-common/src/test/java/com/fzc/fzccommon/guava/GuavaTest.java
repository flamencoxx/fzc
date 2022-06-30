package com.fzc.fzccommon.guava;

import cn.hutool.core.lang.Console;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Flamenco.xxx
 */
public class GuavaTest {
    class Payment{
        private int id;
        private String name;
        private String address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Payment{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    private static Cache<Integer, Payment> paymentCache = CacheBuilder.newBuilder()
            .expireAfterWrite(100, TimeUnit.MINUTES)
            .build();

    @Test
    public void test(){
        int i = 1;
        Set<Payment> set = Sets.newHashSet();
        while(i < 20){
            Payment payment = new Payment();
            payment.setId(i);
            payment.setName(getRandomString(5));
            payment.setAddress(getRandomString(10));
            set.add(payment);
            i++;
        }
        set.forEach(k->{
            paymentCache.put(k.getId(),k);
        });
        Console.log(paymentCache.getIfPresent(1).toString());
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test1(){
        Integer i = 1;
        Console.log(paymentCache.getIfPresent(1).toString());
    }

    @Test
    public void test2(){
        String str = getRandomString(5);
        List<String> list = Lists.newLinkedList();
    }

    @Test
    public void test3(){
        List<String> list = Lists.newArrayList();
        Integer index = new Random().nextInt();
        Console.log(index);
        List<String> list1 = Lists.newArrayList();
        String str = "test";
        Console.log(str);
    }

    //检查大小写
    public static boolean isAllLowerCase(String str){
        return str.equals(str.toLowerCase());
    }

    //用逗号分割字符串返回list,并将大写转换成小写
    public static List<String> splitString(String str){
        List<String> list = Lists.newArrayList();
        String[] str1 = str.split(",");
        for(String s : str1){
            if(isAllLowerCase(s)){
                list.add(s);
            }else{
                list.add(s.toLowerCase());
            }
        }
        return list;
    }

    //检查是否是中国手机号码
    public static boolean isChinaPhoneNumber(String str){
        return str.matches("^1[3-9]\\d{9}$");
    }

    //二分查找一个由字符串组成的list
    public static int binarySearch(List<String> list,String str){
        int low = 0;
        int high = list.size() - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(list.get(mid).equals(str)){
                return mid;
            }else if(list.get(mid).compareTo(str) < 0){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return -1;
    }

    public List<String> getStrList(){
        List<String> list = Lists.newArrayList();
        return null;
    }

    //二分查找一个由字符串组成的list,返回该字符串
    public static String binarySearchStr(List<String> list,String str){
        int low = 0;
        int high = list.size() - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(list.get(mid).equals(str)){
                return list.get(mid);
            }else if(list.get(mid).compareTo(str) < 0){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return null;
    }


    @Test
    public  void test6(){
        List<String> list= Lists.newArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        int index = binarySearch(list,"c");
        Console.log(index);
    }


}

