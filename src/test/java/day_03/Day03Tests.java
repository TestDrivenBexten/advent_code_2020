package day_03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.PuzzleInputReader;

class Day03Tests {
  @Test
  @DisplayName("Terrain with single tree in 1,1 should have single collision for slope 1-1")
  void Single_Tree_Test() {
    boolean treeArray[][] = {{false, false}, {false, true}};
    var terrain = new TreeTerrain(treeArray);
    var collisionCount = terrain.calculateCollisionsPerSlope(1, 1);
    assertEquals(1, collisionCount);
  }

  @Test
  @DisplayName("Terrain with trees in 1,1 and 2,2 should repeat pattern for slope 1-1")
  void Double_Tree_Test() {
    boolean treeArray[][] = {{false, false, false}, {false, true, false}, {false, false, true}};
    var terrain = new TreeTerrain(treeArray);
    var collisionCount = terrain.calculateCollisionsPerSlope(1, 1);
    assertEquals(2, collisionCount);
  }

  @Test
  @DisplayName("Terrain with of height 5 and width 2 should have two collisions for slope 1-1")
  void Overflow_Terrain_Test() {
    boolean treeArray[][] = {
      {false, false},
      {false, true},
      {true, false},
      {false, true},
      {true, false}
    };
    var terrain = new TreeTerrain(treeArray);
    var collisionCount = terrain.calculateCollisionsPerSlope(1, 1);
    assertEquals(4, collisionCount);
  }

  @Test
  @DisplayName("Terrain with tree in 2,2 should have single collisions for slope 1-1")
  void Single_Collision_For_Large_Terrain_Test() {
    boolean treeArray[][] = {{false, false, false}, {false, false, false}, {false, false, true}};
    var terrain = new TreeTerrain(treeArray);
    var collisionCount = terrain.calculateCollisionsPerSlope(1, 1);
    assertEquals(1, collisionCount);
  }

  @Test
  @DisplayName("Terrain with tree in 1,2 should have single collisions for slope 1-2")
  void Single_Collision_For_Uneven_Slope_Test() {
    boolean treeArray[][] = {{false, false, false}, {false, false, false}, {false, true, false}};
    var terrain = new TreeTerrain(treeArray);
    var collisionCount = terrain.calculateCollisionsPerSlope(1, 2);
    assertEquals(1, collisionCount);
  }

  @Test
  @DisplayName("Should count 250 collisions on test terrain")
  void Count_For_P1() throws Exception {
    var path = Paths.get("src/test/java/day_03/day_03_input.txt");
    List<String> treeList = PuzzleInputReader.readStringListFromFile(path);

    int terrainWidth = treeList.get(0).length();
    int terrainHeight = treeList.size();

    boolean[][] treeArray = new boolean[terrainHeight][terrainWidth];
    for (int row = 0; row < terrainHeight; row++) {
      String treeRow = treeList.get(row);
      for (int col = 0; col < terrainWidth; col++) {
        char treeChar = treeRow.charAt(col);
        if (treeChar == '#') {
          treeArray[row][col] = true;
        }
      }
    }

    var treeTerrain = new TreeTerrain(treeArray);
    int collisionCount = treeTerrain.calculateCollisionsPerSlope(3, 1);
    assertEquals(250, collisionCount);
  }

  @Test
  @DisplayName("Should find product of five slope collions on test terrain")
  void Count_For_P2() throws Exception {
    var path = Paths.get("src/test/java/day_03/day_03_input.txt");
    List<String> treeList = PuzzleInputReader.readStringListFromFile(path);

    int terrainWidth = treeList.get(0).length();
    int terrainHeight = treeList.size();

    boolean[][] treeArray = new boolean[terrainHeight][terrainWidth];
    for (int row = 0; row < terrainHeight; row++) {
      String treeRow = treeList.get(row);
      for (int col = 0; col < terrainWidth; col++) {
        char treeChar = treeRow.charAt(col);
        if (treeChar == '#') {
          treeArray[row][col] = true;
        }
      }
    }

    var treeTerrain = new TreeTerrain(treeArray);

    int firstSlopeCount = treeTerrain.calculateCollisionsPerSlope(1, 1);
    int secondSlopeCount = treeTerrain.calculateCollisionsPerSlope(3, 1);
    int thirdSlopeCount = treeTerrain.calculateCollisionsPerSlope(5, 1);
    int fourthSlopeCount = treeTerrain.calculateCollisionsPerSlope(7, 1);
    int fifthSlopeCount = treeTerrain.calculateCollisionsPerSlope(1, 2);

    int collisionProduct =
        firstSlopeCount * secondSlopeCount * thirdSlopeCount * fourthSlopeCount * fifthSlopeCount;
    assertEquals(1592662500, collisionProduct);
  }
}
