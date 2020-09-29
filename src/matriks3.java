import java.io.FileReader;
import java.util.Scanner;


public class matriks03 {
	int IdxMax = 15;
    //Atribut
    int Brs;
    int Kol;
    float[][] Mat = new float[IdxMax][IdxMax];
    float[][] Tampung = new float[IdxMax][IdxMax];
    int IdxBrsMin = 1;
    int IdxKolMin = 1;

    //Scanner
    Scanner keyboard = new Scanner(System.in);

    void matriks(){
    /* Konstruktor matriks, membuat matriks berukuran IdxMax x IdxMax
    dan mengatur semua nilai dari entri matriks bernilai 0*/
        int i,j;

        for (i = 1; i < IdxMax; i++){
            for (j = 1; j < IdxMax; j++){
                this.Mat[i][j] = 0;
            }
        }

        Brs = 0;
        Kol = 0;
    }
//Fungsi yang mengembalikan indeks angka 1 paling kiri dari baris a
    public int LeftestOneKoef(int a) {
    for (int i=1;i<=this.Kol;i++) {
        if (Math.abs(this.Mat[a][i]-1) <= 0.0001) return i;
    }
    return -1;
    }
    public double Elmt(int row, int col){
		return this.Mat[row][col];
    }
    
    void TukarBaris(int a, int b) {
        for (int i=1;i<=this.Kol;i++) {
            this.Mat[0][i] = this.Mat[a][i];
            this.Mat[a][i] = this.Mat[b][i];
            this.Mat[b][i] = this.Mat[0][i];
        }
    }
    void TambahBaris(int a, int b, double x) {
        for (int i=1;i<=this.Kol;i++) {
            this.Mat[a][i] = (float) (this.Mat[a][i] + this.Mat[b][i] * x);
        }
    }
    
    void KaliBaris(int a, double x) {
        for (int i=1;i<=this.Kol;i++) {
            this.Mat[a][i] = (float) (this.Mat[a][i] * x);
        }
    }
    float pangkat(float x,int i)
    {
        int j;
        float xtemp;

        xtemp = x;
        for(j=1;j<=i-1;j++)
        {
            x = x * xtemp;
        }
        return (x);
    }
    void bacaUkuranMatriksInterpolasi(){
        //Menerima input banyaknya baris dan banyaknya kolom dari suatu matriks
            System.out.print("Masukan banyaknya baris : ");
            Brs = keyboard.nextInt();
            Kol = 2;
        }
    void bacaMatriksInterpolasi(){
        int i,j;

        bacaUkuranMatriksInterpolasi();
        for (i = 1; i <= Brs; i++){
            for (j = 1; j <= Kol; j++){
                this.Mat[i][j] = keyboard.nextFloat();
            }
        }

    }
    public static Matriks interpolate(Matriks func, int deg) {
	
		
		Matriks A = new Matriks();
		A.Solution_type = 4;
		
		for (int i = 1; i <= deg+1; i++){
			A.Mat[i][1] = 1;					// asign 1 to all first column
			A.Mat[i][2] = func.Elmt(i,1); // copy the value of func into second column
            for (int j = 3; j <= deg+1; j++){
                A.Mat[i][j] = (float) java.lang.Math.pow(A.Elmt(i, 2), j - 1);
            }
		}
		
		return A;
	}

