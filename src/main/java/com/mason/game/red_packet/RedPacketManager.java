package com.mason.game.red_packet;

/**
 * Created by mwu on 2020/1/2
 */
public class RedPacketManager {

  public static void main(String[] args) {
    RedPacket redPacket = new RedPacket(15, 100);

    for (int i = 1; i <= 20; i++) {
      new Thread("Thread-" + i) {
        @Override
        public void run() {
          redPacket.snatch();
        }
      }.start();
    }
  }
}
