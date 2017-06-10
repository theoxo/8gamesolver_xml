/**
 * Created by theoxo on 2017-03-31.
 */

import java.util.*;


public class MyTreeNode {

    private String form = null;
    private List<MyTreeNode> children = new ArrayList();
    private MyTreeNode parent = null;

    // construct from Board
    public MyTreeNode(Board b){

        this.form = b.getLayout();
    }

    // construct from String (PRIVATE)
    private MyTreeNode(String form){
        this.form = form;
    }

    // add a new child to current node from String
    public void addChild(String data){
        MyTreeNode child = new MyTreeNode(data);
        this.children.add(child);
        child.setParent(this);
    }

    // set parent of current node
    private void setParent(MyTreeNode parent){
        this.parent = parent;
    }

    public String getForm() {
        return form;
    }

    public MyTreeNode getParent() {
        return parent;
    }


    public List<MyTreeNode> getChildren(){
        return children;
    }

    // get tree treePath to node by recursively returning parents
    public static List<MyTreeNode> treePath(MyTreeNode node, List<MyTreeNode> path){

        path.add(node);

        if (node.getParent() != null){
            node = node.getParent();
            treePath(node, path);
        }

        return path;
    }


    public static MyTreeNode getNode(String searchLayout, MyTreeNode root){

        if (root != null){

            Queue<MyTreeNode> queue = new LinkedList<MyTreeNode>();

            queue.add(root);

            while (!queue.isEmpty()){
                MyTreeNode node = queue.remove();

                if (node.getForm().equals(searchLayout)){
                    return node;
                }

                else {
                     queue.addAll(node.getChildren());
                }

            }

            //only reached if tried all nodes
            return null;

        }

        else {
            return null;
        }
    }
}
