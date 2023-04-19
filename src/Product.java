import java.io.Serializable;

public class Product implements Serializable {

  private int pId;
  private String pName;
  private int pQty;
  private int basePrice;
  private int sellPrice;
  private int discoutPrice;
  private int limit;

  public Product(
    int pId,
    String pName,
    int pQty,
    int basePrice,
    int sellPrice,
    int discoutPrice,
    int limit
  ) {
    this.pId = pId;
    this.pName = pName;
    this.pQty = pQty;
    this.basePrice = basePrice;
    this.sellPrice = sellPrice;
    this.discoutPrice = discoutPrice;
    this.limit = limit;
  }

  public int getpId() {
    return pId;
  }

  public String getpName() {
    return pName;
  }

  public int getpQty() {
    return pQty;
  }


  public void increaseQtyby(int x) {
    pQty = pQty + 1;
    return;
  }

  public void decreaseQtyby(int x) {
    pQty = pQty - 1;
  }

  public int getBasePrice() {
    return basePrice;
  }

  public int getSellPrice() {
    return sellPrice;
  }

  public int getDiscoutPrice() {
    return discoutPrice;
  }

  public int getLimit() {
    return limit;
  }
}
