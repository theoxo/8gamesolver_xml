/**
 * Created by theoxola on 2017-04-01.
 */

import java.io.File;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import org.w3c.dom.*;

public class MainClass {

    private static MyTreeNode root;
    private static String toFind;

    public static void main(String[] args){

        /**
         * DEVELOPMENT VERSION
         */

        // keep pointer to root
        Board bd = new Board("12345678*");
        root = new MyTreeNode(bd);

        // begin search
        System.out.println("Root loaded with form " + root.getForm());
        load();


    }


    private static void load(){

        if (root != null){

            System.out.println();
            System.out.println("Beginning generation of the DOM...");
            System.out.println();

            Queue<MyTreeNode> queue = new LinkedList<>();

            // Attempt DOM
            try{
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder build = dbf.newDocumentBuilder();
                Document doc = build.newDocument();

                XPathFactory xpf = XPathFactory.newInstance();
                XPath xpath = xpf.newXPath();
                System.out.println("\nSuccessfully instantiated DocumentBuilderFactory; DocumentBuilder; Document; XPathFactory; and XPath.\n");


                // generate tree parallel to DOM to lazily check for duplication
                queue.add(root);

                // add root
                String rf = root.getForm();
                Element rt = doc.createElement("node");
                doc.appendChild(rt);
                rt.setAttribute("form", rf);

                while(!queue.isEmpty()){

                    MyTreeNode node = queue.remove();
                    String nf = node.getForm();

                    // attempt to generate child nodes in DOM and tree
                    try {

                        // xpath to find parent node in DOM
                        String search = "//node[@form=";
                        search += "\"" + nf + "\"]";
                        XPathExpression xPathExpression = xpath.compile(search);

                        // careful conversion
                        Node parN = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
                        Element par = (Element) parN;
                        Board base = new Board(nf);

                        Board[] nextBoards = Board.nextBoards(base);

                        for (Board b : nextBoards){

                            String form = b.getLayout();
                            MyTreeNode nd = MyTreeNode.getNode(form, root);

                            if (nd == null){
                                node.addChild(form);
                                Element ch = doc.createElement("node");
                                par.appendChild(ch);
                                ch.setAttribute("form", form);

                                // relocate added node and add to queue
                                nd = MyTreeNode.getNode(form, root);
                                queue.add(nd);

                            }

                        }

                    }

                    catch (XPathExpressionException xer){
                        System.out.printf("%nXPathExpressionException %s%n", xer);
                    }
                }

                System.out.println("DOM parsing finished --- proceeding with transformation to xml");

                // attempt transforming
                try {
                    Transformer tf = TransformerFactory.newInstance().newTransformer();
                    Result output   = new StreamResult(new File("8gamesolver.xml"));
                    Source input = new DOMSource(doc);

                    try {
                        tf.transform(input, output);
                        System.out.println("\nProgram finished running succesfully. Goodbye.\n");
                    }

                    catch (TransformerException tefx){
                        System.out.printf("TransformerException %s%n", tefx);
                    }
                }

                catch (TransformerConfigurationException ex){
                    System.out.printf("TCE %s", ex);
                }

            }
            catch(ParserConfigurationException e){
                System.out.printf("PCE %s", e);
            }
        }
    }

}
