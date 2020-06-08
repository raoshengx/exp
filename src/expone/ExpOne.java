package expone;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 学习交流欢迎大佬指正
 *
 * 1000个药瓶中有一瓶毒药 10个小白鼠 无限多个试剂瓶 如何快速找到有毒试剂瓶
 *
 * @author raosheng 1017831729@qq.com
 */
public class ExpOne {
    private static int ratNum=10;
    private static int reagentNum=100000;
    //1000个药瓶中有一瓶毒药 10个小白鼠 无限多个试剂瓶 如何快速找到有毒试剂瓶
    public static void main(String []args){
        //1.投毒
        String reagents[]=initPoison(reagentNum);
        //2.小白鼠分组成10个小组:0-99号用1号小白鼠,100-199用2小白鼠...900-999用10号小白鼠
        //3.每组进行筛选
        ExecutorService executorService=Executors.newCachedThreadPool();
        for(int i=1;i<=ratNum;i++){
            Map<String,Object> spiltReagents=spiltReagents(reagents,i);
            ExcuteTask excuteTask=new ExcuteTask(spiltReagents);
            executorService.execute(excuteTask);
        }
        executorService.shutdown();
    }

    private static String [] initPoison(int reagentNum){
        String reagents[]=new String[reagentNum];
        for(int i=0;i<reagentNum;i++){
            reagents[i]=ConstantUtils.safe;
        }
        Random random=new Random();
        int no=random.nextInt(reagentNum);
        reagents[no]=ConstantUtils.poison;
        System.out.println("===>预设有毒试剂编号:"+no);
        return reagents;
    }

    private static Map<String,Object> spiltReagents(String []reagents,int groupNo){
        int groupSize=reagentNum/ratNum;
        int groupStart=(groupNo-1)*groupSize;
        Map<String,Object> spiltReagents=new HashMap<>();
        for(int i=groupStart;i<groupStart+groupSize-1;i++){
            spiltReagents.put(String.valueOf(i),reagents[i]);
        }
        return spiltReagents;
    }
}
