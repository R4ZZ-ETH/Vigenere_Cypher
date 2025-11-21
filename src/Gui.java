import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Gui extends JFrame {
    Cypher cypher = new Cypher("3591255939063529");
    JLabel resultLabel;
    String result;
    JTextField input;
    JButton cipherButton, decipherButton, copyButton;
    StringSelection stringSelection;
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    public Gui(){
        resultLabel = new JLabel("Result: ");
        input = new JTextField(20);
        cipherButton = new JButton("Cipher");
        decipherButton = new JButton("Decipher");
        copyButton = new JButton("Copy onto clipboard");
        setTitle("Cipher-Decipher");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(input);
        panel.add(cipherButton);
        cipherButton.addActionListener(e->{
            result = cypher.encrypt(input.getText());
            resultLabel.setText("Result: " + result);
        });
        panel.add(decipherButton);
        decipherButton.addActionListener(e->{
            result = cypher.decrypt(input.getText());
            resultLabel.setText("Result: " + result);
        });
        panel.add(resultLabel);
        panel.add(copyButton);
        copyButton.addActionListener(e->{
            String copyText = resultLabel.getText().substring(8, resultLabel.getText().length()); 
            stringSelection = new StringSelection(copyText);
            clipboard.setContents(stringSelection, null);
        });
        add(panel);
        setVisible(true);
    }   
}
