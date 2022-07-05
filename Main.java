import java.util.InputMismatchException;
import java.util.*;

class ToyCar {
    
    //Declare Instance
    private String Code;
    private double Price;
    private int Quantity;
    private final static double Insurance = 0.02;
    
    //Constructor
    public ToyCar(String Code, double Price, int Quantity) {
        this.Code = Code;
        this.Price = Price;
        this.Quantity = Quantity;
    }
    
    //Get method
    public String getCode() {
      return Code;
    }
    
    //Get method
    public double getPrice() {
        return Price;
    }
    
    //Get method
    public int getQuantity() {
        return Quantity;
    }
    
    //Calculate method
    public double getTotal() {
        return getPrice() * getQuantity(); //Calculate the Total of inventory worth
    }
    
    //Calculate method
    public double getInsurance() {
       return getTotal() * Insurance;
    }
    
    //Display String info
    public String toString() {
        return  "Code: " + getCode() + "  Total Cost:" + "$" + getTotal() + "  Insurance Cost:" + " $" + getInsurance();
    }
}

class ToyCarElect extends ToyCar {
    //Declare Instance
    private double Battery_Duration;
    private double Charge_Duration;
    private final static double Insurance2 = 0.10;
    
    //Constructor
    public ToyCarElect(String Code, double Price, int Quantity, double Battery_Duration, double Charge_Duration) {
        super(Code, Price, Quantity);
        this.Battery_Duration = Battery_Duration;
        this.Charge_Duration = Charge_Duration;
    }
    
    public double getBatteryDuration() {
        return Battery_Duration;
    }
    
    public double getChargeDuration() {
        return Charge_Duration;
    }
    
    @Override 
    //Calculate method
    public double getTotal() {
        return super.getPrice() * super.getQuantity(); //Calculate Total of inventory worth
    }
    
    @Override
    //Calculate method
    public double getInsurance() {
       return getTotal() * Insurance2;
    }
    
