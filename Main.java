import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gp1 = new GameProgress(50, 250, 9, 136.5);
        GameProgress gp2 = new GameProgress(60, 350, 10, 236.5);
        GameProgress gp3 = new GameProgress(70, 450, 11, 336.5);
        saveGame("D:\\Games\\savegames\\gp1.dat", gp1);
        saveGame("D:\\Games\\savegames\\gp2.dat", gp2);
        saveGame("D:\\Games\\savegames\\gp3.dat", gp3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("D:\\Games\\savegames\\gp1.dat");
        arrayList.add("D:\\Games\\savegames\\gp2.dat");
        arrayList.add("D:\\Games\\savegames\\gp3.dat");
        zipFiles("D:\\Games\\savegames\\zip.zip",arrayList);
        File gp1Dat=new File("D:\\Games\\savegames\\gp1.dat");
        File gp2Dat=new File("D:\\Games\\savegames\\gp2.dat");
        File gp3Dat=new File("D:\\Games\\savegames\\gp3.dat");
        if(gp1Dat.delete()) System.out.println("Файл\"gp1.dat\"удален");
        if(gp2Dat.delete()) System.out.println("Файл\"gp2.dat\"удален");
        if(gp3Dat.delete()) System.out.println("Файл\"gp3.dat\"удален");

    }

    private static void saveGame(String path, GameProgress gp) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
