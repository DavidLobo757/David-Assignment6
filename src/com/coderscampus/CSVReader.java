package com.coderscampus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
	
	public List<Sales> fileData (String fileName) throws IOException {
		List<Sales> salesInfo = new ArrayList<>();
		BufferedReader read = null;
		
		try {
			read = new BufferedReader (new FileReader(fileName));
			String line = read.readLine();
			while ((line = read.readLine()) != null) {
				String[] data = line.split(",");
				salesInfo.add(new Sales(data[0], data[1]));
			}
					
					return salesInfo;
		} finally {
			if(read != null) 
				read.close();
		}
		
		
	}
}
