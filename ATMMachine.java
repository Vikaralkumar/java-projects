import java.util.*;
class ATM{
    float Balance;
    int PIN=8520;
    //pin validation
    public void checkpin(){
        System.out.println("Enter your Pin");
        Scanner sc=new Scanner(System.in);
        int enteredpin=sc.nextInt();
        if(enteredpin==PIN){
            menu(); // call the menu function 
        }
        else{
            System.out.println("Invalid Pin");
            System.out.println();
            checkpin();
        }
    }
  //option menu or Home Page
    public void menu(){
        System.out.println("Enter Your Choice");
        System.out.println("1. Check A/C Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. EXIT");

        Scanner sc=new Scanner(System.in);
        int opt=sc.nextInt();

        if(opt==1){
            checkBalance();
        }
        else if(opt==2){
            withdrawMoney();
        }
        else if(opt==3){
            depositMoney();
        }
        else if(opt==4){
            return;
        }
    }
    //Balance equiry 
    public void checkBalance(){
        System.out.println("Balance : "+Balance);
        menu();
    }
   //Balance Withdraw
    public void withdrawMoney(){
        System.out.println("Enter Amount to Withdraw");
        Scanner sc=new Scanner(System.in);
        float amount=sc.nextFloat();
        if(amount>Balance){
            System.out.println("Insufficient Balance");
        }
        else{
            Balance=Balance-amount;
            System.out.println("Money Withdraw Successfully");
        }
        menu();
    }
    // Balance Deposit
    public void depositMoney(){
        System.out.println("Enter the Amount");
        Scanner sc=new Scanner(System.in);
        float amount=sc.nextFloat();
        Balance=Balance+amount;
        System.out.println("Money Deposited Successfully");
        menu();
    }

}


public class ATMMachine{

public static void main(String[] args) {
    ATM obj=new ATM();  //Create a object
    obj.checkpin();    //Call the method
    
}
    
}
