import java.util.Scanner;

public class AccountDriver {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //create array of accounts
        Account[] accounts = new Account[10];
        int numAccounts = 0;

        int choice;

        do {
            choice = menu(sc);
            System.out.println();

            if (choice == 1) {
                accounts[numAccounts++] = createAccount(sc);

            } else if (choice == 2) {
                doDeposit(accounts, numAccounts, sc);
            } else if (choice == 3){
                doWithdraw(accounts, numAccounts,sc);
            } else if (choice == 4) {
                applyInterest(accounts,numAccounts,sc);
            }else{
                System.out.println("Goodbye!");
            }
            System.out.println();
        } while (choice != 5);


    }

    public static int seaarchAccount(Account accounts[], int count, int accountNumber) {

        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return i;
            }
        }

        return -1;
    }


    /**
     * @param
     * @param count
     */
    public static void doDeposit(Account accounts[], int count, Scanner sc) {

        //Get account number
        System.out.println("\nPease enter account number: ");
        int accountNumber = sc.nextInt();

        //search for account
        int index = seaarchAccount(accounts, count, accountNumber);

        if (index >= 0) {
            System.out.println("please enter deposit amount");
            double amount = sc.nextDouble();
            accounts[index].deposit(amount);
        } else {
            System.out.println("No account exist with the AccountNumber: " + accountNumber);
        }
    }

    public static void doWithdraw(Account accounts[], int count, Scanner sc) {
        //Get account number
        System.out.println("\nPease enter account number: ");
        int accountNumber = sc.nextInt();

        //search for account
        int index = seaarchAccount(accounts, count, accountNumber);

        if (index >= 0) {
            System.out.println("please enter withdraw amount");
            double amount = sc.nextDouble();
            accounts[index].withdraw(amount);
        } else {
            System.out.println("No account exist with the AccountNumber: " + accountNumber);
        }
    }

    public static void applyInterest(Account accounts[], int count, Scanner sc) {
        //Get account number
        System.out.println("\nPease enter account number: ");
        int accountNumber = sc.nextInt();

        //search for account
        int index = seaarchAccount(accounts, count, accountNumber);

        if (index >= 0) {
            //must be instance saving account
            if (accounts[index] instanceof SavingAccount) {
                ((SavingAccount) accounts[index]).applyInterest();
            }

        } else {
            System.out.println("No account exist with the AccountNumber: " + accountNumber);
        }
    }


    /**
     * this allow user to choice between accounts
     *
     * @param sc
     * @return
     */
    public static int accountMenu(Scanner sc) {
        System.out.println("Select Account Type");
        System.out.println("1. Checking Account");
        System.out.println("2. Saving Account");

        int choice;
        do {
            System.out.println("Enter choice:");
            choice = sc.nextInt();
        } while (choice < 1 || choice > 2);

        return choice;
    }


    /**
     * fuction to create a new account
     *
     * @param sc
     * @return
     */
    public static Account createAccount(Scanner sc) {
        Account account = null;
        int choice = accountMenu(sc);


        int accountNumber;
        System.out.println("Enter Account Number: ");
        accountNumber = sc.nextInt();
        if (choice == 1) {// checking account
            System.out.println("Enter Transaction");
            double fee = sc.nextDouble();
            account = new CheckingAccount(accountNumber, fee);

        } else {
            System.out.println("Please enter interest rate: "); //saving account
            double ir = sc.nextDouble();

            account = new SavingAccount(accountNumber, ir);
        }
        return account;
    }


    /**
     * Display menu for different selections
     *
     * @param sc
     * @return
     */

    public static int menu(Scanner sc) {
        System.out.println("Bank Account Menu");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("3. Apply Interest");
        System.out.println("4. Quit");

        int choice;

        do {
            System.out.println("Please pick from one of the following selection");
            choice = sc.nextInt();


        } while (choice < 1 || choice > 5);

        return choice;
    }


}
