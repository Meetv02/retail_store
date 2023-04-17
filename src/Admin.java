import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

interface adminInterface {
  void addProduct(ArrayList<Product> productArrayList) throws IOException;

  int totalProduct(ArrayList<Product> productArrayList);

  int maxProfit(ArrayList<Product> productArrayList);

  void fineCalculate(ArrayList<RegisteredUsers> regUsers);
}

class Admin implements adminInterface {

  private String aName;
  private String aPwd;

  Admin(String aName, String aPwd) {
    this.aName = aName;
    this.aPwd = aPwd;
  }

  static int idcnt = 1;

  public void addProduct(ArrayList<Product> productArrayList)
      throws IOException {
    Scanner sc = new Scanner(System.in);
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    System.out.println("Welcome, admin!");
    System.out.println("Please enter the product information:");

    System.out.print("Product Name: ");
    String productName = br.readLine();

    System.out.print("Total quantity: ");
    int qty = sc.nextInt();

    System.out.print("Base price: ");
    int baseprice = sc.nextInt();

    System.out.print("Sell price: ");
    int sellprice = sc.nextInt();

    System.out.print("Discount price: ");
    int discountprice = sc.nextInt();

    System.out.println("Limit for return product(in Days): ");
    int lmt = sc.nextInt();

    Product newProduct = new Product(
        idcnt++,
        productName,
        qty,
        baseprice,
        sellprice,
        discountprice,
        lmt);
    System.out.println(newProduct.getpId() + " " + newProduct.getBasePrice()+" " + newProduct.getDiscoutPrice()+" " + newProduct.getpName()+" " + newProduct.getpQty()+" " + newProduct.getSellPrice() +" " + newProduct.getLimit());
    productArrayList.add(newProduct);
  }

  public int totalProduct(ArrayList<Product> productArrayList) {
    int totalProd = 0;
    System.out.println("Total Product count in retail store:");
    for (Product p : productArrayList) {
      totalProd += p.getpQty();
    }
    return totalProd;
  }

  public int maxProfit(ArrayList<Product> productArrayList) {
    int maxProf = 0;
    System.out.println("Maximum profit can make is :");
    for (Product p : productArrayList) {
      maxProf += (p.getSellPrice() - p.getBasePrice()) * p.getpQty();
    }
    return maxProf;
  }

  public void fineCalculate(ArrayList<RegisteredUsers> regUsers) {

    ListIterator<RegisteredUsers> iterate = regUsers.listIterator();
    while (iterate.hasNext()) {
      int fine = 0;
      RegisteredUsers regusr = iterate.next();
      ListIterator<Product> pr = regusr.temp.listIterator();
      while (pr.hasNext()) {
        Product p = pr.next();
        int maxReturnLimit = p.getLimit();
        int finePerDay = 50;
        LocalDate issueDt = regusr.getIssueDate();
        LocalDate returnDt = regusr.getReturnDate();
        // int lateDays = (returnDt.getDay() - issueDt.getDay()) - maxReturnLimit;
        ChronoUnit ChronoUnit = null;
        long lateDays = ChronoUnit.DAYS.between(returnDt, issueDt);
        if (!(lateDays <= 0)) {
          fine += lateDays * finePerDay;
        }
      }
      System.out.println(regusr.getuName() + "  Total fine: " + fine);
    }
  }

  void DisplayRegUsers(ArrayList<RegisteredUsers> regUsers) {
    // TODO :
    System.out.println(
        "------------------------------------- User Profile --------------------------------");
    System.out.println("Full Name  User Name   Password  isMember? ");
    System.out.println(
        "-----------------------------------------------------------------------------------");
    for (RegisteredUsers u : regUsers) {
      u.ShowProfile();
      System.out.println("");
    }
    System.out.println(
        "-----------------------------------------------------------------------------------");
  }
}
