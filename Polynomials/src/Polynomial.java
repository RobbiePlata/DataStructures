import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math.*;
import java.util.List;
import java.util.ListIterator;

public class Polynomial {

    //Length of list determines the degree of polynomial
    public LinkedList<Double> coef = new LinkedList<Double>();
    public LinkedList<Integer> expo = new LinkedList<Integer>();
    public int constant = 0;

    private String polynomial = null;

    public Polynomial(String polynomial) {
        this.polynomial = polynomial;
    }

    public int evaluate(int num) {
        int result = 0;
        int coefficient = 0;
        int exponent = 0;
        int calc = 0;
        for (int i = 0; i < expo.size(); i++) {
            coefficient = coef.get(i).intValue();
            exponent = expo.get(i);
            int mpow = (int) Math.pow(num, exponent);
            calc = coefficient * mpow;
            result += calc;
            }
        result += constant;
        System.out.println("Result: " +result);
        System.out.println();
        return result;
    }

    public Polynomial add(Polynomial polynomial) throws NullPointerException {
        LinkedList<Double> newcoef = new LinkedList<Double>();
        double newcoefficient = 0;
        int largercoefsize = Math.max(coef.size(), polynomial.coef.size()) + 1;

        //MAKE SURE THE SAME SIZE
        while (coef.size() != polynomial.coef.size()) {
            if (coef.size() < polynomial.coef.size()) {
                coef.addFirst(0.0);
            }
            if (coef.size() > polynomial.coef.size()) {
                polynomial.coef.addFirst(0.0);
            }
        }
        System.out.println(coef);
        System.out.println(polynomial.coef);

        //adding up
        for (int i = 0; i < largercoefsize - 1; i++) {
            double coefficient = coef.get(i);
            double coefficient2 = polynomial.coef.get(i);
            newcoefficient = coefficient + coefficient2;
            newcoef.add(newcoefficient);
        }
        int newconstant = constant + polynomial.constant;
        System.out.println(newcoef);
        System.out.println(newconstant);

        String output = "";

        for (int i = 0; i < newcoef.size(); i++) {
            int coeftoint = newcoef.get(i).intValue();
            if(coeftoint == 1){
                output += ("1x");
            }
            if(coeftoint != 1 && coeftoint != 0){
                output += (Integer.toString(coeftoint) + "x" + "^" + Integer.toString(newcoef.size() - i));
            }
            if(i < newcoef.size() - 1) {
                if (newcoef.get(i + 1) > 0) {
                    output += "+";
                }
            }
            if(coeftoint < 0){
                //need not append minus
            }
        }
        if(newconstant > 0){
            output += "+";
            output += Integer.toString(newconstant);
        }
        if(newconstant < 0) {
            //need not append minus
            output += Integer.toString(newconstant);
        }
        Polynomial poly = new Polynomial(output);
        coef = null;
        expo = null;
        return poly;
    }

    public Polynomial subtract(Polynomial polynomial) {
        LinkedList<Double> newcoef = new LinkedList<Double>();
        double newcoefficient = 0;
        int largercoefsize = 0;
        largercoefsize = Math.max(coef.size(), polynomial.coef.size()) + 1;

        //MAKE SURE THE SAME SIZE
        while (coef.size() != polynomial.coef.size()) {
            if (coef.size() < polynomial.coef.size()) {
                coef.addFirst(0.0);
            }
            if (coef.size() > polynomial.coef.size()) {
                polynomial.coef.addFirst(0.0);
            }
        }
        System.out.println(coef);
        System.out.println(polynomial.coef);

        //adding up
        for (int i = 0; i < largercoefsize - 1; i++) {
            double coefficient = coef.get(i);
            double coefficient2 = polynomial.coef.get(i);
            newcoefficient = coefficient - coefficient2;
            newcoef.add(newcoefficient);
        }
        int newconstant = constant - polynomial.constant;
        System.out.println(newcoef);

        String output = "";

        for (int i = 0; i < newcoef.size(); i++) {
            int coeftoint = newcoef.get(i).intValue();
            if(coeftoint == 1){
                output += ("1x");
            }
            if(coeftoint != 1 && coeftoint != 0){
                output += (Integer.toString(coeftoint) + "x" + "^" + Integer.toString(newcoef.size() - i));
            }
            if(i < newcoef.size() - 1) {
                if (newcoef.get(i + 1) > 0 ) {
                    output += "+";
                }
            }
            if(coeftoint < 0) {
                //need not append minus
            }
        }
        if(newconstant > 0){
            output += "+";
            output += Integer.toString(newconstant);
        }
        if(newconstant < 0) {
            //need not append minus
            output += Integer.toString(newconstant);
        }
        Polynomial poly = new Polynomial(output);
        coef = null;
        expo = null;
        return poly;
    }

