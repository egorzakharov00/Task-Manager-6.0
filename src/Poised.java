// import packages
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.time.LocalDate;
import java.sql.*;

/**
 * The Poised program is a project management system for a small structural engineering firm called Poised
 *
 * @author egorzakharov
 * @version 1.0
 * @since 2021-07-09
 */
public class Poised {

    /**
     * Static variables needed to make changes and read data from tables in the database
     */
    static Statement statement;
    static ResultSet resultSet;

    /**
     * This is a method that is used to create and add a new architect to the Architects table
     * by getting user input and adding it to the table in the database
     *
     * @return returns an integer representing the architect's unique id
     * @throws SQLException throws a SQLException error
     */
    public static int newArchitect() throws SQLException {
        // temporary scanner object
        Scanner temp = new Scanner(System.in);
        // variables for details about architect
        String architectName;
        String architectNumber;
        String architectEmail;
        String architectAddress;
        System.out.println("Architect details");
        // while loop to get all the architect's details
        while (true) {
            // output instruction and get user input
            System.out.print("Enter the architect's name and surname: ");
            architectName = temp.nextLine();
            System.out.print("Enter the architect's telephone number: ");
            architectNumber = temp.nextLine();
            System.out.print("Enter the architect's email address: ");
            architectEmail = temp.nextLine();
            System.out.print("Enter the architect's physical address: ");
            architectAddress = temp.nextLine();
            // if any input is blank output message
            if (architectName.isBlank()
                    || architectNumber.isBlank()
                    || architectEmail.isBlank()
                    || architectAddress.isBlank())
                System.out.println("One or more of the fields above were left empty. Re-enter the architect's details.");
                // else if all the input is entered correctly break out of loop
            else
                break;
        }
        // initialise id variable
        int id = 1;
        // executeQuery: runs a SELECT statement and returns the results.
        resultSet = statement.executeQuery("SELECT MAX(id) FROM Architects");
        // Loop over the results and set the id to 1 integer above the maximum id in the table
        while (resultSet.next())
            id = resultSet.getInt("MAX(id)") + 1;
        // insert new architect into table
        statement.executeUpdate("INSERT INTO Architects VALUES(" + id + ", '" + architectName
                + "', '" + architectNumber + "', '" + architectEmail + "', '" + architectAddress + "')");
        // output message and return id
        System.out.println("Architect successfully stored\n");
        return id;

    }

    /**
     * This is a method that is used to create and add a new contractor to the Contractors table
     * by getting user input and adding it to the table in the database
     *
     * @return returns an integer representing the contractor's unique id
     * @throws SQLException throws a SQLException error
     */
    public static int newContractor() throws SQLException {
        // temporary scanner object
        Scanner temp = new Scanner(System.in);
        // variables for details about contractor
        String contractorName;
        String contractorNumber;
        String contractorEmail;
        String contractorAddress;
        System.out.println("Contractor details");
        // while loop to get all architect details
        while (true) {
            // output instruction and get user input
            System.out.print("Enter the contractor's name and surname: ");
            contractorName = temp.nextLine();
            System.out.print("Enter the contractor's telephone number: ");
            contractorNumber = temp.nextLine();
            System.out.print("Enter the contractor's email address: ");
            contractorEmail = temp.nextLine();
            System.out.print("Enter the contractor's physical address: ");
            contractorAddress = temp.nextLine();
            // if any input is blank output message
            if (contractorName.isBlank()
                    || contractorNumber.isBlank()
                    || contractorEmail.isBlank()
                    || contractorAddress.isBlank())
                System.out.println("One or more of the fields above were left blank. Re-enter the contractor's details.");
                // else if all the input is entered correctly break out of loop
            else
                break;
        }
        // initialise id
        int id = 1;
        // executeQuery: runs a SELECT statement and returns the results.
        resultSet = statement.executeQuery("SELECT MAX(id) FROM Contractors");
        // Loop over the results and set the id to 1 integer above the maximum id in the table
        while (resultSet.next())
            id = resultSet.getInt("MAX(id)") + 1;
        // insert new contractor into the table
        statement.executeUpdate("INSERT INTO Contractors VALUES(" + id + ", '" + contractorName
                + "', '" + contractorNumber + "', '" + contractorEmail + "', '" + contractorAddress + "')");
        // output message and return id
        System.out.println("Contractor successfully stored\n");
        return id;
    }

