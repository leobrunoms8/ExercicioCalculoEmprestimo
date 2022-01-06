package model.services;

public class PaypalService implements OnlinePaymentsService{
	
	private Double mounthlyInterest = 0.01;
	private Double paymentTax = 0.02;
	
	@Override
	public double paymentFee(double amount) {
		double paymentFee = amount * paymentTax;
		return paymentFee;
	}

	@Override
	public double interest(double amount, int mounths) {
		double interest = amount * mounthlyInterest * mounths;
		return interest;
	}

}
