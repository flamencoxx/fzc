package com.fzc.fzccommon.Test;

import cn.hutool.core.lang.Console;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author flamenco.xxx
 * @date 2021/12/21 14:55
 */
public class StringUtilTest {

    @Test
    public void test1(){
        String str = "";
        String str1 = "fzc+";
        String str2 = "wxw";
        String res = StringUtils.join(str1,str2);
        boolean bool = StringUtils.isNotBlank(str);
        Console.log(bool);
        Console.log(res);
    }

    @Test
    public void test2(){
        String str1 = null;
        boolean bool = ObjectUtils.equals(str1,null);
        Console.log(bool);
    }

    @Test
    public void test3(){
        String[] args = {"fzc", null, null, "wxw", "ok", null};
        String bool = ObjectUtils.firstNonNull(args);
        Console.log(bool);
        List<String> list = Arrays.stream(args).filter(Objects::nonNull).collect(Collectors.toList());
        list.forEach( k ->{
            Console.log(k);
        });

    }

    public class Node{
        private String name;
        private List<Node> children;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }
    }

    @Test
    public void test4(){
        Node node = new Node();
        node.setName("fzc");
        Node node1 = new Node();
        Node node2 = new Node();
        node1.setName("wxw");
        node2.setName("soul");

        String[] strArray = {"fzc","wxw","kafka","emp",null};
        List<String> list = Lists.newArrayList(strArray);
    }
}
