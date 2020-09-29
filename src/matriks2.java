import java.util.Scanner;

public class matriks2 {
    int IdxMax = 15;
    //Atribut indeks
    int Brs;
    int Kol;
    // Deklarasi Matriks
    float[][] Mat = new float[IdxMax][IdxMax];
    float[][] Temp = new float[IdxMax][IdxMax];
    float[][] MatriksUtamaSPL = new float[IdxMax][IdxMax];
    float[][] MatriksKonstantaSPL = new float[IdxMax][IdxMax];

    //Scanner
    Scanner input = new Scanner(System.in);

    //Konstruktor Matriks
    matriks2(){
    /* Konstruktor matriks, membuat matriks berukuran IdxMax x IdxMax,
    kemudian mengisinya dengan 0 dan inisialisasi Baris dan Kolom efektif*/
        int i,j;

        for (i = 1; i < IdxMax; i++){
            for (j = 1; j < IdxMax; j++){
                this.Mat[i][j] = 0;
            }
        }

        Brs = 0;
        Kol = 0;
    }
    // END OF CONSTRUCTOR




    void bacaUkuranMatriksInterpolasi() {
        // Menerima input jumlah titik yang ingin dimasukkan
        System.out.print("Masukkan jumlah titik yang ingin diinterpolasikan: ");
        Brs = input.nextInt();
        Kol = 2;
    }


    void bacaMatriksInterpolasi(){
        // Membaca matriks khusus untuk fungsi interpolasi
        int i,j;

        bacaUkuranMatriks();
        for (i = 1; i <= Brs; i++){
            for (j = 1; j <= Kol; j++){
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }

    void bacaMatriksGauss() {
        // Membaca matriks khusus untuk fungsi penghitungan Gauss
        int i, j;

        bacaUkuranMatriks ();
        for (i = 1; i <= Brs; i++) {
            for (j = 1; j <= Kol; j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }

    void bacaMatriksGaussJordan() {
        // Membaca matriks khusus untuk fungsi penghitungan Gauss-Jordan
        int i, j;

        bacaUkuranMatriks ();
        for (i = 1; i <= Brs; i++) {
            for (j = 1; j <= Kol; j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }

    boolean isSquare() {
        return (Brs==Kol);
    }

    boolean isBarNol(int i) {
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

    boolean isKolNol(int i, int j) {
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

    int indeksTakNol(int j, int i) {
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

    void buatLeadingOne(int i) {
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
    }

    /* BATASAN LATEST VER */

    void Gauss() {
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
    void GaussJordan() {
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

    void DeterminanReduksi() {
        Gauss();
        det = kaliDiagonal();
    }

    float Kofaktor(int i, int j, int MaxKol) {
        int k;
        float sum = 0;
        // Penghitungan kofaktor baris pertama
        // Asumsi: semua matriks yang ingin dikofaktor berbentuk matriks persegi (nxn)
        if (MaxKol-1>2) {
            for (k = j; k <= MaxKol; k++) {
                sum += Kofaktor(i+1, j+1, MaxKol-1);
            }
        } else {
            return ((Mat[i][j])*((Mat[i+1][j+1]*Mat[i+2][j+2])-(Mat[i+1][j+2]*Mat[i+2][j+1])));
        }
        if (MaxKol==Kol) {
            return (Mat[i][j]*sum);
        }
    }
}

