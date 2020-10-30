import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


class Delete implements Runnable {
    private boolean exit=false;
    private final String FileName;
    int time;
    Timer timer;


    Delete(String FileName, int time) {
        this.FileName = FileName;
        this.time = time;

    }

    public void stop() {
        exit = true;
    }


    public void run() {
        while (!exit) {

            timer = new Timer();
            timer.schedule(new Main(), 0, time * 1000);
        }
        System.out.println("Time is up!");
    }


    class Main extends TimerTask {


        public void run() {

            try {
                File myFile = new File(FileName);
                if (myFile.delete())
                    System.out.println(myFile.getName() + " is deleted!");
                else
                    System.out.println("Delete Operation is failed!!");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}







