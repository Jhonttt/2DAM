public class Tic extends Thread {

  public void tic() {
    try {
      while (true) {
        System.out.print("\r\u001B[33;1mTIC \u001B[0m");
        Thread.sleep(500);
      }
    } catch (InterruptedException e) {
      System.out.println("\u001B[31;1mError en la entrada:" + e.getMessage());
    }
  }

  @Override
  public void run() {
    this.tic();
  }

}
