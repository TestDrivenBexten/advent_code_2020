import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class Day03Tests {
    @Test
    @DisplayName("Terrain with single tree in 1,1 should have single collision for slope 1-1")
    void Single_Tree_Test(){
        boolean treeArray[][] = {{false,false},{false,true}};
        var terrain = new TreeTerrain(treeArray);
        var collisionCount = terrain.calculateCollisionsPerSlope(1,1);
        assertEquals(1,collisionCount);
    }
}