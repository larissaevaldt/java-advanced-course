package jdbc;

import java.sql.Connection;      // factory class for creating the db connection
import java.sql.DriverManager;   // required interface
import java.sql.PreparedStatement;
import java.sql.SQLException;    // required interface
import java.sql.ResultSet;      // required interface
import java.util.ArrayList;

// java -cp <path_to_derby>/derby.jar BankService.java
public class BankService {

    private static Connection con;
    private static BankService bank = new BankService(); // connect to db

    public BankService() {
        try {
            // the DriverManager is one of 2 ways of creating a connection
            // the other one is DataSource
            // in industry you'd use DataSource, it's far more efficient
            // However the DriverManager is the one that is specified for the exam
            con = DriverManager
                    .getConnection("jdbc:derby://localhost:1527/BANK_DB",
                            "sean", "sean"); // never expose your password, it should be loaded in from an external resource, and preferably encrypted
            System.out.println("DB connection OK!");
        } catch (SQLException ex) {  // SQLException is a checked exception
            System.err.println("Exception.");
            ex.printStackTrace();
        }

        // retrieve everything from APP.BANK_TABLE
        String selectSQL = "SELECT * FROM APP.BANK_TABLE";
        try (PreparedStatement ps = con.prepareStatement(selectSQL)) {
            // Note: 'var ps = con.prepareStatement(selectSQL)' is ok too.
            // Failure to pass SQL (a String) into prepareStatement()
            // is a compiler error i.e. con.prepareStatement() is a compiler error
            // Do something with 'ps'...

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void main(String[] args) {
        bank.retrieveOne();
        bank.retrieveAll();
        bank.deleteOne();
        bank.deleteAll();
        bank.add();
        bank.update();
    }

    public void retrieveOne() {
        // retrieve one bank account
        System.out.println(bank.getAccountDetails("123456", "12345678"));
    }

    public void retrieveAll() {
        // retrieve all bank accounts
        for (BankAccount bankAccount : bank.getAllAccounts()) {
            System.out.println(bankAccount);
        }
    }

    public void deleteOne() {
        int nRows = bank.deleteBankAccount("123456", "12345678");
        if (nRows == 1) {
            System.out.println("Delete OK: " + nRows);
        } else {
            System.out.println("Delete error: " + nRows);
        }
    }

    public void deleteAll() {
        // delete all bank accounts
        bank.deleteAllAccounts();
    }

    public void add() {
        // add a bank account
        int nRows = bank.addBankAccount(
                new BankAccount("9999999", "8888888888",
                        "SK", "Dublin", 100)
        );
        if (nRows == 1) {
            System.out.println("Add OK: " + nRows);
        } else {
            System.out.println("Add error: " + nRows);
        }
    }

    public void update() {
        // add a bank account
        BankAccount bankAccount = bank.getAccountDetails("123456", "12345678");
        System.out.println("BEFORE Update: " + bankAccount);
        bankAccount.setCustName("J. Bloggs");
        bankAccount.setCustAddress("London");
        int nRows = bank.updateBankAccount(bankAccount);
        if (nRows == 1) {
            System.out.println("Update OK: " + nRows);
            System.out.println("AFTER Update: " + bank.getAccountDetails("123456", "12345678"));
        } else {
            System.out.println("Update error: " + nRows);
        }
    }

    // SELECT one account
    public BankAccount getAccountDetails(String branchCode, String accountNo) {
        // A '?' is called a 'bind variable' It is a placeholder that we can populate at run time
        String selectSQL = "SELECT * FROM APP.BANK_TABLE WHERE (BRANCH_CODE = ? AND ACCOUNT_NUMBER=?)";
        BankAccount bankAccount= null;

        // Cannot put the prepareStatement() and executeQuery() in the try-with-resources braces
        // as we have bind variables that must be set in between these 2 commands
        try (PreparedStatement ps = con.prepareStatement(selectSQL)) {

            ps.setString(1, branchCode); // bind variables start at 1
            ps.setString(2, accountNo);

            ResultSet rs = ps.executeQuery();    // implies SELECT

            // Move the cursor to the first row
            if (!rs.next()) {  // !F==T i.e. no record there at all.
                return bankAccount;  // null
            }

            // process the record
            bankAccount = new BankAccount(
                    // API:
                    // boolean rs.getBoolean(String columnLabel) also overload: boolean rs.getBoolean(int columnIndex)
                    // double rs.getDouble(String columnLabel) also overload: double rs.getDouble(int columnIndex)
                    // int rs.getInt(String columnLabel) also overload: int rs.getInt(int columnIndex)
                    // long rs.getLong(String columnLabel) also overload: long rs.getLong(int columnIndex)
                    // T rs. getObject(String columnLabel, Class<T> type) also overload:
                    //      T rs.getObject(int columnIndex, Class<T> type)
                    rs.getString("BRANCH_CODE"),
                    rs.getString(2), // "ACCOUNT_NUMBER"
                    rs.getString("CUST_NAME"),
                    rs.getString("CUST_ADDRESS"),
                    rs.getDouble("BALANCE"));

        } catch (SQLException sqle) {
            System.err.println("SQLException in getAccountDetails()");
            sqle.printStackTrace();
        }

        return bankAccount;
    }

    // SELECT all account
    public ArrayList<BankAccount> getAllAccounts() {
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();

        String selectSQL = "SELECT * FROM APP.BANK_TABLE"; // no WHERE clause this time => get all records/rows
        try (PreparedStatement ps = con.prepareStatement(selectSQL)) {
            boolean isResultSet = ps.execute();
            if (isResultSet) {
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    // process the record
                    BankAccount bankAccount = new BankAccount(
                            rs.getString(1),  // "BRANCH_CODE"
                            rs.getString("ACCOUNT_NUMBER"),
                            rs.getString("CUST_NAME"),
                            rs.getString("CUST_ADDRESS"),
                            rs.getDouble("BALANCE"));
                    bankAccounts.add(bankAccount);
                }
            } else {
                System.out.println("Did an update!");
            }

        } catch (SQLException sqle) {
            System.err.println("SQLException in getAllAccounts()");
            sqle.printStackTrace();
        }

        return bankAccounts;
    }

    // DELETE one account
    public int deleteBankAccount(String branchCode, String accountNo) {
        int nRows = -1;
        String deleteSQL = "DELETE FROM APP.BANK_TABLE WHERE (BRANCH_CODE = ? AND ACCOUNT_NUMBER=?)";

        try (PreparedStatement ps = con.prepareStatement(deleteSQL)) {
            ps.setString(1, branchCode);
            ps.setString(2, accountNo);

            nRows = ps.executeUpdate();

        } catch (SQLException sqle) {
            System.err.println("SQLException in deleteBankAccount()");
            sqle.printStackTrace();
        }

        return nRows;
    }

    // DELETE all account
    public void deleteAllAccounts() {
        String deleteSQL = "DELETE FROM APP.BANK_TABLE";

        try (PreparedStatement ps = con.prepareStatement(deleteSQL)) {

            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.err.println("SQLException in deleteAllAccounts()");
            sqle.printStackTrace();
        }
    }

    // INSERT a bank account
    public int addBankAccount(BankAccount ba) {
        int nRows = -1;
        String insertSQL = "INSERT INTO APP.BANK_TABLE " +
                "(BRANCH_CODE, ACCOUNT_NUMBER," +
                "CUST_NAME, CUST_ADDRESS, BALANCE) " +
                "VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(insertSQL)) {
            //ps.setBoolean(int parameterIndex, boolean x)
            //ps.setInt(int parameterIndex, int x)
            //ps.setObject(int parameterIndex, Object x)
            //ps.setDouble(int parameterIndex, double x)
            //ps.setLong(int parameterIndex, long x)
            ps.setString(1, ba.getBranchCode()); // bind variables start at 1
            ps.setString(2, ba.getAccountNo());
            ps.setString(3, ba.getCustName());
            ps.setString(4, ba.getCustAddress());
            ps.setDouble(5, ba.getBalance());

            nRows = ps.executeUpdate();

        } catch (SQLException sqle) {
            System.err.println("SQLException in addBankAccount()");
            sqle.printStackTrace();
        }

        return nRows;
    }

    // UPDATE a bank account
    public int updateBankAccount(BankAccount ba) {
        int nRows = -1;
        String updateSQL = "UPDATE APP.BANK_TABLE " +
                "SET CUST_NAME=?, CUST_ADDRESS=?, BALANCE=? " +
                "WHERE (BRANCH_CODE = ? AND ACCOUNT_NUMBER= ?)";

        try (PreparedStatement ps = con.prepareStatement(updateSQL)) {
            ps.setString(1, ba.getCustName());
            ps.setString(2, ba.getCustAddress());
            ps.setDouble(3, ba.getBalance());
            ps.setString(4, ba.getBranchCode());
            ps.setString(5, ba.getAccountNo());

            nRows = ps.executeUpdate();

        } catch (SQLException sqle) {
            System.err.println("SQLException in updateBankAccount()");
            sqle.printStackTrace();
            return nRows;
        }

        return nRows;
    }

}
