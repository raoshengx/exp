package expone;

import java.util.Map;
import java.util.Set;

/**
 * 学习交流欢迎大佬指正
 * @author raosheng 1017831729@qq.com
 *
 */
public class ExcuteTask implements Runnable{
    private  Map<String,Object> reagents;
    public ExcuteTask(Map<String,Object> reagents){
        this.reagents=reagents;
    }
    //多线程工作内容
    @Override
    public void run() {
        synchronized (reagents){
            Set<Map.Entry<String,Object>> entrySet=reagents.entrySet();
            for(Map.Entry entry:entrySet){
                if(ConstantUtils.poison.equals(entry.getValue())){
                    System.out.println("===>POISON FIND OUT,THAT IS NO:"+entry.getKey());
                }
            }
        }

    }
}