    public Polynomial multiply(int scalar) throws NullPointerException{
        String output = "";
        double coefficient = 0;
        for(int i = 0; i < coef.size(); i++){
            coefficient = coef.get(i);
            coefficient *= scalar;
            int intcoef = (int) coefficient;
            if(coefficient == 1) {
                output += "x";
            }
            else{
                output += Integer.toString(intcoef) + "x^" + Integer.toString(coef.size() - i);
            }
            if(i != coef.size() - 1){
                output += "+";
            }
        }
        if(constant != 0){
            constant *= scalar;
            output += "+" + constant;
        }
        System.out.println(output);
        Polynomial poly = new Polynomial(output);
        return poly;
    }

    public void oldappendCoefficients(){
        LinkedList<Double> coef = new LinkedList<Double>();
        LinkedList<Integer> expo = new LinkedList<Integer>();
        //Iteration through length of the string
        for (int i = 0; i < polynomial.length() - 1; i++) {
            //Only x so just degree of 1
            if (polynomial.charAt(i + 1) == 'x' && polynomial.charAt(i + 2) != '^') {
                int exponent = 1;
                //System.out.println("integer added 1");
                expo.addLast(exponent);
            }
            //Degree after ^ character
            if (polynomial.charAt(i + 1) == '^') {
                char c = polynomial.charAt(i + 2);
                int exponent = (int) (c - '0');
                expo.addLast(exponent);
                //System.out.println("integer added " + exponent);
            }
            //COEFFICIENTS
            if (polynomial.charAt(i + 1) == 'x' || polynomial.charAt(i - 1) == '-' || polynomial.charAt(i - 1) == '+') {
                //IF X THEN ADD 1 TO LIST
                if (polynomial.charAt(i) == 'x') {
                    coef.addLast(1.0); //problem here
                    //System.out.print(polynomial.charAt(i));
                    //System.out.println(" Coefficient added: " + 1.0);
                }
                //ADD NOTHING IF PLUS OR MINUS
                else {
                    char z = polynomial.charAt(i);
                    if (z == '+' || z == '-') {
                    } else {
                        double d = (double) (z - '0');
                        if (i == 0 && polynomial.charAt(i) == '-') {
                            coef.addLast(d);
                        }
                        if (i > 0 && polynomial.charAt(i - 1) == '+' || i == 0 && polynomial.charAt(i) != '-') {
                            coef.addLast(d);
                        }
                        if (i > 0 && polynomial.charAt(i - 1) == '-') {
                            d = 0 - d;
                            coef.addLast(d);
                        }
                        //System.out.println(" Coefficient added: " + d);
                    }
                }
            }
        }
        //Get constant
        int constant = 0;
        char charconstant;
        for (int j = 0; j < polynomial.length(); j++) {
            if (polynomial.charAt(j) == '+' || polynomial.charAt(j) == '-') {
                try {
                    constant = Integer.parseInt(polynomial.substring(j, polynomial.length()));
                } catch (NumberFormatException ex) {
                }
            }
        }
        //System.out.println("constant: " + constant);
        this.constant = constant;
        this.expo = expo;
        this.coef = coef;
    }

