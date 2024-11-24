import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {
            InetAddress serverAddress = InetAddress.getByName("localhost");

            while (true) {
                System.out.print("Nhập số nguyên dương n: ");
                String n = scanner.nextLine();

                // Gửi dữ liệu lên Server
                byte[] sendBuffer = n.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 12345);
                clientSocket.send(sendPacket);

                // Nhận kết quả từ Server
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Kết quả từ Server: " + response);

                // Hỏi người dùng có tiếp tục hay không
                System.out.print("Bạn có muốn tiếp tục không? (y/n): ");
                if (!scanner.nextLine().equalsIgnoreCase("y")) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
