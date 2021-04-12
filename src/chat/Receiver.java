package chat;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Receiver implements Runnable {
	private JTextArea receivedMessageTextArea;
	// Socket usado para enviar
	private MulticastSocket receiveSocket;
	// Porta utilizada;
	private int port = 6000;

	public Receiver(JTextArea receivedMessageTextArea) {
		this.receivedMessageTextArea = receivedMessageTextArea;
		try {
			receiveSocket = new MulticastSocket(port);
			receiveSocket.joinGroup(InetAddress.getByName("225.0.0.20"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Receiver Ready");
		// Datagrama vazio para receber mensagem
		byte[] buffer = new byte[1000];
		DatagramPacket clientDatagram = new DatagramPacket(buffer, buffer.length);

		while (true) {
			try {
				receiveSocket.receive(clientDatagram);
				System.out.println("entrou");
				String msg = new String(buffer);
				SwingUtilities.invokeLater(() -> receivedMessageTextArea.append(msg+"\n"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
