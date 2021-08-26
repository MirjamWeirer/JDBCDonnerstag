public class DonnerstagDB {
    public static void main(String[] args) {
        String fileName = "Donnerstag.db";
        DBHelper db = new DBHelper();
        db.createNewDatabase(fileName);
        db.connect(fileName);
        db.createGameTable(fileName);

        System.out.println("Finished - Table was created");
    }
}
