package com.iotracks.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.json.JsonObject;

import com.iotracks.api.IOFogClient;
import com.iotracks.api.listener.IOFogAPIListener;

public class CSVWriter {
	
	String path = System.getProperty("user.dir");
	String fileExt = ".csv";
	String comma = ", ";
	public void writeCSV(JsonObject json) {
		JsonObject contentJson = json.getJsonObject("contentdata");
		JsonObject contextJson = json.getJsonObject("contextdata");
		long timestamp = Long.parseLong(json.get("timestamp").toString());
		
		File file = new File(path + "sensorData" + fileExt);
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write("FacilityId, Timestamp, X, Y, Z");
			if(contextJson.get("SensorType").toString().equals("uniaxial")) {
				int number = Integer.parseInt(contextJson.get("SensorNumber").toString());
				double x = Double.parseDouble(contentJson.get("x").toString());
				writer.write("uniaxial" + number + ", " + timestamp + ", " + x);
				writer.newLine();
			}
			else {
				int number = Integer.parseInt(contextJson.get("SensorNumber").toString());
				double x = Double.parseDouble(contentJson.get("x").toString());
				double y = Double.parseDouble(contentJson.get("y").toString());
				double z = Double.parseDouble(contentJson.get("z").toString());
				writer.write("triaxial" + number + comma + timestamp + comma + x + comma + y + comma + z);
				writer.newLine();
			}
			writer.close();
		} catch(Exception e) {}
	    
	}
	
	public static void main(String[] args) {
		String containerId = System.getenv("SELFNAME");
		String ioFogHost = System.getProperty("iofabric_host", "iofabric");
		int ioFogPort = 54321;
		try {
			ioFogPort = Integer.parseInt(System.getProperty("iofabric_port", "54321"));
		} catch (Exception e) {}
		IOFogClient client = new IOFogClient(ioFogHost, ioFogPort, containerId);
		IOFogAPIListener listener = new IOFogAPIListenerImpl();
		client.openMessageWebSocket(listener);
		
	}
}