    /**
     * This is a method that is used to create and add a new customer to the Customers table
     * by getting user input and adding it to the table in the database
     *
     * @return returns an integer representing the customer's unique id
     * @throws SQLException throws a SQLException error
     */
    public static int newCustomer() throws SQLException {
        // temporary scanner object
        Scanner temp = new Scanner(System.in);
        System.out.println("Customer details");
        // variable for customer name
        String customerName;
        System.out.print("Enter the customer's name and surname: ");
        // while loop until try block is executed
        // ensures both name and surname of customer is entered
        // (in the case that the project name is left empty and surname is needed)
        while (true) {
            // try split the string entered to see if it consists of two names then break out of loop
            try {
                customerName = temp.nextLine();
                String[] nameArr = customerName.split(" ");
                String surname = nameArr[1];
                break;
            }
            // catch if fails and print out error message
            catch (Exception e) {
                System.out.print("Invalid - enter the customer's name AND surname separated by a space: ");
            }
        }
        // variables about customer details
        String customerNumber;
        String customerEmail;
        String customerAddress;
        // while loop to get all customer details
        while (true) {
            System.out.print("Enter the customer's telephone number: ");
            customerNumber = temp.nextLine();
            System.out.print("Enter the customer's email address: ");
            customerEmail = temp.nextLine();
            System.out.print("Enter the customer's physical address: ");
            customerAddress = temp.nextLine();
            // if any input is blank output message
            if (customerNumber.isBlank()
                    || customerEmail.isBlank()
                    || customerAddress.isBlank())
                System.out.println("One or more of the fields above were left blank. Re-enter the customer's details.");
                // else if all the input is entered correctly break out of loop
            else
                break;
        }
        double projectFee;
        double amountPaid;
        while (true) {
            // try get a numerical value for each field and break out of while loop
            try {
                System.out.print("Enter the total fee being charged for the project: ");
                projectFee = temp.nextDouble();
                System.out.print("Enter total amount paid to date: ");
                amountPaid = temp.nextDouble();
                break;
            }
            // catch if numerical value is not entered and output message
            catch (Exception e) {
                // skip empty line entered by user
                temp.nextLine();
                System.out.println("Invalid input. A numerical value is needed for the field(s) above.");
            }
        }
        // initialise id
        int id = 1;
        // executeQuery: runs a SELECT statement and returns the results.
        resultSet = statement.executeQuery("SELECT MAX(id) FROM Customers");
        // Loop over the results and set the id to 1 integer above the maximum id in the table
        while (resultSet.next())
            id = resultSet.getInt("MAX(id)") + 1;
        // insert new customer into the table
        statement.executeUpdate("INSERT INTO Customers VALUES(" + id + ", '" + customerName
                + "', '" + customerNumber + "', '" + customerEmail + "', '" + customerAddress
                + "', " + projectFee + ", " + amountPaid + ")");
        // output message and return id
        System.out.println("Customer successfully stored\n");
        return id;
    }

