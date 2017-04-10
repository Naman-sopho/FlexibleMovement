/*
 * Copyright 2016 MovingBlocks
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

import com.google.common.collect.Lists;
import org.terasology.engine.Time;
import org.terasology.entitySystem.Component;
import org.terasology.flexiblemovement.plugin.MovementPlugin;
import org.terasology.flexiblemovement.plugin.FlyingMovementPlugin;
import org.terasology.flexiblemovement.plugin.WalkingMovementPlugin;
import org.terasology.math.geom.Vector3f;
import org.terasology.math.geom.Vector3i;
import org.terasology.world.WorldProvider;

import java.util.List;

public final class FlexibleMovementComponent implements Component {
    public Vector3i target = Vector3i.zero();
    public List<Vector3i> path = Lists.newArrayList();
    public int pathIndex = 0;
    public Vector3i pathTarget = Vector3i.zero();
    public List<String> movementTypes = Lists.newArrayList("walking", "jumping");
    public boolean collidedHorizontally;
    public float lastInput;
    public float stuckSince;
    public Vector3f stuckPosition = Vector3f.zero();
    public int sequenceNumber;

    public MovementPlugin getMovementPlugin(WorldProvider world, Time time) {
        // TODO: registry system for these
        if(movementTypes.get(0).equalsIgnoreCase("flying")) {
            return new FlyingMovementPlugin(world, time);
        }
        return new WalkingMovementPlugin(world, time);
    }
}
