package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	
	private Double pricePerHour;
	private Double pricePerDay;
	
	private TaxService taxService;

	public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}
		
		
	public void processInvoice(CarRental carRental) {
		double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes(); // PEGA O PERIODO DE DIFERENÇA EM MINUTOS
		double hours = minutes/60;
		
		double basicPayment;
		if(hours<=12) {
			basicPayment = pricePerHour * Math.ceil(hours);											
		}
		else {
			basicPayment = pricePerDay * Math.ceil(hours/24);
		}
		
		double tax = taxService.tax(basicPayment);						 // PEGA O METODO USANDO BASICPAYMENT COMO ARGUMENTO
		
		carRental.setInvoice(new Invoice(basicPayment, tax)); 			// CHAMAMOS O METODO DA CLASSE CARRENTAL E SETAMOS OS DADOS DA INVOICE PARA VINCULA-LOS
		
		}
	
	

}
