public class BingoPatternPlus extends BingoChecker{
    int row,col;
    public BingoPatternPlus(BingoCard card,int row,int col){
        super(card);
        this.row = row-1;
        this.col = col-1;
    }

    @Override
    public void run() {

        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                int num = card.nums[row][col];
                while(!BingoGame.result[num]){
                    try {
                        synchronized (BingoGame.result){
                            BingoGame.result.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        BingoGame.bingoChecker = true;
        System.out.println("\nPattern Card "+ card.id + " done: " + card);
    }
}
