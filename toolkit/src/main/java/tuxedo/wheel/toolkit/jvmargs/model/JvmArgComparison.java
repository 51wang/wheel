package tuxedo.wheel.toolkit.jvmargs.model;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
@ToString
public class JvmArgComparison {
    private Result result;
    private String raw1;
    private String raw2;

    public JvmArgComparison(JvmArg arg1, JvmArg arg2) {
        this.raw1 = (arg1 == null) ? null : arg1.getRaw();
        this.raw2 = (arg2 == null) ? null : arg2.getRaw();
        if (arg1 == null || arg2 == null) {
            this.result = Result.UNIQUE;
        } else {
            String value1 = arg1.getValue(), value2 = arg2.getValue();
            this.result = (Objects.equals(value1, value2)) ? Result.SAME : Result.DIFF;
        }
    }

    public enum Result {
        SAME,
        DIFF,
        UNIQUE
    }
}
