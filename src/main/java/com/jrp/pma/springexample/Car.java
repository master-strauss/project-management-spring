package com.jrp.pma.springexample;

/**
 * To use Car in another Class just use:
 * 
 * @Autowired
 * Car car;
 * 
 * use in Class where you want instance of Car
 * @Bean
 * public Car newCar(){
 * 
 * }
 * 
 */

public class Car {
	
	Engine e;
	Doors d;
	Tyres t;

	public Car(Engine e, Doors d, Tyres t) {
		this.e = e;
		this.d = d;
		this.t = t;
	}
	
	public void printCarDetils() {
		System.out.println(this.e + " " +this.d);
	}

}
