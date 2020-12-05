import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.nio.file.Paths;
import java.util.List;

public class Day03Tests {
    @Test
    @DisplayName("Terrain with single tree in 1,1 should have single collision for slope 1-1")
    void Single_Tree_Test(){
        boolean treeArray[][] = {{false,false},{false,true}};
        var terrain = new TreeTerrain(treeArray);
        var collisionCount = terrain.calculateCollisionsPerSlope(1,1);
        assertEquals(1,collisionCount);
    }

    @Test
    @DisplayName("Terrain with trees in 1,1 and 2,2 should have two collisions for slope 1-1")
    void Double_Tree_Test(){
        boolean treeArray[][] = {{false,false,false},{false,true,false},{false,false,true}};
        var terrain = new TreeTerrain(treeArray);
        var collisionCount = terrain.calculateCollisionsPerSlope(1,1);
        assertEquals(2,collisionCount);
    }

    @Test
    @DisplayName("Terrain with tree in 2,2 should have single collisions for slope 1-1")
    void Single_Collision_For_Large_Terrain_Test(){
        boolean treeArray[][] = {{false,false,false},{false,false,false},{false,false,true}};
        var terrain = new TreeTerrain(treeArray);
        var collisionCount = terrain.calculateCollisionsPerSlope(1,1);
        assertEquals(1,collisionCount);
    }

    @Test
    @DisplayName("Should count x collisions on test terrain")
    void Count_For_P1() throws Exception{
        var path = Paths.get("src/test/java/day_03/day_03_input.txt");
        List<String> treeList = PuzzleInputReader.readStringListFromFile(path);

        int terrainWidth = treeList.get(0).length();
        int terrainHeight = treeList.size();

        boolean[][] treeArray = new boolean[terrainWidth][terrainHeight];
        for(int row = 0; row < terrainHeight; row++){
            String treeRow = treeList.get(row);
            for(int col = 0; col < terrainWidth; col++){
                char treeChar = treeRow.charAt(col);
                if(treeChar == '#'){
                    treeArray[row][col] = true;
                }
            }
        }

        var treeTerrain = new TreeTerrain(treeArray);
        int collisionCount = treeTerrain.calculateCollisionsPerSlope(3, 1);
        // TODO collision assertion
    }
}