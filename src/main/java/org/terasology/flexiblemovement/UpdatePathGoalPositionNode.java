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

import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.flexiblepathfinding.PathfinderSystem;
import org.terasology.logic.behavior.tree.Node;
import org.terasology.logic.behavior.tree.Status;
import org.terasology.logic.behavior.tree.Task;
import org.terasology.logic.location.Location;
import org.terasology.logic.location.LocationComponent;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.In;
import org.terasology.world.WorldProvider;

import java.util.Arrays;

/**
 * Find the current location of the pathGoalEntity, sets it as pathGoalPosition
 *
 * SUCCESS: if entity exists and has a position
 * FAILURE: otherwise
 */
public class UpdatePathGoalPositionNode extends Node {
    @Override
    public UpdatePathGoalPositionTask createTask() {
        return new UpdatePathGoalPositionTask(this);
    }

    public static class UpdatePathGoalPositionTask extends Task{
        @In
        PathfinderSystem system;

        @In
        private WorldProvider world;

        protected UpdatePathGoalPositionTask(Node node) {
            super(node);
        }

        @Override
        public Status update(float dt) {

            FlexibleMovementComponent movement = actor().getComponent(FlexibleMovementComponent.class);
            EntityRef entity = movement.pathGoalEntity;

            if (entity == null) {
                return Status.FAILURE;
            }

            LocationComponent location = entity.getComponent(LocationComponent.class);
            if (location == null) {
                return Status.FAILURE;
            }

            movement.pathGoalPosition.set(new Vector3i(location.getWorldPosition()));
            actor().save(movement);

            return Status.SUCCESS;
        }

        @Override
        public void handle(Status result) {

        }
    }
}