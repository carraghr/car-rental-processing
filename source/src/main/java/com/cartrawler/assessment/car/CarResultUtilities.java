package com.cartrawler.assessment.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CarResultUtilities {

	public enum CorporateSupplier{
        AVIS,
        BUDGET,
        ENTERPRISE,
        FIREFLY,
        HERTZ,
        SIXT,
        THRIFTY};
        
    private static boolean isCorporateSuppled(CarResult car){
		for(CorporateSupplier supplier : CorporateSupplier.values()) {
			if(supplier.name().equalsIgnoreCase(car.getSupplierName())){
				return true;
			}
		}
		return false;
    }
	
	public static ArrayList<CarResult> removeDuplications(Set<CarResult> cars){
		if(cars == null || cars.isEmpty()){
			return new ArrayList<CarResult>();
		}
		
		List<CarResult> newCarList = new ArrayList<CarResult>();
		
        for (CarResult car : cars) {
        	boolean newCar = true;
        	for(int index = 0; index < newCarList.size();index++){
        		CarResult temp = newCarList.get(index);
        		if(temp != null && temp.equals(car)){
        			newCar = false;
        			break;
        		}
        	}
        	if(newCar){
        		newCarList.add(car);
        	}
        }
		return (ArrayList<CarResult>) newCarList;
	}
		
	public static HashMap<String,ArrayList<CarResult>> splitListByCorporateStatus(ArrayList<CarResult> cars){
		if(cars == null || cars.isEmpty()){
			return new HashMap<String,ArrayList<CarResult>>();
		}
		
		ArrayList<CarResult> corporateCars = new ArrayList<CarResult>();
		ArrayList<CarResult> notCorporateCarss = new ArrayList<CarResult>();
		for (CarResult car : cars) {
        	if(CarResultUtilities.isCorporateSuppled(car)) {
        		corporateCars.add(car);
        	}else{
        		notCorporateCarss.add(car);
        	}
        }
				
		HashMap<String, ArrayList<CarResult>> carCorporateSplits = new HashMap<String,ArrayList<CarResult>>();
		carCorporateSplits.put("corporate",corporateCars);
		carCorporateSplits.put("noncorporate",notCorporateCarss);
		return carCorporateSplits;
	}
	
	public static HashMap<String,ArrayList<CarResult>> orderCarsBySIPPCode(List<CarResult> cars){
		
		HashMap<String, ArrayList<CarResult>> ordered = new HashMap<>();
		ordered.put("M",  new ArrayList<CarResult>());
		ordered.put("C",  new ArrayList<CarResult>());
		ordered.put("E",  new ArrayList<CarResult>());
		ordered.put("O",  new ArrayList<CarResult>());
		for(CarResult car: cars){
			if(ordered.containsKey(""+car.getSippCode().charAt(0))){
				ArrayList<CarResult> temp = ordered.get(""+car.getSippCode().charAt(0));
				temp.add(car);
				ordered.put(""+car.getSippCode().charAt(0),temp);
				
			}else{
				ArrayList<CarResult> temp = ordered.get("O");
				temp.add(car);
				ordered.put("O",temp);
			}
		}
		return ordered;
	}
	
	public static ArrayList<CarResult> orderCarsByPrice(ArrayList<CarResult> cars){
		
		for(int index = 0; index<cars.size();index++){
			for(int nextIndex = index+1; nextIndex<cars.size();nextIndex++){
				if(cars.get(index).getRentalCost()>cars.get(nextIndex).getRentalCost()){
					CarResult temp = cars.get(index);
					cars.set(index,cars.get(nextIndex));
					cars.set(nextIndex, temp);
				}
			}
		}
		return cars;
	}
	
	public static ArrayList<CarResult> medianFilter(ArrayList<CarResult> cars){
		cars = CarResultUtilities.orderCarsByPrice(cars);
		double median;
		if(cars.size()%2 ==0) {
			median = (cars.get(cars.size()/2).getRentalCost() + cars.get((cars.size()/2)-1).getRentalCost() ) / 2;
		}else {
			median = cars.get(cars.size()/2).getRentalCost();
		}
		ArrayList<CarResult> orderedList = new ArrayList<CarResult>();
		for(CarResult car : cars) {
			if(!(car.getRentalCost()>median && (car.getFuelPolicy().equals(CarResult.FuelPolicy.FULLFULL)))){
				orderedList.add(car);
			}
		}
		return orderedList;
	}
	
	public static ArrayList<CarResult> defaultOrdering(Set<CarResult> cars){
		
		ArrayList<CarResult> carList = CarResultUtilities.removeDuplications(cars);
		HashMap<String,ArrayList<CarResult>> corporateSplit = CarResultUtilities.splitListByCorporateStatus(carList);
		
		ArrayList<CarResult> completeOrderedList = new ArrayList<>();
		
		for(String splitKey : new ArrayList<String>(corporateSplit.keySet())){
			HashMap<String,ArrayList<CarResult>> sippCodeSplit = CarResultUtilities.orderCarsBySIPPCode(corporateSplit.get(splitKey));
			for(String sippcode : new ArrayList<String>(sippCodeSplit.keySet())){
				ArrayList<CarResult> temp = CarResultUtilities.orderCarsByPrice(sippCodeSplit.get(sippcode));
				for(CarResult car:temp) {
					completeOrderedList.add(car);
				}
			}
		}
		return completeOrderedList;
	}
	
	public static ArrayList<CarResult> medianFilterOrdering(Set<CarResult> cars){
		
		ArrayList<CarResult> carList = CarResultUtilities.removeDuplications(cars);
		HashMap<String,ArrayList<CarResult>> corporateSplit = CarResultUtilities.splitListByCorporateStatus(carList);
		
		ArrayList<CarResult> completeOrderedList = new ArrayList<>();
		
		for(String splitKey : new ArrayList<String>(corporateSplit.keySet())){
			HashMap<String,ArrayList<CarResult>> sippCodeSplit = CarResultUtilities.orderCarsBySIPPCode(corporateSplit.get(splitKey));
			for(String sippcode : new ArrayList<String>(sippCodeSplit.keySet())){
				ArrayList<CarResult> temp = CarResultUtilities.medianFilter(sippCodeSplit.get(sippcode));
				for(CarResult car:temp) {
					completeOrderedList.add(car);
				}
			}
		}
		return completeOrderedList;
	}
}
