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

    //Scanner
    Scanner input = new Scanner(System.in);

    //Konstruktor Matriks
    public matriks() {
    /* Konstruktor matriks, membuat matriks berukuran IdxMax x IdxMax,
    kemudian mengisinya dengan 0 dan inisialisasi Baris dan Kolom efektif*/
        int i, j;

        for (i = 1; i < IdxMax; i++) {
            for (j = 1; j < IdxMax; j++) {
                this.Mat[i][j] = 0;
            }
        }

        Brs = 0;
        Kol = 0;
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
    public void bacaFileExtInterpolasi(){
		BufferedReader br = null;
		FileReader fr = null;
		int x,dgt,i,d,j;
		float temp,dtemp;
		boolean dec,min;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("matriksInterpolasi.txt");
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
				}
				if (min){
					this.Mat [i][j]=(this.Mat [i][j])*-1;
				}
				Kol=2;
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
    public void bacaUkuranMatriksInterpolasi() {
        // Menerima input jumlah titik yang ingin dimasukkan
        System.out.print("Masukkan jumlah titik yang ingin diinterpolasikan: ");
        Brs = input.nextInt();
        Kol = 2;
    }
    public void bacaMatriksInterpolasi(){
        // Membaca matriks khusus untuk fungsi interpolasi
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

    public void TukarBaris(int a, int b) {
        int c;
        float temp;

        for (c = 1; c <= Kol; c++) {
            temp = this.Mat[b][c];
            this.Mat[b][c] = this.Mat[a][c];
            this.Mat[a][c] = temp;
        }
        det = det * (-1);
    }

    public void inverse() {
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
}