package com.capg.flightmanagement.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.capg.flightmanagement.dao.*;
import com.capg.flightmanagement.exceptions.*;
import com.capg.flightmanagement.models.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlightServicesImpl implements IFlightService {

	@Autowired
	private IFlightDao dao;

	@Override
	public Flight addFlight(Flight flight) {

		if (flight == null) {
			throw new InvalidArgumentException("Flight can not be null");
		}
		flight = dao.save(flight);
		return flight;
	}

	@Override
	public Flight modifyFlight(Flight flight) {

		boolean exists = dao.existsById(flight.getFlightNumber());
		if (!exists) {
			throw new FlightNotFoundException(
					"Flight with FlightNumber : " + flight.getFlightNumber() + " do not exists");
		}
		flight = dao.save(flight);
		return flight;
	}

	@Override
	public Flight viewFlight(BigInteger flightNo) {

		Optional<Flight> optional = dao.findById(flightNo);
		if (optional.isPresent()) {
			Flight flight = optional.get();
			return flight;
		}
		throw new FlightNotFoundException("Flight with FlightNumber : " + flightNo + " do not exists");
	}

	@Override
	public List<Flight> viewAllFlight() {

		List<Flight> list = dao.findAll();
		return list;
	}

	@Override
	public boolean deleteFlight(BigInteger flightNo) {

		 Optional<Flight> optional = dao.findById(flightNo);   
	     if(optional.isPresent()) {
	    	 dao.deleteById(flightNo);
			    return true;
	}
	     throw new FlightNotFoundException("Flight with FlightNumber : "+flightNo+" do not exists"); 
		

	
	}
	
}
