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
    boolean isMember
  ) {
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

    System.out.println(
      getFullName() +
      "         " +
      getuName() +
      "         " +
      getuPwd() +
      "           " +
      ismemb
    );
    System.out.print("Products Bought : ");
    ListIterator<Product> iterate = boughtProducts.listIterator();
    while (iterate.hasNext()) {
      System.out.print((iterate.next()).getpName() + "  ");
    }
    System.out.println();
    System.out.print(
      "-----------------------------------------------------------------------------------"
    );
  }

  public void PurchaseProduct(Product p) throws Exception {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter the date (format: yyyy-MM-dd):");
    String dateStr = scanner.next();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.parse(dateStr, formatter);
    issueDate = date;

    boughtProducts.add(p);
    temp.add(p);
  }

  public Product ReturnProduct(int id) throws Exception {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter the date (format: yyyy-MM-dd):");
    String dateStr = scanner.next();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.parse(dateStr, formatter);
    returnDate = date;

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
