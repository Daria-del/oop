package po82.nazar.oop.model;

public class AccountManager {
    private Individual[] individuals;
    private int size;

    public AccountManager(int size) {
        this.individuals = new Individual[size];
        this.size = 0;
    }

    public AccountManager(Individual[] individuals) {
        this.individuals = individuals;
        this.size = individuals.length;
    }

    public boolean addIndividual(Individual newIndividual) {
        if (individuals.length == size) {
            invArr();
        }
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i] == null) {
                individuals[i] = newIndividual;
                size++;
                return true;
            }
        }
        return false;
    }

    public boolean addIndividualByNumber(int number, Individual newIndividual) {
        if (individuals.length == size) {
            invArr();
        }
        Individual[] shiftedArr = new Individual[individuals.length];
        System.arraycopy(individuals, 0, shiftedArr, 0, number);
        shiftedArr[number] = newIndividual;
        System.arraycopy(individuals, number, shiftedArr, number + 1, individuals.length - (number + 1));
        individuals = shiftedArr;
        return false;
    }

    private void invArr() {
        Individual[] newIndividuals = new Individual[individuals.length * 2];
        System.arraycopy(individuals, 0, newIndividuals, 0, individuals.length);
        individuals = newIndividuals;
    }

    private void decArr() {
        Individual[] newIndividuals = new Individual[individuals.length];
        int extraStep = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i] == null) {
                extraStep--;
                continue;
            }
            newIndividuals[i + extraStep] = individuals[i];
        }
        individuals = newIndividuals;
    }

    public Individual getIndividualByNumber(int number) {
        return individuals[number];
    }

    public Individual changeIndividualByNumber(int number, Individual newIndividual) {
        Individual oldIndividual = individuals[number];
        individuals[number] = newIndividual;
        return oldIndividual;
    }

    public Individual deleteIndividualByNumber(int number) {
        Individual deletedIndividual = individuals[number];
        individuals[number] = null;
        decArr();
        return deletedIndividual;
    }

    public int getIndividualsQuantity() {
        return size;
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

    public Individual[] ascendingIndividualByGeneralBalance() {
        Individual[] individuals = getClearIndividuals();
        Individual bufferIndividual;
        boolean repeat = true;
        for (int i = 0; i < individuals.length; i++) {
            if(repeat) {
                i = 0;
            }
            if (i != individuals.length - 1 && individuals[i].getGeneralBalance() < individuals[i + 1].getGeneralBalance()) {
                bufferIndividual = individuals[i];
                individuals[i] = individuals[i + 1];
                individuals[i + 1] = bufferIndividual;
                repeat = true;
            } else {
                repeat = false;
            }
        }
        return individuals;
    }

    public Individual[] getClearIndividuals() {
        Individual[] clearIndividuals = new Individual[size];
        System.arraycopy(individuals, 0, clearIndividuals, 0, size);
        return clearIndividuals;
    }

    public Account getAccountByBalanceNumber(String number) {
        for (Individual individual: individuals
        ){
            Account account = individual.getAccountByNumberOfBalance(number);
            if (account != null)
                return account;
        }
        return null;
    }

    public Account deleteAccountByBalanceNumber(String number) {
        for (Individual individual: individuals
        ){
            Account deletedAccount = individual.deleteAccountByNumberOfBalance(number);
            if (deletedAccount != null)
                return deletedAccount;
        }
        return null;
    }

    public Account changeAccountByNumber(int number, Account newAccount) {
        for (Individual individual: individuals
        ){
            Account oldAccount = individual.changeAccountByNumber(number, newAccount);
            if (oldAccount != null)
                return oldAccount;
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "";
        for (Individual individual: individuals
        ) {
            if (individual != null) {
                str = str.concat(individual.toString()).concat("\n");
            } else {
                str = str.concat("null").concat("\n");
            }
        }
        return str;
    }
}
