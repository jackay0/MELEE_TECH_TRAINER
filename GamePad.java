package boyandahalf;

import org.lwjgl.glfw.GLFW;

public class GamePad{

    //private GLFWJoystickCallback x;


    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            boolean present = GLFW.glfwJoystickPresent(i);
            System.out.println("Controller " + i + ": " + present + GLFW.glfwGetJoystickName(i));
        }
    }

}