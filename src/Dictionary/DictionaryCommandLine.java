package Dictionary;
import java.util.NoSuchElementException;

public class DictionaryCommandLine {
    /**
     * In danh sách các từ.
     */
    public static void showAllWords() {
        if (Dictionary.listWord == null) {
            System.out.println("Chưa có dữ liệu");
        }
        int count = 1;
        for (Word s : Dictionary.listWord) {
                System.out.println(count++);
                System.out.print(s);
        }
    }

    public static void dictionaryStart() {
        DictionaryManagement.insertFromFile();
    }

    public static void dictionaryFinish() throws NoSuchElementException {
        DictionaryManagement.updateData();
    }
}
