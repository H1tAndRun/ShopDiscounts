
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Shop {
    public static void main(String[] args) {
        /*task1();
        task2();*/
        task3();
        //Дано: папка data, в которой находиться 1000 чеков
        //Чек состоит из: наименования товара, цены, его себестоимости, количества купленного товара, категория клиента
        //Скидки по категориям клиентов: для студентов 5%, для пенсионеров 10%, для врачей 15%

        //Задача №1
        //Из папки data прочитать все чеки, и составить итоговый отчет для генерального директора:
        //Доходы ... Расходы ... Прибыль ...

        //task1();
        //task2();

        //Задача №2
        //Сделать итоговый отчет: товар - сколько куплено
        //Ожидаемый результат:
        //cola - 200 шт
        //milk - 150 шт
        //beer - 100 шт

        //Задача №3
        //Узнать сколько денег потратили покупатели в разрезе по категориям клиентов: обычный, студент, пенсионер, врач
        //Ожидаемый результат:
        //Врач - 240023.00
        //Студент - 5323255.20
        //Обычный - 144422.30
        //Пенсионер - 98550.00
    }

    public static void task1() {
        double priceSum = 0;
        double selfPriceSum = 0;

        for (int i = 1; i < 1001; i++) {
            String path = "/Users/antonleonov/ProjectJava/Shop/src/data/" + i + "_report.txt";

            try {
                FileReader reader = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(reader);
                int numberLine = 0;

                while (bufferedReader.ready()) {
                    if (numberLine == 0) {
                        numberLine++;
                        bufferedReader.readLine();
                        continue;
                    }

                    String line = bufferedReader.readLine();

                    if (line.length() == 0) {
                        continue;
                    }

                    String[] array = line.split(";");

                    DiscountTypes discountTypes = DiscountTypes.valueOf(array[4].toUpperCase());
                    int count = Integer.parseInt(array[3]);

                    priceSum += Double.parseDouble(array[1]) * count * discountTypes.getDiscount();
                    selfPriceSum += Double.parseDouble(array[2]) * count;
                }
                bufferedReader.close();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Файл не существует");
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла");
            }
        }

        System.out.printf("Доход: " + priceSum);
        System.out.printf(" Расход: " + selfPriceSum);
        System.out.printf(" Прибыль: " + (priceSum - selfPriceSum));
    }

    public static void task2() {
        Product[] products = {};

        for (int i = 1; i < 1001; i++) {
            String path = "/Users/antonleonov/ProjectJava/Shop/src/data/" + i + "_report.txt";

            try {
                FileReader reader = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(reader);
                int numberLine = 0;

                while (bufferedReader.ready()) {
                    if (numberLine == 0) {
                        numberLine++;
                        bufferedReader.readLine();
                        continue;
                    }
                    String line = bufferedReader.readLine();
                    String[] strings = line.split(";");
                    Product product = new Product(strings[0], Integer.parseInt(strings[3]));
                    products = checkProductInArray(product, products);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл не существует");
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла");
            }
        }

        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static Product[] checkProductInArray(Product product, Product[] array) {
        for (Product element : array) {
            if (element.getName().equals(product.getName())) {
                int arrayCount = element.getCount();
                int productCount = product.getCount();
                element.setCount(arrayCount + productCount);
                return array;
            }
        }
        Product[] newArray = new Product[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = product;
        return newArray;
    }

    public static void task3() {
        Buyer buyers[] = {};
        for (int i = 1; i < 1001; i++) {
            try {
                String path = "/Users/antonleonov/ProjectJava/Shop/src/data/" + i + "_report.txt";
                FileReader reader = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(reader);
                int numberLine = 0;
                while (bufferedReader.ready()) {
                    if (numberLine == 0) {
                        bufferedReader.readLine();
                        numberLine++;
                        continue;
                    }
                    String line = bufferedReader.readLine();
                    String arr[] = line.split(";");
                    double sum = (Double.parseDouble(arr[1]) * Double.parseDouble(arr[3])) * (Discount.valueOf(arr[4]).getDiscount());
                    Buyer human = new Buyer(arr[4], sum);
                    buyers = iSDiscount(human, buyers);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (Buyer buyer : buyers) {
            System.out.println("Покупатель " + buyer.getName() + " потратил " + buyer.getExp());
        }
    }

    public static Buyer[] iSDiscount(Buyer buyer, Buyer[] buy) {
        for (Buyer element : buy) {
            if (element.getName().equals(buyer.getName())) {
                double b1 = buyer.getExp();
                double b2 = element.getExp();
                element.setExp((b1 + b2));
                return buy;
            }
        }

        Buyer[] newBuyer = new Buyer[buy.length + 1];
        for (int i = 0; i < buy.length; i++) {
            newBuyer[i] = buy[i];
        }
        newBuyer[buy.length] = buyer;
        return newBuyer;
    }


}


