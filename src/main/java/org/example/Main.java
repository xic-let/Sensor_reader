package org.example;

import Sensor.MonitoringSensor;

public class Main {
	public static void main(String[] args) {
		MonitoringSensor monitorSensor = new MonitoringSensor(10);
		monitorSensor.startSensor();
	}
}
