package com.reactor.test;

public class MyLinkenList1 {
   private  int size;
   private Node head;
   public MyLinkenList1(){
       size=0;
       head=null;
   }
   private class Node{
       private Object data;
       private Node next;
       public Node(Object data){
           this.data=data;
       }

       @Override
       public String toString() {
           return "Node{" +
                   "data=" + data +
                   '}';
       }
   }
   public void addHead(Object o){
       if (o==null){
           throw new NullPointerException("空指针异常");
       }
       Node node=new Node(o);
       if (head==null){
           head=node;

       }else{
           node.next=head;
           head=node;

       }
       size++;
   }
   public void delete(Object o){
       if(size == 0){
           throw new NullPointerException("空指针异常");
       }
       if (o==null){
           throw new NullPointerException("空指针异常");
       }
       Node current=head;
       Node curr=head;
       while(current.data!=o){
           if (current.next==null){
               return;
           }else{
               curr=current;
               current=current.next;

           }

       }

       if (current==head){
           head=current.next;
       }else{
           curr.next=current.next;
       }
       size--;
   }

    public Node find(Object obj){
       int temp=size;
       Node current=head;
       while (temp>0){
           if (current.data==obj){
               return current;
           }else{
               current=current.next;
               temp--;
           }
       }
       return null;
    }
    public void display(){
       if (size>0){
           Node current=head;
           int temp=size;
           if (size==1){
               System.out.println(current.data);
           }
           while(temp>0){
               System.out.print(current.data+"-");
               current=current.next;
               temp--;

           }
           System.out.println();
       }
    }
}