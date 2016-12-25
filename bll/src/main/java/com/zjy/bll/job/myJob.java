package com.zjy.bll.job;

import java.util.Date;

/**
 * Created by jyzeng on 2016/12/20.
 */
public class myJob {
    private int counter = 0;
    public void execute()  {
        System.out.format("%1$tF %1$tT (第%2$d次执行)\n", new Date(), counter++);
    }
}
