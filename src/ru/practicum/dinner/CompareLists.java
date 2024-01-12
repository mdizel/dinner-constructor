package ru.practicum.dinner;

import java.util.ArrayList;

public class CompareLists {                    // Сравниваем новый набор блюд с уже имеющимися в списке
    public boolean equals(ArrayList<ArrayList<String>> baseList, ArrayList<String> lineForAdd) {
        for (ArrayList<String> line : baseList) {
            boolean theSame = false;
            for (int i = 0; i < line.size(); i++) {
                if (!line.get(i).equals(lineForAdd.get(i))) {
                    theSame = false;
                    break;
                } else {
                    theSame = true;
                }
            }
            if (theSame) {
                return true;
            }
        }
        return false;
    }
}
