package Dictionary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static Dictionary.Dictionary.*;

public class DictionaryManagement {
    private static String notification = "";

    public static String getNotification() {
        return notification;
    }

    /**
     * Nhập từ bàn phím n từ, với n nhập từ bàn phim, nhập nghĩa kết thúc khi gõ 2 dấu enter.
     */
    public static void insertFromCommandLine() {
        notification = "";
        System.out.println("Enter the number of words you wanna add:");
        int n = Validation.inputInteger(0, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            Word word = new Word();
            Scanner sc = new Scanner(System.in);
            System.out.println("Add word-number(" + (i + 1) + ")'s target");
            String s = sc.nextLine().trim();
            word.setTarget(s);
            System.out.println("Add word-number(" + (i + 1) + ")'s explain. Double enter to finish!");
            s = Validation.inputString();
            word.setExplain(s);
            listAdded.add(0, word);
        }
    }

    /**
     * thêm dữ liệu từ file vào list tương ứng.
     *
     * @param list tên list
     * @param filename tên file
     */
    private static void insertToList(ArrayList<Word> list, String filename) {
        notification = "";
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                Word word = new Word();
                if (s.contains("#")) {
                    word.setTarget(s.substring(1, s.indexOf("#")));
                    word.setPronunciation(s.substring(s.indexOf("#") + 1));
                } else if (s.contains("/")) {
                    word.setTarget(s.substring(1, s.indexOf("/")));
                    word.setPronunciation(s.substring(s.indexOf("/")));
                } else {
                    word.setTarget(s.substring(1));
                }
                String add = sc.nextLine();
                s = "";
                while (!add.equals("")) {
                    s = s.concat(add + "\n");
                    if (sc.hasNextLine()) {
                        add = sc.nextLine();
                    } else {
                        break;
                    }
                }
                word.setExplain(s.trim());
                list.add(word);
            }
            sc.close();
        } catch (IOException e) {
            notification = " Some errors appear when trying to read data!";
        }
    }

    /**
<<<<<<< HEAD
     * NHập từ vào Dictionary.listWord (lưu trữ các từ vào list để đọc).
=======
     * NHập từ vào Dictionary.listWord (lưu trữ các từ vào list để đọc).
>>>>>>>
     */
    public static void insertFromFile() {
        notification = "";
        insertToList(listWord, fileData);
        insertToList(listAdded, fileListAdded);
        insertToList(listDeleted, fileListDeleted);
        insertToList(listModified, fileListModified);
        insertToList(listRecentWord, fileListRecentWord);
    }

    /**
     * tra từ lần lượt theo các nguyên tắc:
     * + Nếu từ trong danh sách từ đã xóa, chỉ hiển thị nghĩa của tôi
     * + Hiển thị từ trong danh sách "Từ của tôi" trước
     * + Hiển thị nghĩa chỉnh sửa nếu có (được lưu trong listModified)
     * + Nếu từ không được lưu trong listModified: hiển thị từ lưu trong dữ liệu - tức file dictionaries.
     * + Nếu từ không có trong dữ liệu, in ra thông báo.
     *
     * @param target từ cần tra
     * @return từ cần tra.
     */
    public static Word dictionaryLookup(String target) {
        notification = "";
        String s = "";
        Word word;
        if (target.equals("")) {
            notification = "Enter a word!";
            return null;
        }
        if ((word = Lookup(target, listAdded)) != null) {
            s = word.getExplain();
        }
        if ((Lookup(target, listDeleted)) != null) {
            notification = " This is a deleted word\n";
            if (!s.equals("")) {
                notification += "* Explain in your Add-word list:\n";
                return new Word(target, null, s);
            }
            return null;
        } else if ((word = Lookup(target, listModified)) != null) {
            if (s.equals("")) {
                return word;
            } else {
                return new Word(target, null, "* Explain in your Add-word list:\n"
                        + s + "\n\n***************\n\n" + word.getExplain());
            }
        } else if ((word = Lookup(target, listWord)) != null) {
            if (s.equals("")) {
                return word;
            } else {
                return new Word(target, null, "* Explain in your Add-word list:\n"
                        + s + "\n\n***************\n\n" + word.getExplain());
            }
        } else if (! s.equals("")) {
            return new Word(target, null, "* Explain in your Add-word list:\n" + s);
        }
        notification = " There is no data\n";
        return null;
    }

    /**
     * trả về danh sách từ gợi ý, nếu từ không chứa trong dữ liệu, gợi ý các từ gần đó.
     *
     * @param target từ cần tra
     * @return danh sách gợi ý
     */
    public static ArrayList<String> dictionarySearch(String target) {
        notification = "";
        ArrayList<String> listSuggestion = new ArrayList<>();
        target = target.trim();
        if (target.equals("")) {
            notification = " Enter a word!\n";
            for (Word word : listRecentWord) {
                listSuggestion.add(word.getTarget());
            }
            return listSuggestion;
        }
        int count = 0;
        Word word;
        int flag = findFlag(target, listWord);
        int length = listWord.size();
        if (flag == -1) {
            notification = " There is no data!\n";
        } else {
            while (count < 30 && count + flag < length) {
                String temp = listWord.get(flag + count).getTarget();
                if (temp.startsWith(target)) {
                    listSuggestion.add(temp);
                    count++;
                } else {
                    count = 30;
                }
            }
        }
        if ((word = Lookup(target, listAdded)) != null) {
            listSuggestion.add(word.getTarget());
        }
        listSuggestion.removeIf(str -> Lookup(str, listDeleted) != null);
        return listSuggestion;
    }

    /**
     * Điều chỉnh từ lần lượt theo các nguyên tắc:
     * + Nếu từ đã chỉnh sửa trước đây (nằm trong listModified) thì chỉnh sửa phần giải thích trong listModified
     * + Nếu từ chưa từng chỉnh sửa, thêm nghĩa mới cho từ và thêm từ đó vào trong listModified.
     * + Nếu không có trong danh sách chỉnh sửa hoặc trong dữ liệu thì thông báo "Chưa có dữ liệu về từ này!"
     *
     * @param target từ cần điều chỉnh
     */
    public static void modifyWord(String target, String explain) {
        notification = "";
        Word word = Lookup(target.trim(), listDeleted);
        if ( word != null ) {
            notification = " The word's has been deleted, remove it form Deleted-word list before!\n";
        } else if ((word = Lookup(target.trim(), listModified)) != null) {
            notification = " This word had been in Modified-word list. Exchanged its explain!\n";
            word.setExplain(explain.trim());
            updateDataOfList(listModified, fileListModified);
            notification = " The word's explain has been modified!\n";
        } else {
            word = Lookup(target.trim(), listWord);
            if (word != null) {
                listModified.add(0, new Word(target.trim(), null, explain.trim()));
                updateDataOfList(listModified, fileListModified);
                notification = " The word's explain has been modified!\n";
            } else {
                notification = " There is no data!\n";
            }
        }
    }

    /**
     * Xóa từ theo nguyên tắc:
     * + Nếu là từ đã xóa thì thông báo cho người dùng, kết thức hàm.
     * + Nếu là từ trong danh sách chỉnh sửa thì xóa từ đó khỏi danh sách chỉnh sửa.
     * + Nếu từ đó chỉ cỏ trong dữ liệu ( file dictionaries ) thì thêm từ đó vào danh sách từ đã xóa
     * -> khi tìm kiếm dựa vào dánh sách này để không hiển thị nữa
     *
     * @param target từ cần xóa
     */
    public static void deleteWord(String target) {
        notification = "";
        Word word = Lookup(target.trim(), listDeleted);
        if (word != null) {
            notification = " This word was deleted. View more in \"Deleted-word\" list\n";
        } else if ((word = Lookup(target.trim(), listModified)) != null) {
            listModified.remove(word);
            updateDataOfList(listDeleted, fileListDeleted);
            notification = " The word has been removed from \"Modified-word\" list\n";
        } else if ((word = Lookup(target.trim(), listWord)) != null) {
            listDeleted.add(0, word);
            updateDataOfList(listDeleted, fileListDeleted);
            notification = " Word has been deleted\n";
        } else {
            notification = " There is no data!\n";
        }
    }

    /**
     * thêm từ vào trong listAdded
     * + Từ đã được thêm trước đây: sửa nghĩa
     * + Từ chưa được thêm : thêm từ.
     * + Từ trong listAdded khi tra cứu thì nghĩa sẽ được hiển thị cùng nghĩa của dữ liệu.
     *
     * @param target từ mới
     */
    public static void addWord(String target, String explain) {
        notification = "";
        Word word;
        if ((word = Lookup(target.trim(), listAdded)) != null) {
            System.out.println(" Overwritten explain for \"" + target.trim() + "\" !");
            word.setExplain(explain.trim());
            listAdded.remove(word);
            listAdded.add(0,word);
            updateDataOfList(listAdded, fileListAdded);
            notification = " The word's explain has been overwritten!\n";
        } else {
            listAdded.add(0, new Word(target.trim(), null, explain.trim()));
            updateDataOfList(listAdded, fileListAdded);
            notification = " The word has been added to Added-word list!\n";
        }
    }

    /**
     * ghi đè ra 3 file text lưu dữ liệu chỉnh sửa của người dùng.
     * @param list danh sách chỉnh sửa
     * @param filename file lưu
     */
    public static void updateDataOfList(ArrayList<Word> list, String filename) {
        notification = "";
        try {
            if (!(list == listModified || list == listAdded
                    || list == listDeleted || list == listRecentWord)) {
                System.err.print("Can only update fileListModified, fileListAdded or fileListDeleted");
            } else {
                File file = new File(filename);
                FileWriter writer = new FileWriter(file);
                PrintWriter out = new PrintWriter(writer);
                for (Word e : list) {
                    out.print("@" + e.getTarget());
                    if (e.getPronunciation() != null) {
                        out.print("#" + e.getPronunciation());
                    }
                    out.println("\n" + e.getExplain() + "\n");
                }
                out.close();
            }
        } catch (IOException e) {
            notification = " Unable to overwrite file!";
        }
    }

    /**
     * cập nhật lại dữ liệu người dùng trước khi tắt chương trình.
     */
    public static void updateData() {
        notification = "";
        updateDataOfList(listAdded, fileListAdded);
        updateDataOfList(listDeleted, fileListDeleted);
        updateDataOfList(listModified, fileListModified);
        updateDataOfList(listRecentWord, fileListRecentWord);
    }
}
