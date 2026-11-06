public class Tac extends Thread {

  public void tac() {
    try {
      Thread.sleep(250);
      while (true) {
        System.out.print("\r\u001B[32;1mTAC \u001B[0m");
        Thread.sleep(500);
      }
    } catch (InterruptedException e) {
      System.out.println("\u001B[31;1mError en la entrada:" + e.getMessage());
    }
  }

  @Override
  public void run() {
    this.tac();
  }

}
