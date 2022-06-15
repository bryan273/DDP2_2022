import java.util.Scanner;
import java.util.Arrays;

public class Lab03 {
    // Membuat instance variabel
    static int pointer = 0;
    static String[][] playlist = new String[1][4];
    static Scanner in = new Scanner(System.in);

    // Method main untuk memutar dan memodifikasi lagu
    public static void main(String[] args) {
        System.out.println("Selamat Datang di Pacilfy!");
        int lanjut;

        // Loop untuk menerima data lagu
        do{
            addMusic();
            System.out.print("Lanjut menambahkan lagu?\n"+
                            "[1] Lanjut\n"+"[0] Berhenti\n"+
                            "Perintah: ");   
            lanjut = Integer.parseInt(in.nextLine());;
        } while(lanjut == 1);

        System.out.println("Pacilfy siap dimulai");
               
        System.out.println("\nSELAMAT DATANG DI\n");
        System.out.println(" /$$$$$$$                     /$$ /$$  /$$$$$$");
        System.out.println("| $$__  $$                   |__/| $$ /$$__  $$");
        System.out.println("| $$  \\ $$ /$$$$$$   /$$$$$$$ /$$| $$| $$  \\__//$$   /$$");
        System.out.println("| $$$$$$$/|____  $$ /$$_____/| $$| $$| $$$$   | $$  | $$");
        System.out.println("| $$____/  /$$$$$$$| $$      | $$| $$| $$_/   | $$  | $$");
        System.out.println("| $$      /$$__  $$| $$      | $$| $$| $$     | $$  | $$");
        System.out.println("| $$     |  $$$$$$$|  $$$$$$$| $$| $$| $$     |  $$$$$$$");
        System.out.println("|__/      \\_______/ \\_______/|__/|__/|__/      \\____  $$");
        System.out.println("                                               /$$  | $$");
        System.out.println("                                              |  $$$$$$/");
        System.out.println("                                               \\______/");

        // Menerima command yang diminta user
        int command = 1;
        while (true){
            display();
            System.out.print("Command (0 untuk exit) : ");
            command = Integer.parseInt(in.nextLine());
            if (command == 1){
                prevMusic();
            }
            else if (command == 2){
                addMusic();
            }
            else if (command == 3){
                detailsMusic();
            }
            else if (command == 4){
                deleteMusic();
            }
            else if (command == 5){
                nextMusic();
            }
            else if (command == 0){
                break;
            }
            else {
                System.out.println("Maaf, command yang anda masukan salah");
            }
        }
        
        System.out.println("Terima kasih sudah menggunakan Pacilfy!");
        in.close();
    }

    // Method untuk memutar lagu berikutnya
    private static void nextMusic() {
        if (pointer < playlist.length-2)
            pointer++;
        else
            pointer=0;
    }

    // Method untuk delete lagu
    private static void deleteMusic() {

        // Kasus tidak valid bila lagu yang ingin didelete = 1 (2 karena 1 belakangnya null)
        if (playlist.length==2){
            System.out.println("Minimal ada satu musik dalam sistem");
            return;
        }

        // Split array jadi 2 tanpa elemen yang tidak ingin dipakai lagi
        String[][] leftArr = Arrays.copyOfRange(playlist, 0, pointer);
        String[][] rightArr = Arrays.copyOfRange(playlist, pointer+1, playlist.length);
        int len = leftArr.length + rightArr.length;

        // Merge array lagi
        playlist = new String[len][4];
        System.arraycopy(leftArr, 0, playlist, 0, leftArr.length);
        System.arraycopy(rightArr, 0, playlist, leftArr.length, rightArr.length);

        // Bila yang di delete lagu paling akhir , maka lanjut lagu paling awal
        if (pointer==playlist.length-1)
            nextMusic();
    }

    // Method untuk mencari detail lagu
    private static void detailsMusic() {
        // Menerima input lagu yang ingin dicari
        System.out.print("Judul yang ingin dicari: ");
        String searched = in.nextLine().toLowerCase();

        // Membuat array untuk judul lagu saja
        String[] judulArr = new String[playlist.length-1];
        for (int i=0; i<playlist.length-1; i++){
            judulArr[i] = playlist[i][0].toLowerCase();
        }

        // Mencari index lagu yang ingin dicari
        int idxSearch = Arrays.binarySearch(judulArr, searched);

        // Jika yang ingin dicari ada di data , maka memberi output data lagu
        if (idxSearch >=0){
            String[] dataSearch = playlist[idxSearch];

            System.out.println("Data lagu:");
            System.out.print("Judul : "+dataSearch[0]+
                            "\nArtist: "+dataSearch[1]+
                            "\nAlbum : "+dataSearch[2]+
                            "\nTahun : "+dataSearch[3]+"\n");
        }
        // Kasus lagu tidak ditemukan pada data
        else
            System.out.println("Lagu tidak ditemukan");
    }

    // Method untuk memutar lagu sebelumnya
    private static void prevMusic() {
        if (pointer == 0)
            pointer = playlist.length-2;
        else
            pointer--;
    }

    // Method untuk menambahkan data musik
    private static void addMusic() {
        System.out.println("Silahkan masukkan lagu Anda");
        System.out.print("Judul : ");
        String judul = in.nextLine();

        System.out.print("Artist: ");
        String artist = in.nextLine();

        System.out.print("Album : ");
        String album = in.nextLine();

        System.out.print("Tahun : ");
        String tahun = in.nextLine();

        // Menambahkan panjang array +1 setiap kali data bertambah 
        int panjang = playlist.length;
        playlist[panjang-1] = new String[]{judul, artist, album, tahun};
        playlist = Arrays.copyOf(playlist, panjang+1);
    }

    // Method untuk menampilkan lagu yang diputar dan tombol lagu
    private static void display() {
        System.out.println();
        System.out.println("Currently Playing");

        String displayedMusic = " " + playlist[pointer][1] + " - " + playlist[pointer][0] + " ";
        String command = "|[1] prev |[2] add music |[3] details |[4] delete music |[5] next|";

        if (displayedMusic.length() < command.length()){
            int width = 62;
            String s = displayedMusic;

            int padSize = width - s.length();
            int padStart = s.length() + padSize / 2;

            s = String.format("%" + padStart + "s", s);
            s = String.format("%-" + width  + "s", s);


            System.out.println(new String(new char[66]).replace("\0", "="));
            System.out.println("= "+ s +" =");
            System.out.println(new String(new char[66]).replace("\0", "="));
            System.out.println(command);

            return;
        }

        System.out.println("=" + new String(new char[displayedMusic.length()]).replace("\0", "=") + "=");
        System.out.println("=" + displayedMusic + "=");
        System.out.println("=" + new String(new char[displayedMusic.length()]).replace("\0", "=") + "=");
        System.out.println(command);
    }
    
}