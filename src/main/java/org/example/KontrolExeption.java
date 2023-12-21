package org.example;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class KontrolExeption {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные (Фамилия Имя Отчество датарождения номертелефона пол):");
        String input = scanner.nextLine();

        String[] data = input.split(" ");

        // Проверяем количество данных
        if (data.length != 6) {
            System.out.println("Ошибка: количество данных не соответствует требуемому");
            return;
        }

        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        String birthDate = data[3];
        long phoneNumber;
        char gender;

        try {
            phoneNumber = Long.parseLong(data[4]);
            gender = data[5].charAt(0);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат номера телефона");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка: неверный формат пола");
            return;
        }

        // Проверяем формат даты рождения
        if (!birthDate.matches("\\d{2}.\\d{2}.\\d{4}")) {
            System.out.println("Ошибка: неверный формат даты рождения");
            return;
        }

        // Создаем строку для записи в файл
        String record = lastName + firstName + middleName + birthDate + " " + phoneNumber + gender;

        // Создаем файл с названием фамилии
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastName + ".txt", true))) {
            writer.write(record);
            writer.newLine();
            System.out.println("Данные успешно записаны в файл");
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл");
            e.printStackTrace();
        }
    }
}