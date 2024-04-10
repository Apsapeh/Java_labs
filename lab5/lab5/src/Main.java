import Client.Client;
import Server.Server;
import General.Request.*;
import General.Response.*;

import java.util.Arrays;

/**
 * This class implements the RequestSenderInterface and is responsible for sending requests to the server.
 */
class RequestSender implements RequestSenderInterface {
    Server server = null;

    public void sendRequest(RequestEnum request, String[] args) {
        //System.out.println("Request: " + request + " Args: " + Arrays.toString(args));

        if (server != null)
            server.handleRequest(request, args);
    }

    public void setServer(Server server) {
        this.server = server;
    }
}

/**
 * This class implements the ResponseSenderInterface and is responsible for sending responses to the client.
 */
class ResponseSender implements ResponseSenderInterface {
    Client client = null;
    public void sendResponse(ResponseEnum response, String[] args) {
        //System.out.println("Resp: " + response + " Args: " + Arrays.toString(args));

        if (client != null)
            client.handleResponse(response, args);
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

public class Main {
    public static void main(String [] args) {
        Server server = new Server();
        Client client = new Client();

        RequestSender requestSender = new RequestSender();
        ResponseSender responseSender = new ResponseSender();
        requestSender.setServer(server);
        responseSender.setClient(client);

        client.setRequestSender(requestSender);
        server.setResponseSender(responseSender);

        client.run();
    }
}


/*

Принцип работы проекта:

Клиент:
1(1). Вводим команду
2(2). Парсим её (выполняем через CommandExecutor) и отправляем на сервер запрос
3(6). Получаем ответ от сервера

Сервер:
1(3). Получаем запрос
2(4). Обрабатываем его менеджером коллекции
3(5). Отправляем ответ клиенту

*/