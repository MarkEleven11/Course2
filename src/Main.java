
import Task.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static final Schedule SCHEDULE = new Schedule();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d.MM.yyy");
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
    }
    private static void addTask(Scanner scanner) {
        String title = readString("Введите название задачи", scanner);
        String description = readString("Введите описание задачи", scanner);
        LocalDateTime taskDate = readDateTime(scanner);
        Type type = readType(scanner);
        Repeatability repeatability = readRepeatability(scanner);
        Task task = switch (repeatability) {
            case SINGLE -> new SingleTask(title, description, taskDate, type);
            case DAILY -> new DaylyTask(title, description, taskDate, type);
            case WEEKLY -> new WeeklyTask(title, description, taskDate, type);
            case MONTHLY -> new MothlyTask(title, description, taskDate, type);
            case YEARLY -> new YearlyTask(title, description, taskDate, type);
        };
        SCHEDULE.addTask(task);
        System.out.println("Задача " + title + " добавлена!");
    }
    private static String readString(String message, Scanner scanner) {
        while (true) {
            System.out.println(message);
            String readString = scanner.nextLine();
            if (readString == null || readString.isEmpty()) {
                System.out.println("Введено пустое значение");
            } else {
                return readString;
            }
        }
    }
    private static LocalDate readDate(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Введите дату задачи в формате: День - Месяц - Год");
                String dateLine = scanner.nextLine();
                return LocalDate.parse(dateLine, DATE_FORMAT);
            } catch (DateTimeException a) {
                System.out.println("Введена дата в неверном формате");
            }
        }
    }
    private static LocalTime readTime(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Введите время задачи в формате: Час - Минута");
                String timeLine = scanner.nextLine();
                return LocalTime.parse(timeLine, TIME_FORMAT);
            } catch (DateTimeException a) {
                System.out.println("Время введено в неверном формате");
            }
        }
    }
    private static LocalDateTime readDateTime(Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        LocalTime localTime = readTime(scanner);
        return  localDate.atTime(localTime);
    }
    private static Type readType(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберите тип задачи");
                for (Type type : Type.values()) {
                    System.out.println(type.ordinal() + ". " + localyzeType(type));
                }
                System.out.println("Введите тип задачи:");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return Type.values() [ordinal];
            } catch (NumberFormatException e) {
                System.out.println("Введен неверный номер типа задачи");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Тип задачи не найден");
            }
        }
    }
    private static String localyzeType(Type type) {
        return switch (type) {
            case WORK -> "Рабочая задача";
            case PERSONAL -> "Личная задача";
        };
    }

    private static Repeatability readRepeatability(Scanner scanner) {
        while (true){
            try {
                System.out.println("Выберете повторяемость задачи");
                for (Repeatability repeatability : Repeatability.values()) {
                    System.out.println(repeatability.ordinal() + ". " + localizeRepeatability(repeatability));
                }
                System.out.println("Введите повторяемость");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return Repeatability.values()[ordinal];
            } catch (NumberFormatException e) {
                System.out.println("Тип задачи не найден");
            }
        }
    }

    private static String localizeRepeatability (Repeatability repeatability) {
        return switch (repeatability) {
            case SINGLE -> "Одноразовая";
            case DAILY -> "Ежедневная";
            case WEEKLY -> "Еженедельная";
            case MONTHLY -> "Ежемесячная";
            case YEARLY -> "Ежегодная";
        };
    }

    public static void removeTask (Scanner scanner) {
        System.out.println("Все задачи");
        for (Task task : SCHEDULE.getAllTask()) {
            System.out.println(task.getId() + " " + task.getTitle() + " " + localyzeType(task.getType()) + " " + localizeRepeatability(task.getRepeatability()));
        }
        System.out.println(SCHEDULE.getAllTask());
        while (true) {
            try {
                System.out.println("Выберите задачу чтобы удалить");
                String idLine = scanner.nextLine();
                int id = Integer.parseInt(idLine);
                SCHEDULE.removeTask(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ID задачи");
            } catch (IncorrectArgEx e) {
                System.out.println("Задача для удаления не найдена");
            }
        }
    }

}