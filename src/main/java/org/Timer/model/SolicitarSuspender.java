package org.Timer.model;

public class SolicitarSuspender {
	private Boolean suspendido;
	
	
	public	Boolean getSuspendido() {
		return suspendido;
	}
	
	public synchronized void setSuspendido(Boolean b) {
		this.suspendido=b;
		notifyAll(); //Notificas a todos los hilos que el valor ha cambiado
	}
	
	public synchronized void resume() {
		while(this.suspendido) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
