import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DriverMatriks {
    static int menu1 = 0; //Menu utama
    static int menu2 = 0; //Metode penyelesaian menuSPL
    static int menu3 = 0; //Pilihan input
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
        menu1 = input.nextInt();
    }

    static void MenuPilihanSPL(){
        System.out.println("-----SISTEM PERSAMAAN LINIER:-----");
        System.out.println("-----Pilih metode di bawah ini:-----");
        System.out.println();
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("9. Kembali");
        System.out.println("0. Menu Utama");
        System.out.println("Masukkan Input di bawah ini:\n");
        menu2 = input.nextInt();
    }

    static void MenuPilihanInterpolasi(){
        System.out.println("-----MATRIKS INTERPOLASI:-----");
        System.out.println("-----Pilih metode di bawah ini:-----");
        System.out.println();
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("9. Kembali");
        System.out.println("0. Menu Utama");
        System.out.println("Masukkan Input di bawah ini:\n");
        menu2 = input.nextInt();
    }

    static void MenuPilihanRegresi(){
        System.out.println("-----MATRIKS Regresi:-----");
        System.out.println("-----Pilih metode di bawah ini:-----");
        System.out.println();
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("9. Kembali");
        System.out.println("0. Menu Utama");
        System.out.println("Masukkan Input di bawah ini:\n");
        menu2 = input.nextInt();
    }

    static void MenuPilihanDeterminan(){
        System.out.println("-----DETERMINAN:-----");
        System.out.println("-----Pilih metode di bawah ini:-----");
        System.out.println();
        System.out.println("1. Reduksi Baris");
        System.out.println("2. Ekspansi Kofaktor");
        System.out.println("9. Kembali");
        System.out.println("0. Menu Utama");
        System.out.println("Masukkan Input di bawah ini:\n");
        menu2 = input.nextInt();
    }

    static void MenuPilihanInverse(){
        System.out.println("-----INVERSE:-----");
        System.out.println("-----Pilih metode di bawah ini:-----");
        System.out.println();
        System.out.println("1. Reduksi Baris (OBE)");
        System.out.println("2. Metode Kofaktor");
        System.out.println("Masukkan Input di bawah ini:\n");
        menu2 = input.nextInt();
    }

    static void MenuInput(){
        System.out.println("-----Pilih metode input di bawah ini:-----");
        System.out.println();
        System.out.println("1. Keyboard");
        System.out.println("2. File .txt");
        System.out.println("9. Kembali");
        System.out.println("0. Menu Utama");
        System.out.println("Masukkan Input di bawah ini:\n");
        menu3 = input.nextInt();
    }

    static void Keluar(){
        System.out.println("TERIMA KASIH, SELAMAT DATANG KEMBALI!");
        System.out.println("Kelompok 8 - IF 2020");
    }

    public static void main (String[] args){

        matriks M = new matriks();

        MenuUtama();
        if (menu1 == 1){
            MenuPilihanSPL();
            if (menu2 == 1){
                MenuInput();
                if(menu3 == 1){
                    M.bacaMatriksSPLGauss();
                    M.Gauss();
                    System.out.println();
                    System.out.println("Matriks SPL Setelah dilakukan metode Gauss:");
                    M.tulisMatriks();
                    System.out.println();
                    M.tulisGauss();
                }
                else if(menu3 == 2){
                    M.bacaMatriksSPLExt();
                    System.out.println("Matriks SPL Awal:");
                    M.tulisMatriks();
                    M.Gauss();
                    System.out.println();
                    System.out.println("Matriks SPL Setelah dilakukan metode Gauss:");
                    M.tulisMatriks();
                    System.out.println();
                    M.tulisGauss();
                }
                else if (menu3 == 9){
                    MenuPilihanSPL();
                }
                else if (menu3 == 0){
                    MenuUtama();
                }
                else{
                    MenuInput();
                }
            }
            else if (menu2 == 2){
                MenuInput();
                if(menu3 == 1){
                    M.bacaMatriksSPLGauss();
                    M.Gauss();
                    M.GaussJordan();
                    System.out.println();
                    System.out.println("Matriks SPL Setelah dilakukan metode Gauss-Jordan:");
                    M.tulisMatriks();
                    System.out.println();
                    M.tulisGaussJordan();
                }
                else if(menu3 == 2){
                    M.bacaMatriksSPLExt();
                    System.out.println("Matriks SPL Awal:");
                    M.tulisMatriks();
                    M.Gauss();
                    M.GaussJordan();
                    System.out.println();
                    System.out.println("Matriks SPL Setelah dilakukan metode Gauss-Jordan:");
                    M.tulisMatriks();
                    System.out.println();
                    M.tulisGaussJordan();
                }
                else if (menu3 == 9){
                    MenuPilihanSPL();
                }
                else if (menu3 == 0){
                    MenuUtama();
                }
                else{
                    MenuInput();
                }
            }
            else if (menu2 == 3){
                MenuInput();
                if(menu3 == 1){
                    M.bacaMatriksSPLGauss();
                    M.bacaMatriksBalikanSPL();
                    M.InverseMatriksSPL();
                    System.out.println();
                    System.out.println("Matriks SPL Setelah dilakukan metode Inverse:");
                    M.tulisMatriks();
                    System.out.println();
                    M.tulisInverseMatriksSPL();
                }
                else if(menu3 == 2){
                    M.bacaMatriksSPLExt();
                    System.out.println("Matriks Awal:");
                    M.tulisMatriks();
                    M.bacaMatriksBalikanSPL();
                    M.InverseMatriksSPL();
                    System.out.println();
                    System.out.println("Matriks SPL Setelah dilakukan metode Inverse:");
                    M.tulisMatriks();
                    System.out.println();
                    M.tulisInverseMatriksSPL();
                }
                else if (menu3 == 9){
                    MenuPilihanSPL();
                }
                else if (menu3 == 0){
                    MenuUtama();
                }
                else{
                    MenuInput();
                }
            }
            else if (menu2 == 4){
                MenuInput();
                if(menu3 == 1){
                    M.bacaMatriksSPLGauss();
                    M.Crammer();
                    M.tulisMatriks();
                    System.out.println();
                    M.tulisCrammer();
                }
                else if(menu3 == 2){
                    M.bacaMatriksSPLExt();
                    System.out.println("Matriks Awal untuk Crammer:");
                    M.tulisMatriks();
                    System.out.println();
                    M.Crammer();
                    System.out.println();
                    M.tulisCrammer();
                }
                else if (menu3 == 9){
                    MenuPilihanSPL();
                }
                else if (menu3 == 0){
                    MenuUtama();
                }
                else{
                    MenuInput();
                }
            }
            else if ((menu2 == 9)||(menu2 == 0)){
                MenuUtama();
            }
            else{
                MenuPilihanSPL();
            }
        }
        else if (menu1 == 2){
            MenuPilihanDeterminan();
            if (menu2 == 1){
                MenuInput();
                if (menu3 == 1){
                    M.bacaMatriksDeterminan();
                    M.bacaDeterminant();
                    System.out.println();
                    System.out.println("Determinan setelah telah dilakukan metode Reduksi:");
                    M.tulisDeterminanReduksi();
                }
                else if (menu3 == 2){
                    M.bacaFileExtDeterminan();
                    System.out.println("Matriks Awal untuk Hitung Determinan:");
                    M.tulisMatriks();
                    System.out.println();
                    M.bacaDeterminant();
                    System.out.println();
                    System.out.println("Determinan setelah telah dilakukan metode Reduksi:");
                    M.tulisDeterminanReduksi();
                }
                else if (menu3 == 9){
                    MenuPilihanDeterminan();
                }
                else if (menu3 == 0){
                    MenuUtama();
                }
                else{
                    MenuInput();
                }
            }
            else if (menu2 == 2){
                MenuInput();
                if (menu3 == 1){
                    M.bacaMatriksDeterminan();
                    System.out.println();
                    System.out.println("Determinan setelah telah dilakukan metode Kofaktor:");
                    M.tulisDeterminanKofaktor();
                }
                else if (menu3 == 2){
                    M.bacaFileExtDeterminan();
                    System.out.println("Matriks Awal untuk Hitung Determinan:");
                    M.tulisMatriks();
                    System.out.println();
                    System.out.println("Determinan setelah telah dilakukan metode Kofaktor:");
                    M.tulisDeterminanKofaktor();
                }
                else if (menu3 == 9){
                    MenuPilihanDeterminan();
                }
                else if (menu3 == 0){
                    MenuUtama();
                }
                else{
                    MenuInput();
                }
            }
            else if ((menu2 == 9)||(menu2 == 0)){
                MenuUtama();
            }
            else{
                MenuPilihanDeterminan();
            }
        }
        else if (menu1 == 3){
            MenuPilihanInverse();
            if (menu2 == 1){
                MenuInput();
                if (menu3 == 1){
                    M.bacaMatriksBalikan();
                    M.bacaInverse();
                    System.out.println();
                    System.out.println("Inverse Matriks setelah telah dilakukan metode Reduksi:");
                    M.tulisInverseReduksi();
                }
                else if (menu3 == 2){
                    //M.bacaFileExtBalikan();
                    System.out.println("Matriks Awal untuk Inverse Reduksi");
                    M.tulisMatriks();
                    System.out.println();
                    M.bacaInverse();
                    System.out.println("Inverse Matriks setelah telah dilakukan metode Reduksi:");
                    M.tulisInverseReduksi();
                }
            }
            else if (menu2 == 2){
                /*
                MenuInput();
                if (menu3 == 1){
                    M.bacaMatriksBalikan();
                    M.InverseKofaktor();
                    System.out.println();
                    System.out.println("Inverse Matriks setelah telah dilakukan metode Kofaktor:");
                    M.tulisInverseKofaktor();
                }
                else if (menu3 == 2){
                    M.bacaFileExtBalikan();
                    System.out.println("Matriks Awal untuk Inverse Kofaktor");
                    M.tulisMatriks();
                    System.out.println();
                    M.InverseKofaktor();
                    System.out.println("Inverse Matriks setelah telah dilakukan metode Kofaktor:");
                    M.tulisInverseKofaktor();
                }
                else if (menu3 == 9){
                    MenuPilihanInverse();
                }
                else if (menu3 == 0){
                    MenuUtama();
                }
                else{
                    MenuInput();
                }
                */
                
            }
            else if ((menu2 == 9)||(menu2 == 0)){
                MenuUtama();
            }
            else{
                MenuPilihanInverse();
            }
        }
        else if (menu1 == 4){
            MenuPilihanInterpolasi();
            if (menu2 == 1){
                MenuInput();
                if(menu3 == 1){
                    M.matriksInterpolasi();
                    System.out.println("Matriks Interpolasi setelah telah dilakukan metode Gauss:");
                    M.tulisInterpolasiGauss();
                    System.out.println();
                }
                else if (menu3 == 2){
                    //M.matriksInterpolasiExt();
                    System.out.println();
                    System.out.println("Matriks Interpolasi setelah telah dilakukan metode Gauss:");
                    //M.tulisInterpolasiGauss();
                    System.out.println();
                }
            }
            else if (menu2 == 2){
                MenuInput();
                /*
                if(menu3 == 1){
                    M.bacaMatriksInterpolasi();
                    System.out.println("Matriks Interpolasi setelah telah dilakukan metode Gauss-Jordan:");
                    M.tulisInterpolasiGaussJordan();
                    System.out.println();
                }
                else if (menu3 == 2){
                    M.bacaMatriksInterpolasiExt();
                    System.out.println("Matriks Awal untuk Interpolasi");
                    M.tulisMatriks();
                    System.out.println("Matriks Interpolasi setelah telah dilakukan metode Gauss-Jordan:");
                    M.tulisInterpolasiGaussJordan();
                    System.out.println();
                }
                else if (menu3 == 9){
                    MenuPilihanInterpolasi();
                }
                else if (menu3 == 0){
                    MenuUtama();
                }
                else{
                    MenuInput();
                }
                */
            }
            else if ((menu2 == 9)||(menu2 == 0)){
                MenuUtama();
            }
            else{
                MenuPilihanInterpolasi();
            }
        }
        else if (menu1 == 5){
            /*
            MenuPilihanRegresi();
            if (menu2 == 1){
                MenuInput();
                if(menu3 == 1){
                    M.bacaMatriksRegresi();
                    System.out.println("Matriks Regresi setelah telah dilakukan metode Gauss:");
                    M.tulisRegresiGauss();
                    System.out.println();
                }
                else if (menu3 == 2){
                    M.bacaMatriksRegresiExt();
                    System.out.println("Matriks Awal untuk Regresi");
                    M.tulisMatriks();
                    System.out.println();
                    System.out.println("Matriks Regresi setelah telah dilakukan metode Gauss:");
                    M.tulisRegresiGauss();
                    System.out.println();
                }
            }
            else if (menu2 == 2){
                MenuInput();
                if(menu3 == 1){
                    M.bacaMatriksRegresi();
                    System.out.println("Matriks Regresi setelah telah dilakukan metode Gauss-Jordan:");
                    M.tulisRegresiGaussJordan();
                    System.out.println();
                }
                else if (menu3 == 2){
                    M.bacaMatriksRegresiExt();
                    System.out.println("Matriks Awal untuk Regresi");
                    M.tulisMatriks();
                    System.out.println("Matriks Regresi setelah telah dilakukan metode Gauss-Jordan:");
                    M.tulisRegresiGaussJordan();
                    System.out.println();
                }
                else if (menu3 == 9){
                    MenuPilihanRegresi();
                }
                else if (menu3 == 0){
                    MenuUtama();
                }
                else{
                    MenuInput();
                }
            }
            else if ((menu2 == 9)||(menu2 == 0)){
                MenuUtama();
            }
            else{
                MenuPilihanRegresi();
            }
            */
        }
        else if (menu1 == 6){
            Keluar();
        }
        else{
            MenuUtama();
        }
    }
}