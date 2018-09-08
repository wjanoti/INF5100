import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Tick {

	String symbol;
	Date timestamp;
	Double latitude;
	Double ultraviolet_level;
	Double temp_fahrenheit;
	String location;
	String weather;
	String weather_station;
	int elevation;
	Double longitude;
	int wind_direction;

	public Tick(String s) {
		symbol = s;
	}

	public Tick(String s, Date t, double l, double u_l,double f,  String lo, String w, String w_s, int e, double longi, int w_d) {
		symbol = s;
		timestamp = t;
		latitude = l;
		ultraviolet_level = u_l;
		temp_fahrenheit = f;
		location = lo;
		weather = w;
		weather_station = w_s;
		elevation = e;
		longitude = longi;
		wind_direction = w_d;
	}

	public Double getTemp_fahrenheit() {
		return temp_fahrenheit;
	}

	public void setTemp_fahrenheit(Double temp_fahrenheit) {
		this.temp_fahrenheit = temp_fahrenheit;
	}

	public String getSymbol() {
		return symbol;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getUltraviolet_level() {
		return ultraviolet_level;
	}

	public String getLocation() {
		return location;
	}

	public String getWeather() {
		return weather;
	}

	public String getWeather_station() {
		return weather_station;
	}

	public int getElevation() {
		return elevation;
	}

	public Double getLongitude() {
		return longitude;
	}

	public int getWind_direction() {
		return wind_direction;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setUltraviolet_level(Double ultraviolet_level) {
		this.ultraviolet_level = ultraviolet_level;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public void setWeather_station(String weather_station) {
		this.weather_station = weather_station;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setWind_direction(int wind_direction) {
		this.wind_direction = wind_direction;
	}

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return 
			   "Symbol: " + symbol + "  " + 
		       "Timestamp: " + dateFormat.format(timestamp) + "   " + 
		       "Latitute: "+ latitude.toString() + "   \t   " + 
		       "Ultraviolet Level: " + ultraviolet_level.toString() + "    \t    " + 
		       "Temp Fahrenheit: " + temp_fahrenheit.toString() + "    \t    " + 
		       "Location: " + location + "    \t    " + 
		       "Weather: " + weather + "   \t   " + 
		       "Weaterh Station: " + weather_station + "   \t   "	+ 
		       "Elevation " + elevation + "   \t   " + 
		       "Longitude " + longitude.toString()+ "   \t   " + 
		       "Wind Direction " + wind_direction + "   \t   " ;

	}

}
