package ru.practicum.dinner;

import java.util.Scanner;

public class Main {
    static DinnerConstructor dinnerConst;
    static Scanner scanner;

    public static void main(String[] args) {
        dinnerConst = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Такой команды нет.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        while (true) {
            System.out.println("Для просмотра списка и типов блюд нажмите 5.");
            System.out.println("Введите тип блюда или нажмите 3 для выхода в главное меню:");
            String typeOfDish = scanner.nextLine();
            if (typeOfDish.equals("3")) {
                return;
            } else if (typeOfDish.equals("5")) {
                System.out.println("Список типов и блюд:\n" + dinnerConst.dishMap);
            } else {
                System.out.println("Введите название блюда:");
                String nameOfDish = scanner.nextLine();
                dinnerConst.addNewDish(typeOfDish, nameOfDish);
                if(!dinnerConst.dishExist) {
                    System.out.println("Блюдо " + nameOfDish + "  занесено под типом " + typeOfDish + ".");
                }
            }
        }
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        if (!scanner.hasNextInt()) {
            System.out.println("Неправильный ввод, введите положительное число.");
            scanner.nextLine();
        } else {
            int numberOfCombos = scanner.nextInt();
            if (numberOfCombos <= 0) {
                System.out.println("Число наборов должно быть больше нуля.");
                scanner.nextLine();
                return;
            }
            scanner.nextLine();
            System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). " +
                    "Для завершения ввода введите пустую строку");
            String nextItem = scanner.nextLine();
            while (!nextItem.isEmpty()) {
                dinnerConst.writeComboRequest(nextItem);
                nextItem = scanner.nextLine();
            }
            System.out.println("Ваш выбор: " + dinnerConst.comboRequest);
            if (numberOfCombos > dinnerConst.getMaxCombo()) {
                System.out.println("Из ваших блюд можно составить только " + dinnerConst.getMaxCombo() + " комбо.");
            }
            dinnerConst.setDishCombos(numberOfCombos);
        }
    }
}
