package com.cartrawler.assessment.view;

import com.cartrawler.assessment.car.CarResult;
import com.cartrawler.assessment.car.CarResultUtilities;

import java.util.ArrayList;
import java.util.Set;

public class Display {
    public void render(Set<CarResult> cars){ 
    	ArrayList<CarResult> test = CarResultUtilities.defaultOrdering(cars);
    	//ArrayList<CarResult> test = CarResultUtilities.medianFilterOrdering(cars);
        for(CarResult car : test){
        	System.out.println(car);
        }
    }
}
