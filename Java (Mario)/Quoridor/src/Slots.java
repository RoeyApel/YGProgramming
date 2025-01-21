import java.awt.Image;

import javax.swing.ImageIcon;

public enum Slots {
    NORMAL("images\\normal_slot.png", "images\\normal_slot_marked.png"),
    WINNER("images\\winning_slot.png", "images\\winning_slot_marked.png");

    private Image slotImage;
    private Image markedSlotImage;

    Slots(String path, String pathMarked) {
        slotImage = new ImageIcon(getClass().getResource(path)).getImage();
        markedSlotImage = new ImageIcon(getClass().getResource(pathMarked)).getImage();
    }

    public Image getSlot() {
        return slotImage;
    }

    public Image getMarkedSlot() {
        return markedSlotImage;
    }

}
