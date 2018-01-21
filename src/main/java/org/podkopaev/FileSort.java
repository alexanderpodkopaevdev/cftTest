package org.podkopaev;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 */
public class FileSort {

    static boolean intOrString = false;
    static boolean waningOrAscending = false;

    public static void main(String[] args) {
        if (args.length != 4) {
            exitAfterPress("Неверное число параметров");
        }
        String inputFileName = args[0];
        String outputFileName = args[1];
        String param1 = args[2];
        String param2 = args[3];
        checkParam(param1,param2);


        //System.out.println( "Hello World!" );
    }

    private static void exitAfterPress(String errorText) {
        String warningText = "Ошибка в программе, ознакомтесь с информацией и нажмите любую кнопку для выхода";
        System.out.println(warningText + "\n" + errorText);
        try {
            System.in.read();
        } catch (IOException ex) {
        } finally {
            System.exit(0);
        }
    }

    private static void checkParam(String param1, String param2) {
        if (param1.equals("-i")) {
            intOrString = true;
        } else if (param1.equals("-s")) {
            intOrString = false;
        } else askQuestion("Неверно указан параметр для выбора чисел и строк. Использовать строки по-умолчанию или закрыть программу?");
        if (param2.equals("-d")) {
            waningOrAscending = true;
        } else if (param1.equals("-a")) {
            waningOrAscending = false;
        } else askQuestion("Неверно указан параметр для выбора режима сортировки. Использовать сортировку по возрастанию по-умолчанию или закрыть программу?");

    }

    private static void askQuestion(String text) {
        System.out.println(text + ". Если хотите продолжить с параметом по-умолчанию введите \"Y\"");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String answer = reader.readLine();
            if (!(answer.equals("Y"))) {
                exitAfterPress("Параметр указан неверно");
            }
        } catch (IOException ex) {
            exitAfterPress("Произошла ошибка ввода");
        }

    }
}
