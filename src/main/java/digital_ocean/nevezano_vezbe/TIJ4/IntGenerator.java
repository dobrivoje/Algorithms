package digital_ocean.nevezano_vezbe.TIJ4;

public abstract class IntGenerator {

	private volatile boolean canceled = false;

	public abstract int next();

	public void cancel() {
		canceled = true;
		Thread.currentThread().interrupt();
	}

	public boolean isCanceled() {
		return canceled;
	}
}