    /**
     * This is a method that is used to create and add a new engineer to the Engineers table
     * by getting user input and adding it to the table in the database
     *
     * @return returns an integer representing the engineer's unique id
     * @throws SQLException throws a SQLException error
     */
    public static int newEngineer() throws SQLException {
        // temporary scanner object
        Scanner temp = new Scanner(System.in);
        // variables for details about engineer
        String engineerName;
        String engineerNumber;
        String engineerEmail;
        String engineerAddress;
        System.out.println("Engineer details");
        // while loop to get all engineer details
        while (true) {
            // output instruction and get user input
            System.out.print("Enter the engineer's name and surname: ");
            engineerName = temp.nextLine();
            System.out.print("Enter the engineer's telephone number: ");
            engineerNumber = temp.nextLine();
            System.out.print("Enter the engineer's email address: ");
            engineerEmail = temp.nextLine();
            System.out.print("Enter the engineer's physical address: ");
            engineerAddress = temp.nextLine();
            // if any input is blank output message
            if (engineerName.isBlank()
                    || engineerNumber.isBlank()
                    || engineerEmail.isBlank()
                    || engineerAddress.isBlank())
                System.out.println("One or more of the fields above were left empty. Re-enter the engineer's details.");
                // else if all the input is entered correctly break out of loop
            else
                break;
        }
        // initialise id
        int id = 1;
        // executeQuery: runs a SELECT statement and returns the results.
        resultSet = statement.executeQuery("SELECT MAX(id) FROM Engineers");
        // Loop over the results and set the id to 1 integer above the maximum id in the table
        while (resultSet.next())
            id = resultSet.getInt("MAX(id)") + 1;
        // insert new engineer into the table
        statement.executeUpdate("INSERT INTO Engineers VALUES(" + id + ", '" + engineerName
                + "', '" + engineerNumber + "', '" + engineerEmail + "', '" + engineerAddress + "')");
        // output message and return id
        System.out.println("Engineer successfully stored\n");
        return id;

    }

    /**
     * This is a method that is used to create and add a new manager to the Manager table
     * by getting user input and adding it to the table in the database
     *
     * @return returns an integer representing the manager's unique id
     * @throws SQLException throws a SQLException error
     */
    public static int newManager() throws SQLException {
        // temporary scanner object
        Scanner temp = new Scanner(System.in);
        // variables for details about architect
        String managerName;
        String managerNumber;
        String managerEmail;
        String managerAddress;
        System.out.println("Manager details");
        // while loop to get all architect details
        while (true) {
            // output instruction and get user input
            System.out.print("Enter the manager's name and surname: ");
            managerName = temp.nextLine();
            System.out.print("Enter the manager's telephone number: ");
            managerNumber = temp.nextLine();
            System.out.print("Enter the manager's email address: ");
            managerEmail = temp.nextLine();
            System.out.print("Enter the manager's physical address: ");
            managerAddress = temp.nextLine();
            // if any input is blank output message
            if (managerName.isBlank()
                    || managerNumber.isBlank()
                    || managerEmail.isBlank()
                    || managerAddress.isBlank())
                System.out.println("One or more of the fields above were left empty. Re-enter the manager's details.");
                // else if all the input is entered correctly break out of loop
            else
                break;
        }
        // initialise id
        int id = 1;
        // executeQuery: runs a SELECT statement and returns the results.
        resultSet = statement.executeQuery("SELECT MAX(id) FROM Managers");
        // Loop over the results and set the id to 1 integer above the maximum id in the table
        while (resultSet.next())
            id = resultSet.getInt("MAX(id)") + 1;
        // insert new manager into the table
        statement.executeUpdate("INSERT INTO Managers VALUES(" + id + ", '" + managerName
                + "', '" + managerNumber + "', '" + managerEmail + "', '" + managerAddress + "')");
        // output message and return id
        System.out.println("Manager successfully stored\n");
        return id;

    }

