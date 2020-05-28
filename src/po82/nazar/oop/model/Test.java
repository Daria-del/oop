package po82.nazar.oop.model;

public class Test {
    public static void main(String[] args) {
        AccountManager[] accountManagers = new AccountManager[2];
        Individual[] individuals = new Individual[4];
        Account[] accounts = new Account[8];
        for (int i = 0; i < 2; i++) {
            accountManagers[i] = new AccountManager(5);
            for (int j = 0; j < 4; j++) {
                individuals[j] = new Individual(9);
                for (int k = 0; k < 8; k++) {
                    accounts[k] = new Account(Integer.toString(1 + k), 100 + k + j + i);
                    individuals[j].addAccount(accounts[k]);
                    //System.out.println(accounts[k].toString());
                }
                //System.out.println(individuals[j].toString());
                accountManagers[i].addIndividual(individuals[j]);
            }
            System.out.println("------------------------------------");
            System.out.println(accountManagers[i].toString());
            System.out.println("------------------------------------");
        }
        System.out.println("------------------------------------");
        printAscendingByBalance(individuals[3].ascendingByBalance());
        System.out.println("------------------------------------");
        printIndividualsAscendingByBalance(accountManagers[1].ascendingIndividualByGeneralBalance());
    }

    public static void printAscendingByBalance(double[] balances) {
        for (double balance: balances
        ) {
            System.out.println(balance);
        }
    }
    public static  void printIndividualsAscendingByBalance(Individual[] individuals) {
        for (Individual individual: individuals
        ) {
            System.out.println(individual.getGeneralBalance());
        }
    }
}
