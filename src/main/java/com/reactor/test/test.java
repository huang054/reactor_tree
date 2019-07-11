package com.reactor.test;



public class test {
    public static void main(String[] args) {
        MyLinkenList1 singleList = new MyLinkenList1();
        singleList.addHead("A");
        singleList.addHead("B");
        singleList.addHead("C");
        singleList.addHead("D");
        //打印当前链表信息
        singleList.display();
        //删除C
        singleList.delete("A");
        singleList.display();
        //查找B
        System.out.println(singleList.find("B"));
        singleList.display();
    }

}
