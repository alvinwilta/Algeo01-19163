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
    public float[][] MatriksUtamaSPL = new float[IdxMax][IdxMax];
    public float[][] MatriksKonstantaSPL = new float[IdxMax][IdxMax];

    int IdxBrsMin = 1;
    int IdxKolMin = 1;
    //Scanner
    Scanner input = new Scanner(System.in);

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
    //Selektor Elemen
    public double Elmt(int row, int col){
        return this.Mat[row][col];
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
    
    public void bacaFileExtInterpolasi() throws Exception {
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
        double ret = 1;
        matriks M2 = new matriks();
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

    public void bacaInverse(){

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

    public static matriks interpolate(matriks func, int deg) {
	
		
		matriks A = new matriks();
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
        while (nol == true) {
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
                this.Mat[i][j] = pangkat(x,(j-1));
            }
            this.Mat[i][Brs+1] = y;
        }
        Kol = Brs+1;
    }

    public void tulisDeterminanReduksi(){
        double x;
        value = bacaDeterminant();
        System.out.printf("Determinan Matriks: %.2f", value);
    }

    public float Crammer(){
        int i,j;
        double XU, XS;

        //Matriks Baru Linear hasil
        matriks hsl = new matriks();
        hsl.Brs = this.Brs;
        hsl.Kol = this.Kol - 1;

        //Matriks Baru Utama sebelum operasi
        matriks MU = new matriks();
        MU.Brs = this.Brs;
        MU.Kol = this.Kol - 1;

        //Matriks Baru Utama untuk operasi
        matriks MO = new matriks();
        MO.Brs = this.Brs;
        MO.Kol = this.Kol - 1;

        //Matriks baru menampung konstanta
        matriks K = new matriks();
        K.Brs = this.Brs;
        K.Kol = 1;


        for (i=IdxBrsMin; i<=MU.Brs; i++){
            for (j=IdxKolMin; j<=MU.Kol; j++){
                MU.Elmt(i, j) = this.Mat[i][j]; 
            }
        }
        for (i=IdxBrsMin; i<=K.Brs; i++){
            K.Elmt(i, j) = this.Mat[i][j];
        }
        for (i=IdxBrsMin; i<=MU.Brs; i++){
            for (j=IdxKolMin; j<=MU.Kol; j++){
                this.Mat[i][j] = 0 ; 
                this.Brs = MU.Brs;
                this.Kol = MU.Kol;
            }
        }
        for (i=IdxBrsMin; i<=MU.Brs; i++){
            for (j=IdxKolMin; j<=MU.Kol; j++){
                this.Mat[i][j] = MU.Elmt(i,j); 
        }

        for (i=IdxBrsMin; i<=MU.Brs; i++){
            for (j=IdxKolMin; j<=MU.Kol; j++){
                this.Mat[i][j] = MU.Elmt(i,j); 
        }
        XU=bacaDeterminant();

        for(j=IdxKolMin; j<=MU.Kol; j++){
            for(i=IdxBrsMin; i<=MU.Brs;i++){
                MO.Elmt(i,j) = 
            }
        }
        
        
    }
}