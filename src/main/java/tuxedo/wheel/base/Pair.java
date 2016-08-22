package tuxedo.wheel.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pair<V1, V2> {
    private V1 v1;
    private V2 v2;
}
