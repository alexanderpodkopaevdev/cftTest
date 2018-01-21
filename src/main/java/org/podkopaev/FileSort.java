package org.podkopaev;


import java.io.*;
import java.util.ArrayList;

/**
 * Hello world!
 */
public class FileSort {

    private static boolean intOrString = false;
    private static boolean waningOrAscending = false;

    public static void main(String[] args) {
        if (args.length != 4) {
            exitAfterPress("Неверное число параметров");
        }
        String inputFileName = args[0];
        String outputFileName = args[1];
        String param1 = args[2];
        String param2 = args[3];
        checkParam(param1,param2);
        ArrayList<String> listElements = readFile(inputFileName);
        String[] readyArray = sortArr(listElements);
        writeFile(readyArray, outputFileName);

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
        System.out.println(text + "\n" + "Если хотите продолжить с параметом по-умолчанию введите \"Y\"");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String answer = reader.readLine();
            if (!(answer.equals("Y"))) {
                exitAfterPress("Работа с программой окончена");
            }
        } catch (IOException ex) {
            exitAfterPress("Произошла ошибка ввода");
        }
    }

    private static ArrayList<String> readFile(String fileName) {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                list.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            exitAfterPress("Файл не найден");
        }
        catch (IOException ex) {
            exitAfterPress("Ошибка чтения входного файла");
        }
        return list;
    }

    private static String[] sortArr(ArrayList<String> list) {
        String[] readyArray = new String[list.size()];
        if (intOrString) {
            int[] arrayInt = new int[list.size()];
            try {
                for (int i = 0; i < list.size(); i++) {
                    arrayInt[i] = Integer.parseInt(list.get(i));
                }
                readyArray = insertionSort(arrayInt);
            } catch (NumberFormatException ex) {
                askQuestion("Указанный файл содержит не только числа. По-умолчанию он будет отсортирован как строковый");
            }
        } else readyArray = insertionSort(list);
        return readyArray;
    }

    private static String[] insertionSort(int[] array){

        for (int i=1; i<array.length; ++i)
        {
            int key = array[i];
            int j = i-1;
            while (j>=0 &&(!waningOrAscending? array[j] > key : array[j] < key))
            {
                array[j+1] = array[j];
                j = j-1;
            }
            array[j+1] = key;
        }
        String[] readyArray = new String[array.length];
        for (int i : array) {
            readyArray[i] = String.valueOf(array[i]);
        }
        return readyArray;
    }

    private static String[] insertionSort(ArrayList<String> list) {
        for (int i=1; i<list.size(); ++i)
        {
            String key = list.get(i);
            int j = i-1;
            while (j>=0 &&(!waningOrAscending? list.get(j).compareTo(key) > 0 : list.get(j).compareTo(key) < 0))
            {
                list.set(j+1,list.get(j));
                j = j-1;
            }
            list.set(j+1, key);
        }
        return list.toArray(new String[list.size()]);
    }

    private static void writeFile(String[] array, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String s : array) {
                writer.write(s);
            }
        } catch (FileNotFoundException e) {
            exitAfterPress("Файл не найден");
        }
        catch (IOException ex) {
            exitAfterPress("Ошибка записил файла");
        }
    }

}
