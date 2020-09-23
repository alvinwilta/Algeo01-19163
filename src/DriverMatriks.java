import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class DriverMatriks {
    static int menu1 = 0; //Menu utama
    static int menu2 = 0; //Metode penyelesaian menuSPL
    static int menu3 = 0; //Pilihan input
    static int menu4 = 0; //pilihan output
    static int n; //Ukuran baris matriks
    static int m; //Ukuran kolom matriks

    static Scanner input = new Scanner(System.in);

    static void MenuUtama(){
        System.out.println("-----SELAMAT DATANG DI KALKULATOR MATRIKS Kelompok 8 !-----");
        System.out.println("-----Pilih menu di bawah ini:-----");
        System.out.println();
        System.out.println("1. Sistem Persamaaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Regresi linier berganda");
        System.out.println("6. Keluar");
        System.out.println("Masukkan Input di bawah ini:\n");
        option1 = input.nextInt();
    }

    static void MenuPilihanSPL(){
        System.out.println("-----SISTEM PERSAMAAN LINIER:-----");
        System.out.println("-----Pilih metode di bawah ini:-----");
        System.out.println();
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("Masukkan Input di bawah ini:\n");
        option2 = input.nextInt();
    }

    static void MenuPilihanDeterminan(){
        System.out.println("-----DETERMINAN:-----");
        System.out.println("-----Pilih metode di bawah ini:-----");
        System.out.println();
        System.out.println("1. Reduksi Baris");
        System.out.println("2. Ekspansi Kofaktor");
        System.out.println("Masukkan Input di bawah ini:\n");
        option2 = input.nextInt();
    }

    static void MenuPilihanInverse(){
        System.out.println("-----INVERSE:-----");
        System.out.println("-----Pilih metode di bawah ini:-----");
        System.out.println();
        System.out.println("1. Reduksi Baris (OBE)");
        System.out.println("2. Metode Kofaktor");
        System.out.println("Masukkan Input di bawah ini:\n");
        option2 = input.nextInt();
    }

    static void MenuInput(){
        System.out.println("-----Pilih metode input di bawah ini:-----");
        System.out.println();
        System.out.println("1. Keyboard");
        System.out.println("2. File .txt");
        System.out.println("Masukkan Input di bawah ini:\n");
        option3 = input.nextInt();
    }

    public static void main (String[] args){

        matriks M = new matriks();

        MenuUtama();
        if (option1 == 1){
            MenuPilihanSPL();
            if (option2 == 1){
                MenuInput();

            }
        }






   
    
    
    
    
    
    public static void main (String[] args){

        matriks M = new matriks();
    
    
    
    }

}