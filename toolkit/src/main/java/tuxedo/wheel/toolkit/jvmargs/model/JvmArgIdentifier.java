package tuxedo.wheel.toolkit.jvmargs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tuxedo.wheel.toolkit.jvmargs.JvmArgType;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class JvmArgIdentifier {
    private JvmArgType type;
    private String option;

    public JvmArgIdentifier(JvmArg arg) {
        this(arg.getType(), arg.getOption());
    }
}
