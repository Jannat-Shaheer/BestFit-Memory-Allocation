import java.util.ArrayList;
import java.util.Scanner;

public class BestFitMemoryAlgorithm{
    static ArrayList<MemoryBlock> memory = new ArrayList<>();

    public void addProcess(int size){
        memory.add(new MemoryBlock(size));
    }

    public void allocateMemory(String processName, int requestedSize){
        int bestFitIndex = -1; //which block to allocate
        int minSizeDiff = Integer.MAX_VALUE; //diff b/w block and requested size

        for (int i=0; i<memory.size(); i++){
            MemoryBlock block = memory.get(i);
            if (block.isFree && block.size >= requestedSize){
                int diff = block.size - requestedSize; //get extra space
                if (diff < minSizeDiff){ //get block with minimum extra space
                    minSizeDiff = diff;
                    bestFitIndex = i;
                }
            }
        }

        if (bestFitIndex == -1){ //if no large enough block is available
            System.out.println("Memory allocation failed. No suitable block available.");
            return;
        }

        MemoryBlock bestfitBlock = memory.get(bestFitIndex);
        if (bestfitBlock.size == requestedSize){ //if a perfect fit, allocate entire block
            bestfitBlock.isFree = false;
            bestfitBlock.name = processName;
        }
        else{
            memory.set(bestFitIndex, new MemoryBlock(requestedSize)); // else fragment memory block
            memory.get(bestFitIndex).isFree = false;
            memory.get(bestFitIndex).name = processName;
            memory.add(bestFitIndex+1, new MemoryBlock(bestfitBlock.size - requestedSize));
        }

        System.out.println("Memory allocated to process " + processName);
    }

    public void deallocateMemory(String processName){
        boolean found = false;
        for (int i=0; i<memory.size(); i++){
            MemoryBlock block = memory.get(i);
            
            //if the block is allocated and has given process name, then deallocate memory
            if (!block.isFree && block.name.equals(processName)){ 
                block.isFree = true;                             
                block.name = null;                                
                found = true;
                System.out.println("Memory deallocated for process " + processName);

                //join adjacent memory block if free after deallocation
                coalesceMemory(i); 
                break;
            }
        }
        if (!found){
            System.out.println("No process found with name " + processName);
        }
    }    

    private void coalesceMemory(int index) {
        // Get the current block that was just deallocated
        MemoryBlock currentBlock = memory.get(index);
        
        // Check if the next block is free
        if (index < memory.size() - 1 && memory.get(index + 1).isFree) {
            MemoryBlock nextBlock = memory.get(index + 1);
            
            // Merge the two blocks but retain the original block sizes
            currentBlock.size += nextBlock.size; 
            memory.remove(index + 1);  // Remove the next block as it is now part of the current block
        }
    }
    
    public void showMemory(){ //display memory status
        System.out.println("===== Memory =====");
        for (int i=0; i<memory.size(); i++){
            MemoryBlock block = memory.get(i);
            System.out.println("Block " + i + " : " + block.size + " KB | " +
            (block.isFree ? "Free" : "Allocated to " + block.name));
        }
    }
}


