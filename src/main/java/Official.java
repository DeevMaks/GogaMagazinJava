public class Official {
    private final Sale sale = new Sale();
    private final Storage storage = new Storage();
    private final Account account = new Account();

    public Official() {
        sale.setStorage(storage);
    }

    public Storage getStorage() {
        return storage;
    }

    public Account getAccount() {
        return account;
    }

    public Sale getSale() {
        return sale;
    }
}

