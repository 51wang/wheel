package tuxedo.wheel.toolkit.jvmargs;

import tuxedo.wheel.toolkit.jvmargs.model.JvmArg;

public enum JvmArgType implements JvmArgsUtils, JvmArgsConsts {
    TYPE_D// -Duser.home=/home/admin
    {
        @Override
        protected JvmArg parse(String raw, String target) {
            if (!target.startsWith(PREFIX_D)) {
                return null;
            }
            target = target.substring(PREFIX_D.length());
            return parseOptionAndValue(raw, target, EQUAL);
        }
    },
    TYPE_XX_COLON// -XX:+PrintGCDetails -XX:-PrintGCCause -XX:MaxGCPauseMillis=100
    {
        @Override
        protected JvmArg parse(String raw, String target) {
            if (!target.startsWith(PREFIX_XX_COLON)) {
                return null;
            }
            target = target.substring(PREFIX_XX_COLON.length());
            if (target.startsWith(PLUS)) {
                return new JvmArg(target.substring(PLUS.length()), PLUS);
            } else if (target.startsWith(MINUS)) {
                return new JvmArg(target.substring(MINUS.length()), MINUS);
            } else {
                return parseOptionAndValue(raw, target, EQUAL);
            }
        }
    },
    TYPE_X// -Xloggc:/tmp/gc.log -Xss512k -Xdebug
    {
        @Override
        protected JvmArg parse(String raw, String target) {
            if (!target.startsWith(PREFIX_X)) {
                return null;
            }
            target = target.substring(PREFIX_X.length());
            if (target.contains(COLON)) {
                return parseOptionAndValue(raw, target, COLON);
            } else {
                return parseOptionAndNumbericValue(raw, target);
            }
        }
    },
    TYPE_COLON// -verbose:gc
    {
        @Override
        protected JvmArg parse(String raw, String target) {
            if (!target.contains(COLON)) {
                return null;
            }
            return parseOptionAndValue(raw, target, COLON);
        }
    },
    TYPE_SIMPLE// -server
    {
        @Override
        protected JvmArg parse(String raw, String target) {
            return new JvmArg(target);
        }
    };

    protected abstract JvmArg parse(String raw, String target);
}
