import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerMain {
    private static final int countPool = 5;
    private static final int port = 666;


    public static void main(String[] args) throws IOException {
        ExecutorService poolExecutor = Executors.newFixedThreadPool(countPool);
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
                String line;
                while ((line = in.readLine()) != null) {
                    Future<List<Long>> future = poolExecutor.submit(new MyCallable(line));
                    out.println(String.format("Ответ: %s", future.get()));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
