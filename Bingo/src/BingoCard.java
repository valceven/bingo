import java.util.Random;

public class BingoCard {

    int [][] nums;
    int id;

    public BingoCard(int id){
        this.id = id;
        nums = new int[5][5];
        Random r = new Random();
        int num = 0;

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                do {
                    switch (i) {
                        case 0 -> num = r.nextInt(15) + 1;
                        case 1 -> num = r.nextInt(15) + 16;
                        case 2 -> num = r.nextInt(15) + 31;
                        case 3 -> num = r.nextInt(15) + 46;
                        case 4 -> num = r.nextInt(15) + 61;
                        }
                    }while(sameNumberChecker(num,j,i));
                nums[j][i] = num;
                }
            }
        nums[2][2] = 0;
        }

    private boolean sameNumberChecker(int num, int upper, int col){
        boolean idk = false;
        for(int i = 0; i < upper; i++){
            if(nums[i][col] == num){
                idk = true;
                break;
            }
        }
        return idk;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                sb.append(nums[row][col]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
