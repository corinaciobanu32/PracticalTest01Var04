package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Context;
import android.content.Intent;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread{
    private Context context;

    String nume;
    String grupa;
    boolean isRunning = true;

    public ProcessingThread(Context context, String nume, String grupa) {
        this.context = context;

       this.nume = nume;
       this.grupa = grupa;
    }

    @Override
    public void run() {
        while (isRunning) {
            sendMessage(Constants.NUME);
            sleep();
            sendMessage(Constants.GRUPA);
            sleep();
        }
    }

    private void sendMessage(int type) {
        Intent intent = new Intent();
        switch (type) {
            case Constants.NUME:
                intent.setAction(Constants.NUME_ACTION);
                intent.putExtra(Constants.DATA, this.nume);
                break;
            case Constants.GRUPA:
                intent.setAction(Constants.GRUPA_ACTION);
                intent.putExtra(Constants.DATA, this.grupa);
                break;
        }

        context.sendBroadcast(intent);
    }

    private void sleep(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
