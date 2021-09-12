package tuxedo.wheel.toolkit.jvmargs;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tuxedo.wheel.toolkit.jvmargs.model.JvmArg;
import tuxedo.wheel.toolkit.jvmargs.model.JvmArgComparision;
import tuxedo.wheel.toolkit.jvmargs.model.JvmArgIdentifier;

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
        List<JvmArg> argList = Arrays.stream(raw.split(" ")).map(this::parseJvmArg).filter(arg -> arg != null).collect(Collectors.toList());
        mapping(argList);
        return argList;
    }

    /* 对比两串JVM参数 */
    public List<JvmArgComparision> compareJvmArgs(String raw1, String raw2) {
        return compareJvmArgs(parseJvmArgs(raw1), parseJvmArgs(raw2));
    }

    /* 对比两串JVM参数 */
    public List<JvmArgComparision> compareJvmArgs(List<JvmArg> argList1, List<JvmArg> argList2) {
        Map<JvmArgIdentifier, JvmArg> argMap1 = mapping(argList1), argMap2 = mapping(argList2);
        return Stream.concat(argMap1.keySet().stream(), argMap2.keySet().stream()).distinct()
                .map(identifier -> new JvmArgComparision(argMap1.get(identifier), argMap2.get(identifier)))
                .sorted((comparision1, comparision2) -> comparision1.getResult().ordinal() - comparision2.getResult().ordinal()).collect(Collectors.toList());
    }
}
