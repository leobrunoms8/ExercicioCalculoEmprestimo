package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;


public class ContractService {
	
	private OnlinePaymentsService onlinePaymentSrevice;

	public ContractService(OnlinePaymentsService onlinePaymentSrevice) {
		this.onlinePaymentSrevice = onlinePaymentSrevice;
	}
	
	public void processContract(Contract contract, int mounths) {
		double primaryQuota = contract.getTotalValue() / mounths; // Divide o valor do empréstimo pela quantidade de meses 
		for (int i = 1; i<mounths; i++) {
			Date date = addMonths(contract.getDate(), i);
			double secundaryQuota = onlinePaymentSrevice.interest(primaryQuota, i);//Adiciona os Juros mensais ao valor da parcela primaria
			double finalQuota = onlinePaymentSrevice.paymentFee(secundaryQuota);//Adiciona a Taxa 
			contract.addInstallment(new Installment(date, finalQuota));
		}
	}
	
	private Date addMonths(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}
	
	
	
	
	
}
