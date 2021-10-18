import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    private static final String ip = "127.0.0.1";
    private static final int port = 666;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(ip, port);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()
                ));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(
                             socket.getOutputStream()
                     ), true
             );
             Scanner scanner = new Scanner(System.in)) {
            String line;
            String msg;
            while (true) {
                System.out.print("Введите значение (для завершения введите \"end\"): ");
                msg = scanner.nextLine();

                out.println(msg);
                if (msg.equals("end")) {
                    System.out.println("Клиентское приложение было остановлено");
                    System.err.println("Не забудь остановить серверное приложение");
                    break;
                }

                while ((line = in.readLine()) == null) {
                    Thread.sleep(500);
                }
                System.out.println(line);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
