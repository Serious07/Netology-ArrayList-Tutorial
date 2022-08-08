import javax.annotation.processing.SupportedSourceVersion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> shoppingList = new ArrayList<String>();

    private static BufferedReader reader;
    public static void main(String[] args) {
        String consoleLine;
        int commandIndex;

        while (true){
            System.out.println();
            System.out.println("Список операций: ");
            System.out.println("1. Добавить.");
            System.out.println("2. Показать.");
            System.out.println("3. Удалить.");
            System.out.println("4. Найти.");

            System.out.println();
            System.out.println("Выберите операцию:");

            try {
                reader = new BufferedReader(new InputStreamReader(System.in));
                consoleLine = reader.readLine();
                commandIndex = Integer.parseInt(consoleLine);

                switch (commandIndex){
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        showAllProducts();
                        break;
                    case 3:
                        removeProduct();
                        break;
                    case 4:
                        findProducts();
                        break;
                    default:
                        warningUseOnlyNumbers();
                        break;
                }
            } catch (Exception e) {
                if(e instanceof NumberFormatException){
                    warningUseOnlyNumbers();
                } else {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void warningUseOnlyNumbers(){
        System.out.println("Для выбора команды используйте цифры от 1 до 4");
    }

    private static void addProduct(){
        System.out.println();
        System.out.println("Какую покупку хотите добавить?");

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            String consoleLine = reader.readLine();

            shoppingList.add(consoleLine);
            System.out.println("Итого в списке: " + shoppingList.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void removeProduct(){
        showListOfProducts(true);
        System.out.println("Какую хотите удалить? Введите номер или название");
        System.out.println();

        String consoleLine = "-";

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            consoleLine = reader.readLine();
            int number = Integer.parseInt(consoleLine);

            if(number > 0 && number <= shoppingList.size()) {
                String item = shoppingList.get(number - 1);

                shoppingList.remove(number - 1);
                System.out.println("Покупка " + item + " удалена, список покупок:");
                showListOfProducts(false);
            } else {
                System.out.println("Покупка не удалена, так как не найдена покупка с индексом: " + number + "!");
            }
        } catch (Exception e) {
            if(e instanceof NumberFormatException){
                if(shoppingList.contains(consoleLine)){
                    shoppingList.remove(consoleLine);

                    System.out.println("Покупка " + consoleLine + " удалена, список покупок:");
                    showListOfProducts(false);
                } else {
                    System.out.println("Ошибка удаления! Продукта " + consoleLine + " нет в списке!");
                }
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    private static void showAllProducts(){
        showListOfProducts(true);
    }

    private static void findProducts(){
        System.out.println();
        System.out.println("Введите текст для поиска:");

        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            String consoleLine = reader.readLine();

            System.out.println("Найдено:");

            int counter = 0;

            for (int i = 0; i < shoppingList.size(); i++) {
                String itemLowCase = shoppingList.get(i).toLowerCase();
                String consoleLineLowCase = consoleLine.toLowerCase();

                if(itemLowCase.contains(consoleLineLowCase)){
                    counter++;
                    System.out.println((i + 1) + ". " + shoppingList.get(i));
                }
            }

            if(counter == 0){
                System.out.println("Ничего не найдено!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showListOfProducts(boolean showTitle){
        if (showTitle) {
            System.out.println();
            System.out.println("Список покупок:");
        }

        for(int i = 0; i < shoppingList.size(); i++){
            System.out.println((i + 1) + ". " + shoppingList.get(i));
        }
    }
}