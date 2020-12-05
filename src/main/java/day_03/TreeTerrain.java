
class TreeTerrain{
    private boolean[][] treeTerrain;
    public TreeTerrain(boolean[][] treeTerrain){
        this.treeTerrain = treeTerrain;
    }

    public int calculateCollisionsPerSlope(int right, int down){
        int collisionCount = 0;

        if(treeTerrain[right][down]){
            collisionCount++;
        }
        return collisionCount;
    }
}