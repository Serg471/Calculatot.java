package com.company;

import java.util.*;


public class Main {
    public static void main(String[] arg){
        //  System.out.println("введите выражение для вычисления арабскими или римскими числами,"+
        //  " используя латинский алфавит, выделяя оператор пробелами");
        Scanner scanner = new Scanner(System.in);
        String phrase = scanner.nextLine();
        phrase = phrase.toUpperCase();
        String[] words = phrase.split(" ");

        if (is_roman_is_false(words[0]) == true & is_roman_is_false(words[2]) == true){ //проверяю, что обе части
            int first_arabian_numb = arabian_to_int(words[0]);     //выражния записаны арабскими числами
            int second_arabian_numb = arabian_to_int(words[2]);     // и перевожу из строки в число
            if (first_arabian_numb < 1 || second_arabian_numb < 1 ||  // проверяю, что удовлетворяет условиям
                    first_arabian_numb > 10 || second_arabian_numb > 10){
                System.out.println("Исключение");

            }
            int res = return_result(first_arabian_numb, second_arabian_numb, words[1]);  //метод считает арифметику
            System.out.println(res);
        }
        else if (is_roman_is_false(words[0]) == false & is_roman_is_false(words[2]) == false){    //проверяю, что обе части
            int first_roman_numb = roman_into_arabian(words[0]);                     //выражния записаны римскими числами
            int second_roman_numb = roman_into_arabian(words[2]);  // перевожу в арабские и смотрю, что они входят в диапозон 1-10
            if ( first_roman_numb == -1 || second_roman_numb == -1){
                System.out.println("Исключение");

            }
            int res_rom = return_result(first_roman_numb, second_roman_numb, words[1]); // считает результат на арабских числах
            if (res_rom < 1) {   // удовлетворение условию, что при работе с римскими не получился 0 и менее
                System.out.println("Исключение");

            }
            StringBuilder res_roman = arabian_to_roman(res_rom); //перевод на римские цифры и выдача результата
            System.out.println(res_roman);
        }
        else {
            System.out.println("Исключение");
        }

    }
    private static int return_result (int first, int second, String operator_str){
        char operator = operator_str.charAt(0);   // метод производит вычисление
        int result = 0;
        switch (operator) {
            case '+':
                return result = first + second;
            case '-':
                return result = first - second;
            case '*':
                return result = first* second;
            case '/':
                return result = first / second;
            default:
                throw new IllegalArgumentException("Исключение");

        }

    }

    private static int arabian_to_int (String str_numb) {
        int numb = Integer.parseInt(str_numb);  // метод переводит символьную строку в число int
        return numb;
    }
    private static boolean is_roman_is_false(String word) {  // метод выдает правду, если арабские цифры и ложь, если римские
        try {                                           // т.о. исключаем вариант, когда записаны и арабские и римские, оставляем только правда-правда или ложь-ложь
            int first_numb = Integer.parseInt(word);
            return true;
        }
        catch (NumberFormatException a) {
            return false;
        }
    }
    private static int roman_into_arabian(String roman_numb){  //метод переводиит римские в арабские числа
        switch (roman_numb) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                return -1;    //если число меньше 1 или больше 10 - отрицательная единица, то есть ошибка, прописана в main

        }
    }
    private static StringBuilder arabian_to_roman(int numb){
        StringBuilder roman = new StringBuilder();   // перевод арабского числа в римское
        while (numb > 0){
            if (numb / 50 > 0) {
                roman = roman.append("L");
                numb = numb - 50;
            }
            else if (numb / 10 > 0) {
                int tens = 0;
                for (int i = 0; i < (numb / 10); i++) {
                    roman = roman.append("X");
                    tens = i + 1;
                }
                numb = numb - (tens * 10);
            }
            else if (numb / 5 > 0){
                numb = numb - 5;
                roman = roman.append("V");
            }
            else if (numb == 4) {
                roman = roman.append("IV");
                numb = numb - 4;
            }
            else {
                while (numb > 0 & numb < 4) {
                    roman = roman.append("I");
                    numb--;
                }
            }
        }

        return roman;
    }

}