package tuxedo.wheel.utility.base;

public class If {
    private final boolean matched;

    private If(boolean matched) {
        this.matched = matched;
    }

    public static If isTrue(boolean condition) {
        return new If(condition);
    }

    public static If isFalse(boolean condition) {
        return new If(!condition);
    }

    public void andThen(Runnable runnable) {
        if (matched) {
            runnable.run();
        }
    }

    public void orElse(Runnable runnable) {
        if (!matched) {
            runnable.run();
        }
    }
}
