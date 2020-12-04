
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public class Main {

		public static void main(String[] args) {
			Controller [] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
			Controller controller = null;
			/*
			JInputJoystick joystick = new JInputJoystick(Controller.Type.STICK, Controller.Type.GAMEPAD);
			System.out.println(joystick.isControllerConnected());
			System.out.println(joystick.getControllerName());
			System.out.println(joystick.getControllerType());
			   for (Controller Controller : controllers) {
		           
		            Controller.poll();
			   }
			if( !joystick.pollController() ) {
				   System.out.println("Controller disconnected!");
				   // Do some stuff.
				}
			System.out.println(joystick.getNumberOfButtons());
		
			// Left controller joystick
			int xValuePercentageLeftJoystick = joystick.getX_LeftJoystick_Percentage();
			int yValuePercentageLeftJoystick = joystick.getY_LeftJoystick_Percentage();
			 
			// Right controller joystick
			int xValuePercentageRightJoystick = joystick.getX_RightJoystick_Percentage();
			int yValuePercentageRightJoystick = joystick.getY_RightJoystick_Percentage();
			 
			// If controller is a gamepad type.
			if(joystick.getControllerType() == Controller.Type.GAMEPAD)
			{ // Must check if controller is a gamepad, because stick type controller also have Z axis but it's for right controller joystick.
			   // If Z Axis exists.
			   if(joystick.componentExists(Component.Identifier.Axis.Z)){
			      int zAxisValuePercentage = joystick.getZAxisPercentage();
			   }
			}
		*/
			
			
			
			/*float hatSwitchPosition = joystick.getHatSwitchPosition();
			 
			
			if(Float.compare(hatSwitchPosition, Component.POV.OFF) == 0){
			  // Hat switch is not pressed. The same as Component.POV.CENTER
			}else if(Float.compare(hatSwitchPosition, Component.POV.UP) == 0){
			   // Do stuff when UP is pressed.
				System.out.println("up");
			}else if(Float.compare(hatSwitchPosition, Component.POV.DOWN) == 0){
			   // Do stuff when DOWN is pressed.
			}else if(Float.compare(hatSwitchPosition, Component.POV.LEFT) == 0){
			   // Do stuff when LEFT is pressed.
			}else if(Float.compare(hatSwitchPosition, Component.POV.RIGHT) == 0){
			   // Do stuff when RIGHT is pressed.
			}else if(Float.compare(hatSwitchPosition, Component.POV.UP_LEFT) == 0){
			   // Do stuff when UP and LEFT is pressed.
			}else if(Float.compare(hatSwitchPosition, Component.POV.UP_RIGHT) == 0){
			   // Do stuff when UP and RIGHT is pressed.
			}else if(Float.compare(hatSwitchPosition, Component.POV.DOWN_LEFT) == 0){
			   // Do stuff when DOWN and LEFT is pressed.
			}else if(Float.compare(hatSwitchPosition, Component.POV.DOWN_RIGHT) == 0){
			   // Do stuff when DOWN and RIGHT is pressed.
			}
			//System.out.println(joystick.getX_LeftJoystick_Value());
			//System.out.println(joystick.getHatSwitchPosition());
			// Number of buttons.
			int numberOfButtons = joystick.getNumberOfButtons();
			
			 */
			// Button one on the controller.
			/*
			 * 
			 */
			//boolean joystickButton_1 = joystick.getButtonValue(0);
			for(int i = 0; i<controllers.length; i++)
			{
				if(controllers[i].getType() == Controller.Type.STICK)
				{
					controller = controllers[i];
					
			}
				//System.out.println(controller);
				
			}
			
			//controller = controllers[4];
			Component[] components = controller.getComponents();
			for(int i = 0; i <components.length; i++) {
				//System.out.println(components[i]);
			}
			
		
			//controller = controllers[4];
		//0 is X
		//1 is A
		//2 is B	
		//3 is Y	
		//4 is L
		//5 is R
		//6 is Y
		//7 is Z
		//8 is ?
		//9 is START
		//10 is ?
		//11 is ?
		//12 is D Up
		//13 is D Right
	    //14 is D Down
		//15 is D Left
		//16 is D 
			//
			
		
		
		EventQueue eventQueue = controller.getEventQueue();
		Event event = new Event();
		Boolean stopped = false;
		
		while (!stopped)
		{
			controller.poll();
			eventQueue.getNextEvent(event);
			
			
			Component component = event.getComponent();
			
			if(component != null) 
			{
				Component.Identifier identifier = component.getIdentifier();
				float data = component.getPollData();
				if(identifier == Component.Identifier.Button._0)
				{
					System.out.println("pressed");				
					}
				else if(identifier == Component.Identifier.Axis.X) {
					System.out.println("z:" + data);
				}
				//else if(identifier == Component.Identifier.Axis.X) {
					//System.out.println("x:" + data);
				//}
			
			
			
			}
			
		}
		}
		
}
