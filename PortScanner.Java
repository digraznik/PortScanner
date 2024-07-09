import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PortScanner {
    private static final Map<Integer, String> COMMON_PORTS = new HashMap<>();
    static {
        COMMON_PORTS.put(21, "FTP");
        COMMON_PORTS.put(22, "SSH");
        COMMON_PORTS.put(23, "Telnet");
        COMMON_PORTS.put(25, "SMTP");
        COMMON_PORTS.put(53, "DNS");
        COMMON_PORTS.put(80, "HTTP");
        COMMON_PORTS.put(110, "POP3");
        COMMON_PORTS.put(135, "EPMAP/RPC");
        COMMON_PORTS.put(143, "IMAP");
        COMMON_PORTS.put(443, "HTTPS");
        COMMON_PORTS.put(445, "SMB");
        COMMON_PORTS.put(3306, "MySQL");
        COMMON_PORTS.put(3389, "RDP");
    }

    public static void main(String[] args) {
        String host = "localhost"; // Cambia esto al host que quieras escanear
        int startPort = 1;
        int endPort = 65535;

        System.out.println("Iniciando escaneo de puertos en " + host);

        for (int port = startPort; port <= endPort; port++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), 200); 
                String service = identifyService(port);
                System.out.println("Puerto " + port + " está abierto - Servicio: " + service);
                socket.close();
            } catch (IOException e) {
                
            }
        }

        System.out.println("Escaneo completado");
    }

    private static String identifyService(int port) {
        return COMMON_PORTS.getOrDefault(port, "Desconocido");
    }
}