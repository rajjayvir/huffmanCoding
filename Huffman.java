/**
 * Assignment 5
 * Author: Jayvirisnh Raj
 * B00907110
 * This is the main class where I have implemented the methods to convert characters to huffman code
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Huffman {

    public static void main(String[] args) throws FileNotFoundException {

        //initialised a scanner class
        Scanner kb = new Scanner(System.in);

        //read the file here
        String fileName = kb.nextLine();
        Scanner file = new Scanner(new File(fileName));

        //this is where queue where the pairs are added
        Queue<BinaryTree<Pair>> S = new LinkedList<>();

        //input from the file
        String letter;
        double prob;

        //probability of the alphabet are added here
        while (file.hasNext()) {

            letter = file.next();
            prob = file.nextDouble();

            Pair connect = new Pair(letter.charAt(0), prob);
            BinaryTree<Pair> check = new BinaryTree<>();
            check.makeRoot(connect);
            S.add(check);
        }

        //created the huffman tree
        BinaryTree<Pair> huffmanTree = createT(S);

        //find the encoding of the letters
        String[] codes = findEncoding(huffmanTree);

        //array of alphabet from A to Z
        int counter = 0;
        Character[] alphabet = new Character[26];
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = (char) (i + 65);
        }

        //where the alphabet to their code are stored
        HashMap<Character, String> connecter = new HashMap<Character, String>();

        //connected the huffman codes to alphabet are connected
        for (int i = 0; i < alphabet.length; i++) {
            connecter.put(alphabet[i], codes[i]);
        }
        //manually added the space so that it interprets as space when a string of characters are to be encoded
        connecter.put(' ', " ");

        //input taken from the user
        System.out.print("Enter a line of text (uppercase letters only):");
        String input = kb.nextLine();

        //put the string as array of characters
        Character[] inputDecoder = new Character[input.length()];

        //output of code is saved here
        ArrayList<String> output = new ArrayList<>();

        //converted to character array here
        for (int i = 0; i < inputDecoder.length; i++) {
            inputDecoder[i] = input.charAt(i);

            //give error if lower case letters are used
            if (Character.isUpperCase(inputDecoder[i]) || inputDecoder[i].equals(' ')) {
                output.add(i, connecter.get(inputDecoder[i]));
            }
            //give error if lower case letters are used or anything else is used
            else {
                System.out.println("Please enter upper case characters only");
                break;
            }
        }

        //print the huffman code of the string input
        System.out.print("Here's the encoded line: ");
        for (int i = 0; i < output.size(); i++) {
            System.out.print(output.get(i));

        }

        System.out.println();

        //Reconverting the huffman code to the string
        System.out.print("The decoded line is: ");
        ArrayList<Character> decodedLine = new ArrayList<>();
        for (int j = 0; j < output.size(); j++) {
            for (char i = 'A'; i < 'Z'; i++) {
                if (output.get(j).equals(connecter.get(i))) {
                    decodedLine.add(i);
                }
            }
            if (output.get(j).equals(connecter.get(' '))) {
                decodedLine.add(' ');
            }
        }

        //printing the String here that was input/ i.e. converted from the huffman code
        for (Character x : decodedLine) {
            System.out.print(x);
        }
    }

    /**
     * Method to create a Huffman tree
     * I have implemented this method as per instruction
     * @param s - queue of alphabets with probability
     * @return a binary tree with codes
     */
    public static BinaryTree<Pair> createT(Queue<BinaryTree<Pair>> s) {


        Queue<BinaryTree<Pair>> T = new LinkedList<>();

        //read while s is empty
        while (!s.isEmpty()) {
            BinaryTree<Pair> A = new BinaryTree<>();
            BinaryTree<Pair> B = new BinaryTree<>();
            if (T.isEmpty()) {
                A = s.remove();
                B = s.remove();
            }

            //while T is empty add to A
            if (!T.isEmpty()) {
                if (T.peek().getData().getProb() < s.peek().getData().getProb()) {
                    A = T.remove();
                } else if (T.peek().getData().getProb() > s.peek().getData().getProb()) {
                    A = s.remove();
                }
                if (T.isEmpty() && !s.isEmpty()) {
                    B = s.remove();
                } else if (!T.isEmpty() && s.isEmpty()) {
                    B = T.remove();
                } else {
                    if (T.peek().getData().getProb() < s.peek().getData().getProb()) {
                        B = T.remove();
                    } else if (T.peek().getData().getProb() > s.peek().getData().getProb()) {
                        B = s.remove();
                    }
                }
            }


            BinaryTree<Pair> P = new BinaryTree<>();
            double add = A.getData().getProb() + B.getData().getProb();
            Pair adder = new Pair('0', A.getData().getProb() + B.getData().getProb());
            P.makeRoot(adder);
            P.attachLeft(A);
            P.attachRight(B);
            T.add(P);
        }

        while (T.size() > 1) {
            BinaryTree<Pair> A = T.remove();
            BinaryTree<Pair> B = T.remove();

            BinaryTree<Pair> P = new BinaryTree<>();
            double add = A.getData().getProb() + B.getData().getProb();
            Pair adder = new Pair('0', A.getData().getProb() + B.getData().getProb());
            P.makeRoot(adder);
            P.attachLeft(A);
            P.attachRight(B);
            T.add(P);
        }
        //returns the huffman Binary tree
        return T.peek();
    }

    //This method I have taken from the instructions of the Assignment
    private static String[] findEncoding(BinaryTree<Pair> bt) {
        String[] result = new String[26];
        findEncoding(bt, result, "");
        return result;
    }


    //This method I have taken from the instructions of the Assignment
    private static void findEncoding(BinaryTree<Pair> bt, String[] a, String prefix) {
        // test is node/tree is a leaf
        if (bt.getLeft() == null && bt.getRight() == null) {
            a[bt.getData().getValue() - 65] = prefix;
        }
        // recursive calls
        else {
            findEncoding(bt.getLeft(), a, prefix + "0");
            findEncoding(bt.getRight(), a, prefix + "1");
        }

    }

}
