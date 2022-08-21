package GUI;

import Dictionary.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionListener;

public class GUI extends JFrame implements ActionListener{
    // panel
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel btnPanel = new JPanel();
    Rectangle frameForPanel = new Rectangle();
    JPanel defineWordPanel = new JPanel();
    JPanel textFieldPanel = new JPanel();

    // button
    JButton searchBtn = new JButton("Search");
    JButton addBtn = new JButton();
    JButton removeBtn = new JButton();
    JButton modifyBtn = new JButton();
    JButton speakBtn = new JButton();
    JButton recentBtn = new JButton();
    JButton ggTranslate = new JButton();

    // ListSuggest
    static JList<Object> listSuggest = new JList<>();
    JScrollPane scroll = new JScrollPane(listSuggest);

    // Define text Area;
    JTextArea textWordExplain = new JTextArea();
    JTextArea textWordTarget = new JTextArea();
    JScrollPane scrollWordExplain = new JScrollPane(textWordExplain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    JPanel defineAreaPanel = new JPanel();

    // Label Dictionary & Label Definition
    JLabel dictionaryLabel = new JLabel("Dictionary");
    JLabel defineLabel = new JLabel("Definition");
    JLabel uet = new JLabel("");
    JLabel fet = new JLabel("");
    JLabel decor = new JLabel("");

    // TextField
    JTextField wordTf = new JTextField();

    // Data
    static ArrayList<String> resultAfterSearch = new ArrayList<>();

    /**
     * Cài đăt các Panel.
     */
    private void setupPanel() {
        // Setup UI
        Color backgroundList = new Color(222,218,218);
        // Main Panel
        this.panel.setBounds(frameForPanel);
        this.panel.setLayout(null);
        this.panel.setBackground(backgroundList);

        Icon decorator = new ImageIcon("Resources/Icon/Imagedecor.png");
        this.decor.setBounds(10, 185,  370, 675);
        this.decor.setIcon(decorator);

        this.scroll.setBounds(10, 160, 379, 740);
        this.scroll.setOpaque(false);
        this.scroll.setBorder(BorderFactory.createEmptyBorder());
        this.scroll.setVisible(true);

        this.panel.add(scroll);
        this.panel.add(scrollWordExplain);
        this.panel.add(textWordTarget);
        this.panel.add(textFieldPanel);
        this.panel.add(defineAreaPanel);
        this.panel.add(ggTranslate);
        this.panel.add(decor);

        Icon dictIcon = new ImageIcon("Resources/Icon/icons8-dictionary-64.png");
        this.dictionaryLabel.setForeground(Color.white);
        this.dictionaryLabel.setFont(new Font("Times New Roman",Font.ITALIC,23));
        this.dictionaryLabel.setBounds(130,10,200,35);
        this.dictionaryLabel.setIcon(dictIcon);
        this.panel.add(dictionaryLabel);

        Color background1 = new Color(41, 162, 162);
        this.btnPanel.setBounds(0,0, 400, 90);
        this.btnPanel.setBackground(background1);
        this.btnPanel.add(searchBtn);
        this.btnPanel.add(addBtn);
        this.btnPanel.add(removeBtn);
        this.btnPanel.add(modifyBtn);
        this.btnPanel.add(recentBtn);
        this.btnPanel.setLayout(null);

        Icon menuIcon = new ImageIcon("Resources/Icon/icons8-menu-24.png");
        this.defineLabel.setForeground(Color.white);
        this.defineLabel.setFont(new Font("Times New Roman",Font.ITALIC,23));
        this.defineLabel.setBounds(10, 60, 220, 20);
        this.defineLabel.setIcon(menuIcon);

        Icon logoUET = new ImageIcon("Resources/Icon/logoUET.png");
        this.uet.setBounds(570, 15,  65, 60);
        this.uet.setIcon(logoUET);

        Icon logoFet = new ImageIcon("Resources/Icon/logoFET.png");
        this.fet.setBounds(650, 15,  110, 60);
        this.fet.setIcon(logoFet);

        Color background2 = new Color(19, 191, 181);
        this.defineWordPanel.setLayout(null);
        this.defineWordPanel.setBounds(400,0,800,90);
        this.defineWordPanel.setBackground(background2);
        this.defineWordPanel.add(defineLabel);
        this.defineWordPanel.add(uet);
        this.defineWordPanel.add(fet);
        this.panel.add(btnPanel);
        this.panel.add(defineWordPanel);

        this.frame.add(panel, BorderLayout.CENTER);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle("My Dictionary");
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.setSize(1200,960);
    }

    /**
     * Cài đặt vị trí cho các nút bấm: thêm, sửa, xóa, gần đây.
     */
    private void setupButton() {
        Icon addIcon = new ImageIcon("Resources/Icon/icons8-add-30.png");
        Icon removeIcon = new ImageIcon("Resources/Icon/icons8-cancel-delete-48.png");
        Icon modifyIcon = new ImageIcon("Resources/Icon/icons8-edit-30.png");
        Icon searchIcon = new ImageIcon("Resources/Icon/icons8-search-64.png");
        Icon recentIcon = new ImageIcon("Resources/Icon/recent.png");
        Icon ggTranslateIcon = new ImageIcon("Resources/Icon/gg.png");

        Color background1 = new Color(41, 162, 162);
        this.searchBtn.setBounds(50, 50, 130, 40);
        this.searchBtn.setBorderPainted(false);
        this.searchBtn.setFont(new Font("Times New Roman", Font.BOLD,20));
        this.searchBtn.setForeground(Color.white);
        this.searchBtn.setBackground(background1);
        this.searchBtn.setIcon(searchIcon);
        this.searchBtn.addActionListener(this.searchBtnListener);

        this.removeBtn.setBounds(180, 50, 40, 40);
        this.removeBtn.setBorderPainted(false);
        this.removeBtn.setIcon(removeIcon);
        this.removeBtn.setBackground(background1);
        this.removeBtn.addActionListener(this.removeBtnListener);

        this.modifyBtn.setBounds(220, 50, 40, 40);
        this.modifyBtn.setBorderPainted(false);
        this.modifyBtn.setIcon(modifyIcon);
        this.modifyBtn.setBackground(background1);
        this.modifyBtn.addActionListener(this.modifyBtnListener);

        this.recentBtn.setBounds(260, 50, 40, 40);
        this.recentBtn.setBorderPainted(false);
        this.recentBtn.setIcon(recentIcon);
        this.recentBtn.setBackground(background1);
        this.recentBtn.addActionListener(this.recentBtnListener);

        this.addBtn.setBounds(300, 50, 40, 40);
        this.addBtn.setBorderPainted(false);
        this.addBtn.setIcon(addIcon);
        this.addBtn.setBackground(background1);
        this.addBtn.addActionListener(this.addBtnListener);

        this.ggTranslate.setBounds(355, 102, 45, 45);
        this.ggTranslate.setBorderPainted(false);
        this.ggTranslate.setIcon(ggTranslateIcon);
        this.ggTranslate.setBackground(new Color(222,218,218));
        this.ggTranslate.addActionListener(this.translateListener);
    }

    /**
     * Cài đặt cho các vùng text và danh sách gợi ý
     */
    private void setupWordUI() {

        Color backgroundBorder = new Color(175,0,0);
        this.wordTf.setBounds(2,2,335,46);
        this.wordTf.setFont(new Font("Times New Roman", Font.PLAIN,22));
        this.textFieldPanel.setBackground(backgroundBorder);
        this.textFieldPanel.setLayout(null);
        this.textFieldPanel.setBounds(10,100,340,50);
        this.textFieldPanel.add(wordTf);

        Color backgroundList = new Color(222,218,218);
        listSuggest.setBackground(backgroundList);
        listSuggest.setBounds(0,160,400,800);
        listSuggest.setFixedCellWidth(300);
        listSuggest.setFixedCellHeight(50);
        listSuggest.setFont(new Font("Times New Roman", Font.PLAIN,22));
        listSuggest.addListSelectionListener(this.selectedItemListener);

        this.defineAreaPanel.setLayout(null);
        this.defineAreaPanel.setBounds(400,90,800,820);
        this.defineAreaPanel.setBackground(Color.white);

        Icon speakIcon = new ImageIcon("Resources/Icon/Thin-L.png");
        this.speakBtn.setIcon(speakIcon);
        this.speakBtn.setBounds(710, 20, 40, 40);
        this.speakBtn.setBorderPainted(false);
        this.speakBtn.addActionListener(this.speakBtnListener);
        this.speakBtn.setVisible(false);
        this.defineAreaPanel.add(speakBtn);

        this.textWordTarget.setLayout(null);
        this.textWordTarget.setEditable(false);
        this.textWordTarget.setBackground(Color.white);
        this.textWordTarget.setBounds(400,115,710,45);
        this.textWordTarget.setFont(new Font("Times New Roman",3,26));

        this.textWordExplain.setLayout(null);
        this.textWordExplain.setEditable(false);
        this.textWordExplain.setBackground(Color.white);
        this.textWordExplain.setBounds(400,160,800,750);
        this.textWordExplain.setFont(new Font("Times New Roman",Font.ITALIC,22));
        textWordExplain.setWrapStyleWord(true);
        textWordExplain.setLineWrap(true);

        this.scrollWordExplain.setBounds(400,160,785,750);
        this.scrollWordExplain.setBackground(Color.white);
        this.scrollWordExplain.setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * Khởi tạo cửa sổ.
     */
    public GUI() {
        setupWordUI();
        setupButton();
        setupPanel();
        setListWord();
    }

    /**
     * Chạy ứng dụng.
     * @param args .
     */
    public static void main(String[] args) {
        try {
            DictionaryManagement.insertFromFile();
            new GUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tạo danh sách từ gọi ý, nếu không gõ gì thì hiện danh sách tra gần đây.
     */
    private void setListWord() {
        wordTf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent event) {
                //
            }
            @Override
            public void keyReleased(KeyEvent event) {
                scroll.setVisible(true);
                resultAfterSearch = DictionaryManagement.dictionarySearch(wordTf.getText().trim());
                if (resultAfterSearch.size() > 0) {
                    listSuggest.setListData(resultAfterSearch.toArray());
                } else {
                    scroll.setVisible(false);
                    listSuggest.clearSelection();
                }
            }
            @Override
            public void keyPressed(KeyEvent event) {
                //
            }
        });
    }

    /**
     * Action: Dịch câu/văn bản.
     */
    ActionListener translateListener = ggTranslate -> {
        if (! wordTf.getText().trim().equals("")) {
            String s = GoogleAPI.translate(wordTf.getText().trim());
            textWordTarget.setText("Bản dịch:");
            Word word = DictionaryManagement.dictionaryLookup(wordTf.getText().trim());
            if (word != null) {
                s +=  "\nExplain in \"My Dictionary\": \n\n" + word;
            }
            textWordExplain.setText(s);
        }
    };

    /**
     * Action: Hiển thị danh sách từ gần đây.
     */
    ActionListener recentBtnListener = showRecentWord -> {
        scroll.setVisible(false);
        wordTf.setText("");
        listSuggest.clearSelection();
        textWordTarget.setText("Các từ gần đây: ");
        textWordExplain.setText(Dictionary.listRecentToString());
    };

    /**
     * Action: Thêm từ nếu nút add được nhấn.
     */
    ActionListener addBtnListener = add -> {
        speakBtn.setVisible(false);
        listSuggest.clearSelection();
        scroll.setVisible(false);
        wordTf.setText("");
        textWordTarget.setText("Danh sách từ đã thêm:");
        textWordExplain.setText(Dictionary.listAddedToString());
        MyDialog addDialog = new MyDialog("addWord");
    };


    /**
     * Action: Sửa từ đang tra nếu nút sửa được ấn, nếu không tra từ nào thì hiện danh sách đã sửa.
     */
    ActionListener modifyBtnListener = modify -> {
        speakBtn.setVisible(false);
        int selectedItem = listSuggest.getSelectedIndex();
        if (selectedItem >= 0) {
            if (resultAfterSearch.get(selectedItem) != null) {
                MyDialog editDialog = new MyDialog("modifyWord");
            }
        } else {
            textWordTarget.setText("Danh sách từ đã chỉnh sửa:");
            wordTf.setText("");
            textWordExplain.setText(Dictionary.listModifiedToString());
            listSuggest.clearSelection();
            scroll.setVisible(false);
        }
    };

    /**
     * Action: Xóa từ đang tra, nếu không có từ đang tra thì hiện danh sách các từ đã tra.
     */
    ActionListener removeBtnListener = remove -> {
        speakBtn.setVisible(false);
        int selectedItem = listSuggest.getSelectedIndex();
        if (selectedItem != -1 && resultAfterSearch.size() > 0) {
            Object[] options = {"Có", "Không"};
            String message =  "Bạn có chắc chắn muốn xoá từ \"" + resultAfterSearch.get(selectedItem) + "\" không?";
            int n = JOptionPane.showOptionDialog(frame,
                    message,
                    "A Silly Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == JOptionPane.OK_OPTION) {
                System.out.println("OKE");
                DictionaryManagement.deleteWord(resultAfterSearch.get(selectedItem));
                resultAfterSearch.remove(selectedItem);
                listSuggest.setListData(resultAfterSearch.toArray());
            }
        } else {
            scroll.setVisible(false);
            wordTf.setText("");
            listSuggest.clearSelection();
            textWordTarget.setText("Danh sách từ đã xóa:");
            textWordExplain.setText(Dictionary.listDeletedToString());
        }
    };

    /**
     * Action: Phát âm.
     */
    ActionListener speakBtnListener = speak -> {
        int selectedItem = listSuggest.getSelectedIndex();
        if (selectedItem != -1) {
            String word = (resultAfterSearch.get(selectedItem));
            Pronunciation.speech(word);
        } else if (! wordTf.getText().equals("")) {
            Pronunciation.speech(wordTf.getText());
        }
    };

    /**
     * Action: Tra từ ở ô tra từ và lấy ví dụ từ Oxford nếu có, nếu không có từ nào GIỐNG HỆT thì in thông báo.
     */
    ActionListener searchBtnListener = search -> {
        Word word = DictionaryManagement.dictionaryLookup(wordTf.getText());
        if (word != null) {
            speakBtn.setVisible(true);
            if (word.getPronunciation() != null) {
                textWordTarget.setText(" " + word.getTarget() + "   " + word.getPronunciation());
            } else {
                textWordTarget.setText(" " + word.getTarget());
            }
            String s = word.getExplain().replaceAll("=", "    • ").replaceAll("\\+", ":");
            textWordExplain.setText(DictionaryManagement.getNotification() + s);
            String oxfExample = Oxford.Example(wordTf.getText());
            if (! oxfExample.equals("")) {
                s += "\n\nVí dụ từ Oxford:\n" + oxfExample;
            }
            textWordExplain.setText(s);
            Dictionary.addWordToListRecent(word);
            DictionaryManagement.updateDataOfList(Dictionary.listRecentWord, Dictionary.fileListRecentWord);
        } else {
            speakBtn.setVisible(false);
            textWordTarget.setText(DictionaryManagement.getNotification());
            textWordExplain.setText("");
            scroll.setVisible(false);
            listSuggest.clearSelection();
        }
    };


    /**
     * Action: Hiển thị mghĩa từ được chọn.
     */
    ListSelectionListener selectedItemListener = selectWord -> {
        speakBtn.setVisible(true);
//        scroll.setVisible(false);
        int selectedItem = listSuggest.getSelectedIndex();
        if (selectedItem > -1) {
            Word word = DictionaryManagement.dictionaryLookup(resultAfterSearch.get(selectedItem));
            if (word != null) {
                if (word.getPronunciation() != null) {
                    textWordTarget.setText(" " + word.getTarget() + "   " + word.getPronunciation());
                } else {
                    textWordTarget.setText(" " + word.getTarget());
                }
                String s = word.getExplain().replaceAll("=", "    • ").replaceAll("\\+", ":");
                textWordExplain.setText(DictionaryManagement.getNotification() +s);
                Dictionary.addWordToListRecent(word);
                DictionaryManagement.updateDataOfList(Dictionary.listRecentWord, Dictionary.fileListRecentWord);
            } else {
                textWordExplain.setText(DictionaryManagement.getNotification());
            }
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }
}