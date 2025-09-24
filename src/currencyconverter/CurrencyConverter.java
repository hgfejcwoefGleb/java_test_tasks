package currencyconverter;

import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== КОНВЕРТЕР ВАЛЮТ ===");
        System.out.println("Доступные валюты:");
        System.out.println("1. USD - Доллар США");
        System.out.println("2. EUR - Евро");
        System.out.println("3. GBP - Фунт стерлингов");
        System.out.println("4. JPY - Японская иена");
        System.out.println("5. RUB - Российский рубль");
        System.out.println("6. CNY - Китайский юань");

        System.out.print("Выберите исходную валюту (1-6): ");
        int fromCurrency = scanner.nextInt();

        System.out.print("Введите сумму: ");
        double amount = scanner.nextDouble();

        System.out.print("Выберите целевую валюту (1-6): ");
        int toCurrency = scanner.nextInt();

        double result = convertCurrency(amount, fromCurrency, toCurrency);

        String fromSymbol = getCurrencySymbol(fromCurrency);
        String toSymbol = getCurrencySymbol(toCurrency);

        System.out.printf("%.2f %s = %.2f %s%n", amount, fromSymbol, result, toSymbol);

        scanner.close();
    }
    private static double convertCurrency(double amount, int fromCurrency, int toCurrency) {
        if (fromCurrency == toCurrency) {
            return amount;
        }

        // Конвертируем сначала в USD, затем в целевую валюту
        double amountInUSD = convertToUSD(amount, fromCurrency);
        return convertFromUSD(amountInUSD, toCurrency);
    }

    private static double convertToUSD(double amount, int currency) {
        switch (currency) {
            case 1: return amount; // USD
            case 2: return amount * ExchangeRate.EUR_TO_USD; // EUR
            case 3: return amount * ExchangeRate.GBP_TO_USD; // GBP
            case 4: return amount * ExchangeRate.JPY_TO_USD; // JPY
            case 5: return amount * ExchangeRate.RUB_TO_USD; // RUB
            case 6: return amount * ExchangeRate.CNY_TO_USD; // CNY
            default: return amount;
        }
    }

    private static double convertFromUSD(double amount, int currency) {
        switch (currency) {
            case 1: return amount; // USD
            case 2: return amount * ExchangeRate.USD_TO_EUR; // EUR
            case 3: return amount * ExchangeRate.USD_TO_GBP; // GBP
            case 4: return amount * ExchangeRate.USD_TO_JPY; // JPY
            case 5: return amount * ExchangeRate.USD_TO_RUB; // RUB
            case 6: return amount * ExchangeRate.USD_TO_CNY; // CNY
            default: return amount;
        }
    }

    private static String getCurrencySymbol(int currency) {
        switch (currency) {
            case 1: return "USD";
            case 2: return "EUR";
            case 3: return "GBP";
            case 4: return "JPY";
            case 5: return "RUB";
            case 6: return "CNY";
            default: return "USD";
        }
    }
}
