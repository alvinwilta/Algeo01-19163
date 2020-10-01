import java.io.FileReader;
import java.util.Scanner;


public class matriks3 {
	int IdxMax = 15;
    //Atribut
    int Brs;
    int Kol;
    float[][] Mat = new float[IdxMax][IdxMax];
    float[][] Temp = new float[IdxMax][IdxMax];
    int IdxBrsMin = 1;
    int IdxKolMin = 1;

    //Scanner
    Scanner input = new Scanner(System.in);
    private boolean solution;
    

    //FUNGSI DASAR//

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
    public void method() throws Exception{  
        System.out.println("Tidaak!! Error...");  
    }  

    public int LeftestOneKoef(int a) {
    for (int i=1;i<=this.Kol;i++) {
        if (Math.abs(this.Mat[a][i]-1) <= 0.0001) return i;
    }
    return -1;
    }
    public double Elmt(int Brs, int Kol){
		return this.Mat[Brs][Kol];
    }
    
    void TukarBaris(int a, int b) {
        for (int i=1;i<=this.Kol;i++) {
            this.Mat[0][i] = this.Mat[a][i];
            this.Mat[a][i] = this.Mat[b][i];
            this.Mat[b][i] = this.Mat[0][i];
        }
    }
    void TambahBaris(int p, int q, double r) {
        for (int i=1;i<=this.Kol;i++) {
            this.Mat[p][i] = (float) (this.Mat[q][i] + this.Mat[q][i] * r);
        }
    }
    
    void KaliBaris(int p, double r) {
        for (int i=1;i<=this.Kol;i++) {
            this.Mat[p][i] = (float) (this.Mat[p][i] * r);
        }
    }
    float Pangkat(float x,int i)
    {
        int j;
        float flo1;
        flo1 = x;
        for(j=1;j<=i-1;j++)
        {
            x = x * flo1;
        }
        return (x);
    }
    //FUNGSI POKOK//

    void bacaUkuranMatriksInterpolasi(){
        //Menerima input banyaknya baris dan banyaknya kolom dari suatu matriks
            System.out.print("Masukan banyaknya baris : ");
            Brs = input.nextInt();
            Kol = 2;
        }
    void bacaMatriksInterpolasi(){
        int i,j;

        bacaUkuranmatriksInterpolasi();
        for (i = 1; i <= Brs; i++){
            for (j = 1; j <= Kol; j++){
                this.Mat[i][j] = input.nextFloat();
            }
        }

    }

    public double BacaDeterminant() {
        double dou = 1;
        matriks M = new matriks();
        M.Brs = this.Brs;
        M.Kol = this.Kol;
        for(int i=1; i<=this.Brs; i++){
            for(int j=1; j<=this.Kol; j++){
                M.Mat[i][j] = this.Mat[i][j];
            }
        }

        for (int i=1;i<=M.Brs;i++) {
            if (M.Mat[i][i] == 0) {
                for (int j=i+1;j<=M.Brs;j++) {
                    if (M.Mat[j][i] != 0) {
                        M.TukarBaris(i, j);
                        dou *= -1;
                        break;
                    }
                }
            }
            if (M.Mat[i][i] == 0) continue;
            dou *= M.Mat[i][i];
            M.KaliBaris(i, 1/M.Mat[i][i]);
            for (int j=i+1;j<=M.Brs;j++) {
                M.TambahBaris(j, i, -1 * M.Mat[j][i] / M.Mat[i][i]);
            }
        }
        for (int i=1;i<=M.Brs;i++) {
            dou *= M.Mat[i][i];
        }
        return dou;
    }
    void BacaInverse(){

        if (this.BacaDeterminant() == 0) {
            System.out.println("matriks ini tidak memiliki invers.");
            return;
        }

        matriks M = new matriks();
        M.Brs = this.Brs;
        M.Kol = this.Kol;
        for(int i=1; i<=this.Brs; i++){
            for(int j=1; j<=this.Kol; j++){
                if(i==j) M.Mat[i][j] = 1;
                else M.Mat[i][j] = 0;
            }
        }
        int Ger = 0;
        double dou;
        for (int i=1;i<=this.Brs;i++) {
            if (this.Mat[i][i + Ger] == 0) {
                boolean tukar = false;
                for (int j=i+1;j<=this.Brs;j++) {
                    if (this.Mat[j][i] != 0) {
                        this.TukarBaris(i, j);
                        M.TukarBaris(i, j);
                        tukar = true;
                        break;
                    }
                }
                if (tukar == false) {
                    Ger++;
                    i--;
                    continue;
                }
            }
            dou = 1/this.Mat[i][i + Ger];
            this.KaliBaris(i, dou);
            M.KaliBaris(i, dou);
            for (int j=i+1;j<=this.Brs;j++) {
                dou = -1 * this.Mat[j][i + Ger] / this.Mat[i][i + Ger];
                this.TambahBaris(j, i, dou);
                M.TambahBaris(j, i, dou);
            }
        }
        for (int i=this.Brs;i>=1;i--) {
            int palingkiri = this.LeftestOneKoef(i);
            if (palingkiri == -1) continue;
            for (int j=i-1;j>=1;j--) {
                dou = -1 * this.Mat[j][palingkiri];
                this.TambahBaris(j, i, dou);
                M.TambahBaris(j, i, dou);
            }
        }
        for(int i=1; i<=this.Brs; i++){
            for(int j=1; j<=this.Kol; j++){
                this.Mat[i][j] = M.Mat[i][j];
            }
        }
    }

