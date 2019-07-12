package com.reactor.test;

public class MyTree implements Tree {
    protected Node root;

    public Node find(int key) {
        Node cuNode = root;
        while (cuNode != null) {
            if (cuNode.data > key) {
                cuNode = cuNode.leftChild;
            } else if (cuNode.data < key) {
                cuNode = cuNode.rightChild;
            } else {
                return cuNode;
            }

        }
        return null;
    }

    public boolean insert(int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
        } else {
            Node parent;
            Node curr = root;
            while (curr != null) {
                parent = curr;
                if (curr.data > data) {
                    curr = curr.leftChild;
                    if (curr == null) {
                        parent.leftChild = node;
                        return true;
                    }
                } else if (curr.data <= data) {
                    curr = curr.rightChild;
                    if (curr == null) {
                        parent.rightChild = node;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //中序遍历
    public void infixOrder(Node current) {
        if (current != null) {
            infixOrder(current.leftChild);
            System.out.print(current.data + "-");
            infixOrder(current.rightChild);
        }
    }

    //前序
    public void preOrder(Node current) {
        if (current != null) {
            System.out.print(current.data + "-");
            infixOrder(current.leftChild);

            infixOrder(current.rightChild);
        }
    }

    //后序
    public void postOrder(Node current) {
        if (current != null) {
            infixOrder(current.leftChild);

            infixOrder(current.rightChild);
            System.out.print(current.data + "-");
        }
    }

    public int maxHight(Node current) {
        int max = 0;
        int leftMax = 0;
        int rightMax = 0;
        if (current != null) {
            max++;
            leftMax = maxHight(current.leftChild);
            rightMax = maxHight(current.rightChild);
            max += leftMax >= rightMax ? leftMax : rightMax;
        }
        return max;
    }

    public Node findMax() {
        Node maxNode = root;
        Node curr = root;
        while (curr != null) {
            maxNode = curr;
            curr = curr.rightChild;

        }
        return maxNode;
    }

    public Node findMin() {
        Node minNode = root;
        Node curr = root;
        while (curr != null) {
            minNode = curr;
            curr = curr.leftChild;

        }
        return minNode;
    }

    public boolean delete1(int key) {
        Node curr = root;
        Node parent = root;
        boolean left = false;
        while (curr.data != key) {
            parent = curr;
            if (curr.data > key) {
                curr = curr.leftChild;
                left = true;
            } else {
                curr = curr.rightChild;
                left = false;
            }
        }
        if (curr == null) {
            return false;
        }
        if (curr.leftChild == null && curr.rightChild == null) {
            if (curr == root) {
                root = null;
                return true;
            }
            if (left) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
            return true;
        }
        if (curr.leftChild == null && curr.rightChild != null) {
            if (curr == root) {
                root = curr.rightChild;
            } else if (left) {
                parent.leftChild = curr.rightChild;
            } else {
                parent.rightChild = curr.rightChild;
            }
            return true;
        }
        if (curr.leftChild != null && curr.rightChild == null) {
            if (curr == root) {
                root = curr.leftChild;
            } else if (left) {
                parent.leftChild = curr.leftChild;
            } else {
                parent.rightChild = curr.leftChild;
            }
            return true;
        }
        //有两个子节点,如果要删除得节点在panrent得左边，就左右右右右找到当前左节点最右的子节点，赋值，删除最右的子节点，反过来是右左左左左
        if (curr.leftChild != null && curr.rightChild != null) {
            curr = curr.leftChild;
            Node par = curr;
            while (curr.rightChild != null) {
                par = curr;
                curr = curr.rightChild;
            }
            if (left) {
                parent.leftChild.data = curr.data;
            } else {
                parent.rightChild.data = curr.data;
            }
            par.rightChild = null;
            return true;
        }
        return false;
    }

    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = false;
        //查找删除值，找不到直接返回false
        while (current.data != key) {
            parent = current;
            if (current.data > key) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null) {
                return false;
            }
        }
        //如果当前节点没有子节点
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
            return true;

            //当前节点有一个子节点，右子节点
        } else if (current.leftChild == null && current.rightChild != null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
            return true;
            //当前节点有一个子节点，左子节点
        } else if (current.leftChild != null && current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
            return true;
        } else {
            //当前节点存在两个子节点
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;
        }
        return false;

    }

    public Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        //后继节点不是删除节点的右子节点，将后继节点替换删除节点
        if (successor != delNode.rightChild) {
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
        bt.insert(101);
        bt.insert(70);
        bt.insert(75);
        bt.insert(55);
        bt.insert(65);
        bt.insert(110);

        bt.infixOrder(bt.root);
        bt.delete1(10);//删除没有子节点的节点
        bt.delete1(30);//删除有一个子节点的节点
        bt.delete1(80);//删除有两个子节点的节点
        System.out.println(bt.maxHight(bt.root));
        System.out.println(bt.findMax().data);
        System.out.println(bt.findMin().data);
        System.out.println(bt.find(100));
        System.out.println(bt.find(200));
        bt.infixOrder(bt.root);
    }
    /**
     * 删除元素
     * 删除元素如果细分的话，可以分为4中：没有子节点，只有左节点，只有右节点，有两个子节点
     * 1）没有子节点这种情况比较简单，直接删除就可以了
     * 2）只有左节点或右节点，这两种情况处理方式是一致的，只是方向相反，所以在一起讲了，
     * 将删除节点的父节点的左节点（右节点）指向删除节点的子节点，将左节点（右节点）指向删除节点的父节点
     * 3）有两个子节点，这种情况相对来说比较复杂一点：
     * 找到删除节点的前驱节点或后继节点，然后将前驱或后继节点的值赋给删除节点，然后将前驱或后继节点删除。本质是删除前驱或后继节点
     * 前驱节点的特点：
     * 1）删除的左子节点没有右子节点，那么左子节点即为前驱节点
     * 2）删除节点的左子节点有右子节点，那么最右边的最后一个节点即为前驱节点
     * 后继节点的特点：
     * 与前驱节点刚好相反，总是右子节点，或则右子节点的最左子节点;例如：删除节点为c ，那么前驱节点为 m，后继节点为n
     *                                          a
     *                                       /     \
     *                                    b          c
     *                                  / \         /  \
     *                                d    e       f    g
     *                              /  \  / \     / \   / \
     * @param item 删除元素          h   i  j  k   l   m n   o
     * @return 删除结果
     */

}
