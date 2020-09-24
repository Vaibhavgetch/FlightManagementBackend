package com.capg.flightmanagement.dto;

import javax.validation.constraints.*;


public class FlightDto {
	
	

    @NotBlank(message = "Flight Model is mandatory")
    private String flightModel;
    @NotBlank(message = "Name is mandatory")
    private String carrierName;
   @NotNull
    private int seatCapacity;
    
    
    

    public FlightDto() {
		super();
	}

	public String getFlightModel() {
		return flightModel;
	}

	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public Integer getSeatCapacity() {
		return seatCapacity;
	}

	public void setSeatCapacity(Integer seatCapacity) {
		this.seatCapacity = seatCapacity;
	}


}
