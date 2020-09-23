import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class matriks {

    int IdxMax = 15;
    //Atribut indeks
    int Brs;
    int Kol;
    float Det;
    // Deklarasi Matriks
    float[][] Mat = new float[IdxMax][IdxMax];
    float[][] Temp = new float[IdxMax][IdxMax];
    float[][] MatriksUtamaSPL = new float[IdxMax][IdxMax];
    float[][] MatriksKonstantaSPL = new float[IdxMax][IdxMax];

    //Scanner
    Scanner input = new Scanner(System.in);

    //Konstruktor Matriks
    matriks() {
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

    void bacaMatriksBalikanSPL() {
        int i, j;

        void bacaUkuranMatriks ();
        for (i = 1; i <= Brs; i++) {
            for (j = 1; j <= Kol; j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }

    void bacaMatriksBalikan() {
        int i, j;

        void bacaUkuranMatriks ();
        for (i = 1; i <= Brs; i++) {
            for (j = 1; j <= Kol; j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }

    void bacaMatriksRegresi() {
        int i, j;

        void bacaUkuranMatriks ();
        for (i = 1; i <= Brs; i++) {
            for (j = 1; j <= Kol; j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }

    float pangkat(float x, int i) {
        int j;
        float temp;
        float xtemp = x;
        for (j = 1; j <= i - 1; j++) {
            x = x * temp;
        }
        return x;
    }

    float kaliDiagonal() {
        float hasil;
        int i;
        for (i = 1; i <= Kol; i++) {
            hasil = hasil * this.Mat[i][i];
        }
        return hasil;
    }

    void tulisMatriks() {
        int i, j;

        for (i = 1; i <= Brs; i++) {
            for (j = 1; j < Kol; j++) {
                System.out.print(this.Mat[i][j] + " ");
            }
            System.out.println(this.Mat[i][Kol]);
        }
    }

    void TukarBaris(int a, int b) {
        int c;
        float temp;

        for (c = 1; c <= Kol; c++) {
            temp = this.Mat[b][c];
            this.Mat[b][c] = this.Mat[a][c];
            this.Mat[a][c] = temp;
        }
        this.Det = this.Det * (-1);
    }

    void inverse() {

    }

    void bacaUkuranMatriksInterpolasi() {
        // Menerima input jumlah titik yang ingin dimasukkan
        System.out.print("Masukkan jumlah titik yang ingin diinterpolasikan: ");
        Brs = input.nextInt();
        Kol = 2;
    }


    void bacaMatriksInterpolasi(){
        // Membaca matriks khusus untuk fungsi interpolasi
        int i,j;

        void bacaUkuranMatriks();
        for (i = 1; i <= Brs; i++){
            for (j = 1; j <= Kol; j++){
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }

    void bacaMatriksSPLGauss() {
        // Membaca matriks khusus untuk fungsi penghitungan Gauss
        int i, j;

        void bacaUkuranMatriks ();
        for (i = 1; i <= Brs; i++) {
            for (j = 1; j <= Kol; j++) {
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }

    void bacaMatriksSPLGaussJordan() {
        // Membaca matriks khusus untuk fungsi penghitungan Gauss-Jordan
        int i, j;

        void bacaUkuranMatriks ();
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
}