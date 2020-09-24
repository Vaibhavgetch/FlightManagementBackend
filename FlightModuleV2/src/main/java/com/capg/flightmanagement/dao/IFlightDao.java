package com.capg.flightmanagement.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.flightmanagement.models.Flight;

@Repository
public interface IFlightDao extends JpaRepository<Flight, BigInteger>{

}
