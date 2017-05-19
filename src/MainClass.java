/**
 * Created by theoxola on 2017-04-01.
 */

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import org.w3c.dom.*;

public class MainClass {

    private static MyTreeNode root;
    private static String toFind;
    private static boolean timeOut;
    private static long timeLimSecs = 0;
    private static long tStart;

    public static void main(String[] args){

        /**
         * DEVELOPMENT VERSION
         */

        // keep pointer to root
        Board bd = new Board(args[0]);
        root = new MyTreeNode(bd);

        // identify requested end state
        Board find = new Board(args[1]);
        toFind = find.getLayout();
        System.out.println("State to find: " + toFind);

        // begin search
        System.out.println("Root loaded with form " + root.getForm());
        load();


        /**
         * END USER VERSION
         */
/*
        System.out.println();
        System.out.println("Initialising puzzle solver. \n");
        Scanner in = new Scanner(System.in);

        // initialise and keep pointer to root
        System.out.print("Please enter your start-state: ");
        Board bd = new Board(in.next());
        root = new MyTreeNode(bd);
        System.out.println("(start date initialised: " + root.getForm() + ")\n");

        // initialise and store requested end state -- run through board to validate
        System.out.print("Please enter the state you wish to reach: ");
        Board tf = new Board(in.next());
        toFind = tf.getLayout();
        System.out.println("(end state initialised: " + toFind + ")\n");

        System.out.println("Do you want to set a maximum time to allow the code to run for? (y/n)");
        String ans = in.next();

        while (!ans.equals("y") && !ans.equals("n")){
            System.out.println("Please answer 'y' or 'n':");
            ans = in.next();
        }

        if (ans.equals("y")){
            timeOut = true;
        }

        else {
            timeOut = false;
        }

        if (timeOut){
            System.out.print("\nPlease enter the amount of minutes you wish the code to run for; ");
            do {
                System.out.print("Whole, positive minutes only, please: ");
                try {
                    timeLimSecs = in.nextInt() * 60;
                } catch (InputMismatchException e) {
                    // skip over users last input
                    in.next();
                }
            }
            while (timeLimSecs <= 0);
        }

        in.close();

        tStart = System.nanoTime();
*/
        // begin search
        //load();

    }



    private static void load(){

        if (root != null){

            System.out.println();
            System.out.println("Beginning search for the requested state...");
            System.out.println();

            Queue<MyTreeNode> queue = new LinkedList<MyTreeNode>();
            //Queue<Element> elements = new LinkedList<>();

            try{
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder build = dbf.newDocumentBuilder();
                Document doc = build.newDocument();

                XPathFactory xpf = XPathFactory.newInstance();
                XPath xpath = xpf.newXPath();
                System.out.println("succ");

                queue.add(root);

                //add root
                String rf = root.getForm();
                System.out.println(rf);
                Element rt = doc.createElement("node");
                doc.appendChild(rt);
                rt.setAttribute("form", rf);

                //elements.add(rt);

                //boolean stateNotYetFound = true;
                boolean timeOver = false;

                while(!queue.isEmpty() /*&& !elements.isEmpty()/*&& stateNotYetFound &&!timeOver*/){

                    MyTreeNode node = queue.remove();
                    //Element par = elements.remove();
                    String nf = node.getForm();

                    try {
                        String search = "//node[@form=";
                        search += "\"" + nf + "\"]";
                        XPathExpression xPathExpression = xpath.compile(search);

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
                                //System.out.println("Board number " + count + " loaded: " + form);
                                // System.out.println("Board with form " + form + " loaded");

                                // attempt to find the state requested
                                /*
                                if(find()){
                                    stateNotYetFound = false;
                                    break;
                                }*/

                                //  else {
                                    //enqueue next
                                nd = MyTreeNode.getNode(form, root);
                                queue.add(nd);
                                //elements.add(ch);
                            //      }

                            }

                        }

                /*
                    if(timeOut){
                        long elapsedTime = System.nanoTime() - tStart;

                        if (TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) > timeLimSecs) {
                            timeOver = true;
                        }
                    }*/
                    }

                    catch (XPathExpressionException xer){
                        System.out.printf("%nXPathExpressionException %s%n", xer);
                    }
                }

                /*
                if (timeOver){
                    System.out.println("!!! No solution could be found in the given amount of time.");
                }*/

                System.out.println("DOM parsing finished --- proceeding with transformation to xml");

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
                    System.out.printf("PCE %s", ex);
                }

            }
            catch(ParserConfigurationException e){
                System.out.printf("PCE %s", e);
            }
        }
    }

    private static boolean find() {

        MyTreeNode found = MyTreeNode.getNode(toFind, root);
        boolean toReturn = false;

        if (found != null) {
            List<MyTreeNode> parents = new ArrayList<>();

            List<MyTreeNode> foundPath = MyTreeNode.treePath(found, parents);

            for (int i = 0; i < 3; i++) {
                System.out.println();
            }
            System.out.println("---> REQUESTED STATE FOUND <---");
            System.out.println();

            int lvl = 0;
            for (MyTreeNode node : foundPath) {
                System.out.println();
                System.out.println(lvl + "\t" + node.getForm());
                lvl++;
            }


            toReturn = true;
        }

        return toReturn;

    }
}
