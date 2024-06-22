package ar.edu.utn.frba.dds.models.entities.contacto.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class ServicioTelegram extends TelegramLongPollingBot {
    private static ServicioTelegram instancia = null;;

    private static final String BOT_TOKEN = "7487258506:AAFtiRE_gNN_MRHPWdmsE5mU-JbeKL0pJIA";
    private static final String BOT_USERNAME = "@TpDds_bot";

    public ServicioTelegram() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void enviarTelegram(String destinatario, String mensaje) {
        SendMessage message = new SendMessage();
        message.setChatId(destinatario); // El ID del chat puede ser el ID del usuario o el username del chat
        message.setText("\n\n" + mensaje);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public static ServicioTelegram getInstance() {
        if (instancia == null) {
            instancia = new ServicioTelegram();
        }
        return instancia;
    }

    @Override
    public void onUpdateReceived(org.telegram.telegrambots.meta.api.objects.Update update){}// No es necesario implementarlo

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}
