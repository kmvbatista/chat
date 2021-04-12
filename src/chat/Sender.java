package chat;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

import javax.swing.JTextArea;

public class Sender{
	
	// Socket usado para enviar
	private MulticastSocket sendSocket;
	// Porta utilizada;
	private int port = 6001;
	// Para quem
	private InetAddress toHost;
	
	public Sender() {
		try {
			sendSocket = new MulticastSocket();
			// configurar socket para enviar em broadCast
			// mandar para endereco de broadcast
			toHost = InetAddress.getByName("225.0.0.20");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void send(String message) {
			if(message.equals("exit")) return;
			// Cria datagrama para carregar a mensagem
			DatagramPacket clientDatagram = new DatagramPacket(message.getBytes(), 
					message.getBytes().length,
					toHost, port);
			// Envia mensagem
			try {
				sendSocket.send(clientDatagram);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// fecha socket e libera porta
	}
	
