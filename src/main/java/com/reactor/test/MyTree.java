package com.reactor.test;

public class MyTree implements Tree{
    protected Node root;
    public Node find(int key) {
        Node cuNode =root;
        while (cuNode!=null){
            if (cuNode.data>key){
                cuNode=cuNode.leftChild;
            }else if (cuNode.data<key){
                cuNode=cuNode.rightChild;
            }else{
                return cuNode;
            }

        }
        return null;
    }

    public boolean insert(int data) {
        Node node = new Node(data);
        if (root==null){
            root=node;
        }else {
            Node parent ;
            Node curr =root;
            while (curr!=null){
                parent=curr;
                if (curr.data>data){
                    curr=curr.leftChild;
                    if (curr==null){
                        parent.leftChild=node;
                        return true;
                    }
                }else if (curr.data<=data){
                    curr=curr.rightChild;
                    if (curr==null){
                        parent.rightChild=node;
                        return true;
                    }
                }
            }
        }

        return false;
    }
  //中序遍历
    public void infixOrder(Node current) {
          if (current!=null){
              infixOrder(current.leftChild);
              System.out.print(current.data+"-");
              infixOrder(current.rightChild);
          }
    }
//前序
    public void preOrder(Node current) {
        if (current!=null){
            System.out.print(current.data+"-");
            infixOrder(current.leftChild);

            infixOrder(current.rightChild);
        }
    }
//后序
    public void postOrder(Node current) {
        if (current!=null){
            infixOrder(current.leftChild);

            infixOrder(current.rightChild);
            System.out.print(current.data+"-");
        }
    }

    public int maxHight(Node current){
        int max=0;
        int leftMax=0;
        int rightMax=0;
        if (current!=null){
            max++;
            leftMax=maxHight(current.leftChild);
            leftMax=maxHight(current.rightChild);
            max+=leftMax>=rightMax?leftMax:rightMax;
        }
        return max;
    }
    public Node findMax() {
        Node maxNode=root;
        Node curr=root;
        while (curr!=null){
            maxNode=curr;
            curr=curr.rightChild;

        }
        return maxNode;
    }

    public Node findMin() {
        Node minNode=root;
        Node curr=root;
        while (curr!=null){
            minNode=curr;
            curr=curr.leftChild;

        }
        return minNode;
    }

    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = false;
        //查找删除值，找不到直接返回false
        while(current.data != key){
            parent = current;
            if(current.data > key){
                isLeftChild = true;
                current = current.leftChild;
            }else{
                isLeftChild = false;
                current = current.rightChild;
            }
            if(current == null){
                return false;
            }
        }
        //如果当前节点没有子节点
        if(current.leftChild == null && current.rightChild == null){
            if(current == root){
                root = null;
            }else if(isLeftChild){
                parent.leftChild = null;
            }else{
                parent.rightChild = null;
            }
            return true;

            //当前节点有一个子节点，右子节点
        }else if(current.leftChild == null && current.rightChild != null){
            if(current == root){
                root = current.rightChild;
            }else if(isLeftChild){
                parent.leftChild = current.rightChild;
            }else{
                parent.rightChild = current.rightChild;
            }
            return true;
            //当前节点有一个子节点，左子节点
        }else if(current.leftChild != null && current.rightChild == null){
            if(current == root){
                root = current.leftChild;
            }else if(isLeftChild){
                parent.leftChild = current.leftChild;
            }else{
                parent.rightChild = current.leftChild;
            }
            return true;
        }else{
            //当前节点存在两个子节点
            Node successor = getSuccessor(current);
            if(current == root){
                root= successor;
            }else if(isLeftChild){
                parent.leftChild = successor;
            }else{
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;
        }
        return false;

    }

    public Node getSuccessor(Node delNode){
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while(current != null){
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        //后继节点不是删除节点的右子节点，将后继节点替换删除节点
        if(successor != delNode.rightChild){
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }

        return successor;
    }

    public static void main(String[] args) {
        MyTree bt = new MyTree();
        bt.insert(50);
        bt.insert(20);
        bt.insert(80);
        bt.insert(10);
        bt.insert(30);
        bt.insert(60);
        bt.insert(90);
        bt.insert(25);
        bt.insert(85);
        bt.insert(100);
          bt.infixOrder(bt.root);
        bt.delete(10);//删除没有子节点的节点
       bt.delete(30);//删除有一个子节点的节点
        bt.delete(80);//删除有两个子节点的节点
        System.out.println(bt.maxHight(bt.root));
        System.out.println(bt.findMax().data);
        System.out.println(bt.findMin().data);
        System.out.println(bt.find(100));
        System.out.println(bt.find(200));
    }
}
