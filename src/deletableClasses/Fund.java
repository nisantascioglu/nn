package deletableClasses;

public class Fund {
    private Long id;
    private double balance;

        public String displayFunds() {
            System.out.println("Funds displayed.");
            return "Fund Information";
        }

        public void updateFunds() {
            System.out.println("Funds have been updated.");
        }

        public void showAlert() {
            System.out.println("Warnings displayed.");
        }

        public double calculateInterest() {
            System.out.println("Interest has been calculated.");
            return 0.0;
        }

}
