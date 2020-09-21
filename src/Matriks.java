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
    float[][] Tampung = new float[IdxMax][IdxMax];

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
    

    
}