package com.flink;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.hadoop.shaded.com.google.common.base.Objects;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;

/**
 * Created by vstoyak on 15-11-15.
 */
public class SessionTrigger<IN, W extends Window> extends Trigger<IN, W> {
    private static final long serialVersionUID = 1L;

    @Override
    public TriggerResult onElement(IN element,
                                   long timestamp,
                                   W window,
                                   TriggerContext ctx) throws Exception {


        //ctx.registerEventTimeTimer(timestamp + sessionTimeout);
        //System.out.print("Triggering " + window +"\n");
        if(element.toString().contains("sins")) {
        	return TriggerResult.FIRE_AND_PURGE;
        }
        return TriggerResult.CONTINUE;
    }

    @Override
    public TriggerResult onProcessingTime(long time,
                                          W window,
                                          TriggerContext ctx) throws Exception {
        //System.out.print("onProcessingTime " + window +"\n");
        return TriggerResult.CONTINUE;
    }

    @Override
    public TriggerResult onEventTime(long time,
                                     W window,
                                     TriggerContext ctx) throws Exception {
        //System.out.print("onEventTime " + window +"\n");
        return TriggerResult.CONTINUE;
    }

	@Override
	public void clear(W arg0, org.apache.flink.streaming.api.windowing.triggers.Trigger.TriggerContext arg1)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}