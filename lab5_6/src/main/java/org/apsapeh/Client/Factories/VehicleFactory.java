package org.apsapeh.Client.Factories;

import org.apsapeh.General.Task.FuelType;
import org.apsapeh.General.Task.VehicleType;

import java.util.Scanner;

public class VehicleFactory {
    public static String[] getInteractiveVehicleFields(Scanner scanner, boolean printFieldsNames) {
        String name;
        Integer x;
        int y;
        Long enginePower;
        VehicleType type;
        FuelType fuelType;

        do {
            if (printFieldsNames)
                System.out.print("Введите имя: ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Имя не может быть пустым");
                continue;
            }
            break;
        } while (true);

        do {
            try {
                if (printFieldsNames)
                    System.out.print("Введите x: ");
                x = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа");
            }
        } while (true);

        do {
            try {
                if (printFieldsNames)
                    System.out.print("Введите y: ");
                y = Integer.parseInt(scanner.nextLine());

                if (y <= -282) {
                    System.out.println("'y' должно быть больше -282");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа");
            }
        } while (true);

        do {
            try {
                if (printFieldsNames)
                    System.out.print("Введите мощность двигателя: ");
                enginePower = Long.parseLong(scanner.nextLine());

                if (enginePower <= 0) {
                    System.out.println("'enginePower' не может быть <= 0");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа");
            }
        } while (true);

        do {
            if (printFieldsNames) {
                System.out.println("Возможные варианты типа транспорта:");
                for (VehicleType vehicleType : VehicleType.values())
                    System.out.println("\t" + vehicleType);
            }

            try {
                if (printFieldsNames)
                    System.out.print("Введите тип транспорта: ");
                type = VehicleType.valueOf(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Такого типа транспорта нет");
            }
        } while (true);

        do {
            if (printFieldsNames) {
                System.out.println("Возможные варианты типа топлива:");
                for (FuelType _fuelType : FuelType.values())
                    System.out.println("\t" + _fuelType);
            }

            try {
                if (printFieldsNames)
                    System.out.print("Введите тип топлива: ");
                fuelType = FuelType.valueOf(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Такого типа топлива нет");
            }
        } while (true);

        return new String[]{
                name,
                x.toString(),
                Integer.toString(y),
                enginePower.toString(),
                type.toString(),
                fuelType.toString()
        };
    }
}
