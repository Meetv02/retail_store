import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ListIterator;
import java.util.Scanner;

class RegisteredUsers extends User {

  private LocalDate issueDate;
  private LocalDate returnDate;

  public RegisteredUsers(
      String uName,
      String uPwd,
      String fullName,
      boolean isMember) {
    super(uName, uPwd, fullName, isMember);
  }

  public LocalDate getIssueDate() {
    return issueDate;
  }

  public LocalDate getReturnDate() {
    return returnDate;
  }

  public void setIssueDate(LocalDate issueDate) {
    System.out.println("Enter the today's date : ");

    this.issueDate = issueDate;
  }

  public void setReturnDate(LocalDate returnDate) {
    this.returnDate = returnDate;
  }

  public void ShowProfile() {
    String ismemb;
    if (getMember()) ismemb = "Yes"; else ismemb = "No";

    //System.out.println(getFullName() + "         " + getuName() + "         " + ismemb);
    System.out.println("---------------------------------- User Profile -----------------------------------");
    System.out.println("Full Name : " + getFullName());
    System.out.println("User Name : " + getuName());
    System.out.println("Member    : " + ismemb);
    System.out.println();


    ListIterator<Product> iterate = boughtProducts.listIterator();
    System.out.println("---------------------------------- Products Bought --------------------------------");
    System.out.println();
    System.out.printf("%-30s %-30s %-30s%n", "Product Name", "Product Id", "Price");
    System.out.println("-----------------------------------------------------------------------------------");
    while (iterate.hasNext()) {
      Product products = iterate.next();
      System.out.printf("%-30s %-30d %-30d%n",products.getpName(),products.getpId(),products.getBasePrice());
      System.out.println();
    }
    System.out.println();
  }

  public void PurchaseProduct(Product p) throws Exception {

    issueDate = LocalDate.now();

    boughtProducts.add(p);
    temp.add(p);
  }

  public Product ReturnProduct(int id) throws Exception {

    returnDate = LocalDate.now();

    ListIterator<Product> iterate = boughtProducts.listIterator();
    while (iterate.hasNext()) {
      Product p = iterate.next();
      if (p.getpId() == id) {
        boughtProducts.remove(p);
        return p;
      }
    }
    return null;
  }
}
