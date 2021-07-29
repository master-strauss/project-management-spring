package com.jrp.pma.springexample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManufacturingConfig {

	@Bean
	public Car newCar() {
		Engine e = new Engine();
		Doors d = new Doors();
		Tyres t = new Tyres();

		return new Car(e, d, t);
	}

}
