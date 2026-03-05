import java.util.Locale;
import java.util.Random;


public class RundomNumbers {

    static Random random = new Random();

    // Генерация случайного float
    public static float randomFloat = Math.round((10 + random.nextFloat() * 998000) * 100) / 100.0f;
    // Форматирование числа: точка → заменяется на запятую
    public static String randomNumberFloat = String.format(Locale.ENGLISH, "%.2f", randomFloat).replace(',', '.');

    // Генерация случайного long
    public static long random10Digit = 1_000_000_000L + (long)(random.nextDouble() * 9_000_000_000L);
    public static String randomNumber10 = String.valueOf(random10Digit);

    // Генерация случайного long
    public static long random10Digit2 = 1_000_000_00L + (long)(random.nextDouble() * 9_000_000_00L);
    public static String randomNumber9 = String.valueOf(random10Digit2);
}
