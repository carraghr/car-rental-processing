package com.cartrawler.assessment.car;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CarResultTest{
	
	 CarResult car;
    
    @Test
    public void testLegalConstruction() {
    	car = new CarResult("Citroen C3 Picasso", "FLIZZR", "EMMV", 75.93d, CarResult.FuelPolicy.FULLEMPTY);
    	assertEquals ("wrong description", "Citroen C3 Picasso", car.getDescription());
    	assertEquals ("wrong sippCode", "EMMV", car.getSippCode());
    	assertEquals ("wrong supplierName", "FLIZZR", car.getSupplierName());
    	assertEquals ("wrong fuelPolicy",  CarResult.FuelPolicy.FULLEMPTY, car.getFuelPolicy());
    	assertEquals ("wrong rentalCost",  75.93d, car.getRentalCost(),0);
    }
}

