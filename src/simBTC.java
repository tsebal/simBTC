import java.math.RoundingMode;
import java.text.DecimalFormat;

public class simBTC {
    static int RUR = 74;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.########");
        df.setRoundingMode(RoundingMode.CEILING);

        double deposit = 10000;
        int course = 10000;
        double depositBTC = course/deposit;
        double profitUSD = 0;
        double profitBTC = 0;
        System.out.println("Start deposit " + deposit + "$ (" + deposit*RUR + " RUR)");
        for (int i = 0; i < 1000; i++) {
            int margin = (int) Math.round(Math.random());
            if (margin == 1) {
                margin = 1000;
                course += margin;
                profitUSD += (depositBTC/5)*course;
                depositBTC -= depositBTC/5;
            }
            else {
                margin = -1000;
                course += margin;
                if (course <= 1) {
                    course = 1000;
                    break;
                }
                depositBTC += profitUSD/3 / course;
                profitUSD -= profitUSD/3;
            }
            profitBTC = profitUSD / course;
            System.out.println(ANSI_BLUE + "Course = " + course + "$" + ANSI_RESET);
            System.out.println(" Deposit = " + df.format(depositBTC) + " BTC (" + Math.round(depositBTC*course*RUR) + " RUR)"
                    +" Profit = " + df.format(profitBTC) + " BTC (" + Math.round(profitBTC*course*RUR) + " RUR) "
                    + Math.round(profitUSD) + "$");
            System.out.println(ANSI_RED + "Total = " + df.format(depositBTC + profitBTC)
                    + " BTC (" + Math.round((depositBTC + profitBTC)*course*RUR) + " RUR)" + ANSI_RESET);
        }
        System.out.println("Clear profit = " + ((((depositBTC + profitBTC) * 100/ deposit) * 10000)-100) + "%");
    }
}