import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MainClass {
    public static void main(String[] args) {

        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new LandmarkBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("Landmark Bot startingggggg....");
    }
}