    /**
     * This is a method that is used to create and add a new Project to the Projects table
     * by getting user input and adding it to the table in the database
     *
     * @throws SQLException throws a SQLException error
     */
    public static void newProject() throws SQLException {
        // call methods to create necessary people for the project and store their unique IDs in variables
        int architect_id = newArchitect();
        int contractor_id = newContractor();
        int customer_id = newCustomer();
        int engineer_id = newEngineer();
        int manager_id = newManager();
        // initialise id
        int proj_num = 1;
        // executeQuery: runs a SELECT statement and returns the results.
        resultSet = statement.executeQuery("SELECT MAX(number) FROM Projects");
        // Loop over the results and set the project number to 1 integer above the maximum project number in the table
        while (resultSet.next())
            proj_num = resultSet.getInt("MAX(number)") + 1;
        // temporary scanner object
        Scanner temp = new Scanner(System.in);
        System.out.println("Project details");
        // variables for numerical fields
        int erfNumber;
        // while loop until try block is executed
        // ensures only numerical values are entered
        while (true) {
            // try get a numerical value for each field and break out of while loop
            try {
                System.out.print("Enter the ERF number: ");
                erfNumber = temp.nextInt();
                break;
            }
            // catch if numerical value is not entered and output message
            catch (Exception e) {
                // skip empty line entered by user
                temp.nextLine();
                System.out.println("Invalid input. A numerical value is needed for the field(s) above.");
            }
        }
        // skip empty line entered by user
        temp.nextLine();
        // project name can be blank
        System.out.print("Enter the project name: ");
        String projectName = temp.nextLine();
        // other string variables for project
        String buildingType;
        String projectAddress;
        // while loop to ensure input is not blank
        while (true) {
            System.out.print("What type of building is being designed? E.g. House, apartment block or store, etc.: ");
            buildingType = temp.nextLine();
            System.out.print("Enter the physical address for the project: ");
            projectAddress = temp.nextLine();
            if (buildingType.isBlank() || projectAddress.isBlank())
                System.out.println("One or more of the fields above were left blank. Please try again.");
            else
                break;
        }
        // initialise string variable for customer name
        String customer_name = null;
        // executeQuery: runs a SELECT statement and returns the results.
        resultSet = statement.executeQuery("SELECT name FROM Customers WHERE id = " +
                "(SELECT id FROM Projects WHERE number = " + proj_num + ")");
        // Loop over the results and store the customer's name
        while (resultSet.next())
            customer_name = resultSet.getString("name");
        // if project name is blank set the project name to the building type and surname of customer
        if (projectName.isBlank())
            projectName = buildingType + " " + customer_name.split(" ")[1];

        System.out.print("Enter the deadline for the project in the format YYYY-MM-DD: ");
        // string variable for project deadline
        String strDeadLine;
        // while loop to ensure date is entered in the required format
        while (true) {
            try {
                strDeadLine = temp.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date deadline = sdf.parse(strDeadLine);
                break;

            } catch (Exception e) {
                System.out.print("Invalid input - enter the deadline for the project in the format YYYY-MM-DD: ");
            }
        }
        // insert new project into table
        statement.executeUpdate("INSERT INTO Projects VALUES(" + proj_num + ", '" + projectName
                + "', '" + buildingType + "', '" + projectAddress + "', " + erfNumber + ", '"
                + strDeadLine + "', 'false', 'incomplete', " + architect_id + ", " + contractor_id + ", " + customer_id
                + ", " + engineer_id + ", " + manager_id + ")");
        // output message
        System.out.println("Project successfully stored");
    }

