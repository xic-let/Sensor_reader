package Sensor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MonitoringSensor {
	BlockingQueue<String> buffer = new LinkedBlockingQueue<>(10); // Buffer com limite de 10 itens

	public MonitoringSensor(int bufferSize) {
		this.buffer = new LinkedBlockingQueue<>(bufferSize);
	}
	
	public void startSensor(){
		Thread sensor1 = new Thread(new Sensor("Temperatura", buffer));
		Thread sensor2 = new Thread(new Sensor("Pressão", buffer));
		Thread sensor3 = new Thread(new Sensor("Humidade", buffer));
		
		sensor1.setPriority(Thread.MAX_PRIORITY); // Prioridade alta para temperatura
		sensor2.setPriority(Thread.NORM_PRIORITY); // Prioridade normal
		sensor3.setPriority(Thread.MIN_PRIORITY); // Prioridade baixa
		
		sensor1.start();
		sensor2.start();
		sensor3.start();
		
		Thread controler = new Thread(new Controler(buffer));
		controler.start();
	}
	public BlockingQueue<String> getBuffer() {
		return buffer;
	}
}


	
