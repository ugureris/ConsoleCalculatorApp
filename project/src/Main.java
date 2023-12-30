import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        System.out.println("***** Gelişmiş Hesap Makinesi *****");
        System.out.println("Toplama: + | Çıkarma: - | Çarpma: * | Bölme: /");
        System.out.println("Karekök: sqrt(x) | Üs Alma: pow(x,y) | Sin: sin(x) | Cos: cos(x) | Tan: tan(x)");
        System.out.println("Çıkmak için: exit");

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.print("İşlemi girin: ");
            String arr = input.nextLine();
            if (arr.equals("exit")) {
                System.out.println("Hesap makinesi kapatılıyor. İyi günler!");
                break;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(arr, "+*-/", true);
            String[] array = new String[stringTokenizer.countTokens()];
            int index = 0;
            while (stringTokenizer.hasMoreTokens()) {
                array[index++] = stringTokenizer.nextToken().trim();
            }
            double sum = calculate(array);
            System.out.println("Sonuç: " + sum);
        }

    }

    public static double calculate(String[] array) {
        Stack<Double> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String element : array) {
            if (isNumeric(element)) {
                numbers.push(Double.parseDouble(element));
            } else if (isSquareRoot(element)) {
                numbers.push(Double.parseDouble(String.valueOf(Math.sqrt(Double.parseDouble(element.substring(5, element.length() - 1))))));
            } else if (isSinus(element)) {
                numbers.push(Double.parseDouble(String.valueOf(Math.sin(Double.parseDouble(element.substring(4, element.length() - 1))))));
            } else if (isCosinus(element)) {
                numbers.push(Double.parseDouble(String.valueOf(Math.cos(Double.parseDouble(element.substring(4, element.length() - 1))))));
            } else if (isTanjant(element)) {
                numbers.push(Double.parseDouble(String.valueOf(Math.tan(Double.parseDouble(element.substring(4, element.length() - 1))))));
            } else if (isPower(element)) {
                int index = element.indexOf(',');
                numbers.push(Double.parseDouble(String.valueOf(Math.pow(Double.parseDouble(element.substring(4, index)), Double.parseDouble(element.substring(index + 1, element.length() - 1))))));
            } else if (isOperator(element)) {
                while (!operators.isEmpty() && operatorPrecedence(element, operators.peek())) {
                    topStatement(numbers, operators);
                }
                operators.push(element);
            }
        }


        while (!operators.isEmpty()) {
            topStatement(numbers, operators);
        }

        return numbers.pop();
    }

    public static void topStatement(Stack<Double> numbers, Stack<String> operators) {
        String op = operators.pop();
        double num2 = numbers.pop();
        double num1 = numbers.pop();

        switch (op) {
            case "+":
                numbers.push(num1 + num2);
                break;
            case "-":
                numbers.push(num1 - num2);
                break;
            case "*":
                numbers.push(num1 * num2);
                break;
            case "/":
                numbers.push(num1 / num2);
                break;
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    public static boolean isSquareRoot(String str) {
        return str.contains("sqrt");
    }

    public static boolean isSinus(String str) {
        return str.contains("sin");
    }

    public static boolean isCosinus(String str) {
        return str.contains("cos");
    }

    public static boolean isTanjant(String str) {
        return str.contains("tan");
    }

    public static boolean isPower(String str) {
        return str.contains("pow");
    }

    public static boolean operatorPrecedence(String op1, String op2) {
        if ((op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-"))) {
            return false;
        }
        return true;
    }


}