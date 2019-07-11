package com.reactor.test;

import java.util.LinkedList;
import java.util.List;

public class testTwo {
    public static void main(String[] args) {
        System.out.println(18>>1);
        TwoWayLinkedList<String> singleList = new TwoWayLinkedList();
      singleList.addHead("A");

        singleList.addHead("B");
        singleList.addHead("C");
        singleList.addHead("D");
        singleList.addTail("E");
        singleList.addTail("F");

        singleList.addTail("G");
        //打印当前链表信息
        singleList.display();
        //删除C


       System.out.println(singleList.findOnHead("C"));
       System.out.println(singleList.get(0));
    //    System.out.println(singleList.findOnTail("C"));
        singleList.deleteHead();
        singleList.display();
        //查找B
        System.out.println(singleList.get(0));
       // System.out.println(singleList.find("B"));

    }
}
