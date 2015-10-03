package be.cegeka.java_8_workshop.ch1.ex07;

public class ChainedRunnable implements Runnable {

    private Runnable wrapped;
    private ChainedRunnable chainedRunnable;

    public ChainedRunnable(Runnable wrapped) {
        this.wrapped = wrapped;
    }

    private ChainedRunnable(ChainedRunnable before, Runnable wrapped) {
        this.chainedRunnable = before;
        this.wrapped = wrapped;
    }

    public ChainedRunnable andThen(Runnable chained) {
        return new ChainedRunnable(this, chained);
    }

    @Override
    public void run() {
        if (chainedRunnable != null) {
            chainedRunnable.run();
        }
        wrapped.run();
    }

}
