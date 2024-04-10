import Client.Executor;

import Server.Senders.StringSenderInterface;
import Server.Requesters.StringRequesterInterface;
import Utilities.InputUtil;

public class Main {
    public static void main(String[] args) {
        StringRequesterInterface input_r = new StringRequesterInterface() {
            public String request(String send_string) {
                return InputUtil.Input(send_string);
            }
        };

        StringRequesterInterface file_r = new StringRequesterInterface() {
            public String request(String send_string) {
                return "h";
            }
        };

        StringSenderInterface print_s = new StringSenderInterface() {
            public void send(String data) {
                System.out.println(data);
            }
        };

        Executor e = new Executor(input_r, file_r, print_s);
        e.executeStringCmd("info");
        e.executeStringCmd("show");
        e.executeStringCmd("help");

        e.Run();

        /*while (true) {
            String cmd = Input("Введите команду: ");
            e.executeStringCmd(cmd);
        }*/
    }
}