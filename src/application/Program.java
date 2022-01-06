package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		System.out.println("Enter Contract data");
		System.out.print("Number: ");
		int contractNumber = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		Date contractDate = sdf.parse(sc.next());
		System.out.print("Contract value: ");
		double contractValue = sc.nextDouble();
		
		Contract contract = new Contract(contractNumber, contractDate, contractValue);
		
		System.out.print("Enter number of installments: ");
		int numberInstallments = sc.nextInt();
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, numberInstallments);
		
		System.out.println("Installments: ");
		
		for (Installment x : contract.getInstallment()) {
			System.out.println(x);
		}
	
		
		
		
		sc.close();

	}

}