    /**
     * This is a method that is used to update details about a project (including details about some Persons)
     * based on user input
     *
     * @param proj_num Project number that is used to identify the project being updated
     * @throws SQLException throws a SQLException error
     */
    public static void update(int proj_num) throws SQLException {
        // temporary scanner object
        Scanner temp = new Scanner(System.in);
        // while loop while user does not exit menu
        while (true) {
            // output menu with operations
            System.out.println("""
                    Enter 1 to change the due date of the project
                    Enter 2 to change the total amount of the fee paid to date
                    Enter 3 to update a contractor's contact details
                    Enter 0 to exit out of change/update section""");
            //
            int operation;
            // loop until an integer is entered
            while (true) {
                try {
                    // if int then break out of loop
                    operation = temp.nextInt();
                    break;
                }
                // catch and output error message
                catch (Exception e) {
                    temp.nextLine();
                    System.out.print("Invalid input  - enter an integer command corresponding " +
                            "to the operation to perform: ");
                }
            }
            // skip empty line entered by user
            temp.nextLine();
            // if type is 1 output instruction, get new due date and set new deadline for project
            if (operation == 1) {
                System.out.print("Enter a new due date for the project in the format YYYY-MM-DD: ");
                String strDeadline;
                // loop until valid date format is entered
                while (true) {
                    try {
                        strDeadline = temp.nextLine();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date deadline = sdf.parse(strDeadline);
                        break;

                    } catch (Exception e) {
                        System.out.print("Invalid input - " +
                                "enter the deadline for the project in the format YYYY-MM-DD: ");
                    }
                }
                // update the deadline of the project
                statement.executeUpdate("UPDATE Projects SET deadline = " + "'" +
                        strDeadline + "' WHERE number = " + proj_num);

            }

            // if type is 2 output instruction, get new amount and set new total amount for project
            else if (operation == 2) {
                System.out.print("Enter the total amount of the fee paid to date: ");
                double newAmount;
                // loop until a double is entered
                while (true) {
                    try {
                        newAmount = temp.nextDouble();
                        break;
                    } catch (Exception e) {
                        temp.nextLine();
                        System.out.print("Invalid input - enter a numerical value: ");
                    }
                }

                // update amount paid by the customer
                statement.executeUpdate("UPDATE Customers SET total_paid = " + newAmount +
                        " WHERE id = (SELECT id FROM Projects WHERE number = " + proj_num + ")");

            }

            // if type is 3
            else if (operation == 3) {
                // output instruction
                System.out.println("""
                        Enter 1 to update a contractor's email address
                        Enter 2 to update a contractor's telephone number
                        """);
                int update;
                // loop until integer is entered
                while (true) {
                    try {
                        update = temp.nextInt();
                        break;
                    } catch (Exception e) {
                        // skip next line entered by user
                        temp.nextLine();
                        System.out.println("Invalid input - enter a numerical value");
                    }
                }
                // if choice is 1, output message, get new email and set new email for contractor
                if (update == 1) {
                    System.out.print("Enter an updated contractor's email address: ");
                    String newEmail = temp.nextLine();
                    // loop until new email entered is not blank
                    while (newEmail.isBlank())
                        newEmail = temp.nextLine();
                    // update email address of contractor in charge of project
                    statement.executeUpdate("UPDATE Contractors SET email = " + "'" +
                            newEmail + "' WHERE id = (SELECT id FROM Projects WHERE number = " + proj_num + ")");
                }

                // if choice is 2, output message, get new number and set new number for contractor
                else if (update == 2) {
                    System.out.print("Enter an updated contractor's telephone number: ");
                    String newNumber = temp.nextLine();
                    // loop until new number entered is not blank
                    while (newNumber.isBlank())
                        newNumber = temp.nextLine();
                    // update number of the contractor in charge of the project
                    statement.executeUpdate("UPDATE Contractors SET number = " + "'" +
                            newNumber + "' WHERE id = (SELECT id FROM Projects WHERE number = " + proj_num + ")");
                }
                // else output error message
                else
                    System.out.println("Invalid input");
            }
            // break out of loop
            else if (operation == 0)
                break;
            // else output error message
            else
                System.out.println("Invalid choice");
        }
    }

