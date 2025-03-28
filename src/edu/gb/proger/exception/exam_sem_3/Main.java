package edu.gb.proger.exception.exam_sem_3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String surname;
        String name;
        String middleName;
        String birthDate;
        Long phoneNumber;
        Character gender;

        try {

            System.out.println("Введите данные о пользователе в формате: \"Фамилия Имя Отчество датарождения номертелефона пол\".");
            String inputString = scanner.nextLine();
            //String inputString = "Стахмич Николай Владимирович 25.01.1985 79030010203 m";
            validateInputString(inputString);

            String[] inputStringSplited = inputString.split(" ");
            surname = inputStringSplited[0];
            name = inputStringSplited[1];
            middleName = inputStringSplited[2];
            birthDate = inputStringSplited[3];
            validateBirthDate(birthDate);
            phoneNumber = Long.parseLong(inputStringSplited[4]);
            validateGender(inputStringSplited[5]);
            gender = inputStringSplited[5].charAt(0);

            String formatted = String.format("<%s><%s><%s><%s><%d><%c>", surname, name, middleName, birthDate, phoneNumber, gender);

            saveData(surname, formatted);

        } catch (IllegalArgumentException | DateTimeParseException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private static void validateInputString(String string) {

        if (string.split(" ").length != 6) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }

    }

    private static void validateBirthDate(String string) throws DateTimeParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(string, formatter);

    }

    private static void validateGender(String gender) throws IllegalArgumentException {

        if (gender.length() != 1 ||
                !(gender.equalsIgnoreCase("f")
                        || gender.equalsIgnoreCase("m"))) {
            throw new IllegalArgumentException("Invalid gender. Expected 'f' or 'm'.");
        }

    }

    private static void saveData(String fileName, String data) throws IOException {

        String filePath = fileName + ".txt";
        File file = new File(filePath);

        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.append(data);
        bufferedWriter.newLine();

        fileWriter.close();

    }

}
