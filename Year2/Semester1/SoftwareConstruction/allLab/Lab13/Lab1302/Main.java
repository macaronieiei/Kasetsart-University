package Lab13.Lab1302;

public class Main {

    public static void main(String[] args) {

        ItemDAO dao = new ItemDAO();
        dao.findAll();
        dao.find("Plush Dog");
        // dao.find("");
        // dao.find("a");
    }
}
