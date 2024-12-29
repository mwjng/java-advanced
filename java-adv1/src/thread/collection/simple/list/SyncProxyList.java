package thread.collection.simple.list;

public class SyncProxyList implements SimpleList {

    private SimpleList target;

    public SyncProxyList(final SimpleList target) {
        this.target = target;
    }

    @Override
    public synchronized int size() {
        return target.size();
    }

    @Override
    public synchronized void add(final Object e) {
        target.add(e);
    }

    @Override
    public synchronized Object get(final int index) {
        return target.get(index);
    }

    @Override
    public String toString() {
        return target.toString() + " by " + this.getClass().getSimpleName();
    }
}
