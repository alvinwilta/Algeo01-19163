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

    float[] MultipleLinearRegression(matriks M, int var) {
        // M adalah matriksnya dengan syarat variabel dependen di kolom pertama, diikuti dengan variabel independen
        // var adalah jumlah variabelnya (dependen dan independen)
        // outputnya berupa array, dengan format [A0, A1, A2,...,An] dan
        // equation untuk MLR adalah Y = A0 + A1X1 + A2X2 + ... + AnXn + error (tidak dihitung errornya)
        // Rumusnya adalah: hasil = (X^T X)^-1 .X^T .y
        int i,j,k; // indeks matriks
        float[] hasil = new float[var];
        float[][] XM = new float[var][var];
        float[][] XT = new float[var][M.Brs];
        float[][] X = new float[M.Brs][var];
        float[] y = new float[var];
        // ASSIGN NILAI KE DALAM MATRIKS TEMPORARY
        XT = M.Transpose(); // intinya matriks XT itu isinya transpose dari matriks M
        for (i=0;i<M.Brs;i++) {
            for (j=0;j<var;j++) {
                X[i][j] = M.Mat[i][j];
            }
        }
        for (i=0;i<M.Brs;i++) {
            y[i] = M.Mat[i][1];     // ASSIGN NILAI Y
            XT[0][i] = 1;           // MENGESET NILAI BIAS
            X[i][0] = 1;            // MENGESET NILAI BIAS
        }
        // PERKALIAN MATRIKS ke-1
        for(i=0;i<M.Kol;i++) {
            for (j = 0;j<M.Kol;j++) {
                XM[i][j] = 0;
                for (k = 0;k<M.Brs;k++) {
                    XM[i][j] += XT[i][k] * M.Mat[k][j];
                }
            }
        }
        // INVERS HASIL MATRIKS
        XM.InverseMatriksSPL();
        // PERKALIAN MATRIKS ke-2
        for(i=0;i<var;i++) {
            for (j = 0;j<var;j++) {
                XM[i][j] = 0;
                for (k = 0;k<M.Brs;k++) {
                    XM[i][j] += XM[i][k] * XT[k][j];
                }
            }
        }
        // PERKALIAN MATRIKS ke-3
        for (i=0;i<var;i++) {
            for (j=0;j<var;j++) {
                hasil[i] = XM[j][i]*y[j];
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
        this.XK = new float[Kol];
        // tolong buat array public untuk nyimpen xk, gaperlu alokasi memori, cukup deklarasiin
        // public float[] XK
        System.out.println("Masukkan data-data xk");
        for (i = 1; i<= Kol; i++) {
            this.XK[i] = input.nextFloat();
        }
    }


    void tulisRegresi(float[] regresi) {
        int i;
        System.out.println("Berikut ini adalah hasil prediktor dari normal equation");
        for (i=0;i<regresi.length; i++) {
            System.out.println("x" + i + " = " + regresi[i]);
        }
    }

}

