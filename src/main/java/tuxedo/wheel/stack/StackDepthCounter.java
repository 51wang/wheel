package tuxedo.wheel.stack;

public class StackDepthCounter {
    private final ThreadLocal<StackDepth> holder = ThreadLocal.withInitial(StackDepth::new);

    public void enter() {
        holder.get().depth++;
    }

    public boolean quit() {
        return (--holder.get().depth) == 0;
    }

    public void clear() {
        holder.get().depth = 0;
    }

    private static class StackDepth {
        int depth = 0;
    }
}
