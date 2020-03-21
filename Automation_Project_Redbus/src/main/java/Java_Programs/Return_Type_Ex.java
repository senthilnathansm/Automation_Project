package Java_Programs;

public class Return_Type_Ex {

	public Integer money = 1000;

	public Integer getthemoney(Integer money) {
		System.out.println("The Given Amont " + money);
		return money;
	}

	public static void main(String args[]) {
		Return_Type_Ex obj = new Return_Type_Ex();
		Integer amt = obj.getthemoney(2000);
		System.out.println(amt);
	}

}
