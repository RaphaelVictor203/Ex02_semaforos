package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ThreadCarro;

import javax.swing.JLabel;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon img = new ImageIcon("veiculos\\Carro1.png");
		img.setImage(img.getImage().getScaledInstance(70, 60, 100));
		JLabel lblCarro1 = new JLabel(img);
		lblCarro1.setBounds(203, 0, 70, 60);
		contentPane.add(lblCarro1);
		
		img = new ImageIcon("veiculos\\Carro2.png");
		img.setImage(img.getImage().getScaledInstance(80, 90, 100));
		JLabel lblCarro2 = new JLabel(img);
		lblCarro2.setBounds(470, 180, 80, 90);
		contentPane.add(lblCarro2);
		
		img = new ImageIcon("veiculos\\Carro3.png");
		img.setImage(img.getImage().getScaledInstance(70, 60, 100));
		JLabel lblCarro3 = new JLabel(img);
		lblCarro3.setBounds(250, 454, 70, 60);
		contentPane.add(lblCarro3);
		
		img = new ImageIcon("veiculos\\Carro4.png");
		img.setImage(img.getImage().getScaledInstance(70, 60, 100));
		JLabel lblCarro4 = new JLabel(img);
		lblCarro4.setBounds(12, 243, 70, 60);
		contentPane.add(lblCarro4);
		
		Rectangle cruzamento = new Rectangle(229, 243, 56, 16);
		int vl = 30;
		Semaphore semaforo = new Semaphore(1);
		
		Thread carro1 = new ThreadCarro("sul", vl, cruzamento, lblCarro1, semaforo, getBounds());
		carro1.start();
		Thread carro2 = new ThreadCarro("oeste", vl, cruzamento, lblCarro2, semaforo, getBounds());
		carro2.start();
		Thread carro3 = new ThreadCarro("norte", vl, cruzamento, lblCarro3, semaforo, getBounds());
		carro3.start();
		Thread carro4 = new ThreadCarro("leste", vl, cruzamento, lblCarro4, semaforo, getBounds());
		carro4.start();
	}
}
