package org.Timer;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import org.Timer.model.Contador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class TimerController implements Observer {
	@FXML
	private Button iniciaButton;
	@FXML
	private Button paraButton;
	@FXML
	private Button reiniciaButton;
	@FXML
	private Text timerField;

	private Integer seconds=0, minutes=0, hours=0;

	Contador timer = new Contador(0, "pipo");
	Thread hilito = new Thread(timer);

	@FXML
	private void switchToSecondary() throws IOException {

	}

	@FXML
	private void inicia() throws InterruptedException {
		this.iniciaButton.setText("Contando");
		this.iniciaButton.setDisable(true);
		this.paraButton.setDisable(false);
		if (!hilito.isAlive()) {
			timer.observe(this);
			hilito.start();

		} else {
			timer.getSuspendido().setSuspendido(false);
		}
	}

	@FXML
	private void para() {
		if (timer.getSuspendido().getSuspendido() == true) {
			timer.getSuspendido().setSuspendido(false);
			this.iniciaButton.setText("Contando");
			this.paraButton.setText("Detener");
		} else {
			timer.getSuspendido().setSuspendido(true);
			this.iniciaButton.setText("Detenido");
			this.paraButton.setText("Reanudar");
		}

	}

	@FXML
	private void reinicia() {
		this.iniciaButton.setDisable(false);
		this.iniciaButton.setText("Inicia");
		this.paraButton.setDisable(true);
		this.paraButton.setText("Detener");
		timer.reset();
		this.timerField.setText("00:00:00");
	}

	public void updateField(Integer contador) {
		if (contador > 59) {
			this.minutes++;
			if (minutes > 59) {
				this.hours++;
				this.minutes = 0;
			}
			this.seconds = 0;
			timer.setContador(0);
		} else {
			this.seconds = contador;
		}
		this.timerField.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		updateField((int) arg1);
	}

}
