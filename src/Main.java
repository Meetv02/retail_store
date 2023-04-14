import java.io.*;
import java.io.Console;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

  //  Convert char array to String
  public static String toString(char[] a) {
    String string = new String(a);

    return string;
  }

  // Read password
  private static String readPassword() {
    Console console;
    if ((console = System.console()) != null) {
      char[] password = console.readPassword();
      for (int i = 0; i < password.length; i++) {
        System.out.print("*");
      }
      System.out.println();
      return toString(password);
    }
    return null;
  }

  //   User login
  public static RegisteredUsers Login(
    String curruname,
    String currupwd,
    ArrayList<RegisteredUsers> registeredUsers
  ) {
    ListIterator<RegisteredUsers> iterate = registeredUsers.listIterator();
    while (iterate.hasNext()) {
      RegisteredUsers u = iterate.next();
      if (u.getuName().equals(curruname) && u.getuPwd().equals(currupwd)) {
        System.out.println("Successfully Logged in.........");
        System.out.println();
        return u;
      }
    }
    return null;
  }

  // Forgot password
  public static boolean forgotPassword(
    String findUser,
    ArrayList<RegisteredUsers> registeredUsers
  ) throws IOException {
    // Searches for the username
    int found = 0;
    ListIterator<RegisteredUsers> iterate = registeredUsers.listIterator();
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);
    while (iterate.hasNext()) {
      RegisteredUsers u = iterate.next();
      if (u.getuName().equals(findUser)) {
        System.out.println("Enter new password : ");
        Scanner scan = new Scanner(System.in);
        String pwd = br.readLine();
        u.setPassword(pwd);
        return true;
      }
    }
    return false;
  }

  public static void displayProductCatalog(
    ArrayList<Product> productArrayList
  ) {
    System.out.println(
      "--------------------------------- Product Catalog ---------------------------------"
    );
    System.out.println(
      "Product ID \t Product Name \t Product Qty \t Availability \tSell price \t Discount price \t Return limit"
    );
    String avail;
    for (Product p : productArrayList) {
      if (p.getpQty() > 0) avail = "In Stock"; else avail = "Out of Stock";
      System.out.println(
        "   " +
        p.getpId() +
        "\t\t" +
        p.getpName() +
        "\t\t" +
        p.getpQty() +
        "\t    " + avail + " \t      " +
        p.getSellPrice() +
        "\t\t" +
        p.getDiscoutPrice() +
        "\t\t" +
        p.getLimit()
      );
    }
    System.out.println(
      "-----------------------------------------------------------------------------------"
    );
  }

  public static void AdmindisplayProductCatalog(
    ArrayList<Product> productArrayList
  ) {
    System.out.println(
      "--------------------------------- Product Catalog ---------------------------------"
    );
    System.out.println(
      "Product ID\tProduct Name \t Product Qty \t Availability \t Base price \t Sell price \t Discount price \t Return limit"
    );

    String avail;
    for (Product p : productArrayList) {
      if (p.getpQty() > 0) avail = "In Stock"; else avail = "Out of Stock";
      System.out.println(
        "   " +
        p.getpId() +
        "\t\t     " +
        p.getpName() +
        "\t\t " +
        p.getpQty() +
        "\t    " + avail + " \t      " +
        p.getBasePrice() +
        " \t\t  " +
        p.getSellPrice() +
        "\t\t  " +
        p.getDiscoutPrice() + "\t\t\t" + p.getLimit()
      );
    }
    System.out.println(
      "-----------------------------------------------------------------------------------"
    );
  }

  public static void AdminLogin(
    Admin admin,
    ArrayList<RegisteredUsers> registeredUsers,
    ArrayList<Product> productArrayList
  ) {
    Scanner scan = new Scanner(System.in);
    while (admin != null) {
      System.out.println(
        "------------------------------------ Admin Panel ----------------------------------"
      );
      System.out.println("1--->Registered Users");
      System.out.println("2--->Add Product");
      System.out.println("3--->Display Products");
      System.out.println("4--->Total Products");
      System.out.println("5--->Maximum Profit");
      System.out.println("6--->Calculate Fine");
      System.out.println("7--->LogOut");
      System.out.println(
        "-----------------------------------------------------------------------------------"
      );

      try {
        System.out.println("Enter your choice : ");
        int ch = scan.nextInt();

        switch (ch) {
          case 1:
            admin.DisplayRegUsers(registeredUsers);
            break;
          case 2:
            admin.addProduct(productArrayList);
            break;
          case 3:
            AdmindisplayProductCatalog(productArrayList);
            break;
          case 4:
            int total = admin.totalProduct(productArrayList);
            System.out.println("Total Availble Products : " + total);
            break;
          case 5:
            int mProfit = admin.maxProfit(productArrayList);
            System.out.println("Maximum profit :  " + mProfit);
            break;
          case 6:
          if(registeredUsers.size()==0){
            System.out.println("No users registered!!");
          }else{
            admin.fineCalculate(registeredUsers);
          }
              break;
          case 7:
            admin = null;
            System.out.println("Logged out successfully!!");
            break;
          default:
            System.out.println("Invalid choice!!");
        }
      } catch (Exception e) {
        System.out.println("Excepetion caught--->" + e);
      }
    }
  }

  private static boolean isUsernameTaken(
    ArrayList<RegisteredUsers> registeredUsers,
    String username
  ) {
    for (RegisteredUsers regusr : registeredUsers) {
      if (regusr.getuName().equals(username)) {
        return true;
      }
    }
    return false;
  }

  public static Product findProduct(int proId, ArrayList<Product> productArrayList) {
    ListIterator<Product> iterate = productArrayList.listIterator();
    while (iterate.hasNext()) {
      Product buyprod = iterate.next();
      if (buyprod.getpId() == proId) {
        return buyprod;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    Console console;
    Scanner scan = new Scanner(System.in);
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);
    System.out.println();

    // Store Books data
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    ArrayList<RegisteredUsers> regUsers = new ArrayList<RegisteredUsers>();

    int ch = 0;

    while (true) {
      try {
        System.out.println("1--->User Login");
        System.out.println("2--->Admin Login");
        System.out.println("3--->Register");
        System.out.println("4--->Exit");
        System.out.println("Enter your choice : ");
        ch = scan.nextInt();
        RegisteredUsers currUser = null;
        switch (ch) {
          case 1:
            System.out.println(
              "------------------------------------- Log In --------------------------------------"
            );
            System.out.println("Enter Username : ");
            String curruname = br.readLine();
            System.out.println("Enter Password : ");
            String currupwd = readPassword();

            //Log in and get the current user's object
            currUser = Login(curruname, currupwd, regUsers);

            if (currUser == null) {
              System.out.println(
                "-----------------------------------------------------------------------------------"
              );
              System.out.println("1-->Forgot Password");
              System.out.println("2-->Back to Main Menu");
              System.out.println(
                "-----------------------------------------------------------------------------------"
              );

              ch = scan.nextInt();

              // Retry , i.e start from the beginning
              if (ch == 2) continue;

              // Forgot password
              System.out.println("Enter the username : ");
              String findUser = br.readLine();
              boolean status = forgotPassword(findUser, regUsers);

              if (status == true) System.out.println(
                "Password updated successfully"
              ); else System.out.println(
                "Password not updated or Username not found"
              );
            }

            break;
          case 2:
            System.out.println("Username : ");
            curruname = br.readLine();
            System.out.println("Password : ");
            currupwd = readPassword();

            // Admin LogIn
            if (curruname.equals("admin") && currupwd.equals("admin")) {
              Admin admin = new Admin(curruname, currupwd);
              System.out.println("Admin Login successful!!");

              AdminLogin(admin, regUsers, productArrayList);
            } else {
              System.out.println("Admin Login Failed");
            }
            break;
          case 3:
            System.out.println(
              "------------------------------------ Registration ---------------------------------"
            );

            System.out.println("Enter Full name : ");
            String regufullname = br.readLine();
            System.out.println("Enter Username : ");
            String reguuname = br.readLine();

            if (isUsernameTaken(regUsers, regufullname)) {
              System.out.println(
                "Username is already taken, please try again with a different username"
              );
            } else {
              System.out.println("Enter Password : ");
              String regupwd = br.readLine();

              System.out.println("Want to be a member(1-->yes/0-->No) : ");
              int ismem = scan.nextInt();
              boolean member;
              if (ismem == 1) member = true; else member = false;

              RegisteredUsers newuser = new RegisteredUsers(
                reguuname,
                regupwd,
                regufullname,
                member
              );
              regUsers.add(newuser);
              System.out.println("User Successfully registered.........");
            }

            break;
          case 4:
            System.exit(0);
            break;
          default:
            System.out.println("Invalid Choice!!");
            break;
        }

        // Perform the operations untill the user is logged in
        while (currUser != null) {
          // Display the available books in the store
          displayProductCatalog(productArrayList);

          System.out.println(
            "-----------------------------------------------------------------------------------"
          );
          System.out.println("1-->Profile");
          System.out.println("2-->Issue/Purchase Product");
          System.out.println("3-->Return/Cancel Product");
          System.out.println("4-->Logout");
          System.out.println(
            "-----------------------------------------------------------------------------------"
          );
          System.out.println("Enter your choice : ");
          try {
            int logichoice = scan.nextInt();
            System.out.println();
            switch (logichoice) {
              // Show profile
              case 1:
                currUser.ShowProfile();
                System.out.println(
                  "-----------------------------------------------------------------------------------"
                );
                System.out.println("1-->Back");
                System.out.println("2-->Logout");
                System.out.println(
                  "-----------------------------------------------------------------------------------"
                );

                System.out.println("Enter your choice : ");
                int profilechoice = scan.nextInt();

                // Logout
                if (profilechoice == 2) {
                  currUser = null;
                  System.out.println("Successfully Logged Out!!");
                }

                break;
              // Issue/Purchase Product
              case 2:
                System.out.println(
                  "Enter the product id which you want to purchase"
                );
                int proId = scan.nextInt();

                // Find the book and purchase the book
                Product foundproduct = findProduct(proId, productArrayList);

                if (foundproduct == null) {
                  System.out.println("please enter valid book id...");
                } else {
                  if (foundproduct.getpQty() > 0) {
                    currUser.PurchaseProduct(foundproduct);
                    System.out.println("Product purchaased successfully!!");
                    foundproduct.decreaseQtyby(1);
                  } else {
                    System.out.println("Out of Stock!!");
                  }
                }
                break;
              // Return/Cancel Product
              case 3:
                System.out.println(
                  "Enter the product id which you want to return "
                );
                proId = scan.nextInt();

                foundproduct = currUser.ReturnProduct(proId);
                if (foundproduct != null) {
                  foundproduct.increaseQtyby(1);
                  System.out.println("Product returned successfully!!");
                } else {
                  System.out.println("Product returned failed!!");
                }
                break;
              //Log out
              case 4:
                currUser = null;
                System.out.println("Successfully Logged Out!!");
                break;
              default:
                break;
            }
          } catch (Exception e) {
            System.out.println("Error Caught--->" + e);
            scan.nextLine();
          }
        }
      } catch (Exception e) {
        System.out.println("Exception caught-->"+e);
      }
    }
  }
}
