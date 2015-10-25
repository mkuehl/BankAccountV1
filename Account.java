package ba; 
 
class  Account {
	

	final static int OVERDRAFT_LIMIT = 0;

	

	//@ invariant balance >= OVERDRAFT_LIMIT;
	int balance = 0;

	
	
	/*@
	 @ ensures balance == 0;
	 @*/
	Account() {
	}

	
	
	/*@
	 @ ensures (!\result ==> balance == \old(balance)) 
	 @   && (\result ==> balance == \old(balance) + x); 
	 @*/
	boolean update(int x) {
		int newBalance = balance + x;
		if (newBalance < OVERDRAFT_LIMIT)
			return false;
		balance = newBalance;
		return true;
	}

	

	final static int INTEREST_RATE = 2;

	

	int interest = 0;

	

	/*@
	  @ ensures (balance >= 0 ==> \result >= 0) 
	  @   && (balance <= 0 ==> \result <= 0);
	  @*/
	/*@ pure @*/ int calculateInterest() {
		return balance * INTEREST_RATE / 36500;
	}

	

	/*@
	 @ requires daysLeft >= 0;
	 @ ensures calculateInterest() >= 0 ==> \result >= interest;
	 @*/
	/*@ pure @*/ int estimatedInterest(int daysLeft) {
		return interest + daysLeft * calculateInterest();
	}

	

	/*@
	 @ requires amount >= 0;
	 @ ensures balance >= amount <==> \result;
	 @*/
	boolean credit(int amount) {
		return balance >= amount;
	}


}
