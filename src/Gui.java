import javax.swing.*;
import java.awt.*;;

public class Gui extends JFrame {
    Cypher cypher = new Cypher("3591255939063529");
    JLabel resultLabel;
    String result;
    JTextField input;
    JButton cipherButton, decipherButton;
    public Gui(){
        resultLabel = new JLabel("Result: ");
        input = new JTextField(20);
        cipherButton = new JButton("Cipher");
        decipherButton = new JButton("Decipher");
        setTitle("Cypher-Decypher");
        setSize(2000, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel(new GridLayout(1, 4, 4, 4));
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
        add(panel);
        setVisible(true);
    }   
}
