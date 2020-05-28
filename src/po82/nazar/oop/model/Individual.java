package po82.nazar.oop.model;

public class Individual {
    private Account[] accounts;
    private int size;

    public Individual() {
        this.accounts = new Account[16];
        this.size = 0;
    }

    public Individual(int size) {
        this.accounts = new Account[size];
        this.size = 0;
    }

    public Individual(Account[] accounts) {
        this.accounts = accounts;
        this.size = accounts.length;
    }

    public boolean addAccount(Account newAccount) {
        if (accounts.length == size) {
            invArr();
        }
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = newAccount;
                size++;
                return true;
            }
        }
        return false;
    }

    public boolean addAccountByNumber(int number, Account newAccount) {
        if (accounts.length == size) {
            invArr();
        }
        Account[] shiftedArr = new Account[accounts.length];
        System.arraycopy(accounts, 0, shiftedArr, 0, number);
        shiftedArr[number] = newAccount;
        System.arraycopy(accounts, number, shiftedArr, number + 1, accounts.length - (number + 1));
        accounts = shiftedArr;
        return false;
    }

    private void invArr() {
        Account[] newAccounts = new Account[accounts.length * 2];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        accounts = newAccounts;
    }

    private void decArr() {
        Account[] newAccounts = new Account[accounts.length];
        int extraStep = 0;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                extraStep--;
                continue;
            }
            newAccounts[i + extraStep] = accounts[i];
        }
        accounts = newAccounts;
    }

    public Account getAccountByNumber(int number) {
        return accounts[number];
    }

    public Account getAccountByNumberOfBalance(String number) {
        for (Account account : accounts
        ) {
            if (account.getNumber().equals(number)) {
                return account;
            }
        }
        return null;
    }

    public boolean isThereNumberOfBalance(String number) {
        for (Account account : accounts
        ) {
            if (account.getNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    public Account changeAccountByNumber(int number, Account newAccount) {
        Account oldAccount = accounts[number];
        accounts[number] = newAccount;
        return oldAccount;
    }

    public Account deleteAccountByNumber(int number) {
        Account deletedAccount = accounts[number];
        accounts[number] = null;
        decArr();
        return deletedAccount;
    }

    public Account deleteAccountByNumberOfBalance(String number) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getNumber().equals(number)) {
                Account deletedAccount = accounts[i];
                accounts[i] = null;
                decArr();
                size--;
                return deletedAccount;
            }
        }
        return null;
    }

    public int getAccountsQuantity() {
        return size;
    }

    public Account[] getClearAccounts() {
        Account[] clearAccounts = new Account[size];
        System.arraycopy(accounts, 0, clearAccounts, 0, size);
        return clearAccounts;
    }

    public double[] ascendingByBalance() {
        Account[] accounts = getClearAccounts();
        Account bufferAccount;
        boolean repeat = true;
        for (int i = 0; i < accounts.length; i++) {
            if (repeat) {
                i = 0;
            }
            if (i != accounts.length - 1 && accounts[i].getBalance() < accounts[i + 1].getBalance()) {
                bufferAccount = accounts[i];
                accounts[i] = accounts[i + 1];
                accounts[i + 1] = bufferAccount;
                repeat = true;
            } else {
                repeat = false;
            }
        }
        double[] balances = new double[accounts.length];
        for (int i = 0; i < accounts.length; i++) {
            balances[i] = accounts[i].getBalance();
        }
        return balances;
    }

    public double getGeneralBalance() {
        double generalBalance = 0;
        for (Account account : accounts
        ) {
            if (account != null) {
                generalBalance += account.getBalance();
            }
        }
        return generalBalance;
    }

    @Override
    public String toString() {
        String str = "";
        for (Account account : accounts
        ) {
            if (account != null) {
                str = str.concat(account.toString()).concat("\n");
            } else {
                str = str.concat("null").concat("\n");
            }
        }
        return str;
    }
}
