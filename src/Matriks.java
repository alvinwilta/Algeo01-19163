import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class matriks {

    public int IdxMax = 15;
    //Atribut indeks
    public int Brs;
    public int Kol;
    public float det = 1;
    // Deklarasi Matriks
    public float[][] Mat = new float[IdxMax][IdxMax];
    public float[][] Temp = new float[IdxMax][IdxMax];
    public float[][] hsl = new float[IdxMax][IdxMax];
    public float[][] M2 = new float[IdxMax][IdxMax];
    public float[][] SPLU = new float[IdxMax][IdxMax];
    public float[][] SPLK = new float[IdxMax][IdxMax];
    public float[] Regresi = new float[IdxMax];

    int IdxBrsMin = 1;
    int IdxKolMin = 1;
    int SPLUKol = 0;
    int SPLUBrs = 0;
    int hslBRS = 0;
    //Scanner
    Scanner input = new Scanner(System.in);
    private boolean solution;

    //Konstruktor Matriks
    public matriks() {
    /* Konstruktor matriks, membuat matriks berukuran IdxMax x IdxMax,
    kemudian mengisinya dengan 0 dan inisialisasi Baris dan Kolom efektif*/
        int i, j;
        int Brs, Kol;

        for (i = 1; i < IdxMax; i++) {
            for (j = 1; j < IdxMax; j++) {
                this.Mat[i][j] = 0;
            }
        }

        Brs = 0;
        Kol = 0;
    }
    public void method() throws IOException{  
        System.out.println("Tidaak!! Error...");  
    }  

    //Selektor Elemen
    public double Elmt(int row, int col){
        return this.Mat[row][col];
    }

    void KaliBaris(int p, double r) {
        for (int i=1;i<=this.Kol;i++) {
            this.Mat[p][i] = (float) (this.Mat[p][i] * r);
        }
    }
    void TambahBaris(int p, int q, double r) {
        for (int i=1;i<=this.Kol;i++) {
            this.Mat[p][i] = (float) (this.Mat[q][i] + this.Mat[q][i] * r);
        }
    }

    //Fungsi yang mengembalikan indeks angka 1 paling kiri dari baris a
    public int LeftestOneKoef(int a) {
        for (int i=1;i<=this.Kol;i++) {
            if (Math.abs(this.Mat[a][i]-1) <= 0.0001) return i;
        }
        return -1;
        }
    
    public void bacaUkuranMatriks(){
        //Menerima input banyaknya baris dan banyaknya kolom dari suatu matriks
        System.out.print("Masukan banyaknya baris : ");
        Brs = input.nextInt();
        System.out.print("Masukan banyaknya kolom : ");
        Kol = input.nextInt();
    }
    public void bacaMatriksSPLGauss() {
        // Membaca matriks khusus untuk fungsi penghitungan SPL
        int i, j;

        bacaUkuranMatriks ();
        for (i = 1; i <= Brs; i++) {
            for (j = 1; j <= Kol; j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }
    public void bacaMatriksSPLExt(){
		BufferedReader br = null;
		FileReader fr = null;
		int x,dgt,i,d,j;
		float temp,dtemp;
		boolean dec,min;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("matriksSPL.txt");
			br = new BufferedReader(fr);

			String sCurrentLine;

			sCurrentLine = br.readLine();

			if ((sCurrentLine) == null){
				System.out.println("File Kosong");
			}
			else{
				i=0;
				j=0;
				Kol = 0;
				min=false;

				while ((sCurrentLine) != null){						//asumsikan antar elemen matriks pada file eksternal hanya dipisahkan satu spasi
					if (min){
						this.Mat [i][j]=(this.Mat [i][j])*(-1);
					}
					dec = false;
					min = false;
					j = 1;
					temp = 0;
					dgt = 1;
					i++;
					for (x=0;x<=(sCurrentLine.length())-1;x++){
						if ((sCurrentLine.charAt(x))!= ' '){
							if ((sCurrentLine.charAt(x))== '.'){
								dec=true;
								dgt=1;
							}
							else if ((sCurrentLine.charAt(x))== '-'){
								min=true;
							}
							else
							{
								dtemp=(sCurrentLine.charAt(x))-'0';
								if (dec){
									for (d=1;d<=dgt;d++){
										dtemp=dtemp/10;
									}
									dgt++;
									temp=temp+dtemp;
									this.Mat[i][j] = temp;
								}
								else{
									temp=(temp*10)+dtemp;
									this.Mat[i][j] = temp;
								}
							}
						}
						else{
							dec = false;
							temp = 0;
							if (min){
								this.Mat [i][j]=(this.Mat [i][j])*-1;
							}
							j++;
							min=false;
						}
					}
					sCurrentLine = br.readLine();
					if (j>Kol){
						Kol = j;
					}
				}
				if (min){
					this.Mat [i][j]=(this.Mat [i][j])*-1;
				}
				Brs=i;
			}

		}

		catch (IOException e) {

			e.printStackTrace();
			System.out.println("Ada kesalahan pada file eksternal.");

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
    }

    public void bacaFileExtDeterminan(){
		BufferedReader br = null;
		FileReader fr = null;
		int x,dgt,i,d,j;
		float temp,dtemp;
		boolean dec,min;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("matriksDeterminan.txt");
			br = new BufferedReader(fr);

			String sCurrentLine;

			sCurrentLine = br.readLine();

			if ((sCurrentLine) == null){
				System.out.println("File Kosong");
			}
			else{
				i=0;
				j=0;
				Kol = 0;
				min=false;

				while ((sCurrentLine) != null){						//asumsikan antar elemen matriks pada file eksternal hanya dipisahkan satu spasi
					if (min){
						this.Mat [i][j]=(this.Mat [i][j])*(-1);
					}
					dec = false;
					min = false;
					j = 1;
					temp = 0;
					dgt = 1;
					i++;
					for (x=0;x<=(sCurrentLine.length())-1;x++){
						if ((sCurrentLine.charAt(x))!= ' '){
							if ((sCurrentLine.charAt(x))== '.'){
								dec=true;
								dgt=1;
							}
							else if ((sCurrentLine.charAt(x))== '-'){
								min=true;
							}
							else
							{
								dtemp=(sCurrentLine.charAt(x))-'0';
								if (dec){
									for (d=1;d<=dgt;d++){
										dtemp=dtemp/10;
									}
									dgt++;
									temp=temp+dtemp;
									this.Mat[i][j] = temp;
								}
								else{
									temp=(temp*10)+dtemp;
									this.Mat[i][j] = temp;
								}
							}
						}
						else{
							dec = false;
							temp = 0;
							if (min){
								this.Mat [i][j]=(this.Mat [i][j])*-1;
							}
							j++;
							min=false;
						}
					}
					sCurrentLine = br.readLine();
					if (j>Kol){
						Kol = j;
					}
				}
				if (min){
					this.Mat [i][j]=(this.Mat [i][j])*-1;
				}
				Brs = i;
			}

		}

		catch (IOException e) {

			e.printStackTrace();
			System.out.println("Ada kesalahan pada file eksternal.");

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
    }
    public void bacaFileExtBalikan(){
		BufferedReader br = null;
		FileReader fr = null;
		int x,dgt,i,d,j;
		float temp,dtemp;
		boolean dec,min;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("matriksBalikan.txt");
			br = new BufferedReader(fr);

			String sCurrentLine;

			sCurrentLine = br.readLine();

			if ((sCurrentLine) == null){
				System.out.println("File Kosong");
			}
			else{
				i=0;
				j=0;
				Kol = 0;
				min=false;

				while ((sCurrentLine) != null){						//asumsikan antar elemen matriks pada file eksternal hanya dipisahkan satu spasi
					if (min){
						this.Mat [i][j]=(this.Mat [i][j])*(-1);
					}
					dec = false;
					min = false;
					j = 1;
					temp = 0;
					dgt = 1;
					i++;
					for (x=0;x<=(sCurrentLine.length())-1;x++){
						if ((sCurrentLine.charAt(x))!= ' '){
							if ((sCurrentLine.charAt(x))== '.'){
								dec=true;
								dgt=1;
							}
							else if ((sCurrentLine.charAt(x))== '-'){
								min=true;
							}
							else
							{
								dtemp=(sCurrentLine.charAt(x))-'0';
								if (dec){
									for (d=1;d<=dgt;d++){
										dtemp=dtemp/10;
									}
									dgt++;
									temp=temp+dtemp;
									this.Mat[i][j] = temp;
								}
								else{
									temp=(temp*10)+dtemp;
									this.Mat[i][j] = temp;
								}
							}
						}
						else{
							dec = false;
							temp = 0;
							if (min){
								this.Mat [i][j]=(this.Mat [i][j])*-1;
							}
							j++;
							min=false;
						}
					}
					sCurrentLine = br.readLine();
					if (j>Kol){
						Kol = j;
					}
				}
				if (min){
					this.Mat [i][j]=(this.Mat [i][j])*-1;
				}
				Brs=i;
			}

		}

		catch (IOException e) {

			e.printStackTrace();
			System.out.println("Ada kesalahan pada file eksternal.");

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
    }
    
    public void bacaFileExtInterpolasi() throws IOException {
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
        try {
            bacaFileExtInterpolasi();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void tulisInterpolasi () {
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

    public void bacaMatriksBalikanSPL() {
        int i, j;

        bacaUkuranMatriks ();
        for (i = 1; i <= Brs; i++) {
            for (j = 1; j <= Kol; j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }
        i = 0;
        j = 0;
        for (i = IdxBrsMin; i <= Brs; i++){
            for (j = IdxKolMin; j< Kol; j++){
                this.SPLU[i][1]= this.Mat[i][j];
            }
        }
        this.SPLUKol = j;
        this.SPLUBrs = i;

        i = 0;
        for (i = IdxBrsMin; i <= Brs; i++){
            this.SPLK[i][1]= this.Mat[i][Kol];
        }
    }
    public void bacaMatriksBalikan() {
        int i, j;

        bacaUkuranMatriks ();
        for (i = 1; i <= Brs; i++) {
            for (j = 1; j <= Kol; j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }
    public void bacaUkuranMatriksInterpolasi(){
        //Menerima input banyaknya baris dan banyaknya kolom dari suatu matriks
            System.out.print("Masukan banyaknya baris : ");
            Brs = input.nextInt();
            Kol = 2;
        }

    public void bacaMatriksInterpolasi(){
        int i,j;

        bacaUkuranMatriksInterpolasi();
        for (i = 1; i <= Brs; i++){
            for (j = 1; j <= Kol; j++){
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }
    public void bacaMatriksDeterminan() {
        // Membaca matriks khusus untuk fungsi penghitungan Reduksi
        int i, j;

        bacaUkuranMatriks ();
        for (i = 1; i <= Brs; i++) {
            for (j = 1; j <= Kol; j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }
    public void bacaMatriksSPLGaussJordan() {
        // Membaca matriks khusus untuk fungsi penghitungan Gauss-Jordan
        int i, j;

        bacaUkuranMatriks ();
        for (i = 1; i <= Brs; i++) {
            for (j = 1; j <= Kol; j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }

    public double bacaDeterminantKofaktor(int Brs) {
        int D = 0;  
        int sign = -1;  
            if (Brs == 1) 
                return this.Mat[0][0]; 
          
            for (int f = 0; f < Brs; f++) 
            { 
                Kofaktor();
                D += sign * Mat[0][f]  * bacaDeterminantKofaktor(Brs-1); 
          
                sign = -sign; 
            } 
            return D; 
            
    } 
    public void InverseMatriksSPL(){
        int i,j,k;
        int Brs, Kol;
        int sum;

        for (i = 1; i < IdxMax; i++) {
            for (j = 1; j < IdxMax; j++) {
                this.Mat[i][j] = this.SPLU[i][j];
            }
        }
        i = 1;
        j = 1;
        k = 1;
        Brs = SPLUBrs;
        Kol = SPLUKol;
        bacaInverse();
        if (bacaDeterminant() != 0){
            for (i=IdxBrsMin;i<=Brs;i++){
                for(j=IdxKolMin;j<=1;j++){
                    sum = 0;
                    for (k=IdxKolMin; k <= Kol; k++){
                        sum+= this.Mat[i][k] = this.SPLU[k][1];
                    }
                    this.hsl[i][1] = sum;
                    hslBRS++;
                }
            }
        }
    }
    public void bacaInverse() {
        // Membuat matriks identitas
        matriks M2 = new matriks();
        M2.Brs = this.Brs;
        M2.Kol = this.Kol;
        for(int i=1; i<=this.Brs; i++){
            for(int j=1; j<=this.Kol; j++){
                if(i==j) {
                M2.Mat[i][j] = 1;
                }else {
                M2.Mat[i][j] = 0;
                }
            }
        }
        matriks M3 = new matriks();
        M3.Brs = this.Brs + M2.Brs;
        M3.Kol = this.Kol + M2.Kol;
        for (int i = 1; i <= M3.Brs; i++) {
            for (int j = 1; j <= M3.Kol; j++) {
                if (j <= this.Kol) {
                    M3.Mat[i][j] = this.Mat[i][j];
                } else {
                    M3.Mat[i][j] = M2.Mat[i][j - this.Kol];
                }
            }
        }

        M3.GaussJordan();

        matriks N = new matriks();
        N.Brs = this.Brs;
        N.Kol = this.Kol;
        for (int i = 1; i <= N.Brs; i++) {
            for (int j = 1; j <= N.Kol; j++) {
                N.Mat[i][j] = M3.Mat[i][j];
            }
        }

        boolean sama = (N.Mat[1][1]==M2.Mat[1][1]);
        int a = 1;
        int b = 1;
        while ((a <= N.Brs)&&(sama)) {
            while ((b <= N.Kol) && (sama)) {
                sama = sama && (N.Mat[a][b]==M2.Mat[a][b]);
            }
        }

        if (sama) {
            for(int i=1; i<=this.Brs; i++){
                for(int j=1; j<=this.Kol; j++){
                    this.Mat[i][j] = M3.Mat[i][j + this.Kol];
                }
            }
        this.solution = true;

        } else {
            this.solution = false;
        }
    }
    /*
    public void bacaInverse(){
        matriks M2 = new matriks();
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
            KaliBaris(i, tmp);
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
    */

    public void TukarBaris(int a, int b) {
        for (int i=1;i<=this.Kol;i++) {
            this.Mat[0][i] = this.Mat[a][i];
            this.Mat[a][i] = this.Mat[b][i];
            this.Mat[b][i] = this.Mat[0][i];
        }
        det = det * -1;
    }

    public void buatKolomNolBawah(int j, int i){
        //Pivot di M[i][j]
        //Membuat kolom j nol dimulai dari baris ke i + 1
            int k = i+1;
            int l;
            float factor;

            while (k <= Brs){
                factor = this.Mat[k][j];
                for (l = 1;l <= Kol;l++){
                    this.Mat[k][l] = this.Mat[k][l] - factor*this.Mat[i][l];
                }
                k += 1;
            }
    }
    public void buatKolomNolAtas(int i, int j){
        // Membuat kolom j berisi nol semua diatas indeks i
            int k = i - 1;
            int l;
            float factor;
    
            while (k >= 1){
                factor = this.Mat[k][j];
                for (l = 1;l <= Kol;l++){
                    this.Mat[k][l] = this.Mat[k][l] - factor*this.Mat[i][l];
                }
                k -= 1;
            }
    } 
    public int indeksPivot(int i){
        //Mengembalikan indeks pivot point pada baris i
        //Dengan asumsi bukan baris yang berisi 0 semua (isBarNol = false)
        int k = 1;
        boolean cek = true;

        while (cek & k < Kol){
            if (this.Mat[i][k] == 0){
                k += 1;
            } else {
                cek = false;
            }
        }
        return k;
    }
    public float pangkat(float x, int i) {
        int j;
        float xtemp = x;
        for (j = 1; j <= i - 1; j++) {
            x = x * xtemp;
        }
        return x;
    }

    public float kaliDiagonal() {
        float hasil = Mat[0][0];
        int i;
        for (i = 1; i <= Kol; i++) {
            hasil = hasil * this.Mat[i][i];
        }
        return hasil;
    }

    public void tulisMatriks() {
        int i, j;

        for (i = 1; i <= Brs; i++) {
            for (j = 1; j < Kol; j++) {
                System.out.print(this.Mat[i][j] + " ");
            }
            System.out.println(this.Mat[i][Kol]);
        }
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

    public boolean isSquare() {
        return (Brs==Kol);
    }

    public boolean isBarNol(int i) {
        // Mengecek pada matriks apakah baris ke-i adalah nol semua
        boolean nol = true;
        int j = 1;
        while (nol == true & j<=Brs) {
            if (this.Mat[i][j] != 0) {
                nol = false;
            } else {
                j++;
            }
        }
        return nol;
    }

    public boolean isKolNol(int i, int j) {
        // Mememeriksa apakah matriks pada kolom ke-j adalah nol semua
        // dimulai dari baris ke-i hingga ke baris ke-Brs (baris ke-i juga diperiksa)
        boolean nol = true;
        while (nol == true & i<=Brs) {
            if (this.Mat[i][j] != 0) {
                nol = false;
            } else {
                i++;
            }
        }
        return nol;
    }

    public int indeksTakNol(int j, int i) {
        // Mengeluarkan indeks baris pada kolom j yang tidak bernilai 0 dari matriks
        // dimulai dari baris ke-i hingga baris ke-Brs (baris ke-i juga diperiksa)
        // ASUMSI MATRIKS BUKAN MATRIKS DENGAN ELEMEN 0 SEMUA
        boolean nol = true;
        int k = i;
        while (nol == true & i<=Brs) {
            if (this.Mat[k][j] == 0) {
                nol = false;
            } else {
                k++;
            }
        }
        return k;
    }

    public void buatLeadingOne(int i) {
        // Mengubah matriks pada baris ke-i menjadi leading one
        // ASUMSI BARIS TERSEBUT TIDAK NOL SEMUA
        int kolom = 1;
        int j;
        boolean nol = true;
        while ((nol == true) && (kolom<= Kol)) {
            if (this.Mat[i][kolom] != 0) {
                nol = false;
            } else {
                kolom++;
            }
        }
        float faktor = Mat[i][kolom];
        for (j = kolom; kolom<=Kol; kolom++) {
            this.Mat[i][j] = this.Mat[i][j]/faktor;
            
        }
        det = det * faktor;
    }

    public void Gauss(){
        int i,j,k;
        float c;
        for (i=1; i<=Brs; i++){
            for (j=i+1; j<=Brs; j++){
                c= this.Mat[j][i]/this.Mat[i][i];
                for (k=1;k<=Kol;k++){
                    this.Mat[j][k] = this.Mat[j][k] - c*this.Mat[i][k];
                }
            }
        }
    }
    
    public void GaussJordan() {
        int i,j,k;
        float c;
        for (i=1; i<=Brs; i++){
            for (j=1; j<=Brs; j++){
                if (i!=j){
                    c= this.Mat[j][i]/this.Mat[i][i];
                    for (k=1;k<=Kol;k++){
                        this.Mat[j][k] = this.Mat[j][k] - c*this.Mat[i][k];
                    }
                }
                else{
                    float faktor = this.Mat[i][j];
                    for (k = 1; k<=Kol; k++) {
                        this.Mat[i][k] = this.Mat[i][k]/faktor;
                    }
                }
            }
        }
    }

    public void tulisGauss(){
        int i,j,k,l,x;
		boolean found = true;
		String NamaFile="HasilGauss.txt";
		String newline="\r\n";

		try
        {
			FileWriter namewriter = new FileWriter(NamaFile);
			BufferedWriter writer = new BufferedWriter(namewriter);

			for (x = 1; x <= Brs; x++)
			{
				for (j = 1; j < Kol; j++){
					writer.append(String.valueOf(this.Mat[x][j]) + " ");
				}
				writer.append(String.valueOf(this.Mat[x][Kol]));
				writer.append(newline);
			}
			writer.append(newline);

			for (i = Brs; i >= 1;i--){
				j = indeksPivot(i);
				for (k = indeksPivot(i+1)-1; k > indeksPivot(i); k--){
					this.Temp[k][Kol+1] = -1;
				}
				this.Temp[j][Kol] = this.Mat[i][Kol];
			}

			i = Brs;
			while (i >= 1 & found){
				if (isBarNol(i) & this.Mat[i][Kol] != 0){
					found = false;
				} else if (isBarNol(i) & this.Mat[i][Kol] == 0){
					i -= 1;
				} else {
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
					i -= 1;
				}
			}

			if (found){
				for (i = Kol-1; i >= 1;i--){
					if (this.Temp[i][Kol+1] == -1){
						System.out.println("x" + i + " adalah variabel bebas");
						writer.append("x" + Integer.toString(i) + " adalah variabel bebas/r/n");
                    } 
                    else {
						System.out.print("x" + i + " = " + this.Temp[i][Kol]);
						writer.append("x" + Integer.toString(i) + " = " + String.valueOf(this.Temp[i][Kol]));
						for (j = i+1;j < Kol;j++){
							if (this.Temp[i][j] != 0){
								System.out.print(" -(" + this.Temp[i][j] + ")x" + j);
								writer.append(" -(" + String.valueOf(this.Temp[i][j]) + ")x" + Integer.toString(j));
							}
						}
						System.out.println();
						writer.append(newline);
					}
				}
            } 
            else {
				System.out.println("Persamaan ini tidak memiliki solusi\n");
				writer.append("Persamaan ini tidak memiliki solusi\r\n");
			}

			writer.close();
		}

		catch(IOException ex) {
            System.out.println("File '"+ NamaFile + "' gagal dibuat!");}
    }

    public void tulisGaussJordan(){
        int k = 1;
        int i = Brs;
        int j, x;
        boolean found = true;
		String NamaFile="HasilGaussJordan.txt";
		String newline="\r\n";

		try
        {
			FileWriter namewriter = new FileWriter(NamaFile);
			BufferedWriter writer = new BufferedWriter(namewriter);

			for (x = 1; x <= Brs; x++)
			{
				for (j = 1; j < Kol; j++){
					writer.append(String.valueOf(this.Mat[x][j]) + " ");
				}
				writer.append(String.valueOf(this.Mat[x][Kol]));
				writer.append(newline);
			}
			writer.append(newline);

			while (i >= 1 & found){
				if (isBarNol(i) & this.Mat[i][Kol] != 0){
					found = false;
					System.out.println("Persamaan tidak memiliki Solusi!");
					writer.append("Persamaan tidak memiliki Solusi!\r\n");
                } 
                else if (isBarNol(i) & this.Mat[i][Kol] == 0){
					i -= 1;
                } 
                else {
					j = indeksPivot(i);

					for (k = indeksPivot(i + 1) - 1;k > indeksPivot(i);k--){
						System.out.println("x" + k + " adalah variabel bebas");
						writer.append("x" + Integer.toString(k) + " adalah variabel bebas\r\n");
					}
					System.out.print("x" + j + " = " + this.Mat[i][Kol]);
					writer.append("x" + Integer.toString(j) + " = " + String.valueOf(this.Mat[i][Kol]));
					for (k = j + 1;k < Kol;k++){
						if (this.Mat[i][k] != 0){
							System.out.print(" -(" + this.Mat[i][k] + ")x" + k);
							writer.append(" -(" + String.valueOf(this.Mat[i][k]) + ")x" + Integer.toString(k));
						}
					}
					System.out.println();
					writer.append(newline);
					i -= 1;
				}
			}
			writer.close();
		}
		catch(IOException ex) {
            System.out.println("File '"+ NamaFile + "' gagal dibuat!");}
        
    }

    public void tulisInverseMatriksSPL(){
        int i,j;
        String NamaFile="HasilMatriksSPLInverse.txt";
		String newline="\r\n";
        if (this.bacaDeterminant() == 0) {
            System.out.println("Matriks ini tidak memiliki invers.");
            return;
        }
        else{
            try
            {
            FileWriter namewriter = new FileWriter(NamaFile);
            BufferedWriter writer = new BufferedWriter(namewriter);
            
                for (i = IdxBrsMin; i<= hslBRS; i++){
                    writer.append("x"+ String.valueOf(i)+ " = "+ String.valueOf(this.hsl[i][1]));
                    writer.append(newline);
                    System.out.printf("x");
                    System.out.printf("%d",i);
                    System.out.printf(" = ");
                    System.out.printf("%.2f\n",this.hsl[i][1]);
                }
            }
            catch(IOException ex) {
                System.out.println("File '"+ NamaFile + "' gagal dibuat!");
            }
        }
    }
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
                this.Mat[i][j] = pangkat(x,(j-1));
            }
            this.Mat[i][Brs+1] = y;
        }
        Kol = Brs+1;
    }
    public void matriksInterpolasiExt(){
    try
    {
        int i,j;
        float x,y;

        bacaFileExtInterpolasi();
        System.out.println("Matriks Awal untuk Interpolasi");
        tulisMatriks();

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
        catch(IOException ex) {
            System.out.println("Matriks gagal dibuat!");}
    }


    public void Crammer(){
        int i,j;
        float xu, xs;
        matriks MU = new matriks();
        matriks K = new matriks();
        MU.Brs = this.Brs;
        MU.Kol = this.Kol - 1;
        K.Brs = 1;
        K.Kol = this.Brs;

        if (this.Brs != this.Kol - 1) {
            this.solution = false;
        }
        else{
            this.solution = true;
            for (i=IdxBrsMin; i<=Brs; i++){
                for (j=1 ; j<=Kol-1; j++){
                    MU.Mat[i][j] = this.Mat[i][j]; 
                }
            }
            for (i=IdxBrsMin; i<=Brs; i++){
                K.Mat[i][1] = this.Mat[i][Kol];
            }

            xu = MU.bacaDeterminant();
    
            for(j=IdxKolMin; j<=MU.Kol; j++){
                for(i=IdxBrsMin; i<=Brs;i++){
                    MU.Mat[i][j] = K.Mat[i][j];
                }
                xs = MU.bacaDeterminant();
                this.hsl[1][j] = xs/xu;
                for(i=IdxBrsMin; i<=Brs; i++){
                    MU.Mat[i][j] = this.Mat[i][j];
                }
            }
        }
    }
    void tulisInverseReduksi(){
        int i,j,k,l,x;
		String NamaFile="HasilInverseReduksi.txt";
        String newline="\r\n";
        try
        {
			FileWriter namewriter = new FileWriter(NamaFile);
            BufferedWriter writer = new BufferedWriter(namewriter);

            if((bacaDeterminantKofaktor(this.Brs)==0)&&(this.solution==false)){
                System.out.println("Matriks tidak memiliki balikan:");
                writer.append("Matriks tidak memiliki balikan:");
                writer.close();
            }
            else{
                System.out.println("Hasil Inverse Reduksi:");
                writer.append("Hasil Inverse Reduksi");
                writer.append(newline);
                for (x = 1; x <= Brs; x++)
                {
                    for (j = 1; j < Kol; j++){
                        writer.append(String.valueOf(this.Mat[x][j]) + " ");
                        System.out.printf("%.2f ", this.Mat[x][j]);
                    }
                    System.out.printf("%.2f\n", this.Mat[x][Kol]);
                    writer.append(String.valueOf(this.Mat[x][Kol]));
                    writer.append(newline);
                }
                writer.append(newline);
                writer.close();
            }
		}
		catch(IOException ex) {
            System.out.println("File '"+ NamaFile + "' gagal dibuat!");
        }
    }

    void tulisInverseKofaktor(){
        int i,j,k,l,x;
		String NamaFile="HasilInverseKofaktor.txt";
		String newline="\r\n";
        try
        {
			FileWriter namewriter = new FileWriter(NamaFile);
            BufferedWriter writer = new BufferedWriter(namewriter);

            if(bacaDeterminantKofaktor(this.Brs)==0){
                System.out.println("Matriks tidak memiliki balikan:");
                writer.append("Matriks tidak memiliki balikan:");
                writer.close();
            }
            else{
            System.out.println("Hasil Inverse Kofaktor:");
            writer.append("Hasil Inverse Kofaktor");
            writer.append(newline);
			for (x = 1; x <= Brs; x++)
			{
				for (j = 1; j < Kol; j++){
                    writer.append(String.valueOf(this.Mat[x][j]) + " ");
                    System.out.printf("%.2f ", this.Mat[x][j]);
                }
                System.out.printf("%.2f\n", this.Mat[x][Kol]);
				writer.append(String.valueOf(this.Mat[x][Kol]));
				writer.append(newline);
			}
            writer.append(newline);
            writer.close();
            }
		}

		catch(IOException ex) {
            System.out.println("File '"+ NamaFile + "' gagal dibuat!");
        }
    }

    public void tulisCrammer() {
        int x;
		String NamaFile="HasilCrammer.txt";
		String newline="\r\n";
        if (this.solution == false){
            System.out.println("Tidak Dapat Diselesaikan dgn Metode Crammer");
        }
        else{
            try{
            FileWriter namewriter = new FileWriter(NamaFile);
            BufferedWriter writer = new BufferedWriter(namewriter);

            System.out.println("Matriks setelah OBE");
            writer.append("Matriks Setelah OBE");
            writer.append(newline);
            for (x = 1; x <= Brs; x++){
                writer.append("x" + String.valueOf(x) + " = " + String.valueOf(this.hsl[x][1]) + " ");
                System.out.printf("x%d = ", x);
                System.out.printf("%.2f\n", this.hsl[1][x]);
				writer.append(newline);
			    }
            }
            catch(IOException ex) {
                System.out.println("File '"+ NamaFile + "' gagal dibuat!");
            }
        }
    }
    public void tulisDeterminanReduksi(){
        int i,j,k,l,x;
		String NamaFile="HasilDeterminanReduksi.txt";
        String newline="\r\n";
        float det = bacaDeterminant();
        try
        {
			FileWriter namewriter = new FileWriter(NamaFile);
            BufferedWriter writer = new BufferedWriter(namewriter);
            writer.append("Matriks Awal");
            writer.append(newline);

			for (x = 1; x <= Brs; x++)
			{
				for (j = 1; j < Kol; j++){
					writer.append(String.valueOf(this.Mat[x][j]) + " ");
				}
				writer.append(String.valueOf(this.Mat[x][Kol]));
				writer.append(newline);
			}
            writer.append(newline);
            System.out.printf("Determinan Matriks: %.2f \n", det);
            writer.append("Determinan Matriks:" + String.valueOf(det) + "\r\n");
            writer.close();
		}

		catch(IOException ex) {
            System.out.println("File '"+ NamaFile + "' gagal dibuat!");
        }
    }
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
        double X = M.bacaDeterminant();
        return X;
    }

    public float bacaDeterminant(){
        int size = this.Brs;
        float[][] m = new float[Brs][Brs];
        int i, j;
    
        for (i = 0; i < size; i++){
            for (j = 0; j < size; j++){
                m[i][j] = this.Mat[i+1][j+1];
            }
        }
    
        float result = 1;
        int swapped = 0;
    
        while (size > 0){
            boolean swap = false;
            i = 0;
            if (m[size-1][size-1] == 0){
                while (i < (size-1) && (!swap)){
                    if (m[i][size-1] == 0){
                        i++;
                    } else {
                        swap = true;
                    }
                }
            }
            if (i == (size-1) && (size > 1)){
                return 0;
            } else if (swap){
                for(j = 0; j < size; j++){
                    float temp = m[size-1][j];
                    m[size-1][j] = m[i][j];
                    m[i][j] = temp;
                }
            }
            for (i = 0; i < (size-1); i++){
                for(j=0; j<size; j++){
                    m[i][j] -= m[size-1][j]*(m[i][size-1]/m[size-1][size-1]);
                }
            }
            result *= m[size-1][size-1];
            swapped = (swapped+(swap ? 1 : 0))%2;
            size--;
        }
        float finalresult = (result*(swapped==0 ? 1.0f : -1.0f));
        return ((finalresult < 1 && finalresult > -1) ? 0.0f : finalresult);
    }

    public void InverseKofaktor(){
        //Rumus: 1/Det(A) * Transpose (Kofaktor A)
        int i,j;
        matriks M1 = new matriks();
        M1.Brs = this.Brs;
        M1.Kol = this.Kol;
        for (i=IdxBrsMin; i<=Brs; i++){
            for (j=IdxKolMin; j<=Kol; j++){
                M1.Mat[i][j] = this.Mat[i][j];
            }
        }
        M1.Kofaktor();
        M1.Transpose();
        double A = M1.bacaDeterminantKofaktor(this.Brs);
        Double B = Double.valueOf(A);
        float C = B.floatValue();

        for (i=IdxBrsMin; i<=Brs; i++){
            for (j=IdxKolMin; j<=Kol; j++){
               this.Mat[i][j] =  M1.Mat[i][j]/C;
            }
        }
    }

    public matriks Kofaktor() {
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

     float DeterminanKofaktor(matriks m,int n) {
        // m adalah matriks yang ingin dicari determinannya
        // n adalah ukuran matriks
        // fungsi ini mengeluarkan float determinan dengan cara kofaktor
        float det=0;
        int p, h, k, i, j;
        matriks temp = new matriks();
        temp.Brs = n;
        temp.Kol = n;
        if(n==1) {
            return m.Mat[0][0];
        } else if(n==2) {
            det=(m.Mat[0][0]*m.Mat[1][1]-m.Mat[0][1]*m.Mat[1][0]);
            return det;
        } else {
            for(p=0;p<n;p++) {
                h = 0;
                k = 0;
                for(i=1;i<n;i++) {
                    for( j=0;j<n;j++) {
                        if(j==p) {
                            continue;
                        }
                        temp.Mat[h][k] = m.Mat[i][j];
                        k++;
                        if(k==n-1) {
                            h++;
                            k = 0;
                        }
                    }
                }
                det=det+m.Mat[0][p]*pangkat(-1,p)*DeterminanKofaktor(temp,n-1);
            }
            return det;
        }
    }
    public void tulisDeterminanKofaktor(){
        int i,j,k,l,x;
		String NamaFile="HasilDeterminanKofaktor.txt";
        String newline="\r\n";
        matriks M = Kofaktor();
        float value = DeterminanKofaktor(M, M.Brs);
        try
        {
			FileWriter namewriter = new FileWriter(NamaFile);
            BufferedWriter writer = new BufferedWriter(namewriter);

            writer.append("Matriks Awal");
            writer.append(newline);

			for (x = 1; x <= Brs; x++)
			{
				for (j = 1; j < Kol; j++){
					writer.append(String.valueOf(this.Mat[x][j]) + " ");
				}
				writer.append(String.valueOf(this.Mat[x][Kol]));
				writer.append(newline);
            }
            DeterminanKofaktor(M, Brs);
            writer.append(newline);
            System.out.printf("Determinan Matriks:");
            System.out.printf("%.2f", value);
            writer.append("Determinan Matriks:" + String.valueOf(value) + "/r/n");
            writer.close();
		}

		catch(IOException ex) {
            System.out.println("File '"+ NamaFile + "' gagal dibuat!");
        }
    }

    void tulisInterpolasiGauss(){
		int i,j,k,l,m,n;
		float sum,x;
        String s,s2,stemp;
        String NamaFile="HasilDeterminanKofaktor.txt";
        String newline="\r\n";
        try{
            FileWriter namewriter = new FileWriter(NamaFile);
            BufferedWriter writer = new BufferedWriter(namewriter);

			for (i = 1; i <= Brs; i++)
			{
				for (j = 1; j < Kol; j++){
                    String.valueOf(this.Mat[i][j] + " ");
                    writer.append(String.valueOf(this.Mat[i][j]) + " ");
				}
				writer.append(String.valueOf(this.Mat[i][Kol]));
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
            writer.append("f(x) = ");
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
                        writer.append(s);
					} else if (i==2)
					{
						sum += this.Temp[i][Kol] * x;
						stemp = String.valueOf(this.Temp[i][Kol]);
						s = s + stemp +"x";
                        System.out.print(this.Temp[i][Kol]+"x");
                        writer.append(s);
					} else
					{
						sum += this.Temp[i][Kol] * pangkat(x,i-1);
						System.out.print(this.Temp[i][Kol]+ "x^"+i);
						stemp = String.valueOf(this.Mat[i][Kol]);
						s = s + stemp + "x^";
						stemp = String.valueOf(i);
                        s = s + stemp;
                        writer.append(s);
					}
					if (m==n)
					{
                        System.out.println();
                        writer.append(newline);
					} else
					{
						System.out.print(" + ");
                        s = s + " + ";
                        writer.append(s);
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
            writer.append(s2);
            writer.close();
        }
        catch (IOException e) {
			e.printStackTrace();
            System.out.println("Ada kesalahan pada file eksternal.");
        }
    }
    float[] MultipleLinearRegression(matriks M, int var) {
        // M adalah matriksnya dengan syarat variabel dependen di kolom pertama, diikuti dengan variabel independen
        // var adalah jumlah variabelnya (dependen dan independen)
        // outputnya berupa array, dengan format [A0, A1, A2,...,An] dan
        // equation untuk MLR adalah Y = A0 + A1X1 + A2X2 + ... + AnXn + error (tidak dihitung errornya)
        // Rumusnya adalah: hasil = (X^T X)^-1 .X^T .y
        int i,j,k; // indeks matriks
        float[] hasil = new float[var];

        matriks XM = new matriks ();
        XM.Brs = var; XM.Kol = var;

        matriks X = new matriks ();
        X.Brs = Brs; X.Kol = var;

        matriks XT = new matriks ();
        XT.Brs = M.Brs; XT.Kol = var;

        float[] y = new float[var];
        // ASSIGN NILAI KE DALAM MATRIKS TEMPORARY
        for (i=0;i<M.Brs;i++) {
            for (j=0;j<M.Kol;j++) {
                XT.Mat[i][j] = M.Mat[j][i];
            }
        }
        for (i=0;i<M.Brs;i++) {
            for (j=0;j<var;j++) {
                X.Mat[i][j] = M.Mat[i][j];
            }
        }
        for (i=0;i<M.Brs;i++) {
            y[i] = M.Mat[i][1];     // ASSIGN NILAI Y
            XT.Mat[0][i] = 1;           // MENGESET NILAI BIAS
            X.Mat[i][0] = 1;            // MENGESET NILAI BIAS
        }
        // PERKALIAN MATRIKS ke-1
        for(i=0;i<M.Kol;i++) {
            for (j = 0;j<M.Kol;j++) {
                XM.Mat[i][j] = 0;
                for (k = 0;k<M.Brs;k++) {
                    XM.Mat[i][j] += XT.Mat[i][k] * M.Mat[k][j];
                }
            }
        }
        // INVERS HASIL MATRIKS
        XM.InverseMatriksSPL();
        // PERKALIAN MATRIKS ke-2
        for(i=0;i<var;i++) {
            for (j = 0;j<var;j++) {
                XM.Mat[i][j] = 0;
                for (k = 0;k<M.Brs;k++) {
                    XM.Mat[i][j] += XM.Mat[i][k] * XT.Mat[k][j];
                }
            }
        }
        // PERKALIAN MATRIKS ke-3
        for (i=0;i<var;i++) {
            for (j=0;j<var;j++) {
                hasil[i] = XM.Mat[j][i]*y[j];
            }
        }
        return hasil;
    }

    void bacaMatriksRegresi() {
        int i,j;
        System.out.println("Masukkan jumlah variabel peubah:");
        this.Kol = input.nextInt();
        System.out.println("Masukkan berapa banyak data yang ingin dimasukkan:");
        this.Brs = input.nextInt();
        System.out.println("Masukkan data-data tersebut dengan urutan yi x1i x2i x3i ... xni");
        System.out.println("(Perhatikan bahwa y berada pada paling depan matriks)");
        for (i = 1; i <= Brs; i++) {
            for (j = 1; j <= (Kol+1); j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }
        float [] XK = new float[Kol];
        // tolong buat array public untuk nyimpen xk, gaperlu alokasi memori, cukup deklarasiin
        // public float[] XK
        System.out.println("Masukkan data-data xk");
        for (i = 1; i<= Kol; i++) {
            XK[i] = input.nextFloat();
        }
    }
    void tulisRegresi() {
        int i;
        float[] Regresi = new float[this.Kol-1];
        Regresi = MultipleLinearRegression(this, this.Kol-1);
        System.out.println("Berikut ini adalah hasil prediktor dari normal equation");
        for (i=0;i<Regresi.length; i++) {
            System.out.println("x" + i + " = " + Regresi[i]);
        }
    }
}
