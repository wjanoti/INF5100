import com.espertech.esper.client.*;
import java.util.StringTokenizer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class INF5100 {
	private static Scanner scanner;

	public static class CEPListener implements UpdateListener {
		public void update(EventBean[] newData, EventBean[] oldData) {
			System.out.println("EVENT! " + newData[0].getUnderlying());
		}
	}

	public static void main(String[] args) {
		// Setup
		Configuration cepConfig = new Configuration();
		cepConfig.addEventType("WeatherTick", Tick.class.getName());
		EPServiceProvider cep = EPServiceProviderManager.getProvider("INF5100", cepConfig);
		EPRuntime cepRT = cep.getEPRuntime();

		EPAdministrator cepAdm = cep.getEPAdministrator();

		for(int queryCounter = 1; queryCounter <= 10; queryCounter++) {
			System.out.println("------------------");
			System.out.println("PERFORMING QUERY " + queryCounter);
			System.out.println("------------------");

			try {
				scanner = new Scanner(new File("query_" + queryCounter + ".epl"));
				String query = scanner.useDelimiter("\\Z").next();
				cepAdm.destroyAllStatements();
				EPStatement cepStatement = cepAdm.createEPL(query);
				cepStatement.addListener(new CEPListener());
			} catch(Exception e) {
				System.err.println("Error: " + e.getMessage());
			}

			// Generate ticks
			try {
				File file = new File("Data.csv");
				BufferedReader reader  = new BufferedReader(new FileReader(file));
				String line = null;
				
				while((line = reader.readLine()) != null) {
					Tick tick = new Tick("WE");
					StringTokenizer tokenizer = new StringTokenizer(line, ",");
		
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					try {
						tick.setTimestamp(dateFormat.parse(tokenizer.nextToken()));
					} catch(Exception e) {
						System.err.println("Error: " + e.getMessage());
					}
					
					tick.setLatitude(Double.parseDouble(tokenizer.nextToken()));
					tick.setUltraviolet_level(Double.parseDouble(tokenizer.nextToken()));
					tick.setTemp_fahrenheit(Double.parseDouble(tokenizer.nextToken()));
					
					tick.setLocation(tokenizer.nextToken().toString());
					tick.setWeather(tokenizer.nextToken().toString());
					tick.setWeather_station(tokenizer.nextToken().toString());
					
					tick.setElevation(Integer.parseInt(tokenizer.nextToken()));				
					tick.setLongitude(Double.parseDouble(tokenizer.nextToken()));
					
					tick.setWind_direction(Integer.parseInt(tokenizer.nextToken()));
					
					//System.out.println("TICK!  " + tick);
					cepRT.sendEvent(tick);
				}

				reader.close();
			} catch(Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}
}
