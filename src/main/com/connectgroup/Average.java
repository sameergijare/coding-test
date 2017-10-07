package com.connectgroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

//This class is to find average of RESPONSETIME Field of input file
public class Average {

	//Method to calculate the average of RESPONSETIME
	public double findAverage(Reader source) {

		BufferedReader br = new BufferedReader(source);
		String line = null;
		int iteration = 0;
		String[] myArray;
		Collection<Long> responseTimeList = new ArrayList<Long>();
		long responseTime = 0;
		long count = 0;
		double average = 0.0;
		try {
			while ((line = br.readLine()) != null) {
				// Logic to remove header from the input file
				if (iteration == 0) {
					iteration++;
					continue;
				}
				myArray = line.split(",");
				for (String eachval : myArray) {
					// Finding the RESPONSE TIME field from the input line
					boolean isNumeric = eachval.chars().allMatch(x -> Character.isDigit(x));
					if (isNumeric) {
						count = eachval.chars().count();
						// Identifying between RESPONSETIME and
						// REQUEST_TIMESTAMP. Unix Timestamp will be always 10
						// digits or 13 digits
						if (count < 10) {
							responseTime = Integer.parseInt(eachval);
							responseTimeList.add(responseTime);
						}
					}
				}

			}
			// Calculating the average
			average = responseTimeList.stream().mapToInt(Long::intValue).average().getAsDouble();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return average;

	}

}
