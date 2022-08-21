package Dictionary;
import java.util.NoSuchElementException;
public class Main {
    public static void main(String[] args) throws NoSuchElementException {
        DictionaryCommandLine.dictionaryStart();
        DictionaryManagement.insertFromCommandLine();
        DictionaryCommandLine.dictionaryFinish();
    }
}