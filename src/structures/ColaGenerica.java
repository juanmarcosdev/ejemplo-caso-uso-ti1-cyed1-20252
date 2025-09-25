package structures;
import java.lang.IndexOutOfBoundsException;

public class ColaGenerica<T> {
  private int head;
  private int tail;
  private int size_max;
  private int size;
  private T[] arreglo;


  public ColaGenerica(int n) {
    this.head = 1;
    this.size_max = n;
    this.size = 0;
    this.tail = 1;
    this.arreglo = (T[]) new Object[n];
  }

  public void enqueue(T n) throws IndexOutOfBoundsException {
    if (this.size_max == size) {
      throw new IndexOutOfBoundsException();
    } else {
      this.size++;
      arreglo[this.tail-1] = n;
      this.tail = (this.tail >= this.size_max) ? 1 : this.tail + 1;
    }
  }

  public T dequeue() throws IndexOutOfBoundsException {
    if (this.size == 0) {
      throw new IndexOutOfBoundsException();
    } else {
      this.size--;
      T val = arreglo[this.head-1];
      this.head = (this.head >= this.size_max) ? 1 : this.head + 1;
      return val;
    }
  }

  public int getHead() {
    return head;
  }

  public int getTail() {
    return tail;
  }

  public int getSize_max() {
    return size_max;
  }

  public int getSize() {
    return size;
  }

  public T[] getArreglo() {
    return arreglo;
  }

  public void setHead(int head) {
    this.head = head;
  }

  public void setTail(int tail) {
    this.tail = tail;
  }

  public void setSize_max(int size_max) {
    this.size_max = size_max;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public void setArreglo(T[] arreglo) {
    this.arreglo = arreglo;
  }
}
