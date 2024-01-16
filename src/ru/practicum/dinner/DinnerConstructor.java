package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    HashMap<String, ArrayList<String>> dishMap = new HashMap<>();
    Random random = new Random();

    public boolean addNewDish(String typeOfDish, String nameOfDish) {           // Добавляем блюда
        boolean dishExist = false;
        if (dishMap.containsKey(typeOfDish)) {
            if (dishMap.get(typeOfDish).contains(nameOfDish)) {
                dishExist = true;
            } else {
                dishMap.get(typeOfDish).add(nameOfDish);
            }
        } else {
            ArrayList<String> dishList = new ArrayList<>();
            dishList.add(nameOfDish);
            dishMap.put(typeOfDish, dishList);
        }
        return dishExist;
    }

    public int getMaxCombo() {                     // Вычисляем возможное количество неповторяющихся наборов
        ArrayList<String> comboRequest = Main.getComboRequest();
        int maxCombo = 1;
        for (String dishType : comboRequest) {
            maxCombo = maxCombo * dishMap.get(dishType).size();
        }
        return maxCombo;
    }

    public HashMap<String, ArrayList<String>> getDishMap() {
        return dishMap;
    }

    public ArrayList<ArrayList<String>> formDishCombos(int quantityCombos) {                 //Формируем наборы
        int maxCombos = getMaxCombo();
        int count = 0;
        if (quantityCombos > maxCombos) {
            quantityCombos = maxCombos;              //Ограничиваем выбор максимальным значением комбинаций наборов
        }
        ArrayList<ArrayList<String>> combosOffer = new ArrayList<>();
        ArrayList<String> comboRequest = Main.getComboRequest();
        while (count < quantityCombos) {
            ArrayList<String> dishSet = new ArrayList<>();
            for (String dishType : comboRequest) {
                int index = random.nextInt(dishMap.get(dishType).size());
                String randomDish = dishMap.get(dishType).get(index);
                dishSet.add(randomDish);
            }
            if (count > 0 && CompareLists.equals(combosOffer, dishSet)) {
                continue;
            }
            combosOffer.add(dishSet);
            count++;
        }
        comboRequest.clear();
        return combosOffer;
    }
}
