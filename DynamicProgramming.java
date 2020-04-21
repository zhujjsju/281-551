public class DynamicProgramming {

    public static void main(String[] arg){
        System.out.println(MatrixMultiplication());
    }

    public static int MatrixMultiplication(){
        int n = 5;
        //int p[] = {5,4,6,2,7};
        //int p[] = {3,6,4,2,5};
        int p[] = {10,10,20,30,40};
        int m[][] = new int[5][5];
        int s[][] = new int[5][5];
        int j,min,q;
        for (int d=1; d<n-1;d++){
            for (int i=1;i<n-d;i++){
                j=i+d;
                min = Integer.MAX_VALUE;
                for (int k=i;k<=j-1;k++){
                    q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if (q<min){
                        min = q;
                        s[i][j]=k;
                    }
                }
                m[i][j]=min;
            }
        }
        printOrder(s,1,n-1);
        System.out.println();
        return m[1][n-1];
    }

    public static void printOrder(int[][] s,int i,int j){
        if (i==j){
            System.out.print("A" + i);
        }
        else {
            System.out.print("(");
            printOrder(s,i,s[i][j]);
            printOrder(s,s[i][j]+1,j);
            System.out.print(")");
        }
    }
}
