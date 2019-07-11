package com.reactor.test;


public class TwoWayLinkedList<E> {
    transient Node<E> head;//表示链表头
    transient Node<E> tail;//表示链表尾
    transient int size;//表示链表的节点个数

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev,E data,Node<E> next) {
            this.prev=prev;
            this.data = data;
            this.next=next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    ", prev=" + prev +
                    '}';
        }
    }

    public TwoWayLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    //在链表头增加节点
    public void addHead(E value) {
        final Node<E> f = head;
        final Node<E> newNode = new Node(null, value, f);
        head = newNode;
        if (f == null) {
            tail = newNode;
        } else {
            f.prev = newNode;
        }
        size++;

      //  Node newNode = new Node(value);
       // Node node = head;
        //if (size == 0) {
        //    head = newNode;
        //    tail = newNode;
        //} else {
         //   head.prev = newNode;
         //   newNode.next = head;
       //     head = newNode;
       // }
      //  size++;
    }
     public E get(int index){

         if (index < (size >> 1)) {
             Node<E> x = head;
             for (int i = 0; i < index; i++) {
                 x = x.next;
             }
             return x.data;
         } else {
             Node<E> x = tail;
             for (int i = size - 1; i > index; i--) {
                 x = x.prev;
             }
             return x.data;
         }
     }
    //在链表尾增加节点
    public void addTail(E value) {
        final Node<E> l = tail;
        final Node<E> newNode = new Node(l, value, null);
        tail = newNode;
        if (l == null) {
            head = newNode;
        }else {
            l.next = newNode;
        }
        size++;
      /*  Node newNode = new Node(value);
        //    Node node=newNode;
        if (size == 0) {
            head = newNode;
            tail = newNode;

            size++;
        } else {
            tail.next = newNode;
            newNode.prev = tail;

            tail = newNode;
            size++;
        }*/
    }

    //删除链表头
    public void deleteHead() {
        Node temp = head;
        if (size != 0) {
            head = head.next;
            head.prev = null;
            size--;
        }

    }

    //删除链表尾
    public Node deleteTail() {
        Node temp = tail;
        if (size != 0) {
            tail = tail.prev;
            tail.next = null;
            size--;
        }
        return temp;
    }

    //获得链表的节点个数
    public int getSize() {
        return size;
    }

    //判断链表是否为空
    public boolean isEmpty() {
        return (size == 0);
    }

    //显示节点信息
    public void display() {
        if (size > 0) {
            Node node = head;
            int tempSize = size;
            if (tempSize == 1) {//当前链表只有一个节点
                System.out.println("[" + node.data + "]");
                return;
            }
            while (tempSize > 0) {
                if (node.equals(head)) {
                    System.out.print("[" + node.data + "->");
                } else if (node.next == null) {
                    System.out.print(node.data + "]");
                } else {
                    System.out.print(node.data + "->");
                }
                node = node.next;
                tempSize--;
            }
            System.out.println();
        } else {//如果链表一个节点都没有，直接打印[]
            System.out.println("[]");
        }

    }

    public int findOnHead(Object o) {
        int index = 0;
        if (o == null) {
            for (Node x = head; x != null; x = x.next) {
                if (x.data == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node x = head; x != null; x = x.next) {
                if (o.equals(x.data)){
                    return index;
                }
                index++;

            }
        }
       return -1;
    }

    public Node findOnTail(Object o) {
        if (tail == null) {
            return null;
        }
        Node node = tail;
        while (node != null) {
            if (node.data == o) {
                return node;
            }
            node = node.prev;
        }
        return null;
    }
}