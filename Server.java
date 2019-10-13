import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wujing
 * @since 2019/10/3 20:01
 */
public class Server {

    /**
     * 默认监听端口号
     */
    private static final int DEFAULT_PORT = 8080;

    /**
     * 是否处理
     */
    private static final boolean ACCEPT = true;

    /**
     * 指定绝对路径(默认相对路径)
     */
    private static final String ABSOLUTE_PATH = "";

    public static void main(String[] args) {

        boolean hasLength = args.length > 0;
        int port = hasLength ? Integer.parseInt(args[0]) : DEFAULT_PORT;

        // 创建服务器监听
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println(String.format("listen on: %d", port));
            while (ACCEPT) {
                Socket socket = server.accept();
                // 处理客户端的请求
                process(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void process(Socket socket) {
        new Thread(() -> {
            try {
                // 请求
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // 获得请求头(GET /... HTTP/1.1)
                String line = br.readLine();
                if (line == null) {
                    return;
                }
                String url = line.split("\\s")[1].substring(1);
                url = "".equals(url) ? "index.html" : url;

                String path = ABSOLUTE_PATH + url;
                // 中文路径
                // path = URLDecoder.decode(path, StandardCharsets.UTF_8.name())
                System.out.println(path);
                // 响应
                OutputStream os = socket.getOutputStream();
                File file = new File(path);
                if (file.exists()) {
                    // 读取文件
                    try (FileInputStream fis = new FileInputStream(file)) {
                        // 响应头与正文需要空行
                        os.write(("HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/html\r\n" +
                                "\r\n").getBytes());
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = fis.read(buffer)) != -1) {
                            os.write(buffer, 0, len);
                        }
                    }
                } else {
                    os.write(("HTTP/1.1 404 NOT FOUND\r\n" +
                            "Content-Type: text/html\r\n" +
                            "\r\n").getBytes());
                    // 文件不存在
                    os.write("<h1>404 - NOT FOUND</h1>\r\n".getBytes());
                }
                socket.shutdownOutput();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}