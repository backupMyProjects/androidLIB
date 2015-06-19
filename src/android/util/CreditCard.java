package android.util;

import java.util.ArrayList;

public class CreditCard {

	private ArrayList<Integer> cardNumList = new ArrayList<Integer>();
	
	public static enum CreditCardtype{
		Visa, Master, JCB, AmericanExpress, Malformed, NotSupport;
	}
	
	public CreditCard(String cardNo) {
		getCardNumberLength(cardNo);
	}
	
	private void getCardNumberLength(String cardNo) {
		char[] cardcharArray = cardNo.toCharArray();
		for (char itemNo : cardcharArray) {
			try {
				int i = Integer.parseInt(String.valueOf(itemNo));
				cardNumList.add(i);
			} catch (Exception e) {

			}
		}
	}
	
	public String getCardType(){
		return creditCardProvider();
	}

	private String creditCardProvider() {
		String cardType = null;
		int number;
		switch (cardNumList.size()) {
		case 15:
			switch (cardNumList.get(0)) {
			case 1:
				number = cardNumList.get(0)*1000+cardNumList.get(1)*100+cardNumList.get(2)*10+cardNumList.get(3);
				if(number == 1800){
					cardType = CreditCardtype.JCB.toString();
				}else{
					cardType = CreditCardtype.Malformed.toString();
				}
				break;
			case 2:
				number = cardNumList.get(0)*1000+cardNumList.get(1)*100+cardNumList.get(2)*10+cardNumList.get(3);
				if(number == 2131){
					cardType = CreditCardtype.JCB.toString();
				}else{
					cardType = CreditCardtype.Malformed.toString();
				}
				break;
			case 3:
				number = cardNumList.get(0)*100+cardNumList.get(1)*10+cardNumList.get(2);
				if(number >= 340 && number <= 379){
					cardType = CreditCardtype.AmericanExpress.toString();
				}else{
					cardType = CreditCardtype.Malformed.toString();
				}
				break;
			default:
				cardType = CreditCardtype.NotSupport.toString();
				break;
			}
			if(!checkCardNumber(1)){
				cardType = CreditCardtype.Malformed.toString();
			}
			break;
		case 16:
			switch (cardNumList.get(0)) {
			case 3:
				number = cardNumList.get(0)*100+cardNumList.get(1)*10+cardNumList.get(2);
				if(number >= 300 && number <= 399){
					cardType = CreditCardtype.JCB.toString();
				}else{
					cardType = CreditCardtype.Malformed.toString();
				}
				break;
			case 4:
				cardType = CreditCardtype.Visa.toString();
				break;
			case 5:
				number = cardNumList.get(0)*10+cardNumList.get(1);
				if(number >= 51 && number <= 55){
					cardType = CreditCardtype.Master.toString();
				}else{
					cardType = CreditCardtype.Malformed.toString();
				}
				break;
			default:
				cardType = CreditCardtype.NotSupport.toString();
				break;
			}
			if(!checkCardNumber(0)){
				cardType = CreditCardtype.Malformed.toString();
			}
			break;

		default:
			cardType = CreditCardtype.Malformed.toString();
			break;
		}
		return cardType;
	}
	
	private boolean checkCardNumber(int first) {
		boolean cardNumber = false;
		int Total = 0;
		for (int i = first ; i < cardNumList.size(); i += 2) {
			int A,B,C,D,E, Tot;
			A = cardNumList.get(i);
			B = cardNumList.get(i+1);
			C = A*2;
			if(C >= 10){
				D = C / 10;
				E = C - 10;
				Tot = D + E + B;
			}else{
				E = C ;
				Tot = E + B;
			}
			Total = Total + Tot;
		}
		if( Total % 10 == 0 ){
			cardNumber = true;
		}else{
			cardNumber = false;
		}
		return cardNumber;
	}
}
