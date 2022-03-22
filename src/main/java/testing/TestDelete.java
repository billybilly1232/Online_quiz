package testing;

import csc1035.project2.Database;

public class TestDelete {
    public static void main(String[] args) {
        Database d = new Database();
        d.deleteQuestion(115);
    }
}
