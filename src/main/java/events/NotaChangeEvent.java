package events;

import entities.Nota;

public class NotaChangeEvent  implements Event{
    private ChangeEventType type;
    private Nota nota,oldNota;

    public NotaChangeEvent(ChangeEventType type, Nota nota) {
        this.type = type;
        this.nota = nota;
    }

    public NotaChangeEvent(ChangeEventType type, Nota nota, Nota oldNota) {
        this.type = type;
        this.nota = nota;
        this.oldNota = oldNota;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Nota getNota() {
        return nota;
    }

    public Nota getOldNota() {
        return oldNota;
    }
}
