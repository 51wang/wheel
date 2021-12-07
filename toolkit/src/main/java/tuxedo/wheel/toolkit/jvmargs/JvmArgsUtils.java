package tuxedo.wheel.toolkit.jvmargs;

import tuxedo.wheel.toolkit.jvmargs.model.JvmArg;
import tuxedo.wheel.toolkit.jvmargs.model.JvmArgIdentifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface JvmArgsUtils {
    default IllegalArgumentException newIllegalArgumentException(String raw) {
        return new IllegalArgumentException("illegal JVM ARG  :  \"" + raw + "\"");
    }

    default JvmArg parseOptionAndValue(String raw, String target, String separator) {
        String[] arr = target.split(separator);
        if (arr.length == 2) {
            return new JvmArg(arr[0], arr[1]);
        }
        if (arr.length == 1 && !arr[0].isEmpty()) {
            return new JvmArg(arr[0]);
        }
        throw newIllegalArgumentException(raw);
    }

    default JvmArg parseOptionAndNumbericValue(String raw, String target) {
        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);
            if (ch >= '0' && ch <= '9') {
                if (i == 0) {
                    break;
                }
                return new JvmArg(target.substring(0, i), target.substring(i));
            }
        }
        return new JvmArg(target);
    }

    default Map<JvmArgIdentifier, JvmArg> mapping(List<JvmArg> argList) {
        Map<JvmArgIdentifier, JvmArg> argMap = new HashMap<>();
        for (JvmArg arg : argList) {
            JvmArg previous = argMap.put(new JvmArgIdentifier(arg), arg);
            if (previous != null) {
                throw new IllegalArgumentException(
                        "conflicting JVM ARGs  :  \"" + arg.getRaw() + "\" and \"" + previous.getRaw() + "\"");
            }
        }
        return argMap;
    }
}
