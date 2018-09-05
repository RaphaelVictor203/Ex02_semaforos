package controller;

import java.awt.Rectangle;
import java.util.concurrent.Semaphore;

import javax.swing.JLabel;

public class ThreadCarro extends Thread{
	
	private String sentido;
	private int vl;
	private Rectangle posCruzamento;
	private JLabel lblCarro;
	private Semaphore semaforo;
	private Rectangle tamanhoJFrame;
	
	public ThreadCarro(String sentido, int vl, Rectangle posCruzamento, JLabel lblCarro, Semaphore semaforo, Rectangle tamanhoJFrame) {
		this.sentido = sentido;
		this.vl = vl;
		this.posCruzamento = posCruzamento;
		this.lblCarro = lblCarro;
		this.semaforo = semaforo;
		this.tamanhoJFrame = tamanhoJFrame;
	}
	
	
	public void run() {
		ligar();
	}
	
	public void ligar() {
		if(sentido.toLowerCase().equals("norte")) {
			while(lblCarro.getBounds().y > (posCruzamento.y + 60)){
				mover(0, vl*(-1));
			}
		}else 
		if(sentido.toLowerCase().equals("sul")){
			while(lblCarro.getBounds().y < (posCruzamento.y - 110)) {
				mover(0, vl);
			}
		}else
		if(sentido.toLowerCase().equals("leste")) {
			while(lblCarro.getBounds().x < (posCruzamento.x - 100)) {
				mover(vl, 0);
			}
		}else
		if(sentido.toLowerCase().equals("oeste")) {
			while(lblCarro.getBounds().x > (posCruzamento.x + 100)) {
				mover(vl*(-1), 0);
			}
		}
		try {
			semaforo.acquire();
			atravessar();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
	}
	
	private void atravessar() {
		int vlAtual;
		if(sentido.toLowerCase().equals("norte")) {
			while(lblCarro.getBounds().y > 0){
				vlAtual = (int)((Math.random()*vl));
				mover(0, vlAtual*(-1));
			}
		}else 
		if(sentido.toLowerCase().equals("sul")){
			while(lblCarro.getBounds().y < (tamanhoJFrame.getHeight()-lblCarro.getHeight())) {
				vlAtual = (int)((Math.random()*vl));
				mover(0, vlAtual);
			}
		}else
		if(sentido.toLowerCase().equals("leste")) {
			while(lblCarro.getBounds().x < (tamanhoJFrame.getWidth()-lblCarro.getWidth())) {
				vlAtual = (int)((Math.random()*vl));
				mover(vlAtual, 0);
			}
		}else
		if(sentido.toLowerCase().equals("oeste")) {
			while(lblCarro.getBounds().x > 0) {
				vlAtual = (int)((Math.random()*vl));
				mover(vlAtual*(-1), 0);
			}
		}
	}
	
	public void mover(int x, int y) {
		lblCarro.setLocation(lblCarro.getBounds().x + x, lblCarro.getBounds().y + y);
		try {
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
