package Sensor;

import java.text.DecimalFormat;
import java.util.concurrent.BlockingQueue;
import static java.lang.Thread.sleep;

class Sensor implements Runnable {
	private final String name;
	private final BlockingQueue<String> buffer;
	private final DecimalFormat decimalFormat;
	
	public Sensor(String name, BlockingQueue<String> buffer) {
		this.name = name;
		this.buffer = buffer;
		this.decimalFormat = new DecimalFormat("#.##"); // Formato para 2 casas decimais
	}
	
	@Override
		public void run() {
		try {
			while (true) {
				double rawData = Math.random() * 100; // Gera um número aleatório entre 0 e 100
				String formattedValue = decimalFormat.format(rawData).replace(",", "."); // Corrige separador decimal
				String formattedData = String.format("{\"sensor\":\"%s\",\"value\":%s}", name, formattedValue);
				buffer.put(formattedData); // Adiciona dados ao buffer
				System.out.println("Sensor " + name + " gerou: " + formattedData);
				Thread.sleep(1000); // Simula o tempo de coleta
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Sensor " + name + " interrompido.");
		}
	}
}
