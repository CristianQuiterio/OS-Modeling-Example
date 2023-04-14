/*
 * Cristian Quiterio
 * NQL182
 * 10/9/22
 * CPSC2800 Section 1 40676
 */
package virtualaddressdecomposer;

import java.util.Scanner;

public class VirtualAddressDecomposer {

    public static void main(String[] args) {
        
        //Setting up initial variables to be read in from the user, page size and virtual address
        Scanner userInput = new Scanner(System.in);
        int pageSize = 0;
        double virtualAddress = 0;
        
   
        //Prompting the user to input the system page size and rejects input that is not valid
        System.out.println("Please enter the system page size:");         
        while(true){ 
            if(!userInput.hasNextInt()){
                System.out.println("ERROR: Invalid input. Please enter an integer.");
                userInput.next();
            }
            else{
                int i = userInput.nextInt();
                if(i != 512 && i != 1024 && i != 2048 && i != 4096 && i != 8192 && i != 16384){
                    System.out.println("ERROR: Please enter an power of 2 between 512 and 16384 (Inclusive):");
                }
                else{
                    pageSize = i;
                    break;
                }
            }   
        }
        
        //Prompting the user to input the virtual address and rejects input that is not valid
        System.out.println("Please enter the virtual address:");
        while(true){            
            if(!userInput.hasNextDouble()){
                System.out.println("ERROR: Invalid input. Please enter an integer.");
                userInput.next();
            }
            else{
                double i = userInput.nextDouble();
                if((i < 0) || (0 < Double.compare(i, 4294967295L))){
                    System.out.printf("ERROR: Please enter an integer from 0 to 4294967295:\n");
                }
                else{     
                    virtualAddress = i;
                    break;
                }
            }
        }
        
        //Setting up the variables to be found
        double virtualPage = 0;
        double offset = 0;
        
        //Subtracts the size of pages that are full from the address size until the address can fit on one page 
        while(virtualAddress > pageSize){
            virtualAddress -= pageSize;
            virtualPage++;
        }
        offset = virtualAddress;
        
        //Displays the page and offset the address was found at
        System.out.println("This address is in the virtual page:\n" + virtualPage + "\nAt offset:\n" + offset);

    }
    
}
