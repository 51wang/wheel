package tuxedo.wheel.toolkit.jvmargs;

import tuxedo.wheel.toolkit.jvmargs.model.JvmArg;
import tuxedo.wheel.toolkit.jvmargs.model.JvmArgComparison;
import tuxedo.wheel.toolkit.jvmargs.model.JvmArgIdentifier;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JvmArgsParser implements JvmArgsUtils, JvmArgsConsts {
    /* 解析一项JVM参数 */
    public JvmArg parseJvmArg(String raw) {
        if (raw.isEmpty()) {
            return null;
        }
        if (!raw.startsWith(HYPHEN)) {
            throw newIllegalArgumentException(raw);
        }
        String target = raw.substring(HYPHEN.length());
        JvmArg arg = null;
        for (JvmArgType type : JvmArgType.values()) {
            arg = type.parse(raw, target);
            if (arg != null) {
                arg.setRaw(raw);
                arg.setType(type);
                break;
            }
        }
        return arg;
    }

    /* 解析一串JVM参数 */
    public List<JvmArg> parseJvmArgs(String raw) {
        List<JvmArg> argList = Arrays.stream(raw.split(SPACE)).map(this::parseJvmArg).filter(Objects::nonNull)
                .collect(Collectors.toList());
        mapping(argList);
        return argList;
    }

    /* 对比两串JVM参数 */
    public List<JvmArgComparison> compareJvmArgs(String raw1, String raw2) {
        return compareJvmArgs(parseJvmArgs(raw1), parseJvmArgs(raw2));
    }

    /* 对比两串JVM参数 */
    public List<JvmArgComparison> compareJvmArgs(List<JvmArg> argList1, List<JvmArg> argList2) {
        Map<JvmArgIdentifier, JvmArg> argMap1 = mapping(argList1), argMap2 = mapping(argList2);
        return Stream.concat(argMap1.keySet().stream(), argMap2.keySet().stream()).distinct()
                .map(identifier -> new JvmArgComparison(argMap1.get(identifier), argMap2.get(identifier)))
                .sorted(Comparator.comparingInt(comparision -> comparision.getResult().ordinal()))
                .collect(Collectors.toList());
    }
}
