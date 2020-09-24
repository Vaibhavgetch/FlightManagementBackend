package com.capg.flightmanagement.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.flightmanagement.dto.FlightDetailDto;
import com.capg.flightmanagement.dto.FlightDto;
import com.capg.flightmanagement.exceptions.FlightNotFoundException;
import com.capg.flightmanagement.exceptions.InvalidArgumentException;
import com.capg.flightmanagement.models.Flight;
import com.capg.flightmanagement.service.IFlightService;

@RestController
@RequestMapping("/flights")
public class FlightRestController {
	
	
	//private static final Logger Log = LoggerFactory.getLogger(FlightRestController.class);     

	@Autowired
	private IFlightService service;

	@GetMapping
	public ResponseEntity<List<FlightDetailDto>> fetchAll() {

		List<Flight> flight = service.viewAllFlight();
		List<FlightDetailDto> list = convertToFlightDto(flight);
		ResponseEntity<List<FlightDetailDto>> response = new ResponseEntity<>(list, HttpStatus.OK);
		return response;

	}

	@PostMapping("/add")
	public ResponseEntity<FlightDetailDto> book(@RequestBody FlightDto flightdto) {
		Flight flight = convertToFlight(flightdto);
		flight = service.addFlight(flight);
		FlightDetailDto dto = convertToFlightDto(flight);
		ResponseEntity<FlightDetailDto> response = new ResponseEntity<>(dto, HttpStatus.OK);
		return response;

	}
	
	@PutMapping("/update/{flightNumber}")
	public ResponseEntity<Boolean> updateFlight(@RequestBody Flight flight,@PathVariable BigInteger flightNumber){
		flight.setFlightNumber(flightNumber);
		service.modifyFlight(flight);
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true,HttpStatus.OK) ;
				return response ;
		
	}
	@DeleteMapping("/delete/{flightNumber}")
	public ResponseEntity<Boolean> deleteFlight(@PathVariable("flightNumber") BigInteger flightNo){
		
		boolean result = service.deleteFlight(flightNo);
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true,HttpStatus.OK) ;
		return response ;
	}
	
	@GetMapping("/get/{flightNumber}")
	public ResponseEntity<FlightDetailDto> findById(@PathVariable("flightNumber") BigInteger flightNo) {
		
	 Flight flight = service.viewFlight(flightNo);
	 FlightDetailDto flightDto = convertToFlightDto(flight);
	 ResponseEntity<FlightDetailDto> response = new ResponseEntity<>(flightDto,HttpStatus.OK);
	 return response ;
	 
		
	}
	
	 	public Flight convertToFlight(FlightDto flightDto) {
	 		
	 		Flight flight = new Flight() ;
	 		flight.setFlightModel(flightDto.getFlightModel());
	 		flight.setCarrierName(flightDto.getCarrierName());
	 		flight.setSeatCapacity(flightDto.getSeatCapacity());
	 		return flight ;
	 		
	 		
	 		
	 	}
	 	
	 	 public List<FlightDetailDto> convertToFlightDto(List<Flight> flights) {
	         List<FlightDetailDto> list = new ArrayList<>();
	         for (Flight flight : flights) {
	         	FlightDetailDto detailsDto = convertToFlightDto(flight);
	             list.add(detailsDto);
	         }
	         return list;
	     }

	  public FlightDetailDto convertToFlightDto(Flight flight) {
	     	FlightDetailDto detailsDto = new FlightDetailDto();
	         detailsDto.setFlightNumber(flight.getFlightNumber());
	         detailsDto.setCarrierName(flight.getCarrierName());
	         detailsDto.setFlightModel(flight.getFlightModel());
	         detailsDto.setSeatCapacity(flight.getSeatCapacity());
	         return detailsDto;
	     }
	  
	  
	  
	    @ExceptionHandler(FlightNotFoundException.class)
	    public ResponseEntity<String> handleEmployeeNotFound(FlightNotFoundException ex) {
	       // Log.error("Invalid Flight Code Exception ", ex);
	        String msg = ex.getMessage();
	        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	        return response;
	    }
	    
	    @ExceptionHandler(InvalidArgumentException.class)
	    public ResponseEntity<String> handleInvalidArgument(InvalidArgumentException ex) {
	       // Log.error("Invalid Argument ", ex);
	        String msg = ex.getMessage();
	        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
	        return response;
	    }

	    @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
	     //   Log.error("constraint violation ", ex);
	        String msg = ex.getMessage();
	        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	        return response;
	    }

	   
	    @ExceptionHandler(Throwable.class)
	    public ResponseEntity<String> handleAll(Throwable ex) {
	   //     Log.error("Something went wrong ", ex);
	        String msg = ex.getMessage();
	        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
	        return response;
	    }

	}

	
	
	
	
	
	
	
	
	
	
	

