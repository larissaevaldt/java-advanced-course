package jdbc;

import javax.xml.transform.Result;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CallingStoredProcedures {

    private static Connection con;
    private static BankService bank = new BankService();

    public CallingStoredProcedures() {
        try {
            con = DriverManager
                    .getConnection("jdbc:derby://localhost:1527/BANK_DB",
                            "sean", "sean");
            System.out.println("DB connection OK!");
        } catch (SQLException ex) {
            System.err.println("Exception.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        noParams();
        inParams();
        outParam();
        inOutParam();
    }

    public static void noParams() {
        // DB - "CREATE PROCEDURE read_dublin_address()"
        String noParamsSQL = "{call read_dublin_addresses()}";
        // try-with-resources will tidy up
        // PreparedStatement ps = con.prepareStatement(sql)
        try(CallableStatement cs = con.prepareCall(noParamsSQL)) {
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("CUST_ADDRESS"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void inParams() {
        // DB - "CREATE PROCEDURE read_address(?)"
        String inParamsSQL = "{call read_addresses(?)}";
        try(CallableStatement cs = con.prepareCall(inParamsSQL)) {

            cs.setString(1, "Dublin"); // PreparedStatement can do this
            // cs.setString("address", "Dublin");      // CallableStatement only


            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("CUST_ADDRESS"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void outParam() {
        String outParamSQL = "{?= call count_customers(?) }";
        try(CallableStatement cs = con.prepareCall(outParamSQL)) {

            cs.registerOutParameter(1, Types.INTEGER); // do this for each OUT, INOUT parameter
            cs.execute(); // no ResultSet coming back this time
            System.out.println(cs.getInt("count"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void inOutParam() {
        String inOutParamSQL = "{ call square_number(?) }";
        // DB - "CREATE PROCEDURE square_number(INOUT number INT)"
        try(CallableStatement cs = con.prepareCall(inOutParamSQL)) {

            cs.setInt(1, 5);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            System.out.println(cs.getInt("number"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
