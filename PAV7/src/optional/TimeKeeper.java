package optional;

/**
 * Acesta este timerul implementat de mine
 * sleep 1 secunda numara secunda si repeta pana la timpul limita sau pana cand jocul este gata(semnalizat de threadul principal)
 * cateva variabile volatile pentru threadul principal
 */
public class TimeKeeper implements Runnable{
    private volatile boolean stop = false;
    private int seconds = 0;
    private int maxSeconds;
    private boolean finished = false;

    public boolean isFinished() {
        return finished;
    }

    public TimeKeeper(int seconds) {
        this.maxSeconds = seconds;
    }

    public void setStop() {
        this.stop = true;
    }

    public int getSeconds() {
        return seconds;
    }

    /**
     * la fiecare secunda numara o secunda in counter si se opreste la limita asignata sau cand jocul este gata
     * la final marcheaza ca s-a terminat procesul
     */
    @Override
    public void run() {
        while(maxSeconds>seconds && !stop){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            seconds++;
        }
        finished = true;
    }
}
