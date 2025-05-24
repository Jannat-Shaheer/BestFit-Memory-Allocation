public class MemoryBlock{
    int size; //size of block in KBs
    boolean isFree;
    String name;

    MemoryBlock(int size){
        this.name = null;
        this.size = size;
        this.isFree = true;
    }
}

