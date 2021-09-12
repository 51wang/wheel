package tuxedo.wheel.toolkit.jvmargs.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JvmArgComparision {
    private Result result;
    private String raw1;
    private String raw2;

    public JvmArgComparision(JvmArg arg1, JvmArg arg2) {
        this.raw1 = (arg1 == null) ? null : arg1.getRaw();
        this.raw2 = (arg2 == null) ? null : arg2.getRaw();
        if (arg1 == null || arg2 == null) {
            this.result = Result.UNIQUE;
        } else {
            String value1 = arg1.getValue(), value2 = arg2.getValue();
            this.result = (value1 == value2 || (value1 != null && value1.equals(value2))) ? Result.SAME : Result.DIFF;
        }
    }

    public static enum Result {
        SAME, DIFF, UNIQUE
    }
}
