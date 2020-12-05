
class TreeTerrain{
    private boolean[][] treeTerrain;
    public TreeTerrain(boolean[][] treeTerrain){
        this.treeTerrain = treeTerrain;
    }

    public int calculateCollisionsPerSlope(int right, int down){
        int collisionCount = 0;

        int horizontalPosition = 0;
        int verticalPosition = 0;

        while(horizontalPosition < treeTerrain.length &&
                verticalPosition < treeTerrain[0].length){
            if(treeTerrain[verticalPosition][horizontalPosition]){
                collisionCount++;
            }
            horizontalPosition += right;
            verticalPosition += down;
        }
        return collisionCount;
    }
}