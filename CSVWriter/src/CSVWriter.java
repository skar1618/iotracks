
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.iotracks.api.IOFogClient;
import com.iotracks.api.listener.IOFogAPIListener;

public class CSVWriter {
	
	String path = System.getProperty("user.dir");
	String fileExt = ".csv";
	String comma = ", ";
	public void writeCSV(JsonObject json) {
		String contentString = json.getString("contentdata");
		String contextString = json.getString("contextdata");
		JsonReader jsonReader = Json.createReader(new StringReader(contentString));
		JsonObject contentJson = jsonReader.readObject();
		jsonReader = Json.createReader(new StringReader(contextString));
		JsonObject contextJson = jsonReader.readObject();
		jsonReader.close();
		long timestamp = Long.parseLong(json.get("timestamp").toString());
		
		File file = new File(path + "\\sensorData" + fileExt);
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write("FacilityId, Timestamp, X, Y, Z");
			writer.newLine();
			if(contextJson.get("SensorType").toString().equals("uniaxial")) {
				int number = Integer.parseInt(contextJson.get("SensorNumber").toString());
				double x = Double.parseDouble(contentJson.get("x").toString());
				writer.write("uniaxial" + number + ", " + timestamp + ", " + x);
			}
			else {
				int number = Integer.parseInt(contextJson.get("SensorNumber").toString());
				double x = Double.parseDouble(contentJson.get("x").toString());
				double y = Double.parseDouble(contentJson.get("y").toString());
				double z = Double.parseDouble(contentJson.get("z").toString());
				writer.write("triaxial" + number + comma + timestamp + comma + x + comma + y + comma + z);
			}
			System.out.println("CSV File written to: " + path + "\\sensorData" + fileExt);
			writer.close();
		} catch(Exception e) {}
	}
	
	public static void main(String[] args) {
		System.out.println("Jan 19th CSVWriter start v3");
		String containerId = System.getenv("SELFNAME");
		String ioFogHost = System.getProperty("iofog_host", "iofog");
		int ioFogPort = 54321;
		try {
			ioFogPort = Integer.parseInt(System.getProperty("iofog_port", "54321"));
		} catch (Exception e) {}
		IOFogClient client = new IOFogClient(ioFogHost, ioFogPort, containerId);
		IOFogAPIListener listener = new IOFogAPIListenerImpl();
		client.openMessageWebSocket(listener);
		System.out.println("message web socket opened");
		System.out.println("Jan 19th CSVWriter end v3");
	}
}
