package org.Timer.model;

import java.util.Observable;
import java.util.Observer;

public class Contador extends Observable implements Runnable {
	private int contador;
	private String nombre;
	private SolicitarSuspender suspendido = new SolicitarSuspender();
	
	private Observer observer;
	
	public SolicitarSuspender getSuspendido() {
		return suspendido;
	}

	public void setSuspendido(SolicitarSuspender suspendido) {
		this.suspendido = suspendido;
	}

	public Contador(int contador, String nombre) {
		this.contador = contador;
		this.nombre = nombre;
		this.suspendido.setSuspendido(false);
		
	}
	
	public void run() {
		while(!this.suspendido.getSuspendido()) {
			System.out.println(contador);
			contador++;
			notifyObservers();
			try {
				Thread.sleep(1000);
				this.suspendido.resume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public synchronized void observe(Observer o) {
		this.observer = o;
	}
	
	public void notifyObservers() {
		if(observer != null) {
			observer.update(this, contador);
		}
	}


	public void reset() {
	
			this.contador = 0;
			this.suspendido.setSuspendido(true);
	
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
}