    public void appendCoefficients(){
        LinkedList<Double> coef = new LinkedList<Double>();
        LinkedList<Integer> expo = new LinkedList<Integer>();
        for (int i = 0; i < polynomial.length() - 1; i++) {
            if (polynomial.charAt(i) == 'x' && polynomial.charAt(i + 1) != '^') {
                int exponent = 1;
                expo.addLast(exponent);
            }
            if (polynomial.charAt(i + 1) == '^') {
                char c = polynomial.charAt(i + 2);
                int exponent = (int) (c - '0');
                expo.addLast(exponent);
            }
            //COEFFICIENTS
            if (polynomial.charAt(i) == 'x') {
                int j = i;
                String strcoef = polynomial.substring(j, i);
                boolean go = true;
                while (go) {
                    if (j != 0) {
                        j--;
                    }
                    if (polynomial.charAt(j) == '+' || polynomial.charAt(j) == '-' || j == 0) {
                        go = false;
                    }
                }
                strcoef = polynomial.substring(j, i);
                double coefficient = Double.parseDouble(strcoef);
                coef.add(coefficient);
                }
            }

        //Get constant
        int constant = 0;
        char charconstant;
        for (int j = 0; j < polynomial.length(); j++) {
            if (polynomial.charAt(j) == '+' || polynomial.charAt(j) == '-') {
                try {
                    constant = Integer.parseInt(polynomial.substring(j, polynomial.length()));
                } catch (NumberFormatException ex) {
                }
            }
        }
        //System.out.println("constant: " + constant);
        this.constant = constant;
        this.expo = expo;
        this.coef = coef;
    }

    public void addZeros(){

        int size = expo.size();
        for(int i = 0; i < expo.size() - 1; i++){
            int exponent = expo.get(i);
            int nextexponent = expo.get(i + 1);
            if(exponent != nextexponent + 1){
                int difference = exponent - nextexponent;
                for(int j = 0; j < difference - 1; j++){
                    expo.add(i+1,0);
                }
            }
        }
        if(expo.get(0) != 0 && expo.get(0) != expo.size()){
            while(expo.get(0) != expo.size()) {
                expo.add(0);
            }
        }

        //fine
        for (int i = 0; i < expo.size(); i++) {
            int exponent = expo.get(i);
            if (exponent == 0) {
                coef.add(i, 0.0);
            }
        }
    }

    public void printAscending() {
        String output = "";
        for (int i = 0; i < coef.size(); i++) {
            int coeftoint = coef.get(i).intValue();
            if(coeftoint == 1){
                output += ("x");
            }
            if(coeftoint != 1 && coeftoint != 0){
                output += (Integer.toString(coeftoint) + "x" + "^" + Integer.toString(coef.size() - i));
            }
            if(i < coef.size() - 1) {
                if (coef.get(i + 1) > 0) {
                    output += "+";
                }
            }
            if(coeftoint < 0) {
                //need not append minus
            }
            if (i == coef.size() - 1 && constant != 0){
                if(constant > 0){
                    output += "+";
                    output += Integer.toString(constant);
                }
                if(constant < 0){
                    //need not append minus
                    output += Integer.toString(constant);
                }
            }
        }
        System.out.println(output);
    }

    public void printDescending() {
        String output = "";
        int size = coef.size() - 1;
        for (int i = size; i != -1; i--) {
            int coeftoint = coef.get(i).intValue();
            if (i == size && constant != 0) {
                output += Integer.toString(constant);
            }
            if(coef.get(i) > 0){
                output += "+";
            }
            if(coeftoint == 1){
                output += "x";
            }
            if(coeftoint != 0 && coeftoint != 1){
                output += (Integer.toString(coeftoint) + "x" + "^" + Integer.toString(size - i + 1));
            }

        }
        System.out.println(output);
    }

    public void printCoefficients(){
        String output = "";
        for (int i = 0; i < coef.size(); i++) {
            int coeftoint = coef.get(i).intValue();
            output += ("(" + Integer.toString(coeftoint) + ")" + "x" + "^" + Integer.toString(coef.size() - i));
            if(i != coef.size() - 1){
                output += "+";
            }
            if (i == coef.size() - 1 && constant != 0) {
                output += "+";
                output += Integer.toString(constant);
            }
        }
        System.out.println(output);
    }

    public String getString() {
        return polynomial;
    }

}