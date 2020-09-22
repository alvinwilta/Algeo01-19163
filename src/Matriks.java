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
    // Deklarasi Matriks
    float[][] Mat = new float[IdxMax][IdxMax];
    float[][] Temp = new float[IdxMax][IdxMax];

    //Scanner
    Scanner input = new Scanner(System.in);

    //Konstruktor Matriks
    matriks(){
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

    void bacaMatriksBalikanSPL(){
        int i,j;

        void bacaUkuranMatriks();
        for (i = 1; i <= Brs; i++){
            for (j = 1; j <= Kol; j++){
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }

    void bacaMatriksBalikan(){
        int i,j;

        void bacaUkuranMatriks();
        for (i = 1; i <= Brs; i++){
            for (j = 1; j <= Kol; j++){
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }

    void bacaMatriksRegresi(){
        int i,j;

        void bacaUkuranMatriks();
        for (i = 1; i <= Brs; i++){
            for (j = 1; j <= Kol; j++){
                this.Mat[i][j] = input.nextFloat();
            }
        }
    }

    float pangkat(float x,int i)
    {
        int j;
        float temp;

        xtemp = x;
        for (j=1 ; j <= i-1; j++)
        {
            x = x * temp;
        }
        return x;
    }

    float kaliDiagonal(){
        float hasil;
        for (i = 1; i <= Kol; i++){
            hasil = hasil * this.Mat[i][i];
        }
        return hasil;
    }

    void tulisMatriks(){
        int i,j;

        for (i = 1; i <= Brs; i++){
            for (j = 1; j < Kol; j++){
                System.out.print(this.Mat[i][j] + " ");
            }
            System.out.println(this.Mat[i][Kol]);
        }
    }
    
    void TukarBaris(int a, int b){
        int c;
        float temp;

        for (c = 1;c <= Kol;c++){
            temp = this.Mat[b][c];
            this.Mat[b][c] = this.Mat[a][c];
            this.Mat[a][c] = temp;
        }
    }