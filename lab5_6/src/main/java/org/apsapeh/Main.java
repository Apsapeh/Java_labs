package org.apsapeh;

import org.apsapeh.Client.Client;
import org.apsapeh.Server.Server;

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

        //client.run();
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