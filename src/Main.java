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

        System.out.println("Список операций: ");
        System.out.println("1. Добавить.");
        System.out.println("2. Показать.");
        System.out.println("3. Удалить.");

        while (true){
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
        System.out.println("Для выбора команды используйте цифры от 1 до 3");
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