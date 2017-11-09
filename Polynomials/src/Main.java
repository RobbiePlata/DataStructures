import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Main {

    public static ArrayList<Polynomial> polynomials = new ArrayList<Polynomial>();

    public static String readFile(String path) throws IOException {
        String str;
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String polynomial = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static String mainOptions() {
        Scanner kb = new Scanner(System.in);
        System.out.println("1. Create Polynomial");
        System.out.println("2. Select Polynomial(s)");
        String choice = kb.next();
        return choice;
    }

    public static String polyOptions() {
        Scanner kb = new Scanner(System.in);
        System.out.println("1. Read Terms from file");
        System.out.println("2. Read Terms from command line");
        String choice = kb.next();
        return choice;
    }

    public static String selectionOptions() {
        Scanner kb = new Scanner(System.in);
        System.out.println("1. Select Polynomial");
        System.out.println("2. Select 2 Polynomials");
        String choice = kb.next();
        return choice;
    }

    public static String selectionOne(){
        Scanner kb = new Scanner(System.in);
        System.out.println("1. Evaluate Polynomial");
        System.out.println("2. Multiply Polynomial");
        System.out.println("3. Print Polynomials");
        String choice = kb.next();
        return choice;
    }

    public static String printOptions() {
        Scanner kb = new Scanner(System.in);
        System.out.println("1. Canonical form decreasing order of degrees");
        System.out.println("2. Canonical form increasing order of degrees");
        System.out.println("3. Show all Coefficients");
        String choice = kb.next();
        return choice;
    }

    public static String operationOption(){
        Scanner kb = new Scanner(System.in);
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        String choice = kb.next();
        return choice;

    }

    public static void main(String[] args) throws IOException {
        System.out.println("Polynomials");
        Scanner kb = new Scanner(System.in);
        boolean go = true;

        // demo problem cases ("2x^5-1x^4");
        // demo problem cases ("6x^6+3x^4+1");

        while (go) {
            Polynomial poly = null;
            Polynomial poly1 = null;
            Polynomial poly2 = null;

            // No terms
            String mainchoice = mainOptions();

            //Appending Polynomials
            if (mainchoice.equals("1")) {
                String polyoption = polyOptions();
                if (polyoption.equals("1")) {
                    String polynomialfromfile = readFile("polynomial.txt");
                    poly = new Polynomial(polynomialfromfile);
                    poly.appendCoefficients();
                    poly.addZeros();
                    polynomials.add(poly);
                    System.out.println("Polynomial: " + polynomialfromfile + " added to list of polynomials");
                    System.out.println();
                }
                if (polyoption.equals("2")) {
                    System.out.print("Enter a Polynomial (Specify Coefficients): ");
                    String entry = kb.next(); //will be user inputted
                    poly = new Polynomial(entry);
                    poly.appendCoefficients();
                    poly.addZeros();
                    polynomials.add(poly);
                    System.out.println(poly.coef);
                    System.out.println(poly.expo);
                    System.out.println(poly.constant);
                    System.out.println();
                }
            }


            //Selection
            if (mainchoice.equals("2")) {
                System.out.println();
                String selectionoption = selectionOptions();
                //Select One
                if (selectionoption.equals("1")) {
                    System.out.println();
                    System.out.println("Select Polynomial: ");
                    for (int i = 0; i < polynomials.size(); i++) {
                        System.out.println(i + " " + polynomials.get(i).getString());
                    }
                    int choice = kb.nextInt();
                    poly1 = polynomials.get(choice);
                    String selectionone = selectionOne();

                    if(selectionone.equals("1")){
                        System.out.println();
                        System.out.println("Evaluate at: ");
                        poly1.evaluate(kb.nextInt());
                    }
                    if(selectionone.equals("2")){
                        System.out.println("Enter Integer: ");
                        int scalar = kb.nextInt();
                        poly = poly1.multiply(scalar);
                        poly.appendCoefficients();
                        poly.addZeros();
                        polynomials.add(poly);
                    }
                    if(selectionone.equals("3")){
                        String printoption = printOptions();
                        if(printoption.equals("1")){
                            poly1.appendCoefficients();
                            poly1.addZeros();
                            poly1.printAscending();

                        }
                        if(printoption.equals("2")){
                            poly1.appendCoefficients();
                            poly1.addZeros();
                            poly1.printDescending();
                        }
                        if(printoption.equals("3")){
                            poly1.appendCoefficients();
                            poly1.addZeros();
                            poly1.printCoefficients();
                        }
                    }
                }

                //Select Two
                if (selectionoption.equals("2")) {
                    System.out.println("Selections:");
                    for (int i = 0; i < polynomials.size(); i++) {
                        System.out.println(i + " " + polynomials.get(i).getString());
                    }
                    System.out.println("Choice 1:");
                    int choice1 = kb.nextInt();
                    poly1 = polynomials.get(choice1);
                    System.out.println("Choice 2:");
                    int choice2 = kb.nextInt();
                    poly2 = polynomials.get(choice2);
                    String operationOption = operationOption();
                    //Add
                    if(operationOption.equals("1")) {
                        try {
                            poly1.appendCoefficients();
                            poly1.addZeros();
                            poly2.appendCoefficients();
                            poly2.addZeros();
                            poly = poly1.add(poly2);
                            poly.appendCoefficients();
                            poly.addZeros();
                            polynomials.add(poly);
                            System.out.println(poly.getString());
                        } catch (NullPointerException ex) {
                            //System.out.println(ex);
                        }
                    }
                    //Subtract
                    if(operationOption.equals("2")){
                        try{
                            poly1.appendCoefficients();
                            poly1.addZeros();
                            poly2.appendCoefficients();
                            poly2.addZeros();
                            poly = poly1.subtract(poly2);
                            poly.appendCoefficients();
                            poly.addZeros();
                            polynomials.add(poly);
                            System.out.println(poly.getString());
                        }catch (NullPointerException ex) {

                        }

                    }

                }
            }

            if (mainchoice.equals("stop")) {
                go = false;
            }
        }
    }
}