import java.io.Serializable;

public class RecursoCompartido implements Serializable {
    private Integer data;
    
    public RecursoCompartido(Integer data) {
        this.data = data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public String toString() {
        return String.valueOf(this.data);
    }
}
