package com.coderscampus;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;



public class ApplicationReport {
	public static void main (String[] args) throws IOException{
		CSVReader csvreader = new CSVReader();
		
		List<Sales> model3sales = csvreader.fileData("model3.csv");
		List<Sales> modelSsales = csvreader.fileData("modelS.csv");
		List<Sales> modelXsales = csvreader.fileData("modelX.csv");
		
		salesReport(model3sales, "Model 3");
		salesReport(modelSsales, "Model S");
		salesReport(modelXsales, "Model X");
	}
	
	public static void salesReport(List<Sales> carSales, String carModel) {
		System.out.println(carModel + " Yearly Sales Report");
		System.out.println("---------------------------");
		
		Map<Integer, List<Sales>> groupedByYear = carSales.stream()
				.collect(Collectors.groupingBy(d -> d.getDate().getYear()));
		
		String totalYearlySales = groupedByYear.entrySet()
						.stream()
						.map(d -> d.getKey() + " -> " + d.getValue().stream()
								.collect(Collectors.summingInt(Sales::getSales)))
						.collect(Collectors.joining("\n"));
		
		System.out.println(totalYearlySales);
		System.out.println("");
		
		Optional<Sales> maxSales = carSales.stream()
				.max((Sales o1, Sales o2) -> o1.getSales().compareTo(o2.getSales()));
		Optional<Sales> minSales = carSales.stream()
				.min((Sales o1, Sales o2) -> o1.getSales().compareTo(o2.getSales()));
		
		System.out.println("The best month for " + carModel + " was: " + maxSales.orElse(new Sales("Jan-70", "0")).getDate());
		System.out.println("The worst month for " + carModel + " was: " + minSales.orElse(new Sales("Jan-70", "0")).getDate());
		System.out.println("");
		
	}
	
}
