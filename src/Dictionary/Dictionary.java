package Dictionary;

import java.util.ArrayList;

public class Dictionary {
    public static final String fileListModified = "listModified.txt";
    public static final String fileListDeleted = "listDeleted.txt";
    public static final String fileListAdded = "listAdded.txt";
    public static final String fileListRecentWord = "listRecentWord.txt";

    public static final String fileData = "dictionaries.txt";
    /**
     * listWord: lưu danh sách các từ trong file dữ liệu chuẩn.
     */
    public static ArrayList<Word> listWord = new ArrayList<>();

    /**
     * listModified: lưu danh sách các từ đã chỉnh sửa.
     */
    public static ArrayList<Word> listModified = new ArrayList<>();

    /**
     * listDeleted: lưu danh sách từ đã xóa.
     */
    public static ArrayList<Word> listDeleted = new ArrayList<>();

    /**
     * listAdded: lưu danh sách những từ thêm vào.
     */
    public static ArrayList<Word> listAdded = new ArrayList<>();

    /**
     * Lưu danh sách các từ đã tra gần đây.
     */
    public static  ArrayList<Word> listRecentWord = new ArrayList<>();

    /**
     * lưu tối đa 30 từ gần nhất cho listRecentWord.
     * @param word Từ muốn thêm vào danh sách từ đã tra gần đây.
     */
    public static void addWordToListRecent(Word word) {
        Word t;
        if ((t = Lookup(word.getTarget(), listRecentWord)) != null) {
            listRecentWord.remove(t);
            listRecentWord.add(0, word);
        } else {
            int count = listRecentWord.size();
            if (listRecentWord.size() > 30) {
                listRecentWord.remove(count - 1);
                listRecentWord.add(word);
            } else {
                listRecentWord.add(word);
            }
        }
    }

    public static String listRecentToString() {
        if (listRecentWord.size() == 0) {
            return "Bạn chưa tra từ nào!";
        }
        String s ="";
        for (Word e: listRecentWord) {
            s += e.getTarget() + "\n" + "-> " +
                    e.getExplain().replaceAll("=", "    • ").replaceAll("\\+", ":")
                    + "\n\n";
        }
        return s;
    }

    /**
     * trả về bản ghi danh sách từ đã chỉnh sửa.
     * @return .
     */
    public static String listModifiedToString() {
        if (listModified.size() == 0) {
            return "Chưa có từ đã sửa";
        }
        String s ="";
        for (Word e: listModified) {
            s += e.getTarget() + "\n" + "-> " + e.getExplain() + "\n\n";
        }
        return s;
    }

    /**
     * bản ghi danh sách từ đã xóa.
     * @return .
     */
    public static String listDeletedToString() {
        if (listDeleted.size() == 0) {
            return "Bạn chưa xóa từ nào!";
        }
        String s ="";
        for (Word e: listDeleted) {
            s += e.getTarget() + "\n" + "-> " + e.getExplain() + "\n\n";
        }
        return s;
    }

    /**
     * bản ghi danh sách từ đã thêm.
     * @return .
     */
    public static String listAddedToString() {
        if (listAdded.size() == 0) {
            return "Bạn chưa thêm từ nào!";
        }
        String s ="";
        for (Word e: listAdded) {
            s += e.getTarget() + "\n" + "-> " + e.getExplain() + "\n\n";
        }
        return s;
    }

    /**
     * tìm TỪ (trả về word) trong 1 list cho trước
     * dùng TÌM KIẾM NHỊ PHÂN với dữ liệu từ file dictionaries.txt
     * dùng tìm kiếm tuần tự đối với các list khác, do ưu tiên các từ được thêm vào gần đây trước.
     * @param target từ cần tìm
     * @param list list tìm kiếm từ.
     * @return word
     */
    public static Word Lookup(String target, ArrayList<Word> list) {
        if (list == Dictionary.listWord) {
            int left = 0;
            int right = list.size() - 1;
            int mid;
            while (left <= right) {
                mid = (left + right) / 2;
                if (list.get(mid).getTarget().compareTo(target) == 0) {
//                System.out.println("vi tri: " + mid);
                    return list.get(mid);
                } else if (list.get(mid).getTarget().compareTo(target) > 0) {
                    right = mid - 1;
                } else if (list.get(mid).getTarget().compareTo(target) < 0) {
                    left = mid + 1;
                }
            }
            return null;
        } else {
            for (Word word : list) {
                if (word.getTarget().equals(target.trim())) {
                    return word;
                }
            }
        }
        return null;
    }

    /**
     * tìm VỊ TRÍ từ trong 1 list cho trước , dùng tìm kiếm nhị phân.
     * nếu không có từ đó, trả về vị trí BÉ NHẤT chứa nó
     * nếu không có từ chữa nó, trả về -1.
     * @param target từ cần tìm
     * @param list list tìm kiếm từ.
     * @return vị trí
     */
    public static int findFlag(String target, ArrayList<Word> list) {
        int left = 0;
        int right = list.size() - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (list.get(mid).getTarget().startsWith(target)) {
                while (mid > 0) {
                    mid = mid - 1;
                    if (! list.get(mid).getTarget().startsWith(target)) {
                        return mid + 1;
                    }
                }
                if (mid == 0) return 0;
            } else if (list.get(mid).getTarget().compareTo(target) > 0) {
                right = mid - 1;
            } else if (list.get(mid).getTarget().compareTo(target) < 0) {
                left = mid + 1;
            }
        }
        return -1;
    }
}