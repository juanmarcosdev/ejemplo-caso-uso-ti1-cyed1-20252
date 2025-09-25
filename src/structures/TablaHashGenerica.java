package structures;
import java.util.LinkedList;
import java.lang.UnsupportedOperationException;
import java.lang.NullPointerException;
import model.Paciente;
import model.Medico;

public class TablaHashGenerica<T> {
  private LinkedList<T>[] tabla;
  private int size;


  public TablaHashGenerica(int n) throws Exception {
    this.tabla = (LinkedList<T>[]) new LinkedList[n];
    this.size = n;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public boolean search(int k, T value) throws Exception {
    int pos = this.hashFunction(k);
    LinkedList<T> found = this.tabla[pos];
    if (found != null) {
      for (T c : found) {
        if (c.equals(value)) {
          return true;
        }
      }
    }
    return false;
  }

  public void insert(int k, T value) throws Exception {
    int pos = this.hashFunction(k);
    LinkedList<T> found = this.tabla[pos];
    if (found == null) {
      found = new LinkedList<T>();
      found.add(value);
      this.tabla[pos] = found;
    } else {
      found.add(value);
      this.tabla[pos] = found;
    }
  }

  public void delete(int k, T value) throws Exception {
    int pos = this.hashFunction(k);
    LinkedList<T> found = this.tabla[pos];
    if (found != null) {
      found.remove(value);
      this.tabla[pos] = found;
    }
  }

  private int hashFunction(int k) throws Exception {
    return k % this.size;
  }

  public T searchValueByKey(int key) throws Exception {
    int pos = this.hashFunction(key);
    LinkedList<T> bucket = this.tabla[pos];
    if (bucket != null) {
        for (T item : bucket) {
            int id = -1;
            if (item instanceof Medico) {
                id = ((Medico)item).getIdMedico();
            } else if (item instanceof Paciente) {
                id = ((Paciente)item).getIdPaciente();
            }
            if (id == key) {
                return item;
            }
        }
    }
    return null; 
}
}
