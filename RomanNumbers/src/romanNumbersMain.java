public class romanNumbersMain {
    public static void main(String[] args) {
        int arrayInt[] = {125, 252, 1000, 1010};
        String arrayRom[] = {"II", "IX", "MLXVI", "MCMLXXXIX"};
        String result = "";

        for (int i = 0; i < arrayInt.length; i++) {
            System.out.println("Number: " + arrayInt[i]);
            result = intToRoman(arrayInt[i]);
            System.out.println(result);
        }

        System.out.println("=======================");

        for (int i = 0; i < arrayRom.length; i++) {
            System.out.println("Number: " + arrayRom[i]);
            result = romanToInt(arrayRom[i]);
            System.out.println(result);
        }


    }

    public static String intToRoman(int num) {
        System.out.println("Integer: " + num);
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLetters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num = num - values[i];
                roman.append(romanLetters[i]);
            }
        }
        return "Corresponding Roman Numerals is: " + roman.toString();
    }

    public static final String romanToInt(String roman) {

        int number = 0;
        for (int i = 0; i < roman.length(); i++) {
            char c = roman.charAt(i);
            switch (c) {
                case 'I':
                    number = (i != roman.length() - 1 && (roman.charAt(i + 1) == 'V' || roman.charAt(i + 1) == 'X'))
                            ? number - 1
                            : number + 1;
                    break;
                case 'V':
                    number += 5;
                    break;
                case 'X':
                    number = (i != roman.length() - 1 && (roman.charAt(i + 1) == 'L' || roman.charAt(i + 1) == 'C'))
                            ? number - 10
                            : number + 10;
                    break;
                case 'L':
                    number += 50;
                    break;
                case 'C':
                    number = (i != roman.length() - 1 && (roman.charAt(i + 1) == 'D' || roman.charAt(i + 1) == 'M'))
                            ? number - 100
                            : number + 100;
                    break;
                case 'D':
                    number += 500;
                    break;
                case 'M':
                    number += 1000;
                    break;
            }
        }

        return "Corresponding Arabic Numeral is: " + number;
    }
}