    @Override
    //Display String info
    public String toString() {
        return super.toString() + " Battery duration: " + getBatteryDuration() + " Mins" + " Charging duration: " + getChargeDuration() + "  Mins";
    }
 }
 
    public class Main {
        public static void main(String[] args) {
            //Declare and initialise Array
	    ArrayList<ToyCar> List = new ArrayList();
	    getUserInput(List);
         }
	
	public static void getUserInput(ArrayList<ToyCar> List) {
	   //Declare Instance
	    Scanner scan = new Scanner(System.in);
	    int Choice = -1;
	    
	    do {
	        System.out.println();
	        System.out.println("1 Add inventory");
	        System.out.println("2 Remove inventory");
		System.out.println("3 Show all inventory");
		System.out.println("4 Search inventory by car model");
	     	System.out.println("5 Search inventory by car price");
	     	System.out.println("6 Search inventory by car battery duration");
		System.out.println("7 Quit");
	        System.out.println("Your selection: ");
	        int Input = scan.nextInt();
	        scan.nextLine();    //clear buffer
	        
	        switch(Input) {
	            case 1:
	            Add(List);
	            break;
	            
	            case 2:
	            Remove(List);
	            break;
	            
	            case 3:
	            DisplayAll(List);
	            break;
	            
	            case 4:
	            SearchInventory(List);
	            break;
	            
	            case 5:
	            InventoryCarPrice(List);
	            break;
	            
	            case 6:
	            InventoryCarBatteryDuration(List);
	            break;
	            
	            case 7:
	            System.out.println("Quit");
	            System.exit(0);
	            break;
	        }
	        
	    }while(Choice != 7);
		
	 }
	 
	 public static void Add(ArrayList<ToyCar> List) {
	     Scanner scan = new Scanner(System.in);
	     boolean yes = true;
             boolean check = false;
             do {
                  try {
                       System.out.printf("Code: ");
                       String Code = scan.nextLine();
                       for(ToyCar T : List) {
                           if(Code.equals(T.getCode())) {
                               System.out.println("Same Code");
                               check = true;
                            }
                       }
                       if(check == true)
                       break;

                System.out.printf("Price: ");
                double Price = scan.nextDouble();

                System.out.printf("Quantity: ");
                int Quantity = scan.nextInt();
                scan.nextLine(); // clear buffer

                if(Price < 0 || Quantity < 0) {
                    System.out.println("No Negative Number");
                    break;
                }
                
                System.out.printf("Does it have a Battery (y/n)? ");
                String battery = scan.nextLine();
                
                if(battery.equals("y")) {
                    System.out.printf("Battery Duration fully Charge in Mins: ");
                    double Battery_Duration = scan.nextDouble();

                    if(Battery_Duration < 0) {
                        System.out.println("No Negative Number");
                        break;
                    }

                    System.out.printf("How it takes for Charge Duration in Mins? ");
                    double Charge_Duration = scan.nextDouble();

                    if(Charge_Duration < 0) {
                        System.out.println("No Negative Number");
                        break;
                    }

                    ToyCarElect test = new ToyCarElect(Code, Price, Quantity, Battery_Duration, Charge_Duration);
                    List.add(test);
                    yes = false;
                }
                else {
                  ToyCar test2 = new ToyCar(Code, Price, Quantity);
                  List.add(test2);
                  yes = false;
                }
                  
            } catch(InputMismatchException error) {
                System.out.println("Wrong key");
                yes = false;
            }
            
         }while(yes == true);
     }
	 
	 public static void Remove(ArrayList<ToyCar> List) {
	     boolean yes = true;
             boolean check = false;
             Scanner scan = new Scanner(System.in);
         
             System.out.printf("Remove Model: "); //Remove Inventory 
             String Code = scan.nextLine();
         
             for(ToyCar T : List) {
             if(Code.equals(T.getCode())) {
                if(T.getQuantity() > 0) {
                    List.remove(T);
                    check = true;
                    System.out.println("Model has Been Remove");
                }
                else if(T.getQuantity() == 0) {
                    System.out.println("Not been Remove as Quantity is 0");
                    check = false;
                }
            }
        }
        if(yes == true) {
            System.out.println("Invalid Model");
        }
     }
	 
        public static void DisplayAll(ArrayList<ToyCar> List) {
	   for(ToyCar T : List) {
	       System.out.println(T.toString());
	    }
	 }
	 
	 public static void SearchInventory(ArrayList<ToyCar> List) {
	     boolean yes = false;
	     Scanner scan = new Scanner(System.in);
	     
             System.out.printf("Search Car Model: "); //Search Inventory by Car Model
             String Code = scan.nextLine();

             for(ToyCar T : List) {
                 if(Code.equals(T.getCode())) {
                     System.out.println(T.toString());
                     yes = true;
                 }
              }
             if(yes == false) {
                 System.out.println("Car Model Not Found");
             }
	 }
	 
	 public static void InventoryCarPrice(ArrayList<ToyCar> List) {
	     boolean yes = true;
	     Scanner scan = new Scanner(System.in);
	     
	     do {
	         try {
	             System.out.printf("Lower Bound: ");
                     int lb = scan.nextInt();
                     scan.nextLine();
                             
                     System.out.printf("Upper Bound: ");
                     int ub = scan.nextInt();
                     scan.nextLine();
                 
                     if(lb < 0 || ub < 0) {
                        System.out.println("Cant be Negative");
                        break;
                     }
                 
                    for(ToyCar T : List) {
                         if(T.getTotal() >= lb && T.getTotal() <= ub) {
                               System.out.println(T.toString() + " Price Range: " + T.getTotal());
                               yes = false;
                          }
                          else {
			      System.out.println("No matching prices found!!!");
			       yes = false;
                          }
                    } 
	         } catch(InputMismatchException error) {
	             System.out.println("Re-enter Number again");
                     scan.nextLine();
                     yes = false;
	         }
	     }while(yes == true);
	 }
	 
	 public static void InventoryCarBatteryDuration(ArrayList<ToyCar> List) {
	     boolean yes = true;
	     Scanner scan = new Scanner(System.in);
	     do {
	         try {
	               System.out.printf("Battery Minimum in Mins: ");
                       double Battery_Minimum = scan.nextDouble();

                       if(Battery_Minimum < 0) {
                          System.out.println("No Negative Number");
                          break;
                       }             
    	           
    	               for(ToyCar T : List) {
                            if(T instanceof ToyCarElect) { //From Parents to child so use downcasting 
                                  ToyCarElect T2 = (ToyCarElect) T;

                                   if(Battery_Minimum < T2.getBatteryDuration()) {
                                        System.out.println(T.toString());
                                        yes = false;
                                   }
                             } 
                             else {
                                System.out.println("No duration found");
                                yes = false;
                             }
                        }
	             
	         } catch(InputMismatchException error) {
	             System.out.println("Error");
                     scan.nextLine();
		     yes = false;
	         }  
	     }while(yes == true);
	 }
}
