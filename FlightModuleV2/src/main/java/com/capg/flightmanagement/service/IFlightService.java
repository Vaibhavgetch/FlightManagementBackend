package com.capg.flightmanagement.service;

import java.math.BigInteger;
import java.util.List;

import com.capg.flightmanagement.models.Flight;



public interface IFlightService {

	
	Flight addFlight(Flight flight) ;
	Flight modifyFlight(Flight flight) ;
	Flight viewFlight(BigInteger flightNo) ; 
	List<Flight> viewAllFlight() ;
	boolean deleteFlight (BigInteger flightNo ) ;
	
	
}
