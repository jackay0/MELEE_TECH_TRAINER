import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Physics { //handles collision




public static boolean Collision(EntityA a, EntityB b) {
	
if(a.getBounds().intersects(b.getBounds())) {
	
	
	return true;


}
return false;
}






}