    /**
     * This is a method that is used to finalize a project in the Projects table
     *
     * @param proj_num Project number that is used to identify the project being finalised
     * @throws SQLException throws a SQLException error
     */
    public static void finalize(int proj_num) throws SQLException {
        // initialise finalised status of the project
        Boolean status = false;
        // executeQuery: runs a SELECT statement and returns the results.
        resultSet = statement.executeQuery("SELECT finalised FROM Projects WHERE number = " + proj_num);
        // update status by looping over the results and setting the status to the string boolean in the table
        while (resultSet.next())
            status = Boolean.parseBoolean(resultSet.getString("finalised"));
        // if the project has not been finalized yet
        if (!status) {
            // finalize the project
            statement.executeUpdate("UPDATE Projects SET finalised = 'true' WHERE number = " + proj_num);
            // get current date and update the completion date
            LocalDate localDate = LocalDate.now();
            statement.executeUpdate("UPDATE Projects SET completed = '"
                    + localDate + "' WHERE number = " + proj_num);
            // Initiate variables for total due and total paid
            double totalDue = 0;
            double totalPaid = 0;
            // executeQuery: runs a SELECT statement and returns the results.
            resultSet = statement.executeQuery("SELECT total_due, total_paid FROM Customers WHERE id = " +
                    "(SELECT customer_id FROM Projects WHERE number = " + proj_num + ")");
            // Loop over the results and update the total_due
            while (resultSet.next()) {
                totalDue = resultSet.getDouble("total_due");
                totalPaid = resultSet.getDouble("total_paid");
            }
            // check if project is paid for in full.
            // If paid in full output message else output invoice
            if (totalPaid >= totalDue) {
                System.out.println("Paid in full");
            }
            else {
                // Initiate variables for customer name, email and number
                String customerName = null;
                String customerEmail = null;
                String customerNumber = null;
                // executeQuery: runs a SELECT statement and returns the results.
                resultSet = statement.executeQuery("SELECT name, email, number FROM Customers WHERE id = " +
                        "(SELECT customer_id FROM Projects WHERE number = " + proj_num + ")");
                // Loop over the results and update the variables
                while (resultSet.next()) {
                    customerName = resultSet.getString("name");
                    customerEmail = resultSet.getString("email");
                    customerNumber = resultSet.getString("number");
                }
                // print invoice for customer
                System.out.println("\nInvoice for " + customerName +
                        "\n\tEmail: " + customerEmail +
                        "\n\tTelephone number: " + customerNumber +
                        "\n\tAmount due: " + (totalDue - totalPaid) + "\n");
            }
        }
        // output message if project already finalized
        else
            System.out.println("Project has already been finalized");
    }


