package GUI;

import Dictionary.DictionaryManagement;
import Dictionary.Word;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyDialog extends JDialog implements ActionListener {
    JDialog addWindow = new JDialog();
    JTextField wordTf = new JTextField();
    JTextArea meaningTf = new JTextArea();
    JButton okButton = new JButton();
    JButton cancelButton = new JButton("Huỷ");
    JLabel editWordLabel = new JLabel();
    JLabel meaningLabel = new JLabel();
    JFrame dialogFrame = new JFrame();
    Word meaningWord = new Word();
    JScrollPane scrollMeaningWord = new JScrollPane(meaningTf, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    /**
     * Hàm tạo cửa sổ khi nhấn thêm hoặc xóa từ.
     * @param option sửa từ hoặc thêm từ ~ "modifyWord"/"addWord".
     */
    public MyDialog(String option) {
        okButton.setBounds(60, 770, 200, 55);
        okButton.setBorderPainted(true);
        okButton.addActionListener(this);

        cancelButton.setBounds(420,770,200,55);
        cancelButton.setBorderPainted(true);
        cancelButton.addActionListener(this);

        editWordLabel.setFont(new Font("Times New Roman", Font.ITALIC,20));
        editWordLabel.setBounds(15,10,300,30);

        wordTf.setBounds(15, 42, 655,50);
        wordTf.setFont(new Font("Times New Roman", Font.BOLD,20));

        meaningLabel.setFont(new Font("Times New Roman", Font.ITALIC,20));
        meaningLabel.setBounds(15,100,300,30);

        meaningTf.setBounds(15, 130, 655,620);
        meaningTf.setFont(new Font("Times New Roman", Font.ITALIC,20));

        scrollMeaningWord.setBounds(15, 130, 655,620);
        scrollMeaningWord.setOpaque(false);
        scrollMeaningWord.setBackground(Color.white);
        scrollMeaningWord.setBorder(BorderFactory.createEmptyBorder());

        //addWindow.add(dialogFrame);
        addWindow.add(okButton);
        addWindow.add(cancelButton);
        addWindow.add(wordTf);
        addWindow.add(meaningLabel);
        addWindow.add(editWordLabel);
        addWindow.add(scrollMeaningWord);
        addWindow.setLayout(null);
        addWindow.setSize(700,900);
        addWindow.setVisible(true);

        if (option.equals("modifyWord")) {
            addWindow.setTitle("Sửa từ");
            okButton.setText("Sửa từ");
            okButton.addActionListener(this.modifyWordButton);
            editWordLabel.setText("Từ bạn muốn sửa");
            meaningLabel.setText("Nghĩa của từ được sửa");
            int selectedItem = GUI.listSuggest.getSelectedIndex();
            wordTf.setText(GUI.resultAfterSearch.get(selectedItem));
            meaningWord = DictionaryManagement.dictionaryLookup(wordTf.getText().trim());
            if (meaningWord != null) {
                meaningTf.setText(meaningWord.getExplain().replaceAll("=", "    • ").replaceAll("\\+", ":"));
            }
        } else if (option.equals("addWord")) {
            addWindow.setTitle("Thêm từ");
            okButton.setText("Thêm từ");
            okButton.addActionListener(this.addWordButton);
            editWordLabel.setText("Từ bạn muốn thêm");
            meaningLabel.setText("Nghĩa của từ được thêm");
        } else {
            System.out.println("Có biến, sos!");
        }
    }

    /**
     * Action: Khi nhấn thêm từ.
     */
    ActionListener addWordButton = speak -> {
        if (wordTf.getText().equals("") && meaningTf.getText().equals("")) {
            JOptionPane.showMessageDialog(dialogFrame, "Bạn không được để trống từ hoặc nghĩa!");
        } else {
            DictionaryManagement.addWord(wordTf.getText(),meaningTf.getText());
            System.out.println(DictionaryManagement.getNotification());
        }
    };

    /**
     * Action: Khi nhấn nút sửa từ.
     */
    ActionListener modifyWordButton = speak -> {
        if (wordTf.getText().equals("") && meaningTf.getText().equals("")) {
            JOptionPane.showMessageDialog(dialogFrame, "Bạn không được để trống từ hoặc nghĩa!");
        } else {
            DictionaryManagement.modifyWord(wordTf.getText(),meaningTf.getText());
            System.out.println(DictionaryManagement.getNotification());
        }
    };

    public void actionPerformed(ActionEvent e) {
        addWindow.dispose();
    }
}
