package chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextArea;

import javax.swing.JButton;

public class Frame {

	private JFrame frame;
	private JTextArea sendMessageArea = new JTextArea();
	private Sender sender =  new Sender();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea receivedMessage = new JTextArea();
		receivedMessage.setBounds(12, 55, 408, 69);
		frame.getContentPane().add(receivedMessage);
		
		sendMessageArea.setBounds(12, 149, 408, 69);
		frame.getContentPane().add(sendMessageArea);
		
		new Thread(new Receiver(receivedMessage)).start();
		
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(157, 226, 117, 25);
		frame.getContentPane().add(btnEnviar);
		btnEnviar.addActionListener(e ->
		{
		    sender.send(sendMessageArea.getText());
		    sendMessageArea.setText("");
		});
	}
}