    /**
     * This is the main method that is used to run the program
     *
     * @param args the command line arguments
     * @throws SQLException throws a SQLException error
     */
    public static void main(String[] args) throws SQLException {
        // scanner object
        Scanner scanner = new Scanner(System.in);
        // try connect to database
        try {
            // Connect to the PoisePMS database, via the jdbc:mysql: channel on localhost (this PC)
            // Use username "otheruser", password "swordfish".
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "otheruser",
                    "swordfish"
            );
            // Create a direct line to the database for running our queries
            statement = connection.createStatement();
            System.out.println("Lets build");
            // initialise variable for menu choice
            int choice;
            // loop while user does not quit
            while (true) {
                // output menu
                System.out.print("""
                                            
                        Enter 1 to add a new project
                        Enter 2 to select and update/finalize an existing project
                        Enter 3 to see a list of projects that still need to be completed
                        Enter 4 to see a list of projects that are overdue
                        Enter 0 to exit
                                            
                        """);
                // try read integer
                try {
                    // read int
                    choice = scanner.nextInt();
                    // if choice is 1 call method to create a new project
                    if (choice == 1) {
                        newProject();
                    }
                    // if choice is 2
                    else if (choice == 2) {
                        // temporary scanner object
                        Scanner temp = new Scanner(System.in);
                        System.out.print("Enter the project name or number: ");
                        // string variable for proj number/name
                        String proj = temp.nextLine();
                        // boolean for proj being an integer
                        boolean isNum;
                        // try parse proj to an integer and set isNum to true
                        try {
                            Integer.parseInt(proj);
                            isNum = true;
                        }
                        // catch if fails and set isNum to false
                        catch (Exception e) {
                            isNum = false;
                        }
                        // initiate project number
                        int proj_num = 0;
                        // if input is a number select projects with matching number
                        if (isNum)
                            resultSet = statement.executeQuery("SELECT * FROM Projects WHERE number = "
                                    + Integer.parseInt(proj));

                        // else select projects with matching name
                        else
                            resultSet = statement.executeQuery("SELECT * FROM Projects WHERE LOWER(name) = '"
                                    + proj.toLowerCase() + "'");
                        // loop and store project number from results
                        while (resultSet.next()) {
                            proj_num = resultSet.getInt("number");
                            }
                        // if project is found
                        if (proj_num != 0) {
                            // initiate project status
                            String status = null;
                            // select finalised status
                            resultSet = statement.executeQuery("SELECT finalised FROM Projects WHERE number = "
                                    + proj_num);
                            // set status while looping through results
                            while (resultSet.next()) {
                                status = resultSet.getString("finalised");
                            }
                            // Output instruction and get choice to change/update details
                            System.out.print("Enter 'x' to change/update details or any other key to skip: ");
                            String choice1 = temp.nextLine();
                            // if user chooses to change/update details
                            if (choice1.equalsIgnoreCase("x")) {
                                // check if project has been finalized and output message if it has
                                if (status.equalsIgnoreCase("true"))
                                    System.out.println("Project has already been finalized");
                                // else call the update method and pass the project as a parameter
                                else
                                    update(proj_num);
                            }
                            // output message and get input to finalize project or not
                            System.out.print("Enter 'f' to finalize the project or enter any other key to skip: ");
                            String finalizeChoice = temp.nextLine();
                            // if user chooses to finalize project
                            // finalize the project by calling finalizeProject and pass through the project as a parameter
                            if (finalizeChoice.equalsIgnoreCase("f")) {
                                if (status.equalsIgnoreCase("true"))
                                    System.out.println("Project has already been finalized");
                                    // else call the update method and pass the project as a parameter
                                else
                                    finalize(proj_num);
                            }
                        }
                        // output message if project not found
                        else
                            System.out.println("Unable to find project");
                    }
                    // if choice is 3
                    else if (choice == 3) {
                        System.out.println("Incomplete projects:");
                        // select number and name from incomplete projects
                        resultSet = statement.executeQuery("SELECT number, name FROM Projects " +
                                "WHERE finalised = 'false'");
                        // loop through result and output project details
                        while (resultSet.next()) {
                            System.out.println("\t-Project " + resultSet.getInt("number") + ": "
                                    + resultSet.getString("name")
                            );
                        }
                    }
                    // if choice is 4
                    else if (choice == 4) {
                        System.out.println("Overdue projects:");
                        // date variables
                        Date dueDate;
                        Date presentDate = new SimpleDateFormat("yyyy-MM-dd").
                                parse(String.valueOf(LocalDate.now()));
                        // select number, name and deadline from projects
                        resultSet = statement.executeQuery("SELECT number, name, deadline FROM Projects");
                        // loop over results and store due date
                        while (resultSet.next()) {
                            dueDate = new SimpleDateFormat("yyyy-MM-dd").
                                    parse(resultSet.getString("deadline"));
                            // if project is past due date output project details
                            if (dueDate.before(presentDate))
                                System.out.println("\t-Project " + resultSet.getInt("number") + ": "
                                                + resultSet.getString("name"));
                        }
                    }
                    // if choice is 0
                    else if (choice == 0) {
                        // output message and break out of loop
                        System.out.println("Good bye\n");
                        break;
                    }
                    // output message if invalid input
                    else {
                        System.out.println("Invalid input\n");
                    }
                }
                // catch if integer is not entered and output error message
                catch (Exception e) {
                    scanner.nextLine();
                    System.out.println("Invalid input\n");
                }
            }
            // close scanner and connections
            scanner.close();
            statement.close();
            resultSet.close();
            connection.close();
        }
        catch (SQLException e) {
            // catch a SQLException
            e.printStackTrace();
        }
    }
}