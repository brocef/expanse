import java.util.Queue;

import core.Resource;
import core.ai.Action;
import core.ai.Day;
import core.ai.Sentinence;


public class DebugAI extends Sentinence {

	@Override
	public Queue<Action> doStuff(Day d) {
		Resource[] rsc = d.getPopulace().getHearth().getResources();
		for (Resource r:rsc) {
			System.out.println(r.getName()+" : "+r.getRemainingResources());
		}
		return null;
	}

}