    public double bacaDeterminant() {
        double ret = 1;
        Matriks M2 = new Matriks();
        M2.Brs = this.Brs;
        M2.Kol = this.Kol;
        for(int i=1; i<=this.Brs; i++){
            for(int j=1; j<=this.Kol; j++){
                M2.Mat[i][j] = this.Mat[i][j];
            }
        }

        for (int i=1;i<=M2.Brs;i++) {
            if (M2.Mat[i][i] == 0) {
                for (int j=i+1;j<=M2.Brs;j++) {
                    if (M2.Mat[j][i] != 0) {
                        M2.TukarBaris(i, j);
                        ret *= -1;
                        break;
                    }
                }
            }
            if (M2.Mat[i][i] == 0) continue;
            ret *= M2.Mat[i][i];
            M2.KaliBaris(i, 1/M2.Mat[i][i]);
            for (int j=i+1;j<=M2.Brs;j++) {
                M2.TambahBaris(j, i, -1 * M2.Mat[j][i] / M2.Mat[i][i]);
            }
        }
        for (int i=1;i<=M2.Brs;i++) {
            ret *= M2.Mat[i][i];
        }
        return ret;
    }
    void bacaInverse(){

        if (this.bacaDeterminant() == 0) {
            System.out.println("Matriks ini tidak memiliki invers.");
            return;
        }

        Matriks M2 = new Matriks();
        M2.Brs = this.Brs;
        M2.Kol = this.Kol;
        for(int i=1; i<=this.Brs; i++){
            for(int j=1; j<=this.Kol; j++){
                if(i==j) M2.Mat[i][j] = 1;
                else M2.Mat[i][j] = 0;
            }
        }
        int move = 0;
        double tmp;
        for (int i=1;i<=this.Brs;i++) {
            if (this.Mat[i][i + move] == 0) {
                boolean tukar = false;
                for (int j=i+1;j<=this.Brs;j++) {
                    if (this.Mat[j][i] != 0) {
                        this.TukarBaris(i, j);
                        M2.TukarBaris(i, j);
                        tukar = true;
                        break;
                    }
                }
                if (tukar == false) {
                    move++;
                    i--;
                    continue;
                }
            }
            tmp = 1/this.Mat[i][i + move];
            this.KaliBaris(i, tmp);
            M2.KaliBaris(i, tmp);
            for (int j=i+1;j<=this.Brs;j++) {
                tmp = -1 * this.Mat[j][i + move] / this.Mat[i][i + move];
                this.TambahBaris(j, i, tmp);
                M2.TambahBaris(j, i, tmp);
            }
        }
        for (int i=this.Brs;i>=1;i--) {
            int palingkiri = this.LeftestOneKoef(i);
            if (palingkiri == -1) continue;
            for (int j=i-1;j>=1;j--) {
                tmp = -1 * this.Mat[j][palingkiri];
                this.TambahBaris(j, i, tmp);
                M2.TambahBaris(j, i, tmp);
            }
        }
        for(int i=1; i<=this.Brs; i++){
            for(int j=1; j<=this.Kol; j++){
                this.Mat[i][j] = M2.Mat[i][j];
            }
        }
    }

    void bacaUkuranMatriks(){
    //Menerima input banyaknya baris dan banyaknya kolom dari suatu matriks
        System.out.print("Masukan banyaknya baris : ");
        Brs = keyboard.nextInt();
        System.out.print("Masukan banyaknya kolom : ");
        Kol = keyboard.nextInt();
    }
    
    
    void bacaFileExtInterpolasi() throws Exception {
        Scanner in = new Scanner (System.in);
        String namaFile = in.nextLine();
        namaFile = "../test/" + namaFile + ".txt";
        FileReader fr = new FileReader(namaFile);
        String str = "";
        int cc;
        while ((cc = fr.read()) != -1) {
            str += (char) cc;
        }
        str = str.trim();
        str += '\n';
        int n = -1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\n') n++;
        }
        this.Brs = n + 1;
        this.Kol = n + 2;
        int x = 1;
        for (int i = 0; i < str.length(); i++) {
            String c1 = "", c2 = "";
            while (str.charAt(i) != ' ') {
                c1 += str.charAt(i);
                i++;
            }
            while (str.charAt(i) == ' ') i++;
            while (str.charAt(i) != '\n') {
                c2 += str.charAt(i);
                i++;
            }
            float cur = 1;
            float a = (float) Double.parseDouble(c1);
            Float b = (float) Double.parseDouble(c2);
            for (int j = 1; j <= n + 1; j++) {
                this.Mat[x][j] = cur;
                cur *= a;
            }
            this.Mat[x][n + 2] = b;
            x++;
        }
    }
    void tulisInterpolasi () {
        for (int i = Brs ; i <= this.Brs ; i++  ) {
          float temp, hsl;
          temp =  this.Mat[i][1];
          hsl = this.Mat[i][2];
          for (int j = this.IdxKolMin ; j <= this.Kol ; j++) {
            if (j != this.Kol) {
              this.Mat[i][j] = (float) (Math.pow(temp, j - 1));
            } else {
              this.Mat[i][j] = hsl;
            }
          }
        }
      }
    
      //FUNGSI LANJUTAN
      void matriksInterpolasi()
    {
        int i,j;
        float x,y;

        bacaMatriksInterpolasi();

        for(i=1;i<=Brs;i++)
        {
            x = this.Mat[i][1];
            y = this.Mat[i][2];
            this.Mat[i][1] = 1;
            for(j=2;j<=Brs;j++)
            {
                this.Mat[i][j] = pangkat(x,(j-1));
            }
            this.Mat[i][Brs+1] = y;
        }
        Kol = Brs+1;
    }
    void matriksInterpolasiExt() throws Exception
    {
        int i,j;
        float x,y;

        bacaFileExtInterpolasi();

        for(i=1;i<=Brs;i++)
        {
            x = this.Mat[i][1];
            y = this.Mat[i][2];
            this.Mat[i][1] = 1;
            for(j=2;j<=Brs;j++)
            {
                this.Mat[i][j] = pangkat(x,(j-1));
            }
            this.Mat[i][Brs+1] = y;
        }
        Kol = Brs+1;
    }
}



