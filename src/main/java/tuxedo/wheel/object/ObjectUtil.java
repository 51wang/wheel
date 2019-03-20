package tuxedo.wheel.object;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectUtil {
    public static String toHexIdentity(Object object) {
        return Integer.toHexString(System.identityHashCode(object));
    }
}
