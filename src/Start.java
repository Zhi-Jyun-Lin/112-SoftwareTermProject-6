import java.awt.EventQueue;

import javax.swing.JFrame;

public class Start {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            CharacterGui chargui = new CharacterGui();
        });
    }
}
