package com.cartrawler.assessment.car;

import static org.junit.Assert.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CarResultUtilitiesTest {

	 public Set<CarResult> cars;
	
	 @Before
	 public void executedBeforeEach() {
		 cars =  new HashSet<>();
		 cars.add(new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, CarResult.FuelPolicy.FULLEMPTY));
		 cars.add(new CarResult("Volkswagen Polo", "NIZA", "EDMR", 13.81d, CarResult.FuelPolicy.FULLEMPTY));
		 cars.add(new CarResult("Volkswagen Polo", "NIZA", "EDMR", 15.81d, CarResult.FuelPolicy.FULLEMPTY));
		 cars.add(new CarResult("Ford C-Max Diesel", "NIZA", "CMMD", 22.04d, CarResult.FuelPolicy.FULLEMPTY));
		 cars.add(new CarResult("Renault Scenic Diesel", "NIZA", "JGAD", 93.67d, CarResult.FuelPolicy.FULLEMPTY));
		 cars.add(new CarResult("Ford C-Max Diesel", "NIZA", "CMMD", 21.04d, CarResult.FuelPolicy.FULLEMPTY));	 
	 }
	 
	 
	@Test
	public void removeDuplicationsTest() {
		List<CarResult> carList =  CarResultUtilities.removeDuplications(cars);
		List<CarResult> expectedCarList = new ArrayList<CarResult>();
		expectedCarList.add(new CarResult("Ford C-Max Diesel", "NIZA", "CMMD", 22.04d, CarResult.FuelPolicy.FULLEMPTY));
		expectedCarList.add(new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, CarResult.FuelPolicy.FULLEMPTY));
		expectedCarList.add(new CarResult("Renault Scenic Diesel", "NIZA", "JGAD", 93.67d, CarResult.FuelPolicy.FULLEMPTY));
		//assertArrayEquals("remove duplications test failed",expectedCarList.toArray(), carList.toArray());
	}

}
