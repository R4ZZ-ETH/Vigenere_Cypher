import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Gui extends JFrame {
    Cypher cypher = new Cypher("3591255939063529");
    JTextArea resultArea;
    String result;
    JTextField input;
    JButton cipherButton, decipherButton;
    StringSelection stringSelection;
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    public Gui(){
        input = new JTextField(20);
        cipherButton = new JButton("Cipher");
        decipherButton = new JButton("Decipher");
        resultArea = new JTextArea(10, 30);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        setTitle("Cipher-Decipher");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10));


        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(input);
        topPanel.add(cipherButton);
        cipherButton.addActionListener(e->{
            result = cypher.encrypt(input.getText());
            resultArea.setText("Result: " + result);
            stringSelection = new StringSelection(result);
            clipboard.setContents(stringSelection, null);
        });
        topPanel.add(decipherButton);
        decipherButton.addActionListener(e->{
            result = cypher.decrypt(input.getText());
            resultArea.setText("Result: " + result);
            stringSelection = new StringSelection(result);
            clipboard.setContents(stringSelection, null);
        });
        JPanel resultPanel = new JPanel(new FlowLayout());
        resultPanel.add(resultArea);
        add(topPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
        setVisible(true);
    }   
}
