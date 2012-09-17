package display;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays data for EE260 debugger
 * 
 * @author boka
 */
public class Display {
	/**
	 * Default constructor
	 */
	public Display() {
	}

	/**
	 * Update the display from String array
	 */
	public void update(String[] input) {
		List<String> output = new ArrayList<String>();
		for (int i = 0; i < input.length; i++) {
			output.add(input[i]);
		}
		this.put(output);
	}

	/**
	 * Update the display from String Array List
	 */
	public void update(List<String[]> input) {
		for (int i = 0; i < input.size(); i++) {
			update(input.get(i));
		}
	}

	/**
	 * Display the output
	 */
	private void put(List<String> output) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < output.size(); i++) {
			stringBuilder.append(output.get(i));
			stringBuilder.append("\t");
		}
		System.out.println(stringBuilder.toString());
	}
}
