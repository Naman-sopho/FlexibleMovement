/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.flexiblemovement;

import org.terasology.math.geom.Vector3f;
import org.terasology.math.geom.Vector3i;

import java.math.RoundingMode;

/**
 * Miscellaneous helper methods
 */
public class FlexibleMovementHelper {
    public static Vector3i posToBlock(Vector3f pos) {
        int y = (int) Math.floor(pos.y);
        Vector3i result = new Vector3i(pos, RoundingMode.HALF_UP);
        result.y = y;
        return result;
    }
}
