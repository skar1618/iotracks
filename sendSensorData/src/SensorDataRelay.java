//import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
//import javax.json.JsonReader;

import com.iotracks.api.IOFogClient;
import com.iotracks.api.listener.IOFogAPIListener;
import com.iotracks.elements.IOMessage;

//import com.iotracks.utils.IOMessageUtils;

public class SensorDataRelay {
	
	private enum SensorType{
		UNIAXIAL,
		TRIAXIAL
	}

	private double[] generateSensorData(SensorType type) {
		System.out.println("Generating sensor data");
		if(type == SensorType.UNIAXIAL) {
			double[] data = new double[1];
			int rangeMin = 1;
			int range = 1;
			data[0] = rangeMin + Math.random() * range;
			return data;
		} else {
			double[] data = new double[3];
			int rangeMin = 1;
			int range = 1;
			data[0] = rangeMin + Math.random() * range;
			data[1] = rangeMin + Math.random() * range;
			data[2] = rangeMin + Math.random() * range;
			return data;
		}
	}
	
	private JsonObject createJsonFromData(SensorType type) {
		System.out.println("createJsonFromData");
		double[] data = generateSensorData(type);
		if(type == SensorType.UNIAXIAL) {
			JsonObject contextJson = Json.createObjectBuilder().add("SensorType", "uniaxial")
					.add("SensorNumber", 1)
					.build();
			JsonObject contentJson = Json.createObjectBuilder().add("x", data[0])
					.build();
			JsonObject json = Json.createObjectBuilder().add("timestamp", System.currentTimeMillis())
					.add("contextdata", contextJson.toString())
					.add("contentdata", contentJson.toString())
					.build();
			return json;
		} else {
			JsonObject contextJson = Json.createObjectBuilder().add("SensorType", "triaxial")
					.add("SensorNumber", 1)
					.build();
			JsonObject contentJson = Json.createObjectBuilder().add("x", data[0])
					.add("y", data[1])
					.add("z", data[2])
					.build();
			JsonObject json = Json.createObjectBuilder().add("timestamp", System.currentTimeMillis())
					.add("contextdata", contextJson.toString())
					.add("contentdata", contentJson.toString())
					.build();
			return json;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Jan 19th SendSensor start v2");
		SensorDataRelay relay = new SensorDataRelay();
		JsonObject json = relay.createJsonFromData(SensorType.TRIAXIAL);
		String containerId = System.getenv("SELFNAME");// ID is needed to map a container to the fog
		String ioFogHost = System.getProperty("iofog_host", "iofog");
		int ioFogPort = 54321;
		try {
			ioFogPort = Integer.parseInt(System.getProperty("iofog_port", "54321"));
		} catch (Exception e) {
			System.out.println("error parsing integer: " + e.getMessage());
		}
		IOFogClient client = new IOFogClient(ioFogHost, ioFogPort, containerId);
		IOFogAPIListener listener = new IOFogAPIListenerImpl();
		client.openMessageWebSocket(listener);// or IOFog rest API
		System.out.println("web socket opened");
		IOMessage msg = new IOMessage(json, false);
		System.out.println("IOMessage created");
		//client.pushNewMessage(msg, listener);
		Thread.sleep(5000);
		client.sendMessageToWebSocket(msg);
		System.out.println("Message sent to web socket");
		System.out.println("Jan 19th SendSensor end v2");
		
	}
	
}


























