package com.abhi;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.abhi.model.Racecar;
import com.abhi.util.RacecarUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * RacecarDetails class will parse json and perform various operations on
 * racecar entries
 * 
 * @author Abhishek Kumar Singh
 * @version 1.0
 * @since 04-05-2021
 * 
 */

public class RacecarDetails {

	/**
	 * This is the main method which call all operations that have to perform on
	 * racecar enteries
	 * 
	 * @param args Unused
	 * @return Nothing
	 * @throws IOException
	 */
	public static void main(String[] args) {
		final ObjectMapper objectMapper = new ObjectMapper();
		List<Racecar> raceDetailsList;
		try {
			raceDetailsList = objectMapper.readValue(new File("racecardetails.json"),
					new TypeReference<List<Racecar>>() {
					});

			RacecarUtil.speedAscending(raceDetailsList);

			RacecarUtil.speedDescendingGroupByMake(raceDetailsList);

			RacecarUtil.racerListWithSameDriver(raceDetailsList, "Sebastian Vettel");

			RacecarUtil.racerListWithUniqueDriver(raceDetailsList);

			RacecarUtil.racerListwithNoDuplicateDriver(raceDetailsList);

			RacecarUtil.removeDuplicateDriverList(raceDetailsList);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
