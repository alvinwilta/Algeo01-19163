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
    public float det;
    // Deklarasi Matriks
    public float[][] Mat = new float[IdxMax][IdxMax];
    public float[][] Temp = new float[IdxMax][IdxMax];
    public float[][] hsl = new float[IdxMax][IdxMax];
    public float[][] M2 = new float[IdxMax][IdxMax];
    public float[][] MU = new float[IdxMax][IdxMax];
    public float[][] K = new float[IdxMax][IdxMax];
    public float[][] SPLU = new float[IdxMax][IdxMax];
    public float[][] SPLK = new float[IdxMax][IdxMax];

    int IdxBrsMin = 1;
    int IdxKolMin = 1;
    int SPLUKol = 0;
    int SPLUBrs = 0;
    int hslBRS =0;
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
    public void bacaMatriksRegresi() {
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
    public double bacaDeterminant() {
        int D = 0;  
        int sign = -1;  
            if (n == 1) 
                return Mat[0][0]; 
          
            for (int f = 0; f < n; f++) 
            { 
                Kofaktor();
                D += sign * Mat[0][f]  
                   * bacaDeterminant(n-1); 
          
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
    public void bacaInverse(){
        if (this.bacaDeterminant(Brs) == 0) {
            System.out.println("Matriks ini tidak memiliki invers.");
            return;
        }

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

    public void TukarBaris(int a, int b) {
        for (int i=1;i<=this.Kol;i++) {
            this.Mat[0][i] = this.Mat[a][i];
            this.Mat[a][i] = this.Mat[b][i];
            this.Mat[b][i] = this.Mat[0][i];
        }
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

    public void Gauss() {
        int i = 1;
        int j = 1;
        float temp;
        while (i <= Brs & j < Kol){
            while (isKolNol(i,j)){
                // untuk skip kolom yang isinya nol semua
                j += 1;
            }
            if (j < Kol){
                TukarBaris(i,indeksTakNol(j,i));
                //meletakkan baris taknol terbawah ke baris paling atas (jika baris paling atas taknol, tukar dengan dirinya)
                buatLeadingOne(i);
                //baris paling 'atas' dibuat menjadi leading one
                buatKolomNolBawah(j,i);
                // (j,i) karena di prosedur buatKolomNolBawah formatnya (kolom,baris)
                i++;
                j++;
            }
        }

    }
    public void GaussJordan() {
        int i = Brs;
        //dimulai dari bawah karena baris paling atas tidak perlu disentuh
        int j;
        Gauss();
        while (i >= 1){
            while (isBarNol(i)){
                i--;
            }
            j = indeksPivot(i);
            buatKolomNolAtas(i,j);
            i--;
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
        if (this.Brs != this.Kol - 1) {
            this.solution = false;
        }

        for (i=IdxBrsMin; i<=Brs; i++){
            for (j=IdxKolMin; j<=Kol-1; j++){
                this.MU[i][j] = this.Mat[i][j]; 
            }
        }
        for (i=IdxBrsMin; i<=Brs; i++){
            this.K[i][1] = this.Mat[i][Kol];
        }
        for (i=IdxBrsMin; i<=Brs; i++){
            for (j=IdxKolMin; j<=Kol-1; j++){
                this.Mat[i][j] = 0 ; 
            }
        }
        for (i=IdxBrsMin; i<=Brs; i++){
            for (j=IdxKolMin; j<=Kol-1; j++){
                this.Mat[i][j] = this.MU[i][j]; 
            }
        }

        for (i=IdxBrsMin; i<=Brs; i++){
            for (j=IdxKolMin; j<=Kol-1; j++){
                this.Mat[i][j] = this.MU[i][j]; 
            }
        }
        double xu = bacaDeterminant();
        Double D = Double.valueOf(xu);
        float XU = D.floatValue();

        for(j=IdxKolMin; j<=Kol-1; j++){
            for(i=IdxBrsMin; i<=Brs;i++){
                this.Mat[i][j] = this.K[i][j];
                double xs = bacaDeterminant();
                Double S = Double.valueOf(xs);
                float XS = S.floatValue();
                this.hsl[i][1] = XS/XU;
                this.Mat[i][j] = this.MU[i][j];
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

            if(bacaDeterminant()==0){
                System.out.println("Matriks tidak memiliki balikan:");
                writer.append("Matriks tidak memiliki balikan:");
                writer.close();

            }
            
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

		catch(IOException ex) {
            System.out.println("File '"+ NamaFile + "' gagal dibuat!");
        }
    }

    public void tulisCrammer() {
        int i,j,k,l,x;
		String NamaFile="HasilCrammer.txt";
		String newline="\r\n";
        if (this.solution==false){
            System.out.println("Tidak Dapat Diselesaikan dgn Metode Cramer");
        }
        else{
            try{
            FileWriter namewriter = new FileWriter(NamaFile);
            BufferedWriter writer = new BufferedWriter(namewriter);

            System.out.println("Matriks setelah OBE");
            writer.append("Matriks Setelah OBE");
            writer.append(newline);
            for (x = 1; x <= Brs; x++){
                writer.append("x" + String.valueOf(x) + "= " + String.valueOf(this.hsl[x][1]) + " ");
                System.out.printf("%.2f ", this.hsl[x][1]);
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
        double Value = bacaDeterminant();
        Double D = Double.valueOf(Value);
        float value = D.floatValue();
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
            System.out.printf("Determinan Matriks: %.2f \n", value);
            writer.append("Determinan Matriks:" + String.valueOf(value) + "\r\n");
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
        double A = M1.bacaDeterminant();
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
}
