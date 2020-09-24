package com.capg.flightmanagement;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class ServletInitializer extends SpringApplicationBuilder{
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FlightMgmt.class);
	}

}
