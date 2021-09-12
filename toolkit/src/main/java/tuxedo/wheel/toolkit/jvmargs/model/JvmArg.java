package tuxedo.wheel.toolkit.jvmargs.model;

import lombok.Data;
import lombok.ToString;
import tuxedo.wheel.toolkit.jvmargs.JvmArgType;

@Data
@ToString
public class JvmArg {
    private String raw;
    private JvmArgType type;
    private String option;
    private String value;

    public JvmArg(String option) {
        this.option = option;
    }

    public JvmArg(String option, String value) {
        this.option = option;
        this.value = value;
    }
}
