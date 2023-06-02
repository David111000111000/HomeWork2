

public class MyTreeMap <K, V>{
    //Добавление элемента[+], получение элемента по ключу[+], удаление элемента по ключу[+].
    public static void main(String[] args) {//тесты
        MyTreeMap<Integer,String> myTreeMap=new MyTreeMap<>();
        myTreeMap.put(100,"100");
        myTreeMap.put(80,"80");
        myTreeMap.put(130,"130");
        myTreeMap.put(40,"40");
        myTreeMap.put(90,"90");
        myTreeMap.put(110,"170");
        myTreeMap.put(20,"20");
        myTreeMap.put(60,"60");
        myTreeMap.put(120,"120");
        myTreeMap.put(140,"140");
        myTreeMap.put(200,"200");
        myTreeMap.put(10,"10");
        myTreeMap.put(30,"30");
        myTreeMap.put(50,"50");
        myTreeMap.put(70,"70");
        myTreeMap.put(5,"5");
        myTreeMap.put(55,"55");
        myTreeMap.put(110,"110");
        myTreeMap.printValue();
        myTreeMap.remove(100);
        myTreeMap.printValue();
    }
    private int size=0;
    private Node root=null;
    private class Node{
        public K key;
        public V value;
        public Node left=null;
        public Node right=null;
        public Node(K key,V value){
            this.key=key;
            this.value=value;
        }
    }
    public V put(K key,V value){
        if(root==null){
            root=new Node(key,value);
            size++;
            return null;
        }else{
            return putHelper(root,key,value);
        }
    }
    public V putHelper(Node node,K key,V value){
        Comparable<? super K> k=(Comparable<? super K>) key;
        int cmp=k.compareTo(node.key);
        if(cmp<0){
            if(node.left==null){
                node.left=new Node(key,value);
                size++;
                return null;
            }
            return putHelper(node.left,key,value);
        }
        if(cmp>0){
            if(node.right==null){
                node.right=new Node(key,value);
                size++;
                return null;
            }
            return putHelper(node.right,key,value);
        }
            V oldValue=node.value;
            node.value=value;
            return oldValue;
    }
    public void printValue(){
        recPrintTree(root);
        System.out.println("----------");
    }
    private void recPrintTree(Node node){
        if(node.left!=null) recPrintTree(node.left);
        System.out.println(node.value);
        if(node.right!=null) recPrintTree(node.right);
    }
    public V get(Object key){
        Node node=getHelper(key);
        if(node==null) return null;
        return node.value;
    }
    private Node getHelper(Object target){
        Comparable<? super K> k=(Comparable<? super K>) target;
        Node node=root;
        while(node!=null){
            int cmp=k.compareTo(node.key);
            if(cmp<0){
                node=node.left;
            }
            if(cmp>0){
                node=node.right;
            }
            if(cmp==0){
                return node;//НАШЛИ!!
            }
        }
        return null;//не нашли;(
    }
    private Node findParent(Object target){
        Comparable<? super K> k=(Comparable<? super K>) target;
        Node node=root;
        Node parent=root;
        while(node!=null){
            int cmp=k.compareTo(node.key);
            if(cmp<0){
                parent=node;
                node=node.left;
            }
            if(cmp>0){
                parent=node;
                node=node.right;
            }
            if(cmp==0){
                return parent;//НАШЛИ!!
            }
        }
        return null;//не нашли;(
    }
    public V remove(Object key){
        V oldValue=get(key);
        if(key==root.key) root = recDelete(key);
        else recDelete(key);
        System.out.println("Root = "+root.value);
        return oldValue;
    }
    private Node recDelete(Object key){
        Node node=getHelper(key);
        Node parent=findParent(key);
        if(node.left==null&&node.right==null){
            if(node==parent.left) parent.left=null;
            if(node==parent.right) parent.right=null;
            size--;
            return parent;
        }
        if(node.right==null){
            if(node==parent.left) parent.left=node.left;
            if(node==parent.right) parent.right=node.left;
            size--;
            return parent;
        }
        if(node.left==null){
            if(node==parent.left) parent.left=node.right;
            if(node==parent.right) parent.right=node.right;
            size--;
            return parent;
        }
        Node tempNode=findSmallest(node.right);
        recDelete(tempNode.key);
        node.key= tempNode.key;
        node.value=tempNode.value;
        return parent;
    }
    private Node findSmallest(Node node){
        if(node.left==null){
            return node;
        }else{
            return findSmallest(node.left);
        }
    }
}