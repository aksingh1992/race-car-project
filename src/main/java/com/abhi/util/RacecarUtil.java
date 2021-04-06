package com.abhi.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.abhi.model.Racecar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * utility class to perform various operations
 * 
 * @author Abhishek Kumar Singh
 * @version 1.0
 * @since 04-05-2021
 * 
 */

public final class RacecarUtil {

	private RacecarUtil() {
	}

	/**
	 * This method is used to sort and display the list of Racecars by speed
	 * ascending
	 * 
	 * @param List of Racecar
	 * @throws JsonProcessingException
	 */
	public static void speedAscending(List<Racecar> racecarList) throws JsonProcessingException {
		final ObjectMapper objectMapper = new ObjectMapper();

		racecarList = racecarList.stream().sorted(Comparator.comparing(Racecar::getSpeed)).collect(Collectors.toList());

		String racecarToDisplay = objectMapper.writeValueAsString(racecarList);
		System.out.println(racecarToDisplay);
	}

	/**
	 * This method is used to sort and display the list of Racecars by speed
	 * descending, group by make
	 * 
	 * @param List of Racecar
	 * @throws JsonProcessingException
	 */
	public static void speedDescendingGroupByMake(List<Racecar> racecarList) throws JsonProcessingException {
		final ObjectMapper objectMapper = new ObjectMapper();

		racecarList = racecarList.stream().sorted(Comparator.comparing(Racecar::getSpeed).reversed())
				.collect(Collectors.toList());

		Map<String, List<Racecar>> racecarMap = racecarList.stream().collect(Collectors.groupingBy(Racecar::getMake));
		List<Racecar> finalRacecarList = racecarMap.values().stream().flatMap(Collection::stream)
				.collect(Collectors.toList());

		String racecarToDisplay = objectMapper.writeValueAsString(finalRacecarList);
		System.out.println(racecarToDisplay);
	}

	/**
	 * This method is used to generate and display a list of all Racecar entries
	 * with the same driver
	 * 
	 * @param List of Racecar
	 * @throws JsonProcessingException
	 */
	public static void racerListWithSameDriver(List<Racecar> racecarList, String driverName)
			throws JsonProcessingException {
		final ObjectMapper objectMapper = new ObjectMapper();

		racecarList = racecarList.stream().filter(raceCar -> raceCar.getDriver().equals(driverName))
				.collect(Collectors.toList());

		String racecarToDisplay = objectMapper.writeValueAsString(racecarList);
		System.out.println(racecarToDisplay);
	}

	/**
	 * This method is used to generate and display a list of all Racecar entries
	 * with unique drivers
	 * 
	 * @param List of Racecar
	 * @throws JsonProcessingException
	 */
	public static void racerListWithUniqueDriver(List<Racecar> racecarList) throws JsonProcessingException {
		final ObjectMapper objectMapper = new ObjectMapper();

		Set<String> driverSet = new HashSet<>();
		racecarList = racecarList.stream().filter(raceCar -> driverSet.add(raceCar.getDriver()))
				.collect(Collectors.toList());

		String racecarToDisplay = objectMapper.writeValueAsString(racecarList);
		System.out.println(racecarToDisplay);
	}

	/**
	 * This method is used to generate and display a list of all Racecar entries
	 * where the driver is not duplicated
	 * 
	 * @param List of Racecar
	 * @throws JsonProcessingException
	 */
	public static void racerListwithNoDuplicateDriver(List<Racecar> racecarList) throws JsonProcessingException {
		final ObjectMapper objectMapper = new ObjectMapper();

		Set<String> driverSet = new HashSet<>();
		List<Racecar> racecarduplicateList = racecarList.stream().filter(raceCar -> !driverSet.add(raceCar.getDriver()))
				.collect(Collectors.toList());

		racecarList = racecarList.stream()
				.filter(raceCar -> racecarduplicateList.stream()
						.noneMatch(duplicateRacer -> duplicateRacer.getDriver().equals(raceCar.getDriver())))
				.collect(Collectors.toList());

		String racecarToDisplay = objectMapper.writeValueAsString(racecarList);
		System.out.println(racecarToDisplay);
	}

	/**
	 * This method is used to remove all entries of the duplicate driver from the
	 * list and marshal and display JSON from the updated Racecar list
	 * 
	 * @param List of Racecar
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void removeDuplicateDriverList(List<Racecar> racecarList) throws FileNotFoundException, IOException {
		final ObjectMapper objectMapper = new ObjectMapper();

		Set<String> driverSet = new HashSet<>();
		List<Racecar> racecarduplicateList = racecarList.stream().filter(raceCar -> !driverSet.add(raceCar.getDriver()))
				.collect(Collectors.toList());

		racecarList = racecarList.stream()
				.filter(raceCar -> racecarduplicateList.stream()
						.noneMatch(duplicateRacer -> duplicateRacer.getDriver().equals(raceCar.getDriver())))
				.collect(Collectors.toList());

		String racecarToDisplay = objectMapper.writeValueAsString(racecarList);
		System.out.println(racecarToDisplay);

		// To save in json file
		objectMapper.writeValue(new FileOutputStream("removedDuplicateDriverList.json"), racecarList);
	}

}
