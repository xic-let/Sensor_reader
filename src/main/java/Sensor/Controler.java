package Sensor;

import java.util.concurrent.BlockingQueue;
import org.json.JSONObject;

public class Controler implements Runnable {
	private final BlockingQueue<String> buffer;
	
	public Controler(BlockingQueue<String> buffer) {
		this.buffer = buffer;
	}
	@Override
	public void run() {
		try {
			while (true) {
				String rawData = buffer.take(); // Remove dados do buffer
				processData(rawData); // Processa os dados
				Thread.sleep(500); // Simula o tempo de processamento
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Controlador interrompido.");
		}
	}
	
	private void processData(String rawData) {
		JSONObject json = new JSONObject(rawData);
		String sensor = json.getString("sensor");
		double value = json.getDouble("value");
		
		// Processa os dados com base no tipo de sensor
		if ("Temperatura".equals(sensor)) {
			System.out.printf("[Análise] Sensor: %s, Valor: %.2f °C%n", sensor, value);
		} else if ("Pressão".equals(sensor)) {
			System.out.printf("[Análise] Sensor: %s, Valor: %.2f kPa%n", sensor, value);
		} else if ("Humidade".equals(sensor)) {
			System.out.printf("[Análise] Sensor: %s, Valor: %.2f %%RH%n", sensor, value);
		} else {
			System.out.println("[Análise] Dados desconhecidos: " + rawData);
		}
	}
}
