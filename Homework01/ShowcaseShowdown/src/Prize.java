// Oliver Benjamin
// CSE 146
// Homework01

public class Prize {
    private String prizeName;
    private int prizePrice;
    private boolean isValid;

    public Prize(String prize) {
        try {
            this.prizeName = prize.split("\t")[0];
            this.prizePrice = Integer.parseInt(prize.split("\t")[1]);
            this.isValid = true;
            
        } catch (Exception e) {
        }
            
    }

    public String getPrizeName() {
        return prizeName;
    }

    public int getPrizePrice() {
        return prizePrice;
    }

    public boolean isValid() {
        return isValid;
    }
    
}
