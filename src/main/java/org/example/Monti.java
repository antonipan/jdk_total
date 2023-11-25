package org.example;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Monti {
    private static final String CAR = "Car";
    private static final String GOAT = "Goat";

    private int indexCar;
    private int quantityDoors;
    private Map<Integer, String> doors;
    private Map<Integer, String> statistic;
    private Random random;
    private Repository repository;
    private int resultLast;



    public Monti (int quantityDoors, Repository repository ){
        this.quantityDoors = quantityDoors;
        this.doors = new HashMap<>(quantityDoors/2);
        this.random = new Random();
        this.repository = repository;
        this.resultLast = repository.loadCount();
        this.statistic = repository.load();
    }

    /**
     * Метод инициализации игры
     */
    public void initGameMonti () {
        System.out.println("Предыдущие раунды: ");
        showDoors(statistic, "Раунд");
        createMonti();
        int selectDoor = selectDoor();
        showSelectDoor(selectDoor);
        int selectDoor2 = selectDoor();
        showWin(selectDoor2);
    }

    /**
     * метод создания игры
     */
    private void createMonti () {
        indexCar = random.nextInt(1, quantityDoors + 1);
//        System.out.println("Index Car: " + indexCar);
        for (int i = 1; i <= quantityDoors; i++) {
            if (i == indexCar) {
                doors.put(i, CAR);
            } else {
                doors.put(i, GOAT);
            }
        }
//        showDoors();
        System.out.print("Выберите одну из " + quantityDoors + " дверей: ");
    }

    /**
     * Метод выбора дверей
     * @return - номер двери.
     */
    private Integer selectDoor () {
        Scanner scanner = new Scanner(System.in);
        int door = scanner.nextInt();
        if (door > quantityDoors || door <= 0) {
            door = 1;
            System.out.println("Мы выбрали дверь за вас. ");
            return door;
        }
        return door;
    }

    /**
     * Метод показа оставшихся дверей.
     * @param selectDoor - выбранный участником номер дверей
     */
    private void showSelectDoor (int selectDoor) {
        if (selectDoor == indexCar) {
            int indexGoat;
            do {
                indexGoat = random.nextInt(1, quantityDoors + 1);
            } while (indexGoat == indexCar);
            System.out.printf("Выберите дверь #%d или оставите прежнюю #%d?\n: ", indexGoat, selectDoor);
        } else {
            System.out.printf("Выберите дверь #%d или оставите прежнюю #%d?\n: ", indexCar, selectDoor);
        }
    }

    /**
     * показывает результат игры
     * @param selectDoor - выбранная дверь второй раз,
     */
    private void showWin(int selectDoor) {
        if (selectDoor == indexCar) {
            System.out.printf("Вы выиграли. Там %s", doors.get(selectDoor));
            repository.save(resultLast, "победа");
        } else {
            System.out.printf("Вы проиграли. Авто было в двери №%d", indexCar);
            repository.save(resultLast, "провал");

        }
//        showDoors();
    }

    /**
     * Вспомогательный метод показывает все двери, и что в них находится.
     */
    public void showDoors (Map <Integer, String> doors, String some) {
        for (Map.Entry<Integer, String> entry: doors.entrySet()
             ) {
            System.out.printf("%s №%d: %s\n", some, entry.getKey(), entry.getValue());
        }
    }
}