    void bacaUkuranmatriks(){
    //Menerima input banyaknya baris dan banyaknya kolom dari suatu matriks
        System.out.print("Masukan banyaknya baris : ");
        Brs = input.nextInt();
        System.out.print("Masukan banyaknya kolom : ");
        Kol = input.nextInt();
    }
    
    
    void bacaFileExtInterpolasi() throws Exception {
        Scanner in = new Scanner (System.in);
        String namaFile = in.nextLine();
        namaFile = "../test/" + namaFile + ".txt";
        FileReader fr = new FileReader(namaFile);
        String sti = "";
        int CC;
        while ((CC = fr.read()) != -1) {
            sti += (char) CC;
        }
        sti = sti.trim();
        sti += '\n';
        int n = -1;
        for (int i = 0; i < sti.length(); i++) {
            if (sti.charAt(i) == '\n') n++;
        }
        this.Brs = n + 1;
        this.Kol = n + 2;
        int x = 1;
        for (int i = 0; i < sti.length(); i++) {
            String c1 = "", c2 = "";
            while (sti.charAt(i) != ' ') {
                c1 += sti.charAt(i);
                i++;
            }
            while (sti.charAt(i) == ' ') i++;
            while (sti.charAt(i) != '\n') {
                c2 += sti.charAt(i);
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
    int indeksPivot(int i){
    //Mengembalikan indeks pivot point pada baris i
    //Dengan asumsi bukan baris yang berisi 0 semua (isBarisNol = false)
        int j = 1;
        boolean Bool = true;

        while (Bool & j < Kol){
            if (this.Mat[i][j] == 0){
                j += 1;
            } else {
                Bool = false;
            }
        } //
        return j;
    }

    void buatKolomNolAtas(int i, int j){
    // Membuat kolom j berisi nol semua diatas indeks i
        int k = i - 1;
        int l;
        float Fkr;

        while (k >= 1){
            Fkr = this.Mat[k][j];
            for (l = 1;l <= Kol;l++){
                this.Mat[k][l] = this.Mat[k][l] - Fkr*this.Mat[i][l];
            }
            k -= 1;
        }}
        void buatKolomNolBawah(int j, int i){
            //Pivot di M[i][j]
            //Membuat kolom j nol dimulai dari baris ke i + 1
                int k = i+1;
                int l;
                float Fkr;
                while (k <= Brs){
                    Fkr = this.Mat[k][j];
                    for (l = 1;l <= Kol;l++){
                        this.Mat[k][l] = this.Mat[k][l] - Fkr*this.Mat[i][l];
                    }
                    k += 1;
                }
            }
    
      //FUNGSI LANJUTAN
      public void matriksInterpolasi()
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
                this.Mat[i][j] = Pangkat(x,(j-1));
            }
            this.Mat[i][Brs+1] = y;
        }
        Kol = Brs+1;
    }
    public void matriksInterpolasiExt() throws Exception
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
                this.Mat[i][j] = Pangkat(x,(j-1));
            }
            this.Mat[i][Brs+1] = y;
        }
        Kol = Brs+1;
    }
    void tulisCramer() {
        matriks sqr = new matriks();
        sqr.Brs = this.Brs;
        sqr.Kol = this.Kol - 1;
        if (this.Brs != this.Kol - 1) {
            System.out.println("Tidak Dapat Diselesaikan dgn Metode Cramer");
            this.solution = false;
        }
    }
    //Fungsi untuk mengembalikan determinan matriks persegi yang akan digunakan untuk kofaktor
    public double DetEx(int a, int b) {
        matriks M = new matriks();
        M.Brs = this.Brs - 1;
        M.Kol = this.Kol - 1;
        int x = 1, y = 1;
        for (int i = 1; i <= this.Brs; i++) {
            if (i == a) continue;
            for (int j = 1; j <= this.Kol; j++) {
                if (j == b) continue;
                M.Mat[x][y++] = this.Mat[i][j];
            }
            y = 1;
            x++;
        }
        return M.Determinan();
    }

    //Fungsi untuk menhasilkan matriks kofaktor
    public float Kofaktor() {
        matriks M = new matriks();
        M.Brs = this.Brs;
        M.Kol = this.Kol;
        for (int i = 1; i <= M.Brs; i++) {
            for (int j = 1; j <= M.Kol; j++) {
                Double cur = 1.0;
                if ((i + j) % 2 == 1) cur *= -1;
                M.Mat[i][j] = (float) (cur * DetEx(i, j));
                if (M.Mat[i][j] != M.Mat[i][j]) M.Mat[i][j] = 0;
            }
        }
        return M;
    }
    public void Transpose(){
        int i,j;
        float temp;
        int brs = Brs;
        int kol = Kol;

        for (i = 1; i<=Brs/2; i++){
            for (j = 1; j <= Kol/2; j++){
                temp = this.Mat[i][j];
                this.Mat[i][j] = this.Mat[j][i];
                this.Mat[j][i] = temp;
            }
        }
        Brs = kol;
        Kol = brs;
    }
    
    public float buatAdjoin() {
        matriks M = this.Kofaktor();
        return M.Transpose();
    }
    
    void tulisInterpolasi(){
        // proses menghitung interpolasi
        for (int i = 1; i <= this.Brs; i++) {
            for (int j = 1; j < this.Kol; j++) {
                this.Mat[i][j] = (float) java.lang.Math.pow(this.Mat[i][j],j-1);
            }
        }
        this.GaussJordan();

        System.out.printf("y= ");
        System.out.printf("%.1f ", this.Mat[1][this.Kol]);
        for (int i = 2; i <= this.Brs; i++){
            if (0 != this.Mat[i][this.Kol]){
                if (this.Mat[i][this.Kol] >= 0){
                    System.out.printf("+");
                }
                System.out.printf("%.1fx^%d ", this.Mat[i][this.Kol], i-1);
            }
        }
        System.out.printf("\n");
    }

    void tulisInterpolasiGauss() throws Exception{
		int i,j,k,l,m,n;
		float sum,x;
		String s,s2,stemp;

			for (i = 1; i <= Brs; i++)
			{
				for (j = 1; j < Kol; j++){
					String.valueOf(this.Mat[i][j] + " ");
				}
				String.valueOf(this.Mat[i][Kol]);
			}

			for (i = Brs; i >= 1;i--){
				j = indeksPivot(i);
				for (k = indeksPivot(i+1)-1; k > indeksPivot(i); k--){
					this.Temp[k][Kol+1] = -1;
				}
				this.Temp[j][Kol] = this.Mat[i][Kol];
			}

			for (i = Brs; i >= 1;i--){
				j = indeksPivot(i);
				for (k = Kol -1;k > j;k--){
					if (this.Temp[k][Kol+1] == -1){
						this.Temp[j][k] = this.Mat[i][k];
					} else {
						this.Temp[j][Kol] = this.Temp[j][Kol] - (this.Temp[k][Kol])*(this.Mat[i][k]);
						for (l = k + 1;l < Kol;l++){
							this.Temp[j][l] = this.Temp[j][l] - (this.Temp[k][l])*(this.Mat[i][k]);
						}
					}
				}
			}
			sum = 0;
			m = 0;
			n = 0;
			System.out.print("Masukkan nilai x = ");
			x = input.nextFloat();
			s = "f(x) = ";
			System.out.print("f(x) = ");
			for (i=1;i<=(Kol-1);i++)
			{
				if(this.Temp[i][Kol] != 0)
				{
					m++;
				}
			}
			for (i=1;i<=(Kol-1);i++)
			{
				if (this.Temp[i][Kol] != 0)
				{
					n++;
					if(i == 1)
					{
						sum += this.Temp[i][Kol];
						stemp = String.valueOf(this.Temp[i][Kol]);
						s = s + stemp;
						System.out.print(this.Temp[i][Kol]);
					} else if (i==2)
					{
						sum += this.Temp[i][Kol] * x;
						stemp = String.valueOf(this.Temp[i][Kol]);
						s = s + stemp +"x";
						System.out.print(this.Temp[i][Kol]+"x");
					} else
					{
						sum += this.Temp[i][Kol] * Pangkat(x,i-1);
						System.out.print(this.Temp[i][Kol]+ "x^"+i);
						stemp = String.valueOf(this.Mat[i][Kol]);
						s = s + stemp + "x^";
						stemp = String.valueOf(i);
						s = s + stemp;
					}
					if (m==n)
					{
						System.out.println();
					} else
					{
						System.out.print(" + ");
						s = s + " + ";
					}

				}
			}
			System.out.println("f(" + x + ") = "+sum);
			stemp = String.valueOf(x);
			s2 = "f(";
			s2 += stemp;
			s2 += ") = ";
			stemp = String.valueOf(sum);
            s2 += sum;
            try {
                tulisInterpolasiGauss();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
	}

}