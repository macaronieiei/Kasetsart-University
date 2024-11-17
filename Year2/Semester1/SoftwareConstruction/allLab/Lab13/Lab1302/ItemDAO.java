package Lab13.Lab1302;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    private static final String FILE_PATH = "D:\\KU KPS\\SoftwareConstruction\\SoftwareConstruction\\Lab13\\Lab1302\\sell.txt";

    public void save(Item i) {

        try {
            File f = new File(FILE_PATH);
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(i.getName() + "," + i.getPrice() + "," + i.getQuantity() + "\n");

            bw.close();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public List<Item> find(String name) {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            FileReader fr = new FileReader(new File(FILE_PATH));
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            try {
                while ((s = br.readLine()) != null) {
                    String[] l = s.split(",");
                    String n = l[0];
                    if (name.equals(n)) {
                        double price = Double.parseDouble(l[1]);
                        int quantity = Integer.parseInt(l[2]);
                        Item i = new Item(name, price, quantity);
                        System.out.println(i);
                        items.add(i);
                    } else {
                        continue;
                    }
                }
                System.out.println();
                br.close();
                fr.close();
            } catch (NumberFormatException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return items;
    }

    public List<Item> findAll() {

        ArrayList<Item> items = new ArrayList<Item>();

        try {
            FileReader fr = new FileReader(new File(FILE_PATH));
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine()) != null) {
                String[] l = s.split(",");
                if (l.length < 3)
                    continue;
                String name = l[0];
                double price = Double.parseDouble(l[1]);
                int quantity = Integer.parseInt(l[2]);
                Item i = new Item(name, price, quantity);
                System.out.println(i);
                items.add(i);
            }
            System.out.println();
            br.close();
            fr.close();
        } catch (NumberFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return items;

    }

    public void delete() {
        File f = new File(FILE_PATH);
        f.delete();
    }

}
