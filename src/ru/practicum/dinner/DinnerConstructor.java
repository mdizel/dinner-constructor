package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    HashMap<String, ArrayList<String>> dishMap = new HashMap<>();
    ArrayList<String> comboRequest = new ArrayList<>();
    Random random = new Random();
    CompareLists compareLists = new CompareLists();
    boolean dishExist = false;

    public void addNewDish(String typeOfDish, String nameOfDish) {           // Добавляем блюда
        dishExist = false;
        if (dishMap.containsKey(typeOfDish)) {
            if(dishMap.get(typeOfDish).contains(nameOfDish)){
                System.out.println("Такое блюдо уже есть.");
                dishExist = true;
            } else {
                dishMap.get(typeOfDish).add(nameOfDish);
            }
        } else {
            ArrayList<String> dishList = new ArrayList<>();
            dishList.add(nameOfDish);
            dishMap.put(typeOfDish, dishList);
        }
    }

    public int getMaxCombo() {                     // Вычисляем возможное количество неповторяющихся наборов
        int maxCombo = 1;
        for (String dishType : comboRequest) {
            maxCombo = maxCombo * dishMap.get(dishType).size();
        }
        return maxCombo;
    }

    public void writeComboRequest(String item) {      // Запрос типов блюд в наборах
        if (!dishMap.containsKey(item)) {
            System.out.println("Такого типа блюд у нас нет.");
        } else {
            comboRequest.add(item);
        }
    }

    public void setDishCombos(int quantityCombos) {                 //Формируем наборы
        int maxCombos = getMaxCombo();
        int count = 0;
        if (quantityCombos > maxCombos) {
            quantityCombos = maxCombos;              //Ограничиваем выбор максимальным значением комбинаций наборов
        }
        ArrayList<ArrayList<String>> combosOffer = new ArrayList<>();
        while (count < quantityCombos) {
            ArrayList<String> dishSet = new ArrayList<>();
            for (String dishType : comboRequest) {
                int index = random.nextInt(dishMap.get(dishType).size());
                String randomDish = dishMap.get(dishType).get(index);
                dishSet.add(randomDish);
            }
            if (count > 0 && compareLists.equals(combosOffer, dishSet)) {
                continue;
            }
            combosOffer.add(dishSet);
            count++;
        }
        for (int i = 0; i < combosOffer.size(); i++) {
            System.out.println("Комбо " + (i + 1));
            System.out.println(combosOffer.get(i));
        }
        comboRequest.clear();
    }
}
